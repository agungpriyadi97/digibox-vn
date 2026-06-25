import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebDriver as WebDriver
import utils.MailinatorHelper as MailinatorHelper

// ======================== STEP 1 ========================
// Open Forgot Password Page
WebUI.callTestCase(findTestCase('WEB/Authentication/Login/Positive/Positive - Ensure Forgot your password link redirects user to reset password page'), 
    [:], FailureHandling.STOP_ON_FAILURE)

// ======================== STEP 2 ========================
String email = 'tesqa@mailinator.com'

String inboxName = email.split('@')[0]

WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_email'), email)

WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_verifikasiCode'))

// Tunggu email terkirim
WebUI.delay(3)

// ======================== STEP 3 ========================
WebDriver driver = DriverFactory.getWebDriver()

String mainHandle = driver.getWindowHandle()

String mailinatorUrl = "https://www.mailinator.com/v4/public/inboxes.jsp?to=$inboxName"

// buka tab baru
WebUI.executeJavaScript("window.open('$mailinatorUrl','_blank')", null)

// tunggu tab baru muncul
boolean switched = false

for (int i = 0; i < 10; i++) {
    Set<String> handles = driver.getWindowHandles()

    if (handles.size() > 1) {
        for (String handle : handles) {
            if (!(handle.equals(mainHandle))) {
                driver.switchTo().window(handle)

                switched = true

                break
            }
        }
    }
    
    if (switched) {
        break
    }
    
    WebUI.delay(1)
}

assert switched : 'Mailinator tab gagal dibuka.'

WebUI.waitForPageLoad(20)

// ======================== STEP 4 ========================
MailinatorHelper helper = new MailinatorHelper()

String verificationCode = helper.getVerificationCodeFromCurrentTab(inboxName)

assert verificationCode != null : 'Verification Code tidak ditemukan di Mailinator.'

println('Verification Code : ' + verificationCode)

// ======================== STEP 5 ========================
// kembali ke aplikasi
driver.switchTo().window(mainHandle)

WebUI.waitForPageLoad(10)

WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_code'), verificationCode)

WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_newPassword'), 'Laskar1234567@')

WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_resetPassword'))

// ======================== STEP 6 ========================
WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/msg_reset_password_success'), 2)

WebUI.verifyElementPresent(findTestObject('WEB/Authentication/ForgotPassword/msg_reset_password_success'), 2)

// ======================== STEP 7 ========================
// tutup tab mailinator
for (String handle : driver.getWindowHandles()) {
    if (!(handle.equals(mainHandle))) {
        driver.switchTo().window(handle)

        driver.close()

        break
    }
}

driver.switchTo().window(mainHandle)

