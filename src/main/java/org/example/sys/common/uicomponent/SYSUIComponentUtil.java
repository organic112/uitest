package org.example.sys.common.uicomponent;

import org.example.sys.common.exception.TestException;
import org.example.sys.common.uicomponent.simple.SimpleUIElement;
import org.example.sys.common.uicomponent.simple.UIComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SYSUIComponentUtil {


    public static <T extends UIComponent> T getComponent(By by, Class<T> clazz) {
        try {
            T component = clazz.newInstance();
            component.init(by);
            return component;
        } catch (InstantiationException | IllegalAccessException e) {
            e.getStackTrace();
            throw new TestException("Problem with instantiating component class:" + clazz.getSimpleName());
        }
    }

    public static <T extends UIComponent> T getComponent(WebElement webElement, Class<T> clazz) {
        try {
            T component = clazz.newInstance();
            component.init(webElement);
            return component;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new TestException("Problem with instantiation component class: " + clazz.getSimpleName());
        }
    }


    public static <T extends UIComponent> T getComponent(SimpleUIElement element, Class<T> clazz) {

        if (element.getConnector() instanceof String) {
            By selector = By.xpath("//*[text()='" + element.getConnector() + "']/../..");
            return getComponent(selector, clazz);
        }
        if (element.getConnector() instanceof By) {
            return getComponent((By) element.getConnector(), clazz);
        }
        throw new TestException(String.format("Type %s not supported", element.getConnector().getClass()));
    }


    public static <T extends UIComponent> T getComponent(WebElement context, SimpleUIElement element, Class<T> clazz) {

        if (element.getConnector() instanceof String) {
            By selector = By.xpath(".//*[text()='" + element.getConnector() + "']/../..");
            WebElement componentElement = context.findElement(selector);
            return getComponent(componentElement, clazz);
        }
        if (element.getConnector() instanceof By) {
            WebElement componentElement = context.findElement((By) element.getConnector());
            return getComponent(componentElement, clazz);
        }
        throw new TestException(String.format("Type %s not supported", element.getConnector().getClass()));
    }


}
