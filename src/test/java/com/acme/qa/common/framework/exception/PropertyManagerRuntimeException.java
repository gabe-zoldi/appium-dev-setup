/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.framework.exception;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class PropertyManagerRuntimeException extends RuntimeException {
    /***
     * Constructor to print the property manager runtime exception
     * @param message of exception
     */
    public PropertyManagerRuntimeException(String message) {
        super(message);
    }

}
