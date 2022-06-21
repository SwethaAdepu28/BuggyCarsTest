@Regression
Feature: Vote fore a car

  Scenario: Vote for a car with comment
    Given I'm on the Buggy cars rating page
    And I select a car Model
    When I enter comment
    And I click on vote button
    Then successful vote message displays and author details displays in the grid

  Scenario: Vote for a car without comment
    Given I'm on the Buggy cars rating page
    And I select a car Model
    When I enter comment
    And I click on vote button
    Then successful vote message displays and author details displays in the grid

