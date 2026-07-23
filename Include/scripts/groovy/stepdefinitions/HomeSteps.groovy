package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.Keys

class HomeSteps {

    // ==========================================
    // BACKGROUND STEPS
    // ==========================================

    @Given("user is on the home page")
    def userIsOnHomePage() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/')
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // ORDER TRACKING STEPS
    // ==========================================

    @When("user hovers over the order tracking icon in the header")
    def hoverOrderTrackingIcon() {
        TestObject iconOrder = findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order')
        WebUI.waitForElementVisible(iconOrder, 10)
        WebUI.mouseOver(iconOrder)
    }

    @Then("order tracking menu should be visible")
    def verifyOrderTrackingVisible() {
        TestObject iconOrder = findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order')
        WebUI.verifyElementPresent(iconOrder, 5)
    }

    @When("user hovers over and clicks the order tracking icon in the header")
    def hoverAndClickOrderTrackingIcon() {
        TestObject iconOrder = findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order')
        WebUI.waitForElementVisible(iconOrder, 10)
        WebUI.mouseOver(iconOrder)
        WebUI.click(iconOrder)
    }

    @Then("system navigates to the order tracking page successfully")
    def verifyOrderTrackingPageLoaded() {
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // SEARCH MENU & FUNCTIONALITY STEPS
    // ==========================================

    @When("user clicks the search icon in the header")
    def clickSearchIcon() {
        TestObject iconSearch = findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search')
        WebUI.waitForElementClickable(iconSearch, 10)
        WebUI.click(iconSearch)
        WebUI.verifyElementPresent(iconSearch, 5)
    }

    @When("user enters search keyword {string} and presses enter")
    def enterSearchKeyword(String keyword) {
        TestObject inputSearch = findTestObject('WEB/Home/Header/Icon Menu/Search/input_search')
        WebUI.setText(inputSearch, keyword)
        WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))
    }

    @Then("system displays search results page successfully")
    def verifySearchResultsLoaded() {
        WebUI.waitForPageLoad(10)
    }
}