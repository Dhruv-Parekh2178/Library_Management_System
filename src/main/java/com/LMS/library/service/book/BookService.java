package com.LMS.library.service.book;

import com.LMS.library.dtos.BookDTO;
import com.LMS.library.dtos.BookRequestDTO;
import com.LMS.library.model.Book;

import java.util.List;

public interface BookService {
    List<BookDTO> getbooks();

    BookDTO getBookById(Long id);

    void deleteBook(Long id);

    void saveBook(BookRequestDTO bookRequestDTO, List<Long> authorIds , List<Long> categoryIds , List<Long> userIds);

    BookDTO updateBook(BookRequestDTO bookRequestDTO, List<Long> authorIds, List<Long> categoryIds, List<Long> userIds, Long id);
}