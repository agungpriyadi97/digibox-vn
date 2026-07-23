@Cart
Feature: Shopping Cart Functionality
  As a user
  I want to manage items in my shopping cart
  So that I can review and adjust my order before checkout

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @AddToCartGuest
  Scenario: Ensure guest user can add product to cart
    Given user navigates to PDP page "https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023"
    When user clicks add to cart button
    Then system displays success toast notification
    And user opens cart page

  @Positive @AddToCartHeaderBadge
  Scenario: Ensure total cart quantity on header icon is displayed correctly
    Given user is logged in
    And user navigates to PDP page "https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023"
    When user clicks add to cart button
    Then system displays success toast notification
    And cart count badge on header icon should be greater than or equal to 1
    And user opens cart page

  @Positive @UpdateCartQuantity
  Scenario: Ensure user can update product quantity in shopping cart
    Given user is logged in
    And user ensures cart is not empty by adding product if needed
    When user opens cart page
    And user clicks increase quantity button
    Then product quantity in cart should increase by 1
    And cart badge count on header should be updated

  @Positive @RemoveProductCart
  Scenario: Ensure user can remove product from shopping cart
    Given user is logged in
    And user navigates to PDP page "https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023"
    And user clicks add to cart button
    And user opens cart page
    When user clicks delete item button on cart page
    And user confirms item deletion on pop-up
    Then system displays empty cart message