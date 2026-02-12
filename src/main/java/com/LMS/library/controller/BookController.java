package com.LMS.library.controller;

import com.LMS.library.dtos.BookDTO;
import com.LMS.library.model.Book;
import com.LMS.library.service.book.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;



    @GetMapping
    public String getAllBooks(Model model){
        List<Book> books = bookService.getbooks();
        model.addAttribute("books" , books);
        return "book/book_list";
    }

    @GetMapping("/get/{id}")
    public String getBookById(@PathVariable Long id, Model model){
        BookDTO book = bookService.getBookById(id);
        if(book==null)
            return "redirect:/book";
        model.addAttribute("book" , book);
        return "book/book_by_id";
    }


    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/book_form";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book){
        List<Long> authorIds = List.of();
        List<Long> categoryIds = List.of();
        List<Long> userIds = List.of();

        try {
            if (book.getAuthorIdsJson() != null &&
                    !book.getAuthorIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                authorIds = mapper.readValue(
                        book.getAuthorIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            if (book.getCategoryIdsJson() != null &&
                    !book.getCategoryIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                categoryIds = mapper.readValue(
                        book.getCategoryIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }
            if (book.getUserIdsJson() != null &&
                    !book.getUserIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                userIds = mapper.readValue(
                        book.getUserIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            bookService.saveBook(book, authorIds , categoryIds , userIds);

        } catch (Exception e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
         return "redirect:/book";
    }

    @GetMapping("/put/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookDTO book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/edit_book";
    }

    @PostMapping("/put/{id}")
    public String UpdateBook(@PathVariable Long id , @Valid @ModelAttribute Book book){
        List<Long> authorIds = List.of();
        List<Long> categoryIds = List.of();
        List<Long> userIds = List.of();

        try {
            if (book.getAuthorIdsJson() != null &&
                    !book.getAuthorIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                authorIds = mapper.readValue(
                        book.getAuthorIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            if (book.getCategoryIdsJson() != null &&
                    !book.getCategoryIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                categoryIds = mapper.readValue(
                        book.getCategoryIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }
            if (book.getUserIdsJson() != null &&
                    !book.getUserIdsJson().isBlank()) {

                ObjectMapper mapper = new ObjectMapper();
                userIds = mapper.readValue(
                        book.getUserIdsJson(),
                        new TypeReference<List<Long>>() {}
                );
            }

            bookService.updateBook(book, authorIds , categoryIds , userIds , id);

        } catch (Exception e) {
            System.out.println( e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }

}