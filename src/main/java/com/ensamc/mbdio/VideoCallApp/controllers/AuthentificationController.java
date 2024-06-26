package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.services.AuthentificationService;
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
    private static final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody Map<String, String> input) {
        String email = input.get("email");
        String password = input.get("password");


        User user = authService.login(email,password);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User jsonUser) {
//        System.out.println("AuthentificationController.register");
        logger.info("AuthentificationController.register");
        if(jsonUser != null){
//            System.out.println("jsonUser isn't null " + jsonUser);
            logger.info("jsonUser isn't null " + jsonUser);
            User user = authService.register(jsonUser);
            if (user != null) {
//                System.out.println("Done Register" + jsonUser);
                logger.info("Done Register" + jsonUser);
                return ResponseEntity.ok(user);
            } else {
//                System.out.println("Done't Register");
                logger.info("Done't Register");
                return ResponseEntity.status(400).build(); // Bad Request
            }

        }else {
//            System.out.println("jsonUser is null ");
            logger.info("jsonUser is null ");

            return ResponseEntity.status(400).build(); // Bad Request
        }

    }

    @PostMapping("/logout")
    public void logout(@RequestBody User jsonUser) {
        System.out.println("AuthentificationController.logout");
//        System.out.println(jsonUser.toString());
        if(jsonUser != null){
            authService.disconnectUser(jsonUser);
        }else {
            System.out.println("jsonUser is null ");
        }

    }

}
