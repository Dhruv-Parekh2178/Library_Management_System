package com.LMS.library.service.impl;

import com.LMS.library.model.Author;
import com.LMS.library.repository.AuthorRepository;
import com.LMS.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
