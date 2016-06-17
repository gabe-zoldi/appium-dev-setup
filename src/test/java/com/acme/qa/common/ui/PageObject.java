/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ui;

import com.google.common.base.Function;
import com.symantec.qa.common.Config;
import com.symantec.qa.common.Global;
import com.symantec.qa.common.framework.logging.Log;
import com.symantec.qa.common.utilities.Utils;
import com.symantec.qa.rover.datamodel.Screen;
import com.symantec.qa.rover.pageobject.common.AlertMessage;
import org.hamcrest.Matchers;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.seleniumhq.selenium.fluent.FluentBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.fail;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class PageObject {

    private final int WAIT_TIMEOUT = 5;     // TimeUnit.SECONDS
    private final int SCRIPT_TIMEOUT = 10;  // TimeUnit.SECONDS
    private final int POLL = 100;           // TimeUnit.MILLISECONDS

    // loading config.properties
    protected static String appUrl = Config.getProperty("app.url.default");
    protected static WebDriver driver = null;

    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>
     * <a href="http://10.81.24.158/testrail/index.php?/cases/view/1761&group_by=cases:section_id&group_id=356&group_order=asc" TARGET="_blank">
     *     C1761 - Base url redirects to signin page
     * </a></u>
     */
    @BeforeClass
    public void init() {
        openBrowser();
    }

    @AfterClass
    public void destroy() {
        closeBrowser();
    }

    protected void checkAlert(final String expected) {
        delay(500);
        String actual = new AlertMessage().AlertText().getText();
        assertThat( addClass("Incorrect alert message"), actual, Matchers.equalTo(expected) );
    }

    protected void mouseOver(final WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        delay(100);
    }

    public String addClass(String message) {
        String subClazzName = Thread.currentThread().getStackTrace()[2].getClassName();
        //String simpleSubClazzName = subClazzName.substring(subClazzName.lastIndexOf(".") + 1);
        return String.format("[%s] %s", subClazzName, message);
    }

    /* -------------------------------------------------------------------------------------------- */
    /* prime directives (angular)                                                                   */
    /* -------------------------------------------------------------------------------------------- */
    protected void waitForAngular() {
        waitForAngular(driver);
    }

    protected void waitForAngular(final WebDriver driver) {
        // synchronous
        String callback = "";
        callback += "var callback = arguments[arguments.length - 1]; ";
        callback += "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);";

        // wait for angular requests to finish
        ((JavascriptExecutor) driver).executeAsyncScript(callback);
    }

    protected WebElement findElement(final By element) {
        return findElement(element, driver);
    }

    protected WebElement findElement(final By element, final WebDriver driver) {
        return autoHighlight(driver.findElement(element));
    }

    protected List<WebElement> findElements(final By element) {
        return findElements(element, driver);
    }

    protected List<WebElement> findElements(final By element, final WebDriver driver) {
        return driver.findElements(element);
    }

    /* -------------------------------------------------------------------------------------------- */
    /* browser keyword                                                                              */
    /* -------------------------------------------------------------------------------------------- */
    protected void openBrowser() {
        createDriver();
        setTimeout(driver);
        setWindow(driver);
        setTestEnvironment();

        // goto starting url
        driver.get(appUrl);
    }

    protected void setTimeout(final WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT, TimeUnit.SECONDS);
    }

    protected void setWindow(final WebDriver driver) {
        final String SCREEN_SETTING = Config.getProperty("screen.setting");

        // re-orient window to start at a consistent location
        // target position upper left corner; this is slightly different for every resolution
        //driver.manage().window().setPosition(new Point(0, 0));
        //driver.manage().window().maximize();

        // target size
        if ( SCREEN_SETTING.equals( Screen.DESKTOP.getType() ) ) {
            driver.manage().window().setSize( Screen.DESKTOP.getDimension() );
        }
        else if ( SCREEN_SETTING.equals( Screen.MOBILE.getType() ) ) {
            driver.manage().window().setSize( Screen.MOBILE.getDimension() );
        }
        else if (SCREEN_SETTING.contains("x")) {
            String[] screen = SCREEN_SETTING.split("x");
            int width  = Integer.valueOf(screen[0]);
            int height = Integer.valueOf(screen[1]);
            driver.manage().window().setSize( new Dimension(width, height) );
        }
        else {
            String error = "";
            error = "[Error] Valid screen.setting => {  desktop | mobile   }";
            error = "                 -OR- custom => { 1280x800 | 1440x900 }";
            throw new RuntimeException(error);
        }
    }

    protected void setTestEnvironment() {
        final String TEST_ENV_SENTINEL = Config.getProperty("test.env.sentinel");

        // check if env sentinel exists; sentinel sets environment by tester
        // -- uses default url if sentinel not found
        File sentinel = new File(TEST_ENV_SENTINEL);

        // check file exists
        if (sentinel.exists() && sentinel.isFile()) {
            // look for app.url.<user>
            String appUrlKey = null;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(sentinel));
                // find first uncommented app url
                commentedSkip: while((appUrlKey = reader.readLine()) != null) {
                    if (appUrlKey.startsWith("#") || appUrlKey.isEmpty())
                        continue commentedSkip;
                    appUrl = Config.getProperty(appUrlKey);     // found override app.url.default
                    break;                                      // find first uncommented url then stop searching
                }
                reader.close();
            }
            catch (Exception e) {
                fail(e.getMessage());
            }
            Log.info("Test environment sentinel file found: " + sentinel.getAbsolutePath());
        }
        else {
            Log.info("Test environment sentinel file not found...using app.url.default in config.properties.");
        }

        Log.info("App url set to: " + appUrl);
    }

    private void createDriver() {
        if (driver == null)
            driver = WebDriverFactory.createDriver();
    }

    protected void closeBrowser() {
        driver.quit();
        driver = null;
    }

    protected void refreshPage() {
        driver.navigate().refresh();
    }

    protected void gotoUrl(final String url) {
        delay(1000);
        driver.navigate().to(url);
    }

    protected void pressKey(final Keys key) {
        driver.switchTo().activeElement().sendKeys(key);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean pageContains(final String text) {
        delay(500);
        return driver.getPageSource().contains(text);
    }

    protected void checkFocusOn(final WebElement expected) {
        assertThat(
                String.format("Focus is not on %s %s.", expected.getText(), expected.getTagName()),
                driver.switchTo().activeElement(),
                equalTo(expected) );
    }

    protected boolean isElementPresent(final By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* selenium rc                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    protected List<String> flattenByGetText(final List<WebElement> list) {
        List<String> flatten = new ArrayList<String>();
        Iterator<WebElement> it = list.iterator();

        while (it.hasNext()) {
            WebElement el = (WebElement) it.next();
            flatten.add(el.getText());
        }

        return flatten;
    }

    private String failMessage(final String reason, final String expected) {
        return String.format( "%s [expected] %s", reason, expected );
    }

    protected void waitForTextEquals(final WebElement element, final String expected, final String reason) {
        new FluentWait<WebElement>( element ).
                withMessage( failMessage(reason, expected) ).
                withTimeout(WAIT_TIMEOUT, TimeUnit.SECONDS).
                pollingEvery(POLL, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().equals(expected);
                    }
                });
    }

    protected void waitForTextContains(final WebElement element, final String expected, final String reason) {
        new FluentWait<WebElement>( element ).
                withMessage( failMessage(reason, expected) ).
                withTimeout(WAIT_TIMEOUT, TimeUnit.SECONDS).
                pollingEvery(POLL, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().contains(expected);
                    }
                });
    }

    protected void waitForElementToBeDisplayed(final WebElement element, final String reason) {
        new FluentWait<WebElement>( element ).
                withMessage( reason ).
                withTimeout(WAIT_TIMEOUT, TimeUnit.SECONDS).
                pollingEvery(POLL, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        //return element.isDisplayed();
                        return null;
                    }
                });
    }

    protected void waitForelementTextToBePresent(final WebElement element, final String expected, final String reason) {
        // check element text is present
        new WebDriverWait( driver, Global.MAX_TIME_ELEMENT_TO_LOAD ).
                withMessage( reason ).
                until(ExpectedConditions.textToBePresentInElement(element, expected));
    }

    protected void waitForElementClickable(final WebElement element, final String reason) {
        // check if element is clickable
        new WebDriverWait( driver, Global.MAX_TIME_ELEMENT_TO_LOAD ).
                withMessage( reason ).
                until( ExpectedConditions.elementToBeClickable( element ) );
    }

    protected void clickAllElements(final List<WebElement> elements) {
        Iterator<WebElement> it = elements.iterator();
        while ( it.hasNext() ) {
            WebElement element = (WebElement) it.next();
            element.click();
        }
    }

    protected boolean elementExists(final WebElement element) {
        try {
            // try to see if selenium can resolve the element
            // if yes, element obviously exists
            // if not, it will throw exception, return false in the catch block
            //element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

//    protected List<String> convertToListString(final List<WebElement> errors) {
//        List<String> list = new ArrayList<String>();
//        Iterator<WebElement> it = errors.iterator();
//
//        while (it.hasNext()) {
//            String error = it.next().getText();
//            list.add(error);
//        }
//
//        return list;
//    }
//
//    protected void assertLinkCount(final By by, final int expectedCount) {
//        List<WebElement> links = driver.findElements( by );
//        assertThat(links.size(), equalTo(expectedCount));
//    }
//
//    protected void assertError(final List<WebElement> errorList, final String expectedError, final String testedWithValue) {
//        // converting from List to Set is going to remove duplicate web elements
//        // refactor if expected to see duplicates
//        Set<WebElement> converted = new HashSet<WebElement>( errorList );
//
//        // build list of errors displayed on the page (removing dupes seen)
//        Set<String> errorsSeenOnThePage = new HashSet<String>();
//        Iterator<WebElement> it = converted.iterator();
//        while (it.hasNext())
//            errorsSeenOnThePage.add( it.next().getText() );
//
//        // verify expected error is in the list of actual errors seen on the page
//        String reason = "";
//        if (!testedWithValue.isEmpty())
//            reason = "Did not get any errors trying '" + testedWithValue + "'";
//
//        assertThat(reason, errorsSeenOnThePage, contains(expectedError));
//    }
//
//    protected void assertNoError(final List<WebElement> errorList) {
//        int found = errorList.size();
//        if ( found == 0 ) {
//            return;
//        }
//
//        // unexpected error was found on the page
//        Iterator<WebElement> it = errorList.iterator();
//        StringBuilder errors = new StringBuilder();
//        while (it.hasNext())
//            errors.append( it.next().getText() + "\n");
//
//        String reason = "Found unexpected error: " + errors;
//        Assert.fail(reason);
//    }

    /* -------------------------------------------------------------------------------------------- */
    /* angular support                                                                              */
    /* -------------------------------------------------------------------------------------------- */
    public static By ngWait(final By by) {
        return new FluentBy() {
            @Override
            public void beforeFindElement(WebDriver driver) {
                driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

                ((JavascriptExecutor) driver).
                        executeAsyncScript(
                                "var callback = arguments[arguments.length - 1];" +
                                        "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);"
                        );

                super.beforeFindElement(driver);
            }

            @Override
            public WebElement findElement(SearchContext context) {
                return by.findElement(context);
            }

            @Override
            public List<WebElement> findElements(SearchContext context) {
                return by.findElements(context);
            }

            @Override
            public String toString() {
                return "ngWait(" + by.toString() + ")";
            }
        };
    }

    /* -------------------------------------------------------------------------------------------- */
    /* utils                                                                                        */
    /* -------------------------------------------------------------------------------------------- */
    protected void delay(final long millis) {
        Utils.delay(millis);
    }

    protected String randomUnique(final String label) {
        return label + Utils.createUniqueRandomNumber();
    }

    protected WebElement autoHighlight(final WebElement element) {
        final boolean AUTO_HIGHLIGHT_ENABLED = Boolean.valueOf( Config.getProperty("auto.highlight") );

        // flash the element before returning it
        if (AUTO_HIGHLIGHT_ENABLED) {
            // flash this many times
            final int FLASH_COUNT = 3;

            for (int i = 0; i < FLASH_COUNT; i++) {
                flash(element);
            }
        }
        // flash or no flash just return the element
        return element;
    }

    private void flash(final WebElement element) {
        // original style set for this element
        String origBgColor = element.getCssValue("backgroundColor");
        String origBorderColor = element.getCssValue("borderColor");
        String origBorderWidth = element.getCssValue("borderWidth");

        // highlight style colors
        final String HI_BG_COLOR = "rgb(255,255,0)";  // yellow
        final String HI_BORDER_COLOR = "rgb(255,0,0)"; // red border
        final String HI_BORDER_WIDTH = "thick";

        // make element appear highlighted
        highlight(element, HI_BG_COLOR, HI_BORDER_COLOR, HI_BORDER_WIDTH);

        // make element appear NOT highlighted
        highlight(element, origBgColor, origBorderColor, origBorderWidth);     // this doesn't work in every case for some reason
        highlight(element, "rgb(255,255,255)", "rgb(255,255,255)", "initial"); // so setting to default page color inspected by eye
    }

    private void highlight(final WebElement element, final String bgcolor, final String bordercolor, final String borderwidth) {
        ((JavascriptExecutor) driver).
                executeScript(
                        setStyleTo(bgcolor, bordercolor, borderwidth),
                        element
                );

        // let's slow down the flashing a bit
        flashSpeed(POLL);
    }

    private String setStyleTo(final String bgcolor, final String bordercolor, final String borderwidth) {
        // template style attributes to change
        String style = "";
        style += "arguments[0].style.backgroundColor = '%s'; ";
        style += "arguments[0].style.borderColor = '%s'; ";
        style += "arguments[0].style.borderWidth = '%s'; ";

        // javascript query to change style
        return String.format(style, bgcolor, bordercolor, borderwidth);
    }

    private void flashSpeed(final int millis) {
        delay(millis);
    }

    /* -------------------------------------------------------------------------------------------- */
    /* properties                                                                                   */
    /* -------------------------------------------------------------------------------------------- */
//    static Properties prop;
//
//    static {
//        loadProperties();
//    }
//
//    private static void loadProperties() {
//        prop = new Properties();
//        InputStream in = PageObject.class.getClassLoader().getResourceAsStream("config.properties");
//        try {
//            prop.load(in);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        Enumeration e = prop.keys();
//
//        while (e.hasMoreElements()) {
//            String key = (String) e.nextElement();
//            System.out.println(key + " -- " + prop.getProperty(key));
//        }
//    }

}