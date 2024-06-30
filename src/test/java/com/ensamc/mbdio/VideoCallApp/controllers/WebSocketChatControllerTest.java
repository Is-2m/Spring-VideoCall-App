package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.controllers.WebSocketChatController;
import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.services.MessageService;
import com.ensamc.mbdio.VideoCallApp.services.CallHistoryService;
import com.ensamc.mbdio.VideoCallApp.wrapper.ResponseWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.Wrapper;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@SpringJUnitConfig
public class WebSocketChatControllerTest {

    @Autowired
    private WebSocketChatController webSocketChatController;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @MockBean
    private MessageService messageService;

    @MockBean
    private CallHistoryService callService;

    private Message testMessage;
    private CallHistory testCall;
    private User sender;
    private User receiver;
    private Chat chat;

    @BeforeEach
    void setUp() {
        sender = new User();
        sender.setId(1L);
        receiver = new User();
        receiver.setId(2L);
        chat = new Chat();
        chat.setId(1L);

        testMessage = new Message();
        testMessage.setSender(sender);
        testMessage.setReceiver(receiver);
        testMessage.setChat(chat);
        testMessage.setContent("Test message");

        testCall = new CallHistory();
        testCall.setCaller(sender);
        testCall.setReceiver(receiver);
    }

    @Test
    void testSendMessage() {
        webSocketChatController.sendMessage("1", testMessage);

        verify(messageService).createMessage(testMessage);
        verify(messagingTemplate).convertAndSend(eq("/topic/notifications/1"), any(ResponseWrapper.class));
        verify(messagingTemplate).convertAndSend(eq("/topic/notifications/2"), any(ResponseWrapper.class));
    }

    @Test
    void testSendCall() {
        webSocketChatController.sendCall("1", testCall);

        verify(callService).createCall(testCall);
        verify(messagingTemplate).convertAndSend(eq("/topic/notifications/2"), any(ResponseWrapper.class));
    }
}