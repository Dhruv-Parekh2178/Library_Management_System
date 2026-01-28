package com.LMS.library.service;

import com.LMS.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author getAuthorById(Long id);

    Author saveAuthor(Author author);

    void updateAuthor(Author author, Long id);

    void deleteAuthor(Long id);
}
