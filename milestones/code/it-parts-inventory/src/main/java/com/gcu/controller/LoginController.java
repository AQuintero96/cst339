package com.gcu.controller;

import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.AccountServiceInterface;
import com.gcu.business.PartServiceInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.SessionUser;

/**
 * Handles login, the database inventory list, and logout.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    private final AccountServiceInterface accountService;
    private final PartServiceInterface partService;

    /**
     * Receives account and inventory business services.
     * @param accountService credential checking service
     * @param partService inventory service
     */
    public LoginController(
            AccountServiceInterface accountService,
            PartServiceInterface partService) {
        this.accountService = accountService;
        this.partService = partService;
    }

    /**
     * Displays the login form.
     * @param model view attributes
     * @return login template
     */
    @GetMapping("/login")
    public String displayLogin(Model model) {
        model.addAttribute("title", "Log In");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Checks credentials and starts a fresh authenticated session.
     * @param loginModel submitted credentials
     * @param bindingResult validation results
     * @param model view attributes
     * @param request current request
     * @return login form or inventory redirect
     */
    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("loginModel") LoginModel loginModel,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {

        model.addAttribute("title", "Log In");

        if (bindingResult.hasErrors()) {
            return "login";
        }

        Optional<SessionUser> user;

        try {
            user = accountService.authenticate(
                    loginModel.getUsername(), loginModel.getPassword());
        } catch (DataAccessException exception) {
            LOGGER.error("Database operation failed during login: {}",
                    exception.getClass().getSimpleName());
            bindingResult.reject("login.unavailable",
                    "Login is temporarily unavailable. Please try again.");
            return "login";
        }

        if (!user.isPresent()) {
            bindingResult.reject("login.invalid",
                    "Username or password is incorrect.");
            return "login";
        }

        // Replaces the old session to prevent reuse of its identifier.
        HttpSession previousSession = request.getSession(false);
        if (previousSession != null) {
            previousSession.invalidate();
        }

        request.getSession(true).setAttribute("currentUser", user.get());
        return "redirect:/inventory";
    }

    /**
     * Displays database inventory for a logged-in user.
     * @param request current request
     * @param model view attributes
     * @return inventory template or login redirect
     */
    @GetMapping("/inventory")
    public String displayInventory(
            HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

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

    /**
     * Ends the current session.
     * @param request current request
     * @param redirectAttributes logout notification
     * @return login redirect
     */
    @PostMapping("/logout")
    public String logout(
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        redirectAttributes.addFlashAttribute(
                "successMessage", "You have been logged out.");
        return "redirect:/login";
    }
}