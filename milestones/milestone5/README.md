# Milestone 5: Product Display, Update, and Delete

**Project:** IT Parts Inventory Manager  
**Author:** Alex Quintero  
**Course:** CST-339  
**Date:** October 11, 2026  
**Reported Time:** 8 hours  
**Development:** Individual project

## Overview

Milestone 5 extends the existing MySQL application with an inventory table, individual part details, editing, and deletion. Spring MVC controllers use injected business services, and the business services access MySQL through a Spring JDBC DAO.

The application retains its shared Thymeleaf layout, Bootstrap styling, registration, login, and part creation.

## Tasks Completed

- Displayed saved parts in an inventory table.
- Added individual part-details pages.
- Added prefilled edit forms with server-side validation.
- Added delete confirmation and cancellation.
- Extended business and DAO interfaces with retrieval, update, and delete operations.
- Handled missing records and database exceptions.
- Checked login sessions on inventory, details, edit, and delete routes.
- Executed functional tests, cancellation tests, access checks, and restart verification.
- Built and ran the executable JAR.
- Recorded a technical and functional demonstration.
- Updated planning, design, service documentation, and test evidence.

## Report Pages

| Page | Contents |
| --- | --- |
| [Analysis and Planning](analysisPlanning.md) | Approach, task tracking, decisions, review notes, and risks |
| [Design and Setup](design.md) | Configuration, sitemap, UI diagrams, ER diagram, class diagrams, and service API |
| [Test Plan](testPlan.md) | Test procedures, expected outcomes, and execution status |
| [Testing and Screenshots](test.md) | Observed results and screenshot evidence |

## Deliverables

- [Video Demonstration](https://youtu.be/6aJvDrHsrDI)
- [Application Source Code](../code/it-parts-inventory/)
- [Database DDL](../milestone4/database/schema.sql)

The existing database schema is reused without modification.

## Results

The RAM quantity was updated from 3 to 5. Negative edit values were rejected. A separate test part was deleted while the RAM and SSD records remained.

Cancellation, missing-record handling, and logged-out access checks passed. The updated inventory remained after restarting the application.

Maven completed with one test, zero failures, and zero errors. The executable JAR started successfully and displayed the saved inventory.

[Return to the repository front page](../../README.md)