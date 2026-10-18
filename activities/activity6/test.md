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

Click a screenshot to view the full image.

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 1. Home page | The public home page displayed a link to log in. | [<img src="screenshots/01-part1-home.png" alt="Part 1 home page" width="220">](screenshots/01-part1-home.png) |
| 2. Invalid login | The login page displayed an invalid username or password message. | [<img src="screenshots/02-part1-invalid-login.png" alt="Part 1 invalid login" width="220">](screenshots/02-part1-invalid-login.png) |
| 3. Authenticated orders | The orders page identified the signed-in test account and displayed six orders. | [<img src="screenshots/03-part1-orders.png" alt="Part 1 authenticated orders" width="220">](screenshots/03-part1-orders.png) |
| 4. JSON response | The JSON endpoint returned the six stored orders, including their document identifiers. | [<img src="screenshots/04-part1-public-json.png" alt="Part 1 JSON response" width="220">](screenshots/04-part1-public-json.png) |
| 5. XML response | The XML endpoint returned the six stored orders. | [<img src="screenshots/05-part1-public-xml.png" alt="Part 1 XML response" width="220">](screenshots/05-part1-public-xml.png) |
| 6. Maven build | The Maven build succeeded after the MongoDB connection configuration was corrected. | [<img src="screenshots/06-part1-build-success.png" alt="Part 1 Maven build success" width="220">](screenshots/06-part1-build-success.png) |
| 7. Executable JAR startup | The packaged application was launched outside Eclipse. | [<img src="screenshots/07-part1-jar-startup.png" alt="Part 1 executable JAR startup" width="220">](screenshots/07-part1-jar-startup.png) |
| 8. Executable JAR orders | The running JAR served the authenticated orders page with six orders. | [<img src="screenshots/08-part1-jar-orders.png" alt="Part 1 executable JAR orders" width="220">](screenshots/08-part1-jar-orders.png) |

## Part 2: MongoDB Authentication

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 9. Stored application account | The users collection contained the test account with a BCrypt password hash. | [<img src="screenshots/09-part2-stored-user.png" alt="MongoDB application account" width="220">](screenshots/09-part2-stored-user.png) |
| 10. Invalid login | Invalid credentials produced a login error. | [<img src="screenshots/10-part2-invalid-login.png" alt="Part 2 invalid login" width="220">](screenshots/10-part2-invalid-login.png) |
| 11. Authenticated orders | Successful authentication displayed six orders under the test account. | [<img src="screenshots/11-part2-orders.png" alt="Part 2 authenticated orders" width="220">](screenshots/11-part2-orders.png) |
| 12. JSON response | The JSON endpoint returned the six stored orders. | [<img src="screenshots/12-part2-public-json.png" alt="Part 2 JSON response" width="220">](screenshots/12-part2-public-json.png) |
| 13. XML response | The XML endpoint returned the six stored orders. | [<img src="screenshots/13-part2-public-xml.png" alt="Part 2 XML response" width="220">](screenshots/13-part2-public-xml.png) |
| 14. Maven build | Maven reported BUILD SUCCESS with one test, zero failures, and zero errors. | [<img src="screenshots/14-part2-build-success.png" alt="Part 2 Maven build success" width="220">](screenshots/14-part2-build-success.png) |
| 15. Executable JAR startup | The console reported that Topic62Application started on port 8080. | [<img src="screenshots/15-part2-jar-startup.png" alt="Part 2 executable JAR startup" width="220">](screenshots/15-part2-jar-startup.png) |
| 16. Executable JAR orders | The packaged application displayed six orders after login. | [<img src="screenshots/16-part2-jar-orders.png" alt="Part 2 executable JAR orders" width="220">](screenshots/16-part2-jar-orders.png) |

## Part 3: Protected REST Endpoints

The screenshots show Basic Auth selected in Postman. Password values are masked.

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 17. JSON with valid credentials | The request to /service/getjson returned HTTP 200 OK and order data in JSON format. | [<img src="screenshots/17-part3-json-authorized.png" alt="JSON request with valid credentials" width="220">](screenshots/17-part3-json-authorized.png) |
| 18. JSON with invalid credentials | The request to /service/getjson returned HTTP 401 Unauthorized. | [<img src="screenshots/18-part3-json-unauthorized.png" alt="JSON request with invalid credentials" width="220">](screenshots/18-part3-json-unauthorized.png) |
| 19. XML with valid credentials | The request to /service/getxml returned HTTP 200 OK and order data in XML format. | [<img src="screenshots/19-part3-xml-authorized.png" alt="XML request with valid credentials" width="220">](screenshots/19-part3-xml-authorized.png) |
| 20. XML with invalid credentials | The request to /service/getxml returned HTTP 401 Unauthorized. The error body was displayed as JSON. | [<img src="screenshots/20-part3-xml-unauthorized.png" alt="XML request with invalid credentials" width="220">](screenshots/20-part3-xml-unauthorized.png) |

## Part 4: GitHub OAuth2

| Test | Observed Result | Screenshot |
| --- | --- | --- |
| 21. GitHub sign-in | GitHub displayed the sign-in page for CST-339 Activity. | [<img src="screenshots/21-part4-github-login.png" alt="GitHub sign-in page" width="220">](screenshots/21-part4-github-login.png) |
| 22. Authenticated response | The browser returned to /service/test and displayed a greeting identifying AQuintero96 as the authenticated GitHub user. | [<img src="screenshots/22-part4-oauth-response.png" alt="Successful GitHub authentication response" width="220">](screenshots/22-part4-oauth-response.png) |

## Testing Boundaries

These screenshots document the demonstrated outcomes. They do not independently prove every security setting or cover all possible requests.

The public endpoint screenshots show successful responses, but do not display enough request information to independently establish the absence of session cookies.

Executable JAR evidence is included for Parts 1 and 2. The Part 3 evidence documents Postman requests, and the Part 4 evidence documents the browser OAuth2 flow.