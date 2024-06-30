package com.ensamc.mbdio.VideoCallApp.repositories;

import com.ensamc.mbdio.VideoCallApp.entities.Status;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    List<User> findByStatus(Status status);

    List<User> findByFirstNameContainingIgnoreCaseOrLastNameIsContainingIgnoreCaseOrUsernameContainingIgnoreCase(String firstName, String lastName, String username);
}

