package com.antsiferov.controllers;

import com.antsiferov.entity.Comment;
import com.antsiferov.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment/edit/{id}")
public class CommentEditController {

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String editComment(Model model, @PathVariable("id") Long commentId) {
        Comment comment = commentService.findCommentById(commentId);
        model.addAttribute("comment", comment);
        return "editcomment";
    }

    @PostMapping("")
    public String saveEditedComment(@PathVariable("id") Long commentId, @RequestParam String text) {
        commentService.updateCommentText(commentId, text);
        Long postId = commentService.findCommentById(commentId).getPost().getId();
        return "redirect:/feed/" + postId;
    }

}