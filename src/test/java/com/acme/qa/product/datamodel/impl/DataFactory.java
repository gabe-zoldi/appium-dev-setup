/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.datamodel.impl;

import com.symantec.qa.rover.App;
import com.symantec.qa.rover.datamodel.User;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DataFactory {
   /*
    * This random data generator is being re-purposed from generating random data which is
    * useful when developing applications that require a lot of sample data, to behave more
    * specific to Portal; should refactor to support both to also accept any VO types.
   */
    public static User createTestUser(Map<User.Status, Object> override) {
        User user = createRandomTestUser();
        return user;
    }

    public static User createRandomTestUser() {
        return createTestUser(App.Test.EMAIL, App.Test.EMAIL_PASSWORD);
    }

    public static User createTestUser(String prefixName, String password) {
        //return User.createFake(prefixName, password);
        return null;
    }

}