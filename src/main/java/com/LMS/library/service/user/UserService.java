package com.LMS.library.service.user;

import com.LMS.library.dtos.UserDTO;
import com.LMS.library.dtos.UserRequestDTO;
import com.LMS.library.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    UserDTO getUserById(Long id);

    void deleteUser(Long id);

    UserDTO updateUserWithBooks(UserRequestDTO userRequestDTO, List<Long> bookIds, Long id);

    void saveUserWithBooks(UserRequestDTO userRequestDTO, List<Long> bookIds);
}