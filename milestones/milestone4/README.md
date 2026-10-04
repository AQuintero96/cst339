# Milestone 4: Database Persistence

**Project:** IT Parts Inventory Manager  
**Author:** Alex Quintero  
**Course:** CST-339  
**Date:** October 4, 2026  
**Time spent:** Approximately 11 hours  
**Development:** Individual project

[Watch the Milestone 4 demonstration](https://youtu.be/aHwgYQovq8A)

## Overview

This milestone connects registration, login, and part creation to MySQL using Spring JDBC and data access objects.

Registered accounts and created parts now remain available after the application restarts. The existing pages retain their shared Thymeleaf layout, validation, and navy and teal theme.

## Work Completed

- Created the inventory database and its account and part tables.
- Added DAO interfaces and Spring-managed JDBC data services.
- Replaced in-memory account storage with database registration and login.
- Preserved salted password hashing.
- Replaced temporary part IDs with database-generated IDs.
- Added transaction handling to part creation.
- Added controller handling for database access failures.
- Verified account and part persistence after restarting the application.
- Checked duplicate usernames, invalid credentials, and invalid part values.
- Built and ran the executable JAR outside Eclipse.
- Recorded the technical and functional demonstration.

## Report Pages

| Page | Contents |
| --- | --- |
| [Analysis and Planning](analysisPlanning.md) | Work plan, technical approach, decisions, theme, and risks |
| [Design and Setup](design.md) | Configuration, sitemap, wireframes, database design, and class diagrams |
| [Testing and Screenshots](test.md) | Test results and screenshot evidence |

## Results

Registration saved an account in MySQL, and that account could log in after an application restart. A created part received a database ID and remained stored after restart.

Duplicate usernames and incorrect passwords were rejected. Negative quantity and cost values displayed validation errors, and the part count remained unchanged.

The Java 17 Maven build completed with one test, zero failures, and zero errors. The packaged application started from PowerShell and successfully authenticated the saved account.

## Current Scope

This milestone implements persistent registration, login, and part creation. Browsing, editing, and deleting saved parts are planned for Milestone 5. Spring Security integration is planned for Milestone 6.

The installed MySQL version is 5.7.24, which does not enforce CHECK constraints. Form values are checked through Java validation.

## Project Links

- [Video Demonstration](https://youtu.be/aHwgYQovq8A)
- [Application Source](../code/it-parts-inventory/)
- [Database Script](database/schema.sql)
- [Milestone 3 Report](../milestone3/README.md)
- [Repository Front Page](../../README.md)