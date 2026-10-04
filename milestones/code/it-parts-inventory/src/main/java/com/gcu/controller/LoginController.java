package com.gcu.controller;

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
import com.gcu.model.LoginModel;
import com.gcu.model.SessionUser;

/**
 * Handles database-backed login, the inventory landing page, and logout.
 */
@Controller
public class LoginController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);

    private final AccountServiceInterface accountService;

    /**
     * Receives the account service through constructor injection.
     *
     * @param accountService the credential checking service
     */
    public LoginController(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    /**
     * Displays an empty login form.
     *
     * @param model the view attributes
     * @return the login template
     */
    @GetMapping("/login")
    public String displayLogin(Model model) {
        model.addAttribute("title", "Log In");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Checks credentials and establishes a new session.
     *
     * @param loginModel the submitted credentials
     * @param bindingResult the validation results
     * @param model the view attributes
     * @param request the current HTTP request
     * @return the form or a redirect to inventory
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
                    loginModel.getUsername(),
                    loginModel.getPassword());
        } catch (DataAccessException exception) {
            // Reports the failure without logging submitted credentials.
            LOGGER.error("Database operation failed during login: {}",
                    exception.getClass().getSimpleName());

            bindingResult.reject(
                    "login.unavailable",
                    "Login is temporarily unavailable. Please try again.");
            return "login";
        }

        if (!user.isPresent()) {
            bindingResult.reject(
                    "login.invalid",
                    "Username or password is incorrect.");
            return "login";
        }

        // Replaces any previous session after successful authentication.
        HttpSession previousSession = request.getSession(false);

        if (previousSession != null) {
            previousSession.invalidate();
        }

        request.getSession(true).setAttribute("currentUser", user.get());
        return "redirect:/inventory";
    }

    /**
     * Displays the inventory landing page.
     *
     * @param model the view attributes
     * @return the inventory template
     */
    @GetMapping("/inventory")
    public String displayInventory(Model model) {
        model.addAttribute("title", "Inventory");
        return "inventory";
    }

    /**
     * Ends the session and redirects to login.
     *
     * @param request the current HTTP request
     * @param redirectAttributes the logout message
     * @return a redirect to login
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