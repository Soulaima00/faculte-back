package com.example.demo.faculte.controller;

import com.example.demo.faculte.entity.Comment;
import com.example.demo.faculte.entity.Post;
import com.example.demo.faculte.service.CommentService;
import com.example.demo.faculte.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forum")
@CrossOrigin(origins = "*")
public class ForumController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody Post post, @RequestParam Long userId) {
        if (post.getTitle() == null || post.getContent() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Le titre et le contenu sont requis"));
        }
        Post createdPost = postService.createPost(post, userId);
        return ResponseEntity.ok().body(Map.of("message", "Post créé avec succès", "post", createdPost));
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/posts/user/{userId}")
    public List<Post> getPostsByAuthorId(@PathVariable Long userId) {
        return postService.getPostsByAuthorId(userId);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody Post updatedPost, @RequestParam Long userId) {
        Post post = postService.updatePost(postId, updatedPost, userId);
        return ResponseEntity.ok().body(Map.of("message", "Post mis à jour avec succès", "post", post));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        postService.deletePost(postId, userId);
        return ResponseEntity.ok().body(Map.of("message", "Post supprimé avec succès"));
    }

    @PostMapping("/comments")
    public ResponseEntity<?> createComment(@RequestBody Comment comment, @RequestParam Long userId, @RequestParam Long postId) {
        Comment createdComment = commentService.createComment(comment, userId, postId);
        return ResponseEntity.ok().body(Map.of("message", "Commentaire créé avec succès", "comment", createdComment));
    }

    @GetMapping("/comments/post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/comments/user/{userId}")
    public List<Comment> getCommentsByAuthorId(@PathVariable Long userId) {
        return commentService.getCommentsByAuthorId(userId);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody Comment updatedComment, @RequestParam Long userId) {
        Comment comment = commentService.updateComment(commentId, updatedComment, userId);
        return ResponseEntity.ok().body(Map.of("message", "Commentaire mis à jour avec succès", "comment", comment));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @RequestParam Long userId) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.ok().body(Map.of("message", "Commentaire supprimé avec succès"));
    }
}