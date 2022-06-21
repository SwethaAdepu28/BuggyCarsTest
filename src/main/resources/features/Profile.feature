@Regression
Feature: Update profile

  Scenario: Update profile
    Given Logged In user
    And I click on profile link
    When I enter Gender "Female", Age "25", Address "Auckland", phone "1234", hobby "Working"
    Then profile updated success message