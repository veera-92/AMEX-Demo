Feature: Login functionality
  As a user of the practice test automation site
  I want to log in with different credentials
  So that I can verify successful logins and error messages

  Background:
    Given I am on the login page

  Scenario: Positive LogIn test
    When I enter username "student" and password "Password123"
    And I click the submit button
    Then I should be redirected to the logged-in successfully page
    And the page should contain "Congratulations"
    And the "Log out" button should be displayed

  Scenario: Negative username test
    When I enter username "incorrectUser" and password "Password123"
    And I click the submit button
    Then an error message should be displayed
    And the error message text should be "Your username is invalid!"

  Scenario: Negative password test
    When I enter username "student" and password "incorrectPassword"
    And I click the submit button
    Then an error message should be displayed
    And the error message text should be "Your password is invalid!"
    
