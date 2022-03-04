package com.antsiferov.controllers;

import com.antsiferov.entity.User;
import com.antsiferov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@RequestParam String name, @RequestParam String password) {
        User user = new User(name, password);
        usersRepository.save(user);
        return "redirect:/home";
    }

}