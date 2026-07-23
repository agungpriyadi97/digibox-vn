@EmailVerification
Feature: Email Verification Functionality
  As a user who forgot their password
  I want to receive a verification code via email
  So that I can verify my identity and reset my password successfully

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @ForgotPasswordEmail
  Scenario: Ensure forgot password verification email is received and password can be reset
    Given user navigates to Forgot Password page
    When user requests verification code for email "tesqa@mailinator.com"
    And user retrieves verification code from Mailinator inbox
    And user submits verification code and new password "Laskar1234567@"
    Then system displays reset password success message