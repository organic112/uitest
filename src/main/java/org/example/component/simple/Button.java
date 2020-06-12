package org.example.component.simple;

public class Button extends Component {

    public void click() { getWrappedElement().click();}

    public boolean isDisabled() {
        return getWrappedElement().getAttribute("class").contains("v-disabled");
    }

}
