/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.framework;

import java.util.Comparator;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class PropertyFileComparator implements Comparator<PropertyFile> {

    public int compare(PropertyFile propFile1, PropertyFile propFile2) {
        return propFile1.getPriority() - propFile2.getPriority();
    }
}
