package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.repository.CommentRepository;
import com.antsiferov.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/feed/{itemId}/comment")
    public String addComment(@RequestParam String text, @PathVariable("itemId") Long postId) {
        Post currentPost = postRepository.findPostById(postId);
        Comment comment = new Comment(text, currentPost);
        commentRepository.save(comment);
        return "redirect:/feed/{itemId}";
    }

}