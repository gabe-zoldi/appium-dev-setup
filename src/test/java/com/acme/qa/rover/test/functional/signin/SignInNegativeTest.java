/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.test.functional.signin;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class SignInNegativeTest { //extends PageObject {

    private AppiumDriver<IOSElement> driver;

    //@BeforeTest
    public void setUp() throws Exception {
        // set app path
        String appDir = "/Users/gabe_zoldi/Library/Developer/Xcode/DerivedData/NortonRover-gzixpcpuesrhdbctddcdepslhoua/Build/Products/Debug-iphonesimulator";
        File app = new File(appDir, "NortonRover.app");

        // set device capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("app", app.getAbsolutePath());

        driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    //@AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void checkUICatalog() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("app", "/Users/gabe_zoldi/Library/Developer/Xcode/DerivedData/UICatalog-cmqtyoxdtmvdsabiorcgullfmaeq/Build/Products/Debug-iphonesimulator/UICatalog.app");

        AppiumDriver<IOSElement> wd = new IOSDriver<IOSElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
        //wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAImage[1]")).click();

        wd.executeScript(
                "mobile: click",
                new HashMap<String, Double>() {{
                    put("tapCount", 1.0);
                    put("touchCount", 1.0);
                    put("duration", 0.535859375);
                    put("x", 81.0);
                    put("y", 100.0);
                }}
        );

        wd.quit();
    }

    @Test
    public void checkInvalidAccountEmail() {
//        // click Don't Allow button
//        wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAImage[1]")).click();
//        By locDontAllow =
//                By.xpath("//UIAApplication[@name='NortonRover']/UIAWindow/UIAButton[@name='Next']");
//        IOSElement button_DontAllow = driver.findElement(locDontAllow);
//        //driver.tap(1, button_DontAllow, 1);
//        button_DontAllow.click();

//        By locNext = By.xpath("//UIAApplication[@name='NortonRover']/UIAWindow/UIAButton[@name='Next']");
//        IOSElement button_Next = driver.findElement(locNext);

//        // click Next 3 times
//        for (int i = 0; i < 3; i++) {
//            driver.findElement(MobileBy.name("Next"));
//            sleep(1000);
//        }
//
//        // click Sign In button (should error)
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]")).click();
    }

//    @Test
//    public void testFindElement() throws Exception {
//        //first view in UICatalog is a table
//        IOSElement table = driver.findElementByClassName("UIATableView");
//        assertNotNull(table);
//        //is number of cells/rows inside table correct
//        List<MobileElement> rows = table.findElementsByClassName("UIATableCell");
//        assertEquals(12, rows.size());
//        //is first one about buttons
//        assertEquals("Buttons, Various uses of UIButton", rows.get(0).getAttribute("name"));
//        //navigationBar is not inside table
//        WebElement nav_bar = null;
//        try {
//            nav_bar = table.findElementByClassName("UIANavigationBar");
//        } catch (NoSuchElementException e) {
//            //expected
//        }
//        assertNull(nav_bar);
//        //there is nav bar inside the app
//        driver.getPageSource();
//        nav_bar = driver.findElementByClassName("UIANavigationBar");
//        assertNotNull(nav_bar);
//    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }
        catch (Exception e) {
            // do nothing
        }
    }

}