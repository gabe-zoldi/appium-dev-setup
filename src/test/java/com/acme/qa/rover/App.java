/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover;

import com.symantec.qa.rover.datamodel.Account;
import com.symantec.qa.rover.pageobject.LoginPage;
import com.symantec.qa.rover.pageobject.ProfilePage;
import com.symantec.qa.rover.pageobject.common.Header;
import com.symantec.qa.rover.pageobject.common.Navbar;
import com.symantec.qa.rover.pageobject.s3.AccountPage;
import com.symantec.qa.rover.pageobject.s3.UserPage;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 *
 * Rover Application Interface for automating mobile regression tests.
 *
 */
public class App {
    /* -------------------------------------------------------------------------------------------- */
    /* constant                                                                                     */
    /* -------------------------------------------------------------------------------------------- */
    public final static String DATETIME_PATTERN = "MM/dd/yy hh:mm a";

    public static class Test {
        public static final String EMAIL = "roverqa+mobile-automation@gmail.com";
        public static final String EMAIL_PASSWORD = "symc1234";
        public final static String EMAIL_DOMAIN = "@acme.com";
    }

    /* -------------------------------------------------------------------------------------------- */
    /* context objects                                                                              */
    /* -------------------------------------------------------------------------------------------- */
    public static Header Header() {
        return new Header();
    }
    public static Navbar Navbar() {
        return new Navbar();
    }
    public static LoginPage LoginPage() {
        return new LoginPage();
    }
    public static ProfilePage ProfilePage() {
        return new ProfilePage();
    }
    public static AccountPage AccountPage(Account account) {
        return new AccountPage(account);
    }
    public static UserPage UserPage(Account account) {
        return new UserPage(account);
    }

}