package com.smartlogix.auth.factory;

import com.smartlogix.auth.model.*;
import org.springframework.stereotype.Component;

@Component
public class ProfileFactory {

    public static Profile createProfile(Role role) {
        return switch (role) {
            case ADMIN -> new AdminProfile();
            case EMPLOYEE -> new ClientProfile(); // Podrías crear EmployeeProfile más adelante
            default -> throw new IllegalArgumentException("Rol no soportado para creación de perfil");
        };
    }
}