package com.antsiferov.controllers;

import com.antsiferov.entity.Post;
import com.antsiferov.repository.PostRepository;
import com.antsiferov.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private StorageService storageService;


    @PostMapping("/new")
    public String newPost(@RequestParam String subject,
                          @RequestParam String postText,
                          @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {
        storageService.uploadFiles(pictures);
        Post newPost = new Post(subject, postText, pictures);
        postRepository.save(newPost);
        return "redirect:/feed";
    }


}