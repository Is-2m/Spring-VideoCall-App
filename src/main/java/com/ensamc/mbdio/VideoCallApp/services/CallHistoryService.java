package com.ensamc.mbdio.VideoCallApp.services;

import com.ensamc.mbdio.VideoCallApp.entities.Call;
import com.ensamc.mbdio.VideoCallApp.repositories.ICallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallService {
    @Autowired
    private ICallRepository callRepository;

    public List<Call> getAllCalls() {
        return callRepository.findAll();
    }

    public Optional<Call> getCallById(Long id) {
        return callRepository.findById(id);
    }

    public Call createCall(Call call) {
        return callRepository.save(call);
    }

    public Call updateCall(Long id, Call callDetails) {
        Call call = callRepository.findById(id).orElseThrow(() -> new RuntimeException("Call not found"));

        call.setCaller(callDetails.getCaller());
        call.setReceiver(callDetails.getReceiver());
        call.setDate(callDetails.getDate());
        call.setDuration(callDetails.getDuration());

        return callRepository.save(call);
    }

    public void deleteCall(Long id) {
        callRepository.deleteById(id);
    }

}
