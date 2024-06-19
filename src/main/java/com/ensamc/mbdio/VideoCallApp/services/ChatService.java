package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.entities.MessageLog;
import com.ensamc.mbdio.VideoCallApp.repositories.IChatRepository;
import com.ensamc.mbdio.VideoCallApp.repositories.IMessageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {


    @Autowired
    private IChatRepository chatRepository;

    public List<Chat> getChatsByUserId(Long userId) {
        return chatRepository.findChatsByUserId(userId);
//    return new ArrayList<>();
    }
}
