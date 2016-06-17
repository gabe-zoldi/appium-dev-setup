/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.common;

import org.openqa.selenium.By;
import com.symantec.qa.common.ui.PageObject;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class Navbar extends PageObject {

    /* ---------------------------------------- */
    /* locator                                  */
    /* ---------------------------------------- */
    String locMain          = "//*[@id='nav']//span[contains(.,'%s')]";
    String locGlobalHealth  = "//*[@class='tabsHolder']//*[contains(.,'%s')]";
    String locS3            = "//*[@class='nav nav-tabs nav-justified']//*[contains(.,'%s')]";
    String locConfiguration = "//*[@class='tabsHolder']//*[contains(.,'%s')]";

    /* ---------------------------------------- */
    /* web control                              */
    /* ---------------------------------------- */
    /* Dashboard */
    public void gotoDashboard() {
        click("Dashboard", locMain, 2000);
    }

    /* Global Health */
    public void gotoGlobalHealth() {
        click("Global Health", locMain, 500);
    }
    public void gotoGlobalHealth_Health() {
        click("Health", locGlobalHealth, 500);
    }
    public void gotoGlobalHealth_Capacity()  {
        click("Capacity", locGlobalHealth, 500);
    }
    public void gotoGlobalHealth_Performance()  {
        click("Performance", locGlobalHealth, 500);
    }
    public void gotoGlobalHealth_DataSafety()  {
        click("Data Safety", locGlobalHealth, 500);
    }

    /* Events */
    public void gotoEvents() {
        click("Events", locMain, 500);
    }

    /* Resources */
    public void gotoResources() {
        click("Resources", locMain, 500);
    }

    /* S3 */
    public void gotoS3() {
        click("S3", locMain, 500);
    }
    public void gotoS3_AccountsByCapacity() {
        click("Accounts By Capacity", locS3, 500);
    }
    public void gotoS3_AccountsByActivity() {
        click("Accounts By Activity", locS3, 500);
    }
    public void gotoS3_AccountsAndUsersManagement() {
        click("Accounts and Users Management", locS3, 500);
    }
    public void gotoS3_SystemBucket() {
        click("System Bucket", locS3, 500);
    }

    /* Configuration */
    public void gotoConfiguration() {
        click("Configuration", locMain, 500);
    }
    public void gotoConfiguration_SystemInfo() {
        click("System Info", locConfiguration, 500);
    }
    public void gotoConfiguration_Security() {
        click("Security", locConfiguration, 500);
    }
    public void gotoConfiguration_Networking() {
        click("Networking", locConfiguration, 500);
    }
    public void gotoConfiguration_Notification() {
        click("Notification", locConfiguration, 500);
    }
    public void gotoConfiguration_S3() {
        click("S3", locConfiguration, 500);
    }
    public void gotoConfiguration_Telemetry() {
        click("Telemetry", locConfiguration, 500);
    }

    /* ---------------------------------------- */
    /* verify                                   */
    /* ---------------------------------------- */
    public void verifyAllNavigation() {
        // click all menu items for navigation
        gotoDashboard();
        gotoGlobalHealth();
        gotoEvents();
        gotoResources();
        gotoS3();
        gotoConfiguration();

        gotoGlobalHealth();
        gotoGlobalHealth_Health();
        gotoGlobalHealth_Capacity();
        gotoGlobalHealth_Performance();
        gotoGlobalHealth_DataSafety();

        gotoS3();
        gotoS3_AccountsByCapacity();
        gotoS3_AccountsByActivity();
        gotoS3_AccountsAndUsersManagement();
        gotoS3_SystemBucket();

        gotoConfiguration();
        gotoConfiguration_SystemInfo();
        gotoConfiguration_Security();
        gotoConfiguration_Networking();
        gotoConfiguration_Notification();
        gotoConfiguration_S3();
        gotoConfiguration_Telemetry();

        gotoDashboard();
    }

    /* ---------------------------------------- */
    /* helper                                   */
    /* ---------------------------------------- */
    private void click(String option, String xpathLocator, long wait) {
        findElement(By.xpath( String.format(xpathLocator, option))).click();
        delay(wait);
    }

}