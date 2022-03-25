package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.CustomUser;
import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import com.antsiferov.repository.CommentRepository;
import com.antsiferov.repository.PostRepository;
import com.antsiferov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/feed/{itemId}/comment")
    public String addComment(Authentication authentication,
                             @RequestParam String text,
                             @PathVariable("itemId") Long postId) {
        Post currentPost = postRepository.findPostById(postId);
        User author = usersRepository.findUserByName(authentication.getName());
        Comment comment = new Comment(text, currentPost, author);
        commentRepository.save(comment);
        return "redirect:/feed/{itemId}";
    }

}