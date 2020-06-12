package org.example.component.simple;

import org.example.common.util.PageUtil;
import org.example.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Component {

    private static final int TIMEOUT_DEFAULT = 5;

    private WebElement wrappedElement;

    public void init(By by) {
        try {
            wrappedElement = BasePage.getWait(TIMEOUT_DEFAULT).until(ExpectedConditions.visibilityOfElementLocated(by));

        } catch (TimeoutException e) {
            throw new TimeoutException(String.format("Cannot init component %s by selector: %s", this.getClass(), by.toString()));
        }
    }

    public void init(WebElement element) {
        wrappedElement = element;
    }

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    protected WebDriver getWrappedDriver() {
        return BasePage.getDriver();
    }

    protected WebDriverWait getWait(int timeout) {
        return BasePage.getWait(timeout);
    }

    protected void clickButton(ISimpleElement element) {
        ComponentSupplier.getButton(element).click();
    }


    protected <T extends BasePage> T getPage(Class<T> clazz){
        return PageUtil.getPage(clazz);
    }


}
