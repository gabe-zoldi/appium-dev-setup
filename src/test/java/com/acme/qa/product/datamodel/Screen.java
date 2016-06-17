/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.datamodel;

import org.openqa.selenium.Dimension;
import java.util.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public enum Screen {

    //DESKTOP ("desktop", "Full", 1920, 1080, new Dimension(1920, 1080)),
    //DESKTOP ("desktop", "Full", 1440, 900, new Dimension(1440, 900)),
    DESKTOP ("desktop", "Full", 1280, 800, new Dimension(1280, 800)),
    MOBILE  ("mobile",  "Half", 1175, 1080, new Dimension(1175, 1080));

    private String type;
    private String size;
    private int width;
    private int height;
    private Dimension dimension;

    /**
     * A mapping between the screen type and its corresponding Screen to facilitate lookup by size.
     */
    private static Map<String, Screen> sizeToScreenDimensionMapping;

    Screen(String type, String size, int width, int height, Dimension dimension) {
        this.type = type;
        this.size = size;
        this.width = width;
        this.height = height;
        this.dimension = dimension;
    }

    public static Screen getSize(String size) {
        if (sizeToScreenDimensionMapping == null) {
            initMapping();
        }
        return sizeToScreenDimensionMapping.get(size);
    }

    private static void initMapping() {
        sizeToScreenDimensionMapping = new HashMap<String, Screen>();
        for (Screen s : values()) {
            sizeToScreenDimensionMapping.put(s.size, s);
        }
    }

    public String getType()         { return type; }
    public String getSize()         { return size; }
    public int getWidth()           { return width; }
    public int getHeight()          { return height; }
    public Dimension getDimension() { return dimension; }

    public boolean isDesktop() {
        if ( type.equals( Screen.DESKTOP.getType() ) ) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isMobile() {
        if ( type.equals( Screen.MOBILE.getType() ) ) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean contains(String size) {
        for (Screen s : Screen.values()) {
            if (s.size.equals(size)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<Screen> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).getSize();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{type='").append(type).append('\'');
        sb.append("{size='").append(size).append('\'');
        sb.append(", width='").append(width).append('\'');
        sb.append(", height='").append(height).append('\'');
        sb.append(", dimension='").append(dimension.toString()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        Screen s = Screen.DESKTOP;
        System.out.println(s);
        System.out.println("type=" + s.getType());
        System.out.println("size=" + s.getSize());
        System.out.println("width=" + s.getWidth());
        System.out.println("height=" + s.getHeight());
        System.out.println("dimension=" + s.getDimension());
        System.out.println("dimension(width)=" + s.getDimension().getWidth());
        System.out.println("dimension(height)=" + s.getDimension().getHeight());
        System.out.println("toString=" + s.toString());
        System.out.println("contains(true)=" + Screen.contains(Screen.MOBILE.getSize()));
        System.out.println("contains(false)=" + Screen.contains("foobar"));
        System.out.println("isDesktop(true)=" + s.isDesktop());
        System.out.println("isMobile(false)=" + s.isMobile());

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(Screen.getRandom());
    }

}