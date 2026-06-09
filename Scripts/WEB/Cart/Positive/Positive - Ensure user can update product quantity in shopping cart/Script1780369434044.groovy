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

// ========================= LOGIN =========================
WebUI.callTestCase(findTestCase('WEB/Authentication/Login/Positive/Positive - Ensure user can log in using valid account and password'), 
    [:], FailureHandling.STOP_ON_FAILURE)

// ========================= PASTIKAN KERANJANG TIDAK KOSONG =========================
int cartCount = 0

boolean isBadgePresent = WebUI.verifyElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count'), 3, 
    FailureHandling.OPTIONAL)

if (isBadgePresent) {
    String badgeText = WebUI.getText(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count')).trim()

    cartCount = Integer.parseInt(badgeText)
}

// Jika keranjang kosong (badge tidak ada atau bernilai 0), tambah 1 produk
if (!(isBadgePresent) || (cartCount == 0)) {
    // Cari produk dan tambah ke keranjang
    WebUI.waitForElementClickable(findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search'), 10)

    WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Search/icon_search'))

    WebUI.setText(findTestObject('WEB/Home/Header/Icon Menu/Search/input_search'), 'iphone')

    WebUI.sendKeys(findTestObject('WEB/Home/Header/Icon Menu/Search/input_search'), Keys.chord(Keys.ENTER))

    WebUI.click(findTestObject('WEB/Product/PDP/iphone 12'))

    WebUI.scrollToElement(findTestObject('WEB/Product/PDP/btn_add to cart'), 5)

    WebUI.click(findTestObject('WEB/Product/PDP/btn_add to cart'))

    WebUI.waitForElementVisible(findTestObject('WEB/Product/PDP/msg_success'), 5)

    WebUI.delay(2 // Tunggu badge update
        )
}

// ========================= BUKA HALAMAN KERANJANG =========================
WebUI.click(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))

WebUI.waitForPageLoad(10)

// ========================= SIMPAN JUMLAH AWAL =========================
String initialQuantity = WebUI.getAttribute(findTestObject('WEB/Cart/Page Checkout/input_quantity'), 'value')

int initialQty = Integer.parseInt(initialQuantity.trim())

// ========================= TAMBAH JUMLAH PRODUK (INCREASE 2 KALI) =========================
WebUI.waitForElementClickable(findTestObject('WEB/Cart/Page Checkout/btn_increase_quantity'), 10)

WebUI.click(findTestObject('WEB/Cart/Page Checkout/btn_increase_quantity'))

WebUI.delay(1)

// ========================= VERIFIKASI JUMLAH BERUBAH =========================
String updatedQuantity = WebUI.getAttribute(findTestObject('WEB/Cart/Page Checkout/input_quantity'), 'value')

int updatedQty = Integer.parseInt(updatedQuantity.trim())

int expectedQty = initialQty + 1

WebUI.verifyEqual(updatedQty, expectedQty)

// ========================= (OPSIONAL) VERIFIKASI BADGE HEADER =========================
WebUI.mouseOver(findTestObject('WEB/Home/Header/Icon Menu/Cart/icon_cart'))

WebUI.waitForElementPresent(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count'), 5)

String cartBadge = WebUI.getText(findTestObject('WEB/Home/Header/Icon Menu/Cart/badge_cart_count')).trim()

int totalItem = Integer.parseInt(cartBadge)

WebUI.verifyGreaterThanOrEqual(totalItem, 1)

