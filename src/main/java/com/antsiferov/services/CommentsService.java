package com.antsiferov.services;

import com.antsiferov.entity.Comment;
import com.antsiferov.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> search(String text) {
        return commentRepository.search(text);
    }
}
