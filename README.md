# Welcome to CST-339: Programming in Java III

My name is Alex Quintero, and this repository contains my activities and milestones for CST-339. The coursework builds on my Java experience through web development with Spring Boot, Maven, and Thymeleaf.

Activities and milestones are organized separately. Related assignments build on earlier work as directed by the course requirements. All milestones are completed individually.

## Activities

### Spring Boot Coursework

The activities introduce the tools and Spring features used throughout the course. They cover application setup, web forms, business services, REST endpoints, and database access.

[View Activity Source Code](activities/code/)

### Activity Progress

| Activity | Focus | Report |
| --- | --- | --- |
| 1 | Eclipse and Java setup, a Hello World application, and Maven builds | [Activity 1](activities/activity1/README.md) |
| 2 | Spring MVC, Thymeleaf views, form validation, and shared layouts | [Activity 2](activities/activity2/README.md) |
| 3 | Business services, dependency injection, bean scopes, and REST endpoints | [Activity 3](activities/activity3/README.md) |
| 4 | MySQL, Spring JDBC, Spring Data JDBC, and custom SQL queries | [Activity 4](activities/activity4/README.md) |

### Latest Update: Activity 4

The orders application now reads records from MySQL. Three separate projects demonstrate JdbcTemplate, a Spring Data JDBC repository, and custom SQL queries.

Each version displayed the activity's database orders. The final project passed a Maven build using Java 17, and its executable JAR displayed all 11 records when run from PowerShell.

The report is divided into shorter pages for planning, design, and testing. Screenshots appear as clickable thumbnails.

- [Activity Overview](activities/activity4/README.md)
- [Analysis and Planning](activities/activity4/analysisPlanning.md)
- [Design and Setup](activities/activity4/design.md)
- [Testing and Screenshots](activities/activity4/test.md)
- [Part 1 Source Code](activities/code/topic4-1/)
- [Part 2 Source Code](activities/code/topic4-2/)
- [Part 3 Source Code](activities/code/topic4-3/)

## Milestones

### IT Parts Inventory Manager

The course project is a computer parts inventory application based on my work in IT. The finished application will track spare components, quantities, unit costs, and storage locations. Each milestone builds on the same application.

[View Application Source Code](milestones/code/it-parts-inventory/)

### Project Progress

| Milestone | Focus | Report |
| --- | --- | --- |
| 1 | Project proposal, planned features, solo work plan, risks, and initial design | [Milestone 1](milestones/milestone1/README.md) |
| 2 | Home page, registration, validation, simulated login, and responsive layouts | [Milestone 2](milestones/milestone2/README.md) |
| 3 | Service interfaces, dependency injection, validated part creation, and updated design | [Milestone 3](milestones/milestone3/README.md) |
| 4 | MySQL persistence for registration, login, and part creation using Spring JDBC | [Milestone 4](milestones/milestone4/README.md) |

### Latest Update: Milestone 4

Accounts and parts are now saved in MySQL. Registration stores account information with salted password hashes, login checks the stored credentials, and part creation returns a database-generated ID.

Business services access the database through DAO interfaces and Spring JDBC implementations. The application retains its shared Thymeleaf layout and existing form validation.

Testing confirmed that accounts and parts remain after an application restart. Duplicate usernames, incorrect passwords, and negative part values were rejected. The application also passed a Java 17 Maven build and ran successfully as an executable JAR outside Eclipse.

The report includes planning, design diagrams, database setup instructions, and screenshot evidence in separate pages. Browsing, editing, and deleting saved parts are planned for Milestone 5.

- [Milestone Overview](milestones/milestone4/README.md)
- [Analysis and Planning](milestones/milestone4/analysisPlanning.md)
- [Design and Setup](milestones/milestone4/design.md)
- [Testing and Screenshots](milestones/milestone4/test.md)
- [Video Demonstration](https://youtu.be/aHwgYQovq8A)
- [Database Script](milestones/milestone4/database/schema.sql)