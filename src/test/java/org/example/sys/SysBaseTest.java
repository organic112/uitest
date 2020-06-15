package org.example.sys;

import lombok.extern.slf4j.Slf4j;
import org.example.sys.helper.AdministrationHelper;
import org.example.sys.sys.utils.DriverManager;
import org.example.sys.sys.utils.PropertySupplier;
import org.example.sys.webpages.LoginWebPage;
import org.example.sys.webpages.MainWebPage;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.*;

@Slf4j
public class SysBaseTest {

    private static MainWebPage mainWebPage;
    private AdministrationHelper administrationHelper;

    protected MainWebPage getMainWebPage() {
        return mainWebPage;
    }

    @BeforeTest(alwaysRun = true)
    public void before(ITestContext iTestContext) {

        DriverManager.initDriver();
        LoginWebPage loginWebPage = new LoginWebPage(DriverManager.getDriver());

        String url = PropertySupplier.getSYSUrl();
        String userLogin = PropertySupplier.getSYSUserLogin();
        String userPass = PropertySupplier.getSYSUserPassword();

        mainWebPage = loginWebPage.login(url, userLogin, userPass);
    }

    @AfterTest(alwaysRun = true)
    public void after() {
        try {
            log.info("Tear down after suite test");
            mainWebPage.logout();
            log.info("Tear down after suite successed");
        } catch (Exception e) {

            log.error("Tear down after suite failed: " + e.getLocalizedMessage());
        } finally {
            DriverManager.getDriver().quit();
        }
    }

    @AfterMethod
    public void afterTest() {

        mainWebPage.reloadPage();
        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            Future<Void> closeTabsWithTimeout = service.submit(() -> {
                mainWebPage.closeAllTabs();
                return null;
            });
            closeTabsWithTimeout.get(2, TimeUnit.MINUTES);

        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        } catch (TimeoutException e) {
            log.error("Closing tabs took more than 2 minutes");
        } finally {
            service.shutdown();
        }
    }
}
