package org.example.sys.common.uicomponent.simple;

public class Button extends UIComponent {

    public void click() { getWrappedElement().click();}

    public boolean isDisabled() {
        return getWrappedElement().getAttribute("class").contains("v-disabled");
    }

}
