package com.ensamc.mbdio.VideoCallApp.services;

import org.springframework.web.multipart.MultipartFile;

public interface IProfilePicService {
    void uploadProfilePicture(Long userId, MultipartFile file);
}
