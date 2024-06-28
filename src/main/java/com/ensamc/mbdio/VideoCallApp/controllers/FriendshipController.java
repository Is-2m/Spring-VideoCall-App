package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.Friendship;
import com.ensamc.mbdio.VideoCallApp.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping
    public List<Friendship> getAllFriendships() {
        return friendshipService.getAllFriendships();
    }

    @GetMapping("/{id}")
    public List<Friendship> getFriendshipsById(@PathVariable Long id) {
        return friendshipService.getFriendshipsByUserID(id);
    }

    @PostMapping
    public Friendship createFriendship(@RequestBody Friendship friendship) {
        return friendshipService.createFriendship(friendship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Friendship> updateFriendship(@PathVariable Long id, @RequestBody Friendship friendshipDetails) {
        Friendship updatedFriendship = friendshipService.updateFriendship(id, friendshipDetails);
        return ResponseEntity.ok(updatedFriendship);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteFriendship(id);
        return ResponseEntity.noContent().build();
    }

}
