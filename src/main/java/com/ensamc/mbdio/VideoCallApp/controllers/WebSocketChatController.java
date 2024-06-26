package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.services.CallHistoryService;
import com.ensamc.mbdio.VideoCallApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebSocketChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private CallHistoryService callHistoryService;

    @MessageMapping("/chats/{chatId}/msg")
    public void sendMessage(@PathVariable String chatId, @Payload Message msg) {
        String chatID = msg.getChat().getId().toString();
        String path = "/topic/chats/" + chatID + "/msg";
        messageService.createMessage(msg);
        messagingTemplate.convertAndSend(path, msg);

    }

    @MessageMapping("/calls/{callId}/msg")
    public void sendCall(@PathVariable String chatId, @Payload CallHistory callHistory) {
        String callID = callHistory.getId().toString();
        String path = "/topic/calls/" + callID + "/" + callHistory.getReceiver().getId();
        callHistoryService.createCall(callHistory);
        messagingTemplate.convertAndSend(path, callHistory);

    }

}
