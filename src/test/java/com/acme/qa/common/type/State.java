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
public enum State {

    AK ("AK", "Alaska"),
    AL ("AL", "Alabama"),
    AR ("AR", "Arkansas"),
    AS ("AS", "American Samoa"),
    AZ ("AZ", "Arizona"),
    CA ("CA", "California"),
    CO ("CO", "Colorado"),
    CT ("CT", "Connecticut"),
    DC ("DC", "District of Columbia"),
    DE ("DE", "Delaware"),
    FL ("FL", "Florida"),
    FM ("FM", "Federated States of Micronesia"),
    GA ("GA", "Georgia"),
    GU ("GU", "Guam"),
    HI ("HI", "Hawaii"),
    IA ("IA", "Iowa"),
    ID ("ID", "Idaho"),
    IL ("IL", "Illinois"),
    IN ("IN", "Indiana"),
    KS ("KS", "Kansas"),
    KY ("KY", "Kentucky"),
    LS ("LS", "Louisiana"),
    MA ("MA", "Massachusetts"),
    MD ("MD", "Maryland"),
    ME ("ME", "Maine"),
    MH ("MH", "Marshall Islands"),
    MI ("MI", "Michigan"),
    MN ("MN", "Minnesota"),
    MO ("MO", "Missouri"),
    MP ("MP", "Northern Mariana Islands"),
    MS ("MS", "Mississippi"),
    MT ("MT", "Montana"),
    NC ("NC", "North Carolina"),
    ND ("ND", "North Dakota"),
    NE ("NE", "Nebraska"),
    NH ("NH", "New Hampshire"),
    NJ ("NJ", "New Jersey"),
    NM ("NM", "New Mexico"),
    NV ("NV", "Nevada"),
    NY ("NY", "New York"),
    OH ("OH", "Ohio"),
    OK ("OK", "Oklahoma"),
    OR ("OR", "Oregon"),
    PA ("PA", "Pennsylvania"),
    PR ("PR", "Puerto Rico"),
    PW ("PW", "Palau"),
    RI ("RI", "Rhode Island"),
    SC ("SC", "South Carolina"),
    SD ("SD", "South Dakota"),
    TN ("TN", "Tennessee"),
    TX ("TX", "Texas"),
    UT ("UT", "Utah"),
    VA ("VA", "Virginia"),
    VI ("VI", "Virgin Islands"),
    VT ("VT", "Vermont"),
    WA ("WA", "Washington"),
    WI ("WI", "Wisconsin"),
    WV ("WV", "West Virginia"),
    WY ("WY", "Wyoming"),
    DEFAULT ("State", "State");

    private String value;
    private String text;

    /**
     * A mapping between the state abbrev and its corresponding State to facilitate lookup by code.
     */
    private static Map<String, State> abbrevToStateNameMapping;

    private State(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static State getState(String value) {
        if (abbrevToStateNameMapping == null) {
            initMapping();
        }
        return abbrevToStateNameMapping.get(value);
    }

    private static void initMapping() {
        abbrevToStateNameMapping = new HashMap<String, State>();
        for (State s : values()) {
            abbrevToStateNameMapping.put(s.value, s);
        }
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static boolean contains(String value) {
        for (State s : State.values()) {
            if (s.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<State> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).getText();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{value=").append(value);
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String [] args) {
        State e = State.CA;
        System.out.println(e);
        System.out.println("value=" + e.getValue());
        System.out.println("text=" + e.getText());
        System.out.println("toString=" + e.toString());
        System.out.println("contains(true)=" + State.contains(State.HI.getValue()));
        System.out.println("contains(false)=" + State.contains("browser"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(State.getRandom());
    }

}