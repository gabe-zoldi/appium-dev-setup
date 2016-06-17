/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.common.dialog.user;

import com.symantec.qa.common.ui.PageObject;
import com.symantec.qa.rover.datamodel.Key;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Created by gzold7195817 on 11/11/15.
 */
public class NewAccessKeyDialog extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* constant                                                                                     */
    /* -------------------------------------------------------------------------------------------- */
    public class Expected {
        public class INFO {
            public static final String CHECK_EMAIL_FOR_SECRET_KEY = "Please check your email for secret key.";
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement AccessKeyField() {
        return _dialog().findElement( By.cssSelector("*[ng-value='newAccessKey.key']") );
    }
    public WebElement NoteText() {
        return _dialog().findElement( By.cssSelector(".modal-body p") );
    }
    public WebElement CloseButton() {
        return _dialog().findElement( By.xpath("//button[text()='Close']") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public Key getKey() {
        delay(500);
        String access = AccessKeyField().getAttribute("value");  // field read only
        Key key = new Key(access);
        return key;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public void verifyKey(Key key) {
        // check key not null or empty
        String actualAccessKey = key.getAccessKey();
        assertThat( addClass("Access key was null or empty"), actualAccessKey, not(isEmptyOrNullString()) );
    }
    public void verifyNote(String expected) {
        String actualNote = NoteText().getText();
        assertThat( addClass("Incorrect new access key note displayed"), actualNote, equalTo(expected) );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement _dialog() {
        return findElement( By.id("generateAccessKeyModal") );
    }

}