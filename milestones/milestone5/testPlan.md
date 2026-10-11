# Milestone 5: Test Plan

[Overview](README.md) | [Design](design.md) | [Evidence](test.md)

## Purpose

Verify inventory display, details, editing, deletion, validation, cancellation, missing-record handling, login-session redirects, persistence, and executable packaging.

## Environment and Preconditions

- Java 17 and Spring Boot 2.7.18.
- MySQL 5.7.24 running with it_parts_inventory configured.
- Valid application database credentials.
- A registered account available for login.
- RAM record ID 1 and SSD record ID 2 available.
- Port 8080 available.

Testing used a separate disposable record, ID 3, named Test Delete Part. The RAM and SSD records were retained.

## Execution

Alex Quintero executed the manual checks on October 11, 2026. Screenshot evidence is linked in test.md. Cancellation and individual route checks were also confirmed by the tester.

| ID | Procedure | Expected Result | Status |
| --- | --- | --- | --- |
| T01 | Log in and open inventory | RAM and SSD appear in the table | Passed |
| T02 | Open RAM details | Saved fields match the selected part | Passed |
| T03 | Open Edit Part | Current values populate the form | Passed |
| T04 | Change RAM quantity from 3 to 5 and save | Details show 5 and a success message | Passed |
| T05 | Submit quantity -1 and unit cost -49.99 | Field errors appear and changes are rejected | Passed |
| T06 | Change RAM quantity to 99 and select Cancel | Stored quantity remains 5 | Passed |
| T07 | Create a valid disposable part | A new record is saved and can be opened | Passed |
| T08 | Open the disposable part's Delete page | Confirmation identifies the selected record | Passed |
| T09 | Select Cancel on a delete confirmation | Record remains available | Passed |
| T10 | Confirm deletion of test record ID 3 | Success message appears and only the test record disappears | Passed |
| T11 | Open /parts/3 after deletion | Redirect to inventory with missing-record message | Passed |
| T12 | Open /parts/3/edit after deletion | Redirect to inventory with missing-record message | Passed |
| T13 | Open /parts/3/delete after deletion | Redirect to inventory with missing-record message | Passed |
| T14 | Log out and request /inventory | Redirect to login | Passed |
| T15 | While logged out, request /parts/1 | Redirect to login | Passed |
| T16 | While logged out, request /parts/1/edit | Redirect to login | Passed |
| T17 | While logged out, request /parts/1/delete | Redirect to login | Passed |
| T18 | Restart and log in | RAM quantity remains 5, SSD remains, and ID 3 is absent | Passed |
| T19 | Run Maven clean package | Successful build and application-context test | Passed |
| T20 | Run the packaged JAR and log in | Application starts and displays persisted inventory | Passed |

## Evidence Rules

A screenshot shows the visible state at capture time. Multi-step behaviors such as cancellation and redirects additionally rely on the tester's execution confirmation.

The Maven test is an application-context test. It does not replace the manual functional cases listed above.

## Not Executed

The following were outside this executed test set:

- Database outage and recovery.
- Simultaneous edits by multiple sessions.
- CSRF protection.
- Full authentication coverage of existing creation routes.
- Empty-database display.
- Separate mobile viewport regression testing.
- Exhaustive validation boundary testing.

These scenarios are not reported as passed.

## Acceptance Result

All 20 listed cases passed based on the collected screenshots, build output, and tester confirmation. Known limitations remain documented in the planning and design pages.