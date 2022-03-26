package com.antsiferov.controllers;

import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import com.antsiferov.services.PostService;
import com.antsiferov.services.StorageService;
import com.antsiferov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private StorageService storageService;

    @PostMapping("/new")
    public String newPost(@RequestParam String subject,
                          @RequestParam String text,
                          @RequestParam(value = "pictures", required = false) MultipartFile[] pictures,
                          Authentication authentication) {
        storageService.uploadFiles(pictures);
        User author = userService.findUserByName(authentication.getName());
        String URLs = postService.getURLs(pictures);
        Post newPost = new Post(subject, text, author, URLs);
        postService.save(newPost);
        return "redirect:/feed";
    }

}