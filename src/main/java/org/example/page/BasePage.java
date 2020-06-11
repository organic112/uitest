package org.example.page;

import org.example.common.util.PageUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final int DEFAULT_TIMEOUT = 60;
    private static WebDriver driver;
    private static WebDriverWait wait;

    protected BasePage(WebDriver driver){

        BasePage.driver = driver;
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

    protected <T extends BasePage> T getPage(Class<T> clazz){

        return PageUtil.getPage(clazz);
    }

}
