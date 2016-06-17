/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DemoChromeDriver extends ChromeDriver {

    private final long DELAY = 200;

    /**
     * Overriding ChromeDriver to better control execution speed which is
     * very useful during demos where it hard to see things running too
     * quickly to see what's happening.  This allows us to slow it down.
     *
     * @param by type of locator
     * @return selenium webelement if found
     */
    @Override
    public WebElement findElement(By by) {
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return by.findElement((SearchContext) this);
    }

}