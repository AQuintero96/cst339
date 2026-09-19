package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

/**
 * Displays the login form, validates input, and provides sample orders.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Opens the login page with an empty form.
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
     * Checks the submitted values before displaying sample orders.
     *
     * @param loginModel the submitted form values
     * @param bindingResult the form binding and validation results
     * @param model the data supplied to the view
     * @return the login template when invalid, or the orders template
     */
    @PostMapping("/doLogin")
    public String doLogin(
            @Valid @ModelAttribute("loginModel") LoginModel loginModel,
            BindingResult bindingResult,
            Model model) {

        // Returns to the form when either field fails validation.
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        // Supplies sample data for the orders table.
        List<OrderModel> orders = new ArrayList<>();
        orders.add(new OrderModel(1L, "1001", "Notebook", 4.99f, 3));
        orders.add(new OrderModel(2L, "1002", "Pen Set", 7.50f, 2));
        orders.add(new OrderModel(3L, "1003", "Desk Organizer", 15.99f, 1));
        orders.add(new OrderModel(4L, "1004", "Folder", 2.25f, 5));

        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", orders);
        return "orders";
    }
}