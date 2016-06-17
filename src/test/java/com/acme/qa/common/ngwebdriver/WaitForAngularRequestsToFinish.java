/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ngwebdriver;

import org.openqa.selenium.JavascriptExecutor;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class WaitForAngularRequestsToFinish {

    public static void waitForAngularRequestsToFinish(JavascriptExecutor driver) {
        driver.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    }

}