import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

class AutomationListener {
	
	private static boolean browserOpenedByListener = false
	
	@BeforeTestCase
	def beforeTestCase(TestCaseContext testCaseContext) {
		KeywordUtil.logInfo("START TEST CASE : ${testCaseContext.getTestCaseId()}")
		
		// Cek apakah browser benar-benar aktif dan bisa digunakan
		boolean isBrowserActive = false
		try {
			isBrowserActive = (DriverFactory.getWebDriver() != null)
			if (isBrowserActive) {
				// Tes tambahan: coba get current url (tanpa throw exception)
				WebUI.getUrl()
			}
		} catch (Exception e) {
			isBrowserActive = false
			KeywordUtil.logInfo("Browser detected as inactive: ${e.getMessage()}")
		}
		
		if (!isBrowserActive && !browserOpenedByListener) {
			try {
				KeywordUtil.logInfo("Opening new browser...")
				WebUI.openBrowser('')
				WebUI.setViewPortSize(1920, 1080)
				
				// Validasi GlobalVariable.URL
				if (GlobalVariable.URL == null || GlobalVariable.URL.isEmpty()) {
					KeywordUtil.markFailed("GlobalVariable.URL is not set. Please configure it in Profiles.")
					return
				}
				
				WebUI.navigateToUrl(GlobalVariable.URL)
				browserOpenedByListener = true
				
				def width = WebUI.executeJavaScript("return window.innerWidth", null)
				def height = WebUI.executeJavaScript("return window.innerHeight", null)
				KeywordUtil.logInfo("Viewport Size : ${width} x ${height}")
				
				WebUI.takeScreenshot(RunConfiguration.getReportFolder() + "/START_PAGE.png")
			} catch (Exception e) {
				KeywordUtil.markFailed("Failed to open browser or navigate to URL: ${e.getMessage()}")
				throw e // Re-throw agar test case gagal segera
			}
		} else if (isBrowserActive) {
			KeywordUtil.logInfo("Browser already opened, reusing existing session.")
			// Opsional: pastikan berada di URL yang benar
			try {
				if (WebUI.getUrl() != GlobalVariable.URL) {
					WebUI.navigateToUrl(GlobalVariable.URL)
				}
			} catch (Exception e) {
				KeywordUtil.logWarning("Could not verify current URL: ${e.getMessage()}")
			}
		} else {
			KeywordUtil.logInfo("Browser was opened by listener in a previous test case but is now closed. Reopening...")
			browserOpenedByListener = false
			// Rekursif panggil beforeTestCase lagi (atau ulangi logika buka browser)
			beforeTestCase(testCaseContext)
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
				
				// Jangan tutup browser untuk test case tertentu
				String tcId = testCaseContext.getTestCaseId()
				if (!tcId.contains("Forgot password verification email")) {
					WebUI.closeBrowser()
					browserOpenedByListener = false // Reset flag karena browser ditutup
				} else {
					// Untuk test case yang tidak menutup browser, tetap pertahankan flag
					KeywordUtil.logInfo("Browser kept open for this test case.")
				}
			} else {
				KeywordUtil.logInfo("No browser to close.")
				browserOpenedByListener = false
			}
		} catch (Exception e) {
			KeywordUtil.markWarning("Listener Error : ${e.getMessage()}")
			browserOpenedByListener = false
		}
	}
}

//class AutomationListener {
//	
//		@BeforeTestCase
//		def beforeTestCase(TestCaseContext testCaseContext) {
//			KeywordUtil.logInfo("START TEST CASE : ${testCaseContext.getTestCaseId()}")
//			
//			// Cek apakah browser udah kebuka pake DriverFactory
//			boolean browserAlreadyOpen = (DriverFactory.getWebDriver() != null)
//			
//			if (!browserAlreadyOpen) {
//				WebUI.openBrowser('')
//				WebUI.setViewPortSize(1920, 1080)
//				WebUI.navigateToUrl(GlobalVariable.URL)
//				
//				def width = WebUI.executeJavaScript("return window.innerWidth", null)
//				def height = WebUI.executeJavaScript("return window.innerHeight", null)
//				KeywordUtil.logInfo("Viewport Size : ${width} x ${height}")
//				
//				WebUI.takeScreenshot(RunConfiguration.getReportFolder() + "/START_PAGE.png")
//			} else {
//				KeywordUtil.logInfo("Browser sudah terbuka, tidak membuka browser baru")
//			}
//		}
//	
//		@AfterTestCase
//		def afterTestCase(TestCaseContext testCaseContext) {
//			try {
//				if (DriverFactory.getWebDriver() != null) {
//					String tcName = testCaseContext.getTestCaseId().replaceAll("[^a-zA-Z0-9]", "_")
//					String screenshotPath = RunConfiguration.getReportFolder() + "/${tcName}_${testCaseContext.getTestCaseStatus()}.png"
//					WebUI.takeScreenshot(screenshotPath)
//					KeywordUtil.logInfo("Screenshot saved : ${screenshotPath}")
//					
//					// Biarkan test case atau Katalon yang nutup browser.
//					// Kalo pengen nutup browser untuk test case tertentu, bisa pake kode ini:
//					String tcId = testCaseContext.getTestCaseId()
//					if (!tcId.contains("Forgot password verification email")) {
//						WebUI.closeBrowser()
//					}
//				}
//			} catch (Exception e) {
//				KeywordUtil.markWarning("Listener Error : ${e.getMessage()}")
//			}
//		}
//	}
	
	
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