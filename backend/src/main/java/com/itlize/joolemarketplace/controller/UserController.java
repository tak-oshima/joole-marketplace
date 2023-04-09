package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Username already exists");
            responseBody.put("details", e.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }
    }

    //login
    

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
        Optional<User> foundUser = userService.getUserByUserName(userName);
        if (foundUser.isPresent()) {
            return new ResponseEntity<>(foundUser.get() , HttpStatus.OK);
        }
        else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "User not found");
            responseBody.put("details", String.format("User with user_name \"%s\" could not be found", userName));
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

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUserBy(@PathVariable String userName) {
        try {
            userService.deleteUser(userName);
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
