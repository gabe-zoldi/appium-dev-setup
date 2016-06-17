/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.common;

import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class AlertMessage extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* constant                                                                                     */
    /* -------------------------------------------------------------------------------------------- */
    public static class Expected {
        public static class SUCCESS {
            public static String ACCOUNT_ADDED(String email) {
                return String.format("%s is added.", email);
            }
            public static String USER_ADDED(String email) {
                return String.format("%s is added.", email);
            }
            public static String USER_UPDATED(String email) {
                return String.format("User %s is updated successfully.", email);
            }
        }
        public static class INFO {

        }
        public static class ERROR {

        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement AlertText() {
        return alert().findElement( By.cssSelector("span.ng-binding") );
    }

    public WebElement AlertX() {
        return alert().findElement( By.cssSelector(".close") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement alert() {
        return findElement( By.cssSelector(".alert") );
    }

}