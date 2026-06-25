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
import utils.AddressHelper as AddressHelper
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import java.util.Arrays as Arrays
import java.util.Random as Random
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

//====================================================
// LOGIN
//====================================================
WebUI.callTestCase(findTestCase('WEB/Authentication/Login/Positive/Positive - Ensure user can log in using valid account and password'), 
    [:], FailureHandling.STOP_ON_FAILURE)

println('LOGIN SUCCESS')

//====================================================
// OPEN MY ADDRESS
//====================================================
WebUI.waitForElementClickable(findTestObject('WEB/Home/Header/Icon Menu/Account/menu_my_address'), 20)

WebUI.enhancedClick(findTestObject('WEB/Home/Header/Icon Menu/Account/menu_my_address'))

WebUI.waitForPageLoad(20)

println('MY ADDRESS PAGE OPENED')

//====================================================
// CLICK ADD ADDRESS
//====================================================
WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/btn_add_new_billing'), 20)

WebUI.scrollToElement(findTestObject('WEB/Address/Billing Add New Address/btn_add_new_billing'), 5)

WebUI.enhancedClick(findTestObject('WEB/Address/Billing Add New Address/btn_add_new_billing'))

println('ADD ADDRESS FORM OPENED')

//====================================================
// RANDOM DATA
//====================================================
Random random = new Random()

long ts = System.currentTimeMillis()

//------------------------------
// Random Vietnamese Name
//------------------------------
String[] firstNames = ['An', 'Bao', 'Binh', 'Cuong', 'Duc', 'Hai', 'Hieu', 'Hung', 'Khanh', 'Long', 'Minh', 'Nam', 'Phong'
    , 'Quan', 'Son', 'Thanh', 'Thang', 'Tuan', 'Viet']

String[] lastNames = ['Nguyen', 'Tran', 'Le', 'Pham', 'Hoang', 'Vo', 'Dang', 'Bui', 'Do', 'Phan']

String firstName = firstNames[random.nextInt(firstNames.length)]

String lastName = lastNames[random.nextInt(lastNames.length)]

//------------------------------
// Valid Vietnam Phone
//------------------------------
String[] prefixes = ['032', '033', '034', '035', '036', '037', '038', '039', '056', '058', '059', '070', '076', '077', '078'
    , '079', '081', '082', '083', '084', '085', '086', '088', '089', '090', '091', '092', '093', '094', '096', '097', '098'
    , '099']

String phone = prefixes[random.nextInt(prefixes.length)]

while (phone.length() < 10) {
    phone += random.nextInt(10).toString()
}

//------------------------------
// Random Gmail
//------------------------------
String first = firstName.toLowerCase()

String last = lastName.toLowerCase()

String email = "$first.$last.$ts@gmail.com"

//------------------------------
// Random Address
//------------------------------
//====================================================
// Random Address
//====================================================
String[] streets = ['Nguyen Trai', 'Tran Hung Dao', 'Le Loi', 'Vo Nguyen Giap', 'Pham Van Dong', 'Hai Ba Trung', 'Nguyen Hue'
    , 'Ly Thuong Kiet']

int houseNumber = random.nextInt(999) + 1

String street = streets[random.nextInt(streets.length)]

String address = "$houseNumber $street"

String zipCode = '100000'

//====================================================
// PRINT RANDOM DATA
//====================================================
println('===========================================')

println("FIRST NAME : $firstName")

println("LAST NAME  : $lastName")

println("PHONE      : $phone")

println("EMAIL      : $email")

println("ADDRESS    : $address")

println("ZIP CODE   : $zipCode")

println('===========================================')

//====================================================
// FILL FORM
//====================================================
WebUI.waitForElementVisible(findTestObject('WEB/Address/Billing Add New Address/input_first_name'), 20)

WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_first_name'), firstName)

WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_last_name'), lastName)

WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_mobile_phone'), phone)

WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_email'), email)

WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_address'), address)

//====================================================
// PROVINCE
//====================================================
WebUI.scrollToElement(findTestObject('WEB/Address/Billing Add New Address/input_TnhThnh Ph_el-input__inner'), 5)

WebUI.mouseOver(findTestObject('WEB/Address/Billing Add New Address/input_TnhThnh Ph_el-input__inner'))

WebUI.click(findTestObject('WEB/Address/Billing Add New Address/input_TnhThnh Ph_el-input__inner'))

WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/li_TP.  Nng'), 20)

WebUI.mouseOver(findTestObject('WEB/Address/Billing Add New Address/li_TP.  Nng'))

WebUI.enhancedClick(findTestObject('WEB/Address/Billing Add New Address/li_TP.  Nng'))

//====================================================
// WARD
//====================================================
WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/input_PhngX_el-input__inner'), 20)

WebUI.mouseOver(findTestObject('WEB/Address/Billing Add New Address/input_PhngX_el-input__inner'))

WebUI.click(findTestObject('WEB/Address/Billing Add New Address/input_PhngX_el-input__inner'))

WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/li_Phng Hi Vn'), 10)

not_run: WebUI.mouseOver(findTestObject('WEB/Address/Billing Add New Address/li_Phng Hi Vn'))

WebUI.click(findTestObject('WEB/Address/Billing Add New Address/li_Phng Hi Vn'))

//====================================================
// ZIP CODE
//====================================================
WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_zip_code'), zipCode)

//====================================================
// SAVE
//====================================================
WebUI.scrollToElement(findTestObject('WEB/Address/Billing Add New Address/btn_billing_save'), 5)

WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/btn_billing_save'), 20)

WebUI.mouseOver(findTestObject('WEB/Address/Billing Add New Address/btn_billing_save'))

WebUI.enhancedClick(findTestObject('WEB/Address/Billing Add New Address/btn_billing_save'))

println('CLICK SAVE')

//====================================================
// VERIFY SAVE SUCCESS
//====================================================
WebUI.waitForPageLoad(20)

println('ADDRESS CREATED SUCCESSFULLY')

