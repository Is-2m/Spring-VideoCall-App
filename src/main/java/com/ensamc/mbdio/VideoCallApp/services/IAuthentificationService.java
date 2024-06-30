package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.User;

public interface IAuthentificationService {
    User register(User user);
    User login(String email, String password);
    void disconnectUser(User user);
}
