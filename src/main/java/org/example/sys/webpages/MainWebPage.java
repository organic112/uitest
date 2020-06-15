package org.example.sys.webpages;


import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainWebPage extends SYSWebPage {

    public MainWebPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {
         //getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sys-header-caption")));
         getWait().until(ExpectedConditions.visibilityOfElementLocated(By.tagName("sys-page")));
    }

    public void clickLandingPageButton(){

        WebElement button = getDriver().findElement(By.cssSelector("#LoadingPageButton_id"));
        button.click();
    }
}
