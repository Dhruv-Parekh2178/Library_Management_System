package com.LMS.library.repository;

import com.LMS.library.model.Category;
import com.LMS.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserById(Long id);
}
