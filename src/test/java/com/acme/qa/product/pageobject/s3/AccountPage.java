/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.s3;

import com.symantec.qa.rover.App;
import com.symantec.qa.rover.datamodel.Account;
import com.symantec.qa.rover.datamodel.Key;
import com.symantec.qa.rover.datamodel.User;
import com.symantec.qa.rover.pageobject.common.AlertMessage;
import com.symantec.qa.rover.pageobject.common.dialog.account.AddAccountDialog;
import com.symantec.qa.rover.pageobject.common.dialog.account.AddUserDialog;
import com.symantec.qa.rover.pageobject.common.dialog.account.NewAccessKeyDialog;
import com.symantec.qa.rover.pageobject.common.dialog.account.DeleteAccessKeyDialog;
import com.symantec.qa.rover.pageobject.common.dialog.account.DisableAccessKeyDialog;
import com.symantec.qa.rover.pageobject.common.dialog.account.EnableAccessKeyDialog;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class AccountPage extends PageObject {

    final private Account expectedAccount;

    public AccountPage(Account expectedAccount) {
        this.expectedAccount = expectedAccount;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* locator                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    By byEmailLink = By.cssSelector(".accountNameData > ul > li:nth-child(2) > a");
    By byAccountDataLeft = By.cssSelector(".accountDataLeft");
    By byNgRepeater_AccountsAsResults = By.cssSelector("*[ng-repeat='account in accounts as results']");
    By byNgRepeater_AccountAccessKeys = By.cssSelector("*[ng-repeat='accesskey in account.accesskeys']");


    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    /* top of the page */
    public WebElement AddAccountLink() {
        return findElement( By.cssSelector(".fa.fa-plus-circle") );
    }

    /* show access keys */
    public WebElement ShowAccessKeysIcon() {
        return accountColumn( By.cssSelector(".accountNameData .fa.fa-plus-square-o") );
    }
    public WebElement HideAccessKeysIcon() {
        return accountColumn( By.cssSelector(".accountNameData .fa.fa-minus-square-o") );
    }

    /* account details */
    public WebElement EmailLink() {
        return accountColumn(byEmailLink);
    }
    public WebElement NameText() {
        return accountColumns(byAccountDataLeft).get(0);
    }
    public WebElement CreatedText() {
        return accountColumns(byAccountDataLeft).get(1);
    }
    public WebElement LastModifiedText() {
        return accountColumns(byAccountDataLeft).get(2);
    }
    public WebElement StatusText() {
        return accountColumns(byAccountDataLeft).get(3);
    }

    /* account actions */
    public WebElement AddUserIcon() {
        return accountColumn( By.cssSelector(".accountData .fa.fa-user-plus") );
    }
    public WebElement GenerateAccessKeyIcon() {
        delay(500);
        return accountColumn( By.cssSelector(".accountData .fa.fa-key") );
    }

    /* expanded accesskeys */
    public WebElement EnabledText() {
        return accountColumn( By.cssSelector(".accesskeyHolder .enableNumber") );
    }
    public WebElement DisabledText() {
        return accountColumn( By.cssSelector(".accesskeyHolder .disableNumber") );
    }

    /* accesskeys actions */
    public WebElement DeleteIcon(Key key) {
        return getAccessKeyRow(key).findElement( By.cssSelector("*[tooltip='Delete']") );
    }
    public WebElement DisableIcon(Key key) {
        return getAccessKeyRow(key).findElement( By.cssSelector("*[tooltip='Disable']") );
    }
    public WebElement EnableIcon(Key key) {
        return getAccessKeyRow(key).findElement( By.cssSelector("*[tooltip='Enable']") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* keyword                                                                                      */
    /* -------------------------------------------------------------------------------------------- */
    public AccountPage addAccount() {
        // click add account link
        AddAccountLink().click();

        // fillout add account modal dialog form and submit it
        new AddAccountDialog() {{
            // set account name field
            NameField().sendKeys( expectedAccount.getName() );
            delay(500);

            // set account email field
            EmailField().sendKeys( expectedAccount.getEmail() );
            delay(500);

            // click add button
            AddButton().click();
            delay(1000);
        }};

        return this;
    }

    public AccountPage addUser(final User user) {
        // click to add new user on account page (click Add User + icon under Actions)
        AddUserIcon().click();

        new AddUserDialog() {{
            // set user name field
            NameField().sendKeys( user.getName() );
            delay(500);

            // set user email field
            EmailField().sendKeys( user.getEmail() );
            delay(500);

            // click add button
            AddButton().click();
            delay(1000);
        }};

        return this;
    }

    public AccountPage dismissAlert() {
        new AlertMessage().AlertX().click();
        return this;
    }

    public AccountPage showAccessKeys() {
        delay(500);
        ShowAccessKeysIcon().click();
        return this;
    }

    public Key generateAccessKey() {
        // click generate access key icon for key
        GenerateAccessKeyIcon().click();

        // scrape key off of modal dialog
        final Key[] key = new Key[1];

        // check key, note then close dialog
        new NewAccessKeyDialog() {{
            key[0] = getKey();
            verifyKey(key[0]);                                      // check keys are not null or empty
            verifyNote(Expected.INFO.CHECK_EMAIL_FOR_SECRET_KEY);   // check note on dialog - check email for secret key
            CloseButton().click();  delay(500);                     // close dialog
        }};

        return key[0];
    }

    public AccountPage deleteAccessKey(final Key key) {
        // click delete icon for key
        DeleteIcon(key).click();

        // confirm delete
        new DeleteAccessKeyDialog() {{
            verifyQuestion(key, expectedAccount);
            DeleteButton().click();
        }};

        return this;
    }

    public AccountPage disableAccessKey(final Key key) {
        // click disable icon for key
        DisableIcon(key).click();

        // confirm disable
        new DisableAccessKeyDialog() {{
            verifyQuestion(key, expectedAccount);
            DisableButton().click();
        }};

        return this;
    }

    public AccountPage enableAccessKey(final Key key) {
        // click enable icon for key
        EnableIcon(key).click();

        // confirm disable
        new EnableAccessKeyDialog() {{
            verifyQuestion(key, expectedAccount);
            EnableButton().click();
        }};

        return this;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public AccountPage verifyAccount() {
        delay(500);
        // just compare date - time is lil tricky with rollovers
        DateTime actualCreated =
                DateTimeFormat.
                        forPattern( App.DATETIME_PATTERN ).
                        parseDateTime( CreatedText().getText() );

        DateTime actualLastModified =
                DateTimeFormat.
                        forPattern( App.DATETIME_PATTERN ).
                        parseDateTime( LastModifiedText().getText() );

        /*
         * check account details correct on page
         */
        assertThat( addClass("Incorrect Email"), EmailLink().getText(), equalTo( expectedAccount.getEmail() ) );
        assertThat( addClass("Incorrect Name"), NameText().getText(), equalTo( expectedAccount.getName() ) );
        assertThat( addClass("Incorrect Created Date"),
                DateUtils.isSameDay( actualCreated.toDate(), expectedAccount.getCreated().toDate() ),
                is(true)
        );
        assertThat( addClass("Incorrect Last Modified Date"),
                DateUtils.isSameDay( actualLastModified.toDate(), expectedAccount.getLastModified().toDate() ),
                is(true)
        );
        assertThat( addClass("Incorrect Status"), StatusText().getText(), equalTo( expectedAccount.getStatus() ) );

        return this;
    }

    public AccountPage verifyAlert(final String expected) {
        checkAlert(expected);
        return this;
    }

    public AccountPage verifyAccessKeyCounts(int enabledCount, int disabledCount) {
        delay(500);
        assertThat( addClass("Incorrect Enabled key count"),
                EnabledText().getText(), equalTo("Enabled " + enabledCount));

        assertThat( addClass("Incorrect Disabled key count"),
                DisabledText().getText(), equalTo("Disabled " + disabledCount));

        return this;
    }

    public AccountPage verifyAccessKeyPanel_Expanded() {
        verifyAccessKeyPanel(true);
        return this;
    }

    public AccountPage verifyAccessKeyPanel_Hidden() {
        verifyAccessKeyPanel(true);
        return this;
    }

    public AccountPage verifyAccessKeyPanel(boolean checkPanelExpanded) {
        delay(500);
        String failMsg = addClass("AccessKey panel is ");

        if (checkPanelExpanded) {
            assertThat(failMsg + "NOT expanded.", HideAccessKeysIcon().isDisplayed(), is(true));
        }
        else {
            assertThat(failMsg + "expanded.", ShowAccessKeysIcon().isDisplayed(), is(true));
        }

        return this;
    }

    /* -------------------------------------------------------------------------------------------- */
    /* helper                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement accountColumn(By by) {
        return getAccountRow().findElement(by);
    }

    private List<WebElement> accountColumns(By by) {
        return getAccountRow().findElements(by);
    }

    private WebElement getAccountRow() {
        String lookup = expectedAccount.getEmail();
        List<WebElement> inList = findElements(byNgRepeater_AccountsAsResults);

        return findBy(lookup, inList);
    }

    private WebElement getAccessKeyRow(Key expectedKey) {
        String lookup = expectedKey.getAccessKey();
        List<WebElement> inList = findElements(byNgRepeater_AccountAccessKeys);

        return findBy(lookup, inList);
    }

    private WebElement findBy(String expected, List<WebElement> inThisList) {
        // search each row in the list containing the expected value
        for (WebElement row : inThisList) {
            try {
                // check if expected contained in row element
                if ( row.getAttribute("innerHTML").contains(expected) ) {
                    // match was found so return the webelement with all its children
                    return row;
                }
            }
            catch (NoSuchElementException e) {
                // just keep checking remaining rows until EOL
                continue;
            }
        }

        throw new RuntimeException("Unable to find a row containing the expected value in the list: " + expected);
    }

}