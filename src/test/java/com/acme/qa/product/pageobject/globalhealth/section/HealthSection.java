/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.globalhealth.section;

import com.symantec.qa.common.ui.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class HealthSection extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* web control                                                                                  */
    /* -------------------------------------------------------------------------------------------- */
    private WebElement SubMenuText_Underlined() {
        return findElement( By.cssSelector(".tabsHolder .active") );
    }

    /* -------------------------------------------------------------------------------------------- */
    /* verify                                                                                       */
    /* -------------------------------------------------------------------------------------------- */
    public HealthSection verifyIsDisplayed() {
        // check section selected in sub-menu
        assertThat( addClass("Sub-menu was not highlighted (or underlined)"),
                SubMenuText_Underlined().getText(), equalTo("Health") );
        return this;
    }

}