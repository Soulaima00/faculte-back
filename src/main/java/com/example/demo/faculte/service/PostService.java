package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post, Long userId);
    List<Post> getAllPosts();
    Post getPostById(Long postId);
    List<Post> getPostsByAuthorId(Long userId);
    Post updatePost(Long postId, Post updatedPost, Long userId);
    void deletePost(Long postId, Long userId);
}