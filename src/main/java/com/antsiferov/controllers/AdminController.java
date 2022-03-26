package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import com.antsiferov.services.CommentService;
import com.antsiferov.services.PostService;
import com.antsiferov.services.StorageService;
import com.antsiferov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private StorageService storageService;

    @GetMapping("")
    public String admin(Model model) {
        Iterable<User> users = userService.findAll();
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("posts", posts);
        return "admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "userToDelete") Long id) {
        commentService.deleteUserComments(id);
        postService.deleteUserPosts(id);
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String writePostAdmin(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "adminnewpost";
    }

    @PostMapping("/new")
    public String savePostAdmin(@RequestParam(value = "selectedAuthor") Long id,
                                @RequestParam String subject,
                                @RequestParam String text,
                                @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {
        storageService.uploadFiles(pictures);
        User author = userService.findUserById(id);
        String URLs = postService.getURLs(pictures);
        Post newPost = new Post(subject, text, author, URLs);
        postService.save(newPost);
        return "redirect:/feed";
    }

    @GetMapping("/comment")
    public String writeCommentAdmin(Model model) {
        Iterable<User> users = userService.findAll();
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("posts", posts);
        return "adminnewcomment";
    }

    @PostMapping("/comment")
    public String saveCommentAdmin(@RequestParam(value = "selectedAuthor") Long idUser,
                                   @RequestParam(value = "selectedPost") Long idPost,
                                   @RequestParam String text) {
        Post selectedPost = postService.findPostById(idPost);
        User selectedUser = userService.findUserById(idUser);
        Comment comment = new Comment(text, selectedPost, selectedUser);
        commentService.save(comment);
        return "redirect:/feed/" + selectedPost.getId();
    }

    @GetMapping("/edit/post")
    public String editPostAdmin(Model model, @RequestParam(value = "selectedPost") Long idPost) {
        Post selectedPost = postService.findPostById(idPost);
        model.addAttribute("post", selectedPost);
        return "admineditpost";
    }

    @PostMapping("/edit/post/{id}")
    public String saveEditedPostAdmin(@PathVariable("id") Long postId,
                                      @RequestParam String subject,
                                      @RequestParam String text,
                                      @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {
        String URLs = postService.getURLs(pictures);
        postService.updatePost(postId, subject, text, URLs);
        return "redirect:/feed/" + postId;
    }

}