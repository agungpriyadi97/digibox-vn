@Checkout
Feature: Checkout and Payment Functionality
  As an authenticated user
  I want to process checkout and manage payment options
  So that I can place orders successfully using various payment methods

  Background:
    Given user is logged in
    And user ensures product is in cart and navigates to checkout page

  # ==========================================
  # PAYMENT SCENARIOS
  # ==========================================

  @Positive @CheckoutOrder
  Scenario: Successfully checkout order using OnePay ATM payment method
    When user selects OnePay payment method and chooses ATM channel
    And user verifies order summary contains "Tóm tắt đơn hàng"
    And user accepts checkout terms and conditions
    And user clicks pay now button on checkout
    And user selects bank "ACB" and enters valid card details
    And user selects installment tenure "6 months" and accepts OnePay policy
    And user submits payment and confirms dialog "Cardholder does NOT have to register installment"
    Then system processes payment successfully and completes the order

  @Positive @CheckoutPromoCode
  Scenario: Successfully apply promo code during checkout
    When user enters promo code "DISCOUNT10"
    And user clicks apply promo button
    Then system applies promotion discount to the total order summary

  @Positive @CancelPayment
  Scenario: Ensure checkout handles cancelled transaction gracefully
    When user selects OnePay payment method and chooses ATM channel
    And user accepts checkout terms and conditions
    And user clicks pay now button on checkout
    And user selects bank "ACB" and enters valid card details
    And user clicks cancel transaction on payment gateway
    And user confirms transaction cancellation
    Then system displays payment failed page "Có lỗi xảy ra" with description "Thanh toán không thành côn"
    And user can click continue shopping button

  @Positive @OrderAddressMatch
  Scenario: Ensure delivery address on created order matches selected checkout address
    When user completes checkout process
    And user navigates to order history details page
    Then order details should display matching shipping recipient name, phone, and address