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
public enum DeviceVersion {

    IPHONE_4   (101, BrowserType.IPHONE, "iPhone", "4",   BrowserOS.MAC_OS_X_10_6, Orientation.PORTRAIT),
    IPHONE_5_0 (102, BrowserType.IPHONE, "iPhone", "5.0", BrowserOS.MAC_OS_X_10_6, Orientation.PORTRAIT),
    IPHONE_5_1 (103, BrowserType.IPHONE, "iPhone", "5.1", BrowserOS.MAC_OS_X_10_8, Orientation.PORTRAIT),
    IPHONE_6_0 (104, BrowserType.IPHONE, "iPhone", "6.0", BrowserOS.MAC_OS_X_10_8, Orientation.PORTRAIT),
    IPHONE_6_1 (105, BrowserType.IPHONE, "iPhone", "6.1", BrowserOS.MAC_OS_X_10_8, Orientation.PORTRAIT),
    IPHONE_7_0 (106, BrowserType.IPHONE, "iPhone", "7.0", BrowserOS.MAC_OS_X_10_9, Orientation.PORTRAIT),
    IPHONE_7_1 (107, BrowserType.IPHONE, "iPhone", "7.1", BrowserOS.MAC_OS_X_10_9, Orientation.PORTRAIT),

    IPAD_4   (201, BrowserType.IPAD, "iPad", "4",   BrowserOS.MAC_OS_X_10_6, Orientation.PORTRAIT),
    IPAD_5_0 (202, BrowserType.IPAD, "iPad", "5.0", BrowserOS.MAC_OS_X_10_6, Orientation.PORTRAIT),
    IPAD_5_1 (203, BrowserType.IPAD, "iPad", "5.1", BrowserOS.MAC_OS_X_10_8, Orientation.PORTRAIT),
    IPAD_6_0 (204, BrowserType.IPAD, "iPad", "6.0", BrowserOS.MAC_OS_X_10_8, Orientation.PORTRAIT),
    IPAD_6_1 (205, BrowserType.IPAD, "iPad", "6.1", BrowserOS.MAC_OS_X_10_8, Orientation.PORTRAIT),
    IPAD_7_0 (206, BrowserType.IPAD, "iPad", "7.0", BrowserOS.MAC_OS_X_10_9, Orientation.PORTRAIT),
    IPAD_7_1 (207, BrowserType.IPAD, "iPad", "7.1", BrowserOS.MAC_OS_X_10_9, Orientation.PORTRAIT),

    ANDROID_4_3                       (301, BrowserType.ANDROID, "Android",                       "4.3", BrowserOS.LINUX, Orientation.PORTRAIT),
    GOOGLE_NEXUS_7_HD_EUMLATOR_4_3    (302, BrowserType.ANDROID, "Google Nexus 7 HD Emulator",    "4.3", BrowserOS.LINUX, Orientation.PORTRAIT),
    LG_NEXUS_4_EMULATOR_4_3           (303, BrowserType.ANDROID, "LG Nexus 4 Emulator",           "4.3", BrowserOS.LINUX, Orientation.PORTRAIT),
    SAMSUMG_GALAXY_NEXUS_EMULATOR_4_3 (304, BrowserType.ANDROID, "Samsung Galaxy Nexus Emulator", "4.3", BrowserOS.LINUX, Orientation.PORTRAIT),
    SAMSUMG_GALAXY_S3_EMULATOR_4_3    (305, BrowserType.ANDROID, "Samsung Galaxy S3 Emulator",    "4.3", BrowserOS.LINUX, Orientation.PORTRAIT),
    SAMSUMG_GALAXY_S4_EMULATOR_4_3    (306, BrowserType.ANDROID, "Samsung Galaxy S4 Emulator",    "4.3", BrowserOS.LINUX, Orientation.PORTRAIT),
    GOOGLE_NEXUS_7C_EMULATOR_4_3      (307, BrowserType.ANDROID, "Google Nexus 7C Emulator",      "4.3", BrowserOS.LINUX, Orientation.PORTRAIT);

    // adding code: no attributes uniquely identify Device types,
    // instead using attribute composites like name/version/os
    private int code;
    private BrowserType browserType;
    private String name;
    private String version;
    private BrowserOS browserOS;
    private Orientation orientation;

    public enum Orientation {
        PORTRAIT  ("portrait"),
        LANDSCAPE ("landscape");

        private String name;

        private Orientation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum CapabilityType {
        DEVICE_NAME        ("deviceName"),
        DEVICE_ORIENTATION ("device-orientation");

        private String key;

        private CapabilityType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    /**
     * A mapping between the browserType/version and its corresponding Device to facilitate lookup by code.
     */
    private static Map<Integer, DeviceVersion> codeToDeviceVersionMapping;

    private DeviceVersion(int code, BrowserType browserType, String name, String version, BrowserOS browserOS, Orientation orientation) {
        this.code = code;
        this.browserType = browserType;
        this.name = name;
        this.version = version;
        this.browserOS = browserOS;
        this.orientation = orientation;
    }

    public static DeviceVersion getDeviceVersion(int code) {
        if (codeToDeviceVersionMapping == null) {
            initMapping();
        }
        return codeToDeviceVersionMapping.get(code);
    }

    private static void initMapping() {
        codeToDeviceVersionMapping = new HashMap<Integer, DeviceVersion>();
        for (DeviceVersion d : values()) {
            codeToDeviceVersionMapping.put(d.code, d);
        }
    }

    public int getCode() {
        return code;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public BrowserOS getBrowserOS() {
        return browserOS;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{code=").append(code);
        sb.append(", browserType='").append(browserType).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", browserOS='").append(browserOS).append('\'');
        sb.append(", orientation='").append(orientation).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String [] args) {
        DeviceVersion d = DeviceVersion.IPHONE_7_1;
        System.out.println(d);
        System.out.println("code=" + d.getCode());
        System.out.println("browserType.target=" + d.getBrowserType().getTarget());
        System.out.println("name=" + d.getName());
        System.out.println("version=" + d.getVersion());
        System.out.println("browserOS=" + d.getBrowserOS().getName());
        System.out.println("orientation=" + d.getOrientation().getName());
        System.out.println("toString=" + d.toString());
    }

}
