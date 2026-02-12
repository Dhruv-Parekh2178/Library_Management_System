package com.LMS.library.repository;

import com.LMS.library.model.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterUserRepository extends JpaRepository<MasterUser , Long> {
    Optional<MasterUser> findByName(String userName);
}
