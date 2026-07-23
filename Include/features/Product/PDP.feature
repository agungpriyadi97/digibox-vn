@PDP @Product
Feature: Product Detail Page (PDP) Functionality
  As a user
  I want to search for products and open their detail page
  So that I can verify product details, stock status, price, and descriptions

  Background:
    Given user searches for product "iphone" and selects "iphone 12"

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @AccessPDP
  Scenario: Ensure user can access Product Detail Page (PDP)
    When user clicks product detail button
    Then system successfully displays Product Detail Page

  @Positive @PDPStock
  Scenario: Ensure product stock information is displayed correctly
    When user checks stock status on PDP
    Then system displays stock status "Có sẵn"
    And user clicks product detail button

  @Positive @PDPPrice
  Scenario: Ensure product price is displayed correctly
    When user views product price details
    And user clicks product detail button
    Then product price should be visible and correct

  @Positive @PDPDetails
  Scenario: Ensure product details are displayed correctly
    When user clicks product detail button
    Then full product specification and details should be displayed