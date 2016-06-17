/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities;

import com.symantec.qa.common.framework.logging.Log;
import java.lang.reflect.Method;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class ReflectionUtility {

    public static Object invokeMethod(String callingClass, String methodName) {
        Class<?> reflectedClass = null;
        Object returnValue = null;

        try {
            reflectedClass = Class.forName(callingClass);

            // if null no point in executing subsequent code
            if (reflectedClass == null ) return null;

            Method method = reflectedClass.getMethod(methodName);
            Object retobj = reflectedClass.newInstance();
            returnValue = method.invoke(retobj);
        }
        catch (ClassNotFoundException e) {
            Log.error("Cannot find class [" + callingClass + "] for reflection - " + e.getMessage());
        }
        catch (NoSuchMethodException e) {
            Log.error("Cannot find method [" + methodName + "] in class [" + callingClass + "] for reflection - " + e.getMessage());
        }
        catch (Exception e) {
            Log.error("Error in loading [" + callingClass + "] for reflection - " + e.getMessage());
        }

        return returnValue;
    }

}