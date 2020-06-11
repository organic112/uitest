package org.example.sys.utils;

import org.testng.TestException;

public class TestPerformanceException extends TestException {

    public TestPerformanceException(String msg) {
        super("Predicting preformance issues:\n" + msg);
    }
}
