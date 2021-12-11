# Why Serenity Framework with Cucumber


- Serenity BDD provides strong support for different types of automated acceptance testing, including:


- Rich built-in support for web testing with Selenium.


- REST API testing with RestAssured.


- Highly readable, maintainable and scalable automated testing with the Screenplay pattern.

# Feature File 


``` 
Feature: Validate user e-mail address
  Scenario: Validate all returned e-mails
    Given The user sets json placeholder API
    When The user gets all user posts by user ID
    And The user gets all comments by post ID
    Then The user validates all e-mails
```

# How to Run the Test cases 

This inscructions on how to run the Serenity with BDD test cases

## Prerequisites
 
- Cucumber

- Core Serenity dependency

- JUnit Serenity dependency

- The Maven Failsafe plugin

- The Serenity Maven Plugin


## Executing the tests

$ git clone https://github.com/amirasolimanahmed/json_placeholder_restassured.git



$ mvn clean verify

## Generating the reports

$ mvn serenity:aggregate


