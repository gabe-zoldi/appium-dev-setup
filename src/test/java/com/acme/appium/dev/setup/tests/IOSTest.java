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
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "9.3");
        caps.setCapability("deviceName", "iPhone 6");
        caps.setCapability("app", "/Users/gabe_zoldi/Library/Developer/Xcode/DerivedData/UICatalog-cmqtyoxdtmvdsabiorcgullfmaeq/Build/Products/Debug-iphonesimulator/UICatalog.app"); //Replace this with app path in your system
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testiOSApp() throws InterruptedException {
        // menu items to click thru on UICatalog app
        String[] menuItemList = {
                "Action Sheets",
                "Activity Indicators",
                "Alert Views"
        };

        // iterate thru the list clicking each menu item
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