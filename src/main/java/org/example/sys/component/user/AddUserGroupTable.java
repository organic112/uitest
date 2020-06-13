package org.example.sys.component.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddUserGroupTable extends VGrid {

    public void selectRow(int row) {
        WebElement cell = getCell(row, AddUserGroupTable.Column.ALL);
        cell.findElement(By.cssSelector(".v-checkbox")).click();
    }

    @AllArgsConstructor
    @Getter
    private enum Column implements IColumn {
        ALL(0);
        private int index;
    }

}
