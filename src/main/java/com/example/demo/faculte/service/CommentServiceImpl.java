package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Comment;
import com.example.demo.faculte.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepo.findByPostId(postId);
    }

    @Override
    public Comment addComment(Comment comment) {
        comment.setDateCreation(LocalDateTime.now());
        return commentRepo.save(comment);
    }

    @Override
    public Comment updateComment(Long id, String contenu) {
        Comment c = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        c.setContenu(contenu);
        return commentRepo.save(c);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
}
