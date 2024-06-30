package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.config.TestSecurityConfig;
import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.services.CallHistoryService;
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
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CallHistoryController.class)
@Import({JwtUtil.class,TestSecurityConfig.class})
public class CallHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CallHistoryService callHistoryService;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private CallHistory testCall;

    @BeforeEach
    void setUp() {
        testCall = new CallHistory();
        testCall.setId(1L);
        testCall.setDuration(120);
    }

    @Test
    @WithMockUser
    void getAllCallsTest() throws Exception {
        when(callHistoryService.getAllCalls()).thenReturn(Arrays.asList(testCall));

        mockMvc.perform(get("/api/call"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].duration").value(120));
    }

    @Test
    @WithMockUser
    void getCallByIdTest() throws Exception {
        when(callHistoryService.getCallById(1L)).thenReturn(Optional.of(testCall));

        mockMvc.perform(get("/api/call/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.duration").value(120));
    }

    @Test
    @WithMockUser
    void getCallByIdNotFoundTest() throws Exception {
        when(callHistoryService.getCallById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/call/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void createCallTest() throws Exception {
        when(callHistoryService.createCall(any(CallHistory.class))).thenReturn(testCall);

        mockMvc.perform(post("/api/call")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCall)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.duration").value(120));
    }

    @Test
    @WithMockUser
    void updateCallTest() throws Exception {
        when(callHistoryService.updateCall(eq(1L), any(CallHistory.class))).thenReturn(testCall);

        mockMvc.perform(put("/api/call/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCall)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.duration").value(120));
    }

    @Test
    @WithMockUser
    void deleteCallTest() throws Exception {
        doNothing().when(callHistoryService).deleteCall(1L);

        mockMvc.perform(delete("/api/call/1"))
                .andExpect(status().isNoContent());

        verify(callHistoryService, times(1)).deleteCall(1L);
    }
}