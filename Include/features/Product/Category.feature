@Category @Product
Feature: Product Category Navigation
  As a user
  I want to browse through various product category menus and submenus
  So that I can explore all available product lines including Mobile Phones, iPad, Mac, Watch, Accessories, Audio, TV, Promo, Installment Plans, and Learn More

  Background:
    Given user is on the home page for category navigation

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @MobilePhonesCategory
  Scenario: Ensure Mobile Phones menu is displayed and submenus are accessible
    When user hovers over Mobile Phones category menu
    Then user navigates through Mobile Phones submenus "iPhone XR", "iPhone SE", "iPhone 12", "Discover", and "Demo Info"

  @Positive @IpadCategory
  Scenario: Ensure Ipad menu is displayed and submenus are accessible
    When user hovers over iPad category menu
    Then user navigates through iPad submenus "iPad mini 5th", "iPad mini 6th", and "Discover"

  @Positive @MacCategory
  Scenario: Ensure Mac menu is displayed and submenus are accessible
    When user hovers over Mac category menu
    Then user navigates through Mac submenus "MacBook Air 2020", "MacBook Air 2017", and "Discover Mac"

  @Positive @WatchCategory
  Scenario: Ensure Watch menu is displayed and submenus are accessible
    When user hovers over Watch category menu
    Then user navigates through Watch submenus "Series 3", "Nike Series 4", and "Nike Series 5"

  @Positive @AccessoriesCategory
  Scenario: Ensure Accessories menu is displayed and submenus are accessible
    When user hovers over Accessories category menu
    Then user navigates through Accessories submenus "USB-C Cable", "AirPods Pro MagSafe", "Discover Accessories", and "iPhone Case"

  @Positive @AudioCategory
  Scenario: Ensure Audio menu is displayed
    When user clicks Audio category menu
    Then system successfully navigates to Audio category page

  @Positive @TVCategory
  Scenario: Ensure TV menu is displayed and submenu is accessible
    When user hovers over TV category menu
    Then user clicks Apple TV 4th Generation submenu

  @Positive @PromoCategory
  Scenario: Ensure Promo menu is displayed and submenus are accessible
    When user hovers over Promo category menu
    Then user navigates through Promo submenus "Flash Sale", "Pre Order", "Pre Test", and "Khuyen Mai Tet 2026"

  @Positive @InstallmentPlansCategory
  Scenario: Ensure Installment Plans menu is displayed
    When user clicks Installment Plans category menu
    Then system successfully navigates to Installment Plans page

  @Positive @LearnMoreCategory
  Scenario: Ensure Learn More menu is displayed and submenu is accessible
    When user hovers over Learn More category menu
    Then user clicks iPhone Learn More submenu