package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.dto.AuthRequest;
import com.itlize.joolemarketplace.dto.AuthResponse;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = userService.registerUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Username already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = userService.authenticateUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Authentication failed");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        Optional<User> foundUser = userService.getUserByUserName(username);
        if (foundUser.isPresent()) {
            return new ResponseEntity<>(foundUser.get() , HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User not found");
            responseBody.put("details", String.format("User with user_name \"%s\" could not be found", username));
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsersByTypeOrAll(@RequestParam(value = "userType", required = false) String userType) {
        List<User> users;
        if (userType != null) {
            users = userService.getUsersByUserType(userType);
        }
        else {
            users = userService.getAllUsers();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserBy(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User not found");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
    }
}
