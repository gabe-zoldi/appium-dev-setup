/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.db;

import com.symantec.qa.common.framework.logging.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DBConnection {

    protected static Connection conn = null;
    protected static Statement stmt = null;
    protected static ResultSet rs = null;

    protected void setConnection() {
        Log.info("The class name is : " + this.getClass().getName());

        String connectionString, driver, url, host, port, user, password, database;
        connectionString = driver = url = host = port = user = password = database = "";

        try {
            Class dbConfig = Class.forName( getClass().getName() );

            driver   = (String) dbConfig.getDeclaredMethod( "getDriver"   ).invoke( dbConfig.newInstance() );
            url      = (String) dbConfig.getDeclaredMethod( "getUrl"      ).invoke( dbConfig.newInstance() );
            host     = (String) dbConfig.getDeclaredMethod( "getHost"     ).invoke( dbConfig.newInstance() );
            port     = (String) dbConfig.getDeclaredMethod( "getPort"     ).invoke( dbConfig.newInstance() );
            user     = (String) dbConfig.getDeclaredMethod( "getUser"     ).invoke( dbConfig.newInstance() );
            password = (String) dbConfig.getDeclaredMethod( "getPassword" ).invoke( dbConfig.newInstance() );
            database = (String) dbConfig.getDeclaredMethod( "getName"     ).invoke( dbConfig.newInstance() );

            Class.forName(driver);
            connectionString = url + host + ":" + port + "/" + database;

            Log.info("Connected to database: " + connectionString);
            conn = DriverManager.getConnection(connectionString, user, password);
        }
        catch (Exception e) {
            throw new RuntimeException(
                    String.format( "Unable to create connection: %s", connectionString ), e
            );
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static Statement getStatement() {
        return stmt;
    }

    protected ResultSet executeResultSet(String query) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not execute query:" + query, e);
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                throw new RuntimeException("Could not close database statement or connection.");
            }
        }

        return rs;
    }

    protected ArrayList<Map<String, String>> executeDDL(String query) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            list = DBUtils.getListMapFromResultSet(rs);
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not execute query:" + query, e);
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                throw new RuntimeException("Could not close database statement or connection.");
            }
        }

        return list;
    }

    @SuppressWarnings("finally")
    protected int executeDML(String query) {
        int numRows = 0;
        setConnection();

        try {
            stmt = conn.createStatement();
            numRows = stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not execute SQL statement: " + query, e);
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                throw new RuntimeException("Could not close database statement or connection.");
            }
        }

        return numRows;
    }

}