package com.LMS.library.repository;

import com.LMS.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author , Integer> {
    Optional<Author> findAuthorById(Long id);
}
