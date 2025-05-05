package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Post;
import com.example.demo.faculte.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAllByOrderByDateCreationDesc();
    }

    @Override
    public Post createPost(Post post) {
        post.setDateCreation(LocalDateTime.now());
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(Long id, Post newPost) {
        Post p = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        p.setTitre(newPost.getTitre());
        p.setContenu(newPost.getContenu());
        return postRepo.save(p);
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }
}

