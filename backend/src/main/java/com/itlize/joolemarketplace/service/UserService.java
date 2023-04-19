package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.dto.AuthRequest;
import com.itlize.joolemarketplace.dto.AuthResponse;
import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AuthResponse registerUser(AuthRequest request);
    AuthResponse authenticateUser(AuthRequest request);
    Optional<User> getUserByUserName(String userName);
    List<User> getUsersByUserType(String userType);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(String userName);
}
