package com.antsiferov.controllers;

import com.antsiferov.entity.CustomUser;
import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import com.antsiferov.repository.PostRepository;
import com.antsiferov.repository.UsersRepository;
import com.antsiferov.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/new")
    public String newPost(@RequestParam String subject,
                          @RequestParam String postText,
                          @RequestParam(value = "pictures", required = false) MultipartFile[] pictures,
                          @AuthenticationPrincipal CustomUser user) {
        if (!pictures[0].isEmpty()) {
            storageService.uploadFiles(pictures);
        }
        User author = usersRepository.findUserById(user.getId());
        Post newPost = new Post(subject, postText, author, pictures);
        postRepository.save(newPost);
        return "redirect:/feed";
    }

}