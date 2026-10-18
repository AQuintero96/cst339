# Milestone 6: Testing and Screenshots

[Overview](README.md) | [Planning](analysisPlanning.md) | [Design](design.md) | [Test Plan](testPlan.md)

## Results Overview

Testing covered database-backed authentication, public registration, protected routes, logout, inventory regression behavior, restart persistence, and executable packaging.

Existing and newly registered accounts authenticated successfully. The inventory retained RAM quantity 7 and SSD quantity 3 after restart and when running from the executable JAR.

Click a screenshot thumbnail to view the full image.

## Authentication and Access

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 1. Protected home page | Opening the home page while logged out redirected to /login. | [<img src="screenshots/01-home-login-required.png" width="220" alt="Login required for home page">](screenshots/01-home-login-required.png) |
| 2. Public registration | The registration form remained accessible without authentication. | [<img src="screenshots/02-public-registration.png" width="220" alt="Public registration form">](screenshots/02-public-registration.png) |
| 3. Invalid login | Incorrect credentials produced a login failure message. | [<img src="screenshots/03-invalid-login.png" width="220" alt="Invalid login message">](screenshots/03-invalid-login.png) |
| 4. Existing-account login | The existing account authenticated successfully. Inventory displayed the greeting and saved RAM and SSD records. | [<img src="screenshots/04-authenticated-inventory.png" width="220" alt="Authenticated inventory">](screenshots/04-authenticated-inventory.png) |
| 5. Logout | Logout returned to the login page with a confirmation message and anonymous navigation. | [<img src="screenshots/05-logout.png" width="220" alt="Logout confirmation">](screenshots/05-logout.png) |
| 6. New registration | A new account was created successfully and redirected to login. | [<img src="screenshots/06-registration-success.png" width="220" alt="Registration success">](screenshots/06-registration-success.png) |
| 7. New-account login | The newly registered account successfully accessed the shared inventory. | [<img src="screenshots/07-new-account-login.png" width="220" alt="New account inventory access">](screenshots/07-new-account-login.png) |

The tester also confirmed that Home opened while authenticated.

After logout, each of these direct requests redirected to /login:

- /inventory
- /parts/new
- /parts/created
- /parts/1
- /parts/1/edit
- /parts/1/delete

These route results rely on execution confirmation in addition to the representative login screenshot.

## Details, Editing, and Validation

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 8. Part details | RAM details displayed the saved fields, including initial quantity 6 and unit cost 49.99. | [<img src="screenshots/08-part-details.png" width="220" alt="Saved part details">](screenshots/08-part-details.png) |
| 9. Prefilled edit form | The edit form contained the existing RAM values. | [<img src="screenshots/09-edit-form.png" width="220" alt="Prefilled edit form">](screenshots/09-edit-form.png) |
| 10. Successful update | Saving the quantity change displayed an update confirmation and quantity 7. | [<img src="screenshots/10-part-updated.png" width="220" alt="Successful part update">](screenshots/10-part-updated.png) |
| 11. Validation feedback | The edit form displayed validation feedback for invalid numeric input. Negative quantity and cost rejection were confirmed during execution. | [<img src="screenshots/11-edit-validation.png" width="220" alt="Edit validation feedback">](screenshots/11-edit-validation.png) |

The tester confirmed that reopening the part after the rejected submission showed quantity 7 and unit cost 49.99.

Changing quantity to 99 and selecting Cancel also left the stored quantity at 7.

## Creation, Deletion, and Missing Records

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 12. Part creation | Milestone 6 Delete Test was saved with ID 3, quantity 1, and unit cost 10.00. | [<img src="screenshots/12-part-created.png" width="220" alt="Disposable part created">](screenshots/12-part-created.png) |
| 13. Delete confirmation | The confirmation page identified test record ID 3 before deletion. | [<img src="screenshots/13-delete-confirmation.png" width="220" alt="Delete confirmation">](screenshots/13-delete-confirmation.png) |
| 14. Confirmed deletion | A success message appeared, and inventory retained only the RAM and SSD records. | [<img src="screenshots/14-part-deleted.png" width="220" alt="Part deletion success">](screenshots/14-part-deleted.png) |
| 15. Missing record | Requesting the deleted record redirected to inventory with “That part no longer exists.” | [<img src="screenshots/15-part-not-found.png" width="220" alt="Missing part message">](screenshots/15-part-not-found.png) |

The tester confirmed that selecting Cancel on the delete confirmation preserved the record before the final deletion.

After deletion, all three authenticated requests produced the expected missing-record behavior:

- /parts/3
- /parts/3/edit
- /parts/3/delete

The representative screenshot shows the resulting message. Confirmation of all three routes comes from manual execution.

## Persistence and Executable Packaging

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 16. Restart persistence | After restarting and logging in, RAM quantity remained 7, SSD quantity remained 3, and deleted ID 3 was absent. | [<img src="screenshots/16-inventory-after-restart.png" width="220" alt="Inventory after restart">](screenshots/16-inventory-after-restart.png) |
| 17. Maven build | Maven reported BUILD SUCCESS with one test, zero failures, zero errors, and zero skipped tests. | [<img src="screenshots/17-maven-build-success.png" width="220" alt="Maven build success">](screenshots/17-maven-build-success.png) |
| 18. Executable JAR startup | The console showed embedded Tomcat running on port 8080 and ItPartsInventoryApplication started. | [<img src="screenshots/18-executable-jar-startup.png" width="220" alt="Executable JAR startup">](screenshots/18-executable-jar-startup.png) |
| 19. Executable JAR inventory | After DB_PASSWORD was configured in PowerShell, login succeeded and the packaged application displayed the persisted inventory. | [<img src="screenshots/19-executable-jar-inventory.png" width="220" alt="Inventory from executable JAR">](screenshots/19-executable-jar-inventory.png) |

The initial JAR login failure was resolved by supplying the database password in the terminal environment and restarting the application.

## Demonstration

[Watch the Milestone 6 technical and functional demonstration](https://youtu.be/QZNVNUzUlr8)

## Evidence Boundaries

Screenshots show the visible state at capture time. Multi-step behaviors, including redirects, cancellation, rejected values remaining unsaved, and use of the newly registered account, also rely on tester confirmation.

The build's single automated test verifies application-context loading. It does not independently establish authentication correctness or database availability.

Normal form submissions succeeded with Spring Security enabled. Dedicated invalid-CSRF-token tests and session-ID inspection were not performed, so those protections are documented as configured rather than independently tested.

The complete execution checklist and untested scenarios appear in the [Test Plan](testPlan.md).