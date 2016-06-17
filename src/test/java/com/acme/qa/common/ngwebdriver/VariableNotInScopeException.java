/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ngwebdriver;

import org.openqa.selenium.WebDriverException;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class VariableNotInScopeException extends WebDriverException {
    public VariableNotInScopeException(String msg) {
        super(msg);
    }
}