package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPhone(user.getPhone());
//            userToUpdate.setProfilePicture(user.getProfilePicture());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setBio(user.getBio());
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    public User updatePass(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(userToUpdate);
        }
        return null;
    }
    public User updateProfilePicture(Long id, String profilePicture) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setProfilePicture(profilePicture);
            return userRepository.save(userToUpdate);
        }
        return null;
    }
//    public User updatePass(Long id, User user) {
//        User userToUpdate = userRepository.findById(id).orElse(null);
//        if (userToUpdate != null) {
//            userToUpdate.setEmail(user.getEmail());
//            userToUpdate.setFirstName(user.getFirstName());
//            userToUpdate.setLastName(user.getLastName());
//            userToUpdate.setPhone(user.getPhone());
//            userToUpdate.setProfilePicture(user.getProfilePicture());
//            userToUpdate.setUsername(user.getUsername());
//            return userRepository.save(userToUpdate);
//        }
//        return null;
//    }
}
