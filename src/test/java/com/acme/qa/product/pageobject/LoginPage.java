/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject;

import org.openqa.selenium.By;
import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class LoginPage extends PageObject {

    /*
     * --------------------------------------------------------------------------------------------
     * constant
     * --------------------------------------------------------------------------------------------
     */
    public static class Expected {
        public static final String TITLE = PageObject.appUrl + "/signin";
        public static class SUCCESS {

        }
        public static class INFO {

        }
        public static class ERROR {
            public static final String EMAIL_PASSWORD_DO_NOT_MATCH = "Email and password do not match.";
            public static final String INCORRECT_USERNAME_PASSWORD =
                    "Incorrect username or password. Please check your username/password and try again.";
        }
    }
    public static class Default {
        public static class ADMIN {
            public static final String USER = "admin";
            public static final String PASSWORD = "Adm1n$";
        }
        public static class SUPPORT {
            public static final String USER = "support";
            public static final String PASSWORD = "Supp0rt$";
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* locator                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    protected By byUsernameField = By.name("username");
    protected By byPasswordField = By.name("password");
    protected By byLoginButton = By.id("loginBtn");

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement UsernameField() {
        return findElement( byUsernameField  );
    }
    public WebElement PasswordField() {
        return findElement( byPasswordField );
    }
    public WebElement LoginButton() {
        return findElement( byLoginButton );
    }
    public WebElement ForgotPasswordLink()  {
        return findElement( By.linkText("Forgot password?") );
    }
    public WebElement ErrorMessageText() {
        return findElement( By.cssSelector(".row.row-fixed.error.ng-binding[data-ng-show='error']") );
    }
    public WebElement ResetPasswordButton() {
        return findElement( By.xpath("//button[text()='Reset Password']") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public void loginAsAdmin() {
        new LoginPage() {{
            login(Default.ADMIN.USER, Default.ADMIN.PASSWORD);
        }};
    }

    public void loginAsSupport() {
        new LoginPage() {{
            login(Default.SUPPORT.USER, Default.SUPPORT.PASSWORD);
        }};
    }

    public void login(String username, String password) {
        delay(500);

        // set the username and password fields
        UsernameField().sendKeys(username);
        delay(500);

        PasswordField().sendKeys(password);
        delay(500);

        // click to submit signin form
        LoginButton().click();
        delay(500);
    }

    public void clearLoginPage() {
        // clear all fields on page
        UsernameField().clear();
        delay(500);

        PasswordField().clear();
        delay(500);
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public void verifyUsernameRequired() {
        delay(500);
        assertThat( addClass("Username field is not required"),
                UsernameField().getAttribute("required"), notNullValue());
    }
    public void verifyPasswordRequired() {
        delay(500);
        assertThat( addClass("Password field is not required"),
                PasswordField().getAttribute("required"), notNullValue());
    }
    public void verifyLoginButtonIsDisplayed() {
        delay(500);
        assertThat( addClass("Login button not found"),
                LoginButton().isDisplayed(), is(true));
    }
    public void verifyPasswordIsMasked() {
        delay(500);
        assertThat( addClass("Password is unmasked"),
                PasswordField().getAttribute("type"), is("password"));
    }
    public void verifyErrorMessage(String expected) {
        delay(1000);
        assertThat( addClass("Incorrect error message displayed"),
                ErrorMessageText().getText(), equalTo(expected));
    }

}