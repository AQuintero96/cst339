# Activity 4: Analysis and Planning

[Activity Overview](README.md) | [Design and Setup](design.md) | [Testing](test.md)

## Purpose

This activity replaces the orders application's hardcoded list with data from MySQL. The three projects show different ways to connect the business layer to the database while keeping the existing login form and orders page.

## Work Completed

| Step | Work | Result |
| --- | --- | --- |
| 1 | Import the supplied SQL script | Created the cst339 database and populated ORDERS with 11 records |
| 2 | Configure database access | Connected using a dedicated activity account and an environment variable for its password |
| 3 | Build topic4-1 | Read orders using JdbcTemplate and SqlRowSet |
| 4 | Build topic4-2 | Read orders through a Spring Data JDBC repository |
| 5 | Build topic4-3 | Use a custom repository SELECT query and a JDBC INSERT statement |
| 6 | Package and run the application | Built cst339activity.jar and verified its orders page |

## Technical Approach

### Part 1: Spring JDBC

The controller calls the business service, which requests orders from the data service.

OrdersDataService executes a SELECT statement through JdbcTemplate. It loops through the returned rows and creates an OrderModel for each record.

Its create method uses an INSERT statement with bound parameters.

### Part 2: Spring Data JDBC

OrderEntity maps the database columns to Java fields. OrdersRepository extends CrudRepository, allowing Spring Data JDBC to provide standard persistence operations.

The data service retrieves entities from the repository. The business service converts those entities into OrderModel objects before returning them to the controller. This keeps database mapping annotations out of the presentation model.

OrderRowMapper was also created as directed by the guide. The default repository performs its own mapping, so this mapper is not used by the current findAll operation.

### Part 3: Custom SQL

The repository's findAll method uses an explicit SELECT statement through @Query.

The data service's create method uses JdbcTemplate and an INSERT statement instead of calling repository.save. This changes the service's insertion path; it does not override the repository's save method itself.

## Implementation Decisions

| Decision | Reason |
| --- | --- |
| Separate projects for each part | Preserves each approach for comparison |
| Reuse the same ORDERS table | Keeps the data consistent across demonstrations |
| Generic DataAccessInterface | Defines a common set of data operations |
| Constructor injection in data services | Makes their database and repository dependencies explicit |
| Separate OrderEntity and OrderModel | Keeps persistence details separate from the objects used by the controller and views |
| Bound INSERT parameters | Passes submitted values separately from the SQL statement |
| DB_PASSWORD environment variable | Keeps the database password out of committed application properties |
| Retain the guide's float price type | Keeps the Java model aligned with the supplied activity schema |

## Problem Encountered

The first connection attempt used a blank password for root. MySQL rejected it, and the orders page displayed no records because the data service caught the exception and returned an empty list.

A separate cst339_app account was created with access to the activity database. Its password was supplied through DB_PASSWORD in Eclipse and later in PowerShell. The application then retrieved the 11 records successfully.

## Scope and Limitations

The demonstrations verified reading orders in all three projects and running the final executable JAR.

The create methods were implemented but were not tested through the login flow. The guide's optional findById, update, and delete exercises were not completed. Their placeholder return values do not demonstrate database operations.

The data services print caught exceptions to the console. As observed during setup, a failed read can therefore appear as an empty orders table. A finished application should distinguish a database failure from a successful query with no results.

[Return to Activity 4](README.md)