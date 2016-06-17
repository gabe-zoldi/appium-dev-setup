/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.common.dialog.account;

import com.symantec.qa.common.ui.PageObject;
import com.symantec.qa.rover.datamodel.Account;
import com.symantec.qa.rover.datamodel.Key;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gzold7195817 on 11/11/15.
 */
public class EnableAccessKeyDialog extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* constant                                                                                     */
    /* -------------------------------------------------------------------------------------------- */
    public class Expected {
        public class QUESTION {
            public static final String CONFIRM = "Do you really want to enable access key %s for account %s?";
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement QuestionText() {
        return _dialog().findElement( By.cssSelector(".modal-body") );
    }
    public WebElement EnableButton() {
        return _dialog().findElement( By.cssSelector("#mDisEnBtn") );
    }
    public WebElement CancelButton() {
        return _dialog().findElement( By.cssSelector("#mDisEnCancelBtn") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* web verify                                                                                   */
    /* -------------------------------------------------------------------------------------------- */
    public void verifyQuestion(Key key, Account account) {
        delay(500);
        String actual = QuestionText().getText();
        String expected = String.format(Expected.QUESTION.CONFIRM, key.getAccessKey(), account.getName());
        assertThat( addClass("Incorrect question displayed"), actual, equalTo(expected) );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement _dialog() {
        return findElement( By.id("disEnAccessKeyModal") );
    }

}
