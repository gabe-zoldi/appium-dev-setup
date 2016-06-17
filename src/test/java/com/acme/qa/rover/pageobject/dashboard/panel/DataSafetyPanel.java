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
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DataSafetyPanel extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement panel() {
        return findElement( By.cssSelector(".panel.panel-default.datasafety") );
    }
    private WebElement stateRow() {
        return panel().findElement(By.cssSelector(".panel-body > .row"));
    }

    public WebElement TitleText() {
        return panel().findElement( By.cssSelector(".panel-title") );
    }
    public WebElement CriticalStateIcon() {
        return stateRow().findElement(By.cssSelector(".critical"));
    }
    public WebElement StateText() {
        return stateRow().findElement( By.cssSelector(".text") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public DataSafetyPanel clickState() {
        stateRow().click();
        return this;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public DataSafetyPanel verifyIsDisplayed() {
        delay(500);
        // check panel specific properties
        this.
                verifyTitle();
        return this;
    }
    public DataSafetyPanel verifyTitle() {
        assertThat( addClass("Incorrect title for panel"), TitleText().getText(), equalTo("Data Safety") );
        return this;
    }

    public DataSafetyPanel verifyState(String expected) {
        assertThat( addClass("Incorrect state value for Data Safety" + expected),
                StateText().getText(), equalTo(expected) );
        return this;
    }

}