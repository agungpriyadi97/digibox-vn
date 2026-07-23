package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

public class DeleteAddress {

    // Helper privat untuk menangani penekanan tombol konfirmasi "Đồng ý"
    private static void confirmDeleteModal() {
        // XPath dinamis untuk dialog Element UI konfirmasi "Đồng ý"
        String xpathConfirm = "//div[contains(@class, 'el-message-box')]//button[contains(@class, 'el-button--primary')]//span[normalize-space()='Đồng ý']"
        TestObject confirmBtn = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpathConfirm)
        
        // Menunggu sampai modal dialog benar-benar terlihat dan dapat diklik
        WebUI.waitForElementVisible(confirmBtn, 10)
        WebUI.waitForElementClickable(confirmBtn, 10)
        
        try {
            WebUI.enhancedClick(confirmBtn)
        } catch (Exception e) {
            WebUI.click(confirmBtn)
        }
        
        // Jeda penanganan animasi penutupan modal & reload daftar
        WebUI.delay(2)
    }

    // ========================= DELETE DELIVERY ADDRESS =========================
    
    /**
     * Menghapus alamat delivery yang PALING BAWAH (terakhir ditambahkan)
     */
    @Keyword
    def deleteLastDeliveryAddress() {
        String xpathDelete = "(//section[.//span[text()='Địa chỉ giao hàng']]//div[@class='address-operation']//span[text()='Xoá'])[1]"
        TestObject deleteBtn = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpathDelete)
        
        if (WebUI.verifyElementPresent(deleteBtn, 5, FailureHandling.OPTIONAL)) {
            WebUI.scrollToElement(deleteBtn, 5)
            WebUI.waitForElementClickable(deleteBtn, 10)
            WebUI.click(deleteBtn)
            
            // Konfirmasi dialog modal
            confirmDeleteModal()
        } else {
            println("WARN: Tidak ditemukan alamat delivery untuk dihapus.")
        }
    }
    
    /**
     * Menghapus alamat delivery berdasarkan email unik
     * @param email alamat email yang muncul di card alamat
     */
    @Keyword
    def deleteAddressByEmail(String email) {
        String xpathDelete = "//div[@class='address-center']//p[contains(text(), '${email}')]/ancestor::div[@class='address-center']/following-sibling::div[@class='address-operation']//span[text()='Xoá']"
        TestObject deleteBtn = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpathDelete)
        
        if (WebUI.verifyElementPresent(deleteBtn, 5, FailureHandling.OPTIONAL)) {
            WebUI.scrollToElement(deleteBtn, 5)
            WebUI.waitForElementClickable(deleteBtn, 10)
            WebUI.click(deleteBtn)
            
            confirmDeleteModal()
        } else {
            println("WARN: Alamat delivery dengan email '${email}' tidak ditemukan.")
        }
    }

    // ========================= DELETE BILLING ADDRESS =========================
    
    /**
     * Menghapus alamat billing yang PALING BAWAH (terakhir ditambahkan)
     */
    @Keyword
    def deleteLastBillingAddress() {
        String xpathDelete = "(//section[.//span[text()='Địa chỉ thanh toán']]//div[@class='address-operation']//span[text()='Xoá'])[1]"
        TestObject deleteBtn = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpathDelete)
        
        if (WebUI.verifyElementPresent(deleteBtn, 5, FailureHandling.OPTIONAL)) {
            WebUI.scrollToElement(deleteBtn, 5)
            WebUI.waitForElementClickable(deleteBtn, 10)
            WebUI.click(deleteBtn)
            
            confirmDeleteModal()
        } else {
            println("WARN: Tidak ditemukan alamat billing untuk dihapus.")
        }
    }
    
    /**
     * Menghapus alamat billing berdasarkan email unik
     * @param email alamat email yang muncul di card alamat
     */
    @Keyword
    def deleteBillingAddressByEmail(String email) {
        String xpathDelete = "//section[.//span[text()='Địa chỉ thanh toán']]//div[@class='address-center']//p[contains(text(), '${email}')]/ancestor::div[@class='address-center']/following-sibling::div[@class='address-operation']//span[text()='Xoá']"
        TestObject deleteBtn = new TestObject().addProperty('xpath', ConditionType.EQUALS, xpathDelete)
        
        if (WebUI.verifyElementPresent(deleteBtn, 5, FailureHandling.OPTIONAL)) {
            WebUI.scrollToElement(deleteBtn, 5)
            WebUI.waitForElementClickable(deleteBtn, 10)
            WebUI.click(deleteBtn)
            
            confirmDeleteModal()
        } else {
            println("WARN: Alamat billing dengan email '${email}' tidak ditemukan.")
        }
    }
}