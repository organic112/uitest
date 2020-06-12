package org.example.component;

import org.example.common.util.TextException;
import org.example.component.simple.Component;
import org.example.component.simple.ISimpleElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ComponentUtil {

    public static <T extends Component> T getComponent(By by, Class<T> clazz) {

        try {
            T component = clazz.newInstance();
            component.init(by);
            return component;
        } catch (InstantiationException | IllegalAccessException e) {
            e.getStackTrace();
            throw new TextException("Problem with instantiating component class:" + clazz.getSimpleName());
        }
    }

    public static <T extends Component> T getComponent(WebElement webElement, Class<T> clazz) {
        try {
            T component = clazz.newInstance();
            component.init(webElement);
            return component;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new TextException("Problem with instantiation component class: " + clazz.getSimpleName());
        }
    }


    public static <T extends Component> T getComponent(ISimpleElement element, Class<T> clazz) {

        if (element.getConnector() instanceof String) {
            By selector = By.xpath("//*[text()='" + element.getConnector() + "']/../..");
            return getComponent(selector, clazz);
        }
        if (element.getConnector() instanceof By) {
            return getComponent((By) element.getConnector(), clazz);
        }
        throw new TextException(String.format("Type %s not supported", element.getConnector().getClass()));
    }


    public static <T extends Component> T getComponent(WebElement context, ISimpleElement element, Class<T> clazz) {

        if (element.getConnector() instanceof String) {
            By selector = By.xpath(".//*[text()='" + element.getConnector() + "']/../..");
            WebElement componentElement = context.findElement(selector);
            return getComponent(componentElement, clazz);
        }
        if (element.getConnector() instanceof By) {
            WebElement componentElement = context.findElement((By) element.getConnector());
            return getComponent(componentElement, clazz);
        }
        throw new TextException(String.format("Type %s not supported", element.getConnector().getClass()));
    }
}
