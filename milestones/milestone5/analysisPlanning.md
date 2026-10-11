# Milestone 5: Analysis and Planning

[Overview](README.md) | [Design](design.md) | [Test Plan](testPlan.md) | [Results](test.md)

## Objective

Complete the inventory application's display, update, and delete modules while preserving the layered architecture and MySQL persistence introduced in Milestone 4.

## Work Plan

Alex Quintero completed the milestone individually and reported 8 hours. Time was reported as a total rather than a separate duration for each task.

| Sequence | Task | Status |
| --- | --- | --- |
| 1 | Review the existing application and database schema | Complete |
| 2 | Extend the DAO and business-service interfaces | Complete |
| 3 | Implement inventory retrieval and individual details | Complete |
| 4 | Implement validated editing | Complete |
| 5 | Implement delete confirmation and cancellation | Complete |
| 6 | Check missing records and login-session redirects | Complete |
| 7 | Execute functional and persistence tests | Complete |
| 8 | Build and run the executable JAR | Complete |
| 9 | Record the demonstration | Complete |
| 10 | Assemble the updated report and test documentation | Complete |

## General Technical Approach

The presentation layer uses Spring MVC controllers and Thymeleaf templates. Controllers bind form values, display validation feedback, and select views or redirects.

PartService implements the business interface. It validates submitted part values, normalizes text, and delegates persistence to PartDataAccessInterface.

PartDataService implements the DAO using JdbcTemplate and parameterized SQL. It returns stored parts, generated identifiers, and update/delete outcomes.

## Key Technical Decisions

| Decision | Reason |
| --- | --- |
| Retain MySQL and Spring JDBC | Builds directly on the milestone application's established persistence |
| Extend existing service interfaces | Keeps persistence details out of controllers |
| Use constructor injection | Makes required service dependencies explicit |
| Keep PartModel validation | Applies consistent field rules to creation and editing |
| Validate again in PartService | Protects persistence when the business service is called outside MVC |
| Bind only editable form fields | Prevents posted fields from changing the system-managed identifier |
| Use the route identifier for updates | Selects the target independently of editable form data |
| Delete through POST | Viewing the confirmation page does not modify data |
| Redirect after successful changes | Refreshing the destination page does not repeat the form submission |
| Return an Optional for a missing part | Handles nonexistent and previously deleted identifiers explicitly |
| Preserve the shared inventory | Parts remain accessible to authenticated users without individual ownership |

## Theme and Layout

The application retains its navy navigation, teal action buttons, light background, and shared footer. New templates decorate the existing defaultTemplate layout.

The inventory table uses a responsive container. Forms use Bootstrap columns, and action groups can wrap onto additional lines.

## Review Notes

The implementation was examined for separation of responsibilities, parameterized SQL, validation, affected-row checks, missing-record handling, and error messages that avoid exposing database connection details.

This is an individual project. No separate human peer review is claimed in this report.

## Known Limitations and Risks

| Item | Current behavior or limitation |
| --- | --- |
| Concurrent edits | Updates use the part ID without comparing a previously read version; the last successful save wins |
| Version column | Incremented on update, but not used for optimistic locking |
| Security scope | Inventory, details, edit, and delete handlers check the session; comprehensive Spring Security integration remains Milestone 6 work |
| Existing creation routes | The earlier creation controller does not yet enforce the same login-session checks |
| CSRF protection | A POST confirmation prevents accidental deletion by navigation, but does not provide CSRF protection |
| Shared data | Authenticated users operate on the same inventory; there are no ownership or role restrictions |
| Database outage | Error handling is implemented but outage scenarios were not exercised |
| MySQL 5.7.24 | CHECK constraints are not enforced; Java validation remains important |
| CDN availability | Bootstrap resources require network access unless cached |
| Automated coverage | The Maven build runs one application-context test; functional coverage comes from manual testing |

## Next Milestone

Milestone 6 will introduce Spring Security form authentication and consistent protection for application pages and state-changing requests. REST API implementation is scheduled for Milestone 7.