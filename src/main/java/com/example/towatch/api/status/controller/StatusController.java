package com.example.towatch.api.status.controller;

import com.example.towatch.api.status.dto.StatusRequest;
import com.example.towatch.api.status.dto.StatusResponse;
import com.example.towatch.api.status.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusResponse>> getAllStatuses() {
        List<StatusResponse> response = statusService.getAllStatuses();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> getStatusById(@PathVariable Long id) {
        StatusResponse response = statusService.getStatusById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<StatusResponse> createStatus(
            @Valid @RequestBody StatusRequest request,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        StatusResponse response = statusService.createStatus(request,  userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusRequest request,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        StatusResponse response = statusService.updateStatus(id, request, userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteStatus(
            @PathVariable Long id,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        statusService.deleteStatus(id, userId);

        return ResponseEntity.status(HttpStatus.OK);
    }
}
