package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.entities.MessageLog;
import com.ensamc.mbdio.VideoCallApp.services.MessageLogService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/message-log")
public class MessageLogController {

    @Autowired
    private MessageLogService messageLogService;
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    public MessageLog createMessageLog(@RequestBody MessageLog messageLog) {

        return messageLogService.createMessageLog(messageLog);
    }

}
