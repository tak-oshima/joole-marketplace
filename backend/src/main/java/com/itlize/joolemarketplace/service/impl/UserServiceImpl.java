package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.dto.AuthRequest;
import com.itlize.joolemarketplace.dto.AuthResponse;
import com.itlize.joolemarketplace.exception.UserAlreadyExistsException;
import com.itlize.joolemarketplace.exception.UserNotFoundException;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.model.enums.Role;
import com.itlize.joolemarketplace.repository.UserRepository;
import com.itlize.joolemarketplace.service.UserService;

import com.itlize.joolemarketplace.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse registerUser(AuthRequest request) {
        if (userRepository.findById(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(request.getUsername());
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(request.getUserType())
                .role(Role.USER)
                .build();
        UserDetails userDetails = userRepository.save(user);

        return AuthResponse.builder()
                .username(userDetails.getUsername())
                .token(jwtUtil.generateToken(userDetails))
                .build();
    }

    @Override
    public AuthResponse authenticateUser(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = userRepository.findById(request.getUsername()).get();

        return AuthResponse.builder()
                .username(userDetails.getUsername())
                .token(jwtUtil.generateToken(userDetails))
                .build();
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
        if (!userRepository.findById(user.getUsername()).isPresent()) {
            throw new UserNotFoundException(user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        if (!userRepository.findById(userName).isPresent()) {
            throw new UserNotFoundException(userName);
        }
        userRepository.deleteById(userName);
    }
}
