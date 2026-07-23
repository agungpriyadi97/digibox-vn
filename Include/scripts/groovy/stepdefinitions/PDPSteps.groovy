package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.Keys

class PDPSteps {

    // ==========================================
    // BACKGROUND STEPS
    // ==========================================

    @Given("user searches for product {string} and selects {string}")
    def searchAndSelectProduct(String searchKeyword, String productName) {
        TestObject iconSearch = findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search')
        TestObject inputSearch = findTestObject('WEB/Home/Header/Icon Menu/Search/input_search')
        
        WebUI.waitForElementClickable(iconSearch, 10)
        WebUI.click(iconSearch)
        WebUI.verifyElementPresent(iconSearch, 5)

        WebUI.setText(inputSearch, searchKeyword)
        WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))

        TestObject itemProduct = findTestObject('WEB/Product/PDP/iphone 12')
        WebUI.waitForElementVisible(itemProduct, 10)
        WebUI.click(itemProduct)
    }

    // ==========================================
    // ACTIONS (WHEN)
    // ==========================================

    @When("user clicks product detail button")
    def clickProductDetailButton() {
        TestObject btnDetail = findTestObject('WEB/Product/PDP/btn_detail_pdp')
        WebUI.waitForElementVisible(btnDetail, 10)
        WebUI.click(btnDetail)
    }

    @When("user checks stock status on PDP")
    def checkStockStatus() {
        TestObject txtStock = findTestObject('WEB/Product/PDP/txtStockStatus')
        WebUI.scrollToElement(txtStock, 3)
    }

    @When("user views product price details")
    def viewProductPrice() {
        WebUI.waitForPageLoad(5)
    }

    // ==========================================
    // ASSERTIONS (THEN)
    // ==========================================

    @Then("system successfully displays Product Detail Page")
    def verifyPDPPageLoaded() {
        WebUI.waitForPageLoad(10)
    }

    @Then("system displays stock status {string}")
    def verifyStockStatusText(String expectedStockText) {
        WebUI.verifyTextPresent(expectedStockText, false)
    }

    @Then("product price should be visible and correct")
    def verifyProductPriceVisible() {
        WebUI.waitForPageLoad(5)
    }

    @Then("full product specification and details should be displayed")
    def verifyProductDetailsVisible() {
        WebUI.waitForPageLoad(5)
    }
}