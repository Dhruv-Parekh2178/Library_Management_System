package com.LMS.library.controller;

import com.LMS.library.model.Book;
import com.LMS.library.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/library/book")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private final BookService bookService;



    @GetMapping("/getAll")
    public String getAllBooks(){
        List<Book> books = bookService.getbooks();
        return "";
    }

    @GetMapping("/get/{id}")
    public String getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        if(book==null)
            return "";
        return "";
    }

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
         bookService.saveBook(book);
         return "";
    }

    @PostMapping("/put/{id}")

    public String UpdateBook(@PathVariable Long id , @RequestBody Book book){
        Book savedBook = bookService.getBookById(id);
        if(savedBook == null){
            return "";
        }
        bookService.updateBook(book , id);
        return "";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        Book savedBook = bookService.getBookById(id);
        if(savedBook == null){
            return "";
        }
        bookService.deleteBook(id);
        return "";
    }

}