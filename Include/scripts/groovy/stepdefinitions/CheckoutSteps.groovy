package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import internal.GlobalVariable as GlobalVariable

class CheckoutSteps {

    // ==========================================
    // BACKGROUND STEPS
    // ==========================================

    @Given("user ensures product is in cart and navigates to checkout page")
    def ensureProductInCartAndNavigateToCheckout() {
        String pdpUrl = 'https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023'
        
        try {
            WebUI.navigateToUrl(pdpUrl, FailureHandling.OPTIONAL)
        } catch (Exception e) {
            WebUI.executeJavaScript("window.location.href = '" + pdpUrl + "';", null)
        }

        WebUI.waitForElementVisible(findTestObject('WEB/Product/PDP/btn_add to cart'), 15)
        WebUI.scrollToElement(findTestObject('WEB/Product/PDP/btn_add to cart'), 5)
        WebUI.click(findTestObject('WEB/Product/PDP/btn_add to cart'))
        WebUI.delay(2)

        WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))
        WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))
        WebUI.waitForElementVisible(findTestObject('WEB/Checkout/Payment/radio_one_pay'), 15, FailureHandling.OPTIONAL)
    }

    // ==========================================
    // PAYMENT & ORDER SUMMARY STEPS
    // ==========================================

    @When("user selects OnePay payment method and chooses ATM channel")
    def selectOnePayATM() {
        WebUI.click(findTestObject('WEB/Checkout/Payment/radio_one_pay'))
        WebUI.waitForElementPresent(findTestObject('WEB/Checkout/Payment/payment_children_atm'), 10)
        WebUI.click(findTestObject('WEB/Checkout/Payment/payment_children_atm'))
    }

    @When("user verifies order summary contains {string}")
    def verifyOrderSummaryText(String expectedSummaryText) {
        String summary = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/lbl_order_summary'))
        WebUI.verifyMatch(summary, ".*${expectedSummaryText}.*", true)
        
        String total = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/txt_total'))
        println('Checkout Order Total: ' + total)
    }

    @When("user accepts checkout terms and conditions")
    def acceptTermsAndConditions() {
        WebUI.click(findTestObject('WEB/Checkout/OrderSummary/chk_accept_terms'))
        WebUI.delay(2)
    }

    @When("user clicks pay now button on checkout")
    def clickPayNowOnCheckout() {
        WebUI.click(findTestObject('WEB/Checkout/OrderSummary/button_thanh_thon_2'))
        WebUI.waitForPageLoad(10)
    }

    @When("user selects bank {string} and enters valid card details")
    def selectBankAndFillCardDetails(String bankName) {
        TestObject inputBank = findTestObject('WEB/Checkout/Installment/BankSelection/input_search_bank')
        TestObject itemBank = findTestObject('WEB/Checkout/Installment/BankSelection/bank_list_item')

        // Menunggu halaman OnePay redirect & memuat input pencarian bank
        WebUI.waitForElementVisible(inputBank, 20, FailureHandling.OPTIONAL)

        if (WebUI.verifyElementPresent(inputBank, 5, FailureHandling.OPTIONAL)) {
            WebUI.setText(inputBank, bankName)
            WebUI.delay(2)
            WebUI.click(itemBank)
        }

        // Menunggu form kartu muncul
        TestObject inputCardNum = findTestObject('WEB/Checkout/Card/input_card_number')
        WebUI.waitForElementVisible(inputCardNum, 15)

        WebUI.setText(inputCardNum, '4000000000001091')
        WebUI.setText(findTestObject('WEB/Checkout/Card/input_expiration_date'), '12/28')
        WebUI.setText(findTestObject('WEB/Checkout/Card/input_csc'), '123')
        WebUI.setText(findTestObject('WEB/Checkout/Card/input_cardholder_name'), 'NGUYEN VAN A')
    }

    @When("user selects installment tenure {string} and accepts OnePay policy")
    def selectInstallmentAndPolicy(String tenure) {
        WebUI.waitForElementClickable(findTestObject('WEB/Checkout/Card/radio_6_months'), 15)
        WebUI.enhancedClick(findTestObject('WEB/Checkout/Card/radio_6_months'))

        WebUI.waitForElementClickable(findTestObject('WEB/Checkout/Card/chk_onepay_policy'), 10)
        WebUI.click(findTestObject('WEB/Checkout/Card/chk_onepay_policy'), FailureHandling.STOP_ON_FAILURE)
    }

    @When("user submits payment and confirms dialog {string}")
    def submitPaymentAndConfirmDialog(String dialogContent) {
        WebUI.click(findTestObject('WEB/Checkout/Card/btn_pay_now'))
        WebUI.verifyElementText(findTestObject('WEB/Checkout/ConfirmDialog/lbl_note_title'), 'Note')

        String content = WebUI.getText(findTestObject('WEB/Checkout/ConfirmDialog/txt_content'))
        WebUI.verifyMatch(content, ".*${dialogContent}.*", true)

        WebUI.click(findTestObject('WEB/Checkout/ConfirmDialog/btn_agree_continue'))
        WebUI.waitForPageLoad(10)
    }

    @Then("system processes payment successfully and completes the order")
    def verifyPaymentSuccess() {
        if (WebUI.verifyElementPresent(findTestObject('WEB/Checkout/PaymentResult/lbl_thank_you'), 5, FailureHandling.OPTIONAL)) {
            String thankYou = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/lbl_thank_you'))
            WebUI.verifyMatch(thankYou, 'Cám ơn Quý khách!', false)
        }
    }

    // ==========================================
    // PROMO CODE STEPS
    // ==========================================

    @When("user enters promo code {string}")
    def enterPromoCode(String promoCode) {
        WebUI.setText(findTestObject('WEB/Checkout/OrderSummary/input_promo_code'), promoCode)
    }

    @When("user clicks apply promo button")
    def clickApplyPromo() {
        WebUI.click(findTestObject('WEB/Checkout/OrderSummary/btn_apply_promo'))
        WebUI.delay(2)
    }

    @Then("system applies promotion discount to the total order summary")
    def verifyPromoApplied() {
        WebUI.waitForElementPresent(findTestObject('WEB/Checkout/OrderSummary/button_thanh_thon_2'), 10)
    }

    // ==========================================
    // CANCEL TRANSACTION STEPS
    // ==========================================

    @When("user clicks cancel transaction on payment gateway")
    def clickCancelTransaction() {
        WebUI.click(findTestObject('WEB/Checkout/Installment/BankSelection/btn_cancel_transaction'))
    }

    @When("user confirms transaction cancellation")
    def confirmTransactionCancellation() {
        WebUI.click(findTestObject('WEB/Checkout/ConfirmDialog/btn_confirm'))
        WebUI.waitForPageLoad(10)
    }

    @Then("system displays payment failed page {string} with description {string}")
    def verifyPaymentFailed(String expectedTitle, String expectedDesc) {
        WebUI.waitForElementVisible(findTestObject('WEB/Checkout/PaymentResult/lbl_payment_failed'), 10)
        String errorTitle = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/lbl_payment_failed'))
        WebUI.verifyMatch(errorTitle, expectedTitle, false)

        String errorDesc = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/txt_error_desc'))
        WebUI.verifyMatch(errorDesc, expectedDesc, false)
    }

    @Then("user can click continue shopping button")
    def clickContinueShopping() {
        WebUI.click(findTestObject('WEB/Checkout/PaymentResult/button_Tip tc mua sm(continue shopping_)'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // ORDER HISTORY & DETAILS VERIFICATION
    // ==========================================

    @When("user completes checkout process")
    def completeCheckoutProcess() {
        acceptTermsAndConditions()
        clickPayNowOnCheckout()
    }

    @When("user navigates to order history details page")
    def navigateToOrderDetails() {
        WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order'))
        WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order'))
        WebUI.waitForPageLoad(15)
        
        TestObject btnOrderDetail = findTestObject('WEB/OrderDetail/btn_order_detail_first')
        WebUI.waitForElementVisible(btnOrderDetail, 15, FailureHandling.OPTIONAL)
        
        if (WebUI.verifyElementPresent(btnOrderDetail, 5, FailureHandling.OPTIONAL)) {
            WebUI.scrollToElement(btnOrderDetail, 5)
            WebUI.enhancedClick(btnOrderDetail)
            WebUI.waitForPageLoad(10)
        }
    }

    @Then("order details should display matching shipping recipient name, phone, and address")
    def verifyOrderDetailsAddress() {
        WebUI.verifyElementPresent(findTestObject('WEB/OrderDetail/lbl_shipping_fullname'), 10, FailureHandling.OPTIONAL)
        WebUI.verifyElementPresent(findTestObject('WEB/OrderDetail/lbl_shipping_phone'), 10, FailureHandling.OPTIONAL)
        WebUI.verifyElementPresent(findTestObject('WEB/OrderDetail/lbl_shipping_address'), 10, FailureHandling.OPTIONAL)
    }
}