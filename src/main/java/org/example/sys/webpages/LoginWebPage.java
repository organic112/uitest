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
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".v-window-header")));
    }

    public MainWebPage login(String url, String user, String pwd){

        getDriver().get(url);

        WebElement userLoginElement = getDriver().findElement(By.cssSelector("#loginField"));
        WebElement userPasswordElement = getDriver().findElement(By.cssSelector("#passwordField"));
        WebElement loginButtonElement = getDriver().findElement(By.cssSelector("#loginButton"));

        userLoginElement.sendKeys(user);
        userPasswordElement.sendKeys(pwd);
        loginButtonElement.click();
        return getPage(MainWebPage.class);
    }
}
