package org.example.sys.webpages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainWebPage extends SYSWebPage {

    public MainWebPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sys-header-caption")));
    }

/*    public MainWebPage reloadPage(){
        getDriver().navigate().refresh();
        return getPage(MainWebPage.class);
    }*/

}
