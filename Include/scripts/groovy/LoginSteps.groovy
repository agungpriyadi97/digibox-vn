import io.cucumber.java.en.When
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable

class LoginSteps {

    // Anotasi disamakan persis dengan teks Gherkin baris 27
    @When("pengguna memasukkan username yang valid")
    def inputEmailValid() {
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_email'), GlobalVariable.account)
    }

    // Anotasi disamakan persis dengan teks Gherkin baris 28
    @When("pengguna memasukkan password yang valid")
    def inputPasswordValid() {
        WebUI.setText(findTestObject('WEB/Authentication/Login/input_password'), GlobalVariable.password)
    }

    // Baris 29
    @When("pengguna menekan tombol login")
    def clickLoginButton() {
        WebUI.click(findTestObject('WEB/Authentication/Login/btn_login'))
    }
}