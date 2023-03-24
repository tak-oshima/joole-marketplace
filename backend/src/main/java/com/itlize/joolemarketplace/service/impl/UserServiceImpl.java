package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.UserRepository;
import com.itlize.joolemarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException(
                    String.format("Create User Exception: user with user_name \"%s\" already exists", user.getUserName())
            );
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getAllUserByUserType(String userType) {
        return userRepository.findAllByUserType(userType);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException(
                    String.format("Update User Exception: user with user_name \"%s\" not found", user.getUserName())
            );
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        if (!userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException(
                    String.format("Delete User Exception: user with user_name \"%s\" not found", user.getUserName())
            );
        }
        userRepository.delete(user);
    }
}



