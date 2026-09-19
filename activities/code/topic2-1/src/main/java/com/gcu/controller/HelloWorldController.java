package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Demonstrates different ways to return text and display views with Spring MVC.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    /**
     * Returns plain text directly to the browser.
     *
     * @return the Hello World greeting
     */
    @GetMapping("/test1")
    @ResponseBody
    public String printHello() {
        return "Hello World!";
    }

    /**
     * Passes a greeting to the Hello view through the model.
     *
     * @param model holds the data displayed in the view
     * @return the name of the Hello view
     */
    @GetMapping("/test2")
    public String printHello(Model model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    /**
     * Sets the view and both greetings using a ModelAndView object.
     *
     * @return the Hello view with its two messages
     */
    @GetMapping("/test3")
    public ModelAndView printHello1() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(
                "message", "Hello World from ModelAndView!");
        modelAndView.addObject(
                "message2", "Another Hello World from ModelAndView!");
        modelAndView.setViewName("hello");

        return modelAndView;
    }

    /**
     * Receives a message from the URL and passes it to the Hello view.
     *
     * @param message the message supplied in the request parameter
     * @param model holds the data displayed in the view
     * @return the name of the Hello view
     */
    @GetMapping("/test4")
    public String printHello(
            @RequestParam("message") String message, Model model) {
        model.addAttribute("message", message);
        return "hello";
    }
}