package org.example.sys.sys.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class RetryAnalyser implements IRetryAnalyzer {

    private int count = 0;
    private final static int maxTry = 2;
    private static Set<String> skippedTestsToDelete = new HashSet<>();


    @Override
    public boolean retry(ITestResult iTestResult) {
        if (PropertySupplier.readRetryRunnerProperty()) {
            if (!iTestResult.isSuccess()) {
                if (count < maxTry) {
                    count++;
                    skippedTestsToDelete.add(iTestResult.getMethod().toString());
                    log.error("Skipped cause: " + iTestResult.getThrowable() + Joiner.on("\n")
                            .join(Iterables.limit(Arrays.asList(iTestResult.getThrowable().getStackTrace()), 4)));
                    iTestResult.setStatus(ITestResult.FAILURE);
                    return true;
                } else {
                    iTestResult.setStatus(ITestResult.FAILURE);
                    count = 0;
                }
            } else {
                iTestResult.setStatus(ITestResult.SUCCESS);
            }
        }
        return false;
    }

    public static Set<String> getSkippedTestsSet() {
        return skippedTestsToDelete;
    }
}
