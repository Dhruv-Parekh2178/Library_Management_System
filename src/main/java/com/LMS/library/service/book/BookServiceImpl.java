package com.LMS.library.service.book;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.*;
import com.LMS.library.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final PublisherRepository publisherRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<Book> getbooks() {
        List<Book> books = bookRepository.findAll().stream()
                .filter(book -> !book.isDeleted()).toList();
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findBooksById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book" , "BookId" ,id));
    }

    @Override

    public void deleteBook(Long id) {
        Book savedBook = bookRepository.findBooksById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book" , "BookId" , id));

        savedBook.setDeleted(true);
        bookRepository.save(savedBook);
    }

    @Override
    @Transactional
    public void saveBook(Book book, List<Long> authorIds, List<Long> categoryIds, List<Long> userIds) {
        List<Author> authors = authorRepository.findAllById(authorIds);
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        List<User> users = userRepository.findAllById(userIds);

        book.setAuthors(authors);
        book.setCategories(categories);

        if (book.getPublisher() != null) {
            Publisher publisher = publisherRepository
                    .findPublisherById(book.getPublisher().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Publisher", "id", book.getPublisher().getId()));
            book.setPublisher(publisher);
        }

        bookRepository.save(book);

        for (User user : users) {
            user.getBooks().add(book);
        }
    }

    @Override
    @Transactional
    public void updateBook(Book book, List<Long> authorIds, List<Long> categoryIds, List<Long> userIds, Long id) {
        Book savedBook = getBookById(id);
        savedBook.setName(book.getName());

        if (savedBook.getAuthors() != null) {
            savedBook.getAuthors().clear();
        }
        if (savedBook.getCategories() != null) {
            savedBook.getCategories().clear();
        }
        if (savedBook.getUsers() != null) {
            for (User user : savedBook.getUsers()) {
                user.getBooks().remove(savedBook);
            }
        }

        List<Author> authors = authorRepository.findAllById(authorIds);
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        List<User> users = userRepository.findAllById(userIds);

        savedBook.setAuthors(authors);
        savedBook.setCategories(categories);

        if (book.getPublisher() != null) {
            Publisher publisher = publisherRepository
                    .findPublisherById(book.getPublisher().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Publisher", "id", book.getPublisher().getId()));
            savedBook.setPublisher(publisher);
        }

        for (User user : users) {
            user.getBooks().add(savedBook);
        }

    }


}