package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController{
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String recipient = chatMessage.getRecipient();
        messagingTemplate.convertAndSendToUser(recipient, "/queue/messages", chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendToUser("/queue/messages")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
