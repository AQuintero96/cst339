# Welcome to CST-339: Programming in Java III

My name is Alex Quintero, and this repository contains my activities and milestones for CST-339. The coursework builds on my Java experience through web development with Spring Boot, Maven, and Thymeleaf.

Activities and milestones are organized separately. Related assignments build on earlier work as directed by the course requirements. All milestones are completed individually.

## Activities

### Spring Boot Coursework

The activities introduce the tools and Spring features used throughout the course. They cover application setup, web forms, business services, REST endpoints, and relational and document database access.

[View Activity Source Code](activities/code/)

### Activity Progress

| Activity | Focus | Report |
| --- | --- | --- |
| 1 | Eclipse and Java setup, a Hello World application, and Maven builds | [Activity 1](activities/activity1/README.md) |
| 2 | Spring MVC, Thymeleaf views, form validation, and shared layouts | [Activity 2](activities/activity2/README.md) |
| 3 | Business services, dependency injection, bean scopes, and REST endpoints | [Activity 3](activities/activity3/README.md) |
| 4 | MySQL, Spring JDBC, Spring Data JDBC, and custom SQL queries | [Activity 4](activities/activity4/README.md) |
| 5 | MongoDB Atlas, document persistence, JSON/XML responses, and identifier queries | [Activity 5](activities/activity5/README.md) |

### Latest Update: Activity 5

The orders application now uses Spring Data MongoDB to access documents stored in MongoDB Atlas. The application displays saved orders through Thymeleaf and provides JSON and XML responses.

A dedicated verification profile saved an additional order and read it back successfully. A derived repository query retrieves an order by its document identifier, and an absent identifier produces a not-found response.

The Maven build completed with one test, zero failures, and zero errors. The executable JAR ran from PowerShell and displayed all six stored orders.

The report is divided into shorter pages for planning, design, and testing. Screenshots appear as clickable thumbnails.

- [Activity Overview](activities/activity5/README.md)
- [Analysis and Planning](activities/activity5/analysisPlanning.md)
- [Design and Setup](activities/activity5/design.md)
- [Testing and Screenshots](activities/activity5/test.md)
- [Activity 5 Source Code](activities/code/topic5-1/)

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

### Latest Update: Milestone 5

The application now lists saved parts and displays individual component details. Users can edit validated part information and delete records through a confirmation page.

Spring-managed business services use a DAO interface backed by Spring JDBC. The new pages retain the existing Thymeleaf layout and application styling.

Testing covered successful updates, rejected negative values, cancellation, deletion, missing records, logged-out access to the new workflows, and restart persistence.

Maven completed with one application-context test and no failures or errors. The executable JAR started successfully and displayed the saved inventory.

The updated report includes design diagrams, service contracts, a test plan, screenshots, and the video demonstration. Spring Security integration is planned for Milestone 6.

- [Milestone Overview](milestones/milestone5/README.md)
- [Analysis and Planning](milestones/milestone5/analysisPlanning.md)
- [Design and Setup](milestones/milestone5/design.md)
- [Test Plan](milestones/milestone5/testPlan.md)
- [Testing and Screenshots](milestones/milestone5/test.md)
- [Video Demonstration](https://youtu.be/6aJvDrHsrDI)
- [Database Script](milestones/milestone4/database/schema.sql)