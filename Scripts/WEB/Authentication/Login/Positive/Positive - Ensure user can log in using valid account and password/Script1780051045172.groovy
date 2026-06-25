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

'Input Email Valid\r\n'
WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), GlobalVariable.account)

'Input Password Valid'
WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), GlobalVariable.password)

'Click Button Login'
WebUI.click(findTestObject('WEB/Authentication/Login/btn_login'))

WebUI.waitForElementVisible(findTestObject('WEB/Home/Header/Icon Menu/Account/icon-account'), 4)

WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Account/icon-account'))

WebUI.verifyElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Account/txt_verify_account'), 10)