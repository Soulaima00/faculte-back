package com.example.demo.faculte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.faculte.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}