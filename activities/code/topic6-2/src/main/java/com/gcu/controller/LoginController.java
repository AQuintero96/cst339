package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Displays the login page; Spring Security processes submitted credentials.
 */
@Controller
public class LoginController {

    /**
     * Displays the custom login form.
     * @param model view attributes
     * @return login template
     */
    @GetMapping("/login")
    public String display(Model model) {
        model.addAttribute("title", "Login Form");
        return "login";
    }
}