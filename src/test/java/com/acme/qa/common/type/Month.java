/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.type;

import java.util.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public enum Month {

    M01_JAN ("01", "Jan", "January"),
    M02_FEB ("02", "Feb", "February"),
    M03_MAR ("03", "Mar", "March"),
    M04_APR ("04", "Apr", "April"),
    M05_MAY ("05", "May", "May"),
    M06_JUN ("06", "Jun", "June"),
    M07_JUL ("07", "Jul", "July"),
    M08_AUG ("08", "Aug", "August"),
    M09_SEP ("09", "Sep", "September"),
    M10_OCT ("10", "Oct", "October"),
    M11_NOV ("11", "Nov", "November"),
    M12_DEC ("12", "Dec", "December"),
    DEFAULT ("Month", "Month", "Month");

    private String value;
    private String abbrev;
    private String text;

    /**
     * A mapping between the month number and its corresponding Month to facilitate lookup by code.
     */
    private static Map<String, Month> numberToMonthNameMapping;

    private Month(String value, String abbrev, String text) {
        this.value = value;
        this.abbrev = abbrev;
        this.text = text;
    }

    public static Month getMonth(String value) {
        if (numberToMonthNameMapping == null) {
            initMapping();
        }
        return numberToMonthNameMapping.get(value);
    }

    private static void initMapping() {
        numberToMonthNameMapping = new HashMap<String, Month>();
        for (Month m : values()) {
            numberToMonthNameMapping.put(m.value, m);
        }
    }

    public String getValue() {
        return value;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getText() {
        return text;
    }

    public static boolean contains(String value) {
        for (Month m : Month.values()) {
            if (m.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<Month> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).getText();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{value='").append(value).append('\'');
        sb.append(", abbrev='").append(abbrev).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String[] args) {
        Month m = Month.M01_JAN;
        System.out.println(m);
        System.out.println("value=" + m.getValue());
        System.out.println("abbrev=" + m.getAbbrev());
        System.out.println("text=" + m.getText());
        System.out.println("toString=" + m.toString());
        System.out.println("contains(true)=" + Month.contains(Month.M05_MAY.getValue()));
        System.out.println("contains(false)=" + Month.contains("browser"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(Month.getRandom());
    }

}