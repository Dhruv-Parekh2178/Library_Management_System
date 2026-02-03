package com.LMS.library.service.book;

import com.LMS.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getbooks();

    Book getBookById(Long id);

    void saveBook(Book book);

    void updateBook(Book book, Long id);

    void deleteBook(Long id);
}