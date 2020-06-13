package org.example.sys.common.uicomponent.simple;

import org.example.sys.common.util.PageUtil;
import org.example.sys.webpages.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIComponent {

    private static final int TIMEOUT_DEFAULT = 5;

    private WebElement wrappedElement;

    public void init(By by) {
        try {
            wrappedElement = BaseWebPage.getWait(TIMEOUT_DEFAULT).until(ExpectedConditions.visibilityOfElementLocated(by));

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
        return BaseWebPage.getDriver();
    }

    protected WebDriverWait getWait(int timeout) {
        return BaseWebPage.getWait(timeout);
    }

    protected void clickButton(SimpleUIElement element) {
        ComponentSupplier.getButton(element).click();
    }


    protected <T extends BaseWebPage> T getPage(Class<T> clazz){
        return PageUtil.getPage(clazz);
    }


}
