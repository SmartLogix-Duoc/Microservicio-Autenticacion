package com.smartlogix.auth.repository;

import com.smartlogix.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Añadimos esto para seguir el estándar de Spring
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);

    // Añadimos esto para validar correos duplicados en el futuro registro
    Boolean existsByEmail(String email);
}