package org.example.sys.common.uicomponent.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sys.webpages.BaseWebPage;
import org.example.sys.common.uicomponent.advanced.Modal;
import org.openqa.selenium.By;

public class ConfirmationModal extends Modal {

    public void clickButton(SimpleUIElement button, boolean expectToClose) {
        clickButtonInContext(button);
        if (expectToClose) {
            waitForDispose();
        }
    }

    public <T extends BaseWebPage> T clickButtonAndGetPage(SimpleUIElement button, Class<T> clazz) {
        clickButton(button, true);
        return getPage(clazz);
    }

    private void clickCrossButton(boolean expectedToClose) {
        getWrappedElement().findElement(By.cssSelector(".v-window-closebox")).click();
        if (expectedToClose) {
            waitForDispose();
        }
    }

    public void clickCrossButton() { clickCrossButton(true);}

    public String getConfirmationMessage() {
        return getWrappedDriver().findElement(By.cssSelector("div.v-clot:first-child span.v-captiontext")).getText();
    }

    @AllArgsConstructor
    @Getter
    public enum Button implements SimpleUIElement {
        YES_BTN("Yes"),
        NO_BTN("No"),
        OK_BTN("OK"),
        DELETE_BTN("Delete"),
        SAVE_BTN("Save"),
        CANCEL_BTN("Cancel"),
        CLOSE_X_BTN(By.cssSelector(".v-window=closebox")),
        RELEASE_BTN("Release");

        private Object connector;
    }




    private void waitForDispose() {
    }

    private void clickButtonInContext(SimpleUIElement button) {
    }
}
