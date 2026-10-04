# Milestone 4: Design and Setup

[Overview](README.md) | [Analysis and Planning](analysisPlanning.md) | [Testing](test.md)

## Installation and Configuration

### Requirements

- Java 17
- MySQL
- Maven Wrapper included with the project
- Port 8080 available
- Internet access for initial Maven dependencies and Bootstrap CDN resources

The development environment uses Spring Boot 2.7.18 and MySQL 5.7.24.

### Database Setup

Open [schema.sql](database/schema.sql) in MySQL Workbench and execute it using an administrative connection.

The script creates:

- Database: it_parts_inventory
- Account table: app_users
- Inventory table: parts

It does not drop existing tables. It also does not modify the structure of tables that already exist.

The application uses the cst339_app account created during Activity 4. An administrator grants it access with:

```sql
GRANT SELECT, INSERT, UPDATE, DELETE
ON it_parts_inventory.*
TO 'cst339_app'@'localhost';
```

On another computer, create an application account with a locally chosen password before granting access. Do not commit credentials to the repository.

### Application Properties

```properties
spring.application.name=it-parts-inventory

# Connects to the milestone database.
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/it_parts_inventory
spring.datasource.username=cst339_app

# Reads the password from the launch environment.
spring.datasource.password=${DB_PASSWORD}

# Schema creation is managed through the documented SQL script.
spring.sql.init.mode=never
```

The project uses spring-boot-starter-jdbc and MySQL Connector/J. JdbcTemplate and the connection pool are configured by Spring Boot.

### Running in Eclipse

1. Import the application as an existing Maven project.
2. Select Java 17 and update the Maven project.
3. Open its Spring Boot run configuration.
4. Add DB_PASSWORD under Environment.
5. Start MySQL.
6. Run the application and open http://localhost:8080/.

Stop other applications using port 8080 first.

### Building and Running Outside Eclipse

On the development computer:

```powershell
Set-Location 'C:\git\cst339\milestones\code\it-parts-inventory'

$env:JAVA_HOME = 'C:\opt\jdk-17.0.20.1+1'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

$dbCredential = Get-Credential -UserName 'cst339_app' -Message 'Enter your database password'
$env:DB_PASSWORD = $dbCredential.GetNetworkCredential().Password

.\mvnw.cmd clean package
java -jar target\it-parts-inventory.jar
```

Adjust the project and Java paths on another computer. Eclipse environment settings do not automatically carry over to PowerShell.

Press Ctrl+C to stop the JAR. Saved accounts and parts remain in MySQL.

## Sitemap

These paths describe navigation and form submissions, not enforced access-control rules.

```mermaid
flowchart TD
    Home["Home /"] --> Register["Register /register"]
    Home --> Login["Login /login"]
    Register -->|Account saved| Login
    Login -->|Correct credentials| Inventory["Inventory /inventory"]
    Login -->|Incorrect credentials| Login
    Inventory --> Add["Add Part /parts/new"]
    Nav["Shared logged-in navigation"] --> Inventory
    Nav --> Add
    Add --> Submit["POST /parts"]
    Submit -->|Validation or database error| Add
    Submit -->|Saved successfully| Confirmation["Confirmation /parts/created"]
    Add -->|Cancel| Inventory
    Confirmation -->|Add Another Part| Add
    Confirmation -->|Return to Inventory| Inventory
    Nav --> Logout["POST /logout"]
    Logout --> Login
```

## User Interface Pages

Click a preview to open its full image. Home and registration previews come from Milestone 2; they show the established layout. The other previews show Milestone 4.

These are implementation screenshots. The Mermaid wireframes follow the table.

