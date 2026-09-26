# Activity 3: Orders REST API Design

- **Author:** Alex Quintero
- **Course:** CST-339 Programming in Java III
- **Date:** September 26, 2026
- **Version:** 1.0
- **Project:** topic3-2

## Overview

The Orders REST API returns the sample orders used by the application. It provides two GET endpoints: one returns JSON, and the other returns XML.

Both endpoints call the same orders business service. The data is a hard-coded list, so these requests do not read from a database or change any records.

## Base URL

```text
http://localhost:8080
```

The application must be running locally before sending requests.

## Endpoints

| Method | Path | Response Format | Purpose |
| --- | --- | --- | --- |
| GET | `/service/getjson` | JSON | Return the sample orders as an array |
| GET | `/service/getxml` | XML | Return the sample orders inside an orders element |

Neither endpoint requires authentication, query parameters, or a request body.

## Get Orders as JSON

### Request

**Method:** `GET`

**URL:**

```text
http://localhost:8080/service/getjson
```

An optional Accept header can specify the expected format:

```http
Accept: application/json
```

### Successful Response

**Status:** `200 OK`

**Content-Type:** `application/json`

The response body is an array containing four order objects.

```json
[
  {
    "id": 1,
    "orderNo": "1001",
    "productName": "Notebook",
    "price": 4.99,
    "quantity": 3
  },
  {
    "id": 2,
    "orderNo": "1002",
    "productName": "Pen Set",
    "price": 7.5,
    "quantity": 2
  },
  {
    "id": 3,
    "orderNo": "1003",
    "productName": "Desk Organizer",
    "price": 15.99,
    "quantity": 1
  },
  {
    "id": 4,
    "orderNo": "1004",
    "productName": "Folder",
    "price": 2.25,
    "quantity": 5
  }
]
```

JSON represents the Pen Set price as `7.5`. This has the same numeric value as `7.50`; display formatting is handled separately from the API data.

## Get Orders as XML

### Request

**Method:** `GET`

**URL:**

```text
http://localhost:8080/service/getxml
```

An optional Accept header can specify the expected format:

```http
Accept: application/xml
```

### Successful Response

**Status:** `200 OK`

**Content-Type:** `application/xml`

The response has an `orders` root element. Each order appears inside an `order` element.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<orders>
    <order>
        <id>1</id>
        <orderNo>1001</orderNo>
        <price>4.99</price>
        <productName>Notebook</productName>
        <quantity>3</quantity>
    </order>
    <order>
        <id>2</id>
        <orderNo>1002</orderNo>
        <price>7.5</price>
        <productName>Pen Set</productName>
        <quantity>2</quantity>
    </order>
    <order>
        <id>3</id>
        <orderNo>1003</orderNo>
        <price>15.99</price>
        <productName>Desk Organizer</productName>
        <quantity>1</quantity>
    </order>
    <order>
        <id>4</id>
        <orderNo>1004</orderNo>
        <price>2.25</price>
        <productName>Folder</productName>
        <quantity>5</quantity>
    </order>
</orders>
```

`OrderList` provides the XML root element and wraps the list returned by the business service.

## Response Fields

Both formats contain the same order information.

| Field | Java Type | Meaning | Example |
| --- | --- | --- | --- |
| `id` | Long | Order identifier | `1` |
| `orderNo` | String | Order number | `"1001"` |
| `productName` | String | Name of the ordered product | `"Notebook"` |
| `price` | float | Price per item | `4.99` |
| `quantity` | int | Number of items ordered | `3` |

The JSON endpoint returns the list directly. The XML endpoint wraps the list because the document needs a single root element.

## Implementation

| Class | Responsibility |
| --- | --- |
| `OrdersRestService` | Maps the two GET endpoints and returns response data |
| `OrdersBusinessServiceInterface` | Defines the service operations |
| `OrdersBusinessService` | Supplies the sample order list |
| `OrderModel` | Holds the values for one order |
| `OrderList` | Wraps the orders for XML output |
| `SpringConfig` | Registers the orders service as a singleton bean |

The REST controller receives the business service through dependency injection. Both endpoint methods call `getOrders()`, so the sample data stays in the business service instead of being duplicated in the controller.

## Response Status and Error Handling

Both tested requests returned `200 OK`.

The controller does not define custom error responses. Unsupported paths, methods, or requested response formats are handled by Spring MVC. Those error cases have not been tested as part of the evidence shown here.

There are no request fields to validate because these endpoints accept no input parameters or request body.

## Testing

The endpoints were opened in the browser and sent as GET requests in Postman.

| Check | Result | Evidence |
| --- | --- | --- |
| JSON in the browser | Four orders displayed as formatted JSON | [Browser JSON](screenshots/11-rest-json-browser.png) |
| XML in the browser | Four order elements displayed under the orders root | [Browser XML](screenshots/12-rest-xml-browser.png) |
| JSON in Postman | 200 OK with a formatted JSON response | [Postman JSON](screenshots/13-rest-json-postman.png) |
| XML in Postman | 200 OK with a formatted XML response | [Postman XML](screenshots/14-rest-xml-postman.png) |

The Postman collection is named **CST-339 Activity 3** and contains:

- **Get Orders JSON**
- **Get Orders XML**

These were manual request-and-response checks. No automated Postman assertions are claimed.

## Current Limits

- The API returns sample data rather than database records.
- Only read operations are included.
- There is no authentication.
- Filtering, sorting, and pagination are not implemented.
- The endpoint names follow the Activity 3 guide.
- The service remains in singleton scope after the scope demonstrations.

## References

- CST-339 Activity 3 Guide, supplied as `CST-339-RS-Activity3Guide.pdf`.
- CST-339 REST API Design Template 1, supplied as `CST-339-RS-REST_API_DesignTemplate1.docx`.