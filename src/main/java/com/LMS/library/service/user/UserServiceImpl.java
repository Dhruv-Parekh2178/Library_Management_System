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
        List<User> users = userRepository.findAll().stream()
                .filter(user -> !user.isDeleted()).toList();
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"UserId" , id));
    }


    @Override
    public void deleteUser(Long id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedUser.setDeleted(true);
        userRepository.save(savedUser);
    }

    @Override
    public void updateUserWithBooks(User user, List<Long> bookIds, Long id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));


        savedUser.setName(user.getName());
        savedUser.setAge(user.getAge());
        savedUser.setDeleted(user.isDeleted());
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        savedUser.setBooks(books);

        userRepository.save(savedUser);
    }

    @Override
    public void saveUserWithBooks(User user, List<Long> bookIds) {
        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        user.setBooks(books);

        userRepository.save(user);
    }
}