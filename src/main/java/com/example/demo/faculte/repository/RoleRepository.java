package com.example.demo.faculte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.faculte.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}