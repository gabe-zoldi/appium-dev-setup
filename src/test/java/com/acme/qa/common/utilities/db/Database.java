/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public interface Database {

    public ArrayList<Map<String, String>> executeQuery(String query);

    public int executeUpdate(String query);

    public ResultSet getResultSet(String query);

    public String getDriver();
    public String getUrl();
    public String getHost();
    public String getPort();
    public String getUser();
    public String getPassword();
    public String getName();

}