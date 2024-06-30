package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.repositories.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService {

    @Autowired
    private IChatRepository chatRepository;

    @Override
    public List<Chat> getChatsByUserId(Long userId) {
        return chatRepository.findChatsByUserId(userId);
    }

    @Override
    public Chat getChatBySenderAndReceiver(Long senderId, Long receiverId) {
        User sender = new User(senderId);
        User receiver = new User(receiverId);

        Chat res = chatRepository.findBySenderEqualsAndReceiverEquals(sender, receiver);
        if (res == null) {
            res = chatRepository.findBySenderEqualsAndReceiverEquals(receiver, sender);
        }
        if (res == null) {
            res = new Chat();
            res.setSender(sender);
            res.setReceiver(receiver);
            chatRepository.save(res);
        }
        return res;
    }

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}

