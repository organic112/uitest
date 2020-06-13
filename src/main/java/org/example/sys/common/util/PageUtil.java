package org.example.sys.common.util;

import lombok.extern.slf4j.Slf4j;
import org.example.sys.common.exception.TestException;
import org.example.sys.webpages.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

import static org.example.sys.webpages.BaseWebPage.getDriver;
import static org.example.sys.webpages.BaseWebPage.getWait;


@Slf4j
public class PageUtil {

    private static final int LOADING_INDICATOR_TIMEOUT = 60;

    private static final By LOADING_INDICATOR_SELECTOR = By.cssSelector("v-loading-indicator");

    public static <T extends BaseWebPage> T getPage(Class<T> clazz) {

        try {
            T page = clazz.getDeclaredConstructor(WebDriver.class).newInstance(getDriver());
            page.waitForPage();
            return page;
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException
                | IllegalAccessException | TimeoutException e) {

            throw new TestException("Cannot bind class" + clazz.getSimpleName() + e.toString());
        }
    }

    public static String getNotificationText(boolean closeNotification) {

        return "TODO FIXME";
    }

    public static void waitForLoadingIndicator() {

        System.out.println("TODO in Page Util");
    }

    public static void fastWaitForLoadingIndicator() {
        try {
            getWait().withTimeout(Duration.ofMillis(250L)).pollingEvery(Duration.ofMillis(125L))
                    .until(ExpectedConditions.visibilityOfElementLocated(LOADING_INDICATOR_SELECTOR));
        } catch (TimeoutException ignored) {
        }
    }


}
