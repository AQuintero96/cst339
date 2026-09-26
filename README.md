# Welcome to CST-339: Programming in Java III

My name is Alex Quintero, and this repository contains my activities and milestones for CST-339. The coursework builds on my Java experience through web development with Spring Boot, Maven, and Thymeleaf.

Activities and milestones are organized separately. Related assignments build on earlier work as directed by the course requirements. All milestones are completed individually.

## Activities

### Spring Boot Coursework

The activities introduce the tools and Spring features used throughout the course. They cover application setup, web forms, business services, and REST endpoints. Each report includes screenshots and explanations of the work.

[View Activity Source Code](activities/code/)

### Activity Progress

| Activity | Focus | Report |
| --- | --- | --- |
| 1 | Eclipse and Java setup, a Hello World application, and Maven builds | [Activity 1](activities/activity1/README.md) |
| 2 | Spring MVC, Thymeleaf views, form validation, and shared layouts | [Activity 2](activities/activity2/README.md) |
| 3 | Business services, dependency injection, bean scopes, and REST endpoints | [Activity 3](activities/activity3/README.md) |

### Latest Update: Activity 3

The login and orders application now gets its order data from a business service. Spring dependency injection connects the services to the controller.

Console tests compared prototype, request, session, and singleton scopes. REST endpoints were also added to return orders as JSON and XML. Both endpoints were checked in the browser and Postman.

- [Report and Screenshots](activities/activity3/README.md)
- [REST API Design](activities/activity3/rest-api-design.md)
- [Part 1 Source Code](activities/code/topic3-1/)
- [Parts 2 and 3 Source Code](activities/code/topic3-2/)

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

### Latest Update: Milestone 3

Users can now open Add Part from the navigation or inventory page, enter component information, and review an accepted submission on a confirmation page. The form checks required fields, supported categories, whole-number quantities, and costs with up to two decimal places.

Registration and login now use service interfaces with constructor injection. The new pages use the same shared Thymeleaf layout and navy and teal theme as the rest of the application.

Testing covered registration, login, part validation, navigation, and phone and tablet layouts through DevTools emulation. The application also passed a Maven build using Java 17.

Accounts remain in memory, and part submissions are not saved. Database persistence is planned for Milestone 4.

- [Design Report and Screenshots](milestones/milestone3/README.md)
- [Video Demonstration](https://youtu.be/jWA0RCb9LVA)
- [Draft Database Script](milestones/milestone3/database/schema-draft.sql)