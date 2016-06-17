/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ui;

import com.symantec.qa.common.Config;
import com.symantec.qa.common.framework.PropertyManager;
import com.symantec.qa.common.Global;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.symantec.qa.common.type.browser.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.symantec.qa.common.type.browser.BrowserType.*;
import static org.openqa.selenium.Platform.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public abstract class WebDriverFactory {

    // read from config.properties
    private final static BrowserOS   _BROWSER_OS      = BrowserOS.getBrowserOS( Config.getProperty("browser.os") );
    private final static BrowserType _BROWSER_TYPE    = getBrowserType( Config.getProperty("browser.type") );
    private final static String      _BROWSER_VERSION = Config.getProperty("browser.version");

    // build object mapping for browser type and version
    private final static BrowserVersion BROWSER_TYPE_AND_VERSION = BrowserVersion.getBrowser( _BROWSER_TYPE, _BROWSER_VERSION );

    static DesiredCapabilities getCapabilities(BrowserOS browserOS, BrowserVersion browserVersion) {
        DesiredCapabilities caps = Browser.getDesiredCapabilities(browserOS, browserVersion);
        return caps;
    }

    protected static WebDriver createDriver() {
        // check if tests should run locally from dev box or on a remote selenium grid test environment
        final boolean IF_RUN_TESTS_ON_SELENIUM_GRID =
                Boolean.parseBoolean( Config.getProperty("seleniumgrid.runtests") );

        // check if running in seleniumgrid otherwise create a local webdriver
        WebDriver driver =
                ( IF_RUN_TESTS_ON_SELENIUM_GRID ) ?
                        createDriverForRemoteExecution() :   // [true]  grid mode: tests sent to remote node for execution
                        createDriverForLocalExecution();     // [false] dev mode:  tests are run locally

        // set how long to wait for elements etc.
        driver.manage().timeouts().implicitlyWait( Global.MAX_TIME_ELEMENT_TO_LOAD, TimeUnit.SECONDS );

        // make screen as big as possible so screenshot won't miss things
        if (Config.getProperty("screenshot.on.failure").equals("true")) {
            driver.manage().window().maximize();
        }

        return driver;
    }

    /*
     * create Remote WebDriver
     */
    private static WebDriver createDriverForRemoteExecution() {
        // check if valid browser os specified
        if ( _BROWSER_OS == null ) {
            throw new RuntimeException(
                    String.format(
                            "BrowserOS specified (in %s) not found: browser.os=%s",
                            PropertyManager.CONFIG_PROP,
                            Config.getProperty("browser.os")
                    )
            );
        }

        // check if valid browser type and version specified
        if ( BROWSER_TYPE_AND_VERSION == null ) {
            throw new RuntimeException(String.format(
                    "Browser specified (in %s) not found: browser.type=%s, browser.version=%s  [ Please refer to %s ]",
                    PropertyManager.CONFIG_PROP,
                    Config.getProperty("browser.type"),
                    Config.getProperty("browser.version"),
                    BROWSER_TYPE_AND_VERSION.getClass().getName()
            ));
        }

        // assumptions: please ensure that selenium grid is always 'Running' as a service
        // UnreachableBrowserException or HttpHostConnectException: Connection refused may occur
        final String SELENIUM_GRID_HUB_URL =
                String.format( "http://%s:%s/wd/hub",
                        Config.getProperty( "seleniumgrid.host" ),
                        Config.getProperty( "seleniumgrid.port" )
        );

        WebDriver remoteDriver = null;

        // create remote driver and let selenium hub decide from capabilities
        // which slave selenium node it should send it to for remote testing
        try {
            remoteDriver =  new RemoteWebDriver(
                    new URL( SELENIUM_GRID_HUB_URL ),
                    getCapabilities( _BROWSER_OS, BROWSER_TYPE_AND_VERSION )
            );
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Invalid selenium grid hub connect url: " + SELENIUM_GRID_HUB_URL, e);
        }

        return remoteDriver;
    }

    /*
     * create Local WebDriver
     */
    private static WebDriver createDriverForLocalExecution() {
        // TODO: add check to see if system.property is set like '-Dbrowser.type=firefox' to override default
        // TODO: browser type check if valid browser type specified
        if ( _BROWSER_TYPE == null ) {
            throw new RuntimeException(String.format(
                    "BrowserType specified (in %s) not found: browser.type=%s",
                    PropertyManager.CONFIG_PROP,
                    Config.getProperty("browser.type")
            ));
        }

        WebDriver localDriver = null;

        // create webdriver for browser type
        if ( _BROWSER_TYPE.equals( CHROME ) ) {
            // TODO: for now defaults to 32-bit version, refactor to use 64-bit if detected
            setMyDriverExecutable( CHROME );

            final boolean DEMOMODE_ENABLED = Boolean.valueOf( Config.getProperty("demo.mode") );

            // this custom driver adds delays to show automation running during a demo
            // so folks can see what is happening, otherwise, it runs too quickly
            localDriver =
                    (DEMOMODE_ENABLED) ?
                            new DemoChromeDriver() :
                            new ChromeDriver();
        }
        else if ( _BROWSER_TYPE.equals(IE) ) {
            // TODO: check which token supportedby DesiredCapabilities: internetExplorer or 'internet explorer' or ie
            String property = "webdriver.ie.driver";
            File file = new File( Config.getProperty( property ) );
            System.setProperty( property, file.getAbsolutePath() );

            localDriver = new InternetExplorerDriver();
        }
        else if ( _BROWSER_TYPE.equals(FIREFOX) ) {
            localDriver = new FirefoxDriver();
        }

        // check if appropriate local driver found
        if (localDriver == null)
            throw new RuntimeException("Matching BrowserType not found: " + _BROWSER_TYPE);
        return localDriver;
    }

    /**
     * Sets the local system property to use browsertype webdriver specified in config.properties.
     *
     * @param browser BrowserType
     */
    private static void setMyDriverExecutable(BrowserType browser) {
        // what is the current os running?  windows, mac, linux, android, ios
        Platform currentOS = Platform.getCurrent();
        File fDriver = null;
        String driverSysProp = null;

        // match to browser type
        if (browser == CHROME) {
            driverSysProp = "webdriver.chrome.driver";

            if (currentOS == WINDOWS || currentOS == WIN8_1 || currentOS == WIN8 || currentOS == VISTA || currentOS == XP) {
                // get from config.prop which driver binary
                fDriver = new File( Config.getProperty( driverSysProp + ".windows" ) );
            }
            else if (currentOS == MAC) {
                fDriver = new File( Config.getProperty( driverSysProp + ".mac" ) );
            }
            else if (currentOS == LINUX) {
                fDriver = new File( Config.getProperty( driverSysProp + ".linux" ) );
            }
            else {
                String error = "";
                error += "[ERROR] Detected OS unsupported: %s\n";
                error += "[ERROR] (%s) browser.type => %s";
                throw new RuntimeException(
                        String.format( error, currentOS, PropertyManager.CONFIG_PROP, browser )
                );
            }
        }
        else if ( browser == IE ) {
            driverSysProp = "webdriver.ie.driver";

            String notYetImplemented = "[HALT ERROR] Not yet implemented. Please check the test framework code.";
            throw new RuntimeException( notYetImplemented );
        }

        // check if driver exists for the test framework to use
        if (fDriver == null || driverSysProp == null) {
            String fatal = "";
            fatal += "[FATAL ERROR] Test cannot continue. Could not find proper local webdriver to use.\n";
            fatal += "[FATAL ERROR] (%s) browser.os => %s, browser.type => %s";
            throw new RuntimeException(
                    String.format( fatal, PropertyManager.CONFIG_PROP, currentOS,browser.getTarget() )
            );
        }

        // set system property for webdriver
        System.setProperty(driverSysProp, fDriver.getAbsolutePath());
    }

}
