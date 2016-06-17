/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.testng;

import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import au.com.bytecode.opencsv.*;
import com.symantec.qa.common.framework.logging.Log;
import org.testng.annotations.DataProvider;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DataProviderImpl {

    @DataProvider(name="csvData")
    public static Object[][] getCSVData(Method m) throws Exception {
        // load csv file
        String fileName = "data\\" + m.getName() + ".csv";
        return parseCsvToMap(fileName);
    }

    /***
     * Parses a CSV file into a map of data for testing
     *
     * @param  fileName - name of the csv file to parse
     * @return Two dimensional object array with the map in it
     * @throws Exception generic
     */
    public static Object[][] parseCsvToMap(String fileName) throws Exception {
        File csvFile = new File(fileName);

        if (!csvFile.exists()) {
            String errorMessage = "CSV data file [" + fileName + "] not found.\r\n";
            Log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        // load file into hash table
        String[] inputLine;
        CSVReader reader = new CSVReader(new FileReader(fileName));

        // load first file into hash table
        boolean firstLine = true;
        String[] columnNames = null;
        List<HashMap<String, String>> maps = new ArrayList<HashMap<String, String>>();

        while ( ( inputLine = reader.readNext() ) != null ) {
            if (firstLine) {
                // read column names
                columnNames = inputLine;
                firstLine = false;
            }
            else {
                // put data into a hash map
                HashMap<String, String> csvMap = new HashMap<String, String>();
                int counter = 0;

                for (String column : columnNames) {
                    String value;

                    if (counter  == inputLine.length) {
                        value = "";
                    }
                    else {
                        value = inputLine[counter];

                        if (value.contains("{") && value.contains("}") ) {
                            int start = value.indexOf("{");
                            int end = value.indexOf("}");
                            value = (String)ReflectKeyword(value.substring(start + 1, end));
                        }
                    }

                    csvMap.put(column, value);
                    counter++;
                }

                // add hash map to list
                maps.add(csvMap);
            }
        }

        // Place maps into an array to return to TestNG
        Object[][] returnArrays = new Object[maps.size()][1];
        for (int i = 0; i < maps.size(); i++)
            returnArrays[i][0] = maps.get(i);

        return returnArrays;
    }

    /***
     * Reflects the class and method passed into it against the Keywords project
     * to invoke the methods for use in csv files.
     *
     * @param  value  - Class.method format of the method to invoke
     * @return Object - containing the return value of the invoked method.
     */
    public static Object ReflectKeyword(String value) {
        String className = null;
        String methodName = null;
        Object returnValue = null;

        className = value.substring(0, value.lastIndexOf("."));
        methodName = value.substring(value.lastIndexOf(".") + 1, value.length());

        Log.info("Class = " + className);
        Log.info("Method = " + methodName);

        Class<?> reflectedClass = null;

        try {
            reflectedClass = Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            Log.error("Cannot find class [" + className + "] for reflection - " + e.getMessage());
        }
        catch (Exception e) {
            Log.error("Error in loading class [" + className + "] for reflection - " + e.getMessage());
        }

        // if null no point in executing subsequent code
        if (reflectedClass == null ) return null;

        try {
            Method method = reflectedClass.getMethod(methodName);
            returnValue = method.invoke(reflectedClass);
        }
        catch (NoSuchMethodException e) {
            Log.error("Cannot find method [" + methodName + "] in class [" + className + "] for reflection - " + e.getMessage());
        }
        catch (Exception e) {
            Log.error("Error in invoking method [" + className + "] for reflection - " + e.getMessage());
        }

        return returnValue;
    }

}