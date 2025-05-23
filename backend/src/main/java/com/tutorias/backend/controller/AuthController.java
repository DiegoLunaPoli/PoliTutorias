package com.tutorias.backend.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorias.backend.model.User;
import com.tutorias.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    static class LoginRequest {
        private String username;
        private String password;

        // Getters y Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterReq {
        private String username;
        private String password;
        private String role; // Puede ser "PROFESOR" o "ESTUDIANTE"
    
        // Getters y setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getUsername()) && "1234".equals(request.getPassword())) {
            return "Login exitoso";
        } else {
            return "Credenciales incorrectas";
        }
    }


   @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ya existe.");
        }

        user.setPassword(user.getPassword()); // Aquí podrías encriptar con BCrypt
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Usuario registrado correctamente",
                "username", user.getUsername(),
                "role", user.getRole()
        ));
    }
}