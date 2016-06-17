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
public class AddUserDialog extends PageObject {

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
        return _dialog().findElement( By.cssSelector("#mAddBtn") );
    }
    public WebElement CancelButton() {
        return _dialog().findElement( By.cssSelector("mAddCancelBtn") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement _dialog() {
        return findElement( By.id("createUserModal") );
    }

}
