package com.antsiferov.controllers;

import com.antsiferov.entity.User;
import com.antsiferov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public String admin(Model model) {
        Iterable<User> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "userToDelete") Long id) {
        usersRepository.deleteUserById(id);
        return "redirect:/admin";
    }
}
