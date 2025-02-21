package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.services.ProfilePicService;
import com.ensamc.mbdio.VideoCallApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/profile-pictures")
public class ProfilePictureController {

    private final Path uploadsPath;

    @Autowired
    private ProfilePicService pdpService;

    @Autowired
    public ProfilePictureController(Path uploadsPath) {
        this.uploadsPath = uploadsPath;
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        System.out.println("ProfilePictureController.uploadProfilePicture");

        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            }
            pdpService.uploadProfilePicture(userId, file);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
        }

    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String filename) {
        try {
            Path path = uploadsPath.resolve(filename);
            if (!Files.exists(path)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            byte[] image = Files.readAllBytes(path);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path));
            headers.setCacheControl("max-age=1209600"); //2 weeks
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
