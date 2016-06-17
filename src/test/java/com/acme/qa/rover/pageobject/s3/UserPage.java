/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.s3;

import com.symantec.qa.common.ui.PageObject;
import com.symantec.qa.rover.App;
import com.symantec.qa.rover.datamodel.Account;
import com.symantec.qa.rover.datamodel.User;
import com.symantec.qa.rover.pageobject.common.AlertMessage;
import com.symantec.qa.rover.pageobject.common.dialog.user.AddUserDialog;
import com.symantec.qa.rover.pageobject.common.dialog.user.DisableUserDialog;
import com.symantec.qa.rover.pageobject.common.dialog.user.EditUserDialog;
import junit.framework.Assert;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class UserPage extends PageObject {

    final private Account expectedAccount;

    public UserPage(Account account) {
        this.expectedAccount = account;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* locator                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    By byUserNameData = By.cssSelector(".userNameData .ng-binding");
    By byNgBinding = By.cssSelector(".ng-binding");
    By byUserDataLeft = By.cssSelector(".userDataLeft");

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement AddUserLink() {
        delay(500);
        return findElement( By.cssSelector("*[data-target='#createUserModal'] .fa.fa-user-plus") );
    }

    /* account data */
    public List<WebElement> AccountDataList() {
        return findElements( By.cssSelector(".accountData") );
    }
    public WebElement AccountNameText() {
        return AccountDataList().get(0).findElement(byNgBinding);
    }
    public WebElement AccountEmailText() {
        return AccountDataList().get(1).findElement(byNgBinding);
    }
    public WebElement AccountCreatedText() {
        return AccountDataList().get(2).findElement(byNgBinding);
    }
    public WebElement AccountLastModifiedText() {
        return AccountDataList().get(3).findElement(byNgBinding);
    }

    /* user data */
    public List<WebElement> UserList() {
        return findElements( By.cssSelector("*[ng-repeat='user in users as results']") );
    }
    public WebElement EmailText(User user) {
        return getUserRow(user).findElement(byUserNameData);
    }
    public WebElement NameText(User user) {
        return getUserRow(user).findElements(byUserDataLeft).get(0);
    }
    public WebElement CreatedText(User user) {
        return getUserRow(user).findElements(byUserDataLeft).get(1);
    }
    public WebElement LastModifiedText(User user) {
        return getUserRow(user).findElements(byUserDataLeft).get(2);
    }
    public WebElement StatusText(User user) {
        return getUserRow(user).findElements(byUserDataLeft).get(3);
    }

    /* user actions */
    public WebElement EditIcon(User user) {
        return getUserRow(user).findElement( By.cssSelector(".userData .fa-pencil") );
    }
    public WebElement DisableIcon(User user) {
        return getUserRow(user).findElement( By.cssSelector(".userData .fa-check-square-o") );
    }
    public WebElement EnableIcon(User user) {
        return getUserRow(user).findElement( By.cssSelector(".userData .fa-square-o") );
    }
    public WebElement GenerateAccessKeyIcon(User user) {
        return getUserRow(user).findElement( By.cssSelector(".userData .fa-key") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public void addUser(final User user) {
        // click to Add New User
        AddUserLink().click();

        new AddUserDialog() {{
            NameField().sendKeys( user.getName() );     delay(500);     // set user name field
            EmailField().sendKeys( user.getEmail() );   delay(500);     // set user email field
            CreateButton().click();                     delay(1000);    // click add button
        }};
    }

    public void dismissAlert() {
        new AlertMessage().AlertX().click();
    }

    public User disableUser(User forUser) {
        // click user action
        DisableIcon(forUser).click();

        // check question and confirm to close dialog
        new DisableUserDialog() {{
            verifyQuestion(forUser);
            DisableButton().click();
            delay(1000);
        }};

        // update data object
        User updatedStatus = ((User) forUser.clone());
        updatedStatus.setStatus(User.Status.DISABLED);
        return updatedStatus;
    }

    public User editUser(final String newName, final String newEmail, final User forUser) {
        // select user action
        EditIcon(forUser).click();
        delay(1000);

        // update fields and submit
        new EditUserDialog() {{
            // clear fields
            NameField().clear();
            EmailField().clear();

            // set new value
            NameField().sendKeys(newName);
            EmailField().sendKeys(newEmail);

            // submit change
            UpdateButton().click();
            delay(1000);
        }};

        // update data object
        User updated = (User) forUser.clone();
        updated.setName(newName);
        updated.setEmail(newEmail);

        // send data object back to caller
        return updated;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public void verifyAccount() {
        delay(2000);

        // just compare date - time is lil tricky with rollovers
        DateTime actualCreated =
                DateTimeFormat.
                        forPattern( App.DATETIME_PATTERN ).
                        parseDateTime( AccountCreatedText().getText() );

        DateTime actualLastModified =
                DateTimeFormat.
                        forPattern( App.DATETIME_PATTERN ).
                        parseDateTime( AccountLastModifiedText().getText() );
        /*
         * check account details on page
         */
        assertThat( addClass("Incorrect Account Name"),
                AccountNameText().getText(), equalTo(expectedAccount.getName())
        );
        assertThat( addClass("Incorrect Account Email"),
                AccountEmailText().getText(), equalTo(expectedAccount.getEmail())
        );
        assertThat( addClass("Incorrect Account Created Date"),
                DateUtils.isSameDay( actualCreated.toDate(), expectedAccount.getCreated().toDate() ),
                is(true)
        );
        assertThat( addClass("Incorrect Account Last Modified Date"),
                DateUtils.isSameDay( actualLastModified.toDate(), expectedAccount.getLastModified().toDate() ),
                is(true)
        );
    }

    public void verifyUser(final User expected) {
        // just compare date - time is lil tricky with rollovers
        DateTime actualCreated =
                DateTimeFormat.
                        forPattern( App.DATETIME_PATTERN ).
                        parseDateTime( CreatedText(expected).getText() );

        DateTime actualLastModified =
                DateTimeFormat.
                        forPattern( App.DATETIME_PATTERN ).
                        parseDateTime( LastModifiedText(expected).getText() );
        /*
         * check user details by email on user page
         */
        assertThat( addClass("Incorrect Email"),
                EmailText(expected).getText(), equalTo(expected.getEmail())
        );
        assertThat( addClass("Incorrect Name"),
                NameText(expected).getText(), equalTo(expected.getName())
        );
        assertThat( addClass("Incorrect Created Date"),
                DateUtils.isSameDay( actualCreated.toDate(), expected.getCreated().toDate() ),
                is(true)
        );
        assertThat( addClass("Incorrect Last Modified Date"),
                DateUtils.isSameDay( actualLastModified.toDate(), expected.getLastModified().toDate() ),
                is(true)
        );
        assertThat( addClass("Incorrect Status"),
                StatusText(expected).getText(), equalTo(expected.getStatus())
        );
    }

    public void verifyAlert(final String expected) {
        delay(500);
        String actual = new AlertMessage().AlertText().getText();
        assertThat( addClass("Incorrect alert message"), actual, equalTo(expected) );
    }

    public void verifyDisabled(final User user) {
        try {
            EnableIcon(user).isDisplayed();
        }
        catch (NoSuchElementException e) {
            Assert.fail( addClass("User is not disabled: " + user.getEmail()) );
        }
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public WebElement getUserRow(User expected) {
        // search for row with user by email
        for (WebElement user : UserList()) {
            try {
                // get email for current row
                String currentEmail = user.findElement(byUserNameData).getText();
                // check if email matches
                if (currentEmail.equals(expected.getEmail()))
                    return user;
            }
            catch (NoSuchElementException e) {
                // continue checking other rows
                continue;
            }
        }
        throw new RuntimeException("Unable to find UserRow containing email: " + expected.getEmail());
    }

}