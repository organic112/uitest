package org.example.sys.common.uicomponent.simple;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

@Slf4j
public class InputField extends UIComponent {

    private WebElement getInputElement(){
        return getWrappedElement();
    }

    public String getValue() {
        return getInputElement().getAttribute("value");
    }

    public InputField setValue(String string){
        clear();
        getInputElement().sendKeys(string);
        return this;
    }

    public boolean isReadOnly(){
        return getInputElement().getAttribute("class").contains("v-textfield-readonly")
                || getInputElement().getAttribute("class").contains("v-disabled")
                || getInputElement().getAttribute("class").contains("v-filterselect-input-readonly")
                || getInputElement().getAttribute("class").contains("v-textarea-readonly");
    }

    private InputField clear() {
        getInputElement().clear();
        return this;
    }

    public InputField sendEnter(){
        getInputElement().sendKeys(Keys.ENTER);
        return this;
    }


}
