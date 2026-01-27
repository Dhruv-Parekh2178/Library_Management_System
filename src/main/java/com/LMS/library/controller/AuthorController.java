package com.LMS.library.controller;

import com.LMS.library.model.Author;
import com.LMS.library.service.AuthorService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
public class AuthorController {
    @Autowired
    private AuthorService authorService;

}
