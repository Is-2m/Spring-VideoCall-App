package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.services.MessageLogService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message-log")
public class MessageLogController {

    @Autowired
    private MessageLogService messageLogService;
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public Message createMessageLog(@RequestBody Message message) {

        return messageLogService.createMessageLog(message);
    }

}
