package com.LMS.library.service.user;

import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Book;
import com.LMS.library.model.Category;
import com.LMS.library.model.User;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"UserId" , id));
    }

    @Override
    public void saveUser(User user) {
        if (user.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : user.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(()-> new ResourceNotFoundException("Book"  , "bookId" , book.getId()));
                managedBooks.add(managedBook);
            }
            user.setBooks(managedBooks);
        }
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void updateUser(User user, Long id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));


        savedUser.setName(user.getName());

        savedUser.setDeleted(user.isDeleted());

        if (user.getBooks() != null) {
            List<Book> managedBooks = new ArrayList<>();
            for (Book book : user.getBooks()) {
                Book managedBook = bookRepository.findBooksById(book.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Book", "bookId", book.getId()));
                managedBooks.add(managedBook);
            }
            savedUser.setBooks(managedBooks);
        }

        userRepository.save(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedUser.setDeleted(true);
        userRepository.save(savedUser);
    }
}
