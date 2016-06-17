/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.test.functional.signin;

import com.symantec.qa.common.ui.PageObject;
import com.symantec.qa.rover.App;
import org.testng.annotations.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class SignInPositiveTest extends PageObject {

    @AfterMethod
    public void tearDown() {
        App.Header().logout();
    }

    /**
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>
     * <a href="http://10.81.24.158/testrail/index.php?/cases/view/5212&group_by=cases:section_id&group_id=356&group_order=asc" TARGET="_blank">
     *     C5212 - Default admin user exists
     * </a></u>
     */
    @Test(groups = "smoke")
    public void checkDefaultAdminUserExists() {
        new App() {{
            LoginPage().loginAsAdmin();             // test signin ass admin
            Header().verifyUserLoggedIn("admin");   // verify user is logged-in
            Navbar().verifyAllNavigation();         // click thru navigation
        }};
    }

    /**
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>
     * <a href="http://10.81.24.158/testrail/index.php?/cases/view/5213&group_by=cases:section_id&group_id=356&group_order=asc" TARGET="_blank">
     *     C5213 - Default support user exists
     * </a></u>
     */
    @Test(groups = "smoke")
    public void checkDefaultSupportUserExists() {
        new App() {{
            LoginPage().loginAsSupport();               // test signin as support
            Header().verifyUserLoggedIn("support");     // verify user is logged-in
            Navbar().verifyAllNavigation();             // click thru navigation
        }};
    }

}