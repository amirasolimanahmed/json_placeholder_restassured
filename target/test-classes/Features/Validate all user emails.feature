
Feature: Validate user e-mail address
  Scenario: Validate all returned e-mails
    Given The user sets json placeholder API
    When The user gets all user posts by user ID
    Then The user validates all e-mails
