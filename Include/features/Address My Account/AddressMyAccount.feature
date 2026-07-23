@AddressManagement @AddressMyAccount
Feature: Address Management in My Account
  As an authenticated user
  I want to manage my shipping and billing addresses
  So that I can easily select my preferred addresses during order checkout

  Background:
    Given user is logged in and navigates to My Address page

  # ==========================================
  # SHIPPING ADDRESS SCENARIOS
  # ==========================================

 @Positive @ShippingAddress
  Scenario: Successfully add a new shipping address
    When user clicks add new shipping address button
    And user fills shipping address form with random valid details
    And user selects province "TP. Hồ Chí Minh" and ward "Xã Phước Hòa"
    And user clicks save shipping address button
    Then system successfully saves the new shipping address

  @Positive @ShippingAddress
  Scenario: Successfully edit the last shipping address
    When user edits the last shipping address with random data
    Then system successfully updates the shipping address

  @Positive @ShippingAddress
  Scenario: Successfully delete the last shipping address
    When user deletes the last shipping address
    Then system successfully removes the shipping address

  # ==========================================
  # BILLING ADDRESS SCENARIOS
  # ==========================================

  @Positive @BillingAddress
  Scenario: Successfully add a new billing address
    When user clicks add new billing address button
    And user fills billing address form with random valid details
    And user selects billing province "TP. Đà Nẵng" and ward "Phường Hải Vân"
    And user clicks save billing address button
    Then system successfully saves the new billing address

  @Positive @BillingAddress
  Scenario: Successfully delete the last billing address
    When user deletes the last billing address
    Then system successfully removes the billing address