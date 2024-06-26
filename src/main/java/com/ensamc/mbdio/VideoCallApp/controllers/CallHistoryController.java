package com.ensamc.mbdio.VideoCallApp.controllers;

import com.ensamc.mbdio.VideoCallApp.entities.Call;
import com.ensamc.mbdio.VideoCallApp.services.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/call")
public class CallController {

    @Autowired
    private CallService callService;

    @GetMapping
    public List<Call> getAllCalls() {
        return callService.getAllCalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Call> getCallById(@PathVariable Long id) {
        Optional<Call> call = callService.getCallById(id);
        return call.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Call createCall(@RequestBody Call call) {
        return callService.createCall(call);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Call> updateCall(@PathVariable Long id, @RequestBody Call callDetails) {
        Call updatedCall = callService.updateCall(id, callDetails);
        return ResponseEntity.ok(updatedCall);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCall(@PathVariable Long id) {
        callService.deleteCall(id);
        return ResponseEntity.noContent().build();
    }
}
