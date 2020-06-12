package org.example.component.simple;

import org.example.component.ComponentUtil;
import org.example.page.Modal;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class ComponentSupplier {

    private final static By modalSelector = By.cssSelector(".v-window-wrap");
    private final static By panelSelector = By.cssSelector(".v-tabsheet-content");

    public static <T extends Modal> T getModal(Class<T> clazz) {

        try {
            return ComponentUtil.getComponent(modalSelector, clazz);

        } catch (TimeoutException ignored) {
            throw new NoSuchElementException("Cannot find modal window for class: " + clazz.getSimpleName());
        }
    }

    public static Button getButton(ISimpleElement element) {

        return ComponentUtil.getComponent(element, Button.class);
    }

    public static Checkbox getCheckBox(ISimpleElement element) {

        return ComponentUtil.getComponent(element, Checkbox.class);
    }

    public static ComboButton getComboButton(ISimpleElement element) {

        return ComponentUtil.getComponent(element, ComboButton.class);
    }
}

