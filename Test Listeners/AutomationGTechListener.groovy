import java.io.File
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

        KeywordUtil.logInfo("================================================")
        KeywordUtil.logInfo("START TEST CASE : ${testCaseContext.getTestCaseId()}")
        KeywordUtil.logInfo("================================================")

        boolean isBrowserActive = false

        try {
            isBrowserActive = (DriverFactory.getWebDriver() != null)

            if (isBrowserActive) {
                WebUI.getUrl()
            }

        } catch (Exception e) {

            isBrowserActive = false
            KeywordUtil.logInfo("Browser detected as inactive : ${e.getMessage()}")

        }

        if (!isBrowserActive && !browserOpenedByListener) {

            try {

                KeywordUtil.logInfo("Opening new browser...")

                WebUI.openBrowser('')
                WebUI.setViewPortSize(1920, 1080)

                if (GlobalVariable.URL == null || GlobalVariable.URL.trim().isEmpty()) {
                    KeywordUtil.markFailedAndStop("GlobalVariable.URL is not set.")
                }

                WebUI.navigateToUrl(GlobalVariable.URL)
                WebUI.waitForPageLoad(30)

                browserOpenedByListener = true

                def width = WebUI.executeJavaScript("return window.innerWidth", null)
                def height = WebUI.executeJavaScript("return window.innerHeight", null)

                KeywordUtil.logInfo("Viewport Size : ${width} x ${height}")

                saveStartPageScreenshot()

            } catch (Exception e) {

                KeywordUtil.markFailed("Failed to open browser : ${e.getMessage()}")
                throw e

            }

        } else if (isBrowserActive) {

            KeywordUtil.logInfo("Browser already opened. Reusing existing session.")

            try {

                if (WebUI.getUrl() != GlobalVariable.URL) {
                    WebUI.navigateToUrl(GlobalVariable.URL)
                }

            } catch (Exception e) {

                KeywordUtil.logWarning("Could not verify current URL : ${e.getMessage()}")

            }

        } else {

            KeywordUtil.logInfo("Browser closed unexpectedly. Re-opening browser...")

            browserOpenedByListener = false

            WebUI.openBrowser('')
            WebUI.setViewPortSize(1920, 1080)

            if (GlobalVariable.URL == null || GlobalVariable.URL.trim().isEmpty()) {
                KeywordUtil.markFailedAndStop("GlobalVariable.URL is not set.")
            }

            WebUI.navigateToUrl(GlobalVariable.URL)
            WebUI.waitForPageLoad(30)

            browserOpenedByListener = true

            saveStartPageScreenshot()

            try {

                def width = WebUI.executeJavaScript("return window.innerWidth", null)
                def height = WebUI.executeJavaScript("return window.innerHeight", null)

                KeywordUtil.logInfo("Viewport Size : ${width} x ${height}")

            } catch (Exception ignored) {
            }
        }
    }

    @AfterTestCase
    def afterTestCase(TestCaseContext testCaseContext) {

        try {

            if (DriverFactory.getWebDriver() != null) {

                String tcName = testCaseContext.getTestCaseId()
                        .replaceAll("[^a-zA-Z0-9]", "_")

                String status = testCaseContext.getTestCaseStatus()

                // ============================================
                // Screenshot GitLab Artifact
                // ============================================

                String screenshotFolder =
                        RunConfiguration.getProjectDir() + "/Screenshot"

                new File(screenshotFolder).mkdirs()

                String artifactScreenshot =
                        screenshotFolder + "/" + tcName + "_" + status + ".png"

                try {
                    WebUI.takeScreenshot(artifactScreenshot)
                    KeywordUtil.logInfo("Artifact Screenshot : ${artifactScreenshot}")
                } catch (Exception ignored) {
                }

                // ============================================
                // Screenshot Katalon Report
                // ============================================

                String reportFolder = RunConfiguration.getReportFolder()

                if (reportFolder != null) {

                    String reportScreenshot =
                            reportFolder + "/" + tcName + "_" + status + ".png"

                    try {
                        WebUI.takeScreenshot(reportScreenshot)
                        KeywordUtil.logInfo("Report Screenshot : ${reportScreenshot}")
                    } catch (Exception ignored) {
                    }
                }

                // ============================================
                // Close Browser
                // ============================================

                String tcId = testCaseContext.getTestCaseId()

                if (!tcId.contains("Forgot password verification email")) {

                    WebUI.closeBrowser()
                    browserOpenedByListener = false

                } else {

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

    private void saveStartPageScreenshot() {

        String screenshotFolder =
                RunConfiguration.getProjectDir() + "/Screenshot"

        new File(screenshotFolder).mkdirs()

        try {
            WebUI.takeScreenshot(screenshotFolder + "/START_PAGE.png")
        } catch (Exception ignored) {
        }

        String reportFolder = RunConfiguration.getReportFolder()

        if (reportFolder != null) {

            try {
                WebUI.takeScreenshot(reportFolder + "/START_PAGE.png")
            } catch (Exception ignored) {
            }

        }
    }
}