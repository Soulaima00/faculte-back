package com.example.demo.faculte.controller;

import com.example.demo.faculte.entity.User;
import com.example.demo.faculte.service.AuthService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // pour permettre les requêtes depuis Angular
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup-etudiant")
    public ResponseEntity<?> signupEtudiant(@RequestBody User user) {
        String message = authService.registerEtudiant(user);
        return ResponseEntity.ok().body(Map.of("message", message));
    }

    @PostMapping("/signup-prof")
    public ResponseEntity<?> signupProf(@RequestBody User user) {
        String message = authService.registerProf(user);
        return ResponseEntity.ok().body(Map.of("message", message));
    }
    
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return user; 
        } else {
            return "Email ou mot de passe incorrect !";
        }
    }
}
