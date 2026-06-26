import com.kms.katalon.core.annotation.BeforeTestCase
import java.io.File
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

class AutomationListener {

    @BeforeTestCase
    def beforeTestCase(TestCaseContext testCaseContext) {

        KeywordUtil.logInfo("================================================")
        KeywordUtil.logInfo("START TEST CASE : ${testCaseContext.getTestCaseId()}")
        KeywordUtil.logInfo("================================================")

        boolean browserReady = false

        try {
            DriverFactory.getWebDriver()
            browserReady = true
        } catch (Exception ignored) {
            browserReady = false
        }

        if (!browserReady) {

            KeywordUtil.logInfo("Opening Browser...")

            WebUI.openBrowser('')
            WebUI.setViewPortSize(1920, 1080)

            if (GlobalVariable.URL == null || GlobalVariable.URL.trim().isEmpty()) {
                KeywordUtil.markFailedAndStop("GlobalVariable.URL is empty.")
            }

            WebUI.navigateToUrl(GlobalVariable.URL)
            WebUI.waitForPageLoad(30)

            String screenshotFolder = RunConfiguration.getProjectDir() + "/Screenshot"
            new File(screenshotFolder).mkdirs()

            try {
                WebUI.takeScreenshot(screenshotFolder + "/START_PAGE.png")
            } catch (Exception ignored) {
            }

        } else {

            KeywordUtil.logInfo("Browser already opened. Reusing existing browser.")

        }
    }

    @AfterTestCase
    def afterTestCase(TestCaseContext testCaseContext) {

        String tcName = testCaseContext.getTestCaseId()
                .replaceAll("[^a-zA-Z0-9]", "_")

        String status = testCaseContext.getTestCaseStatus()

        String screenshotFolder =
                RunConfiguration.getProjectDir() + "/Screenshot"

        new File(screenshotFolder).mkdirs()

        String screenshotPath =
                screenshotFolder + "/" + tcName + "_" + status + ".png"

        try {

            DriverFactory.getWebDriver()

            // Screenshot GitLab Artifact
            try {
                WebUI.takeScreenshot(screenshotPath)
            } catch (Exception ignored) {
            }

            // Screenshot Report Katalon
            try {
                WebUI.takeScreenshot(
                        RunConfiguration.getReportFolder() +
                        "/" + tcName + "_" + status + ".png")
            } catch (Exception ignored) {
            }

            KeywordUtil.logInfo("Screenshot saved : " + screenshotPath)

        } catch (Exception e) {

            KeywordUtil.logInfo("Unable to capture screenshot : " + e.getMessage())

        }

        KeywordUtil.logInfo("END TEST CASE : ${testCaseContext.getTestCaseId()}")
        KeywordUtil.logInfo("================================================")
    }

}