package com.antsiferov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TEMPController {

    @GetMapping("/tmp")
    public String tmp() {
        return "tmp";
    }

}
