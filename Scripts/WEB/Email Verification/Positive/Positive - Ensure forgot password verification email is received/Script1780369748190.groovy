import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import utils.MailinatorHelper as MailinatorHelper

// ======================== STEP 1 ========================
// callTestCase membuka browser aplikasi utama
WebUI.callTestCase(findTestCase('WEB/Authentication/Login/Positive/Positive - Ensure Forgot your password link redirects user to reset password page'), 
    [:], FailureHandling.STOP_ON_FAILURE)

// ======================== STEP 2 ========================
String email = 'tesqa@mailinator.com'

String inboxName = email.split('@')[0]

WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_email'), email)

WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_verifikasiCode'))

// ======================== STEP 3 ========================
WebUI.delay(3 // Beri waktu email terkirim
    )

// Simpan indeks window utama (aplikasi)
int mainWindowIndex = WebUI.getWindowIndex()

// Buka tab baru untuk Mailinator (tanpa membuka browser baru)
WebUI.executeJavaScript("window.open('https://www.mailinator.com/v4/public/inboxes.jsp?to=$inboxName', '_blank')", null)

WebUI.delay(2)

// Pindah ke tab Mailinator (indeks 1)
WebUI.switchToWindowIndex(1)

// Ambil kode verifikasi dari Mailinator menggunakan helper (helper tidak membuka browser baru)
MailinatorHelper mailHelper = new MailinatorHelper()

String verificationCode = mailHelper.getVerificationCodeFromCurrentTab(inboxName)

if (verificationCode == null) {
    throw new Exception('Kode verifikasi tidak ditemukan di inbox Mailinator')
}

println("✅ Kode verifikasi yang didapat: $verificationCode")

// ======================== STEP 4 ========================
// Kembali ke window utama aplikasi
WebUI.switchToWindowIndex(mainWindowIndex)

WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_code'), verificationCode)

WebUI.setText(findTestObject('WEB/Authentication/ForgotPassword/input_newPassword'), 'Laskar1234567@')

WebUI.click(findTestObject('WEB/Authentication/ForgotPassword/btn_resetPassword'))

// ======================== STEP 5 ========================
WebUI.waitForElementVisible(findTestObject('WEB/Authentication/ForgotPassword/msg_reset_password_success'), 10)

WebUI.verifyElementPresent(findTestObject('WEB/Authentication/ForgotPassword/msg_reset_password_success'), 5)

// (Opsional) Tutup tab Mailinator jika tidak diperlukan lagi
not_run: WebUI.switchToWindowIndex(1)

not_run: WebUI.closeWindowIndex(1)

not_run: WebUI.switchToWindowIndex(mainWindowIndex)

