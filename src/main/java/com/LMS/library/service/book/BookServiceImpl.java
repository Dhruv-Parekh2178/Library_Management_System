package com.LMS.library.service.book;

import com.LMS.library.dtos.*;
import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.*;
import com.LMS.library.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    private ModelMapper modelMapper;

    @Override

    public List<BookDTO> getbooks() {

      List<Book> books = bookRepository.findAll().stream()
                .filter(book -> !book.isDeleted()).toList();
        return books.stream()
        .map(book -> modelMapper.map(book , BookDTO.class)).toList();
    }

    @Override
    @Cacheable(value = "book" , key = "#id")
    public BookDTO getBookById(Long id) {
        System.out.println("Fetching book with "+id+" from DB...");
        Book book = bookRepository.findBooksById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book" , "BookId" ,id));
        return modelMapper.map(book,BookDTO.class);
    }

    @Override
    @CacheEvict(value = "book" , key = "#id")
    public void deleteBook(Long id) {
        Book savedBook = bookRepository.findBooksById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book" , "BookId" , id));

        savedBook.setDeleted(true);
        bookRepository.save(savedBook);
    }

    @Override
    @Transactional
    public void saveBook(BookRequestDTO dto,
                         List<Long> authorIds,
                         List<Long> categoryIds,
                         List<Long> userIds) {

        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<User> users = new ArrayList<>();

        if (authorIds != null && !authorIds.isEmpty()) {
            authors = authorRepository.findAllById(authorIds);
            if (authors.size() != authorIds.size()) {
                throw new RuntimeException("Invalid Author IDs");
            }
        }

        if (categoryIds != null && !categoryIds.isEmpty()) {
            categories = categoryRepository.findAllById(categoryIds);
            if (categories.size() != categoryIds.size()) {
                throw new RuntimeException("Invalid Category IDs");
            }
        }

        if (userIds != null && !userIds.isEmpty()) {
            users = userRepository.findAllById(userIds);
            if (users.size() != userIds.size()) {
                throw new RuntimeException("Invalid User IDs");
            }
        }

        Book book = new Book();
        book.setName(dto.getName());

        if (dto.getPublisherId() != null) {
            Publisher publisher = publisherRepository
                    .findPublisherById(dto.getPublisherId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Publisher", "id", dto.getPublisherId()));
            book.setPublisher(publisher);
        }

        book.setAuthors(authors);
        book.setCategories(categories);
        book.setUsers(users);

        bookRepository.save(book);

        for (Author author : authors) {
            author.getBooks().add(book);
        }

        for (Category category : categories) {
            category.getBooks().add(book);
        }

        for (User user : users) {
            user.getBooks().add(book);
        }
    }

    @Override
    @Transactional
    @CachePut(value = "book", key = "#id")
    public BookDTO updateBook(BookRequestDTO dto,
                              List<Long> authorIds,
                              List<Long> categoryIds,
                              List<Long> userIds,
                              Long id) {

        Book savedBook = bookRepository.findBooksById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book", "BookId", id));

        savedBook.setName(dto.getName());

        for (Author author : savedBook.getAuthors()) {
            author.getBooks().remove(savedBook);
        }
        savedBook.getAuthors().clear();

        for (Category category : savedBook.getCategories()) {
            category.getBooks().remove(savedBook);
        }
        savedBook.getCategories().clear();

        for (User user : savedBook.getUsers()) {
            user.getBooks().remove(savedBook);
        }
        savedBook.getUsers().clear();
        List<Author> authors = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<User> users = new ArrayList<>();
        if (authorIds != null && !authorIds.isEmpty()) {
            authors = authorRepository.findAllById(authorIds);
            if (authors.size() != authorIds.size()) {
                throw new RuntimeException("Invalid Author IDs");
            }
        }
        if (categoryIds != null && !categoryIds.isEmpty()) {
            categories = categoryRepository.findAllById(categoryIds);
            if (categories.size() != categoryIds.size()) {
                throw new RuntimeException("Invalid Category IDs");
            }
        }
        if (userIds != null && !userIds.isEmpty()) {
            users = userRepository.findAllById(userIds);
            if (users.size() != userIds.size()) {
                throw new RuntimeException("Invalid User IDs");
            }
        }
        if (dto.getPublisherId() != null) {
            Publisher publisher = publisherRepository
                    .findPublisherById(dto.getPublisherId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Publisher", "id", dto.getPublisherId()));
            savedBook.setPublisher(publisher);
        } else {
            savedBook.setPublisher(null);
        }
        savedBook.setAuthors(authors);
        savedBook.setCategories(categories);
        savedBook.setUsers(users);
        for (Author author : authors) {
            author.getBooks().add(savedBook);
        }
        for (Category category : categories) {
            category.getBooks().add(savedBook);
        }
        for (User user : users) {
            user.getBooks().add(savedBook);
        }
        return modelMapper.map(savedBook, BookDTO.class);
    }


}