package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.AccountServiceInterface;
import com.gcu.business.RegistrationServiceInterface;
import com.gcu.model.UserModel;

/**
 * Handles registration requests using injected service interfaces.
 */
@Controller
public class RegistrationController {

    private final RegistrationServiceInterface registrationService;
    private final AccountServiceInterface accountService;

    /**
     * Supplies the registration services through constructor injection.
     *
     * @param registrationService the password confirmation service
     * @param accountService the account registration service
     */
    public RegistrationController(
            RegistrationServiceInterface registrationService,
            AccountServiceInterface accountService) {

        this.registrationService = registrationService;
        this.accountService = accountService;
    }

    /**
     * Displays an empty registration form.
     *
     * @param model the data supplied to the view
     * @return the registration template
     */
    @GetMapping("/register")
    public String displayRegistration(Model model) {
        model.addAttribute("title", "Create an Account");
        model.addAttribute("userModel", new UserModel());
        return "register";
    }

    /**
     * Validates the form and requests account creation.
     *
     * @param userModel the submitted registration values
     * @param bindingResult the binding and validation results
     * @param model the data supplied to the view
     * @param redirectAttributes messages carried across the redirect
     * @return the registration form or a redirect to login
     */
    @PostMapping("/register")
    public String processRegistration(
            @Valid @ModelAttribute("userModel") UserModel userModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("title", "Create an Account");

        // Checks confirmation after both password fields pass validation.
        if (!bindingResult.hasFieldErrors("password")
                && !bindingResult.hasFieldErrors("confirmPassword")
                && !registrationService.passwordsMatch(
                        userModel.getPassword(),
                        userModel.getConfirmPassword())) {

            bindingResult.rejectValue(
                    "confirmPassword",
                    "password.mismatch",
                    "Passwords must match.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        // The service handles account creation and duplicate detection.
        if (!accountService.register(userModel)) {
            bindingResult.rejectValue(
                    "username",
                    "username.duplicate",
                    "That username is already registered. Choose another.");
            return "register";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Account created successfully. Please log in.");

        return "redirect:/login";
    }
}