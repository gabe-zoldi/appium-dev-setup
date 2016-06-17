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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class StorageCapacityPanel extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement panel() {
        return findElement( By.cssSelector(".panel.panel-default.storageusage") );
    }
    private WebElement row() {
        return panel().findElement( By.cssSelector(".panel-body > .row ") );
    }
    private WebElement subrow() {
        return row().findElement( By.cssSelector("div:nth-child(2) > .row.center") );
    }

    public WebElement TitleText() {
        return panel().findElement( By.cssSelector(".panel-title") );
    }
    public WebElement UsagePercentText() {
        return row().findElement( By.cssSelector("div > section > svg > g > text") );
    }

    public WebElement FreeText() {
        return subrow().findElement( By.cssSelector("div:nth-child(1) > div > div") );
    }
    public WebElement UsedText() {
        return subrow().findElement( By.cssSelector("div:nth-child(2) > div") );
    }
    public WebElement TotalText() {
        return subrow().findElement( By.cssSelector("div:nth-child(3) > div") );
    }

    public WebElement FreeLabelText() {
        return subrow().findElement( By.cssSelector("div:nth-child(1) > div > small") );
    }
    public WebElement UsedLabelText() {
        return subrow().findElement( By.cssSelector("div:nth-child(2) > small") );
    }
    public WebElement TotalLabelText() {
        return subrow().findElement( By.cssSelector("div:nth-child(3) > small") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public StorageCapacityPanel clickUsagePercent() {
        UsagePercentText().click();
        return this;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public StorageCapacityPanel verifyIsDisplayed() {
        delay(500);
        // check panel specific properties
        this.
                verifyTitle().
                verifyLabels();
        return this;
    }
    public StorageCapacityPanel verifyTitle() {
        assertThat( addClass("Incorrect title for panel"), TitleText().getText(), equalTo("Storage Capacity") );
        return this;
    }
    public StorageCapacityPanel verifyPercentUsed(String expected) {
        // TODO: for now just checking it contains % Used, the pct value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for % Used"), UsagePercentText().getText(), containsString("% Used") );
        //assertThat( addClass("Incorrect % Used"), UsagePercentText().getText(), equalTo(expected) );
        return this;
    }

    public StorageCapacityPanel verifyFreeSpace(String expected) {
        // TODO: for now just checking it contains GIB, the value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for Free space"), FreeText().getText(), containsString("GIB") );
        //assertThat( addClass("Incorrect value for Free space"), FreeText().getText(), equalTo(expected) );
        return this;
    }
    public StorageCapacityPanel verifyUsedSpace(String expected) {
        // TODO: for now just checking it contains GIB, the value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for Used space"), UsedText().getText(), containsString("GIB") );
        //assertThat( addClass("Incorrect value for Used space"), UsedText().getText(), equalTo(expected) );
        return this;
    }
    public StorageCapacityPanel verifyTotalSpace(String expected) {
        // TODO: for now just checking it contains GIB, the value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for Total space"), TotalText().getText(), containsString("GIB") );
        //assertThat( addClass("Incorrect value for Total space"), TotalText().getText(), equalTo(expected) );
        return this;
    }

    public StorageCapacityPanel verifyLabels() {
        assertThat( addClass("Incorrect label for Free space"), FreeLabelText().getText(), equalTo("Free") );
        assertThat( addClass("Incorrect label for Used space"), UsedLabelText().getText(), equalTo("Used") );
        assertThat( addClass("Incorrect label for Total space"), TotalLabelText().getText(), equalTo("Total") );
        return this;
    }

}