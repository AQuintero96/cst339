package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Displays the publicly accessible home page.
 */
@Controller
public class HomeController {

    /**
     * Supplies the home view.
     * @param model view attributes
     * @return home template
     */
    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("title", "My Home");
        return "home";
    }
}