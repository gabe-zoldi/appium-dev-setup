/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.datamodel;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public final class Key implements Cloneable {

    private String accessKey;
    private String created;
    private String status;

    public static class Status {
        public final static String ENABLED  = "Enabled";
        public final static String DISABLED = "Disabled";
    }

    /*
     * constructor
     */
    public Key(final String accessKey) {
        this.accessKey = accessKey;
    }

    public Key(final String accessKey, final String created, final String status) {
        this.accessKey = accessKey;
        this.created = created;
        this.status = status;
    }

    /*
     * getter-setter
     */
    public String getAccessKey() {
        return accessKey;
    }
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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

}