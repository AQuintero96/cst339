package com.gcu.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.AccountService;
import com.gcu.model.LoginModel;
import com.gcu.model.SessionUser;

/**
 * Coordinates simulated login, the inventory landing page, and logout.
 * Spring Security page protection will be added in its scheduled milestone.
 */
@Controller
public class LoginController {

    private final AccountService accountService;

    /**
     * Supplies the temporary account service.
     *
     * @param accountService the service used to check credentials
     */
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Displays an empty login form.
     *
     * @param model the data supplied to the view
     * @return the login template
     */
    @GetMapping("/login")
    public String displayLogin(Model model) {
        model.addAttribute("title", "Log In");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Validates credentials and establishes the simulated logged-in state.
     *
     * @param loginModel the submitted credentials
     * @param bindingResult the binding and validation results
     * @param model the data supplied to the view
     * @param request the current HTTP request
     * @return the login form or a redirect to inventory
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

        Optional<SessionUser> user = accountService.authenticate(
                loginModel.getUsername(),
                loginModel.getPassword());

        if (!user.isPresent()) {
            bindingResult.reject(
                    "login.invalid",
                    "Username or password is incorrect.");
            return "login";
        }

        // Starts a fresh session after successful credential checking.
        HttpSession previousSession = request.getSession(false);
        if (previousSession != null) {
            previousSession.invalidate();
        }

        request.getSession(true).setAttribute("currentUser", user.get());
        return "redirect:/inventory";
    }

    /**
     * Displays the initial inventory landing page.
     *
     * @param model the data supplied to the view
     * @return the inventory template
     */
    @GetMapping("/inventory")
    public String displayInventory(Model model) {
        model.addAttribute("title", "Inventory");
        return "inventory";
    }

    /**
     * Ends the simulated login session.
     *
     * @param request the current HTTP request
     * @param redirectAttributes the logout confirmation message
     * @return a redirect to the login page
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
                "successMessage",
                "You have been logged out.");

        return "redirect:/login";
    }
}