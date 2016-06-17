/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.db;

import com.symantec.qa.common.framework.exception.DatabaseManagerRuntimeException;
import com.symantec.qa.common.utilities.db.types.QADatabase;
import com.symantec.qa.common.utilities.db.types.StageDatabase;
import com.symantec.qa.common.utilities.db.types.base.MssqlDatabase;
import com.symantec.qa.common.utilities.db.types.base.MysqlDatabase;
import com.symantec.qa.common.utilities.db.types.base.OracleDatabase;
import com.symantec.qa.common.utilities.db.types.base.PostgresDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DatabaseManager {

    private static Database db = null;

    public static ArrayList<Map<String, String>> executeQuery(String query, DatabaseType type) {
        ArrayList<Map<String, String>> list;

        if ( (db = getDatabase( type )) == null )
            throw new DatabaseManagerRuntimeException("Could not find the database: " + type);

        if ( (list = db.executeQuery( query )) == null )
            throw new DatabaseManagerRuntimeException("[" + query + "] could not be executed.\r\n");

        return list;
    }

    public static int executeUpdate(String query, DatabaseType type) {
        int numRows;

        if ( (db = getDatabase( type )) == null )
            throw new DatabaseManagerRuntimeException("Could not find the database: " + type);

        if ( (numRows = db.executeUpdate( query )) == 0 )
            throw new DatabaseManagerRuntimeException("[" + query + "] could not be executed.\r\n");

        return numRows;
    }

    public static ResultSet getResultSet(String query, DatabaseType type) {
        ResultSet rs;

        if ( (db = getDatabase( type )) == null )
            throw new DatabaseManagerRuntimeException("Could not find the database: " + type);

        if ( (rs = db.getResultSet( query )) == null )
            throw new DatabaseManagerRuntimeException("[" + query + "] could not be executed.\r\n");

        return rs;
    }

    private static Database getDatabase(DatabaseType type) {
        switch (type) {
            // base settings
            case MSSQL:     return new MssqlDatabase();
            case MYSQL:     return new MysqlDatabase();
            case ORACLE:    return new OracleDatabase();
            case POSTGRES:  return new PostgresDatabase();

            // app settings
            case QA: 		return new QADatabase();
            case STAGE: 	return new StageDatabase();

            // misconfiguration
            default: throw new RuntimeException(
                    String.format( "Unable to lookup db cart from DatabaseType: %s", type )
            );
        }
    }

}
