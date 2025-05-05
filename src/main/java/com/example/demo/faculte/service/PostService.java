package com.example.demo.faculte.service;

import java.util.List;

import com.example.demo.faculte.entity.Post;

public interface PostService {
    List<Post> getAllPosts();
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
