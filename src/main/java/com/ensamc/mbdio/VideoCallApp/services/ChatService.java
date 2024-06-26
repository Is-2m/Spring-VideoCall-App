package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.repositories.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {


    @Autowired
    private IChatRepository chatRepository;

    public List<Chat> getChatsByUserId(Long userId) {
        return chatRepository.findChatsByUserId(userId);
//    return new ArrayList<>();
    }
}
