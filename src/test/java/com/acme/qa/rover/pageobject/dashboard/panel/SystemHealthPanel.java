/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.dashboard.panel;

import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class SystemHealthPanel extends PageObject {

    public enum Time {
        // underscores are for ordering; also enums can't start with numbers
        __1AM(0), __2AM(1), __3AM(2), __4AM(3), __5AM(4), __6AM(5), __7AM(6), __8AM(7),
        __9AM(8), __10AM(9), __11AM(10), ___12PM(11), ____1PM(12), ____2PM(13), ____3PM(14), ____4PM(15),
        ____5PM(16), ____6PM(17), ____7PM(18), ____8PM(19), ____9PM(20), ____10PM(21), ____11PM(22), ____12AM(23);

        // index of chartBar element
        private final int index;

        Time(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public enum Day {
        TODAY_MINUS_6(0), TODAY_MINUS_5(1), TODAY_MINUS_4(2), TODAY_MINUS_3(3),
        TODAY_MINUS_2(4), TODAY_MINUS_1(5), TODAY(6);

        // index of chartDisplayData element
        private final int index;

        Day(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement panel() {
        return findElement( By.cssSelector(".panel.panel-default.systemHealth") );
    }
    private WebElement panelTitle() {
        return panel().findElement( By.cssSelector(".panel-title") );
    }
    private WebElement row() {
        return panel().findElement( By.cssSelector(".panel-body .row") );
    }

    public WebElement TitleText() {
        return panelTitle().findElement( By.cssSelector("span:nth-child(1)") );
    }
    public WebElement SeverityText() {
        return panelTitle().findElement( By.cssSelector("*[ng-class*='currentSeverity']") );
    }
    public List<WebElement> BarChartList() {
        return findElements( By.cssSelector("*[ng-repeat*='chartDisplayData']") );
    }
    public WebElement BarChartList(Day day) {
        return BarChartList().get( day.getIndex() );
    }
    public List<WebElement> BarsList(Day day) {
        return BarChartList(day).findElements( By.cssSelector("*[ng-repeat*='chartDisplay.severity']") );
    }
    public WebElement Bar(Day day, Time time) {
        return BarsList(day).get( time.getIndex() );
    }
    public WebElement HeartBeatIcon() {
        return findElement( By.cssSelector(".fa.fa-heartbeat") );
    }
    public WebElement ChartTimeRangeText() {
        return row().findElement( By.cssSelector(".systemHealthChart .chart .side .customLabel") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public SystemHealthPanel clickHeartBeat() {
        HeartBeatIcon().click();
        return this;
    }
    public SystemHealthPanel clickBar(Day day, Time time) {
        // single bar of <time> for the chartDisplay on <day>
        WebElement bar = Bar(day, time);
        mouseOver(bar);
        delay(2000);

        bar.click();
        return this;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public SystemHealthPanel verifyIsDisplayed() {
        delay(500);
        // check panel specific properties
        this.
                verifyTitle().
                verifySeverity("NO DATA").
                verifyBarChartCount(7).
                verifyChartTimeRange("1 AM\n11 PM");
        return this;
    }
    public SystemHealthPanel verifyTitle() {
        assertThat( addClass("Incorrect title for panel"), TitleText().getText(), equalTo("System Health") );
        return this;
    }
    public SystemHealthPanel verifySeverity(String expected) {
        assertThat( addClass("Incorrect value for Severity"), SeverityText().getText(), equalTo(expected) );
        return this;
    }
    public SystemHealthPanel verifyBarChartCount(int expected) {
        assertThat( addClass("Incorrect count of Bar Charts"), BarChartList().size(), is(expected) );
        return this;
    }
    public SystemHealthPanel verifyChartTimeRange(String expected) {
        assertThat( addClass("Incorrect time range for Bar Charts"), ChartTimeRangeText().getText(), equalTo(expected) );
        return this;
    }

}