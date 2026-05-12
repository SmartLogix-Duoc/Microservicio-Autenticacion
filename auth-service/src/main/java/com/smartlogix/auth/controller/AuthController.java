package com.smartlogix.auth.controller;

import com.smartlogix.auth.dto.*;
import com.smartlogix.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ── POST /auth/register → crea usuario con rol USER ───────────────────────
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // ── POST /auth/login → login compartido por todos los roles ──────────────
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // ── POST /auth/register/client → crea usuario con rol CLIENT ─────────────
    // Endpoint reservado, descomenta cuando se decida exponer el registro de clientes
    /*
    @PostMapping("/register/client")
    public ResponseEntity<AuthResponse> registerClient(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.registerClient(request));
    }
    */
}