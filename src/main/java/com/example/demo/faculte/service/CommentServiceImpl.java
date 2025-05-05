package com.example.demo.faculte.service;

import com.example.demo.faculte.entity.Comment;
import com.example.demo.faculte.entity.Post;
import com.example.demo.faculte.entity.User;
import com.example.demo.faculte.repository.CommentRepository;
import com.example.demo.faculte.repository.PostRepository;
import com.example.demo.faculte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long userId, Long postId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur non trouvé"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post non trouvé"));
        comment.setAuthor(author);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    @Override
    public List<Comment> getCommentsByAuthorId(Long userId) {
        return commentRepository.findByAuthorId(userId);
    }

    @Override
    public Comment updateComment(Long commentId, Comment updatedComment, Long userId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Commentaire non trouvé"));
        if (!existingComment.getAuthor().getId().equals(userId)) {
            throw new SecurityException("Seul l'auteur peut modifier ce commentaire");
        }
        existingComment.setContent(updatedComment.getContent());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Commentaire non trouvé"));
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new SecurityException("Seul l'auteur peut supprimer ce commentaire");
        }
        commentRepository.delete(comment);
    }
}