package com.ensamc.mbdio.VideoCallApp;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoCallAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoCallAppApplication.class, args);

	}

}
