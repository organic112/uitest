package org.example.sys.webpages.administration.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.*;
import org.example.sys.common.util.PageUtil;
import org.example.sys.component.user.UserGroupsPanel;
import org.example.sys.common.uicomponent.advanced.Modal;
import org.example.sys.webpages.SYSWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserPage extends SYSWebPage {


    public UserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {
        PageUtil.waitForLoadingIndicator();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='TODO/TODO']/..")));
    }

    public boolean isInReadMode() {
        WebElement actionsBarElement = getDriver().findElement(By.cssSelector("#SYS_toolbar_id"));
        String actionBarText = actionsBarElement.getText();

        return actionBarText.contains("Edit User") || !actionBarText.contains("Exit");
    }

    public String getItemStatus() {
        return getDriver().findElement(By.cssSelector(".v-label-status-label")).getText();
    }

    public UserPage clickEdit() {
        getButton(() -> "Edit User").click();
        return getPage(UserPage.class);
    }

    public UserPage clickExit() {
        getButton(() -> "Exit").click();
        return getPage(UserPage.class);
    }

    public UserGroupsPanel clickUserGroupPanel() {

        getButton(() -> "User Group panel").click();
        PageUtil.waitForLoadingIndicator();
        return SYSUIComponentUtil.getComponent(() -> By.cssSelector(".v-margin-left"), UserGroupsPanel.class);
    }

    public UserPage clickSave() {
        SYSUIComponentUtil.getComponent(Element.SAVE_USER_BUTTON, Button.class).click();
        return getPage(UserPage.class);
    }

    public String getUserName() {
        return getDriver().findElement(By.cssSelector(".v-slot-sys-code-value-label")).getText();
    }

    public ComboButton getStatusChangeComboButton() {
        return getComboButton(() -> "Change status");
    }

    public String getStatus() {
        return getDriver().findElement(By.cssSelector(".v-slot-status-label")).getText();
    }

    public <T extends Modal> T selectAndGetModal(String option, Class<T> clazz) {
        selectOption(option);
        return ComponentSupplier.getModal(clazz);
    }

    public void selectOption(String label) {
        ComponentSupplier.getComboButton(Element.CHANGE_STATUS_BUTTON).selectOption(label);
    }

    public CheckBox getCheckBox(SimpleUIElement element) {
        return ComponentSupplier.getCheckBox(element);
    }

    @AllArgsConstructor
    @Getter
    public enum Element implements SimpleUIElement {
        CHANGE_STATUS_BUTTON("Change status"),
        SAVE_USER_BUTTON("Save user");

        // TODO adjust
        private Object connector;
    }
}
