package com.gcu.business;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demonstrates a protected endpoint using the authenticated GitHub identity.
 */
@RestController
@RequestMapping("/service")
public class MyService {

    /**
     * Greets the identity established by OAuth2 login.
     * @param principal authenticated user
     * @return personalized greeting
     */
    @GetMapping(value = "/test", produces = MediaType.TEXT_PLAIN_VALUE)
    public String test(Principal principal) {
        return "Hello, " + principal.getName()
                + "! You authenticated successfully with GitHub.";
    }
}