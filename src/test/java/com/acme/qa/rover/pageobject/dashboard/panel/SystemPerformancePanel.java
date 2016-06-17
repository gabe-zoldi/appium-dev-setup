/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.dashboard.panel;

import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class SystemPerformancePanel extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement panel() {
        return findElement( By.cssSelector(".panel.panel-default.sysperform") );
    }
    private WebElement row() {
        return panel().findElement( By.cssSelector(".panel-body > .row ") );
    }

    public WebElement TitleText() {
        return panel().findElement( By.cssSelector(".panel-title") );
    }

    public WebElement ResponseTimeText() {
        return row().findElement( By.cssSelector("div:nth-child(1) > div") );
    }
    public WebElement ReadThroughputText() {
        return row().findElement( By.cssSelector("div:nth-child(2) > div") );
    }
    public WebElement CurrentDataRateText() {
        return row().findElement( By.cssSelector("div:nth-child(3) > div") );
    }

    public WebElement ResponseTimeLabelText() {
        return row().findElement( By.cssSelector("div.spacedown:nth-child(1)") );
    }
    public WebElement ReadThroughputLabelText() {
        return row().findElement( By.cssSelector("div.spacedown:nth-child(2)") );
    }
    public WebElement CurrentDataRateLabelText() {
        return row().findElement( By.cssSelector("div.spacedown:nth-child(3)") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public SystemPerformancePanel clickResponseTime() {
        ResponseTimeText().click();
        return this;
    }
    public SystemPerformancePanel clickReadThroughput() {
        ReadThroughputText().click();
        return this;
    }
    public SystemPerformancePanel clickCurrentDataRate() {
        CurrentDataRateText().click();
        return this;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public SystemPerformancePanel verifyIsDisplayed() {
        delay(500);
        // check panel specific properties
        this.
                verifyTitle().
                verifyLabels();
        return this;
    }
    public SystemPerformancePanel verifyTitle() {
        assertThat( addClass("Incorrect title for panel"), TitleText().getText(), equalTo("System Performance") );
        return this;
    }
    public SystemPerformancePanel verifyResponseTime(String expected) {
        // TODO: for now just checking it contains ms, the value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for Response Time"), ResponseTimeText().getText(), containsString("ms") );
        //assertThat( addClass("Incorrect value for Response Time"), ResponseTimeText().getText(), equalTo(expected) );
        return this;
    }
    public SystemPerformancePanel verifyReadThroughput(String expected) {
        // TODO: for now just checking it contains MiB/s, the value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for Read Throughput"), ReadThroughputText().getText(), containsString("MiB/s") );
        //assertThat( addClass("Incorrect value for Read Throughput"), ReadThroughputText().getText(), equalTo(expected) );
        return this;
    }
    public SystemPerformancePanel verifyCurrentDataRate(String expected) {
        // TODO: for now just checking it contains MiB/s, the value keeps changing
        // TODO: need to figure out how to query the expected value maybe from influxdb
        assertThat( addClass("Incorrect value for Current Data Rate"), CurrentDataRateText().getText(), containsString("MiB/s") );
        //assertThat( addClass("Incorrect value for Current Data Rate"), CurrentDataRateText().getText(), equalTo(expected) );
        return this;
    }

    public SystemPerformancePanel verifyLabels() {
        assertThat( addClass("Incorrect label for Response Time"),
                ResponseTimeLabelText().getText(),
                allOf(
                        containsString("ms"),
                        containsString("Response Time"),
                        containsString("as of"),
                        containsString("today")
                )
        );
        assertThat( addClass("Incorrect label for Read Throughput"),
                ReadThroughputLabelText().getText(),
                allOf(
                        containsString("MiB/s"),
                        containsString("Current Request Rate"),
                        containsString("Read Throughput")
                )
        );
        assertThat( addClass("Incorrect label for Current Data Rate"),
                CurrentDataRateLabelText().getText(),
                allOf(
                        containsString("MiB/s"),
                        containsString("Current Data Rate")
                )
        );

        return this;
    }

}