package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Role;
import com.example.demo.faculte.entity.User;
import com.example.demo.faculte.repository.RoleRepository;
import com.example.demo.faculte.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public String registerEtudiant(User user) {
        return registerUserWithRole(user, "ETUDIANT");
    }

    public String registerProf(User user) {
        return registerUserWithRole(user, "PROFESSEUR");
    }

    private String registerUserWithRole(User user, String roleName) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email déjà utilisé !";
        }

        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));

        user.setRole(role);
        userRepository.save(user);
        return roleName + " inscrit avec succès !";
    }
    
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
