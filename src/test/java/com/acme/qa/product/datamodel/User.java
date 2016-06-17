/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.datamodel;

import com.symantec.qa.common.utilities.Utils;
import com.symantec.qa.rover.App;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * Created by gzold7195817 on 10/25/15.
 */
public final class User implements Cloneable {

    public static class Status {
        public final static String ENABLED  = "Enabled";
        public final static String DISABLED = "Disabled";
    }

    // user attributes
    private String email;
    private String name;
    private DateTime created;
    private DateTime last_modified;
    private String status;

    /*
     * constructor
     */
    public User(
            final String email,
            final String name,
            final DateTime created,
            final DateTime last_modified,
            final String status )
    {
        this.email         = email;
        this.name          = name;
        this.created       = created;
        this.last_modified = last_modified;
        this.status        = status;
    }

    /*
     * getter-setter
     */
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public DateTime getCreated() {
        return created;
    }
    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getLastModified() {
        return last_modified;
    }
    public void setLastModified(DateTime last_modified) {
        this.last_modified = last_modified;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public static User createRandom(final String label) {
        String name = label + Utils.createUniqueRandomNumber();
        String email = name + App.Test.EMAIL_DOMAIN;
        DateTime now = DateTime.now();

        return new User(email, name, now, now, Status.ENABLED);
    }


    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException("Object cannot be cloned: " + e.getStackTrace());
        }
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static void main(String [] args) {
        User user1 = new User(
                "user1@acme.com",
                "user1",
                DateTime.now(),
                DateTime.now(),
                Status.ENABLED
        );

        User user2 = (User) user1.clone();
        user2.setEmail("user2@acme.com");
        user2.setName("user2");
        user2.setCreated(DateTime.now());
        user2.setLastModified(DateTime.now());
        user2.setStatus(Status.DISABLED);

        System.out.println( user1.toString() );
        System.out.println( user2.toString() );
    }

}