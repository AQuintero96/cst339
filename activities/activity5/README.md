# Activity 5: MongoDB

**Author:** Alex Quintero  
**Course:** CST-339  
**Date:** October 10, 2026

## Overview

This activity connects the Spring Boot orders application to MongoDB Atlas using Spring Data MongoDB. It builds on the Spring Data JDBC application from Activity 4.

The application retrieves order documents, displays them through Thymeleaf, and returns JSON and XML responses. A separate verification profile saves an order and reads it back. A derived repository query retrieves an individual order by its MongoDB identifier.

## Report Pages

| Page | Contents |
| --- | --- |
| [Analysis and Planning](analysisPlanning.md) | Approach, completed tasks, decisions, and limitations |
| [Design and Setup](design.md) | Collection structure, application layers, configuration, and execution |
| [Testing and Screenshots](test.md) | Screenshot evidence, observed results, and testing boundaries |

## Source Code

Both parts of this activity are implemented in [topic5-1](../code/topic5-1/).

## Results

The application displayed five initial Atlas orders. The save/read verification added order 1006, bringing the collection to six orders. JSON and XML responses displayed the stored information, and identifier lookups returned the expected order or a not-found message.

Maven completed successfully with one test, zero failures, and zero errors. The executable JAR started outside Eclipse and displayed all six orders.

[Return to the repository front page](../../README.md)