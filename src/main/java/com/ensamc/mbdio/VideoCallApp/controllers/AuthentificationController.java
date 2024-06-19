package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.services.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    @Autowired
    private AuthentificationService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody Map<String, String> input) {
        String email = input.get("email");
        String password = input.get("password");
        User user = authService.login(email,password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User jsonUser) {
        if(jsonUser != null){
            User user = authService.register(jsonUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(400).build(); // Bad Request
            }

        }else {
            return ResponseEntity.status(400).build(); // Bad Request
        }

    }

}
