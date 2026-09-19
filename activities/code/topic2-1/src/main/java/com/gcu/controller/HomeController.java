package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the Activity 2 home page.
 */
@Controller
public class HomeController {

    /**
     * Displays the welcome page at the root address.
     *
     * @return the name of the home view
     */
    @GetMapping("/")
    public String display() {
        return "home";
    }
}