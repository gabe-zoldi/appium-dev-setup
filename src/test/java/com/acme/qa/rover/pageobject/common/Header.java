/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.common;

import org.openqa.selenium.By;
import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class Header extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* constant                                                                                     */
    /* -------------------------------------------------------------------------------------------- */
    public static class Expected {
        public static class SUCCESS {

        }
        public static class INFO {

        }
        public static class ERROR {

        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* locator                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    protected By byHumanIcon = By.cssSelector(".fa.fa-user");
    protected By byLogoutDropdown = By.xpath("//a[@href='/logout']");

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement UsernameText() {
        return findElement( By.cssSelector("li.ng-binding") );
    }
    public WebElement HumanIcon() {
        return findElement( byHumanIcon );
    }
    public WebElement ProfileDropdown() {
        return findElement( By.xpath("//a[@href='/user/personalinfo']") );
    }
    public WebElement LogoutDropdown() {
        delay(1000);
        return findElement( byLogoutDropdown );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    protected void gotoProfile() {
        HumanIcon().click();
        ProfileDropdown().click();
    }

    public void logout() {
        // logout (upper-right corner of page)
        delay(500);
        HumanIcon().click();
        LogoutDropdown().click();
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public void verifyUserLoggedIn(String expected) {
        // get actual username displayed
        delay(3000);
        String actualUsernameDisplayed = UsernameText().getText();

        // what message to set if test fails
        String failMsg = addClass("Incorrect username displayed on Dashboard header.");
        assertThat(failMsg, actualUsernameDisplayed, equalTo(expected));
    }

}