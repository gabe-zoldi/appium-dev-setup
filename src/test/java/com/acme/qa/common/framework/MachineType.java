/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.framework;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public enum MachineType {

    FE ("frontend"),
    BE ("backend"),
    DB ("database"),

    PORTAL ("portal");

    private final String keyName;

    MachineType(String key) {
        keyName = key;
    }

    public String getKey() {
        return keyName;
    }
}
