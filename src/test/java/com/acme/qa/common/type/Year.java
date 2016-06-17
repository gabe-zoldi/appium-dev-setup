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
public enum Year {

    YR_1999 ("1999", "1999"),
    YR_2000 ("2000", "2000"),
    YR_2001 ("2001", "2001"),
    YR_2002 ("2002", "2002"),
    YR_2003 ("2003", "2003"),
    YR_2004 ("2004", "2004"),
    YR_2005 ("2005", "2005"),
    YR_2006 ("2006", "2006"),
    YR_2007 ("2007", "2007"),
    YR_2008 ("2008", "2008"),
    YR_2009 ("2009", "2009"),
    YR_2010 ("2010", "2010"),
    YR_2011 ("2011", "2011"),
    YR_2012 ("2012", "2012"),
    YR_2013 ("2013", "2013"),
    YR_2015 ("2015", "2015"),
    YR_2016 ("2016", "2016"),
    YR_2017 ("2017", "2017"),
    YR_2018 ("2018", "2018"),
    YR_2019 ("2019", "2019"),
    YR_2020 ("2020", "2020"),
    YR_2021 ("2021", "2021"),
    YR_2022 ("2022", "2022"),
    YR_2023 ("2023", "2023"),
    YR_2024 ("2024", "2024"),
    YR_2025 ("2025", "2025"),
    YR_2026 ("2026", "2026"),
    YR_2027 ("2027", "2027"),
    YR_2028 ("2028", "2028"),
    YR_2029 ("2029", "2029"),
    YR_2030 ("2030", "2030"),
    DEFAULT ("Year", "Year");

    private String value;
    private String text;

    /**
     * A mapping between the text and its corresponding Year to facilitate lookup by value.
     */
    private static Map<String, Year> valueToMonthTextMapping;

    private Year(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static Year getYear(String value) {
        if (valueToMonthTextMapping == null) {
            initMapping();
        }
        return valueToMonthTextMapping.get(value);
    }

    private static void initMapping() {
        valueToMonthTextMapping = new HashMap<String, Year>();
        for (Year y: values()) {
            valueToMonthTextMapping.put(y.value, y);
        }
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static boolean contains(String value) {
        for (Year y : Year.values()) {
            if (y.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<Year> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
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
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String[] args) {
        Year yr = Year.YR_2018;
        System.out.println(yr);
        System.out.println("value=" + yr.getValue());
        System.out.println("text=" + yr.getText());
        System.out.println("toString=" + yr.toString());
        System.out.println("contains(true)=" + Year.contains( Year.YR_2025.getText() ));
        System.out.println("contains(false)=" + Year.contains("browser"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(Year.getRandom());
    }

}