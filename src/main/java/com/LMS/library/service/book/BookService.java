package com.LMS.library.service.book;

import com.LMS.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getbooks();

    Book getBookById(Long id);

    void deleteBook(Long id);

    void saveBook(Book book, List<Long> authorIds , List<Long> categoryIds , List<Long> userIds);

    void updateBook(Book book, List<Long> authorIds, List<Long> categoryIds, List<Long> userIds, Long id);
}