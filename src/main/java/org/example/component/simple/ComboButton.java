package org.example.component.simple;

import org.example.common.util.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class ComboButton extends Component {

    private static final By menuSelector = By.cssSelector(".v-menubar-submenu");
    private static final int comboButtonTimeout = 5;


    public ComboButton expand() {
        getWrappedElement().click();
        getWait(comboButtonTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(menuSelector));
        return this;
    }

    public ComboButton collapse() {
        getWrappedElement().click();
        getWait(comboButtonTimeout).until(ExpectedConditions.invisibilityOfElementLocated(menuSelector));
        return this;
    }

    private List<WebElement> getOptionsElements() {
        WebElement menuElement = getWait(comboButtonTimeout)
                .until(ExpectedConditions.visibilityOfElementLocated(menuSelector));
        return menuElement.findElements(By.cssSelector(".v-menubar-menuitem"));
    }

    public List<String> getOptions() {
        return CommonUtil.webElementToString(getOptionsElements());
    }

    public void selectOption(String label) {

        for (WebElement element : getOptionsElements()) {

            if (element.getText().contains(label)) {
                element.click();
            }

            getWait(comboButtonTimeout).until(ExpectedConditions.invisibilityOfElementLocated(menuSelector));
            return;
        }
        throw new NoSuchElementException("Option not found: " + label);
    }

}
