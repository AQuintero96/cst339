# Testing and Screenshots

[Return to Activity 6](README.md)

## Results Overview

| Part | Evidence |
| --- | --- |
| 1 | Home page, invalid login, authenticated orders, JSON and XML responses, build and executable JAR |
| 2 | Stored application account, invalid login, authenticated orders, JSON and XML responses, build and executable JAR |
| 3 | HTTP 200 and HTTP 401 responses for both REST endpoints |
| 4 | GitHub sign-in and authenticated application response |

## Part 1: In-Memory Authentication

### Home Page

The public home page provides access to the login page.

![Part 1 home page](screenshots/01-part1-home.png)

### Invalid Login

The login page displays an error after invalid credentials are submitted.

![Part 1 invalid login](screenshots/02-part1-invalid-login.png)

### Authenticated Orders

The orders page identifies the signed-in test account and displays six orders.

![Part 1 orders](screenshots/03-part1-orders.png)

### Public JSON Endpoint

The JSON endpoint returns the stored orders.

![Part 1 JSON response](screenshots/04-part1-public-json.png)

### Public XML Endpoint

The XML endpoint returns the stored orders.

![Part 1 XML response](screenshots/05-part1-public-xml.png)

### Maven Build

The corrected database configuration allowed the Maven build to succeed.

![Part 1 build success](screenshots/06-part1-build-success.png)

### Executable JAR Startup

The packaged application was launched outside Eclipse.

![Part 1 JAR startup](screenshots/07-part1-jar-startup.png)

### Executable JAR Orders

The running JAR served the authenticated orders page.

![Part 1 JAR orders](screenshots/08-part1-jar-orders.png)

## Part 2: MongoDB Authentication

### Stored Application Account

The users collection contains the application account and its BCrypt password hash.

![MongoDB application account](screenshots/09-part2-stored-user.png)

### Invalid Login

Invalid credentials produce a login error.

![Part 2 invalid login](screenshots/10-part2-invalid-login.png)

### Authenticated Orders

Successful authentication displays the orders page under the test account.

![Part 2 orders](screenshots/11-part2-orders.png)

### Public JSON Endpoint

The JSON endpoint returns the six stored orders.

![Part 2 JSON response](screenshots/12-part2-public-json.png)

### Public XML Endpoint

The XML endpoint returns the six stored orders.

![Part 2 XML response](screenshots/13-part2-public-xml.png)

### Maven Build

Maven reports BUILD SUCCESS with one test, zero failures, and zero errors.

![Part 2 build success](screenshots/14-part2-build-success.png)

### Executable JAR Startup

The console reports that Topic62Application started on port 8080.

![Part 2 JAR startup](screenshots/15-part2-jar-startup.png)

### Executable JAR Orders

The packaged application displays the orders after login.

![Part 2 JAR orders](screenshots/16-part2-jar-orders.png)

## Part 3: Protected REST Endpoints

The replacement screenshots show Basic Auth selected in Postman. Password values are masked.

### JSON with Valid Credentials

The JSON request returns HTTP 200 OK and order data.

![Authorized JSON request](screenshots/17-part3-json-authorized.png)

### JSON with Invalid Credentials

The JSON request returns HTTP 401 Unauthorized.

![Unauthorized JSON request](screenshots/18-part3-json-unauthorized.png)

### XML with Valid Credentials

The XML request returns HTTP 200 OK and order data.

![Authorized XML request](screenshots/19-part3-xml-authorized.png)

### XML with Invalid Credentials

The XML request returns HTTP 401 Unauthorized. The error body is displayed as JSON.

![Unauthorized XML request](screenshots/20-part3-xml-unauthorized.png)

## Part 4: GitHub OAuth2

### GitHub Sign-In

GitHub displays the sign-in page for CST-339 Activity.

![GitHub login](screenshots/21-part4-github-login.png)

### Authenticated Response

After authentication, the browser returns to /service/test. The response identifies AQuintero96 as the authenticated GitHub user.

![GitHub authenticated response](screenshots/22-part4-oauth-response.png)

## Testing Boundaries

These screenshots document the demonstrated outcomes. They do not independently prove every security setting or cover all possible requests.

The public endpoint screenshots show successful responses, but do not display enough request information to independently establish the absence of session cookies.

Executable JAR evidence is included for Parts 1 and 2. The Part 3 evidence documents Postman requests, and the Part 4 evidence documents the browser OAuth2 flow.