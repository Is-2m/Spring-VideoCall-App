package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;

import java.util.List;

public interface IChatService {
    List<Chat> getChatsByUserId(Long userId);
    Chat getChatBySenderAndReceiver(Long senderId, Long receiverId);
    Chat createChat(Chat chat);
}
