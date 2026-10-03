# Activity 4: Spring JDBC

**Author:** Alex Quintero  
**Course:** CST-339  
**Date:** October 3, 2026

## Overview

This activity connects the orders application to MySQL. Three versions demonstrate reading orders with Spring JDBC, using a Spring Data JDBC repository, and writing custom SQL queries.

Each version displays the 11 orders supplied in the activity's database script. The final version was also packaged and run as an executable JAR.

## Report Pages

| Page | Contents |
| --- | --- |
| [Analysis and Planning](analysisPlanning.md) | Activity steps, approach, and implementation decisions |
| [Design and Setup](design.md) | Database configuration, application structure, and build instructions |
| [Testing and Screenshots](test.md) | Results from all three parts, Maven build, and executable JAR |

## Source Code

| Part | Implementation | Source |
| --- | --- | --- |
| 1 | Spring JDBC with JdbcTemplate | [topic4-1](../code/topic4-1/) |
| 2 | Spring Data JDBC with CrudRepository | [topic4-2](../code/topic4-2/) |
| 3 | Custom repository query and SQL insert | [topic4-3](../code/topic4-3/) |

## Results

All three versions displayed database orders. The final Maven build used Java 17 and completed with one test, zero failures, and zero errors. The packaged application, `cst339activity.jar`, started successfully and displayed all 11 orders.

The create methods were implemented but were not exercised by the login demonstration. The optional find-by-ID, update, and delete exercises remain unimplemented.

[Return to the repository front page](../../README.md)