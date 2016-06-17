/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.framework;

import com.symantec.qa.common.framework.exception.ConfigurationRuntimeException;
import com.symantec.qa.common.utilities.db.DatabaseType;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class ConfigurationManager {

    private static Properties properties = null;

    public class PropKey {
        final static String DRIVER   = ".db_driver";
        final static String URL      = ".db_url";
        final static String HOST     = ".db_host";
        final static String PORT     = ".db_port";
        final static String USER     = ".db_user";
        final static String PASSWORD = ".db_password";
        final static String DATABASE = ".db_database";
    }

    private ConfigurationManager() {

    }

    private static void initialize() {
        properties = new Properties();
        FileInputStream file;

        try {
            file = new FileInputStream( PropertyManager.CONFIG_PROP );
            properties.load( file );
        }
        catch (Exception e) {
            throw new RuntimeException( e );
        }

        // if '-DportalServer=x.x.x.x' exists from command-line, replaces ocpServer property
        final String APP_SERVER = "portalServer";
        String overrideWith = System.getProperty( APP_SERVER );

        if ( overrideWith != null && !overrideWith.isEmpty() )
            properties.setProperty( APP_SERVER, overrideWith );
    }

    /**
     * Method to get machine name for a machine type.
     * @param machineType type of machine
     * @return machine name based on machine type
     */
    public static String getMachineName(MachineType machineType) {
        return getProperty( machineType.getKey() );
    }

    /**
     * Method to get a configuration property identified by a given key.
     * @param key key token to lookup
     * @return A configuration property value based on the key supplied
     */
    public static String getProperty(String key) {
        if ( properties == null )
            initialize();
        if ( !properties.containsKey( key ) )
            throw new ConfigurationRuntimeException("Key [" + key + "] not found in property file [" + PropertyManager.CONFIG_PROP + "].");
        return properties.getProperty(key);
    }


    /**
     * Method to get current configured HTTP Protocol property
     * @return A string either https:// or http:// indicating secure or non-secure respectively
     */
    public static String getHttpProtocol() {
        return Boolean.parseBoolean( getProperty("useHttps") ) ? "https://" : "http://";
    }


    /**
     * Method to get database driver
     * @param dbType DatabaseType
     * @return database driver
     */
    public static String getDatabaseDriver(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.DRIVER );
    }

    /**
     * Method to get database url
     * @param dbType DatabaseType
     * @return database url
     */
    public static String getDatabaseUrl(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.URL );
    }

    /**
     * Method to get database host
     * @param dbType DatabaseType
     * @return database host
     */
    public static String getDatabaseHost(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.HOST );
    }

    /**
     * Method to get database port
     * @param dbType DatabaseType
     * @return database port
     */
    public static String getDatabasePort(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.PORT );
    }

    /**
     * Method to get database user
     * @param dbType DatabaseType
     * @return database user
     */
    public static String getDatabaseUser(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.USER );
    }

    /**
     * Method to get database password
     * @param dbType DatabaseType
     * @return database password
     */
    public static String getDatabasePassword(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.PASSWORD );
    }

    /**
     * Method to get database, e.g., qa, stage, prod
     * @param dbType DatabaseType
     * @return database name
     */
    public static String getDatabaseName(DatabaseType dbType) {
        return getProperty( dbType.getDatabaseType() + PropKey.DATABASE );
    }

}
