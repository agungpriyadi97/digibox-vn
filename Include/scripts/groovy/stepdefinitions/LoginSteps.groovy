package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys

class LoginSteps {

    // ==========================================
    // BACKGROUND & COMMON STEPS
    // ==========================================
    
    @Given("user is on the login page")
    def navigateToLogin() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/login')
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/btn_login'), 10)
    }

    @When("user clicks login button")
    def clickLoginButton() {
        WebUI.click(findTestObject('WEB/Authentication/Login/btn_login'))
    }

    // ==========================================
    // POSITIVE SCENARIO (GLOBAL VARIABLE)
    // ==========================================

    @When("user enters valid username")
    def inputEmailValid() {
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), GlobalVariable.account)
    }

    @When("user enters valid password")
    def inputPasswordValid() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/input_password'), 5)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), GlobalVariable.password)
    }

    @Then("user successfully logs in and views account verification")
    def verifyAccount() {
        WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Icon Menu/Account/icon-account'), 10)
        WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Account/icon-account'))
        WebUI.verifyElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Account/txt_verify_account'), 10)
    }

    // ==========================================
    // NEGATIVE SCENARIOS (DATA-DRIVEN)
    // ==========================================

    @When("user enters account {string}")
    def inputDynamicAccount(String account) {
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), account)
    }

    @When("user enters password {string}")
    def inputDynamicPassword(String password) {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/input_password'), 5)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), password)
    }

    @Then("system rejects login access and displays error message")
    def verifyLoginError() {
        WebUI.delay(1)
        boolean isCredentialError = WebUI.verifyTextPresent('Account or password is not correct.', false, FailureHandling.OPTIONAL)
        boolean isMandatoryError = WebUI.verifyTextPresent('Bắt buộc', false, FailureHandling.OPTIONAL)
        
        assert (isCredentialError || isMandatoryError) : "System did not display error message (Account not correct / Bắt buộc)"
    }

    // ==========================================
    // NEGATIVE SCENARIOS (SPECIFIC VALIDATION)
    // ==========================================

    @When("user clicks account field and clears it")
    def clearAccountField() {
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), '')
    }

    @When("user moves cursor away")
    def clickOutside() {
        WebUI.click(findTestObject('WEB/Authentication/Login/btn_login')) 
    }

    @Then("system displays mandatory validation message on account field")
    def verifyMandatoryEmailMessage() {
        WebUI.delay(1)
        WebUI.verifyTextPresent('Bắt buộc', false)
    }

    @When("password does not contain a combination of letters, numbers, and special characters")
    def descriptivePasswordFormat() {
        TestObject inputPass = findTestObject('WEB/Authentication/Login/input_password')
        WebUI.waitForElementVisible(inputPass, 5)
        
        // 1. Ketik password yang tidak memenuhi kriteria (tanpa kombinasi simbol/angka/huruf)
        WebUI.setText(inputPass, '12345')
        
        // 2. Trigger validation event via TAB & Click Login
        WebUI.sendKeys(inputPass, Keys.chord(Keys.TAB))
        WebUI.click(findTestObject('WEB/Authentication/Login/btn_login'))
    }

    @Then("system displays password requirement validation message")
    def verifyPasswordRequirementMessage() {
        WebUI.delay(1)

        // Pengecekan bertingkat dengan variasi frase validasi bahasa Vietnam
        boolean isMessagePresent = WebUI.verifyTextPresent('Giá trị phải chứa các ký tự đặc biệt', false, FailureHandling.OPTIONAL) ||
                                   WebUI.verifyTextPresent('ký tự đặc biệt', false, FailureHandling.OPTIONAL) ||
                                   WebUI.verifyTextPresent('gồm cả chữ và số', false, FailureHandling.OPTIONAL) ||
                                   WebUI.verifyTextPresent('ít nhất sáu', false, FailureHandling.OPTIONAL)

        assert isMessagePresent : "Password requirement validation message did not appear on screen."
    }

    // ==========================================
    // POSITIVE SCENARIOS (REDIRECTS & UI)
    // ==========================================

    @When("user clicks {string} link")
    def clickDynamicLink(String linkName) {
        if (linkName == "Create an account") {
            TestObject btnCreateAccount = findTestObject('WEB/Authentication/Registration/btn_createanaccount')
            WebUI.waitForElementVisible(btnCreateAccount, 20)
            WebUI.scrollToElement(btnCreateAccount, 5)
            WebUI.waitForElementClickable(btnCreateAccount, 20)
            WebUI.mouseOver(btnCreateAccount)
            WebUI.click(btnCreateAccount)
        } else if (linkName == "Forgot your password") {
            WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/btn_ForgotPassword'), 5)
            WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_ForgotPassword'))
        }
    }

    @Then("user should be redirected to registration page")
    def verifyRegistrationPage() {
        WebUI.verifyElementPresent(findTestObject('WEB/Authentication/Registration/btn_register'), 10, FailureHandling.OPTIONAL)
    }

    @Then("user should be redirected to reset password page")
    def verifyForgotPasswordPage() {
        WebUI.verifyElementPresent(findTestObject('WEB/Authentication/ForgotPassword/btn_submit_forgot'), 10, FailureHandling.OPTIONAL)
    }

    @When("user types in password field")
    def typeInPasswordField() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/input_password'), 5)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), 'Rahasia123!')
    }

    @Then("password characters should be masked with dots or stars")
    def verifyPasswordMasking() {
        WebUI.verifyElementAttributeValue(findTestObject('WEB/Authentication/Login/input_password'), 'type', 'password', 5)
    }
}