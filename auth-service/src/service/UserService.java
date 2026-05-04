package com.smartlogix.auth.service;

import com.smartlogix.auth.model.User;
import com.smartlogix.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok genera el constructor para la inyección de dependencias
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional // Asegura que si algo falla, no se guarde nada a medias
    public User createUser(User user) {
        // Aquí podrías añadir lógica: cifrar password, validar si el email existe, etc.
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