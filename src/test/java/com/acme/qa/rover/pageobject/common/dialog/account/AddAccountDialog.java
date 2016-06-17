/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.common.dialog.account;

import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by gzold7195817 on 11/11/15.
 */
public class AddAccountDialog extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement NameField() {
        return _dialog().findElement( By.name("name") );
    }
    public WebElement EmailField() {
        return _dialog().findElement( By.name("email") );
    }
    public WebElement AddButton() {
        return _dialog().findElement( By.xpath("//button[text()='Add']") );
    }
    public WebElement CancelButton() {
        return _dialog().findElement( By.xpath("//button[text()='Cancel']") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement _dialog() {
        return findElement( By.id("createAccountModal") );
    }

}
