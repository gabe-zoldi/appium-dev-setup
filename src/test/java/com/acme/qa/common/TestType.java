/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class TestType {

    public static final String UNIT          = "unit";              // Unit Testing
    public static final String INTEGRATION   = "integration";       // Integration Testing
    public static final String API           = "api";               // API Testing
    public static final String FUNCTIONAL    = "functional";        // Functional Testing
    public static final String SYSTEM        = "system";            // System Testing
    public static final String END_TO_END    = "end-to-end";        // End-to-End Testing

    public static final String SANITY        = "sanity";            // Sanity Testing
    public static final String REGRESSION    = "regression";        // Regression Testing
    public static final String ACCEPTANCE    = "acceptance";        // Acceptance Testing
    public static final String AUTOMATION    = "automation";        // Automation Testing

    public static final String BUILD         = "build";             // Build Verification Testing
    public static final String SMOKE         = "smoke";             // Smoke Testing
    public static final String SERVICE       = "service";           // Service Layer Testing (J2EE, JMS, Spring, .NET Framework)
    public static final String WEB_SERVICES  = "ws";                // Web Services Testing (SOAP, RESTFUL API)

    public static final String WORKFLOW      = "workflow";          // Workflow Testing
    public static final String USE_CASE      = "use-case";          // Use Case Verification

    public static final String LOAD          = "load";              // Load Testing
    public static final String STRESS        = "stress";            // Stress Testing
    public static final String PERFORMANCE   = "performance";       // Performance Testing

    public static final String USABILITY     = "usability";         // Usability Testing
    public static final String INSTALL       = "install";           // Install / Uninstall Testing
    public static final String RECOVERY      = "recovery";          // Recovery Testing
    public static final String SECURITY      = "security";          // Security Testing
    public static final String COMPATIBILITY = "compatibility";     // Compatibility Testing
    public static final String DATABASE      = "database";          // Database Testing
    public static final String BIG_DATA      = "big-data";          // Hadoop NoSQL Testing

    public static final String POC           = "prototype";         // Protyping (Proof Of Concept)
    public static final String ALPHA         = "alpha";             // Alpha Testing
    public static final String BETA          = "beta";              // Beta Testing
    public static final String RC1           = "rc1";               // Release Candidate 1 Verification
    public static final String RC2           = "rc2";               // Release Candidate 2 Verification
    public static final String RC3           = "rc3";               // Release Candidate 3 Verification
    public static final String RTM           = "rtm";               // Release To Manufacturing Verification

    public static final String BLACKBOX      = "blackbox";          // Blackbox Testing
    public static final String WHITEBOX      = "whitebox";          // Whitebox Testing

}