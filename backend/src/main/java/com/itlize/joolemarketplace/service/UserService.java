package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.dto.AuthenticationRequest;
import com.itlize.joolemarketplace.dto.AuthenticationResponse;
import com.itlize.joolemarketplace.dto.RegisterRequest;
import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AuthenticationResponse registerUser(RegisterRequest request);
    AuthenticationResponse authenticateUser(AuthenticationRequest request);
    Optional<User> getUserByUserName(String userName);
    List<User> getUsersByUserType(String userType);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(String userName);
}
