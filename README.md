# API Price Query Service

> :information_source:
> This project uses hexagonal, DDD, message buses and BDD.
> 
> To have an overview about the architecture read:
>
> [Overview on Project Architecture Document](doc/architecture.md)

## About

The goal of this API is to provide the active product price on a given date.

## Functional Requirements

[Complete problem statement (Spanish)](doc/problem_statement.md)

### 1. Query the price for a product on a given date
    
![Service requirements](doc/images/service_requirements.png "Service requirements")

*Acceptance Test*
```gherkin
Feature:
  As a user,
  I want to query for a product on a specific date,
  so I can find out the current selling price at that time.

  Background:
    Given the following prices exists:
      | brand_id | start_date          | end_date            | price_list | product_id | priority | price | curr |
      | 1        | 2020-06-14T00:00:00 | 2020-12-31T23:59:59 | 1          | 35455      | 0        | 35.50 | EUR  |
      | 1        | 2020-06-14T15:00:00 | 2020-06-14T18:30:00 | 2          | 35455      | 1        | 25.45 | EUR  |
      | 1        | 2020-06-15T00:00:00 | 2020-06-15T11:00:00 | 3          | 35455      | 1        | 30.50 | EUR  |
      | 1        | 2020-06-15T16:00:00 | 2020-12-31T23:59:59 | 4          | 35455      | 1        | 38.95 | EUR  |

  Scenario Outline: Query the price for product:<productId>, brand: <brandId> on <date>
    When query the price for product <productId> and brand <brandId> on date <date>
    Then the price is <expectedPrice>

    Examples:
      | productId | brandId | date                  | expectedPrice |
      | 35455     | 1       | "2020-06-14T10:00:00" | 35.50         |
      | 35455     | 1       | "2020-06-14T16:00:00" | 25.45         |
      | 35455     | 1       | "2020-06-14T21:00:00" | 35.50         |
      | 35455     | 1       | "2020-06-15T10:00:00" | 30.50         |
      | 35455     | 1       | "2020-06-16T21:00:00" | 38.95         |
```


