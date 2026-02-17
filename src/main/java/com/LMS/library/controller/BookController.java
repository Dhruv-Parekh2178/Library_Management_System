package com.LMS.library.controller;

import com.LMS.library.dtos.BookDTO;
import com.LMS.library.dtos.BookRequestDTO;
import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Book;
import com.LMS.library.service.author.AuthorService;
import com.LMS.library.service.book.BookService;
import com.LMS.library.service.category.CategoryService;
import com.LMS.library.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;


    @GetMapping
    public String getAllBooks(Model model){
        List<BookDTO> books = bookService.getbooks();
        model.addAttribute("books" , books);
        return "book/book_list";
    }

    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<BookDTO>>> getAllBooksData(){
        List<BookDTO> books = bookService.getbooks();
        return ResponseEntity.ok(new ApiResponse<>(true,"success" , books));
    }

    @GetMapping("/get/{id}")
    public String getBookById(@PathVariable Long id, Model model){
        BookDTO book = bookService.getBookById(id);
        if(book==null)
            return "redirect:/book";
        model.addAttribute("book" , book);
        return "book/book_by_id";
    }

    @GetMapping("/get/{id}/data")
    public ResponseEntity<ApiResponse<BookDTO>> getBookByIdData(@PathVariable Long id){
        BookDTO book = bookService.getBookById(id);
        if(book==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , book));
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("book", new BookRequestDTO());
        model.addAttribute("authors" ,authorService.getAuthors());
        model.addAttribute("categories" , categoryService.getCategories());
        model.addAttribute("users" ,userService.getUsers());
        return "book/book_form";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute BookRequestDTO bookRequestDTO){


            bookService.saveBook(bookRequestDTO,bookRequestDTO.getAuthorIds(),bookRequestDTO.getCategoryIds(),bookRequestDTO.getUserIds());

         return "redirect:/book";
    }

    @GetMapping("/add/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> addBook(@RequestBody Book book){
        return ResponseEntity.ok().body(new ApiResponse<>(true , "success" , "Book added Successfully"));
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookRequestDTO bookRequestDTO = modelMapper.map(bookService.getBookById(id),BookRequestDTO.class);
        model.addAttribute("book", bookRequestDTO);
        model.addAttribute("authors" ,authorService.getAuthors());
        model.addAttribute("categories" , categoryService.getCategories());
        model.addAttribute("users" ,userService.getUsers());
        return "book/edit_book";
    }

    @PostMapping("/put/{id}")
    public String UpdateBook(@PathVariable Long id , @Valid @ModelAttribute BookRequestDTO bookRequestDTO){
        bookService.updateBook(bookRequestDTO,bookRequestDTO.getAuthorIds(),bookRequestDTO.getCategoryIds(),bookRequestDTO.getUserIds(),id);

        return "redirect:/book";
    }

    @GetMapping("/put/{id}/data")
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> showEditForm(@PathVariable Long id) {
        BookDTO savedBook = bookService.getBookById(id);
        if(savedBook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Book with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Book with id " + id + " updated successfully."));
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}/data")
    public ResponseEntity<ApiResponse<String>> deleteBookData(@PathVariable Long id){
        BookDTO savedBook = bookService.getBookById(id);
        if(savedBook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Book with id " + id + " is not found." ));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Book with id " + id + " deleted successfully."));
    }
}