package com.LMS.library.service.author;

import com.LMS.library.dtos.AuthorDTO;
import com.LMS.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    AuthorDTO getAuthorById(Long id);

    Author deleteAuthor(Long id);

    void saveAuthorWithBooks(Author author, List<Long> bookIds);

    void updateAuthorWithBooks(Author author, List<Long> bookIds , Long id);
}