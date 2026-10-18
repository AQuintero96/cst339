# Milestone 6: Test Plan

[Overview](README.md) | [Planning](analysisPlanning.md) | [Design](design.md) | [Results](test.md)

## Purpose

Verify Spring Security authentication, public registration, protected-page redirects, logout, compatibility with existing accounts, and registration of new accounts.

Repeat all 20 Milestone 5 test cases against the refactored application, adapting record values to the current database state.

## Environment and Preconditions

- Java 17 and Spring Boot 2.7.18.
- MySQL 5.7.24 running through MAMP.
- The it_parts_inventory database and application database account.
- DB_PASSWORD configured in the environment launching the application.
- An existing registered account.
- RAM record ID 1 and SSD record ID 2.
- Port 8080 available.
- A browser session suitable for testing logged-out access.

The RAM record began this milestone with quantity 6. Testing changed it to 7. Its unit cost remained 49.99.

SSD quantity remained 3. A disposable part named Milestone 6 Delete Test received ID 3 and was deleted during testing.

## Execution and Evidence

Alex Quintero executed the manual tests on October 18, 2026.

Results are based on the screenshots linked in [Testing and Screenshots](test.md) and tester confirmation of multi-step behavior. These confirmations include authenticated home access, rejected edits not being saved, edit cancellation, delete cancellation, and all requested redirect checks.

The Maven build executed one application-context test. That automated test does not replace the manual functional tests below.

## Authentication and Access Tests

| ID | Procedure | Expected Result | Status |
| --- | --- | --- | --- |
| S01 | While logged out, open / | Redirect to /login | Passed |
| S02 | While logged out, open /register | Registration form appears | Passed |
| S03 | Submit an existing username with an incorrect password | Login fails with a generic error | Passed |
| S04 | Log in with an existing account | Inventory and the authenticated greeting appear | Passed |
| S05 | Select Log Out | Login page displays logout confirmation and anonymous navigation | Passed |
| S06 | While logged out, open /parts/new | Redirect to /login | Passed |
| S07 | While logged out, open /parts/created | Redirect to /login | Passed |
| S08 | Register a new account with valid details | Account creation succeeds and redirects to login | Passed |
| S09 | Log in with the newly registered account | Inventory appears successfully | Passed |
| S10 | Open Home while authenticated | Home page opens successfully | Passed |

The remaining protected inventory routes are covered by T14 through T17 below.

## Milestone 5 Regression Tests

These cases preserve the intent of the Milestone 5 test plan. The update and persistence expectations use the current quantity of 7.

| ID | Procedure | Expected Result | Status |
| --- | --- | --- | --- |
| T01 | Log in and open inventory | RAM and SSD appear in the table | Passed |
| T02 | Open RAM details | Saved fields match the selected part | Passed |
| T03 | Open Edit Part | Current values populate the form | Passed |
| T04 | Change RAM quantity from 6 to 7 and save | Details show 7 and an update confirmation | Passed |
| T05 | Submit quantity -1 and unit cost -49.99, then reopen the part | Validation rejects both values; stored quantity remains 7 and cost remains 49.99 | Passed |
| T06 | Change RAM quantity to 99 and select Cancel | Stored quantity remains 7 | Passed |
| T07 | Create the disposable test part | A new record is saved with ID 3 | Passed |
| T08 | Open the test part's Delete page | Confirmation identifies ID 3 and its saved information | Passed |
| T09 | Select Cancel on the delete confirmation | The test record remains available | Passed |
| T10 | Confirm deletion of test record ID 3 | Success message appears and the test record disappears | Passed |
| T11 | Open /parts/3 after deletion while authenticated | Redirect to inventory with a missing-record message | Passed |
| T12 | Open /parts/3/edit after deletion while authenticated | Redirect to inventory with a missing-record message | Passed |
| T13 | Open /parts/3/delete after deletion while authenticated | Redirect to inventory with a missing-record message | Passed |
| T14 | Log out and request /inventory | Redirect to login | Passed |
| T15 | While logged out, request /parts/1 | Redirect to login | Passed |
| T16 | While logged out, request /parts/1/edit | Redirect to login | Passed |
| T17 | While logged out, request /parts/1/delete | Redirect to login | Passed |
| T18 | Restart the application and log in | RAM quantity remains 7, SSD quantity remains 3, and ID 3 is absent | Passed |
| T19 | Run Maven clean package | Successful build and application-context test | Passed |
| T20 | Run the packaged JAR and log in | Application starts and displays persisted inventory | Passed |

## Packaging Configuration Issue

The first executable JAR login attempt failed before the terminal's database password configuration was corrected.

DB_PASSWORD was then supplied in PowerShell, and the JAR was restarted. The subsequent login and inventory check passed.

The final passing result is recorded above. The initial configuration issue is retained here and in the planning documentation.

## Not Executed

The following scenarios were not part of the completed test set:

- Dedicated missing-token or invalid-token CSRF requests.
- Inspection of session identifiers before and after login.
- Controlled database outage and recovery.
- Simultaneous edits by multiple users.
- Login throttling, account lockout, or multifactor authentication.
- Exhaustive registration and inventory validation boundaries.
- Empty-database display.
- Separate mobile viewport regression testing.
- Production HTTPS deployment.
- REST endpoint testing, which is scheduled for Milestone 7.

These scenarios are not reported as passed.

## Acceptance Result

All 10 listed authentication and access cases and all 20 adapted Milestone 5 regression cases passed based on screenshot evidence and tester confirmation.

Maven reported one automated test, zero failures, zero errors, and zero skipped tests. Manual cases are separate from that automated test count.

Known limitations remain documented in [Analysis and Planning](analysisPlanning.md).