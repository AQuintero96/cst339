# CST-339: Milestone 2

## IT Parts Inventory Manager

- **Author:** Alex Quintero
- **Instructor:** Professor Bobby Estey
- **Course:** CST-339 Programming in Java III
- **Date:** September 20, 2026
- **Project arrangement:** Solo
- **Time spent:** Approximately 10 hours before finishing the report and submission files
- **Repository:** [CST-339 on GitHub](https://github.com/AQuintero96/cst339)
- **Source code:** [IT Parts Inventory Manager](../code/it-parts-inventory/)
- **Previous milestone:** [Milestone 1](../milestone1/README.md)
- **Video:** [Milestone 2 Screencast](https://youtu.be/LT1w99JllCw)

## Introduction

Milestone 1 introduced the plan for IT Parts Inventory Manager. Milestone 2 puts the first parts of that plan into a working application.

The idea comes from working in IT, where spare computer parts need to be organized and easy to find. The finished application will track parts, quantities, costs, and storage locations. For this milestone, the focus was the home page, registration, login, and a consistent layout.

Users can register an account, log in, reach the inventory landing page, and log out. Forms check for missing or invalid information and show messages explaining what needs to be corrected. Accounts are temporary and are cleared when the application restarts because this milestone does not use a database.

## Tasks Completed

- Created and configured the Spring Boot project.
- Built the home page and application navigation.
- Added a shared Thymeleaf layout, header, and footer.
- Applied a navy and teal theme using Bootstrap and custom CSS.
- Created the registration form and its validation rules.
- Added password confirmation and duplicate-username checks.
- Added temporary account storage.
- Created the login form and checked submitted credentials.
- Added an inventory landing page.
- Changed the navigation to reflect whether a user is logged in.
- Added logout.
- Tested valid and invalid form submissions.
- Checked the layout in two desktop browsers, on a phone, and with tablet emulation.
- Built the project with Maven.
- Recorded the demonstration video.
- Prepared the database design and draft SQL script.

## Weekly Status Summary

| Task | Owner | Status |
| --- | :---: | --- |
| Project setup, home page, and shared layout | Alex Quintero | Complete |
| Registration and validation | Alex Quintero | Complete |
| Login, inventory landing page, and logout | Alex Quintero | Complete |
| Browser and responsive layout checks | Alex Quintero | Screenshots captured |
| Maven build | Alex Quintero | Successful |
| Screencast | Alex Quintero | Uploaded and accessible |
| Design report and draft database script | Alex Quintero | Prepared for final review |
| GitHub submission | Alex Quintero | Pushed |

**Hours worked:** Approximately 10 hours were spent on the milestone before the final documentation and submission work. This is an estimate of the total time, not a separate time log for each task.

**Peer review:** This is a solo project, so there was no separate team-member review. Testing and code review are handled individually.

## Planning Documentation

### How the Work Is Managed

The project follows the weekly milestone plan from Milestone 1. Each feature is broken into smaller tasks so it can be built and checked before moving on.

For Milestone 2, the home page and shared layout came first. Registration was added next, followed by validation, account storage, and login. Once those worked together, the remaining work focused on the inventory landing page, logout, responsive layouts, and documentation.

Alex Quintero is responsible for development, testing, documentation, and submission. GitHub keeps the source code and reports together and records changes as the project develops.

### What Stayed the Same from Milestone 1

The application still focuses on spare computer components:

- RAM.
- SSDs and hard drives.
- Processors.
- Motherboards.
- Power supplies.
- Graphics cards.
- Cooling components.

The planned inventory fields are still the part name, category, manufacturer, model, quantity, unit cost, storage location, and description.

Users will share the same inventory. The project does not include purchasing, employee equipment assignments, or tracking individual serial numbers.

The registration form now includes a phone number because it is required for Milestone 2.

### Milestone Boundaries

| Milestone 2 Includes | Later Milestones Will Add |
| --- | --- |
| Home, registration, and login pages | Creating part records |
| Form validation | Database storage |
| Temporary accounts | Full inventory listing and part details |
| Login state and logout | Updating and deleting parts |
| Shared layouts and responsive styling | Spring Security |
| Inventory landing page | Secured REST APIs |
| Database design and draft SQL | Database integration |

### Retrospective Results

Building the shared layout first made the other pages easier to add. The header, navigation, colors, and footer stayed consistent without copying the same HTML into every page.

Testing registration in stages also helped. Empty fields, invalid formats, mismatched passwords, and duplicate usernames were checked before moving on to the complete login flow.

The temporary account store worked for this milestone, but restarting the application clears registered users. That made it important to finish registration and login tests without restarting between them.

## Design Documentation

### General Technical Approach

The application uses Spring MVC controllers to handle browser requests and Thymeleaf templates to display pages. Bootstrap handles the responsive grid, navigation, and form styling. The custom stylesheet adds the project colors, spacing, and typography.

The code is divided into controllers, models, and business services.

| Component | What It Does |
| --- | --- |
| Controllers | Handle requests, check validation results, call services, and choose the next page |
| Form models | Hold submitted values and define field validation rules |
| Business services | Check password confirmation, create temporary accounts, and verify credentials |
| Session model | Hold the username and first name used after login |
| Templates | Display page content, forms, messages, and navigation |
| Future persistence layer | Store users and parts in MySQL |

There is no database connection in Milestone 2. Account records are kept in memory by `AccountService`.

### Key Technical Decisions

| Choice | Reason |
| --- | --- |
| Java 17 and Spring Boot 2.7.18 | Keep the same course environment used so far |
| Spring MVC | Handle page requests and form submissions |
| Thymeleaf | Display model data and validation messages in HTML |
| Thymeleaf Layout Dialect | Reuse the page layout |
| Bootstrap 5.3.3 | Support desktop, tablet, and phone layouts |
| Custom CSS | Give the application a consistent navy and teal appearance |
| Constructor injection | Pass services into controllers |
| Bean Validation | Check submitted fields on the server |
| In-memory account storage | Allow registration and login without a database |
| Case-insensitive usernames | Prevent accounts that differ only by capitalization |
| Salted password hashes | Avoid keeping plaintext passwords in account records |
| HTTP sessions | Track the simulated logged-in state |
| MySQL and Spring Data JDBC | Continue the database plan from Milestone 1 |
| BigDecimal and SQL DECIMAL | Use decimal values for future part costs |

### Installation and Configuration

#### Required Tools

- Java 17.
- Eclipse with Spring Tools or another compatible Java development environment.
- Maven or the included Maven wrapper.
- A browser.
- Internet access to download dependencies and load Bootstrap resources.

#### Project Folder

```text
C:\git\cst339\milestones\code\it-parts-inventory
```

#### Run from Eclipse

1. Import the folder as an existing Maven project if it is not already open.
2. Confirm the project uses Java 17.
3. Run Maven Update Project.
4. Run `ItPartsInventoryApplication` as a Spring Boot application.
5. Open `http://localhost:8080/`.

Stop any other application using port `8080` before starting this project.

#### Build the Project

From the project folder, run:

```powershell
.\mvnw.cmd clean package
```

The build creates:

```text
target/it-parts-inventory.jar
```

To run the packaged application:

```powershell
java -jar .\target\it-parts-inventory.jar
```

The build screenshot shows that the JAR was created. The recorded demonstration uses the application running from the local development environment.

#### Application Settings

The current `application.properties` file contains:

```properties
spring.application.name=it-parts-inventory
```

No database settings are needed yet.

A new account must be registered before logging in. Restarting the application clears the temporary accounts.

### Application Routes

| Method | Address | Purpose |
| --- | --- | --- |
| GET | `/` | Open the home page |
| GET | `/register` | Open registration |
| POST | `/register` | Validate and register an account |
| GET | `/login` | Open login |
| POST | `/login` | Check credentials and start the logged-in session |
| GET | `/inventory` | Open the inventory landing page |
| POST | `/logout` | End the session |

The inventory page is not protected in this milestone. Opening it directly without logging in shows a guest message. After login, it displays the user's first name and the logged-in navigation.

### Form Validation

| Field or Check | Rule |
| --- | --- |
| First name | Required, with a maximum of 50 characters |
| Last name | Required, with a maximum of 50 characters |
| Email | Required, with a valid format and a maximum of 254 characters |
| Phone number | Exactly 10 digits |
| Registration username | 3 to 32 letters, numbers, or underscores |
| Registration password | 8 to 64 characters |
| Password confirmation | Required and must match the password |
| Duplicate username | Rejected, regardless of capitalization |
| Login username | Required, with a maximum of 32 characters |
| Login password | Required, with a maximum of 64 characters |
| Incorrect credentials | Display a general login error |

Validation runs on the server. If registration fails, the other form values stay visible so the user does not have to enter everything again. Password fields are cleared.

### Account Storage and Login State

`AccountService` stores accounts in a concurrent map. Each account contains the registration details, a random salt, and a password hash.

Passwords are processed using PBKDF2 with HMAC-SHA256, a 16-byte salt, 600,000 iterations, and a 256-bit hash.

After successful login, the application creates a new session and stores a `SessionUser`. This object contains only the username and first name. Logout ends the session.

This supports the simulated login required for Milestone 2. Spring Security and protected pages will be added in Milestone 6.

### Known Issues and Limitations

| Item | Current Behavior |
| --- | --- |
| Temporary accounts | Restarting the application removes registered accounts |
| Inventory | The landing page does not contain part records yet |
| Page protection | Pages are not protected by Spring Security |
| Tablet testing | Completed with emulation instead of a physical tablet |
| Bootstrap resources | Loaded from a CDN, so an internet connection is needed |
| Session IDs | Some redirects include `jsessionid` in the address; session tracking configuration will need review |
| Automated tests | The Maven build ran one test; functional checks were also performed manually |
| Database script | Drafted but not executed or connected to the application |

### Risks

| Risk | Type | Plan | Owner |
| --- | --- | --- | :---: |
| Running short on development time | Planning | Complete required features before optional changes | Alex Quintero |
| Losing demo accounts after a restart | Functional | Register again after restarting and avoid restarts during the recording | Alex Quintero |
| Dependency or Java version problems | Technical | Keep versions consistent and check Maven builds | Alex Quintero |
| Bootstrap failing to load | Technical | Check the network connection before demonstrations | Alex Quintero |
| Layout differences on a real tablet | Functional | Identify the test as emulation and check a physical tablet when available | Alex Quintero |
| Treating temporary login as finished security | Technical | Document the current limits and add Spring Security in its scheduled milestone | Alex Quintero |
| Database fields not matching later Java code | Technical | Review the schema and mappings before adding persistence | Alex Quintero |

### Sitemap Diagram

This diagram shows the pages and navigation available in Milestone 2.

```mermaid
flowchart TD
    home["Home"]
    registration["Registration"]
    login["Login"]
    inventory["Inventory Landing Page"]
    logout["Logout"]

    home --> registration
    home --> login
    registration -->|Invalid information| registration
    registration -->|Account created| login
    registration -->|Cancel| home
    login -->|Invalid credentials| login
    login -->|Successful login| inventory
    inventory -->|Home link| home
    inventory --> logout
    logout -->|Session ended| login
    home -->|Inventory link after login| inventory
    guest["Direct request without login"] --> inventory
```

Visitors see Home, Login, and Register in the navigation. Logged-in users see Home, Inventory, their first name, and Log Out.

### User Interface Diagrams

These wireframes show the main sections of each page. Screenshots of the actual pages are included later in the report.

#### Home Page

```text
+--------------------------------------------------------+
| IT Parts Inventory             Home   Login   Register |
+--------------------------------------------------------+
| Application title             Inventory summary        |
| Introduction                  Component information    |
| Create an Account | Log In    Quantity, cost, location  |
+--------------------------------------------------------+
| Memory and storage | Core parts | Supporting hardware   |
+--------------------------------------------------------+
| Project name                    Author and course       |
+--------------------------------------------------------+
```

#### Registration Page

```text
+--------------------------------------------------------+
| Shared navigation                                      |
+--------------------------------------------------------+
| Create an Account                                      |
| Instructions and validation summary                    |
|                                                        |
| First Name                 Last Name                   |
| Email Address              Phone Number                |
| Username                                               |
| Password                   Confirm Password            |
| Validation messages beside the fields                  |
|                                                        |
| Create Account             Cancel                      |
| Link to Login                                          |
+--------------------------------------------------------+
| Shared footer                                          |
+--------------------------------------------------------+
```

#### Login Page

```text
+--------------------------------------------------------+
| Shared navigation                                      |
+--------------------------------------------------------+
| Log In                                                 |
| Success or error message                               |
|                                                        |
| Username                                               |
| Password                                               |
| Log In                                                 |
| Link to Register                                       |
+--------------------------------------------------------+
| Shared footer                                          |
+--------------------------------------------------------+
```

#### Inventory Landing Page

```text
+--------------------------------------------------------+
| Home       Inventory       Hello, Alex       Log Out    |
+--------------------------------------------------------+
| Parts Inventory                                        |
| Welcome message                                        |
|                                                        |
| Inventory Overview                                     |
| Description of the planned part information            |
| No inventory records to display                        |
+--------------------------------------------------------+
| Shared footer                                          |
+--------------------------------------------------------+
```

On a phone, the navigation collapses into a menu button and the registration fields stack into one column.

### ER Diagram

The database design contains two tables: users and parts.

```mermaid
erDiagram
    APP_USERS {
        BIGINT user_id PK
        VARCHAR first_name
        VARCHAR last_name
        VARCHAR email
        CHAR phone_number
        VARCHAR username UK
        VARCHAR password_hash
    }

    PARTS {
        BIGINT part_id PK
        VARCHAR part_name
        VARCHAR category
        VARCHAR manufacturer
        VARCHAR model
        INT quantity
        DECIMAL unit_cost
        VARCHAR storage_location
        VARCHAR description
        BIGINT version
    }
```

**PK** means primary key. It identifies each record. **UK** means unique key. The username must be unique so another account cannot register the same name.

There is no ownership relationship between users and parts. The project uses one shared inventory, as planned in Milestone 1.

Password confirmation is only used by the registration form and will not be stored in the database. Session information is also separate from the database tables.

Each part record represents a part model at a storage location. The quantity shows how many units are available. The version field is planned for detecting conflicting updates when database operations are added.

### DDL Script

[View the draft database script](database/schema-draft.sql)

The script defines the `app_users` and `parts` tables, their keys, and basic constraints. It includes checks for supported categories, required text, and nonnegative quantities and costs.

The script targets MySQL 8.0.16 or later. It has not been run or tested against a database because database implementation is scheduled for Milestone 4.

The planned password column will hold an encoded credential with the information needed to verify it. The current application keeps the salt and hash separately in memory, so that format will need to be handled when database storage is added.

### Controller Class Diagram

```mermaid
classDiagram
    class HomeController {
        +displayHome(Model model) String
    }

    class RegistrationController {
        -RegistrationService registrationService
        -AccountService accountService
        +RegistrationController(RegistrationService registrationService, AccountService accountService)
        +displayRegistration(Model model) String
        +processRegistration(UserModel userModel, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) String
    }

    class LoginController {
        -AccountService accountService
        +LoginController(AccountService accountService)
        +displayLogin(Model model) String
        +processLogin(LoginModel loginModel, BindingResult bindingResult, Model model, HttpServletRequest request) String
        +displayInventory(Model model) String
        +logout(HttpServletRequest request, RedirectAttributes redirectAttributes) String
    }

    class RegistrationService {
        +passwordsMatch(String password, String confirmPassword) boolean
    }

    class AccountService {
        +register(UserModel user) boolean
        +authenticate(String username, String password) Optional~SessionUser~
        -normalizeUsername(String username) String
        -hashPassword(String password, byte[] salt) byte[]
    }

    RegistrationController --> RegistrationService : checks passwords
    RegistrationController --> AccountService : creates account
    LoginController --> AccountService : checks credentials
```

### Object Model Class Diagram

```mermaid
classDiagram
    class UserModel {
        -String firstName
        -String lastName
        -String email
        -String phoneNumber
        -String username
        -String password
        -String confirmPassword
        +UserModel()
        +getFirstName() String
        +setFirstName(String firstName) void
        +getLastName() String
        +setLastName(String lastName) void
        +getEmail() String
        +setEmail(String email) void
        +getPhoneNumber() String
        +setPhoneNumber(String phoneNumber) void
        +getUsername() String
        +setUsername(String username) void
        +getPassword() String
        +setPassword(String password) void
        +getConfirmPassword() String
        +setConfirmPassword(String confirmPassword) void
    }

    class LoginModel {
        -String username
        -String password
        +LoginModel()
        +getUsername() String
        +setUsername(String username) void
        +getPassword() String
        +setPassword(String password) void
    }

    class SessionUser {
        -String username
        -String firstName
        +SessionUser(String username, String firstName)
        +getUsername() String
        +getFirstName() String
    }

    class AccountRecord {
        -String firstName
        -String lastName
        -String email
        -String phoneNumber
        -String username
        -byte[] salt
        -byte[] passwordHash
        -AccountRecord(String firstName, String lastName, String email, String phoneNumber, String username, byte[] salt, byte[] passwordHash)
    }

    class AccountService

    AccountService ..> UserModel : receives registration data
    AccountService *-- AccountRecord : stores accounts
    AccountService ..> SessionUser : returns login information
```

`AccountRecord` is a private class inside `AccountService`. `SessionUser` implements `Serializable` and contains no password. Getters and setters in the form models allow Spring MVC to bind submitted values.

## Screenshots and Testing

### 01. Home Page

The home page introduces the application and provides links to registration and login.

![Home page](screenshots/01-home-page.png)

### 02. Component Categories and Footer

The lower part of the page shows the types of computer components covered by the project.

![Component categories and footer](screenshots/02-home-page-components.png)

### 03. Registration Form

The form includes the required account details and password confirmation.

![Registration form](screenshots/03-registration-form.png)

### 04. Empty Registration Form

Submitting the empty form displays messages for the missing fields.

![Required registration errors](screenshots/04-registration-required-errors.png)

### 05. Password Mismatch

The other values pass validation, but the passwords do not match. The form explains the problem beside the confirmation field.

![Password mismatch](screenshots/05-registration-password-mismatch.png)

### 06. Successful Registration

Valid registration creates an account and redirects to login with a success message.

![Registration success](screenshots/06-registration-success.png)

### 07. Empty Login Form

Both login fields display required-field messages when submitted empty.

![Required login errors](screenshots/07-login-required-errors.png)

### 08. Incorrect Credentials

The login page displays an error when the submitted credentials do not match an account.

![Incorrect credentials](screenshots/08-login-invalid-credentials.png)

### 09. Inventory Page After Login

The inventory landing page displays the user's first name. The navigation now includes Inventory and Log Out.

![Inventory after login](screenshots/09-inventory-after-login.png)

### 10. Logout

The confirmation message shows that logout completed. Login and Register return to the navigation.

![Logout success](screenshots/10-logout-success.png)

### 11. Duplicate Username

Trying to register an existing username displays an error beside that field.

![Duplicate username](screenshots/11-registration-duplicate-username.png)

### 12. Invalid Field Formats

This test shows the messages for an invalid email, short phone number, username containing a space, and short passwords.

![Invalid registration formats](screenshots/12-registration-invalid-formats.png)

### 13. Opera GX

The inventory page and logged-in navigation display in Opera GX.

![Opera GX](screenshots/13-desktop-browser-one.png)

### 14. Microsoft Edge

The same page displays in Microsoft Edge.

![Microsoft Edge](screenshots/14-desktop-browser-two.png)

### 15. Mobile Home Page

The home page was opened on a physical iPhone 16 Pro Max using Chrome. The navigation collapses and the content fits the smaller screen.

![Mobile home page](screenshots/15-mobile-home.png)

### 16. Mobile Registration

The registration fields stack vertically on the phone.

![Mobile registration](screenshots/16-mobile-registration.png)

### 17. Tablet Home Page

The tablet layout was checked using iPad Pro 13 emulation in Edge DevTools. A physical tablet was not available.

![Emulated tablet home page](screenshots/17-tablet-home.png)

### 18. Tablet Registration

The registration form fits the emulated tablet layout.

![Emulated tablet registration](screenshots/18-tablet-registration.png)

### 19. Maven Build

Maven reported BUILD SUCCESS with one test, zero failures, zero errors, and zero skipped tests. The build created `it-parts-inventory.jar`.

The build result is separate from the manual form and browser checks shown above.

![Maven build success](screenshots/19-maven-build-success.png)

## Screencast

[Watch the Milestone 2 demonstration](https://youtu.be/LT1w99JllCw)

The video is unlisted on YouTube. It was opened in a private browser window to confirm that the link works without signing into the owner's account.

## Conclusion

Milestone 2 turned the initial project plan into a working set of pages. Users can register, correct invalid entries, log in, view the inventory landing page, and log out. The shared layout keeps those pages consistent across the tested screen sizes.

The next milestones will build on this work by adding part records, database storage, and security. The project remains focused on the computer-parts inventory application planned in Milestone 1.

## Course References

- CST-339 CLC Project Overview, supplied as `CST-339 CLC Project Overview.doc` and reviewed through `CST-339-Milestones.pdf`.
- CST-339 Project Design Report Template, supplied as `CST-339-RS-ProjectDesignReportTemplate.docx`.
- Professor's Milestone 2 requirements and rubric, supplied through the course assignment page.
- [Professor's Milestone 1 requirements](https://gitlab.com/bobby.estey/gcu-cst339/-/tree/main/milestones/milestone01).
- [Professor's Markdown report example](https://gitlab.com/bobby.estey/gcu-cst339/-/blob/main/milestones/milestone01/example01.md).
- [MySQL documentation: CHECK constraints](https://dev.mysql.com/doc/refman/8.0/en/information-schema-check-constraints-table.html).