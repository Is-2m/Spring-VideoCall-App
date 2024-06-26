package com.ensamc.mbdio.VideoCallApp.entities;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String recipient;  // Add recipient field
    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    // Getters and Setters
}

