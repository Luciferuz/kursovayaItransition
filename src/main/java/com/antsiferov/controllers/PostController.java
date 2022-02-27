package com.antsiferov.controllers;

import com.antsiferov.entity.Post;
import com.antsiferov.repository.PostRepository;
import com.antsiferov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/new")
    public String newPost(@RequestParam String subject, @RequestParam String postText) {
        Post newPost = new Post(subject, postText);
        postRepository.save(newPost);
        return "feed";
    }
}


