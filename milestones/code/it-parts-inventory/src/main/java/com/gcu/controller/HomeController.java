package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the application's home page.
 */
@Controller
public class HomeController {

    /**
     * Displays the welcome page.
     *
     * @param model the data supplied to the view
     * @return the home template
     */
    @GetMapping("/")
    public String displayHome(Model model) {
        model.addAttribute("title", "Home");
        return "home";
    }
}