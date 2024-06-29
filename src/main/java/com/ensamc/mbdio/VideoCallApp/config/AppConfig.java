package com.ensamc.mbdio.VideoCallApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AppConfig {

    @Value("${uploads.directory:uploads/images}")
    private String uploadsDirectory;

    @Bean
    public Path uploadsPath() {
        Path jarPath = Paths.get(System.getProperty("user.dir")); // Current working directory
        Path uploadsPath = jarPath.resolve(uploadsDirectory);

        // Create the directory if it doesn't exist
        try {
            Files.createDirectories(uploadsPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create uploads directory", e);
        }

        return uploadsPath;
    }
}
