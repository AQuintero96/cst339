package com.gcu.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Displays the login page and authenticated inventory.
 * Spring Security processes login submissions, logout, and access checks.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    private final com.gcu.business.PartServiceInterface partService;

    /**
     * Receives the inventory business service.
     *
     * @param partService inventory service
     */
    public LoginController(
            com.gcu.business.PartServiceInterface partService) {
        this.partService = partService;
    }

    /**
     * Displays the login form.
     * Authentication errors and logout messages are rendered by the template.
     *
     * @param model view attributes
     * @return login template
     */
    @GetMapping("/login")
    public String displayLogin(Model model) {
        model.addAttribute("title", "Log In");
        return "login";
    }

    /**
     * Displays stored inventory after Spring Security authenticates the request.
     *
     * @param model view attributes
     * @return inventory template
     */
    @GetMapping("/inventory")
    public String displayInventory(Model model) {
        model.addAttribute("title", "Inventory");

        try {
            model.addAttribute("parts", partService.getParts());
            model.addAttribute("loadFailed", false);
        } catch (DataAccessException exception) {
            LOGGER.error("Inventory retrieval failed: {}",
                    exception.getClass().getSimpleName());

            // A database failure must not appear as an empty inventory.
            model.addAttribute("parts", Collections.emptyList());
            model.addAttribute("loadFailed", true);
            model.addAttribute("errorMessage",
                    "Inventory is temporarily unavailable. Please try again.");
        }

        return "inventory";
    }
}