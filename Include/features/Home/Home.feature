@Home
Feature: Home Page Functionality
  As a user
  I want to interact with elements on the home page such as Order Tracking and Search
  So that I can easily track my orders and search for products

  Background:
    Given user is on the home page

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @OrderTrackingMenu
  Scenario: Ensure Order Tracking menu is displayed
    When user hovers over the order tracking icon in the header
    Then order tracking menu should be visible

  @Positive @OrderTrackingPage
  Scenario: Ensure Order Tracking page is accessible
    When user hovers over and clicks the order tracking icon in the header
    Then system navigates to the order tracking page successfully

  @Positive @SearchFunctionality
  Scenario: Ensure search menu is displayed and functional
    When user clicks the search icon in the header
    And user enters search keyword "iphone" and presses enter
    Then system displays search results page successfully