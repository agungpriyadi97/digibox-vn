@ForgotPassword
Feature: Forgot Password Functionality
  As a registered user
  I want to request a verification code and reset my password
  So that I can regain access to my account

  Background:
    Given user is on the reset password page

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @ForgotPasswordCode
  Scenario: Successfully send verification code to a registered email
    When user enters registered email
    And user clicks send verification code button
    Then system successfully sends verification code and displays success message

  @Positive @ResetPasswordSuccess
  Scenario: Successfully reset password using a valid verification code
    When user enters registered email
    And user clicks send verification code button
    And user enters verification code "123456"
    And user enters new password "Laskar1234567@"
    And user clicks reset password button
    Then system successfully updates user password

  # ==========================================
  # NEGATIVE SCENARIOS
  # ==========================================

  @Negative @UnregisteredEmail
  Scenario: Ensure forgot password fails using unregistered email
    When user enters email "agungsiapayah@gmail.com"
    And user clicks send verification code button
    Then system displays unregistered email error message

  @Negative @InvalidVerificationCode
  Scenario: Ensure forgot password fails using incorrect verification code
    When user enters registered email
    And user clicks send verification code button
    And user enters verification code "123456"
    And user enters new password "Laskar1234567@"
    And user clicks reset password button
    Then system displays invalid verification code error message

  @Negative @EmptyMandatoryFields
  Scenario: Ensure change password button cannot be used when mandatory fields are empty
    When user clears email, verification code, and new password fields
    And user clicks reset password button
    Then system displays mandatory field error message