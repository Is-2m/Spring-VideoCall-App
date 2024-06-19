package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.repositories.ICallHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallHistoryService {
    @Autowired
    private ICallHistoryRepository callHistoryRepository;

    public List<CallHistory> getAllCallHistories() {
        return callHistoryRepository.findAll();
    }

    public Optional<CallHistory> getCallHistoryById(Long id) {
        return callHistoryRepository.findById(id);
    }

    public CallHistory createCallHistory(CallHistory callHistory) {
        return callHistoryRepository.save(callHistory);
    }

    public CallHistory updateCallHistory(Long id, CallHistory callHistoryDetails) {
        CallHistory callHistory = callHistoryRepository.findById(id).orElseThrow(() -> new RuntimeException("CallHistory not found"));

        callHistory.setCaller(callHistoryDetails.getCaller());
        callHistory.setReceiver(callHistoryDetails.getReceiver());
        callHistory.setDate(callHistoryDetails.getDate());
        callHistory.setDuration(callHistoryDetails.getDuration());

        return callHistoryRepository.save(callHistory);
    }

    public void deleteCallHistory(Long id) {
        callHistoryRepository.deleteById(id);
    }

}
