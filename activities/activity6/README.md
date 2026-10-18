# Activity 6: Securing a Web Application and REST APIs

**Author:** Alex Quintero  
**Course:** CST-339  
**Date:** October 17, 2026

## Overview

This activity demonstrates four approaches to application security: in-memory form login, MongoDB-backed form login, HTTP Basic authentication for REST endpoints, and GitHub OAuth2 login.

The first three projects build on the orders application. The fourth project is a separate application that authenticates through GitHub and displays the authenticated username.

## Report Pages

| Page | Contents |
| --- | --- |
| [Analysis and Planning](analysisPlanning.md) | Objectives, implementation approach, and troubleshooting |
| [Design and Setup](design.md) | Project structure, authentication flows, and execution instructions |
| [Testing and Screenshots](test.md) | Results and screenshots for all four parts |

## Source Code

| Part | Project | Authentication |
| --- | --- | --- |
| 1 | [topic6-1](../code/topic6-1/) | In-memory account with form login |
| 2 | [topic6-2](../code/topic6-2/) | MongoDB account with BCrypt password verification |
| 3 | [topic6-3](../code/topic6-3/) | Protected REST endpoints with HTTP Basic authentication |
| 4 | [topic6-4](../code/topic6-4/) | GitHub OAuth2 login |

## Results

Form login rejected invalid credentials and displayed the orders page after successful authentication. Parts 1 and 2 retained public JSON and XML endpoints.

Part 3 returned HTTP 200 responses with order data for valid credentials and HTTP 401 responses for invalid credentials.

Part 4 redirected to GitHub sign-in and returned to the application with a greeting identifying the authenticated GitHub user.

Parts 1 and 2 were also packaged and run as executable JAR files.

[Return to the repository front page](../../README.md)