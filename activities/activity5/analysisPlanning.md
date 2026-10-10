# Activity 5: Analysis and Planning

[Activity Overview](README.md) | [Design and Setup](design.md) | [Testing](test.md)

## Objective

Replace the orders application's relational persistence with MongoDB document storage while retaining the existing controllers, shared Thymeleaf layout, and JSON/XML responses. Add a repository query that retrieves an order by its document identifier.

## Completed Tasks

| Task | Outcome |
| --- | --- |
| Prepare the project | Copied topic4-2 into topic5-1 and updated the project identity |
| Configure Atlas | Created CST339Cluster and the cst339.orders collection |
| Prepare initial data | Inserted five order documents |
| Replace JDBC persistence | Added Spring Data MongoDB and removed the JDBC dependencies and row mapper |
| Update identifiers | Changed entity and presentation-model identifiers to String |
| Read documents | Displayed Atlas orders in the application and REST responses |
| Verify saving | Saved order 1006 through the data service and read it back |
| Add identifier query | Added getOrderById through the repository, data service, business service, and REST controller |
| Check missing records | Requested an absent identifier and observed the not-found response |
| Package and execute | Built the application with Maven and ran the executable JAR from PowerShell |
| Document results | Collected screenshots and organized the report into separate pages |

## Technical Approach

The application keeps its layered structure. Controllers request presentation models from the business service. The business service requests entities from the data service and converts them into OrderModel objects. The data service delegates persistence operations to OrdersRepository.

OrdersRepository extends MongoRepository<OrderEntity, String>. Spring Data supplies the standard persistence operations and derives the getOrderById query from the method name.

## Key Decisions

| Decision | Reason |
| --- | --- |
| Use MongoDB Atlas | Provides the MongoDB deployment used for the activity |
| Keep credentials in MONGODB_URI | Avoids placing the private connection string in source files |
| Use String identifiers | Represents MongoDB document identifiers in the Java application |
| Retain OrderEntity and OrderModel separately | Keeps persistence annotations out of the presentation model |
| Enable automatic index creation | Applies the unique indexes declared on orderNo and productName |
| Use a save-test profile | Makes the save verification explicit instead of running it during every normal startup |
| Return a generic lookup failure message | Avoids exposing database connection details in REST responses |

The save verification checks for order 1006 before creating it. It then reads the collection again and checks the saved identifier, product name, price, and quantity.

## Limitations

- Login remains a demonstration that accepts nonempty credentials. It does not authenticate against MongoDB.
- Update and delete operations are not implemented.
- The unique product-name index follows the activity design, but would restrict repeated products in a real order system.
- Prices retain the activity's float representation in Java. A production financial application should use decimal arithmetic.
- Atlas access requires an available network connection, valid database credentials, and an allowed client IP address.
- Concurrent save-verification runs and database outage behavior were not tested.

## Completion Summary

The demonstrated functionality includes document retrieval, saving and reading back an order, JSON/XML responses, identifier lookup, and execution from a packaged JAR. Detailed evidence and the limits of the testing appear in [Testing and Screenshots](test.md).