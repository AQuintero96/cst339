# Milestone 6: Analysis and Planning

[Overview](README.md) | [Design](design.md) | [Test Plan](testPlan.md) | [Results](test.md)

## Objective

Replace manual session-based authentication with Spring Security while preserving the inventory application's existing accounts, registration, persistence, and CRUD workflows.

The milestone-specific access requirement protects every application page except the login and registration modules. Supporting static resources remain available so those public pages render correctly.

## Work Plan

Alex Quintero completed the implementation individually and reported 10 hours. Time was recorded as a total rather than separate estimates for each task.

| Sequence | Task | Outcome |
| --- | --- | --- |
| 1 | Review the existing authentication and inventory code | Identified manual session checks and the existing PBKDF2 password format |
| 2 | Add security dependencies | Added Spring Security, Thymeleaf security extras, and security test support |
| 3 | Implement database authentication | Connected UserDetailsService to the existing account DAO |
| 4 | Preserve password compatibility | Added a PasswordEncoder matching existing stored credentials |
| 5 | Refactor account registration | Reused the shared encoder for new accounts |
| 6 | Refactor controllers | Removed manual login, logout, and currentUser session checks |
| 7 | Update templates | Added security-based navigation, greetings, and login messages |
| 8 | Verify authentication and protected routes | Tested existing accounts, new registration, invalid login, logout, and direct URLs |
| 9 | Repeat inventory regression tests | Tested details, update, validation, cancellation, creation, deletion, and missing records |
| 10 | Verify persistence and packaging | Restarted the application, built the JAR, and tested it outside Eclipse |
| 11 | Record the demonstration | Completed the technical and functional screencast |
| 12 | Assemble the report | Documented the design, test plan, evidence, and limitations |

## General Technical Approach

The application retains its N-Layer structure.

Spring MVC controllers and Thymeleaf templates form the presentation layer. Controllers coordinate form validation, business-service calls, and view selection.

Business services manage registration and inventory operations. Persistence services use Spring JDBC and parameterized SQL to access MySQL.

Spring Security filters requests before controller execution. The security configuration permits login, registration, and supporting static resources while requiring authentication for the remaining application routes.

InventoryUserDetailsService retrieves an account through AccountDataAccessInterface. DaoAuthenticationProvider verifies the submitted password using InventoryPasswordEncoder and establishes the authenticated security context.

InventoryUserDetails retains the account identifier and first name so templates can display the existing personalized greeting.

## Key Technical Decisions

| Decision | Reason |
| --- | --- |
| Continue the existing milestone application | Preserves the established inventory features and layered architecture |
| Retain MySQL and Spring JDBC | Reuses the existing account and inventory persistence |
| Use database-backed form login | Meets the milestone's authentication requirements |
| Preserve the PBKDF2 credential format | Allows existing users to authenticate without resetting passwords |
| Share one password encoder | Keeps registration and authentication compatible |
| Use Spring Security's authenticated principal | Replaces the separate currentUser session attribute |
| Protect routes centrally | Applies consistent authentication requirements to existing and future application routes |
| Protect the home page | Follows the milestone-specific requirement excluding only login and registration modules |
| Keep CSRF protection enabled | Requires valid tokens for state-changing form requests |
| Use POST for logout and deletion | Prevents ordinary navigation from performing these operations |
| Change the session ID after login | Retains session fixation protection |
| Keep constructor injection | Makes service dependencies explicit |
| Preserve the shared inventory | All authenticated users continue to work with the same parts |

## Password Handling

The stored password format includes the algorithm name, iteration count, random salt, and derived hash.

The implementation uses PBKDF2WithHmacSHA256 with 600,000 iterations, a 16-byte random salt, and a 256-bit derived hash. Salt and hash values are encoded with Base64.

The encoder validates the stored format and compares derived hashes using MessageDigest.isEqual. Temporary password characters and derived hash arrays are cleared after use.

Existing account login and new-account login were both demonstrated after the refactor.

## Theme and Layout

The application retains the navy navigation, teal action buttons, light page background, and shared footer.

Anonymous visitors see login and registration links. Authenticated users see Home, Inventory, Add Part, their first name, and a logout button.

The login page displays authentication failures and logout confirmations. Inventory greetings read the first name from the Spring Security principal.

## Review Notes

Source inspection checked the removal of the old currentUser, SessionUser, and LoginModel workflow. It also examined route protection, account lookup, password compatibility, form actions, and the preservation of inventory error handling.

The implementation retains parameterized SQL, restricted form binding, service-layer validation, and explicit missing-record handling.

This is an individual project. No separate human peer review is claimed.

## Troubleshooting

The executable JAR initially displayed a generic login failure. The application required DB_PASSWORD in the PowerShell process used to launch it; the Eclipse run configuration did not supply that variable to the separate terminal.

After the database password was supplied in PowerShell and the JAR was restarted, authentication and inventory retrieval succeeded.

The setup instructions explicitly configure DB_PASSWORD before building and launching from PowerShell.

## Known Issues and Risks

| Item | Current behavior or limitation |
| --- | --- |
| Concurrent edits | Updates do not compare a previously read version; the last successful save wins |
| Version column | Incremented during updates but not used for optimistic locking |
| Shared inventory | Authenticated accounts have the same inventory access; ownership and administrative roles are not implemented |
| Login error messages | A generic failure message does not distinguish incorrect credentials from authentication infrastructure failures |
| CSRF verification | Protection is configured and normal form submissions succeeded; dedicated missing-token and invalid-token tests were not executed |
| Database availability | Authentication and inventory operations depend on MySQL; a controlled outage/recovery test was not performed |
| Local HTTP | The demonstration uses localhost HTTP; a deployed application would require HTTPS and deployment-specific configuration |
| MySQL 5.7.24 | CHECK constraints are not enforced; Java validation remains important |
| CDN availability | Bootstrap resources require network access unless cached |
| Automated coverage | The Maven build ran one application-context test; functional results come from manual execution |
| Abuse prevention | Login throttling, account lockout, and multifactor authentication are outside this milestone |

## Next Milestone

Milestone 7 will add secured REST endpoints for retrieving all parts and an individual part, together with API documentation and corresponding tests.