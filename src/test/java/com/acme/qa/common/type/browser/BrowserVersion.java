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
public enum BrowserVersion {

    IE_6  (106, BrowserType.IE, "6"),
    IE_7  (107, BrowserType.IE, "7"),
    IE_8  (108, BrowserType.IE, "8"),
    IE_9  (109, BrowserType.IE, "9"),
    IE_10 (110, BrowserType.IE, "10"),
    IE_11 (111, BrowserType.IE, "11"),

    FIREFOX_3   (203,  BrowserType.FIREFOX, "3"),
    FIREFOX_3_0 (2030, BrowserType.FIREFOX, "3.0"),
    FIREFOX_3_5 (2035, BrowserType.FIREFOX, "3.5"),
    FIREFOX_3_6 (2036, BrowserType.FIREFOX, "3.6"),
    FIREFOX_4   (204,  BrowserType.FIREFOX, "4"),
    FIREFOX_5   (205,  BrowserType.FIREFOX, "5"),
    FIREFOX_6   (206,  BrowserType.FIREFOX, "6"),
    FIREFOX_7   (207,  BrowserType.FIREFOX, "7"),
    FIREFOX_8   (208,  BrowserType.FIREFOX, "8"),
    FIREFOX_9   (209,  BrowserType.FIREFOX, "9"),
    FIREFOX_10  (210,  BrowserType.FIREFOX, "10"),
    FIREFOX_20  (220,  BrowserType.FIREFOX, "20"),
    FIREFOX_30  (230,  BrowserType.FIREFOX, "30"),
    FIREFOX_40  (240,  BrowserType.FIREFOX, "40"),
    FIREFOX_41  (241,  BrowserType.FIREFOX, "41"),

    CHROME_26   (326, BrowserType.CHROME, "26"),
    CHROME_27   (327, BrowserType.CHROME, "27"),
    CHROME_28   (328, BrowserType.CHROME, "28"),
    CHROME_29   (329, BrowserType.CHROME, "29"),
    CHROME_30   (330, BrowserType.CHROME, "30"),
    CHROME_40   (340, BrowserType.CHROME, "40"),
    CHROME_41   (341, BrowserType.CHROME, "41"),
    CHROME_42   (342, BrowserType.CHROME, "42"),
    CHROME_43   (343, BrowserType.CHROME, "43"),
    CHROME_44   (344, BrowserType.CHROME, "44"),
    CHROME_45   (345, BrowserType.CHROME, "45"),
    CHROME_46   (346, BrowserType.CHROME, "46"),
    CHROME_BETA (390, BrowserType.CHROME, "beta"),

    SAFARI_5 (405, BrowserType.SAFARI, "5"),
    SAFARI_6 (406, BrowserType.SAFARI, "6"),
    SAFARI_7 (407, BrowserType.SAFARI, "7"),

    OPERA_11 (511, BrowserType.OPERA, "11"),
    OPERA_12 (512, BrowserType.OPERA, "12"),
    OPERA_23 (523, BrowserType.OPERA, "23");

    private int code;
    private BrowserType type;
    private String version;

    /**
     * A mapping between the browserType, version and its corresponding BrowserVersion to facilitate lookup by code.
     */
    private static Map<Integer, BrowserVersion> codeToBrowserTypeVersionMapping;

    private BrowserVersion(int code, BrowserType type, String version) {
        this.code = code;
        this.type = type;
        this.version = version;
    }

    public static BrowserVersion getBrowserVersion(int code) {
        if (codeToBrowserTypeVersionMapping == null) {
            initMapping();
        }
        return codeToBrowserTypeVersionMapping.get(code);
    }

    private static void initMapping() {
        codeToBrowserTypeVersionMapping = new HashMap<Integer, BrowserVersion>();
        for (BrowserVersion browser : values()) {
            codeToBrowserTypeVersionMapping.put(browser.code, browser);
        }
    }

    public int getCode() {
        return code;
    }

    public BrowserType getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public static boolean contains(BrowserType type, String version) {
        if (getBrowser(type, version) == null)
            return false;
        return true;
    }

    public static BrowserVersion getBrowser(BrowserType type, String version) {
        for (BrowserVersion bv : BrowserVersion.values()) {
            if (bv.type.equals(type)
                    && bv.version.equals(version))
            {
                return BrowserVersion.getBrowserVersion( bv.getCode() );
            }
        }
        throw new RuntimeException(
                String.format(
                        "[BrowserVersion] Could not find %s with version %s.",
                        type.getTarget().toUpperCase(),
                        version
                )
        );
    }

    /*
     * randomizer
     */
    private static final List<BrowserVersion> VALUES = Collections.unmodifiableList( Arrays.asList( values() ) );
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static BrowserVersion getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{code=").append(code);
        sb.append(", browserType='").append(type).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String [] args) {
        BrowserVersion browser = BrowserVersion.IE_10;
        System.out.println(browser);
        System.out.println("code=" + browser.getCode());
        System.out.println("browserType.target=" + browser.getType().getTarget());
        System.out.println("version=" + browser.getVersion());
        System.out.println("toString=" + browser.toString());
        System.out.println("getBrowser()=" + BrowserVersion.getBrowser(BrowserType.FIREFOX, "30"));
        System.out.println("getBrowser(null)=" + BrowserVersion.getBrowser(BrowserType.FIREFOX, "999"));
        System.out.println("getBrowser(null)=" + BrowserVersion.getBrowser(BrowserType.FIREFOX, ""));
        System.out.println("contains(true)=" + BrowserVersion.contains(BrowserType.FIREFOX, "30"));
        System.out.println("contains(false)=" + BrowserVersion.contains(BrowserType.FIREFOX, "999"));
        System.out.println("contains(false)=" + BrowserVersion.contains(BrowserType.FIREFOX, ""));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(BrowserVersion.getRandom());
    }

}
