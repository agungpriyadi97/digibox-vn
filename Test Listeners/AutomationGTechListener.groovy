import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

//class AutomationListener {
//
//    @BeforeTestCase
//    def beforeTestCase(TestCaseContext testCaseContext) {
//        println("START TEST CASE : ${testCaseContext.getTestCaseId()}")
//        
//        // Cek apakah browser sudah terbuka
//        boolean browserAlreadyOpen = false
//        try {
//            WebUI.getUrl() // akan throw exception jika browser tidak terbuka
//            browserAlreadyOpen = true
//        } catch (Exception e) {
//            // browser belum terbuka
//        }
//        
//        // Hanya buka browser baru jika belum ada yang terbuka
//        if (!browserAlreadyOpen) {
//            WebUI.openBrowser('')
//            WebUI.setViewPortSize(1920, 1080)
//            WebUI.navigateToUrl(GlobalVariable.URL)
//            
//            // Debug viewport
//            def width = WebUI.executeJavaScript("return window.innerWidth", null)
//            def height = WebUI.executeJavaScript("return window.innerHeight", null)
//            KeywordUtil.logInfo("Viewport Size : ${width} x ${height}")
//            
//            WebUI.takeScreenshot(RunConfiguration.getReportFolder() + "/START_PAGE.png")
//        } else {
//            println("Browser sudah terbuka, tidak membuka browser baru")
//        }
//    }
//
//    @AfterTestCase
//    def afterTestCase(TestCaseContext testCaseContext) {
//        try {
//            if (DriverFactory.getWebDriver() != null) {
//                String tcName = testCaseContext.getTestCaseId().replaceAll("[^a-zA-Z0-9]", "_")
//                String screenshotPath = RunConfiguration.getReportFolder() + "/${tcName}_${testCaseContext.getTestCaseStatus()}.png"
//                WebUI.takeScreenshot(screenshotPath)
//                KeywordUtil.logInfo("Screenshot saved : ${screenshotPath}")
//                
//                // Jangan tutup browser secara otomatis.
//                // Biarkan test case atau Katalon yang menutup (atau browser tetap terbuka untuk test case berikutnya)
//                // Jika Anda ingin menutup browser untuk test case TERTENTU (misal yang tidak pakai Mailinator),
//                // tambahkan pengecekan seperti contoh di bawah (comment):
//                
//                String tcId = testCaseContext.getTestCaseId()
//                if (!tcId.contains("Forgot password verification email")) {
//                    WebUI.closeBrowser()
//                }
//                
//            }
//        } catch (Exception e) {
//            KeywordUtil.markWarning("Listener Error : ${e.getMessage()}")
//        }
//    }
//}


class AutomationListener {
	
		@BeforeTestCase
		def beforeTestCase(TestCaseContext testCaseContext) {
			KeywordUtil.logInfo("START TEST CASE : ${testCaseContext.getTestCaseId()}")
			
			// Cek apakah browser udah kebuka pake DriverFactory
			boolean browserAlreadyOpen = (DriverFactory.getWebDriver() != null)
			
			if (!browserAlreadyOpen) {
				WebUI.openBrowser('')
				WebUI.setViewPortSize(1920, 1080)
				WebUI.navigateToUrl(GlobalVariable.URL)
				
				def width = WebUI.executeJavaScript("return window.innerWidth", null)
				def height = WebUI.executeJavaScript("return window.innerHeight", null)
				KeywordUtil.logInfo("Viewport Size : ${width} x ${height}")
				
				WebUI.takeScreenshot(RunConfiguration.getReportFolder() + "/START_PAGE.png")
			} else {
				KeywordUtil.logInfo("Browser sudah terbuka, tidak membuka browser baru")
			}
		}
	
		@AfterTestCase
		def afterTestCase(TestCaseContext testCaseContext) {
			try {
				if (DriverFactory.getWebDriver() != null) {
					String tcName = testCaseContext.getTestCaseId().replaceAll("[^a-zA-Z0-9]", "_")
					String screenshotPath = RunConfiguration.getReportFolder() + "/${tcName}_${testCaseContext.getTestCaseStatus()}.png"
					WebUI.takeScreenshot(screenshotPath)
					KeywordUtil.logInfo("Screenshot saved : ${screenshotPath}")
					
					// Biarkan test case atau Katalon yang nutup browser.
					// Kalo pengen nutup browser untuk test case tertentu, bisa pake kode ini:
					String tcId = testCaseContext.getTestCaseId()
					if (!tcId.contains("Forgot password verification email")) {
						WebUI.closeBrowser()
					}
				}
			} catch (Exception e) {
				KeywordUtil.markWarning("Listener Error : ${e.getMessage()}")
			}
		}
	}