package com.example.demo.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.faculte.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByDateCreationDesc();
}
