package com.LMS.library.controller;

import com.LMS.library.model.ApiResponse;

import com.LMS.library.model.Author;
import com.LMS.library.model.Book;
import com.LMS.library.service.author.AuthorService;
import com.LMS.library.service.book.BookService;
import com.LMS.library.service.category.CategoryService;
import com.LMS.library.service.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;



    @GetMapping("/book/getAll")
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks(){
        List<Book> books = bookService.getbooks();
        return ResponseEntity.ok(new ApiResponse<>(true,"success" , books));
    }

    @GetMapping("/book/get/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        if(book==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , book));
    }

    @PostMapping("/book/add")
    public ResponseEntity<ApiResponse<String>> addBook(@RequestBody Book book){
         bookService.saveBook(book);
         return ResponseEntity.ok().body(new ApiResponse<>(true , "success" , "Book added Successfully"));
    }

    @PostMapping("/book/put/{id}")

    public  ResponseEntity<ApiResponse<String>> UpdateBook(@PathVariable Long id , @RequestBody Book book){
        Book savedBook = bookService.getBookById(id);
        if(savedBook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Book with id " + id + " is not found." ));
        }
        bookService.updateBook(book , id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Book with id " + id + " updated successfully."));
    }

    @PostMapping("/book/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Long id){
        Book savedBook = bookService.getBookById(id);
        if(savedBook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Book with id " + id + " is not found." ));
        }
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Book with id " + id + " deleted successfully."));
    }

}
