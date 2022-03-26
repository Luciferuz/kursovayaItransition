package com.antsiferov.controllers;

import com.antsiferov.entity.Post;
import com.antsiferov.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/post/edit/{id}")
public class PostEditController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String editPost(Model model, @PathVariable("id") Long postId) {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "editpost";
    }

    @PostMapping("")
    public String saveEditedPost(@PathVariable("id") Long postId,
                                 @RequestParam String subject,
                                 @RequestParam String text,
                                 @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {
        String URLs = postService.getURLs(pictures);
        postService.updatePost(postId, subject, text, URLs);
        return "redirect:/feed/" + postId;
    }
}