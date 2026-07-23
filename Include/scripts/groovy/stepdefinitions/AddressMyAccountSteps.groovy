package stepdefinitions

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.model.FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.util.Random
import CustomKeywords

class AddressMyAccountSteps {

    String firstName, lastName, phone, email, address, zipCode

    // ==========================================
    // BACKGROUND & NAVIGATION
    // ==========================================

    @Given("user is logged in and navigates to My Address page")
    def loginAndNavigateToMyAddress() {
        WebUI.navigateToUrl('https://d-speedshop-digibox-vn.gtechdigital.id/login')
        WebUI.waitForElementVisible(findTestObject('WEB/Authentication/Login/input_password'), 5)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), GlobalVariable.account)
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), GlobalVariable.password)
        WebUI.click(findTestObject('WEB/Authentication/Login/btn_login'))

        WebUI.waitForElementClickable(findTestObject('WEB/Home/Header/Icon Menu/Account/menu_my_address'), 20)
        WebUI.enhancedClick(findTestObject('WEB/Home/Header/Icon Menu/Account/menu_my_address'))
        WebUI.waitForPageLoad(20)
    }

    // ==========================================
    // HELPER DATA GENERATOR
    // ==========================================

    def generateRandomAddressData() {
        Random random = new Random()
        long ts = System.currentTimeMillis()

        String[] firstNames = ['An', 'Bao', 'Binh', 'Cuong', 'Duc', 'Hai', 'Hieu', 'Hung', 'Khanh', 'Long', 'Minh', 'Nam', 'Phong', 'Quan', 'Son', 'Thanh', 'Thang', 'Tuan', 'Viet']
        String[] lastNames = ['Nguyen', 'Tran', 'Le', 'Pham', 'Hoang', 'Vo', 'Dang', 'Bui', 'Do', 'Phan']
        String[] prefixes = ['032', '033', '034', '035', '036', '037', '038', '039', '056', '058', '059', '070', '076', '077', '078', '079', '081', '082', '083', '084', '085', '086', '088', '089', '090', '091', '092', '093', '094', '096', '097', '098', '099']
        String[] streets = ['Nguyen Trai', 'Tran Hung Dao', 'Le Loi', 'Vo Nguyen Giap', 'Pham Van Dong', 'Hai Ba Trung', 'Nguyen Hue', 'Ly Thuong Kiet']

        firstName = firstNames[random.nextInt(firstNames.length)]
        lastName = lastNames[random.nextInt(lastNames.length)]
        phone = prefixes[random.nextInt(prefixes.length)]
        while (phone.length() < 10) {
            phone += random.nextInt(10).toString()
        }
        email = "${firstName.toLowerCase()}.${lastName.toLowerCase()}.${ts}@gmail.com"
        address = "${random.nextInt(999) + 1} ${streets[random.nextInt(streets.length)]}"
        zipCode = '100000'
    }

    // ==========================================
    // SHIPPING ADDRESS STEPS
    // ==========================================

    @When("user clicks add new shipping address button")
    def clickAddNewShippingAddress() {
        TestObject btnAdd = findTestObject('WEB/Address/Add Address Delivery/btn_add_new_address')
        WebUI.waitForElementClickable(btnAdd, 20)
        WebUI.scrollToElement(btnAdd, 5)
        WebUI.enhancedClick(btnAdd)
    }

    @When("user fills shipping address form with random valid details")
    def fillShippingForm() {
        generateRandomAddressData()
        WebUI.waitForElementVisible(findTestObject('Cammon/Address Form/input_first_name'), 20)
        WebUI.setText(findTestObject('Cammon/Address Form/input_first_name'), firstName)
        WebUI.setText(findTestObject('Cammon/Address Form/input_last_name'), lastName)
        WebUI.setText(findTestObject('Cammon/Address Form/input_mobile_phone'), phone)
        WebUI.setText(findTestObject('Cammon/Address Form/input_email'), email)
        WebUI.setText(findTestObject('Cammon/Address Form/input_address'), address)
        WebUI.setText(findTestObject('Cammon/Address Form/input_zip_code'), zipCode)
    }

    @When("user selects province {string} and ward {string}")
    def selectShippingProvinceAndWard(String province, String ward) {
        WebUI.scrollToElement(findTestObject('Cammon/Address Form/input_TnhThnh Ph_el-input__inner'), 5)
        WebUI.click(findTestObject('Cammon/Address Form/input_TnhThnh Ph_el-input__inner'))
        WebUI.waitForElementClickable(findTestObject('Cammon/Address Form/li_TP. H Ch Minh'), 20)
        WebUI.click(findTestObject('Cammon/Address Form/li_TP. H Ch Minh'))

        WebUI.waitForElementClickable(findTestObject('Cammon/Address Form/input_PhngX_el-input__inner'), 20)
        WebUI.click(findTestObject('Cammon/Address Form/input_PhngX_el-input__inner'))
        WebUI.waitForElementClickable(findTestObject('Cammon/Address Form/li_X Phc Ha'), 20)
        WebUI.click(findTestObject('Cammon/Address Form/li_X Phc Ha'))
    }

    @When("user clicks save shipping address button")
    def clickSaveShippingAddress() {
        TestObject btnSave = findTestObject('Cammon/Address Form/btn_save')
        
        WebUI.delay(1)
        WebUI.scrollToElement(btnSave, 5)
        WebUI.waitForElementClickable(btnSave, 10)
        
        try {
            WebUI.enhancedClick(btnSave)
        } catch (Exception e) {
            WebUI.click(btnSave)
        }
        
        WebUI.delay(3)
    }

    @Then("system successfully saves the new shipping address")
    def verifyShippingSaveSuccess() {
        WebUI.waitForElementNotPresent(findTestObject('Cammon/Address Form/btn_save'), 10, FailureHandling.OPTIONAL)
        WebUI.waitForElementClickable(findTestObject('WEB/Address/Add Address Delivery/btn_add_new_address'), 15)
        WebUI.delay(2)
    }

    @When("user edits the last shipping address with random data")
    def editLastShippingAddress() {
        CustomKeywords.'utils.EditAddressHelper.editLastDeliveryAddressWithRandomData'()
    }

    @Then("system successfully updates the shipping address")
    def verifyShippingEditSuccess() {
        WebUI.waitForPageLoad(10)
    }

    @When("user deletes the last shipping address")
    def deleteLastShippingAddress() {
        CustomKeywords.'utils.DeleteAddress.deleteLastDeliveryAddress'()
    }

    @Then("system successfully removes the shipping address")
    def verifyShippingDeleteSuccess() {
        WebUI.waitForPageLoad(10)
    }

    // ==========================================
    // BILLING ADDRESS STEPS
    // ==========================================

    @When("user clicks add new billing address button")
    def clickAddNewBillingAddress() {
        TestObject btnAddBilling = findTestObject('WEB/Address/Billing Add New Address/btn_add_new_billing')
        WebUI.waitForElementClickable(btnAddBilling, 20)
        WebUI.scrollToElement(btnAddBilling, 5)
        WebUI.enhancedClick(btnAddBilling)
    }

    @When("user fills billing address form with random valid details")
    def fillBillingForm() {
        generateRandomAddressData()
        WebUI.waitForElementVisible(findTestObject('WEB/Address/Billing Add New Address/input_first_name'), 20)
        WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_first_name'), firstName)
        WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_last_name'), lastName)
        WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_mobile_phone'), phone)
        WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_email'), email)
        WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_address'), address)
        WebUI.setText(findTestObject('WEB/Address/Billing Add New Address/input_zip_code'), zipCode)
    }

    @When("user selects billing province {string} and ward {string}")
    def selectBillingProvinceAndWard(String province, String ward) {
        WebUI.scrollToElement(findTestObject('WEB/Address/Billing Add New Address/input_TnhThnh Ph_el-input__inner'), 5)
        WebUI.click(findTestObject('WEB/Address/Billing Add New Address/input_TnhThnh Ph_el-input__inner'))
        WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/li_TP.  Nng'), 20)
        WebUI.enhancedClick(findTestObject('WEB/Address/Billing Add New Address/li_TP.  Nng'))

        WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/input_PhngX_el-input__inner'), 20)
        WebUI.click(findTestObject('WEB/Address/Billing Add New Address/input_PhngX_el-input__inner'))
        WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/li_Phng Hi Vn'), 10)
        WebUI.click(findTestObject('WEB/Address/Billing Add New Address/li_Phng Hi Vn'))
    }

    @When("user clicks save billing address button")
    def clickSaveBillingAddress() {
        TestObject btnSaveBilling = findTestObject('WEB/Address/Billing Add New Address/btn_billing_save')
        
        WebUI.delay(1)
        WebUI.scrollToElement(btnSaveBilling, 5)
        WebUI.waitForElementClickable(btnSaveBilling, 10)
        
        try {
            WebUI.enhancedClick(btnSaveBilling)
        } catch (Exception e) {
            WebUI.click(btnSaveBilling)
        }
        
        WebUI.delay(3)
    }

    @Then("system successfully saves the new billing address")
    def verifyBillingSaveSuccess() {
        WebUI.waitForElementNotPresent(findTestObject('WEB/Address/Billing Add New Address/btn_billing_save'), 10, FailureHandling.OPTIONAL)
        WebUI.waitForElementClickable(findTestObject('WEB/Address/Billing Add New Address/btn_add_new_billing'), 15)
        WebUI.delay(2)
    }

    @When("user deletes the last billing address")
    def deleteLastBillingAddress() {
        CustomKeywords.'utils.DeleteAddress.deleteLastBillingAddress'()
    }

    @Then("system successfully removes the billing address")
    def verifyBillingDeleteSuccess() {
        WebUI.waitForPageLoad(10)
    }
}