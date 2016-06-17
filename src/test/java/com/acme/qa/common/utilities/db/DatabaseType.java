/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.db;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public enum DatabaseType {

    MYSQL    ("mysql"),
    ORACLE   ("oracle"),
    MSSQL    ("mssql"),
    POSTGRES ("postgres"),

    QA    ("qa"),
    STAGE ("stage");

    private String type;

    DatabaseType(String type) {
        this.type = type;
    }

    public String getDatabaseType() {
        return type;
    }

}