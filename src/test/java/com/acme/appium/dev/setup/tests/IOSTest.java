package com.acme.appium.dev.setup.tests;

import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IOSTest {

    private IOSDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        // set app location
        String APP_PATH = "/Users/gabe_zoldi/";
        APP_PATH += "Library/Developer/Xcode/DerivedData/UICatalog-cmqtyoxdtmvdsabiorcgullfmaeq/";
        APP_PATH += "Build/Products/Debug-iphonesimulator/";

        // set app name
        final String APP_NAME = "UICatalog.app";

        // set capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "9.3");
        caps.setCapability("deviceName", "iPhone 6");
        caps.setCapability("app", APP_PATH + APP_NAME);

        // create mobile app instance
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testiOSApp() throws InterruptedException {
        // menu items to click on UICatalog app
        String[] menuItemList = {
                "Action Sheets",
                "Activity Indicators",
                "Alert Views"
        };

        // iterate thru menu item list clicking on each menu item
        // then click the back button tor reset to main menu
        for (String menuItem : menuItemList) {
            driver.findElement(By.xpath("//*[@name='" + menuItem + "']")).click();
            driver.findElement(By.xpath("//*[@name='Back']")).click();
        }

        sleep(1000);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }
        catch (Exception e) {
            // TODO: do nothing - swallow the exception
        }
    }

}