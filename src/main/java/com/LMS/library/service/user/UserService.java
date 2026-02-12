package com.LMS.library.service.user;

import com.LMS.library.dtos.UserDTO;
import com.LMS.library.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    UserDTO getUserById(Long id);

    void deleteUser(Long id);

    void updateUserWithBooks(User user, List<Long> bookIds, Long id);

    void saveUserWithBooks(User user, List<Long> bookIds);
}