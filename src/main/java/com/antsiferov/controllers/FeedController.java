package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.services.CommentService;
import com.antsiferov.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class FeedController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/feed")
    public String feed(Model model) {
        List<Post> posts = postService.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "feed";
    }

    @GetMapping("/new")
    public String newPost() {
        return "new";
    }

    @GetMapping("/feed/{itemId}")
    public String showPost(Model model, @PathVariable("itemId") Long id) {
        Post post = postService.findPostById(id);
        List<Comment> comments = commentService.findAllCommentsByPostId(id);
        Collections.reverse(comments);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post";
    }
}