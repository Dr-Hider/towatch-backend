package com.example.towatch.api.user.controller;

import com.example.towatch.api.user.dto.AuthResponse;
import com.example.towatch.api.user.dto.AuthRequest;
import com.example.towatch.api.user.dto.MeResponse;
import com.example.towatch.api.user.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.register(authRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(Authentication authentication) {
        Long id = Long.valueOf(authentication.getName());

        MeResponse response = authService.me(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
