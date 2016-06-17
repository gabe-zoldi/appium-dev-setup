/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ui;

import org.openqa.selenium.WebElement;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class HighlightElement {

    /**
     * Highlights the webelement while tests are being executed.
     * Great mode to be in to show what the test is doing.
     *
     * @param element
     */
    public static void highlightElement(WebElement element) {
        // TODO: find a way to add a tooltip annotator to elements to display step progress during
        // TODO: execution; we should be able to add things like custom comments to each step
        /*
         * to do synopsis:
         * this nice-to-have functionality might seem pointless for jenkins execution but actually
         * its useful for when tests fail, to be able to re-run failed tests in screen record mode
         * that way we see what exactly automation saw in terms of the failure; also its very useful
         * for manual testers to see or verify or signoff on the automation scripts running locally,
         * that it indeed ran correctly; this tooltip functionality is to mainly show whats being
         * executed like hey the webdriver is now 'Clicking this button.' or 'Setting that field
         * with an incorrect password.' or 'Verifying alert error message is...'
         */
        for (int i = 0; i <2; i++) {
            //JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.driver;
            //js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
            //js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

}