# Analysis and Planning

[Return to Activity 6](README.md)

## Objective

The objective was to secure browser pages and REST endpoints using Spring Security and compare local account authentication with an external identity provider.

## Implementation Plan

| Part | Approach | Expected Result |
| --- | --- | --- |
| 1 | Add Spring Security and an in-memory test account | Require login for the orders page while retaining public REST responses |
| 2 | Retrieve application accounts from MongoDB | Authenticate against a stored BCrypt password hash |
| 3 | Require authentication for REST endpoints | Return order data for valid credentials and reject invalid credentials |
| 4 | Create a separate GitHub OAuth2 application | Redirect to GitHub and display the authenticated username |

## Implementation Decisions

Parts 1 through 3 use separate project copies so each security configuration can be reviewed independently. Part 4 starts as a new application because its purpose is to demonstrate GitHub authentication without the orders application's database dependencies.

The projects use Java 17 and Spring Boot 2.7.18. SecurityFilterChain configuration and the OAuth2 client starter adapt the guide's older security examples to this project setup.

The MongoDB application account is stored in the users collection. Its password is represented by a BCrypt hash. The Atlas database connection account serves a separate purpose: allowing the application to connect to MongoDB.

MongoDB connection details and GitHub client credentials are supplied through environment variables.

## Troubleshooting

The Part 1 Maven build initially failed because the MongoDB connection string was invalid. Correcting the environment variable allowed the build to complete.

The first Part 3 JSON screenshot showed a successful response while Postman displayed No Auth. That screenshot did not clearly demonstrate HTTP Basic authentication. The test was repeated, and the replacement screenshots show Basic Auth with successful and unsuccessful responses.

Part 4 encountered a Maven resource-copying error with maven-resources-plugin 3.2.0. The project configuration was updated to use version 3.3.1. The application subsequently ran and completed the GitHub login demonstration.

The browser also displayed a connection-refused message when the local application was not running. Starting Topic64Application made the endpoint available.

## Scope and Limitations

This activity demonstrates authentication using a small set of test accounts and requests. It does not establish production readiness or comprehensive authorization coverage.

The test evidence covers invalid login handling, successful orders access, REST responses, executable JAR operation for Parts 1 and 2, and GitHub OAuth2 login. Automated security regression tests and load testing were outside the demonstrated scope.