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

WebUI.callTestCase(findTestCase('WEB/Authentication/Login/Positive/Positive - Ensure user can log in using valid account and password'), 
    [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search'), 10)

WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search'))

WebUI.verifyElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search'), 5)

WebUI.setText(findTestObject('WEB/Home/Header/Icon Menu/Search/input_search'), 'iphone')

WebUI.sendKeys(findTestObject('WEB/Home/Header/Icon Menu/Search/input_search'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('WEB/Product/PDP/iphone 12'))

WebUI.scrollToElement(findTestObject('WEB/Product/PDP/btn_add to cart'), 5)

WebUI.mouseOver(findTestObject('WEB/Product/PDP/btn_add to cart'))

WebUI.click(findTestObject('WEB/Product/PDP/btn_add to cart'))

// Verifikasi pesan sukses muncul
WebUI.verifyElementPresent(findTestObject('WEB/Product/PDP/msg_success'), 5)

// Ambil teks pesan
String actualMsg = WebUI.getText(findTestObject('WEB/Product/PDP/msg_success'))

WebUI.verifyMatch(actualMsg, 'Thành công', false)

WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))

WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('WEB/Cart/Page Checkout/input_quantity'))

WebUI.setText(findTestObject('WEB/Checkout/OrderSummary/input_promo_code'), 'agung')

WebUI.click(findTestObject('WEB/Checkout/OrderSummary/btn_apply_promo'))

WebUI.waitForElementPresent(findTestObject('WEB/Checkout/OrderSummary/button_thanh_thon_2'), 10)

WebUI.takeScreenshot()

