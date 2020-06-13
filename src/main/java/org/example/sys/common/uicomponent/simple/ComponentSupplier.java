package org.example.sys.common.uicomponent.simple;

import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.advanced.Modal;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class ComponentSupplier {

    private final static By modalSelector = By.cssSelector(".v-window-wrap");
    private final static By panelSelector = By.cssSelector(".v-tabsheet-content");

    public static <T extends Modal> T getModal(Class<T> clazz) {

        try {
            return SYSUIComponentUtil.getComponent(modalSelector, clazz);

        } catch (TimeoutException ignored) {
            throw new NoSuchElementException("Cannot find modal window for class: " + clazz.getSimpleName());
        }
    }

    public static Button getButton(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, Button.class);
    }

    public static CheckBox getCheckBox(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, CheckBox.class);
    }

    public static ComboButton getComboButton(SimpleUIElement element) {

        return SYSUIComponentUtil.getComponent(element, ComboButton.class);
    }
}

