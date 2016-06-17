/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.framework.exception;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class ConfigurationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/***
     * Constructor to print the configuration runtime exception
     * @param message of exception
     */
    public ConfigurationRuntimeException(String message) {
        super(message);
    }

}
