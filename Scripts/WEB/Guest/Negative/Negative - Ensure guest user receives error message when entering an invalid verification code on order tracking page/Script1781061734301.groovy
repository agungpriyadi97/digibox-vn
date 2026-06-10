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

WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order'))

WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Order/icon_order'))

// Masukkan email
WebUI.setText(findTestObject('WEB/TrackOrder/input_email'), 'agung.priyadi97@gmail.com')

// Klik tombol kirim kode verifikasi (jika sudah aktif)
WebUI.waitForElementClickable(findTestObject('WEB/TrackOrder/btn_send_code'), 10)

WebUI.click(findTestObject('WEB/TrackOrder/btn_send_code'))

WebUI.waitForElementVisible(findTestObject('WEB/TrackOrder/msg_verification_sent'), 10)

// Tunggu beberapa saat (opsional)
WebUI.delay(2)

// Masukkan kode verifikasi (misal: 123456)
WebUI.setText(findTestObject('WEB/TrackOrder/input_verification_code'), '123456')

// Klik tombol Tiếp tục
WebUI.click(findTestObject('WEB/TrackOrder/btn_continue'))

WebUI.waitForElementVisible(findTestObject('WEB/TrackOrder/msg_verification_code_error'), 10)

