package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
//    userRepository.
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
    public User register(User jsonUser) {
        User user = userRepository.findByEmail(jsonUser.getEmail());
        if (user == null) {
            user = new User();

            user.setEmail(jsonUser.getEmail());
            user.setPassword(passwordEncoder.encode(jsonUser.getPassword()));
            user.setBirthDate(jsonUser.getBirthDate());
            user.setPhone(jsonUser.getPhone());
            user.setUsername(jsonUser.getUsername());

            userRepository.save(user);

            return user;
        }
        return null;
    }
}
