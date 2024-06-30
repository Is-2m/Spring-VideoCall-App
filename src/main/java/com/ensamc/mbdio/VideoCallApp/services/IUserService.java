package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.User;

import java.util.List;

public interface IUserService {
    User updateUser(Long id, User user);
    User updatePass(Long id, User user);
    User updateProfilePicture(Long id, String profilePicture);
    List<User> findConnectedUsers();
    List<User> findUsersByName(String name);
}
