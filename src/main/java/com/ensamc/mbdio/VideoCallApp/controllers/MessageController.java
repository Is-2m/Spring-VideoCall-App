package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.services.MessageService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message-log")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public Message createMessage(@RequestBody Message message) {

        return messageService.createMessage(message);
    }

}
