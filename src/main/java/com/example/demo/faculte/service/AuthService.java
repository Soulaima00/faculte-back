package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.User;

public interface AuthService {
    String registerEtudiant(User user);
    String registerProf(User user);
    User login(String email, String password);
}