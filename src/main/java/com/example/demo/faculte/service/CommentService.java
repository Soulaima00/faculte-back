package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment, Long userId, Long postId);
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getCommentsByAuthorId(Long userId);
    Comment updateComment(Long commentId, Comment updatedComment, Long userId);
    void deleteComment(Long commentId, Long userId);
}