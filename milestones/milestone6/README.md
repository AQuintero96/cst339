# Milestone 6: Spring Security

## IT Parts Inventory Manager

**Author:** Alex Quintero  
**Course:** CST-339: Programming in Java III  
**Completed:** October 18, 2026  
**Time Spent:** 10 hours  
**Project Format:** Individual implementation

## Overview

This milestone refactors the inventory application's login module to use Spring Security with MySQL-backed authentication.

The login and registration modules remain public. All other application pages, including the home page and inventory creation routes, require authentication. Unauthenticated requests to protected pages redirect to login.

The application retains its existing inventory features, database, and visual design. Existing accounts remain compatible because the password encoder preserves the stored PBKDF2 credential format.

## Completed Tasks

- Added Spring Security and Thymeleaf security integration.
- Connected authentication to the existing account DAO.
- Implemented a PasswordEncoder compatible with existing PBKDF2 hashes.
- Updated registration to use the shared password encoder.
- Replaced manual login and logout processing with Spring Security.
- Removed the obsolete LoginModel and SessionUser classes.
- Removed controller checks for the old currentUser session attribute.
- Protected the home page and all inventory workflows.
- Updated navigation and greetings to use the authenticated principal.
- Retained CSRF protection and session ID changes after authentication.
- Verified existing-account and new-account login.
- Repeated the Milestone 5 functional tests using the current inventory values.
- Verified restart persistence, Maven packaging, and executable JAR operation.
- Recorded the technical and functional demonstration.

## Report Pages

| Page | Contents |
| --- | --- |
| [Analysis and Planning](analysisPlanning.md) | Work sequence, technical approach, decisions, review notes, and risks |
| [Design and Setup](design.md) | Configuration, sitemap, UI diagrams, ER diagram, class diagrams, and service contracts |
| [Test Plan](testPlan.md) | Procedures, expected results, and execution status |
| [Testing and Screenshots](test.md) | Observed results and clickable screenshot evidence |

## Source Code and Database

- [Application Source Code](../code/it-parts-inventory/)
- [Database DDL Script](../milestone4/database/schema.sql)

Milestone 6 continues the existing application. No database schema changes were required.

## Demonstration

[Watch the Milestone 6 technical and functional demonstration](https://youtu.be/QZNVNUzUlr8)

## Results

Existing and newly registered accounts authenticated successfully. Invalid credentials displayed a login error. Logout returned the application to its anonymous navigation, and direct requests to protected pages redirected to login.

Inventory testing confirmed details, editing, validation, cancellation, creation, deletion, and missing-record handling. RAM quantity changed from 6 to 7. The disposable test record, ID 3, was deleted while the RAM and SSD records remained.

After restarting, the application retained RAM quantity 7 and SSD quantity 3, with the deleted record absent.

Maven reported BUILD SUCCESS with one application-context test, zero failures, and zero errors. The executable JAR started and served the authenticated inventory after DB_PASSWORD was configured in PowerShell.

## Submission

The report and application source are maintained in GitHub. Submission follows the instructor-approved repository-link workflow.

[Return to the repository front page](../../README.md)