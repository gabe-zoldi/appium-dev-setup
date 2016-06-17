/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.dashboard.panel;

import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class S3Panel extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement panel() {
        return findElement( By.cssSelector(".panel.panel-default.s3im") );
    }
    private WebElement row() {
        return panel().findElement( By.cssSelector(".panel-body > .row ") );
    }

    public WebElement TitleText() {
        return panel().findElement( By.cssSelector(".panel-title") );
    }

    public WebElement AccountsInSystemText() {
        return row().findElement( By.cssSelector("div:nth-child(1) > .bignum") );
    }
    public WebElement UsersInSystemText() {
        return row().findElement( By.cssSelector("div:nth-child(2) > .bignum") );
    }

    public WebElement AccountsInSystemLabelText() {
        return row().findElement( By.cssSelector("div:nth-child(1) > .center") );
    }
    public WebElement UsersInSystemLabelText() {
        return row().findElement( By.cssSelector("div:nth-child(2) > .center") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public S3Panel verifyIsDisplayed() {
        delay(500);
        // check panel specific properties
        this.
                verifyTitle().
                verifyLabels();
        return this;
    }
    public S3Panel verifyTitle() {
        assertThat( addClass("Incorrect title for panel"), TitleText().getText(), equalTo("S3") );
        return this;
    }

    public S3Panel verifyAccountsInSystem(int count) {
        assertThat( addClass("Incorrect count of Accounts in the System"),
                AccountsInSystemText().getText(), equalTo( String.valueOf(count) ) );
        return this;
    }

    public S3Panel verifyUsersInSystem(int count) {
        assertThat( addClass("Incorrect count of Users in the System"),
                UsersInSystemText().getText(), equalTo( String.valueOf(count) ) );
        return this;
    }

    public S3Panel verifyLabels() {
        assertThat( addClass("Incorrect label for Accounts In System"),
                AccountsInSystemLabelText().getText(),
                anyOf(
                        containsString("Accounts in the System")
                )
        );
        assertThat( addClass("Incorrect label for Users In System"),
                UsersInSystemLabelText().getText(),
                anyOf(
                        containsString("Users in the System"),
                        containsString("Limit: 5000 users")
                )
        );

        return this;
    }

}