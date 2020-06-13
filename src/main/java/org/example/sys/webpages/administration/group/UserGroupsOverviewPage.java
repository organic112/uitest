package org.example.sys.webpages.administration.group;

import org.example.sys.common.util.PageUtil;
import org.example.sys.webpages.SYSWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserGroupsOverviewPage extends SYSWebPage {

    public UserGroupsOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPage() {

        PageUtil.waitForLoadingIndicator();
        getWait().until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[text()='UserGroups Overview']")));
    }


}
