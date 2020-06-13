package org.example.sys.common.uicomponent.simple;

import org.openqa.selenium.*;

public class CheckBox extends UIComponent {

    private boolean isSelected() {
        return getWrappedElement().findElement(By.tagName("input")).isSelected();
    }

    public void click() {

        try {
            getWrappedElement().findElement(By.tagName("label")).click();
        } catch (WebDriverException e) {
            try {
                getWrappedElement().findElement(By.tagName("label")).click();

            } catch (WebDriverException wde) {
                getWrappedElement().findElement(By.tagName("input")).click();
            }
        }
    }

    private boolean isSelectedJs() {
        JavascriptExecutor js = (JavascriptExecutor) getWrappedDriver();

        return js.executeScript("return arguments[0].checked;", getWrappedElement().findElement(By.tagName("input")))
                .toString().contains("true");
    }

    public boolean isCheckBoxSelected() {
        return isSelectedJs() || isSelected();
    }

    public boolean isReadOnly() {
        try {
            String classAttribute = getWrappedElement().findElement(By.tagName("span")).getAttribute("class");

            return classAttribute.contains("v-readonly") || classAttribute.contains("v-checkbox-disabled")
                    || classAttribute.contains("v-disabled");

        } catch (NoSuchElementException ignore) {
            return getWrappedElement().getAttribute("class").contains("disabled");
        }
    }
}
