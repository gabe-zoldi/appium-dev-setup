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
public enum BrowserOS {

    // this list was created from saucelabs compiled list of OSes they support
    WINDOWS     ("windows",     "any"),
    WINDOWS_XP  ("windows xp",  "whistler"),
    WINDOWS_7   ("windows 7",   "blackcomb"),
    WINDOWS_8   ("windows 8",   "jupiter"),
    WINDOWS_8_1 ("windows 8.1", "blue"),

    MAC           ("mac",       "any"),
    MAC_OS_X_10_6 ("os x 10.6", "snow leopard"),
    MAC_OS_X_10_8 ("os x 10.8", "mountain lion"),
    MAC_OS_X_10_9 ("os x 10.9", "mavericks"),

    LINUX ("linux", "any");

    private String name;
    private String codename;

    /**
     * A mapping between the platform and its corresponding OS to facilitate lookup by code.
     */
    private static Map<String, BrowserOS> nameToOSMapping;

    private BrowserOS(String name, String codename) {
        this.name = name;
        this.codename = codename;
    }

    public static BrowserOS getBrowserOS(String name) {
        if (nameToOSMapping == null) {
            initMapping();
        }
        return nameToOSMapping.get( name.toLowerCase() );
    }

    private String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    private static void initMapping() {
        nameToOSMapping = new HashMap<String, BrowserOS>();
        for (BrowserOS os : values()) {
            nameToOSMapping.put(os.name, os);
        }
    }

    public String getName() {
        return name;
    }

    public String getCodename() {
        return codename;
    }

    public static boolean contains(String name) {
        for (BrowserOS osv : BrowserOS.values()) {
            if (osv.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<BrowserOS> VALUES = Collections.unmodifiableList( Arrays.asList( values() ) );
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).getName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{name='").append(name).append('\'');
        sb.append(", codename='").append(codename).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static void main(String [] args) {
        BrowserOS os = BrowserOS.WINDOWS;
        System.out.println(os);
        System.out.println("platform=" + os.getName());
        System.out.println("codename=" + os.getCodename());
        System.out.println("toString=" + os.toString());
        System.out.println("contains(true)=" + BrowserOS.contains(BrowserOS.WINDOWS_XP.getName()));
        System.out.println("contains(false)=" + BrowserOS.contains("xxx"));

        System.out.println("getBrowser()=" + BrowserOS.getBrowserOS("Windows"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(BrowserOS.getRandom());
    }

}
