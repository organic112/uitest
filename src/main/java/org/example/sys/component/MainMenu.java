package org.example.sys.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sys.common.uicomponent.SYSUIComponentUtil;
import org.example.sys.common.uicomponent.simple.ComboButton;
import org.example.sys.common.uicomponent.simple.SimpleUIElement;
import org.example.sys.common.uicomponent.simple.UIComponent;
import org.example.sys.webpages.SYSWebPage;
import org.example.sys.webpages.administration.group.UserGroupsOverviewPage;
import org.example.sys.webpages.administration.user.UsersOverviewPage;

public class MainMenu extends UIComponent {


    public <T extends SYSWebPage> T openPage(SubMenuOption subMenuOption) {

        getComboButton(subMenuOption.getParent())
                .expand()
                .selectOption(subMenuOption.getLabel());

        return getPage((Class<T>) subMenuOption.getExpectedViewClass());
    }

    private ComboButton getComboButton(MenuOption subMenu) {

        return SYSUIComponentUtil.getComponent(subMenu, ComboButton.class);
    }


    @AllArgsConstructor
    @Getter
    private enum MenuOption implements SimpleUIElement {

        ADMINISTRATION("Administration"),
        CARS("Cars");

        private Object connector;
    }

    @AllArgsConstructor
    @Getter
    public enum SubMenuOption {

        USER_LIST_PAGE(MenuOption.ADMINISTRATION, "Users", UsersOverviewPage.class),
        USER_GROUPS_PAGE( MenuOption.ADMINISTRATION, "User Groups", UserGroupsOverviewPage.class);

        //USER_PERMISSIONS(MenuOption.ADMINISTRATION, "Permissions", UserPermissions ) - not overview

        private MenuOption parent;
        private String label;
        private Class<? extends  SYSWebPage> expectedViewClass;
    }

}
