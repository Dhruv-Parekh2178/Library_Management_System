package com.LMS.library.service.user;

import com.LMS.library.model.Category;
import com.LMS.library.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void updateUser(User user, Long id);

    void deleteUser(Long id);
}