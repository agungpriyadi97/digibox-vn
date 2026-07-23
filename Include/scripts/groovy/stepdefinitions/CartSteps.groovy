package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory

class CartSteps {

    int initialQty = 0

    // ==========================================
    // BACKGROUND & SETUP STEPS
    // ==========================================

    @Given("user is logged in")
    def loginUser() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/login')
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/input_password'), 5)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), GlobalVariable.account)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), GlobalVariable.password)
        WebUI.click(findTestObject('WEB/Authentication/Login/btn_login'))
        WebUI.waitForPageLoad(10)
    }

    @Given("user navigates to PDP page {string}")
    def navigateToPDP(String pdpUrl) {
        WebUI.navigateToUrl(pdpUrl)
        WebUI.waitForPageLoad(60)
    }

    @Given("user ensures cart is not empty by adding product if needed")
    def ensureCartIsNotEmpty() {
        int cartCount = 0
        WebDriver driver = DriverFactory.getWebDriver()
        List badgeElements = driver.findElements(By.xpath("//span[contains(@class,'cart-count')]"))
        boolean isBadgePresent = badgeElements.size() > 0

        if (isBadgePresent) {
            String badgeText = badgeElements.get(0).getText().trim()
            badgeText = badgeText.replaceAll("[^0-9]", "")
            if (!badgeText.isEmpty()) {
                cartCount = Integer.parseInt(badgeText)
            }
        }

        if (cartCount == 0) {
            WebUI.navigateToUrl("https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023")
            WebUI.waitForPageLoad(30)
            WebUI.scrollToElement(findTestObject('WEB/Product/PDP/btn_add to cart'), 5)
            WebUI.waitForElementClickable(findTestObject('WEB/Product/PDP/btn_add to cart'), 20)
            WebUI.mouseOver(findTestObject('WEB/Product/PDP/btn_add to cart'))
            WebUI.enhancedClick(findTestObject('WEB/Product/PDP/btn_add to cart'))
            WebUI.waitForElementVisible(findTestObject('WEB/Product/PDP/msg_success'), 20)
            WebUI.delay(2)
        }
    }

    // ==========================================
    // ACTIONS (WHEN)
    // ==========================================

    @When("user clicks add to cart button")
    def clickAddToCart() {
        WebUI.scrollToElement(findTestObject('WEB/Product/PDP/btn_add to cart'), 5)
        WebUI.waitForElementClickable(findTestObject('WEB/Product/PDP/btn_add to cart'), 20)
        WebUI.click(findTestObject('WEB/Product/PDP/btn_add to cart'))
        WebUI.delay(5)
    }

    @When("user opens cart page")
    def openCartPage() {
        WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))
        WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))
        WebUI.waitForPageLoad(10)

        // Catat jumlah awal jika sedang berada di halaman Cart
        if (WebUI.verifyElementPresent(findTestObject('WEB/Cart/Page Checkout/input_quantity'), 3, FailureHandling.OPTIONAL)) {
            String initialQuantity = WebUI.getAttribute(findTestObject('WEB/Cart/Page Checkout/input_quantity'), 'value')
            initialQty = Integer.parseInt(initialQuantity.trim())
        }
    }

    @When("user clicks increase quantity button")
    def clickIncreaseQuantity() {
        WebUI.waitForElementClickable(findTestObject('WEB/Cart/Page Checkout/btn_increase_quantity'), 10)
        WebUI.click(findTestObject('WEB/Cart/Page Checkout/btn_increase_quantity'))
        WebUI.delay(2)
    }

    @When("user clicks delete item button on cart page")
    def clickDeleteItemCart() {
        WebUI.click(findTestObject('WEB/Cart/Page Checkout/btn_delete_cart_item'))
        WebUI.waitForPageLoad(10)
    }

    @When("user confirms item deletion on pop-up")
    def confirmDeletePopUp() {
        WebUI.click(findTestObject('WEB/Cart/Page Checkout/btn_confirm_popup'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // ASSERTIONS (THEN)
    // ==========================================

    @Then("system displays success toast notification")
    def verifySuccessToast() {
        String toast = WebUI.executeJavaScript('var t=document.querySelector("div[role=\'alert\'] p"); return t==null?"":t.textContent;', null)
        println("Toast Notification: " + toast)
    }

    @Then("cart count badge on header icon should be greater than or equal to {int}")
    def verifyCartBadgeCount(int expectedMinimum) {
        WebUI.waitForElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count'), 10)
        String cartCountText = WebUI.getText(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count')).trim()
        int actualCount = Integer.parseInt(cartCountText)
        WebUI.verifyGreaterThanOrEqual(actualCount, expectedMinimum)
    }

    @Then("product quantity in cart should increase by {int}")
    def verifyQuantityIncreased(int increment) {
        String updatedQuantity = WebUI.getAttribute(findTestObject('WEB/Cart/Page Checkout/input_quantity'), 'value')
        int updatedQty = Integer.parseInt(updatedQuantity.trim())
        int expectedQty = initialQty + increment
        WebUI.verifyEqual(updatedQty, expectedQty)
    }

    @Then("cart badge count on header should be updated")
    def verifyHeaderCartBadgeUpdated() {
        WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))
        WebUI.waitForElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count'), 5)
        String cartBadge = WebUI.getText(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count')).trim()
        int totalItem = Integer.parseInt(cartBadge)
        WebUI.verifyGreaterThanOrEqual(totalItem, 1)
    }

    @Then("system displays empty cart message")
    def verifyCartIsEmpty() {
        WebUI.verifyElementPresent(findTestObject('WEB/Cart/Page Checkout/txt_cart_empty'), 5)
    }
}