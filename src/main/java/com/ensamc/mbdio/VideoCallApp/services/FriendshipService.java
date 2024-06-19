package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Friendship;
import com.ensamc.mbdio.VideoCallApp.repositories.IFriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendshipService {

    @Autowired
    private IFriendshipRepository friendshipRepository;

    public List<Friendship> getAllFriendships() {
        return friendshipRepository.findAll();
    }

    public Optional<Friendship> getFriendshipById(Long id) {
        return friendshipRepository.findById(id);
    }

    public Friendship createFriendship(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    public Friendship updateFriendship(Long id, Friendship friendshipDetails) {
        Friendship friendship = friendshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Friendship not found"));

        friendship.setSender(friendshipDetails.getSender());
        friendship.setReceiver(friendshipDetails.getReceiver());
        friendship.setState(friendshipDetails.getState());

        return friendshipRepository.save(friendship);
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.deleteById(id);
    }
}
