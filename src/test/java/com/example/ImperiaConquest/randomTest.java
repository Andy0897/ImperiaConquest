package com.example.ImperiaConquest;

import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import com.example.ImperiaConquest.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class randomTest {
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void checkIfExistsUserByUsername() {
        // Given
        User user = new User();
        user.setUsername("test");
        when(userRepository.getUserByUsername("test")).thenReturn(user);
        // When
        userService.checkIfExistsUserByUsername("test");

        // Then
        assertEquals(true, userService.checkIfExistsUserByUsername("test"));
    }

}

/*
public boolean checkIfExistsUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return true;
    }*/
