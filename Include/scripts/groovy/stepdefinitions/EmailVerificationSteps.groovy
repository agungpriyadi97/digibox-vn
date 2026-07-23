package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import utils.MailinatorHelper

class EmailVerificationSteps {

    String targetEmail = ""
    String inboxName = ""
    String verificationCode = ""
    String mainHandle = ""

    // ==========================================
    // NAVIGATION STEPS
    // ==========================================

    @Given("user navigates to Forgot Password page")
    def navigateToForgotPassword() {
        // Memanggil Test Case Forgot Password bawaan project yang sudah pasti valid
        WebUI.callTestCase(
            findTestCase('WEB/Authentication/Login/Positive/Positive - Ensure Forgot your password link redirects user to reset password page'), 
            [:], 
            FailureHandling.STOP_ON_FAILURE
        )
    }

    // ==========================================
    // ACTIONS (WHEN)
    // ==========================================

    @When("user requests verification code for email {string}")
    def requestVerificationCode(String email) {
        this.targetEmail = email
        this.inboxName = email.split('@')[0]

        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_email'), targetEmail)
        WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_verifikasiCode'))
        
        // Jeda waktu agar email verifikasi dikirimkan ke Mailinator
        WebUI.delay(30)
    }

    @When("user retrieves verification code from Mailinator inbox")
    def retrieveVerificationCodeFromMailinator() {
        WebDriver driver = DriverFactory.getWebDriver()
        this.mainHandle = driver.getWindowHandle()

        String mailinatorUrl = "https://www.mailinator.com/v4/public/inboxes.jsp?to=$inboxName"

        // Buka tab baru untuk Mailinator
        WebUI.executeJavaScript("window.open('$mailinatorUrl','_blank')", null)

        // Switch ke tab Mailinator
        boolean switched = false
        for (int i = 0; i < 10; i++) {
            Set<String> handles = driver.getWindowHandles()
            if (handles.size() > 1) {
                for (String handle : handles) {
                    if (!handle.equals(mainHandle)) {
                        driver.switchTo().window(handle)
                        switched = true
                        break
                    }
                }
            }
            if (switched) break
            WebUI.delay(1)
        }

        assert switched : 'Mailinator tab gagal dibuka.'
        WebUI.waitForPageLoad(20)

        // Ambil kode verifikasi via MailinatorHelper
        MailinatorHelper helper = new MailinatorHelper()
        this.verificationCode = helper.getVerificationCodeFromCurrentTab(inboxName)

        assert verificationCode != null : 'Verification Code tidak ditemukan di Mailinator.'
        println('Verification Code terdeteksi: ' + verificationCode)

        // Tutup tab Mailinator & kembali ke tab utama aplikasi
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainHandle)) {
                driver.switchTo().window(handle)
                driver.close()
                break
            }
        }
        driver.switchTo().window(mainHandle)
        WebUI.waitForPageLoad(10)
    }

    @When("user submits verification code and new password {string}")
    def submitVerificationCodeAndNewPassword(String newPassword) {
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_code'), verificationCode)
        WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_newPassword'), newPassword)
        WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_resetPassword'))
    }

    // ==========================================
    // ASSERTIONS (THEN)
    // ==========================================

    @Then("system displays reset password success message")
    def verifyResetPasswordSuccess() {
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/msg_reset_password_success'), 5)
    }
}