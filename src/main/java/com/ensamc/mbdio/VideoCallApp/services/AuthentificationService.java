package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Status;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthentificationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthentificationService.class);

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email.toLowerCase());
//    userRepository.
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setStatus(Status.ONLINE);
            userRepository.save(user);
            return user;
        }
        return null;
    }
    public User register(User jsonUser) {
        User user = userRepository.findByEmail(jsonUser.getEmail().toLowerCase());
        if (user == null) {
            user = new User();

            user.setEmail(jsonUser.getEmail().toLowerCase());
            user.setPassword(passwordEncoder.encode(jsonUser.getPassword()));
            user.setBirthDate(jsonUser.getBirthDate());
            user.setPhone(jsonUser.getPhone());
            user.setUsername(jsonUser.getUsername());

            user.setStatus(Status.ONLINE);

            userRepository.save(user);

            return user;
        }
        return null;
    }


    public void disconnectUser(User user){
        System.out.println("AuthentificationService.disconnectUser");
        User userr = userRepository.findByEmail(user.getEmail());
        if (userr != null) {
            userr.setStatus(Status.OFFLINE);
            System.out.println("User disconnected");
            System.out.println(userr.toString());
            userRepository.save(userr);
        }else {
            System.out.println("User not found");
        }
    }
}
