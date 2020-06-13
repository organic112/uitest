package org.example.sys.component.user;

import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.advanced.Panel;
import org.openqa.selenium.By;

import static org.example.sys.common.uicomponent.simple.ComponentSupplier.getButton;

public class UserGroupsPanel extends Panel {

    public AddUserGroupModal clickAdd(){

        getButton(() -> "Add").click();
        return SYSUIComponentUtil.getComponent(() -> By.cssSelector(".v-window-wrap"), AddUserGroupModal.class);
    }


}
