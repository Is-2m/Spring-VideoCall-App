package com.ensamc.mbdio.VideoCallApp.entities;

//import jakarta.persistence.*;

import  jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String username;

    private String phone;

    private String firstName;

    private String lastName;

    private String bio;

    private Long birthDate;

    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return "User{\n" +
                "id=" + id +
                ",\n email='" + email + '\'' +
                ",\n password='" + password + '\'' +
                ",\n username='" + username + '\'' +
                ",\n phone='" + phone + '\'' +
                ",\n firstName='" + firstName + '\'' +
                ",\n lastName='" + lastName + '\'' +
                ",\n bio='" + bio + '\'' +
                ",\n birthDate=" + birthDate +
                ",\n profilePicture='" + profilePicture + '\'' +
                ",\n status=" + status +
                "\n}";
    }
}