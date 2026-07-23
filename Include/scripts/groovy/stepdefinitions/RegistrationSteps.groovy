package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable as GlobalVariable
import CustomKeywords

class RegistrationSteps {

    Map currentUserData

    // ==========================================
    // BACKGROUND & NAVIGATION
    // ==========================================

    @Given("user is on the registration page")
    def navigateToRegistrationPage() {
        currentUserData = CustomKeywords.'utils.DummyData.generateRegistrationData'()

        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/login')
        TestObject btnCreateAccount = findTestObject('WEB/Authentication/Registration/btn_createanaccount')
        WebUI.waitForElementVisible(btnCreateAccount, 20)
        WebUI.scrollToElement(btnCreateAccount, 5)
        WebUI.click(btnCreateAccount)
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Registration/btn_register'), 10)
    }

    // ==========================================
    // POSITIVE REGISTRATION STEPS
    // ==========================================

    @When("user enters valid registration details using generated dummy data")
    def inputValidRegistrationData() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_email'), currentUserData.email)
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_account'), currentUserData.account)
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_password'), currentUserData.password)
    }

    @When("user clicks register button")
    def clickRegisterButton() {
        WebUI.click(findTestObject('WEB/Authentication/Registration/btn_register'))
    }

    @Then("system displays registration success message {string}")
    def verifyRegistrationSuccessMessage(String expectedMessage) {
        WebUI.verifyElementText(findTestObject('WEB/Authentication/Registration/txt_succses'), expectedMessage)
    }

    @Then("system redirects user back to login page")
    def verifyRedirectToLoginPage() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/btn_login'), 5)
        WebUI.verifyElementPresent(findTestObject('WEB/Authentication/Login/btn_login'), 5)
    }

    // ==========================================
    // GLOBAL VARIABLE & GENERATED INPUT STEPS
    // ==========================================

    @When("user enters email from GlobalVariable")
    def inputGlobalEmail() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_email'), GlobalVariable.email)
    }

    @When("user enters account name from GlobalVariable")
    def inputGlobalAccount() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_account'), GlobalVariable.account)
    }

    @When("user enters password from GlobalVariable")
    def inputGlobalPassword() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_password'), GlobalVariable.password)
    }

    @When("user enters generated email")
    def inputGeneratedEmail() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_email'), currentUserData.email)
    }

    @When("user enters generated account name")
    def inputGeneratedAccount() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_account'), currentUserData.account)
    }

    @When("user enters generated password")
    def inputGeneratedPassword() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_password'), currentUserData.password)
    }

    // ==========================================
    // SPECIFIC / DYNAMIC PARAMETER STEPS
    // ==========================================

    @When("user enters specific email {string}")
    def inputSpecificEmail(String email) {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_email'), email)
    }

    @When("user enters specific account name {string}")
    def inputSpecificAccount(String account) {
        String finalAccount = (account == "valid") ? currentUserData.account : account
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_account'), finalAccount)
    }

    @When("user enters specific password {string}")
    def inputSpecificPassword(String password) {
        String finalPassword = (password == "valid") ? currentUserData.password : password
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_password'), finalPassword)
    }

    @When("user enters account name with spaces")
    def inputWhitespaceAccount() {
        WebUI.setText(findTestObject('WEB/Authentication/Registration/input_account'), '                ')
    }

    // ==========================================
    // ASSERTION STEPS
    // ==========================================

    @Then("system displays duplicate error message {string}")
    def verifyDuplicateError(String expectedMessage) {
        WebUI.verifyElementText(findTestObject('WEB/Authentication/Registration/error_duplicate'), expectedMessage)
    }

    @Then("system displays invalid email error message {string}")
    def verifyInvalidEmailError(String expectedMessage) {
        WebUI.delay(1)
        WebUI.verifyTextPresent(expectedMessage, false)
    }

    @Then("system displays mandatory error message {string}")
    def verifyMandatoryError(String expectedMessage) {
        WebUI.delay(1)
        WebUI.verifyTextPresent(expectedMessage, false)
    }

    @Then("system displays password requirement error message {string}")
    def verifyPasswordRequirementError(String expectedMessage) {
        WebUI.delay(1)
        WebUI.verifyTextPresent(expectedMessage, false)
    }

    @Then("system displays empty account parameter error message {string}")
    def verifyEmptyAccountParamError(String expectedMessage) {
        WebUI.delay(1)
        WebUI.verifyTextPresent(expectedMessage, false)
    }
}