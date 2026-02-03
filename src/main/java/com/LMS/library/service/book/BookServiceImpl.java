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
//    @Transactional
//    @Override
//    public void saveBook(Book book) {
//
//
//        if (book.getAuthors() != null) {
//            List<Author> managedAuthors = new ArrayList<>();
//            for (Author author : book.getAuthors()) {
//                Author managedAuthor = authorRepository.findAuthorById(author.getId())
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("Author", "id", author.getId()));
//                managedAuthors.add(managedAuthor);
//            }
//            book.setAuthors(managedAuthors);
//        }
//
//
//        if (book.getCategories() != null) {
//            List<Category> managedCategories = new ArrayList<>();
//            for (Category category : book.getCategories()) {
//                Category managedCategory = categoryRepository.findCategoryById(category.getId())
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("Category", "id", category.getId()));
//                managedCategories.add(managedCategory);
//            }
//            book.setCategories(managedCategories);
//        }
//
//
//        if (book.getPublisher() != null) {
//            Publisher publisher = publisherRepository.findPublisherById(book.getPublisher().getId())
//                    .orElseThrow(() ->
//                            new ResourceNotFoundException("Publisher", "id", book.getPublisher().getId()));
//            book.setPublisher(publisher);
//        }
//
//
//        if (book.getUsers() != null) {
//            List<User> managedUsers = new ArrayList<>();
//            for (User user : book.getUsers()) {
//                User managedUser = userRepository.findUserById(user.getId())
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("User", "id", user.getId()));
//                managedUsers.add(managedUser);
//            }
//            book.setUsers(managedUsers);
//        }
//
//        bookRepository.save(book);
//    }

//    @Override
//    public void updateBook(Book book, Long id) {
//        Book savedBook = bookRepository.findBooksById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Book" , "BookId" , id));
//
//        savedBook.setName(book.getName());
//        savedBook.setDeleted(book.isDeleted());
//
//        if (book.getAuthors() != null) {
//            List<Author> managedAuthors = new ArrayList<>();
//            for (Author author : book.getAuthors()) {
//                Author managedAuthor = authorRepository.findAuthorById(author.getId())
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("Author", "id", author.getId()));
//                managedAuthors.add(managedAuthor);
//            }
//            savedBook.setAuthors(managedAuthors);
//        }
//
//
//        if (book.getCategories() != null) {
//            List<Category> managedCategories = new ArrayList<>();
//            for (Category category : book.getCategories()) {
//                Category managedCategory = categoryRepository.findCategoryById(category.getId())
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("Category", "id", category.getId()));
//                managedCategories.add(managedCategory);
//            }
//            savedBook.setCategories(managedCategories);
//        }
//
//
//        if (book.getPublisher() != null) {
//            Publisher publisher = publisherRepository.findPublisherById(book.getPublisher().getId())
//                    .orElseThrow(() ->
//                            new ResourceNotFoundException("Publisher", "id", book.getPublisher().getId()));
//            savedBook.setPublisher(publisher);
//        }
//
//
//        if (book.getUsers() != null) {
//            List<User> managedUsers = new ArrayList<>();
//            for (User user : book.getUsers()) {
//                User managedUser = userRepository.findUserById(user.getId())
//                        .orElseThrow(() ->
//                                new ResourceNotFoundException("User", "id", user.getId()));
//                managedUsers.add(managedUser);
//            }
//            savedBook.setUsers(managedUsers);
//        }
//
//        bookRepository.save(savedBook);
//
//    }

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
        List<Author> authors =authorRepository.findAllById(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new RuntimeException("One or more Author IDs are invalid");
        }




        List<Category> categories =categoryRepository.findAllById(categoryIds);
        if (categories.size() != categoryIds.size()) {
            throw new RuntimeException("One or more Category IDs are invalid");
        }


        if (book.getPublisher() != null) {
            Publisher publisher = publisherRepository.findPublisherById(book.getPublisher().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Publisher", "id", book.getPublisher().getId()));
            book.setPublisher(publisher);
        }

        List<User> users =userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            throw new RuntimeException("One or more User IDs are invalid");
        }
        book.setAuthors(authors);
        book.setCategories(categories);
        book.setUsers(users);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book, List<Long> authorIds, List<Long> categoryIds, List<Long> userIds, Long id) {
        Book savedBook = bookRepository.findBooksById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", id));
       savedBook.setName(book.getName());


        if (book.getPublisher() != null) {
            Publisher publisher = publisherRepository.findPublisherById(book.getPublisher().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Publisher", "id", book.getPublisher().getId()));
            savedBook.setPublisher(publisher);
        }

        List<Author> authors =authorRepository.findAllById(authorIds);
        if (authors.size() != authorIds.size()) {
            throw new RuntimeException("One or more Author IDs are invalid");
        }




        List<Category> categories =categoryRepository.findAllById(categoryIds);
        if (categories.size() != categoryIds.size()) {
            throw new RuntimeException("One or more Category IDs are invalid");
        }

        List<User> users =userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            throw new RuntimeException("One or more User IDs are invalid");
        }

        savedBook.setAuthors(authors);
        savedBook.setCategories(categories);
        savedBook.setUsers(users);
        bookRepository.save(savedBook);
    }


}