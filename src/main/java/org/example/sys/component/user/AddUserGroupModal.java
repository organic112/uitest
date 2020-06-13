package org.example.sys.component.user;

import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.ConfirmationModal;
import org.example.sys.common.uicomponent.advanced.Modal;
import org.openqa.selenium.By;

import static org.example.sys.common.uicomponent.simple.ComponentSupplier.getButton;

public class AddUserGroupModal extends Modal {

    public AddUserGroupTable getAddUserGroupTable(){
        AddUserGroupTable table = SYSUIComponentUtil
                .getComponent(getWrappedElement(), ()-> By.cssSelector(".v-table"), AddUserGroupTable.class);
        //table.setAsTable();
        return table;
    }

    public ConfirmationModal clickAndGetConfirmationModal(){
        getButton(() -> "Add selected  User Groups").click();
        return SYSUIComponentUtil.getComponent(() -> By.cssSelector("#confirmdialog-window"), ConfirmationModal.class);
    }

}
