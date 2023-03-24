package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String username);
    List<User> findAllByUserType(String userType);
}

