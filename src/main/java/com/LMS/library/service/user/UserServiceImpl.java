package com.LMS.library.service.user;

import com.LMS.library.dtos.UserDTO;
import com.LMS.library.dtos.UserRequestDTO;
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

import java.util.ArrayList;
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
    public List<UserDTO> getUsers() {


        List<User> users = userRepository.findAll().stream()
                .filter(user -> !user.isDeleted()).toList();
        return users.stream()
                .map(user -> modelMapper.map(user , UserDTO.class)).toList();
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
    @CachePut(value = "user", key = "#id")
    public UserDTO updateUserWithBooks(UserRequestDTO dto, List<Long> bookIds, Long id) {

        User savedUser = userRepository.findUserById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "UserId", id));

        savedUser.setName(dto.getName());
        savedUser.setAge(dto.getAge());

        for (Book book : savedUser.getBooks()) {
            book.getUsers().remove(savedUser);
        }
        savedUser.getBooks().clear();

        List<Book> newBooks = new ArrayList<>();

        if (bookIds != null && !bookIds.isEmpty()) {
            newBooks = bookRepository.findAllById(bookIds);
            if (newBooks.size() != bookIds.size()) {
                throw new RuntimeException("One or more Book IDs are invalid");
            }
        }

        savedUser.setBooks(newBooks);

        for (Book book : newBooks) {
            book.getUsers().add(savedUser);
        }

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    @Transactional
    public void saveUserWithBooks(UserRequestDTO dto, List<Long> bookIds) {

        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());

        List<Book> books = new ArrayList<>();

        if (bookIds != null && !bookIds.isEmpty()) {
            books = bookRepository.findAllById(bookIds);
            if (books.size() != bookIds.size()) {
                throw new RuntimeException("One or more Book IDs are invalid");
            }
        }

        user.setBooks(books);
        userRepository.save(user);

        for (Book book : books) {
            book.getUsers().add(user);
        }
    }

}