package com.smartlogix.auth.service;

import com.smartlogix.auth.dto.AuthRequest;
import com.smartlogix.auth.dto.AuthResponse;
import com.smartlogix.auth.model.Role;
import com.smartlogix.auth.model.User;
import com.smartlogix.auth.repository.UserRepository;
import com.smartlogix.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // ── Registro de usuario interno (USER) ────────────────────────────────────
    public AuthResponse register(AuthRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Usuario ya existe");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .userRole(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    // ── Registro de cliente (CLIENT) ──────────────────────────────────────────
    // Mismo flujo que register() pero asigna rol CLIENT.
    // El método existe pero no está expuesto en ningún endpoint aún.
    public AuthResponse registerClient(AuthRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Usuario ya existe");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .userRole(Role.CLIENT)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    // ── Login (compartido por todos los roles) ────────────────────────────────
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}