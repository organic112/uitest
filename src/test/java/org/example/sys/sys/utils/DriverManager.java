package org.example.sys.sys.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.example.sys.common.exception.TestException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
public class DriverManager {

    private static WebDriver driver;

    public static void initDriver() {

        log.info("WebDriver - creating new instance ");

        if (!PropertySupplier.useGrid()) {
            try {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeConfiguration());
                driver.manage().window().maximize();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("cannot setup driver");
            }
        } else {
            try {
                driver = new RemoteWebDriver(PropertySupplier.getGridUrl(), getChromeConfigurationForGrid());
            } catch (MalformedURLException ex) {
                throw new TestException("Invalid remote host URL:" + ex.getMessage());
            }
        }
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        log.info("WebBrowser:" + capabilities.getBrowserName() + " " + capabilities.getVersion());
    }

    private static ChromeOptions getChromeConfiguration() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars",
                "--disable-web-security",
                "--allow-running-insecure-content",
                "--disable-dev-shm-usage",
                "--no-sandbox"
        );
        if (PropertySupplier.runHeadless()) {
            options.addArguments("--headless",
                    "--window-size=1920,1080");
        }
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    private static ChromeOptions getChromeConfigurationForGrid() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.setHeadless(true);
        if (PropertySupplier.forceLocalNode()) {
            options.setCapability("applicationName", "node-local");
        }
        return options;
    }

    public static boolean isDriverClosedOrNull() {

        return driver == null || ((RemoteWebDriver) driver).getSessionId() == null
                || driver.toString().contains("null");
    }

    public static WebDriver getDriver(){
        if(isDriverClosedOrNull()){
            initDriver();
        }
        return driver;
    }

}
