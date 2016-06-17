/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.globalhealth;

import com.symantec.qa.common.ui.PageObject;
import com.symantec.qa.rover.pageobject.globalhealth.section.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class GlobalHealthPage extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* sub-context objects                                                                          */
    /* -------------------------------------------------------------------------------------------- */
    public HealthSection HealthSection() {
        return new HealthSection();
    }
    public CapacitySection CapacitySection() {
        return new CapacitySection();
    }
    public PerformanceSection PerformanceSection() {
        return new PerformanceSection();
    }
    public DataSafetySection DataSafetySection() {
        return new DataSafetySection();
    }

}