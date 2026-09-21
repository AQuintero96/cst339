package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.business.AccountService;
import com.gcu.business.RegistrationService;
import com.gcu.model.UserModel;

/**
 * Displays the registration form and coordinates temporary account creation.
 */
@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AccountService accountService;

    /**
     * Supplies the services used for registration.
     *
     * @param registrationService the password confirmation service
     * @param accountService the temporary account service
     */
    public RegistrationController(
            RegistrationService registrationService,
            AccountService accountService) {

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
     * Validates registration details and creates a temporary account.
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

        // Leaves account creation and duplicate checking in the service.
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

        // Redirecting prevents refresh from repeating the registration request.
        return "redirect:/login";
    }
}