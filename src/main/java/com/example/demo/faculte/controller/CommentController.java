package com.example.demo.faculte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.faculte.entity.Comment;
import com.example.demo.faculte.service.CommentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Récupérer tous les commentaires pour un post donné
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // Ajouter un commentaire
    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    // Modifier un commentaire (seulement son contenu)
    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody String contenu) {
        return commentService.updateComment(id, contenu);
    }

    // Supprimer un commentaire
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
