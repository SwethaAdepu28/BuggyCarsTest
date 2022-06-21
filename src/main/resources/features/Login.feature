@Regression
Feature: Login to Buggy cars rating

  Scenario Outline: Valid user login
    Given I'm on Buggy Cars Rating home page
    When I enter username "<username>", password "<password>"
    Then logged In username displays in the navigation bar
    Examples:
      | username   | password   |
      | cde        | cde        |


  Scenario: In valid Login
    Given I'm on Buggy Cars Rating home page
    When I enter username "bcd", password "BcdBcd@123"
    And I click on login button
    Then validation Error message displays



