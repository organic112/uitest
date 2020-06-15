package org.example.sys.user;

import org.assertj.core.api.SoftAssertions;
import org.example.sys.SysBaseTest;
import org.example.sys.webpages.MainWebPage;
import org.testng.annotations.Test;

public class MainWebPageTest extends SysBaseTest {


    @Test(invocationCount = 3)  // button clicked 3 times
    public void createNewUser() {

        SoftAssertions soft = new SoftAssertions();

        // example button click form test
        MainWebPage mainWebPage = getMainWebPage();
        mainWebPage.clickLandingPageButton();
    }

    @Test(invocationCount = 3)
    public void createExistingUser() {

        SoftAssertions soft = new SoftAssertions();

    }

}
