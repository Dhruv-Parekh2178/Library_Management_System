package com.LMS.library.service.author;

import com.LMS.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author getAuthorById(Long id);

    Author saveAuthor(Author author);

    void updateAuthor(Author author, Long id);

    void deleteAuthor(Long id);

    void saveAuthorWithBooks(Author author, List<Long> bookIds);

    void updateAuthorWithBooks(Author author, List<Long> bookIds , Long id);
}