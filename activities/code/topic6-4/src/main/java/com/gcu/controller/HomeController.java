package com.gcu.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides a public entry page for the OAuth2 demonstration.
 */
@RestController
public class HomeController {

    /**
     * Displays a link that starts GitHub authentication.
     * @return public home page
     */
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport"
                          content="width=device-width, initial-scale=1.0">
                    <title>CST-339 GitHub Login</title>
                </head>
                <body>
                    <h1>CST-339 GitHub OAuth2 Demonstration</h1>
                    <p>Authenticate with GitHub to access the protected endpoint.</p>
                    <a href="/oauth2/authorization/github">Log in with GitHub</a>
                </body>
                </html>
                """;
    }
}