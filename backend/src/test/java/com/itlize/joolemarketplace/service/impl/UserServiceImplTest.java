package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.UserNotFoundException;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.UserRepository;
import com.itlize.joolemarketplace.service.UserService;
import com.itlize.joolemarketplace.util.JwtUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImpl.class})
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void getUserByUserName() {
        User user = new User();
        user.setUsername("jmart0");
        user.setUserType("customer");
        user.setPassword("WrFMKkR2Uh");

        when(userRepository.findById((String) any())).thenReturn(Optional.of(user));
        Optional<User> foundOptionalUser = userService.getUserByUserName("jmart0");

        assertTrue(foundOptionalUser.isPresent());
        assertEquals(user, foundOptionalUser.get());
        verify(userRepository).findById("jmart0");
    }

    @Test
    void getUsersByUserType() {
        User user1 = new User();
        user1.setUsername("jmart0");
        user1.setUserType("customer");
        user1.setPassword("WrFMKkR2Uh");
        User user2 = new User();
        user2.setUsername("dgavan1");
        user2.setUserType("customer");
        user2.setPassword("h4RWbvrBRi7q");
        User user3 = new User();
        user3.setUsername("hlackney2");
        user3.setUserType("seller");
        user3.setPassword("7FKIl2LLN");

        when(userRepository.findAllByUserType("customer")).thenReturn(Arrays.asList(user1, user2));
        List<User> foundUsers = userService.getUsersByUserType("customer");

        assertEquals(Arrays.asList(user1, user2), foundUsers);
        verify(userRepository).findAllByUserType("customer");
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setUsername("jmart0");
        user1.setUserType("customer");
        user1.setPassword("WrFMKkR2Uh");
        User user2 = new User();
        user2.setUsername("dgavan1");
        user2.setUserType("customer");
        user2.setPassword("h4RWbvrBRi7q");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> foundUsers = userService.getAllUsers();

        assertEquals(Arrays.asList(user1, user2), foundUsers);
        verify(userRepository).findAll();
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUsername("jmart0");
        user.setUserType("customer");
        user.setPassword("WrFMKkR2Uh");

        when(userRepository.findById((String) any())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(user);

        assertEquals(user, updatedUser);
        verify(userRepository).findById((String) any());
        verify(userRepository).save(user);
    }

    @Test
    void updateUserThatDoesNotExist() {
        User user = new User();
        user.setUsername("Alice");
        user.setUserType("customer");
        user.setPassword("WrFMKkR2Uh");

        when(userRepository.findById((String) any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user));
        verify(userRepository).findById((String) any());
        verify(userRepository, never()).save(any());
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("jmart0");
        user.setUserType("customer");
        user.setPassword("WrFMKkR2Uh");

        when(userRepository.findById((String) any())).thenReturn(Optional.of(user));
        userService.deleteUser("jmart0");

        verify(userRepository).findById("jmart0");
        verify(userRepository).deleteById("jmart0");
    }

    @Test
    void deleteUserThatDoesNotExist() {
        User user = new User();
        user.setUsername("Alice");
        user.setUserType("customer");
        user.setPassword("WrFMKkR2Uh");

        when(userRepository.findById((String) any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser("Alice"));
        verify(userRepository).findById("Alice");
        verify(userRepository, never()).deleteById(any());
    }
}
