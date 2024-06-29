package com.ensamc.mbdio.VideoCallApp.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ProfilePicService {

    @Autowired
    private UserService userService;

    public void uploadProfilePicture(Long userId, MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            String extension = getExtension(originalFileName);
            String fileName = UUID.randomUUID().toString() + extension;

            File tempFile = convertToFile(file, fileName);
            System.out.println("ProfilePicService.uploadProfilePicture");
            System.out.println(file.getContentType());


            String fileUrl = uploadFile(tempFile, fileName, file.getContentType());

            userService.updateProfilePicture(userId, fileUrl);

            tempFile.delete();
        } catch (IOException e) {
            throw new RuntimeException("Could not upload the file: " + e.getMessage());
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String uploadFile(File file, String fileName, String contentType) throws IOException {
        BlobId blobId = BlobId.of("mimichat-fb1f8.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(contentType)
                .build();

        InputStream inputStream = ProfilePicService.class.getClassLoader()
                .getResourceAsStream("mimichat-fb1f8-firebase-adminsdk-yrdi4-36aee5f8e0.json");
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Upload the file
        Blob blob = storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        // Generate a signed URL that's valid for a long time (e.g., 10 years)
        System.out.println("ProfilePicService.uploadFile");
        URL signedUrl = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS);

        System.out.println(signedUrl);

        return signedUrl.toString();
    }

}

