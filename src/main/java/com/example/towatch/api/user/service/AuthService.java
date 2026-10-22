package com.example.towatch.api.user.service;

import com.example.towatch.api.jwt.JwtService;
import com.example.towatch.api.user.dto.AuthResponse;
import com.example.towatch.api.user.dto.AuthRequest;
import com.example.towatch.api.user.dto.MeResponse;
import com.example.towatch.api.user.entity.User;
import com.example.towatch.api.user.exception.InvalidLoginOrPasswordException;
import com.example.towatch.api.user.exception.UserNotFoundException;
import com.example.towatch.api.user.exception.UsernameAlreadyExistsException;
import com.example.towatch.api.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest request) {
        String username = request.username().toLowerCase();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidLoginOrPasswordException());

        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new InvalidLoginOrPasswordException();
        }

        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse register(AuthRequest request) {
        String username = request.username().toLowerCase();

        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole("USER");

        userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(user));
    }

    public MeResponse me(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));

        return new MeResponse(user.getId(), user.getUsername());
    }
}
