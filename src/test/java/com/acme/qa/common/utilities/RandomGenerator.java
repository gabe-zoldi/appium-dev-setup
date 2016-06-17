/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities;

import java.util.Random;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class RandomGenerator {
    /**
     * Utility method to create random string 8 characters long
     *
     * @return random string of 8 characters in length
     */
    public static String getRandomString() {
        return getRandomString(8);
    }

    /**
     * Utility method to create the random string of a specified length
     *
     * @param 	strLen how long to generate
     * @return 	the random string
     */
    public static String getRandomString(int strLen) {
        StringBuffer sb = new StringBuffer();
        for (int x = 0; x < strLen; x++)
            sb.append( (char)( (int)(Math.random() * 26) + 97 ) );
        return sb.toString();
    }

    /**
     * Random street address such as '10214 108 AVE NE' or '22345 S 104 PL'
     *
     * @return street address
     */
    public static String getRandomStreetAddress() {
        StringBuilder address = new StringBuilder();
        Random random = new Random();

        String streetTypes[] = { "AVE", "CIR", "CT", "PL", "ST", "WAY" };
        String compass[]     = { "N", "W", "S", "E", "NE", "NW", "SE", "SW" };
        String preCompass;
        String postCompass;

        if ( random.nextBoolean() ) {
            preCompass = compass[ random.nextInt(compass.length) ] + " ";
            postCompass = "";
        }
        else {
            preCompass = "";
            postCompass = " " + compass[ random.nextInt(compass.length) ];
        }

        address.append( random.nextInt(24999) + 1 );
        address.append( " " + preCompass );
        address.append( random.nextInt(999) + 1 );
        address.append( " " );
        address.append( streetTypes[ random.nextInt(streetTypes.length) ] );
        address.append( postCompass );

        return address.toString();
    }

    /**
     * Generates 5-digit ZIP code value between 10001 and 99999
     *
     * @return random zip
     */
    public static int getRandomZip() {
        int zipMin = 10001;
        int zipRange = 89998;
        return zipMin + new Random().nextInt(zipRange);
    }

}