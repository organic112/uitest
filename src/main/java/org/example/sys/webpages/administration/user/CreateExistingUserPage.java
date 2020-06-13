package org.example.sys.webpages.administration.user;

import org.example.sys.common.util.PageUtil;
import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.InputField;
import org.example.sys.webpages.SYSWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateExistingUserPage extends SYSWebPage {

    public CreateExistingUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {
        PageUtil.waitForLoadingIndicator();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Create existing user']")));
    }

    public InputField getUserNameInputField() {
        return SYSUIComponentUtil.getComponent(CreateNewUserPage.Element.USER_INPUT, InputField.class);
    }


}
