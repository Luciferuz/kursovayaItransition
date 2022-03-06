package com.antsiferov.controllers;

import com.antsiferov.entity.Post;
import com.antsiferov.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class FeedController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/feed")
    public String feed(Model model) {
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        return "feed";
    }

    @GetMapping("/new")
    public String newPost() {
        return "new";
    }

}