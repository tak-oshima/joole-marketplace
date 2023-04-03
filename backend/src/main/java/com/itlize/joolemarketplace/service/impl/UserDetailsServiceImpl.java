package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.UserNotFoundException;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findById(username);
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        return foundUser.get();
    }
}
