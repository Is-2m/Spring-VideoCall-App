package com.ensamc.mbdio.VideoCallApp.wrapper;

import com.ensamc.mbdio.VideoCallApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseWrapper {
    private String jwt;
    private User user;
}


