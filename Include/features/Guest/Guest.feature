@Guest
Feature: Guest User Functionality
  As an unauthenticated guest user
  I want to explore products, manage guest cart/checkout, and track orders
  So that I can shop without needing to create an account while ensuring profile pages remain restricted

  # ==========================================
  # POSITIVE SCENARIOS
  # ==========================================

  @Positive @ExploreWebsite
  Scenario: Ensure user can explore the website as a guest
    Given user is on home page as a guest
    When guest user browses through category menus
    Then system successfully displays all product category menus to the guest

  @Positive @AddCompanyAddress
  Scenario: Make sure guest users can add company addresses during checkout
    Given guest user adds a product to cart and proceeds to checkout email page
    When guest user enters guest email "agung.priyadi96@gmail.com" and continues
    And guest user selects company address option and fills company details
      | companyName | taxCode    | firstName | lastName | mobile     | address                      | zip   |
      | Công ty TNHH ABC | 0123456789 | Agung     | Priyadi  | 0987654321 | No. 45, Jalan Dinh Tien Hoang | 70000 |
    Then guest user saves the company delivery address successfully

  @Positive @GuestCheckout
  Scenario: Ensure guest user can checkout order successfully
    Given guest user adds a product to cart and proceeds to checkout email page
    When guest user enters guest email "agung.priyadi96@gmail.com" and continues
    And guest user completes individual delivery address form
    And guest user selects OnePay ATM payment method and accepts terms
    And guest user submits OnePay card details and completes payment
    Then system processes guest order successfully and displays order confirmation

  # ==========================================
  # NEGATIVE SCENARIOS
  # ==========================================

  @Negative @RestrictedAccess
  Scenario: Ensure guest user cannot access profile page
    Given user is on home page as a guest
    When guest user clicks account icon in header
    Then system redirects guest user to login page or denies profile access

  @Negative @InvalidGuestEmail
  Scenario: Ensure guest user input email invalid
    Given guest user adds a product to cart and proceeds to checkout email page
    When guest user enters invalid guest email "agun" and continues
    Then system displays invalid email error message on guest checkout page

  @Negative @OrderTracking
  Scenario: Ensure guest user receives error message when entering an invalid verification code on order tracking page
    Given guest user navigates to order tracking page
    When guest user requests tracking code for email "agung.priyadi96@gmail.com"
    And guest user submits invalid verification code "123456"
    Then system displays verification code error message on tracking page