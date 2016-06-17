/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.pageobject.dashboard;

import com.symantec.qa.common.ui.PageObject;
import com.symantec.qa.rover.pageobject.dashboard.panel.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DashboardPage extends PageObject {

    /* -------------------------------------------------------------------------------------------- */
    /* sub-context objects                                                                          */
    /* -------------------------------------------------------------------------------------------- */
    public SystemHealthPanel SystemHealthPanel() {
        return new SystemHealthPanel();
    }
    public StorageCapacityPanel StorageCapacityPanel() {
        return new StorageCapacityPanel();
    }
    public DataSafetyPanel DataSafetyPanel() {
        return new DataSafetyPanel();
    }
    public SystemPerformancePanel SystemPerformancePanel() {
        return new SystemPerformancePanel();
    }
    public S3Panel S3Panel() {
        return new S3Panel();
    }

}