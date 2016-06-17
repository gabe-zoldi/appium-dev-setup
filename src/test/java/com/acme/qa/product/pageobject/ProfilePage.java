/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject;

import com.symantec.qa.rover.App;
import org.openqa.selenium.By;
import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class ProfilePage extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* constant                                                                                     */
    /* -------------------------------------------------------------------------------------------- */
    public static class Expected {
        public static class SUCCESS {
            public static final String USER_DATA_UPDATED = "user data is updated.";
            public static final String PASSWORD_UPDATED = "password is updated.";
        }
        public static class INFO {

        }
        public static class ERROR {
            public static final String ADDITIONAL_PROP_NOT_ALLOWED = "Invalid message: Additional properties not allowed";
            public static final String STRING_DOES_NOT_MATCH_PATTERN = "Invalid message: String does not match pattern:";
            public static final String VALUES_ARE_NOT_SAME = "values are not the same";
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement EmailField() {
        return findElement( By.cssSelector("#email") );
    }
    public WebElement UpdateUserInfoButton() {
        return findElement( By.xpath("//button[text()='Update User Info']") );
    }
    public WebElement OldPasswordField() {
        return findElement( By.name("oldpassword") );
    }
    public WebElement NewPasswordField() {
        return findElement( By.name("newpassword") );
    }
    public WebElement ConfirmNewPasswordField() {
        return findElement( By.name("confirmnewpassword") );
    }
    public WebElement ChangePasswordButton() {
        return findElement( By.xpath("//button[text()='Change password']") );
    }
    public WebElement ErrorText() {
        return findElement( By.cssSelector(".error.ng-scope.ng-active") );
    }
    public WebElement SuccessAlert() {
        return findElement( By.cssSelector("div.alert.ng-isolate-scope.alert-success > span") );
    }
    public WebElement SuccessAlertX() {
        // X inside alert message to dismiss it
        return findElement( By.cssSelector("div.alert.ng-isolate-scope.alert-success > button > span") );
    }
    public WebElement ErrorAlert() {
        return findElement( By.cssSelector("div.alert.ng-isolate-scope.alert-danger > span") );
    }
    public WebElement ErrorAlertX() {
        // X inside alert message to dismiss it
        return findElement( By.cssSelector("div.alert.ng-isolate-scope.alert-danger > button > span") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public void gotoPage() {
        new App() {{
            Header().HumanIcon().click();
            delay(500);

            Header().ProfileDropdown().click();
            delay(500);
        }};
    }

    public void updateUserInfo(final String email) {
        EmailField().clear();
        delay(500);

        EmailField().sendKeys( email );
        delay(500);

        UpdateUserInfoButton().click();
        delay(500);
    }

    public void changePassword(final String oldPassword, final String newPassword, final String confirmNewPassword) {
        // set old password
        OldPasswordField().sendKeys(oldPassword);
        delay(500);

        // set new password
        NewPasswordField().sendKeys(newPassword);
        delay(500);

        // set confirm new password
        ConfirmNewPasswordField().sendKeys(confirmNewPassword);
        delay(500);

        // submit change
        ChangePasswordButton().click();
        delay(500);
    }

    public void dismissAlertSuccess() {
        SuccessAlertX().click();
        delay(500);
    }

    public void dismissAlertError() {
        ErrorAlertX().click();
        delay(500);
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public void verifyConfirmNewPasswordError(final String expected) {
        delay(500);
        assertThat( addClass("Incorrect new password error message"),
                ErrorText().getText(), equalTo(expected));
    }

    public void verifyAlertMessage(final String expected, final WebElement alert) {
        delay(500);
        assertThat( addClass("Incorrect alert message"), alert.getText(), equalTo(expected));
    }

    public void verifyAlertMessageContains(final String expected, final WebElement alert) {
        assertThat( addClass("Alert message did not contain: " + expected),
                alert.getText(), containsString(expected));
    }

}