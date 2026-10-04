package com.gcu.controller;

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
import com.gcu.business.RegistrationServiceInterface;
import com.gcu.model.UserModel;

/**
 * Handles registration forms through injected business services.
 */
@Controller
public class RegistrationController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationServiceInterface registrationService;
    private final AccountServiceInterface accountService;

    /**
     * Receives the registration and account services.
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
     * @param model the view attributes
     * @return the registration template
     */
    @GetMapping("/register")
    public String displayRegistration(Model model) {
        model.addAttribute("title", "Create an Account");
        model.addAttribute("userModel", new UserModel());
        return "register";
    }

    /**
     * Validates registration and requests account creation.
     *
     * @param userModel the submitted registration
     * @param bindingResult the validation results
     * @param model the view attributes
     * @param redirectAttributes the success message
     * @return the form or a redirect to login
     */
    @PostMapping("/register")
    public String processRegistration(
            @Valid @ModelAttribute("userModel") UserModel userModel,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("title", "Create an Account");

        // Checks matching passwords after both fields pass validation.
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

        try {
            if (!accountService.register(userModel)) {
                bindingResult.rejectValue(
                        "username",
                        "username.duplicate",
                        "That username is already registered. Choose another.");
                return "register";
            }
        } catch (DataAccessException exception) {
            // Avoids logging submitted values or encoded credentials.
            LOGGER.error("Database operation failed during registration: {}",
                    exception.getClass().getSimpleName());

            bindingResult.reject(
                    "registration.unavailable",
                    "Registration is temporarily unavailable. Please try again.");
            return "register";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Account created successfully. Please log in.");

        return "redirect:/login";
    }
}