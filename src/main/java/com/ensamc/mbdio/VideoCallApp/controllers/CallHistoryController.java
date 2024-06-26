package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.services.CallHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/call")
public class CallHistoryController {

    @Autowired
    private CallHistoryService callHistoryService;

    @GetMapping
    public List<CallHistory> getAllCalls() {
        return callHistoryService.getAllCalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallHistory> getCallById(@PathVariable Long id) {
        Optional<CallHistory> call = callHistoryService.getCallById(id);
        return call.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CallHistory createCall(@RequestBody CallHistory callHistory) {
        return callHistoryService.createCall(callHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CallHistory> updateCall(@PathVariable Long id, @RequestBody CallHistory callHistoryDetails) {
        CallHistory updatedCallHistory = callHistoryService.updateCall(id, callHistoryDetails);
        return ResponseEntity.ok(updatedCallHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCall(@PathVariable Long id) {
        callHistoryService.deleteCall(id);
        return ResponseEntity.noContent().build();
    }
}
