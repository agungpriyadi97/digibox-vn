@Registration
Feature: Registration Functionality
  As a new user
  I want to create a new account
  So that I can register and access the system

  Background:
    Given user is on the registration page

  # ==========================================
  # POSITIVE SCENARIO
  # ==========================================

  @Positive @RegistrationSuccess
  Scenario: Successfully register a new account with valid dummy data
    When user enters valid registration details using generated dummy data
    And user clicks register button
    Then system displays registration success message "Việc đăng ký của bạn đã thành công!"
    And system redirects user back to login page

  # ==========================================
  # NEGATIVE SCENARIOS
  # ==========================================

  @Negative @DuplicateRegistration
  Scenario: Ensure registration fails when using an already existing account or email
    When user enters email from GlobalVariable
    And user enters account name from GlobalVariable
    And user enters password from GlobalVariable
    And user clicks register button
    Then system displays duplicate error message "The mobile or email already exist."

  @Negative @InvalidEmailFormat
  Scenario Outline: Ensure registration fails when email format is invalid or empty
    When user enters specific email "<email>"
    And user enters generated account name
    And user enters generated password
    And user clicks register button
    Then system displays invalid email error message "Vui lòng nhập email hợp lệ"

    Examples:
      | email                       |
      | agungpriyadi7252#gmail.com  |
      |                             |

  @Negative @EmptyFields
  Scenario Outline: Ensure registration fails when mandatory fields are empty
    When user enters specific email "<email>"
    And user enters specific account name "<account>"
    And user enters specific password "<password>"
    And user clicks register button
    Then system displays mandatory error message "Bắt buộc"

    Examples:
      | email | account | password |
      | valid | valid   |          |
      | valid |         | valid    |

  @Negative @InvalidPasswordRequirement
  Scenario: Ensure registration fails when password does not meet requirements
    When user enters generated email
    And user enters generated account name
    And user enters specific password "A@"
    And user clicks register button
    Then system displays password requirement error message "ký tự đặc biệt"

  @Negative @WhitespaceAccount
  Scenario: Ensure registration fails when account contains only whitespace
    When user enters generated email
    And user enters account name with spaces
    And user enters generated password
    And user clicks register button
    Then system displays empty account parameter error message "Parameter (account) cannot be empty"