# Welcome to CST-339: Programming in Java III

My name is Alex Quintero, and this repository contains my activities and milestones for CST-339. The coursework builds on my Java experience through web development with Spring Boot, Maven, and Thymeleaf.

Activities and milestones are organized separately. Related assignments build on earlier work as directed by the course requirements. All milestones are completed individually.

## Activities

### Spring Boot Coursework

The activities introduce the tools and Spring features used throughout the course. They cover application setup, web forms, business services, REST endpoints, relational and document database access, and authentication.

[View Activity Source Code](activities/code/)

### Activity Progress

| Activity | Focus | Report |
| --- | --- | --- |
| 1 | Eclipse and Java setup, a Hello World application, and Maven builds | [Activity 1](activities/activity1/README.md) |
| 2 | Spring MVC, Thymeleaf views, form validation, and shared layouts | [Activity 2](activities/activity2/README.md) |
| 3 | Business services, dependency injection, bean scopes, and REST endpoints | [Activity 3](activities/activity3/README.md) |
| 4 | MySQL, Spring JDBC, Spring Data JDBC, and custom SQL queries | [Activity 4](activities/activity4/README.md) |
| 5 | MongoDB Atlas, document persistence, JSON/XML responses, and identifier queries | [Activity 5](activities/activity5/README.md) |
| 6 | Form login, MongoDB accounts, HTTP Basic authentication, and GitHub OAuth2 | [Activity 6](activities/activity6/README.md) |

### Latest Update: Activity 6

Four separate projects demonstrate application security with Spring Security.

Part 1 uses an in-memory account for form login. Part 2 retrieves application accounts from MongoDB and verifies BCrypt password hashes. Both projects display orders after login and retain public JSON and XML endpoints.

Part 3 protects the REST endpoints using HTTP Basic authentication. Postman tests show HTTP 200 responses for valid credentials and HTTP 401 responses for invalid credentials.

Part 4 uses GitHub OAuth2 login in a separate application. After GitHub authentication, the application displays a greeting with the authenticated username.

Parts 1 and 2 were also packaged and run as executable JAR files. The report includes 22 screenshots documenting the four parts.

- [Activity Overview](activities/activity6/README.md)
- [Analysis and Planning](activities/activity6/analysisPlanning.md)
- [Design and Setup](activities/activity6/design.md)
- [Testing and Screenshots](activities/activity6/test.md)
- [Part 1 Source Code](activities/code/topic6-1/)
- [Part 2 Source Code](activities/code/topic6-2/)
- [Part 3 Source Code](activities/code/topic6-3/)
- [Part 4 Source Code](activities/code/topic6-4/)

## Milestones

### IT Parts Inventory Manager

The course project is a computer parts inventory application based on my work in IT. It tracks components, quantities, unit costs, and storage locations. Each milestone builds on the same application.

[View Application Source Code](milestones/code/it-parts-inventory/)

### Project Progress

| Milestone | Focus | Report |
| --- | --- | --- |
| 1 | Project proposal, planned features, solo work plan, risks, and initial design | [Milestone 1](milestones/milestone1/README.md) |
| 2 | Home page, registration, validation, simulated login, and responsive layouts | [Milestone 2](milestones/milestone2/README.md) |
| 3 | Service interfaces, dependency injection, validated part creation, and updated design | [Milestone 3](milestones/milestone3/README.md) |
| 4 | MySQL persistence for registration, login, and part creation using Spring JDBC | [Milestone 4](milestones/milestone4/README.md) |
| 5 | Inventory display, part details, validated updates, deletion, and test documentation | [Milestone 5](milestones/milestone5/README.md) |
| 6 | Spring Security, database-backed authentication, protected pages, and regression testing | [Milestone 6](milestones/milestone6/README.md) |

### Latest Update: Milestone 6

The application now uses Spring Security for database-backed form authentication, logout, and centralized access control.

Login and registration remain public. All other application pages, including Home and Add Part, require authentication. Unauthenticated requests to protected pages redirect to login.

The password encoder preserves the existing PBKDF2 credential format. Existing accounts and newly registered accounts both authenticated successfully after the refactor.

Navigation and greetings now use the Spring Security principal. Manual currentUser session checks and the obsolete login/session models were removed. CSRF protection remains enabled for form submissions.

Testing covered authentication, protected routes, registration, logout, and all 20 adapted Milestone 5 regression cases. Inventory changes persisted after restart.

Maven completed with one application-context test, zero failures, and zero errors. The executable JAR started and displayed the saved inventory after its database environment variable was configured.

The report includes updated diagrams, service contracts, configuration instructions, a test plan, 19 screenshots, and the technical and functional demonstration. Total reported effort was 10 hours.

- [Milestone Overview](milestones/milestone6/README.md)
- [Analysis and Planning](milestones/milestone6/analysisPlanning.md)
- [Design and Setup](milestones/milestone6/design.md)
- [Test Plan](milestones/milestone6/testPlan.md)
- [Testing and Screenshots](milestones/milestone6/test.md)
- [Video Demonstration](https://youtu.be/QZNVNUzUlr8)
- [Database Script](milestones/milestone4/database/schema.sql)