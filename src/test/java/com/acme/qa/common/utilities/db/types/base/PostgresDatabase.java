/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.db.types.base;

import com.symantec.qa.common.framework.ConfigurationManager;
import com.symantec.qa.common.utilities.db.DBConnection;
import com.symantec.qa.common.utilities.db.Database;
import com.symantec.qa.common.utilities.db.DatabaseType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class PostgresDatabase extends DBConnection implements Database {

    public ArrayList<Map<String, String>> executeQuery(String query) {
        setConnection();
        return executeDDL(query);
    }

    public int executeUpdate(String query) {
        setConnection();
        return executeDML(query);
    }

    public ResultSet getResultSet(String query) {
        setConnection();
        return executeResultSet(query);
    }

    private DatabaseType dbType = DatabaseType.POSTGRES;

    public String getDriver() {
        return ConfigurationManager.getDatabaseDriver(dbType);
    }

    public String getUrl() {
        return ConfigurationManager.getDatabaseUrl( dbType );
    }

    public String getHost() {
        return ConfigurationManager.getDatabaseHost( dbType );
    }

    public String getPort() {
        return ConfigurationManager.getDatabasePort(dbType);
    }

    public String getUser() {
        return ConfigurationManager.getDatabaseUser( dbType );
    }

    public String getPassword() {
        return ConfigurationManager.getDatabasePassword( dbType );
    }

    public String getName() {
        return ConfigurationManager.getDatabaseName( dbType );
    }

}
