package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import com.antsiferov.services.CommentService;
import com.antsiferov.services.PostService;
import com.antsiferov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentsController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/feed/{itemId}/comment")
    public String addComment(Authentication authentication,
                             @RequestParam String text,
                             @PathVariable("itemId") Long postId) {
        Post currentPost = postService.findPostById(postId);
        User author = userService.findUserByName(authentication.getName());
        Comment comment = new Comment(text, currentPost, author);
        commentService.save(comment);
        return "redirect:/feed/{itemId}";
    }

}