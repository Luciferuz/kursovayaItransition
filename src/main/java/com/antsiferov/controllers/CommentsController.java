package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/feed/{itemId}/comment")
    public String addComment(@RequestParam String text) {
        Comment comment = new Comment(text);
        commentRepository.save(comment);
        return "redirect:/feed/{itemId}";
    }

}