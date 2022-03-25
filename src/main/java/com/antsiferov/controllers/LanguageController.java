package com.antsiferov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class LanguageController {

    @GetMapping("/changelanguage")
    public String changeLanguage(Locale locale){
        String newLanguage;
        if (locale.toString().equals("en_EN")) {
            newLanguage = "ru_RU";
        } else {
            newLanguage = "en_EN";
        }
        return "redirect:/home/?lang=" + newLanguage;
    }
}
