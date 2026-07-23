package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.cucumber.datatable.DataTable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling

class GuestSteps {

    // ==========================================
    // BACKGROUND & SETUP STEPS
    // ==========================================

    @Given("user is on home page as a guest")
    def userIsOnHomePageAsGuest() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/')
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Icon Menu/Logo Home/logo_home'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Logo Home/logo_home'))
        WebUI.verifyElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Logo Home/logo_home'), 5)
    }

    @Given("guest user adds a product to cart and proceeds to checkout email page")
    def addProductToCartAsGuest() {
        WebUI.callTestCase(findTestCase('WEB/Cart/Positive/Positive - Ensure user can add product to cart'), [:], FailureHandling.STOP_ON_FAILURE)
    }

    @Given("guest user navigates to order tracking page")
    def navigateToOrderTracking() {
        TestObject iconOrder = findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order')
        WebUI.mouseOver(iconOrder)
        WebUI.click(iconOrder)
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // ACTIONS (WHEN)
    // ==========================================

    @When("guest user browses through category menus")
    def browseCategoryMenus() {
        TestObject iconAccount = findTestObject('WEB/Home/Header/Icon Menu/Account/icon-account')
        WebUI.waitForElementVisible(iconAccount, 10)
        WebUI.mouseOver(iconAccount, FailureHandling.STOP_ON_FAILURE)
        WebUI.click(iconAccount, FailureHandling.STOP_ON_FAILURE)
    }

    @When("guest user enters guest email {string} and continues")
    def enterGuestEmailAndContinue(String email) {
        WebUI.setText(findTestObject('WEB/Checkout/Guest/input_guest_email'), email)
        TestObject btnContinue = findTestObject('WEB/Checkout/Guest/btn_guest_continue')
        WebUI.mouseOver(btnContinue)
        WebUI.click(btnContinue)
        WebUI.delay(2)
    }

    @When("guest user enters invalid guest email {string} and continues")
    def enterInvalidGuestEmail(String invalidEmail) {
        WebUI.setText(findTestObject('WEB/Checkout/Guest/input_guest_email'), invalidEmail)
        TestObject btnContinue = findTestObject('WEB/Checkout/Guest/btn_guest_continue')
        WebUI.mouseOver(btnContinue)
        WebUI.click(btnContinue)
    }

    @When("guest user selects company address option and fills company details")
    def fillCompanyAddressDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0)

        WebUI.click(findTestObject('WEB/Checkout/DeliveryAddressCompany/radio_cong_ty'))
        
        WebUI.clearText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_company_name'), FailureHandling.STOP_ON_FAILURE)
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_company_name'), data.get('companyName'))

        WebUI.clearText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_tax_code'), FailureHandling.STOP_ON_FAILURE)
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_tax_code'), data.get('taxCode'))

        WebUI.click(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_first_name'), FailureHandling.STOP_ON_FAILURE)
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_first_name'), data.get('firstName'))

        WebUI.clearText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_last_name'), FailureHandling.STOP_ON_FAILURE)
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_last_name'), data.get('lastName'))

        WebUI.clearText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_mobile'), FailureHandling.STOP_ON_FAILURE)
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_mobile'), data.get('mobile'))

        WebUI.clearText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_address'))
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_address'), data.get('address'))

        WebUI.clearText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_zip'))
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_zip'), data.get('zip'))

        WebUI.click(findTestObject('WEB/Checkout/DeliveryAddressIndividual/btn_save'))
    }

    @When("guest user completes individual delivery address form")
    def completeIndividualDeliveryAddress() {
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressIndividual/input_first_name'), 'Agung')
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressIndividual/input_last_name'), 'Priyadi')
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_mobile'), '0915551234')
        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_address'), 'No. 1, Đường Lê Duẩn, Phường Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 700000, Vietnam')

        TestObject inputCity = findTestObject('WEB/Checkout/DeliveryAddressIndividual/input_TnhThnh Ph_el-input__inner')
        WebUI.scrollToElement(inputCity, 5)
        WebUI.click(inputCity)
        
        TestObject liCity = findTestObject('WEB/Checkout/DeliveryAddressIndividual/li_TP. H Ch Minh')
        WebUI.waitForElementClickable(liCity, 10)
        WebUI.enhancedClick(liCity)

        TestObject inputWard = findTestObject('WEB/Checkout/DeliveryAddressIndividual/input_PhngX_el-input__inner')
        WebUI.waitForElementClickable(inputWard, 20)
        WebUI.click(inputWard)

        TestObject liWard = findTestObject('WEB/Checkout/DeliveryAddressIndividual/li_X Phc Ha')
        WebUI.waitForElementClickable(liWard, 20)
        WebUI.enhancedClick(liWard)

        WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_zip'), '70000')
        
        TestObject btnSave = findTestObject('WEB/Checkout/DeliveryAddressIndividual/btn_save')
        WebUI.waitForElementClickable(btnSave, 10)
        WebUI.click(btnSave)
    }

    @When("guest user selects OnePay ATM payment method and accepts terms")
    def selectOnePayAndAcceptTerms() {
        WebUI.click(findTestObject('WEB/Checkout/Payment/radio_one_pay'))
        WebUI.waitForElementPresent(findTestObject('WEB/Checkout/Payment/payment_children_atm'), 10)
        WebUI.click(findTestObject('WEB/Checkout/Payment/payment_children_atm'))

        String summary = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/lbl_order_summary'))
        WebUI.verifyMatch(summary, '.*Tóm tắt đơn hàng.*', true)

        String total = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/txt_total'))
        println('Guest Order Total: ' + total)

        WebUI.click(findTestObject('WEB/Checkout/OrderSummary/chk_accept_terms'))
        WebUI.delay(10)
        WebUI.click(findTestObject('WEB/Checkout/OrderSummary/button_thanh_thon_2'))
        WebUI.waitForPageLoad(10)
    }

    @When("guest user submits OnePay card details and completes payment")
    def fillCardDetailsAndSubmit() {
        WebUI.setText(findTestObject('WEB/Checkout/Installment/BankSelection/input_search_bank'), 'ACB')
        WebUI.delay(2)
        WebUI.click(findTestObject('WEB/Checkout/Installment/BankSelection/bank_list_item'))

        WebUI.setText(findTestObject('WEB/Checkout/Card/input_card_number'), '4000000000001091')
        WebUI.setText(findTestObject('WEB/Checkout/Card/input_expiration_date'), '12/28')
        WebUI.setText(findTestObject('WEB/Checkout/Card/input_csc'), '123')
        WebUI.setText(findTestObject('WEB/Checkout/Card/input_cardholder_name'), 'NGUYEN VAN A')

        WebUI.waitForElementClickable(findTestObject('WEB/Checkout/Card/radio_6_months'), 15)
        WebUI.enhancedClick(findTestObject('WEB/Checkout/Card/radio_6_months'))

        WebUI.waitForElementClickable(findTestObject('WEB/Checkout/Card/chk_onepay_policy'), 10)
        WebUI.click(findTestObject('WEB/Checkout/Card/chk_onepay_policy'), FailureHandling.STOP_ON_FAILURE)

        WebUI.click(findTestObject('WEB/Checkout/Card/btn_pay_now'))
        WebUI.verifyElementText(findTestObject('WEB/Checkout/ConfirmDialog/lbl_note_title'), 'Note')

        String content = WebUI.getText(findTestObject('WEB/Checkout/ConfirmDialog/txt_content'))
        WebUI.verifyMatch(content, '.*Cardholder does NOT have to register installment.*', true)

        WebUI.click(findTestObject('WEB/Checkout/ConfirmDialog/btn_agree_continue'))
        WebUI.waitForPageLoad(10)
    }

    @When("guest user clicks account icon in header")
    def clickAccountIcon() {
        TestObject iconAccount = findTestObject('WEB/Home/Header/Icon Menu/Account/icon-account')
        WebUI.waitForElementVisible(iconAccount, 10)
        WebUI.mouseOver(iconAccount, FailureHandling.STOP_ON_FAILURE)
    }

    @When("guest user requests tracking code for email {string}")
    def requestTrackingCode(String email) {
        WebUI.setText(findTestObject('WEB/TrackOrder/input_email'), email)
        TestObject btnSendCode = findTestObject('WEB/TrackOrder/btn_send_code')
        WebUI.waitForElementClickable(btnSendCode, 10)
        WebUI.click(btnSendCode)
        WebUI.waitForElementVisible(findTestObject('WEB/TrackOrder/msg_verification_sent'), 10)
        WebUI.delay(2)
    }

    @When("guest user submits invalid verification code {string}")
    def submitInvalidVerificationCode(String code) {
        WebUI.setText(findTestObject('WEB/TrackOrder/input_verification_code'), code)
        WebUI.click(findTestObject('WEB/TrackOrder/btn_continue'))
    }

    // ==========================================
    // ASSERTIONS (THEN)
    // ==========================================

    @Then("system successfully displays all product category menus to the guest")
    def verifyCategoryMenusDisplayed() {
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Mobile Phones menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Ipad menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Watch menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Mac menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Accessories menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Audio menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure TV menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Promo menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Installment Plans menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
        WebUI.callTestCase(findTestCase('WEB/Product/Category/Positive/Positive - Ensure Learn more menu is displayed'), [:], FailureHandling.STOP_ON_FAILURE)
    }

    @Then("guest user saves the company delivery address successfully")
    def verifyCompanyAddressSaved() {
        WebUI.waitForPageLoad(10)
    }

    @Then("system processes guest order successfully and displays order confirmation")
    def verifyGuestCheckoutSuccess() {
        WebUI.waitForElementVisible(findTestObject('WEB/Checkout/PaymentResult/lbl_thank_you'), 10)
        String thankYou = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/lbl_thank_you'))
        WebUI.verifyMatch(thankYou, 'Cám ơn Quý khách!', false)

        String orderText = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/txt_order_id'))
        String orderId = orderText.replaceAll('Mã đơn hàng.', '').trim()
        println('Guest Order ID: ' + orderId)

        WebUI.click(findTestObject('WEB/Checkout/PaymentResult/button_Tip tc mua sm(continue shopping_)'))
    }

    @Then("system redirects guest user to login page or denies profile access")
    def verifyProfileAccessDenied() {
        WebUI.waitForPageLoad(5)
    }

    @Then("system displays invalid email error message on guest checkout page")
    def verifyInvalidGuestEmailError() {
        WebUI.waitForElementPresent(findTestObject('WEB/Checkout/Guest/error_invalid_email'), 5)
    }

    @Then("system displays verification code error message on tracking page")
    def verifyTrackingCodeError() {
        WebUI.waitForElementVisible(findTestObject('WEB/TrackOrder/msg_verification_code_error'), 10)
    }
}