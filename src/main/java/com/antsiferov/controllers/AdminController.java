package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import com.antsiferov.repository.CommentRepository;
import com.antsiferov.repository.PostRepository;
import com.antsiferov.repository.UsersRepository;
import com.antsiferov.services.PostService;
import com.antsiferov.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public String admin(Model model) {
        Iterable<User> users = usersRepository.findAll();
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("posts", posts);
        return "admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "userToDelete") Long id) {
        usersRepository.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String writePostAdmin(Model model) {
        Iterable<User> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "adminnewpost";
    }

    @PostMapping("/new")
    public String savePostAdmin(@RequestParam(value = "selectedAuthor") Long id,
                                @RequestParam String subject,
                                @RequestParam String text,
                                @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {
        storageService.uploadFiles(pictures);
        User author = usersRepository.findUserById(id);
        String URLs = postService.getURLs(pictures);
        Post newPost = new Post(subject, text, author, URLs);
        postRepository.save(newPost);
        return "redirect:/feed";
    }

    @GetMapping("/comment")
    public String writeCommentAdmin(Model model) {
        Iterable<User> users = usersRepository.findAll();
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("posts", posts);
        return "adminnewcomment";
    }

    @PostMapping("/comment")
    public String saveCommentAdmin(@RequestParam(value = "selectedAuthor") Long idUser,
                                   @RequestParam(value = "selectedPost") Long idPost,
                                   @RequestParam String text) {
        Post selectedPost = postRepository.findPostById(idPost);
        User selectedUser = usersRepository.findUserById(idUser);
        Comment comment = new Comment(text, selectedPost, selectedUser);
        commentRepository.save(comment);
        return "redirect:/feed/" + selectedPost.getId();
    }

    @GetMapping("/edit/post")
    public String editPostAdmin(Model model, @RequestParam(value = "selectedPost") Long idPost){
        Post selectedPost = postRepository.findPostById(idPost);
        model.addAttribute("post", selectedPost);
        return "admineditpost";
    }

    @PostMapping("/edit/post/{id}")
    public String saveEditedPostAdmin(@PathVariable("id") Long postId,
                                 @RequestParam String subject,
                                 @RequestParam String text,
                                 @RequestParam(value = "pictures", required = false) MultipartFile[] pictures) {
        String URLs = postService.getURLs(pictures);
        postRepository.updatePost(postId, subject, text, URLs);
        return "redirect:/feed/" + postId;
    }

}
