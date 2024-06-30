package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.config.TestSecurityConfig;
import com.ensamc.mbdio.VideoCallApp.controllers.ChatController;
import com.ensamc.mbdio.VideoCallApp.entities.Chat;
import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.entities.User;
import com.ensamc.mbdio.VideoCallApp.services.ChatService;
import com.ensamc.mbdio.VideoCallApp.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
@Import({JwtUtil.class, TestSecurityConfig.class})
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private Chat testChat;

    private User sender;
    private User receiver;
    private Message testMessage;

    @BeforeEach
    void setUp() {
        sender = new User();
        sender.setId(1L);
        receiver = new User();
        receiver.setId(2L);

        testMessage = new Message();
        testMessage.setId(13L);
        testMessage.setSender(sender);
        testMessage.setReceiver(receiver);
        testMessage.setChat(testChat);
        testMessage.setContent("Test message");

        testChat = new Chat();
        testChat.setId(1L);
        testChat.setMessageList(Arrays.asList(testMessage));
    }

    @Test
    void getChatsByUserIdTest() throws Exception {
        List<Chat> chats = Arrays.asList(testChat);
        when(chatService.getChatsByUserId(1L)).thenReturn(chats);

        mockMvc.perform(get("/api/chats/1/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(1));
    }

    @Test
    void createChatTest() throws Exception {
        when(chatService.createChat(any(Chat.class))).thenReturn(testChat);

        mockMvc.perform(post("/api/chats/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testChat)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userId").value(1));
    }
}
