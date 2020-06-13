package org.example.sys.webpages.administration.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sys.common.util.PageUtil;
import org.example.sys.common.uicomponent.simple.CheckBox;
import org.example.sys.common.uicomponent.simple.ComponentSupplier;
import org.example.sys.common.uicomponent.simple.SimpleUIElement;
import org.example.sys.webpages.SYSWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsersOverviewPage extends SYSWebPage {


    public UsersOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {

        PageUtil.waitForLoadingIndicator();
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[text()='User Overview']")));
    }

    public CreateNewUserPage clickCreateNew() {
        getButton(Element.CREATE_NEW_BUTTON).click();
        return getPage(CreateNewUserPage.class);
    }

    public CreateExistingUserPage clickCreateExisting() {
        getButton(Element.CREATE_NEW_BUTTON).click();
        return getPage(CreateExistingUserPage.class);
    }

    public void clickSearch() {
        getButton(Element.SEARCH_BUTTON).click();
        PageUtil.waitForLoadingIndicator();
    }

    public UsersOverviewPage setUser(String userName) {
        getInputField(Element.USER_INPUT).setValue(userName);
        return this;
    }

    public CheckBox getCheckBox(SimpleUIElement element) {
        return ComponentSupplier.getCheckBox(element);
    }

    @AllArgsConstructor
    @Getter
    public enum Element implements SimpleUIElement {
        CREATE_NEW_BUTTON("Create new"),
        USER_INPUT(By.cssSelector("#Overview_user_id")),
        SEARCH_BUTTON("Search");

        // TODO adjust to page
        private Object connector;
    }
}
