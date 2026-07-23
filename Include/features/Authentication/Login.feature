@LoginProfile
Feature: Login and Authentication Functionality
  As a registered user
  I want to log in using various conditions
  So that I can ensure the system responds correctly to credentials

  Background:
    Given user is on the login page

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @GlobalVariableLogin @Positive
  Scenario: Successfully log in using credentials from Global Variable
    When user enters valid username
    And user enters valid password
    And user clicks login button
    Then user successfully logs in and views account verification

  @Positive @Redirect
  Scenario: Redirect user to registration page via Create an account link
    When user clicks "Create an account" link
    Then user should be redirected to registration page

  @Positive @Redirect
  Scenario: Redirect user to reset password page via Forgot your password link
    When user clicks "Forgot your password" link
    Then user should be redirected to reset password page

  @Positive @UI
  Scenario: Password characters are masked during input
    When user types in password field
    Then password characters should be masked with dots or stars

  # ==========================================
  # NEGATIVE SCENARIOS
  # ==========================================

  @Negative @LoginValidation
  Scenario Outline: Ensure login fails using invalid credentials
    When user enters account "<account>"
    And user enters password "<password>"
    And user clicks login button
    Then system rejects login access and displays error message

    Examples:
      | condition                             | account            | password      |
      | incorrect account/email               | wrong@email.com    | Laskar123456@ |
      | incorrect password                    | valid@email.com    | WrongPass123! |
      | unregistered account or email         | unknown@email.com  | Laskar123456@ |
      | valid account with empty password     | valid@email.com    |               |
      | empty account with valid password     |                    | Laskar123456@ |
      | account and password fields are empty |                    |               |

  @Negative @FormValidation
  Scenario: Ensure mandatory validation message appears when account field is empty
    When user clicks account field and clears it
    And user moves cursor away
    Then system displays mandatory validation message on account field

  @Negative @FormValidation
  Scenario: Ensure password validation message appears when password does not meet requirements
    When password does not contain a combination of letters, numbers, and special characters
    Then system displays password requirement validation message