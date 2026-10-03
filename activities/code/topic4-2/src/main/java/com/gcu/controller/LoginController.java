package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;

/**
 * Handles login requests and obtains orders from the business service.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    // Receives the orders implementation selected in SpringConfig.
    @Autowired
    private OrdersBusinessServiceInterface service;

    // Receives the security demonstration service.
    @Autowired
    private SecurityBusinessService security;

    /**
     * Displays an empty login form.
     *
     * @param model the data supplied to the view
     * @return the login template
     */
    @GetMapping({"", "/"})
    public String display(Model model) {
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    /**
     * Validates the form and requests orders from the injected service.
     *
     * @param loginModel the submitted form values
     * @param bindingResult the binding and validation results
     * @param model the data supplied to the view
     * @return the login template when invalid, or the orders template
     */
    @PostMapping("/doLogin")
    public String doLogin(
            @Valid @ModelAttribute("loginModel") LoginModel loginModel,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        boolean authenticated = security.authenticate(
                loginModel.getUsername(),
                loginModel.getPassword());

        if (!authenticated) {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        service.test();

        // The controller passes service data to the view.
        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", service.getOrders());

        return "orders";
    }
}