| Page Name | Description | Preview | Page Name | Description | Preview |
| --- | --- | --- | --- | --- | --- |
| Home | Introduces the application and links to account entry. | [<img src="../milestone2/screenshots/15-mobile-home.png" width="160" alt="Home page preview">](../milestone2/screenshots/15-mobile-home.png) | Registration | Collects account details and displays validation errors. | [<img src="../milestone2/screenshots/16-mobile-registration.png" width="160" alt="Registration page preview">](../milestone2/screenshots/16-mobile-registration.png) |
| Login | Checks stored credentials and displays errors. | [<img src="screenshots/08-invalid-database-login.png" width="160" alt="Login page preview">](screenshots/08-invalid-database-login.png) | Inventory | Welcomes the user and links to part creation. | [<img src="screenshots/12-executable-jar-login.png" width="160" alt="Inventory page preview">](screenshots/12-executable-jar-login.png) |
| Add Part | Collects and validates component information. | [<img src="screenshots/09-invalid-part-values.png" width="160" alt="Add Part preview">](screenshots/09-invalid-part-values.png) | Confirmation | Shows the saved submission and database ID. | [<img src="screenshots/05-database-part-created.png" width="160" alt="Part confirmation preview">](screenshots/05-database-part-created.png) |

### Mermaid Wireframes

All pages use the shared Thymeleaf layout. The diagrams show content sections rather than exact dimensions.

<details>
<summary>Home and Registration</summary>

```mermaid
flowchart TB
    subgraph Home["Home Page"]
        direction TB
        H1["Brand | Home | Login | Register"]
        H2["Application introduction<br/>Create an Account | Log In<br/>Inventory summary"]
        H3["Component category cards"]
        H4["Shared footer"]
        H1 ~~~ H2 ~~~ H3 ~~~ H4
    end
    subgraph Registration["Registration Page"]
        direction TB
        R1["Shared navigation"]
        R2["Create an Account | Instructions"]
        R3["First Name | Last Name<br/>Email | Phone<br/>Username<br/>Password | Confirmation"]
        R4["Validation messages<br/>Create Account | Cancel | Login link"]
        R5["Shared footer"]
        R1 ~~~ R2 ~~~ R3 ~~~ R4 ~~~ R5
    end
```

</details>

<details>
<summary>Login and Inventory</summary>

```mermaid
flowchart TB
    subgraph Login["Login Page"]
        direction TB
        L1["Shared navigation"]
        L2["Log In | Instructions"]
        L3["Success or error message<br/>Username<br/>Password"]
        L4["Log In | Registration link"]
        L5["Shared footer"]
        L1 ~~~ L2 ~~~ L3 ~~~ L4 ~~~ L5
    end
    subgraph Inventory["Inventory Page"]
        direction TB
        I1["Home | Inventory | Add Part | Greeting | Log Out"]
        I2["Parts Inventory | Welcome"]
        I3["Inventory Overview | Add Part"]
        I4["Part entry available<br/>Browsing and editing planned"]
        I5["Shared footer"]
        I1 ~~~ I2 ~~~ I3 ~~~ I4 ~~~ I5
    end
```

</details>

<details>
<summary>Add Part and Confirmation</summary>

```mermaid
flowchart TB
    subgraph AddPart["Add Part Page"]
        direction TB
        A1["Shared navigation"]
        A2["Add Part | Instructions"]
        A3["Validation or database error"]
        A4["Name | Category<br/>Manufacturer | Model<br/>Quantity | Unit Cost"]
        A5["Storage Location<br/>Optional Description"]
        A6["Create Part | Cancel"]
        A7["Shared footer"]
        A1 ~~~ A2 ~~~ A3 ~~~ A4 ~~~ A5 ~~~ A6 ~~~ A7
    end
    subgraph Confirmation["Confirmation Page"]
        direction TB
        C1["Shared navigation"]
        C2["Part Created | Success message"]
        C3["Database ID<br/>Saved component details"]
        C4["Add Another Part | Return to Inventory"]
        C5["Shared footer"]
        C1 ~~~ C2 ~~~ C3 ~~~ C4 ~~~ C5
    end
```

</details>

## Database Design

[View the complete DDL script](database/schema.sql)

The inventory is shared, so parts do not have an owner foreign key. PK means primary key, and UK means unique key.

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

### Account Storage

Names allow 50 characters each, email allows 254, phone contains 10 characters, and username allows 32. Username uniqueness uses the database's case-insensitive collation.

The password_hash column allows 255 characters. It stores the algorithm, iteration count, Base64 salt, and Base64 hash. Password confirmation is never persisted.

### Part Storage

| Field | Definition |
| --- | --- |
| part_id | Auto-increment primary key |
| part_name | Required, maximum 100 characters |
| category | Required, maximum 30 characters |
| manufacturer | Required, maximum 60 characters |
| model | Required, maximum 100 characters |
| quantity | Required integer |
| unit_cost | Required DECIMAL(12,2) |
| storage_location | Required, maximum 100 characters |
| description | Optional, maximum 1,000 characters |
| version | Defaults to zero; reserved for later update handling |

