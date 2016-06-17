/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.type.browser;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import java.lang.reflect.Method;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class Browser {

    public static DesiredCapabilities getDesiredCapabilities(BrowserOS browserOS, BrowserVersion browserVersion) {
        return
                new Browser().
                        createDefaultCapability(
                                browserOS.getName(),
                                browserVersion.getType().getTarget(),
                                browserVersion.getVersion()
                        );
    }

    public static DesiredCapabilities getDesiredCapabilities(DeviceVersion device) {
        DesiredCapabilities capabilities =
                new Browser().
                        createDefaultCapability(
                                device.getBrowserOS().getName(),
                                device.getBrowserType().getTarget(),
                                device.getVersion()
                        );
        capabilities.setCapability( DeviceVersion.CapabilityType.DEVICE_NAME.getKey(), device.getName() );
        capabilities.setCapability( DeviceVersion.CapabilityType.DEVICE_ORIENTATION.getKey(), device.getOrientation().getName() );
        return capabilities;
    }

    private DesiredCapabilities createDefaultCapability(String platform, String browserName, String browserVersion) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( CapabilityType.PLATFORM, platform );
        capabilities.setCapability( CapabilityType.BROWSER_NAME, browserName );
        capabilities.setCapability( CapabilityType.VERSION, browserVersion );
        return capabilities;
    }


    // TODO: may not have needed implemented this using Reflection to dynamically create DC by Browser method;
    // TODO: setting with the correct CapabilityType.BROWSER_NAME seems sufficient; if this is true we may want
    // TODO: to delete this unused method
    private static DesiredCapabilities createCapability(Object obj) {
        DesiredCapabilities caps = null;
        Class cls = null;
        Object instance = null;
        Method method = null;

        // strictly a dummy variable, the browser methods like chrome(), android(),
        // firefox(), internetExplorer(), ..., etc. doesn't take in arguments
        Class noparams[] = {};

        try {
            // create DesiredCapabilities to get the browser method
            cls = Class.forName( DesiredCapabilities.class.getName() );
            instance = cls.newInstance();

            // pass the browser method name to get called
            if (obj instanceof BrowserVersion) {
                method = cls.getDeclaredMethod(
                        ((BrowserVersion) obj).getType().getTarget(), noparams );
            }
            else if (obj instanceof DeviceVersion) {
                method = cls.getDeclaredMethod(
                        ((DeviceVersion) obj).getBrowserType().getTarget(), noparams );
            }
            else {
                Assert.fail( "Unable to determine browser or device version type. Please check your testng code." );
            }

            return (DesiredCapabilities) method.invoke(instance, (Object[]) null);
        }
        catch (Exception e) {
            throw new RuntimeException("Ouch! Whooooo let the dogs out. Wooof, woof woof woof...", e);
        }
    }


    public static void main(String [] args) {
        DesiredCapabilities caps = null;

        caps = Browser.getDesiredCapabilities( BrowserOS.WINDOWS_7, BrowserVersion.CHROME_46 );
        System.out.println( "==========================================================" );
        System.out.println( String.format("[expected]\n%s\n%s", BrowserOS.WINDOWS_7.toString(), BrowserVersion.CHROME_46.toString()) );
        System.out.println( "==========================================================" );
        System.out.println( caps );
        System.out.println( "toString=" + caps.toString() );
        System.out.println( "platform=" + caps.getCapability(CapabilityType.PLATFORM) );
        System.out.println( "version=" + caps.getCapability(CapabilityType.VERSION) );
        System.out.println( "browserName=" + caps.getBrowserName() );
        System.out.println( "deviceName=" + caps.getCapability(DeviceVersion.CapabilityType.DEVICE_NAME.getKey()) );
        System.out.println( "device-orientation=" + caps.getCapability(DeviceVersion.CapabilityType.DEVICE_ORIENTATION.getKey()) );

        caps = Browser.getDesiredCapabilities( DeviceVersion.IPHONE_7_1 );
        System.out.println( "==========================================================" );
        System.out.println( String.format("[expected]\n%s", DeviceVersion.IPHONE_7_1) );
        System.out.println( "==========================================================" );
        System.out.println( caps );
        System.out.println( "toString=" + caps.toString() );
        System.out.println( "platform=" + caps.getCapability(CapabilityType.PLATFORM) );
        System.out.println( "version=" + caps.getCapability(CapabilityType.VERSION) );
        System.out.println( "browserName=" + caps.getBrowserName() );
        System.out.println( "deviceName=" + caps.getCapability(DeviceVersion.CapabilityType.DEVICE_NAME.getKey()) );
        System.out.println( "device-orientation=" + caps.getCapability(DeviceVersion.CapabilityType.DEVICE_ORIENTATION.getKey()) );

        caps = Browser.getDesiredCapabilities( DeviceVersion.GOOGLE_NEXUS_7C_EMULATOR_4_3 );
        System.out.println( "==========================================================" );
        System.out.println( String.format("[expected]\n%s", DeviceVersion.GOOGLE_NEXUS_7C_EMULATOR_4_3) );
        System.out.println( "==========================================================" );
        System.out.println( caps );
        System.out.println( "toString=" + caps.toString() );
        System.out.println( "platform=" + caps.getCapability(CapabilityType.PLATFORM) );
        System.out.println( "version=" + caps.getCapability(CapabilityType.VERSION) );
        System.out.println( "browserName=" + caps.getBrowserName() );
        System.out.println( "deviceName=" + caps.getCapability(DeviceVersion.CapabilityType.DEVICE_NAME.getKey()) );
        System.out.println( "device-orientation=" + caps.getCapability(DeviceVersion.CapabilityType.DEVICE_ORIENTATION.getKey()) );

        System.out.println( "==========================================================" );
        System.out.println("android=" + DesiredCapabilities.android().getBrowserName());
        System.out.println("chrome=" + DesiredCapabilities.chrome().getBrowserName());
        System.out.println("htmlunit=" + DesiredCapabilities.htmlUnit().getBrowserName());
        System.out.println("firefox=" + DesiredCapabilities.firefox().getBrowserName());
        System.out.println("htmlunitwithjs=" + DesiredCapabilities.htmlUnitWithJs().getBrowserName());
        System.out.println("ie=" + DesiredCapabilities.internetExplorer().getBrowserName());
        System.out.println("ipad=" + DesiredCapabilities.ipad().getBrowserName());
        System.out.println("iphone=" + DesiredCapabilities.iphone().getBrowserName());
        System.out.println("phantomjs=" + DesiredCapabilities.phantomjs().getBrowserName());
        System.out.println("safari=" + DesiredCapabilities.safari().getBrowserName());
    }

}
