package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable

class ForgotPasswordSteps {

    // ==========================================
    // BACKGROUND & NAVIGATION
    // ==========================================

    @Given("user is on the reset password page")
    def navigateToResetPasswordPage() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/login')
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/btn_ForgotPassword'), 10)
        WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_ForgotPassword'))
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/input_email'), 10)
    }

    // ==========================================
    // COMMON INPUT ACTIONS
    // ==========================================

    @When("user enters registered email")
    def inputRegisteredEmail() {
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_email'), GlobalVariable.email)
    }

    @When("user enters email {string}")
    def inputSpecificEmail(String email) {
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_email'), email)
    }

    @When("user clicks send verification code button")
    def clickSendVerificationCode() {
        WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_verifikasiCode'))
    }

    @When("user enters verification code {string}")
    def inputVerificationCode(String code) {
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_code'), code)
    }

    @When("user enters new password {string}")
    def inputNewPassword(String newPassword) {
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_newPassword'), newPassword)
    }

    @When("user clicks reset password button")
    def clickResetPassword() {
        WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_resetPassword'))
    }

    @When("user clears email, verification code, and new password fields")
    def clearAllFields() {
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_email'), '')
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_code'), '')
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_newPassword'), '')
    }

    // ==========================================
    // ASSERTIONS & VERIFICATIONS
    // ==========================================

    @Then("system successfully sends verification code and displays success message")
    def verifyCodeSentSuccess() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/txtField_succes_massage'), 10)
    }

    @Then("system successfully updates user password")
    def verifyPasswordResetSuccess() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/input_email'), 10)
    }

    @Then("system displays unregistered email error message")
    def verifyUnregisteredEmailError() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/txtField_email_already'), 10)
    }

    @Then("system displays invalid verification code error message")
    def verifyInvalidCodeError() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/txtField_invalid_code'), 10)
    }

    @Then("system displays mandatory field error message")
    def verifyMandatoryFieldsError() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/textField_error_massage'), 10)
    }
}