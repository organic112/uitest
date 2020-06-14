package org.example.sys.sys.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.TestException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Slf4j
public class PropertySupplier {


    private static Properties cache = new Properties();
    private static Properties propertiesFromArgs;
    private static Properties propertiesFromCfg;
    private static Properties propertiesFromXML;

    public static String getSYSUrl() {
        return readProperty("sys.url");
    }

    public static String getSYSUserLogin() {
        return readProperty("sys.user.login");
    }

    public static String getSYSUserPassword() {
        return readProperty("sys.user.password");
    }

    public static String getDBUrl() {
        return readProperty("db.url");
    }

    public static String getDBUserLogin() {
        return readProperty("db.user.login");
    }

    public static String getDBUserPassword() {
        return readProperty("db.user.password", true);
    }

    public static String getScreenshotsDirPath() {
        return readProperty("screenshots.path".replace("/", File.separator));
    }

    public static boolean runHeadless() {
        return false; // readProperty("run.headless").equals("true");
    }

    // todo grid property


    /**
     * provides property by key from several sources
     * when not found in any source throws exception
     */
    private static String readProperty(String key, boolean insecure) {
        String value = cache.getProperty(key);
        if (null != value) {
            log.info("cache {} {} {}", key, value, insecure);
            return value;
        }
        value = propertiesFromArgs.getProperty(key);
        if (null != value) {
            log.info("command line args {} {} {}", key, value, insecure);
            cache.put(key, value);
            return value;
        }
        value = propertiesFromCfg.getProperty(key);
        if (null != value) {
            log.info("app.properties {} {} {}", key, value, insecure);
            cache.put(key, value);
            return value;
        }
        value = propertiesFromXML.getProperty(key);
        if (null != value) {
            log.info("suite xml file {} {} {}", key, value, insecure);
            cache.put(key, value);
            return value;
        }
        throw new TestException("No property found: " + key);
    }

    private static String readProperty(String key) {
        return readProperty(key, false);
    }

    private static void printLog(String source, String key, String value, boolean insecure) {
        String msg = String.format("Read property: '%s' from '%s'", key, source);
        if (!insecure) {
            msg += ": " + value;
        }
        log.info(msg);
    }

    public static void loadConfiguration(ISuite iSuite) {

        log.info("Reading configuration from all sources...");
        propertiesFromArgs = System.getProperties();
        propertiesFromCfg = readPropertiesFromCfg();
        propertiesFromXML = new Properties();
        propertiesFromXML.putAll(iSuite.getXmlSuite().getAllParameters());
    }

    private static Properties readPropertiesFromCfg() {
        Properties properties = new Properties();
        String configFileName = "app.properties";
        InputStream inputStream = PropertySupplier.class.getClassLoader().getResourceAsStream(configFileName);

        if (null != inputStream) {
            try {
                properties.load(inputStream);
                return properties;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Missing configuration file: " + configFileName);
        }
    }

    public static boolean useGrid() {
        return false; // readProperty("grid.enabled").equals("true");
    }

    public static boolean forceLocalNode() {
        try {
            return readProperty("grid.force.local.node").equals("true");
        } catch (TestException e) {
            return false;
        }
    }

    public static URL getGridUrl() throws MalformedURLException {
        String urlString = readProperty("grid.url");
        return new URL(urlString);
    }

    public static boolean readRetryRunnerProperty() {
        return readProperty("retry.analyzer.enabled").equals("true");
    }
}


