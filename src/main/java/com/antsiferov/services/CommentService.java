package com.antsiferov.services;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.User;
import com.antsiferov.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> search(String text) {
        return commentRepository.search(text);
    }

    public List<Comment> findAllCommentsByUser(User user) {
        return commentRepository.findAllCommentsByUser(user);
    }

    public List<Comment> findAllCommentsByPostId(Long id) {
        return commentRepository.findAllCommentsByPostId(id);
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment findCommentById(Long id) {
        return commentRepository.findCommentById(id);
    }

    public void updateCommentText(Long commentId, String text) {
        commentRepository.updateCommentText(commentId, text);
    }

    public void deleteUserComments(Long id) {
        commentRepository.deleteUserComments(id);
    }
}