/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.testng;

import com.symantec.qa.common.framework.ConfigurationManager;
import com.symantec.qa.common.framework.logging.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class EventListener implements ITestListener {

    public void onTestStart(ITestResult result) { }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result) {
        boolean takeScreenshot = Boolean.parseBoolean(
                ConfigurationManager.getProperty("screenshot.on.failure") );
        if (takeScreenshot) {
            String fileName = String.format("autoshot_%s",  result.getMethod());
            Log.takeScreenshot(fileName, true);
        }
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        Log.setScreenshotDir(context.getOutputDirectory());
    }

    public void onFinish(ITestContext context) {

    }

}