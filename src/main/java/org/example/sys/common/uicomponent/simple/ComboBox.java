package org.example.sys.common.uicomponent.simple;

import org.example.sys.common.exception.TestException;
import org.example.sys.common.util.CommonUtil;
import org.example.sys.common.util.PageUtil;
import org.example.sys.common.uicomponent.advanced.Modal;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ComboBox extends UIComponent {


    private static final int EXPAND_TIMEOUT = 10;
    private static final int SELECT_OPTION_TIMEOUT = 2;
    private static final int DEFAULT_TIMEOUT = 2;

    public ComboBox expand() {

        getWrappedElement().findElement(By.cssSelector(".v-filterselect-button")).click();
        PageUtil.fastWaitForLoadingIndicator();
        return this;
    }

    public List<String> getOptionsLabels() {

        return CommonUtil.webElementToString(getOptions());
    }

    public void selectOption(String label) {
        selectOption(label, false);
    }


    private void selectOption(String label, boolean withWait) {

        for (WebElement option : getOptions()) {
            if (option.getText().contains(label)) {
                option.click();
                if (withWait) {
                    getWait(SELECT_OPTION_TIMEOUT).until(driver -> getValue().contains(label));
                    PageUtil.fastWaitForLoadingIndicator();
                }
                return;
            }
        }
    }

    public ComboBox selectOption(int index) {
        return selectOption(index, false);
    }

    public ComboBox selectOption(int index, boolean withWait) {

        List<WebElement> options = getOptions();

        if (index >= options.size()) {
            throw new TestException(String.format("Not enough options count in combobox (index=%s, size=%s)", index, options.size()));
        }

        String optionText = options.get(index).getText();
        options.get(index).click();

        if (withWait) {
            getWait(SELECT_OPTION_TIMEOUT).until(driver -> getValue().contains(optionText));
            PageUtil.fastWaitForLoadingIndicator();
        }
        return this;
    }

    private String getValue() {
        return getInputElement().getAttribute("value");
    }

    public ComboBox setValue(String text) {
        clear();
        return setValue(text, false);
    }

    public ComboBox setValue(String text, boolean sendEnter) {
        clear();
        WebElement input = getInputElement();
        input.sendKeys(text);
        if (sendEnter) {
            input.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public ComboBox setValueAndSelect(String text) {
        setValue(text);
        PageUtil.fastWaitForLoadingIndicator();
        return selectOption(0);
    }

    public boolean isReadOnly() {
        return getInputElement().getAttribute("class").contains("v-filterselect-input-readonly");
    }


    private ComboBox clear() {
        getInputElement().clear();
        return this;
    }

    public ComboBox waitUntilEnabled() {
        WebElement element = getComboBoxElement();
        try {
            getWait(DEFAULT_TIMEOUT).until(v -> !element.getAttribute("class").contains("v-disabled"));
        } catch (TimeoutException e) {
            throw new TestException("Combobox remains disabled");
        }
        return this;
    }

    public boolean isDisabled() {

        return getComboBoxElement().getAttribute("class").contains("v-disabled") || getComboBoxElement().getAttribute("class").contains("v-readonly");
    }

    private WebElement getComboBoxElement() {
        return getWrappedElement();
    }

    private WebElement getInputElement() {
        return getWrappedElement().findElement(By.cssSelector(".v-filterselect-input"));
    }

    private List<WebElement> getOptions() {

        By elementSelector = By.xpath("//*[@class='v-filterselect-suggestmenu']//td");
        try {
            getWait(EXPAND_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementSelector));
        } catch (TimeoutException e) {
            throw new TestException("Combobox with no values!");
        }
        PageUtil.fastWaitForLoadingIndicator();
        return getWrappedDriver().findElements(elementSelector);
    }

    public <T extends Modal> T selectOptionAndGetModal(String option, Class<T> clazz) {
        selectOption(option);
        return ComponentSupplier.getModal(clazz);
    }
}
