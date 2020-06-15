package org.example.sys.webpages;


import lombok.extern.slf4j.Slf4j;
import org.example.sys.common.uicomponent.advanced.Modal;
import org.example.sys.common.util.CommonUtil;
import org.example.sys.common.util.NavMenuButton;
import org.example.sys.common.util.PageUtil;
import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.*;
import org.example.sys.component.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
public abstract class SYSWebPage extends BaseWebPage {

    protected SYSWebPage(WebDriver driver) {
        super(driver);
    }


    public MainWebPage reloadPage() {
        getDriver().navigate().refresh();
        return getPage(MainWebPage.class);
    }

    public MainMenu getMainMenu() {

        return SYSUIComponentUtil.getComponent(By.cssSelector("#Menu_mainMenu_id"), MainMenu.class);
    }

    public String getNotificationText() {

        return getDriver().findElement(By.cssSelector(".v-Notification")).getText();
    }

    public String getNotificationTextAndClose(boolean closeNotification) {

        return PageUtil.getNotificationText(closeNotification);
    }

    public LoginWebPage logout() {

        getButton(() -> "LOGOUT").click();
        return getPage(LoginWebPage.class);
    }

    public void clickNotificationEnvelop() {
        getDriver().findElement(By.xpath("//div[contains(@class, 'sys-iconbutton-notification')]")).click();
        CommonUtil.sleep(1000);
    }

    public ComboBox getComboBox(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, ComboBox.class);
    }

    public Button getButton(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, Button.class);
    }

    public InputField getInputField(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, InputField.class);
    }

    public CheckBox getCheckbox(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, CheckBox.class);
    }

    protected ComboButton getComboButton(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, ComboButton.class);
    }

    protected <T extends Modal> T getModal(Class<T> clazz) {

        return ComponentSupplier.getModal(clazz);
    }

    private List<WebElement> getCloseButtonElements() {
        WebElement tabsheetElement = getDriver().findElement(By.cssSelector(".v-tabsheet-tabs"));
        return tabsheetElement.findElements(By.cssSelector("v-tabsheet-caption-close"));
    }

    private boolean isVisible(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException | TimeoutException ignored) {
            return false;
        }
    }

    public boolean isVisible(String label) {
        PageUtil.waitForLoadingIndicator();
        return isVisible(By.xpath("//*[text()='" + label + "']"));
    }

    public WebElement getTab(String text) {

        return getDriver().findElement(By.xpath(String.format("//div[@class='v-captiontext' and text()='%s']", text)));
    }

    public String getActiveTabName() {
        return getDriver().findElement(By.xpath("//div[contains(@class, 'v-tabsheet-tabitem-focus')]//div[contains(@class, 'v-caption-closable']//div[@class='v-captiontext']")).getText();
    }

    public <T extends SYSWebPage> T closeCurrentTab(Class<T> type) {
        getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//td[contains(@class, 'v-tabsheet-tabitemcell-selected')]//span[text()='x']")))).click();
        return getPage(type);
    }

    public ConfirmationModal closeCurrentTabAndGetConfirmationModal() {
        getWait().until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//td[contains(@class, 'v-tabsheet-tabitemcell-selected')]//span[text()='x']")))).click();

        return SYSUIComponentUtil.getComponent(() -> By.cssSelector(".v-window.v-widget"),
                ConfirmationModal.class);
    }

    public void closeAllTabs() {
        List<WebElement> closeButtons = getCloseButtonElements();
        if (closeButtons.size() > 0) {
            log.info("Some tabs remain opened - attempting to close it");
            do {
                closeButtons.get(0).click();
                try {
                    WebElement modalElement = getDriver().findElement(By.cssSelector(".v-window-warp"));
                    ConfirmationModal modal = SYSUIComponentUtil.getComponent(modalElement, ConfirmationModal.class);
                    modal.clickButton(() -> "Discard", true);
                } catch (NoSuchElementException ignored) {
                }
                closeButtons = getCloseButtonElements();

            } while (closeButtons.size() > 0);
        }
    }

    public void closeAndRemainingPopupAndNotifications() {

        getDriver().findElements(By.xpath("//*[@class='v-window-closebox']")).stream().findFirst().ifPresent(WebElement::click);
        List<WebElement> bulkActionElements = getDriver().findElements(By.xpath("//*[@class='v-expand'][div//span[text()='Mark all as read']]"));
        if (!bulkActionElements.isEmpty()) {
            clickNotificationEnvelop();
        }
    }

    public boolean checkIfNavigationMenuContainsButton(NavMenuButton buttonName) {
        WebElement actionBarElement = getDriver().findElement(By.cssSelector("#subNavMenu .v-tabsheet-tabsheetpanel"));
        String actionBarText = actionBarElement.getText();
        return actionBarText.contains(buttonName.getButtonName());
    }

}
