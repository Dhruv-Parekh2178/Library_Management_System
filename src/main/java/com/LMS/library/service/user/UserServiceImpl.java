package com.LMS.library.service.user;

import com.LMS.library.dtos.UserDTO;
import com.LMS.library.exception.ResourceNotFoundException;
import com.LMS.library.model.Book;
import com.LMS.library.model.User;
import com.LMS.library.repository.BookRepository;
import com.LMS.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<User> getUsers() {


        List<User> users = userRepository.findAll().stream()
                .filter(user -> !user.isDeleted()).toList();
        return users;
    }

    @Override
    @Cacheable(value = "user" , key ="#id")
    public UserDTO getUserById(Long id) {
        System.out.println("Fetching user with "+id+" from DB...");
        User user = userRepository.findUserById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"UserId" , id));
        return modelMapper.map(user , UserDTO.class);
    }


    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(Long id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author" , "AuthorId" , id));

        savedUser.setDeleted(true);
        userRepository.save(savedUser);
    }

    @Override
    @Transactional
    @CachePut(value = "user" , key="#id")
    public void updateUserWithBooks(User user, List<Long> bookIds, Long id) {
        User savedUser = userRepository.findUserById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User" ,"UserId" , id));

        savedUser.setName(user.getName());
        savedUser.setAge(user.getAge());
        if (savedUser.getBooks() != null) {
            for (Book book : savedUser.getBooks()) {
                book.getUsers().remove(savedUser);
            }

            savedUser.getBooks().clear();
        }

        List<Book> newBooks = bookRepository.findAllById(bookIds);
        if (newBooks.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        for (Book book : newBooks) {
            savedUser.getBooks().add(book);
            book.getUsers().add(savedUser);
        }
    }

    @Override
    @Transactional
    public void saveUserWithBooks(User user, List<Long> bookIds) {

        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new RuntimeException("One or more Book IDs are invalid");
        }

        userRepository.save(user);
        for (Book book : books) {
            user.getBooks().add(book);
            book.getUsers().add(user);
        }
    }
}