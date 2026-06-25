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

WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/pdp/iphone-12/SP220318148023')

WebUI.waitForPageLoad(10)

WebUI.scrollToElement(findTestObject('WEB/Product/PDP/btn_add to cart'), 5)

WebUI.click(findTestObject('WEB/Product/PDP/btn_add to cart'))

WebUI.delay(5)

// Verifikasi pesan sukses muncul
String toast = WebUI.executeJavaScript('\nvar t=document.querySelector("div[role=\'alert\'] p");\nreturn t==null?"":t.textContent;\n', 
    null)

println(toast)

WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))

WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('WEB/Cart/Page Checkout/btn_delete_cart_item'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('WEB/Cart/Page Checkout/btn_confirm_popup'))

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('WEB/Cart/Page Checkout/txt_cart_empty'), 5)

