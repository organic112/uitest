package org.example.sys.webpages;

import org.example.sys.common.util.PageUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseWebPage {

    private static final int DEFAULT_TIMEOUT = 60;
    private static WebDriver driver;
    private static WebDriverWait wait;

    protected BaseWebPage(WebDriver driver){

        BaseWebPage.driver = driver;
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait(int timeoutInSeconds) {
        wait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
        return wait;
    }

    public static WebDriverWait getWait() {
        return getWait(DEFAULT_TIMEOUT);
    }

    public abstract void waitForPage();

    protected <T extends BaseWebPage> T getPage(Class<T> clazz){

        return PageUtil.getPage(clazz);
    }
}
