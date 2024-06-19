package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.CallHistory;
import com.ensamc.mbdio.VideoCallApp.services.CallHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/call-hist")
public class CallHistoryController {

    @Autowired
    private CallHistoryService callHistoryService;

    @GetMapping
    public List<CallHistory> getAllCallHistories() {
        return callHistoryService.getAllCallHistories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CallHistory> getCallHistoryById(@PathVariable Long id) {
        Optional<CallHistory> callHistory = callHistoryService.getCallHistoryById(id);
        return callHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CallHistory createCallHistory(@RequestBody CallHistory callHistory) {
        return callHistoryService.createCallHistory(callHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CallHistory> updateCallHistory(@PathVariable Long id, @RequestBody CallHistory callHistoryDetails) {
        CallHistory updatedCallHistory = callHistoryService.updateCallHistory(id, callHistoryDetails);
        return ResponseEntity.ok(updatedCallHistory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCallHistory(@PathVariable Long id) {
        callHistoryService.deleteCallHistory(id);
        return ResponseEntity.noContent().build();
    }
}
