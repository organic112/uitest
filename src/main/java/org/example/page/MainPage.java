package org.example.page;


import org.example.common.util.CommonUtil;
import org.example.common.util.PageUtil;
import org.example.component.ComponentUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends SYSPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage reloadPage(){
        getDriver().navigate().refresh();
        return getPage(MainPage.class);
    }






}
