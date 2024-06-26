package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Message;
import com.ensamc.mbdio.VideoCallApp.repositories.IMessageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageLogService {

    @Autowired
    private IMessageLogRepository messageLogRepository;

//    public List<MessageLog> getAllMessageLogs() {
//        return messageLogRepository.findAll();
//    }

//    public Optional<MessageLog> getMessageLogById(Long id) {
//        return messageLogRepository.findById(id);
//    }

    public Message createMessageLog(Message message) {
        return messageLogRepository.save(message);
    }

//    public MessageLog updateMessageLog(Long id, MessageLog messageLogDetails) {
//        MessageLog messageLog = messageLogRepository.findById(id).orElseThrow(() -> new RuntimeException("MessageLog not found"));
//
//        messageLog.setChat(messageLogDetails.getChat());
//        messageLog.setContent(messageLogDetails.getContent());
//        messageLog.setDate(messageLogDetails.getDate());
//
//        return messageLogRepository.save(messageLog);
//    }

//    public void deleteMessageLog(Long id) {
//        messageLogRepository.deleteById(id);
//    }
}
