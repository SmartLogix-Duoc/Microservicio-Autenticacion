package com.smartlogix.auth.model;

import org.springframework.stereotype.Component;

@Component
public class ProfileFactory {

    public static Profile createProfile(Role role) {
        return switch (role) {
            case ADMIN -> new AdminProfile();
            case USER -> new ClientProfile();
            default -> throw new IllegalArgumentException("Rol no soportado: " + role);
        };
    }
}