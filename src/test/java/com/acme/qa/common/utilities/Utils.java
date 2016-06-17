/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities;

import java.util.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public final class Utils {

    public static long generateUniqueRandomNumber() {
        return getUniqueRandomNumber();
    }

    public static String createUniqueRandomNumber() {
        return String.valueOf(getUniqueRandomNumber());
    }

    /**
     * Generate unique random number using Calendar millis
     *
     * @return the long
     */
    private static long getUniqueRandomNumber() {
        // generate random uniqueid based on current timestamp
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime( new Date() );
        return gc.getTimeInMillis();
    }

    public static int getRandomWithinRange(int min, int max) {
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        return new Random().nextInt( (max - min) + 1 ) + min;
    }

    /*
     * try not to sprinkle too much of this throughout the testng code and
     * use for debugging purposes only; if you must use it for sake of time
     * and don't want to deal with timing issue immediately, please add
     * // TO DO comment to refactor testng code later
     */
    public static void delay(long millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void delay() {
        final int DEFAULT = 2000;  // 2 secs
        delay(DEFAULT);
    }

    public static Collection findMissingItemsInList(Collection expected, Collection actual) {
        return removeRightSet(expected, actual);
    }

    public static Collection findUexpectedItemsInList(Collection expected, Collection actual) {
        return removeRightSet(actual, expected);
    }

    public static Collection removeRightSet(Collection left, Collection right) {
        Collection set = new ArrayList( left );
        set.removeAll( right );
        return set;
    }

}