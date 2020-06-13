package org.example.sys.webpages.administration.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sys.common.util.PageUtil;
import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.Button;
import org.example.sys.common.uicomponent.simple.CheckBox;
import org.example.sys.common.uicomponent.simple.ComponentSupplier;
import org.example.sys.common.uicomponent.simple.SimpleUIElement;
import org.example.sys.webpages.SYSWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Handles operation over 'create new user' web page
 */
public class CreateNewUserPage extends SYSWebPage {

    public CreateNewUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {
        PageUtil.waitForLoadingIndicator();
        getWait().until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Create new User']"))));
    }

    public CheckBox getCheckBox(SimpleUIElement element) {

        return ComponentSupplier.getCheckBox(element);
    }

    public String getActiveFlag() {
        return getDriver().findElement(By.cssSelector(".v-label-sys-bold-label")).getText();
    }

    /**
     * Provides email
     */
    public String getEmail() {
        return getDriver().findElement(By.xpath("//*[text()='Email']/../../..")).getText();
    }

    public UserPage clickSave() {
        SYSUIComponentUtil.getComponent(Element.SAVE_USER_BUTTON, Button.class).click();
        return getPage(UserPage.class);
    }

    @AllArgsConstructor
    @Getter
    public enum Element implements SimpleUIElement {
        SAVE_USER_BUTTON("Save user"),
        USER_INPUT(By.cssSelector(".v-textfield-error")),
        EMAIL(By.xpath("//*[text()='Email']/../../../.."));

        private Object connector;
    }
}


