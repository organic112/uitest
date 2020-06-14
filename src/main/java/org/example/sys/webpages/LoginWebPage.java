package org.example.sys.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginWebPage extends BaseWebPage {


    public LoginWebPage(WebDriver driver){
        super(driver);
    }

    @Override
    public void waitForPage() {
        //getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-window-header")));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("loginLayout")));
    }

    public MainWebPage login(String url, String user, String pwd){

        getDriver().get(url);

        WebElement userLoginElement = getDriver().findElement(By.cssSelector("#vaadinLoginUsername"));
        WebElement userPasswordElement = getDriver().findElement(By.cssSelector("#vaadinLoginPassword"));
        WebElement loginButtonElement = getDriver().findElement(By.xpath(".//*[@part='vaadin-login-submit' and text()='Login']")); // TODO selector by Xpath

        userLoginElement.sendKeys(user);
        userPasswordElement.sendKeys(pwd);
        loginButtonElement.click();
        return getPage(MainWebPage.class);
    }
}
