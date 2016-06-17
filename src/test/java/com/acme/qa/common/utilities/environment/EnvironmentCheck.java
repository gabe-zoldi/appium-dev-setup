/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.environment;

import com.symantec.qa.common.framework.logging.Log;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class EnvironmentCheck {

    /**
     * Verifies URL returns a response
     * @param 	url check if url returns 200
     * @return  bool whether the ping succeeded or failed
     */
    public static boolean verifyURL(String url) {

        boolean connectionValid = false;
        URL testURL = null;
        URLConnection conn = null;
        InputStream input = null;

        try {
            testURL = new URL( url );

            // TODO: add to check 200 response code
            // TODO: look at integrating http://developer.eclipsesource.com/restfuse/
            if ( testURL != null ) {
                conn = testURL.openConnection();
                input = conn.getInputStream();
                input.close();
                connectionValid = true;
            }
        }
        catch (Exception e) {
            Log.error( "Exception verifying URL: " + url + "\n" + e.getMessage() );
        }

        return connectionValid;
    }

}