/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.type.browser;

import java.util.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public enum BrowserType {

    // browsers types
    IE      ("ie"),
    FIREFOX ("firefox"),
    CHROME  ("chrome"),
    SAFARI  ("safari"),
    OPERA   ("opera"),

    // device types
    ANDROID ("android"),
    IPHONE  ("iPhone"),
    IPAD    ("iPad");

    private String target;

    /**
     * A mapping between the target browser to construct the DesiredCapabilities and its corresponding BrowserType.
     */
    private static Map<String, BrowserType> methodToBrowserMapping;

    public static int _32_BIT = 32;
    public static int _64_bit = 64;

    private BrowserType(String target) {
        this.target = target;
    }

    public static BrowserType getBrowserType(String target) {
        if (methodToBrowserMapping == null) {
            initMapping();
        }
        return methodToBrowserMapping.get(target);
    }

    private static void initMapping() {
        methodToBrowserMapping = new HashMap<String, BrowserType>();
        for (BrowserType type : values()) {
            methodToBrowserMapping.put(type.target, type);
        }
    }

    public String getTarget() {
        return target;
    }

    public static boolean contains(BrowserType type) {
        for (BrowserType browser : BrowserType.values()) {
            if (browser == type) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(String target) {
        for (BrowserType browser : BrowserType.values()) {
            if (browser.getTarget().equals(target)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<BrowserType> VALUES = Collections.unmodifiableList( Arrays.asList( values() ) );
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static BrowserType getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{target='").append(target).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String [] args) {
        BrowserType type = BrowserType.CHROME;
        System.out.println(type);
        System.out.println("target=" + type.getTarget());
        System.out.println("toString=" + type.toString());
        System.out.println("contains(true)=" + BrowserType.contains(BrowserType.FIREFOX));
        System.out.println("containsByTarget(true)=" + BrowserType.contains(BrowserType.ANDROID.getTarget()));
        System.out.println("contains(false)=" + BrowserType.contains("browser"));
        System.out.println("getBrowserType(valid)=" + BrowserType.getBrowserType("chrome"));
        System.out.println("getBrowserType(invalid)=" + BrowserType.getBrowserType("xxxxxx"));
        System.out.println("equals(valid)=" + BrowserType.getBrowserType( "chrome" ).equals( BrowserType.CHROME ));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(BrowserType.getRandom());
    }

}
