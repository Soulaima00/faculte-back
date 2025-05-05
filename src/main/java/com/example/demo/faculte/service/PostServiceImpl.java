package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Post;
import com.example.demo.faculte.entity.User;
import com.example.demo.faculte.repository.PostRepository;
import com.example.demo.faculte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost(Post post, Long userId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur non trouvé"));
        post.setAuthor(author);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAllOrderByCreatedAtDesc();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post non trouvé"));
    }

    @Override
    public List<Post> getPostsByAuthorId(Long userId) {
        return postRepository.findByAuthorId(userId);
    }

    @Override
    public Post updatePost(Long postId, Post updatedPost, Long userId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post non trouvé"));
        if (!existingPost.getAuthor().getId().equals(userId)) {
            throw new SecurityException("Seul l'auteur peut modifier ce post");
        }
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post non trouvé"));
        if (!post.getAuthor().getId().equals(userId)) {
            throw new SecurityException("Seul l'auteur peut supprimer ce post");
        }
        postRepository.delete(post);
    }
}