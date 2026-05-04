package com.smartlogix.auth.service;

import com.smartlogix.auth.model.User;
import com.smartlogix.auth.repository.UserRepository;
import com.smartlogix.auth.security.JwtService; // Importante añadir este
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor 
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final JwtService jwtService; // Se añade la inyección del servicio JWT

    @Override
    public String login(String username, String password) {
        // 1. Buscamos al usuario
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Validamos la contraseña (Por ahora texto plano, luego usaremos BCrypt)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Actualizamos la última fecha de acceso (CamelCase)
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // 4. Generamos y retornamos el token
        return jwtService.generateToken(user);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}