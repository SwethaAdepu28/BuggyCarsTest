@Regression
  Feature: Registering to Buggy Cards Rating Page

  Background:
    Given I'm on Registration Page
    When I enter Login "<Login>", First Name "<First Name>", Last Name "<Last Name>", Password "<Password>", Confirm Password "<Confirm Password>"

    Scenario:  Successful Registration
      And I click on register button
      Then successful registration message displays

    Scenario: Duplicate user Registration
      And I click on register button
      Then Error message should display for duplicate

    Scenario: Registration Fields Validations
       And I delete all the entered values
       Then I should see all the error messages displaying for empty fields

    Scenario: Registration password validations
      And I click on register button
      Then password validation error msg displays
