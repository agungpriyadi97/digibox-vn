package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import CustomKeywords

class ExternalIntegrationSteps {

    // ==========================================
    // ZALO CHAT STEPS
    // ==========================================

    @Given("user is on home page")
    def userIsOnHomePage() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/')
        WebUI.waitForPageLoad(10)
    }

    @When("user clicks Zalo chat button in navbar")
    def clickZaloNavbarButton() {
        TestObject btnZaloNav = findTestObject('WEB/Home/Navbar/zaloChatButton')
        WebUI.waitForElementVisible(btnZaloNav, 10)
        WebUI.click(btnZaloNav)
        WebUI.delay(2)
    }

    @Then("system opens new tab with Zalo URL containing {string}")
    def verifyZaloNavbarTabUrl(String expectedUrl) {
        WebUI.switchToWindowIndex(1)
        String currentUrl = WebUI.getUrl()
        WebUI.verifyMatch(currentUrl, expectedUrl, false)
        WebUI.takeScreenshot()
        WebUI.switchToWindowIndex(0)
    }

    @When("user clicks Zalo chat button on product page")
    def clickZaloProductPageButton() {
        // Disimpan untuk eksekusi assertions di step 'Then'
    }

    @Then("system opens new tab and verifies Zalo URL matches {string}")
    def verifyZaloPDPWindow(String expectedUrlPrefix) {
        TestObject btnZaloPDP = findTestObject('WEB/Product/PDP/btn_chat_zalo_with_buy_now')
        boolean isUrlMatch = CustomKeywords.'utils.WindowHelper.clickAndVerifyNewTab'(btnZaloPDP, expectedUrlPrefix)
        WebUI.verifyEqual(isUrlMatch, true)
    }

    // ==========================================
    // ZALOPAY GATEWAY STEPS
    // ==========================================

    @When("user selects ZaloPay payment method")
    def selectZaloPay() {
        TestObject radioZaloPay = findTestObject('WEB/Checkout/Payment/radio_zalopay')
        WebUI.waitForElementVisible(radioZaloPay, 10)
        WebUI.scrollToElement(radioZaloPay, 5)
        WebUI.click(radioZaloPay)

        String summary = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/lbl_order_summary'))
        WebUI.verifyMatch(summary, '.*Tóm tắt đơn hàng.*', true)
        
        String total = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/txt_total'))
        println('Total Order ZaloPay: ' + total)
    }

    @When("user proceeds to pay with ZaloPay")
    def proceedToZaloPayGateway() {
        WebUI.delay(5)
        WebUI.click(findTestObject('WEB/Checkout/OrderSummary/button_thanh_thon_2'))
        WebUI.waitForPageLoad(30)
        WebUI.delay(3)
    }

    private boolean isZaloPayMaintenance() {
        String currentUrl = WebUI.getUrl()
        WebDriver driver = DriverFactory.getWebDriver()
        String pageSource = driver.getPageSource()

        boolean isMaintenance = (currentUrl.toLowerCase().contains('maintenance') || 
                                 pageSource.toLowerCase().contains('bảo trì') || 
                                 pageSource.toLowerCase().contains('website is under maintenance') || 
                                 pageSource.toLowerCase().contains('service unavailable'))

        if (isMaintenance) {
            WebUI.takeScreenshot('Reports/ZaloPay_Maintenance.png')
            println('====================================================')
            println('INFO : ZaloPay Gateway sedang maintenance')
            println('INFO : Payment Gateway verification skipped')
            println('====================================================')
        }
        return isMaintenance
    }

    @Then("system redirects to ZaloPay gateway and verifies merchant logo and transaction details")
    def verifyZaloPayGatewayPage() {
        if (isZaloPayMaintenance()) return

        WebUI.waitForElementVisible(findTestObject('WEB/ZaloPayGateway/merchant_logo'), 20)
        WebUI.verifyElementPresent(findTestObject('WEB/ZaloPayGateway/merchant_logo'), 10)
        WebUI.verifyElementPresent(findTestObject('WEB/ZaloPayGateway/transaction_content_value'), 10)
        WebUI.verifyElementPresent(findTestObject('WEB/ZaloPayGateway/option_open_zalopay_app'), 10)
        WebUI.takeScreenshot('Reports/ZaloPay_Gateway.png')
    }

    @Then("user verifies ZaloPay app, international card, VietQR, and domestic card options")
    def verifyAllZaloPayPaymentMethods() {
        if (isZaloPayMaintenance()) return

        WebUI.waitForElementVisible(findTestObject('WEB/ZaloPayGateway/merchant_logo'), 20)
        WebUI.verifyElementPresent(findTestObject('WEB/ZaloPayGateway/merchant_logo'), 10)

        // 1. ZaloPay App
        TestObject optApp = findTestObject('WEB/ZaloPayGateway/option_open_zalopay_app')
        WebUI.verifyElementPresent(optApp, 10)
        WebUI.click(optApp)
        WebUI.waitForPageLoad(10)
        WebUI.takeScreenshot('Reports/ZaloPay_App.png')
        WebUI.click(findTestObject('WEB/ZaloPayGateway/link_back_to_previous_page'))

        // 2. International Card
        TestObject optIntCard = findTestObject('WEB/ZaloPayGateway/option_international_card')
        WebUI.verifyElementPresent(optIntCard, 10)
        WebUI.click(optIntCard)
        WebUI.waitForPageLoad(10)
        WebUI.takeScreenshot('Reports/International_Card.png')
        WebUI.click(findTestObject('WEB/ZaloPayGateway/link_back_to_previous_page'))

        // 3. VietQR
        TestObject optVietQR = findTestObject('WEB/ZaloPayGateway/option_vietqr')
        WebUI.verifyElementPresent(optVietQR, 10)
        WebUI.click(optVietQR)
        WebUI.waitForPageLoad(10)
        WebUI.takeScreenshot('Reports/VietQR.png')
        WebUI.click(findTestObject('WEB/ZaloPayGateway/link_back_to_previous_page'))

        // 4. Domestic Card
        TestObject optDomCard = findTestObject('WEB/ZaloPayGateway/option_domestic_card')
        WebUI.verifyElementPresent(optDomCard, 10)
        WebUI.click(optDomCard)
        WebUI.waitForPageLoad(10)
        WebUI.takeScreenshot('Reports/Domestic_Card.png')
        WebUI.click(findTestObject('WEB/ZaloPayGateway/link_back_to_previous_page'))

        println('===== Semua Payment Method berhasil diverifikasi =====')
    }
}