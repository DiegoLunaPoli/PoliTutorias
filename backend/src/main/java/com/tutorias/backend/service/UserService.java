package com.tutorias.backend.service;

import com.tutorias.backend.model.User;
import com.tutorias.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registra un nuevo usuario. Lanza IllegalArgumentException si ya existe.
     */
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        // Aquí podrías encriptar la contraseña con BCryptPasswordEncoder
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su nombre.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}