package org.example.sys.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;

@Slf4j
public class ScreenshotUtil {

    private static ScreenshotUtil instance = null;

    private ScreenshotUtil() {

    }

    public static ScreenshotUtil getInstance() {
        if (null == instance) {
            instance = new ScreenshotUtil();
        }
        return instance;
    }

    public String takeScreenshot(ITestResult result, boolean knownIssue, boolean performanceIssue) {

        String fileName = getScreenshotFileName(result, knownIssue, performanceIssue);
        saveScreenshotUrl(fileName);
        String screenshotUrl = getScreenShotUrl(fileName);
        log.info("Screenshot url: " + screenshotUrl);
        return screenshotUrl;
    }

    private void saveScreenshotUrl(String fileName) {
        // TODO
    }

    private String getScreenShotUrl(String fileName) {
        return "TODO";
    }

    private String getScreenshotFileName(ITestResult result, boolean knownIssue, boolean performanceIssue) {
        return "TODO";
    }


}

