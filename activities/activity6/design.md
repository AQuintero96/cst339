# Design and Setup

[Return to Activity 6](README.md)

## Technology

- Java 17
- Spring Boot 2.7.18
- Spring Security
- Thymeleaf for the orders application's browser pages
- Spring Data MongoDB and MongoDB Atlas
- Postman for REST authentication tests
- GitHub OAuth2 for external authentication
- Maven for dependency management and packaging

## Project Organization

| Project | Main Application | Purpose |
| --- | --- | --- |
| topic6-1 | Topic61Application | In-memory form authentication |
| topic6-2 | Topic62Application | MongoDB-backed form authentication |
| topic6-3 | Topic63Application | Form login and protected REST endpoints |
| topic6-4 | Topic64Application | GitHub OAuth2 authentication |

## Parts 1 and 2: Form Login

Spring Security processes the submitted username and password. Successful authentication opens the orders page. Invalid credentials return the user to the login page with an error message.

Part 1 uses an in-memory account. Part 2 uses UserBusinessService and UsersDataService to retrieve an account through UsersRepository. A BCrypt password encoder verifies the submitted password against the stored hash.

The users collection contains these fields:

| Field | Purpose |
| --- | --- |
| _id | Application account identifier |
| username | Login name |
| password | BCrypt password hash |

The orders collection supplies the data displayed by the browser page and REST endpoints.

## Part 3: REST Authentication

The security configuration removes public access to the service endpoints and enables HTTP Basic authentication.

| Endpoint | Successful Response |
| --- | --- |
| /service/getjson | Order data in JSON format |
| /service/getxml | Order data in XML format |

Postman sends the account credentials using Basic Auth. Invalid credentials produce an HTTP 401 response. The project also retains form login for browser access.

## Part 4: GitHub OAuth2

The separate OAuth2 application uses Spring Security's OAuth2 client support.

1. The user requests /service/test.
2. Spring Security directs the user into the GitHub login flow.
3. GitHub authenticates the user and returns an authorization code.
4. Spring Security completes the OAuth2 exchange and obtains the user identity.
5. The application displays a greeting using the authenticated GitHub username.

The registered callback URL is:

`http://localhost:8080/login/oauth2/code/github`

The project configures GitHub's login attribute as the principal name.

## Environment Variables

| Projects | Required Variables |
| --- | --- |
| topic6-1, topic6-2, topic6-3 | MONGODB_URI |
| topic6-4 | GITHUB_CLIENT_ID and GITHUB_CLIENT_SECRET |

Configure these variables in the Eclipse run configuration or the terminal session used to launch the application.

## Running the Applications

Only one project should use port 8080 at a time.

1. Stop any previous application using port 8080.
2. Select the desired project's main application class.
3. Confirm that its required environment variables are configured.
4. Run the class as a Java Application or Spring Boot App.
5. Wait for the console's application-started message.
6. Keep the application running while testing in the browser or Postman.

For Parts 1 through 3, open:

`http://localhost:8080/`

For Part 4, open:

`http://localhost:8080/service/test`

## Executable JAR Verification

Parts 1 and 2 were packaged with Maven and launched outside Eclipse.

From the corresponding project directory, with Java 17 and MONGODB_URI configured:

```powershell
.\mvnw.cmd clean package
java -jar target\cst339activity.jar
```

The browser was then used to log in and display the orders page.