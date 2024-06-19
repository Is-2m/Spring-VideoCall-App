package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.entities.MessageLog;
import com.ensamc.mbdio.VideoCallApp.services.ChatService;
import com.ensamc.mbdio.VideoCallApp.services.MessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{userId}/all")
    public List<Chat> getChatsByUserId(@PathVariable Long userId) {
        return chatService.getChatsByUserId(userId);
    }
}
