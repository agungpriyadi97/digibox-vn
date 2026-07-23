@ExternalIntegration
Feature: External Integration Functionality
  As a user
  I want to interact with external third-party integrations such as Zalo Chat and ZaloPay Gateway
  So that I can communicate with support or complete payments seamlessly

  # ==========================================
  # ZALO CHAT INTEGRATION SCENARIOS
  # ==========================================

  @Positive @ZaloChat @Navbar
  Scenario: Verify user can click Zalo chat button from navbar and open Zalo link in new tab
    Given user is on home page
    When user clicks Zalo chat button in navbar
    Then system opens new tab with Zalo URL containing "https://zalo.me/4480413680347660968"

  @Positive @ZaloChat @PDP
  Scenario: Ensure user can open Zalo chat from product page
    Given user navigates to PDP page "https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023"
    When user clicks Zalo chat button on product page
    Then system opens new tab and verifies Zalo URL matches "https://zalo.me/"

  # ==========================================
  # ZALOPAY GATEWAY INTEGRATION SCENARIOS
  # ==========================================

  @Positive @ZaloPayGateway
  Scenario: Ensure ZaloPay payment option is displayed and redirects to gateway
    Given user is logged in
    And user ensures product is in cart and navigates to checkout page
    When user selects ZaloPay payment method
    And user accepts checkout terms and conditions
    And user proceeds to pay with ZaloPay
    Then system redirects to ZaloPay gateway and verifies merchant logo and transaction details

  @Positive @ZaloPayInterface
  Scenario: Ensure ZaloPay interface displays all available payment methods
    Given user is logged in
    And user ensures product is in cart and navigates to checkout page
    When user selects ZaloPay payment method
    And user accepts checkout terms and conditions
    And user proceeds to pay with ZaloPay
    Then user verifies ZaloPay app, international card, VietQR, and domestic card options