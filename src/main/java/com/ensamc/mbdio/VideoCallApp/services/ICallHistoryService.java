package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;

import java.util.List;
import java.util.Optional;

public interface ICallHistoryService {
    List<CallHistory> getAllCalls();
    Optional<CallHistory> getCallById(Long id);
    CallHistory createCall(CallHistory callHistory);
    CallHistory updateCall(Long id, CallHistory callHistoryDetails);
    void deleteCall(Long id);
}

