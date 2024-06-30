package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Status;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setPhone(user.getPhone());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setBio(user.getBio());
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public User updatePass(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public User updateProfilePicture(Long id, String profilePicture) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setProfilePicture(profilePicture);
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public List<User> findConnectedUsers() {
        return userRepository.findByStatus(Status.ONLINE);
    }

    @Override
    public List<User> findUsersByName(String name) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameIsContainingIgnoreCase(name, name);
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
