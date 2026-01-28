package com.LMS.library.controller;

import com.LMS.library.model.Author;
import com.LMS.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/author/getAll")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authors = authorService.getAuthors();
         return new ResponseEntity<>(authors , HttpStatus.OK);
    }

    @GetMapping("/author/get/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        if(author==null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(author , HttpStatus.OK);
    }

    @PostMapping("/author/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>("Author added successfully." , HttpStatus.CREATED);
    }

    @PostMapping("/author/put/{id}")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author , @PathVariable Long id){
        Author savedAuthor = authorService.getAuthorById(id);
        if(savedAuthor == null){
            return new ResponseEntity<>("Author with id " + id + " is not found." , HttpStatus.NOT_FOUND);
        }
        authorService.updateAuthor(author , id);
        return new ResponseEntity<>("Author with id " + id + " updated successfully." , HttpStatus.OK);
    }

    @PostMapping("/author/delete/{id}")
    public ResponseEntity<String> DeleteAuthor(@PathVariable Long id){
        Author savedAuthor = authorService.getAuthorById(id);
        if(savedAuthor == null){
            return new ResponseEntity<>("Author with id " + id + " is not found." , HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author with id " + id + " deleted successfully." , HttpStatus.OK);
    }

}
