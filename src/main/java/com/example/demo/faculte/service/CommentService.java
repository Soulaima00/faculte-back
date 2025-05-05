package com.example.demo.faculte.service;

import java.util.List;

import com.example.demo.faculte.entity.Comment;

public interface CommentService {
    List<Comment> getCommentsByPostId(Long postId);
    Comment addComment(Comment comment);
    Comment updateComment(Long id, String contenu);
    void deleteComment(Long id);
}
