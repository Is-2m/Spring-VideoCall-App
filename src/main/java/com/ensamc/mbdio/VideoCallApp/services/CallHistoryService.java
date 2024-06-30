package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.repositories.ICallHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallHistoryService implements ICallHistoryService {
    @Autowired
    private ICallHistoryRepository callRepository;

    @Override
    public List<CallHistory> getAllCalls() {
        return callRepository.findAll();
    }

    @Override
    public Optional<CallHistory> getCallById(Long id) {
        return callRepository.findById(id);
    }

    @Override
    public CallHistory createCall(CallHistory callHistory) {
        return callRepository.save(callHistory);
    }

    @Override
    public CallHistory updateCall(Long id, CallHistory callHistoryDetails) {
        CallHistory callHistory = callRepository.findById(id).orElseThrow(() -> new RuntimeException("Call not found"));

        callHistory.setCaller(callHistoryDetails.getCaller());
        callHistory.setReceiver(callHistoryDetails.getReceiver());
        callHistory.setDate(callHistoryDetails.getDate());
        callHistory.setDuration(callHistoryDetails.getDuration());

        return callRepository.save(callHistory);
    }

    @Override
    public void deleteCall(Long id) {
        callRepository.deleteById(id);
    }
}

