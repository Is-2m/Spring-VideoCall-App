package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.services.AuthentificationService;
import com.ensamc.mbdio.VideoCallApp.utils.JwtUtil;
import com.ensamc.mbdio.VideoCallApp.wrapper.AuthResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    @Autowired
    private AuthentificationService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> input) {
        System.out.println("AuthentificationController.login");

        String email = input.get("email");
        System.out.println("Email: " + email);

        String password = input.get("password");
        System.out.println("Password: " + password);

        User user = authService.login(email, password);
        System.out.println("User: " + user==null?"null":user.toString());

        if (user != null) {
            final String jwt = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponseWrapper(jwt, user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User jsonUser) {
        if (jsonUser != null) {
            User user = authService.register(jsonUser);
            if (user != null) {
                final String jwt = jwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(new AuthResponseWrapper(jwt, user));
            } else {
                return ResponseEntity.status(400).build(); // Bad Request
            }
        } else {
            return ResponseEntity.status(400).build(); // Bad Request
        }

    }

    @PostMapping("/logout")
    public void logout(@RequestBody User jsonUser) {
        if (jsonUser != null) {
            authService.disconnectUser(jsonUser);
        } else {
            System.out.println("jsonUser is null ");
        }

    }

}
