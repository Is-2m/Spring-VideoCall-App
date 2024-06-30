package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Status;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthentificationServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthentificationService authService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setUsername("testuser");
        testUser.setPassword("password");
    }

    @Test
    void registerSuccessTest() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(testUser.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = authService.register(testUser);

        assertNotNull(result);
        assertEquals(testUser.getEmail(), result.getEmail());
        verify(passwordEncoder).encode(testUser.getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void registerFailureTest() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(testUser);

        User result = authService.register(testUser);

        assertNull(result);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void loginSuccessTest() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(testUser);
        when(passwordEncoder.matches(testUser.getPassword(), testUser.getPassword())).thenReturn(true);

        User result = authService.login(testUser.getEmail(), testUser.getPassword());

        assertNotNull(result);
        assertEquals(testUser.getEmail(), result.getEmail());
    }

    @Test
    void loginFailureTest() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(testUser);
        when(passwordEncoder.matches(testUser.getPassword(), testUser.getPassword())).thenReturn(false);

        User result = authService.login(testUser.getEmail(), testUser.getPassword());

        assertNull(result);
    }

}
