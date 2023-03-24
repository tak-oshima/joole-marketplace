package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserByUserName(String userName);
    List<User> getUsersByUserType(String userType);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(String userName);
}
