package org.example.sys.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;


@Slf4j
public class TestListener extends TestListenerAdapter implements ISuiteListener, IAnnotationTransformer {


    @Override
    public void onStart(ISuite iSuite) {
        PropertySupplier.loadConfiguration(iSuite);
        QueryRepository.loadQueries();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info(TestListenerUtil.getTestNameFormatted(result) + "started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(TestListenerUtil.getTestNameFormatted(result) + " succeed in "
                + (result.getEndMillis() - result.getStartMillis()) / 1000 + "s");

        KnownIssue knownIssue = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(KnownIssue.class);
        if (null != knownIssue) {
            log.info("Consider remove knownIssue annotation from test method signature");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(TestListenerUtil.getTestNameFormatted(result) + "skipped");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String stackTrace = TestListenerUtil.getTestNameFormatted(result);
        log.info(TestListenerUtil.getTestNameFormatted(result) + " failed:\n" + stackTrace);

        KnownIssue knownIssue = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(KnownIssue.class);

        boolean isKnownIssue = null != knownIssue;
        if (isKnownIssue) {
            TestException testException = TestListenerUtil.getCustomException(result, knownIssue);
            result.setThrowable(testException);
        }

        boolean isPerformanceIssue = result.getThrowable() instanceof TestPerformanceException;
        ScreenshotUtil.getInstance().takeScreenshot(result, isKnownIssue, isPerformanceIssue);
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        if (!DriverManager.isDriverClosedOrNull()) {
            ScreenshotUtil.getInstance().takeScreenshot(result, false, false);
        }
        String stackTrace = TestListenerUtil.getStackTrace(result);
        log.info(stackTrace);
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(IRetryAnalyzer.class);
    }

    @Override
    public void onFinish(ITestContext context) {

        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            for (String s : RetryAnalyzer.getSkippedTestsSet()) {
                if (skippedTestCase.getMethod().toString().equals(s)) {
                    log.info("Skipped test case removed: " + skippedTestCase.getMethod().getMethodName());
                    skippedTestCases.remove();
                }
            }
        }
    }
}