Java validation restricts categories and rejects negative quantity and cost values. MySQL 5.7.24 does not enforce CHECK constraints, although primary keys, uniqueness, and NOT NULL constraints still apply.

The version column is not currently mapped in PartModel.

## Class Design

### Business and Persistence Services

```mermaid
classDiagram
    class AccountServiceInterface {
        <<interface>>
        +register(UserModel) boolean
        +authenticate(String, String) Optional~SessionUser~
    }
    class AccountService {
        -AccountDataAccessInterface accountDataService
        +register(UserModel) boolean
        +authenticate(String, String) Optional~SessionUser~
        -encodePassword(String) String
        -passwordMatches(String, String) boolean
        -hashPassword(String, byte[], int) byte[]
    }
    class AccountDataAccessInterface {
        <<interface>>
        +create(UserModel, String) void
        +findByUsername(String) Optional~AccountRecord~
    }
    class AccountDataService {
        -JdbcTemplate jdbcTemplate
        +create(UserModel, String) void
        +findByUsername(String) Optional~AccountRecord~
    }
    class RegistrationServiceInterface {
        <<interface>>
        +passwordsMatch(String, String) boolean
    }
    class RegistrationService {
        +passwordsMatch(String, String) boolean
    }
    class PartServiceInterface {
        <<interface>>
        +createPart(PartModel) PartModel
    }
    class PartService {
        -PartDataAccessInterface partDataService
        +createPart(PartModel) PartModel
    }
    class PartDataAccessInterface {
        <<interface>>
        +create(PartModel) Long
    }
    class PartDataService {
        -JdbcTemplate jdbcTemplate
        +create(PartModel) Long
    }

    AccountServiceInterface <|.. AccountService
    AccountDataAccessInterface <|.. AccountDataService
    AccountService --> AccountDataAccessInterface
    RegistrationServiceInterface <|.. RegistrationService
    PartServiceInterface <|.. PartService
    PartDataAccessInterface <|.. PartDataService
    PartService --> PartDataAccessInterface
```

### Object Models

Accessors are omitted from the diagram for readability. Form models expose getters and setters. AccountRecord and SessionUser expose read access to their stored values.

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
    }
    class LoginModel {
        -String username
        -String password
    }
    class AccountRecord {
        -Long userId
        -String username
        -String firstName
        -String passwordHash
    }
    class SessionUser {
        -String username
        -String firstName
    }
    class PartModel {
        -Long partId
        -String partName
        -String category
        -String manufacturer
        -String model
        -Integer quantity
        -BigDecimal unitCost
        -String storageLocation
        -String description
    }
```

### Controller Dependencies

```mermaid
classDiagram
    class HomeController
    class RegistrationController
    class LoginController
    class PartController
    class AccountServiceInterface
    class RegistrationServiceInterface
    class PartServiceInterface

    RegistrationController --> AccountServiceInterface
    RegistrationController --> RegistrationServiceInterface
    LoginController --> AccountServiceInterface
    PartController --> PartServiceInterface
```

HomeController displays the home page. RegistrationController handles account forms. LoginController handles login, logout, and the inventory landing page. PartController handles creation and confirmation.

## Part Creation Transaction

```mermaid
sequenceDiagram
    actor User
    participant Controller as PartController
    participant Service as PartService
    participant DAO as PartDataService
    participant DB as MySQL

    User->>Controller: Submit part
    Controller->>Controller: Validate fields
    alt Invalid input
        Controller-->>User: Form with errors
    else Valid input
        Controller->>Service: createPart
        Service->>DAO: create
        Note over DAO,DB: Transaction begins
        DAO->>DB: Parameterized INSERT
        DB-->>DAO: Generated ID
        Note over DAO,DB: Commit on success or roll back on runtime failure
        DAO-->>Service: Part ID
        Service-->>Controller: Saved part
        Controller-->>User: Redirect to confirmation
    end
```

Database failures propagate to the controller, which returns the form with an error message. The confirmation page is shown only after a successful service result.

[Return to Milestone 4](README.md)