package com.LMS.library.controller;

import com.LMS.library.model.ApiResponse;
import com.LMS.library.model.Author;
import com.LMS.library.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
public class AuthorController {


    @Autowired
    private final AuthorService authorService;

    @GetMapping("/author/getAll")
    public ResponseEntity<ApiResponse<List<Author>>> getAllAuthors(){
        List<Author> authors = authorService.getAuthors();
        return ResponseEntity.ok(new ApiResponse<>(true , "success" , authors));
    }

    @GetMapping("/author/get/{id}")
    public ResponseEntity<ApiResponse<Author>> getAuthorById(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        if(author==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false ,"fail" , null));
        return ResponseEntity.ok(new ApiResponse<>(true, "success" , author));
    }

    @PostMapping("/author/add")
    public ResponseEntity<ApiResponse<String>> addAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
        return ResponseEntity.ok(new ApiResponse<>(true,"success","Author added successfully."));
    }

    @PostMapping("/author/put/{id}")
    public ResponseEntity<ApiResponse<String>> updateAuthor(@RequestBody Author author , @PathVariable Long id){
        Author savedAuthor = authorService.getAuthorById(id);
        if(savedAuthor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Author with id " + id + " is not found." ));
        }
        authorService.updateAuthor(author , id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Author with id " + id + " updated successfully."));
    }

    @PostMapping("/author/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAuthor(@PathVariable Long id){
            Author savedAuthor = authorService.getAuthorById(id);
            if(savedAuthor == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, "fail","Author with id " + id + " is not found." ));
            }
            authorService.deleteAuthor(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "success" ,"Author with id " + id + " deleted successfully."));
    }

}