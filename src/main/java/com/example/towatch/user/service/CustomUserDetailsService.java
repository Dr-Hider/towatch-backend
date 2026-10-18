
package com.example.towatch.user.service;

import com.example.towatch.user.entity.User;
import com.example.towatch.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        String normalizedUsername =
                username.trim().toLowerCase(Locale.ROOT);

        User user = userRepository
                .findByUsernameIgnoreCase(normalizedUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid login or password"));

        String authority = switch (user.getRole()) {
            case "USER" -> "ROLE_USER";
            case "ADMIN" -> "ROLE_ADMIN";
            default -> throw new UsernameNotFoundException("Invalid login or password");
        };

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .authorities(new SimpleGrantedAuthority(authority))
                .build();
    }
}
