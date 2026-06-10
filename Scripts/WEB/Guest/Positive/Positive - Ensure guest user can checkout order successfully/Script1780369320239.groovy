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

WebUI.callTestCase(findTestCase('WEB/Cart/Positive/Positive - Ensure user can add product to cart'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('WEB/Checkout/Guest/input_guest_email'), 'agung.priyadi96@gmail.com')

WebUI.mouseOver(findTestObject('WEB/Checkout/Guest/btn_guest_continue'))

WebUI.click(findTestObject('WEB/Checkout/Guest/btn_guest_continue'))

// Isi form alamat pengiriman
WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressIndividual/input_first_name'), 'Agung')

WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressIndividual/input_last_name'), 'Priyadi')

WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_mobile'), '0915551234')

WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_address'), 'No. 1, Đường Lê Duẩn, Phường Bến Nghé, Quận 1, Thành phố Hồ Chí Minh 700000, Vietnam')

WebUI.setText(findTestObject('WEB/Checkout/DeliveryAddressCompany/input_zip'), '70000')

WebUI.waitForElementClickable(findTestObject('WEB/Checkout/DeliveryAddressIndividual/btn_save'), 10)

// ... dan seterusnya
// Klik Lưu
WebUI.click(findTestObject('WEB/Checkout/DeliveryAddressIndividual/btn_save'))

WebUI.click(findTestObject('WEB/Checkout/Payment/radio_one_pay'))

// 2. Tunggu hingga children-item muncul (maksimal 10 detik)
WebUI.waitForElementPresent(findTestObject('WEB/Checkout/Payment/payment_children_atm'), 10)

// 3. Klik elemen children-item (misalnya untuk memilih metode pembayaran ATM)
WebUI.click(findTestObject('WEB/Checkout/Payment/payment_children_atm'))

// Order Summary
// Verifikasi ringkasan pesanan
String summary = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/lbl_order_summary'))

WebUI.verifyMatch(summary, '.*Tóm tắt đơn hàng.*', true)

// Masukkan kode promo
not_run: WebUI.setText(findTestObject('WEB/Checkout/OrderSummary/input_promo_code'), 'DISCOUNT10')

not_run: WebUI.click(findTestObject('WEB/Checkout/OrderSummary/btn_apply_promo'))

// Ambil nilai total
String total = WebUI.getText(findTestObject('WEB/Checkout/OrderSummary/txt_total'))

println('Total: ' + total)

// Centang checkbox setujui syarat & ketentuan
WebUI.click(findTestObject('WEB/Checkout/OrderSummary/chk_accept_terms'))

WebUI.delay(10)

WebUI.click(findTestObject('WEB/Checkout/OrderSummary/button_thanh_thon_2'))

WebUI.waitForPageLoad(10)

//BankSelection//
// Ketik nama bank pada kotak pencarian
WebUI.setText(findTestObject('WEB/Checkout/Installment/BankSelection/input_search_bank'), 'ACB')

WebUI.delay(2 // Tunggu hasil filter
    )

// Klik bank pertama yang muncul
WebUI.click(findTestObject('WEB/Checkout/Installment/BankSelection/bank_list_item'))

WebUI.setText(findTestObject('WEB/Checkout/Card/input_card_number'), '4000000000001091')

WebUI.setText(findTestObject('WEB/Checkout/Card/input_expiration_date'), '12/28')

WebUI.setText(findTestObject('WEB/Checkout/Card/input_csc'), '123')

WebUI.setText(findTestObject('WEB/Checkout/Card/input_cardholder_name'), 'NGUYEN VAN A')

// Pilih cicilan 6 bulan
WebUI.waitForElementClickable(findTestObject('WEB/Checkout/Card/radio_6_months'), 15)

WebUI.enhancedClick(findTestObject('WEB/Checkout/Card/radio_6_months'))

WebUI.waitForElementClickable(findTestObject('WEB/Checkout/Card/chk_onepay_policy'), 10)

WebUI.click(findTestObject('WEB/Checkout/Card/chk_onepay_policy'), FailureHandling.STOP_ON_FAILURE)

// Klik tombol Pay Now
WebUI.click(findTestObject('WEB/Checkout/Card/btn_pay_now'))

// Verifikasi judul dialog
WebUI.verifyElementText(findTestObject('WEB/Checkout/ConfirmDialog/lbl_note_title'), 'Note')

// Verifikasi teks isi dialog
String content = WebUI.getText(findTestObject('WEB/Checkout/ConfirmDialog/txt_content'))

WebUI.verifyMatch(content, '.*Cardholder does NOT have to register installment.*', true)

// Klik tombol Agree & Continue
WebUI.click(findTestObject('WEB/Checkout/ConfirmDialog/btn_agree_continue'))

WebUI.waitForPageLoad(10)

// Verifikasi halaman sukses
WebUI.waitForElementVisible(findTestObject('WEB/Checkout/PaymentResult/lbl_thank_you'), 10)

// Ambil teks ucapan terima kasih
String thankYou = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/lbl_thank_you'))

WebUI.verifyMatch(thankYou, 'Cám ơn Quý khách!', false)

// Ambil nomor order dari teks
String orderText = WebUI.getText(findTestObject('WEB/Checkout/PaymentResult/txt_order_id'))

String orderId = orderText.replaceAll('Mã đơn hàng.', '').trim()

println('Order ID: ' + orderId)

// Klik tombol lanjut belanja
WebUI.click(findTestObject('WEB/Checkout/PaymentResult/button_Tip tc mua sm(continue shopping_)'))

