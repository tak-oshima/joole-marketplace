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
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findById(userName);
    }

    @Override
    public List<User> getUsersByUserType(String userType) {
        return userRepository.findAllByUserType(userType);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.findById(user.getUserName()).isPresent()) {
            throw new RuntimeException(
                    String.format("Update User Exception: user with user_name \"%s\" not found", user.getUserName())
            );
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        if (!userRepository.findById(userName).isPresent()) {
            throw new RuntimeException(
                    String.format("Delete User Exception: user with user_name \"%s\" not found", userName)
            );
        }
        userRepository.deleteById(userName);
    }
}
