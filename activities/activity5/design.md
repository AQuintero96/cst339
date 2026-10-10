# Activity 5: Design and Setup

[Activity Overview](README.md) | [Analysis and Planning](analysisPlanning.md) | [Testing](test.md)

## Environment

| Component | Configuration |
| --- | --- |
| Java | 17 |
| Spring Boot | 2.7.18 |
| Build tool | Maven Wrapper |
| Persistence | Spring Data MongoDB |
| Database host | MongoDB Atlas |
| Cluster | CST339Cluster |
| Database | cst339 |
| Collection | orders |
| Web port | 8080 |
| Executable | target/cst339activity.jar |

## Application Structure

Requests follow this sequence:

1. LoginController or OrdersRestService receives a request.
2. OrdersBusinessService requests the required data.
3. OrdersDataService delegates to OrdersRepository.
4. The repository accesses MongoDB Atlas.
5. The business service converts entities into OrderModel objects.
6. The controller returns a Thymeleaf page, JSON, or XML.

| Class or Interface | Responsibility |
| --- | --- |
| Topic51Application | Starts Spring Boot |
| SpringConfig | Registers the selected business service |
| OrderEntity | Maps documents in the orders collection |
| OrderModel | Supplies order values to HTML, JSON, and XML responses |
| OrderList | Provides the XML root wrapper |
| DataAccessInterface | Defines data-service operations |
| OrdersDataService | Delegates reads and saves to the repository |
| OrdersRepository | Extends MongoRepository and declares getOrderById |
| OrdersBusinessServiceInterface | Defines business operations |
| OrdersBusinessService | Converts database entities into presentation models |
| AnotherOrdersBusinessService | Retains the unselected sample implementation |
| LoginController | Displays the login and orders pages |
| SecurityBusinessService | Provides demonstration login behavior |
| OrdersRestService | Exposes collection and identifier endpoints |
| OrderSaveVerification | Performs saving and read-back checks under the save-test profile |

## Collection Design

OrderEntity uses @Document(collection = "orders"). Its String id field uses @Id. The orderNo and productName fields each declare @Indexed(unique = true).

| Field | Initial Atlas document type | Java representation | Purpose |
| --- | --- | --- | --- |
| _id | ObjectId | String | Document identifier |
| orderNo | String | String | Order number |
| productName | String | String | Product name |
| price | Decimal128 | float | Unit price |
| quantity | Int32 | int | Quantity ordered |

The five initial documents were entered with Decimal128 prices. Java retains float prices from the existing activity application; documents saved through Java may therefore use a different BSON numeric representation.

Initial orders were Notebook, Pen Set, Desk Organizer, Folder, and Stapler. The Java verification added order 1006, Java Save Test, priced at 12.50 with quantity 2.

## Endpoints

| Method | Path | Purpose |
| --- | --- | --- |
| GET | /login | Displays the login form |
| POST | /login/doLogin | Validates the demonstration form and displays orders |
| GET | /service/getjson | Returns all orders as JSON |
| GET | /service/getxml | Returns all orders as XML |
| GET | /service/getorder/{id} | Looks up an order by its MongoDB identifier |

The identifier endpoint is implemented to return HTTP 200 for a matching order, HTTP 404 when no document matches, and HTTP 500 when an exception occurs. Error responses contain a generic JSON message.

## Configuration

The application properties are:

```properties
spring.application.name=topic5-1

# Reads the private Atlas connection string from the environment.
spring.data.mongodb.uri=${MONGODB_URI}

# Selects the activity database.
spring.data.mongodb.database=cst339

# Applies the entity's declared indexes.
spring.data.mongodb.auto-index-creation=true
```

The complete Atlas connection string is supplied through MONGODB_URI. Its password placeholder must be replaced with the database user's password. Reserved characters in credentials must be URI-encoded.

The database user must have the required access to cst339, and the current client IP must be permitted by Atlas network access settings. The private connection string is not included in this report.

## Run from Eclipse

1. Import topic5-1 as an existing Maven project.
2. Use Java 17 and update the Maven project.
3. Open the Topic51Application run configuration.
4. Add MONGODB_URI under Environment with the complete private connection string.
5. Stop any other application using port 8080.
6. Run Topic51Application and open http://localhost:8080/login.

The demonstration login accepts nonempty username and password values.

## Run the Save Verification

Add this program argument to the Eclipse run configuration:

```text
--spring.profiles.active=save-test
```

The runner creates order 1006 if it is absent, reads the stored order, and verifies its values. A successful run prints:

```text
MongoDB save/read verification PASSED
```

Remove the argument for normal application execution. The saved document remains in MongoDB.

## Build and Run from PowerShell

Stop the Eclipse application first. Run:

```powershell
Set-Location 'C:\git\cst339\activities\code\topic5-1'

$env:JAVA_HOME = 'C:\opt\jdk-17.0.20.1+1'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

$mongoSecret = Read-Host 'Paste your complete MongoDB connection string' -AsSecureString
$env:MONGODB_URI = [System.Net.NetworkCredential]::new('', $mongoSecret).Password

.\mvnw.cmd clean package
java -jar target\cst339activity.jar
```

The connection string is entered at the hidden prompt. MONGODB_URI must also be available during the Maven build because the application-context test starts Spring Boot.

After startup, open http://localhost:8080/login. Press Ctrl+C in PowerShell to stop the application.

## Scope

The shared Thymeleaf presentation is retained from the earlier activity. This activity changes persistence and adds identifier lookup. It does not add a production authentication system or implement order update and deletion.