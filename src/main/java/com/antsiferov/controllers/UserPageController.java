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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class UserPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/lk/{userId}")
    public String lkWithId(Model model, @PathVariable("userId") Long userId) {
        return findUserPage(model, userId);
    }

    @GetMapping("/lk")
    public String lk(Model model, Authentication authentication) {
        User current = userService.findUserByName(authentication.getName());
        return findUserPage(model, current.getId());
    }

    private String findUserPage(Model model, Long userId) {
        User user = userService.findUserById(userId);
        List<Post> posts = postService.findAllPostsByUser(user);
        List<Comment> comments = commentService.findAllCommentsByUser(user);
        Collections.reverse(posts);
        Collections.reverse(comments);
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("comments", comments);
        return "userpage";
    }

}