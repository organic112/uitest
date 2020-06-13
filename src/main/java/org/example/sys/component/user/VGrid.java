package org.example.sys.component.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sys.common.uicomponent.simple.UIComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VGrid extends UIComponent {

    private By headerSelector = By.cssSelector(".v-grid-header");
    private By contentSelector = By.cssSelector(".v-grid-body");
    private By columnsSelector = By.tagName("th");


    public WebElement getCell(int row, IColumn column) {
        return getRows().get(row).findElements(By.tagName("td")).get(column.getIndex());
    }

    private List<WebElement> getRows() {
        return getContent().findElements(By.tagName("tr"));
    }

    private WebElement getContent(){
        return getWrappedElement().findElement(contentSelector);
    }



    @AllArgsConstructor
    @Getter
    private enum Column {
        ALL(0);
        private int index;
    }

    public interface IColumn {
        int getIndex();
    }


}
