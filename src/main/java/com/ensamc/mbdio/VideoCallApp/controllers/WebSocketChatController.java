package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.services.CallHistoryService;
import com.ensamc.mbdio.VideoCallApp.services.MessageService;
import com.ensamc.mbdio.VideoCallApp.wrapper.ResponseWrapper;
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
    private CallHistoryService callService;

    @MessageMapping("/chats/{chatId}/msg")
    public void sendMessage(@PathVariable String chatId, @Payload Message msg) {

        String chatID = msg.getChat().getId().toString();


        String pathReceiver = "/topic/notifications/" + msg.getReceiver().getId();
        String pathSender = "/topic/notifications/" + msg.getSender().getId();

        messageService.createMessage(msg);

        ResponseWrapper<Message> wrappedMsg = new ResponseWrapper<>("message", msg, chatID);

        messagingTemplate.convertAndSend(pathSender, wrappedMsg);
        messagingTemplate.convertAndSend(pathReceiver, wrappedMsg);
    }

    @MessageMapping("/calls")
    public void sendCall(@PathVariable String callId, @Payload CallHistory call) {

        String path = "/topic/notifications/" + call.getReceiver().getId();

        callService.createCall(call);

        ResponseWrapper<CallHistory> wrappedCall = new ResponseWrapper<>("call", call);

        messagingTemplate.convertAndSend(path, wrappedCall);
    }
}
