package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.services.CommentsService;
import com.antsiferov.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FindController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/find")
    public String find(Model model) {
        model.addAttribute("textlabel", "Результаты поиска");
        return "findpage";
    }

    @PostMapping("/find")
    public String showFindResults(Model model, @RequestParam String text) {
        List<Post> searchResultsPosts = postService.search(text);
        List<Comment> searchResultsComments = commentsService.search(text);
        model.addAttribute("searchResultsPosts", searchResultsPosts);
        model.addAttribute("searchResultsComments", searchResultsComments);
        model.addAttribute("textlabel", "Результаты поиска для: " + text);
        return "findpage";
    }
}
