package org.example.sys.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.sys.common.exception.TestException;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;


public class CommonUtil {


    public static  String getRandomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static List<String> webElementToString(List<WebElement> webElements) {
        return webElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public static File getFileFromResource(String resourceName) {
        try {
            return new File(CommonUtil.class.getClassLoader().getResource(resourceName).toURI());

        } catch (URISyntaxException | NullPointerException e) {
            throw new TestException(String.format("Cannot load file: %s %s", resourceName, e));
        }
    }

    public static void sleep(int millisecondsToWait) {

        try {
            Thread.sleep(millisecondsToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
