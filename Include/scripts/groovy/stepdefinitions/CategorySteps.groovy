package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling

class CategorySteps {

    // ==========================================
    // BACKGROUND STEPS
    // ==========================================

    @Given("user is on the home page for category navigation")
    def userIsOnHomePageForCategory() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/')
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // MOBILE PHONES (IPHONE) STEPS
    // ==========================================

    @When("user hovers over Mobile Phones category menu")
    def hoverMobilePhonesMenu() {
        TestObject menuIphone = findTestObject('WEB/Home/Header/Menu/Menu Iphone/menu_iphone')
        WebUI.mouseOver(menuIphone)
    }

    @Then("user navigates through Mobile Phones submenus {string}, {string}, {string}, {string}, and {string}")
    def navigateMobilePhonesSubmenus(String sub1, String sub2, String sub3, String sub4, String sub5) {
        TestObject menuIphone = findTestObject('WEB/Home/Header/Menu/Menu Iphone/menu_iphone')

        // 1. iPhone XR
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_iPhone_XR'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_iPhone_XR'))
        WebUI.waitForPageLoad(10)

        // 2. iPhone SE
        WebUI.mouseOver(menuIphone)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_iPhone_SE'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_iPhone_SE'))
        WebUI.waitForPageLoad(10)

        // 3. iPhone 12
        WebUI.mouseOver(menuIphone)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_iPhone_12'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_iPhone_12'))
        WebUI.waitForPageLoad(10)

        // 4. Discover
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_Discover'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_Discover'))
        WebUI.waitForPageLoad(10)

        // 5. Demo Info
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_Demo_info'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Iphone/submenu_Demo_info'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // IPAD STEPS
    // ==========================================

    @When("user hovers over iPad category menu")
    def hoverIpadMenu() {
        TestObject menuIpad = findTestObject('WEB/Home/Header/Menu/Menu Ipad/menu_ipad')
        WebUI.mouseOver(menuIpad)
    }

    @Then("user navigates through iPad submenus {string}, {string}, and {string}")
    def navigateIpadSubmenus(String sub1, String sub2, String sub3) {
        TestObject menuIpad = findTestObject('WEB/Home/Header/Menu/Menu Ipad/menu_ipad')

        // 1. iPad mini 5th
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Ipad/submenu_iPad mini 5th'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Ipad/submenu_iPad mini 5th'))
        WebUI.waitForPageLoad(10)

        // 2. iPad mini 6th Generation
        WebUI.mouseOver(menuIpad)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Ipad/submenu_iPad mini 6th Generation'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Ipad/submenu_iPad mini 6th Generation'))
        WebUI.waitForPageLoad(10)

        // 3. Discover
        WebUI.mouseOver(menuIpad)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Ipad/submenu_Discover'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Ipad/submenu_Discover'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // MAC STEPS
    // ==========================================

    @When("user hovers over Mac category menu")
    def hoverMacMenu() {
        TestObject menuMac = findTestObject('WEB/Home/Header/Menu/Menu Mac/menu_Mac')
        WebUI.mouseOver(menuMac)
    }

    @Then("user navigates through Mac submenus {string}, {string}, and {string}")
    def navigateMacSubmenus(String sub1, String sub2, String sub3) {
        TestObject menuMac = findTestObject('WEB/Home/Header/Menu/Menu Mac/menu_Mac')

        // 1. MacBook Air 2020
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Mac/submenu_MacBook_Air_2020'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Mac/submenu_MacBook_Air_2020'))
        WebUI.waitForPageLoad(10)

        // 2. MacBook Air 2017
        WebUI.mouseOver(menuMac)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Mac/submenu_MacBook_Air_2017'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Mac/submenu_MacBook_Air_2017'))
        WebUI.waitForPageLoad(10)

        // 3. Discover Mac
        WebUI.mouseOver(menuMac)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Mac/submenu_Discover_Mac'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Mac/submenu_Discover_Mac'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // WATCH STEPS
    // ==========================================

    @When("user hovers over Watch category menu")
    def hoverWatchMenu() {
        TestObject menuWatch = findTestObject('WEB/Home/Header/Menu/Menu Watch/menu_Watch')
        WebUI.mouseOver(menuWatch)
    }

    @Then("user navigates through Watch submenus {string}, {string}, and {string}")
    def navigateWatchSubmenus(String sub1, String sub2, String sub3) {
        TestObject menuWatch = findTestObject('WEB/Home/Header/Menu/Menu Watch/menu_Watch')

        // 1. Apple Watch Series 3
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Watch/submenu_Apple Watch Series 3'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Watch/submenu_Apple Watch Series 3'))
        WebUI.waitForPageLoad(10)

        // 2. Apple Watch Nike Series 4
        WebUI.mouseOver(menuWatch)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Watch/submenu_Apple Watch Nike Series 4'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Watch/submenu_Apple Watch Nike Series 4'))
        WebUI.waitForPageLoad(10)

        // 3. Apple Watch Nike Series 5
        WebUI.mouseOver(menuWatch)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Watch/submenu_Apple Watch Nike Series 5'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Watch/submenu_Apple Watch Nike Series 5'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // ACCESSORIES STEPS
    // ==========================================

    @When("user hovers over Accessories category menu")
    def hoverAccessoriesMenu() {
        TestObject menuAcc = findTestObject('WEB/Home/Header/Menu/Menu Accessories/menu_Accessories')
        WebUI.mouseOver(menuAcc)
    }

    @Then("user navigates through Accessories submenus {string}, {string}, {string}, and {string}")
    def navigateAccessoriesSubmenus(String sub1, String sub2, String sub3, String sub4) {
        TestObject menuAcc = findTestObject('WEB/Home/Header/Menu/Menu Accessories/menu_Accessories')

        // 1. USB-C Cable
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_USB_C_Cable'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_USB_C_Cable'))
        WebUI.waitForPageLoad(10)

        // 2. AirPods Pro MagSafe
        WebUI.mouseOver(menuAcc)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_AirPods_Pro_Magsafe'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_AirPods_Pro_Magsafe'))
        WebUI.waitForPageLoad(10)

        // 3. Discover Accessories
        WebUI.mouseOver(menuAcc)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_Discover_Accessories'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_Discover_Accessories'))
        WebUI.waitForPageLoad(10)

        // 4. iPhone Case
        WebUI.mouseOver(menuAcc)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_iPhone_Case'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Accessories/submenu_iPhone_Case'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // AUDIO STEPS
    // ==========================================

    @When("user clicks Audio category menu")
    def clickAudioMenu() {
        TestObject menuAudio = findTestObject('WEB/Home/Header/Menu/Menu Audio/menu_Audio')
        WebUI.mouseOver(menuAudio)
        WebUI.waitForElementVisible(menuAudio, 10)
        WebUI.click(menuAudio)
    }

    @Then("system successfully navigates to Audio category page")
    def verifyAudioPageLoaded() {
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // TV STEPS
    // ==========================================

    @When("user hovers over TV category menu")
    def hoverTVMenu() {
        TestObject menuTV = findTestObject('WEB/Home/Header/Menu/Menu TV/menu_TV')
        WebUI.mouseOver(menuTV)
    }

    @Then("user clicks Apple TV 4th Generation submenu")
    def clickAppleTVSubmenu() {
        TestObject subTV = findTestObject('WEB/Home/Header/Menu/Menu TV/submenu_Apple_TV_Generasi_4')
        WebUI.waitForElementVisible(subTV, 10)
        WebUI.click(subTV)
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // PROMO STEPS
    // ==========================================

    @When("user hovers over Promo category menu")
    def hoverPromoMenu() {
        TestObject menuPromo = findTestObject('WEB/Home/Header/Menu/Menu Promo/menu_promo')
        WebUI.mouseOver(menuPromo)
    }

    @Then("user navigates through Promo submenus {string}, {string}, {string}, and {string}")
    def navigatePromoSubmenus(String sub1, String sub2, String sub3, String sub4) {
        TestObject menuPromo = findTestObject('WEB/Home/Header/Menu/Menu Promo/menu_promo')

        // 1. Flash sale
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Flash_sale'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Flash_sale'))
        WebUI.waitForPageLoad(10)

        // 2. Pre order
        WebUI.mouseOver(menuPromo)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Pre_order'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Pre_order'))
        WebUI.waitForPageLoad(10)

        // 3. Pre test
        WebUI.mouseOver(menuPromo)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Pre_test'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Pre_test'))
        WebUI.waitForPageLoad(10)

        // 4. Khuyen Mai Tet 2026
        WebUI.mouseOver(menuPromo)
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Khuyen_Mai_Tet_2026'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Menu/Menu Promo/submenu_Khuyen_Mai_Tet_2026'))
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // INSTALLMENT PLANS STEPS
    // ==========================================

    @When("user clicks Installment Plans category menu")
    def clickInstallmentPlansMenu() {
        TestObject menuInstallment = findTestObject('WEB/Home/Header/Menu/Menu Installment Plans/menu_Installment_Plans')
        WebUI.mouseOver(menuInstallment)
        WebUI.waitForElementVisible(menuInstallment, 10)
        WebUI.click(menuInstallment)
    }

    @Then("system successfully navigates to Installment Plans page")
    def verifyInstallmentPlansPageLoaded() {
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // LEARN MORE STEPS
    // ==========================================

    @When("user hovers over Learn More category menu")
    def hoverLearnMoreMenu() {
        TestObject menuLearnMore = findTestObject('WEB/Home/Header/Menu/Menu Learn more/Menu Learn more')
        WebUI.mouseOver(menuLearnMore)
    }

    @Then("user clicks iPhone Learn More submenu")
    def clickIphoneLearnMoreSubmenu() {
        TestObject subLearnMore = findTestObject('WEB/Home/Header/Menu/Menu Learn more/submenu_iPhone_LearnMore')
        WebUI.waitForElementVisible(subLearnMore, 10)
        WebUI.click(subLearnMore)
        WebUI.waitForPageLoad(10)
    }
}