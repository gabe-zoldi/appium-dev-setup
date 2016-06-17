/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.rover.test.services;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.*;
import org.junit.Rule;
import org.junit.runner.RunWith;
import com.eclipsesource.restfuse.Assert;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
@RunWith( HttpJUnitRunner.class )
public class RestAPITest {

    @Rule
    public Destination destination = new Destination( this, "http://restfuse.com" );

    @Context
    private Response response;

    @Context
    private PollState pollState;

    private class TestCallbackResource extends DefaultCallbackResource {
        @Override
        public Response post( Request request ) {
            org.junit.Assert.assertNotNull(request.getBody());
            return super.post( request );
        }
    }

    @HttpTest( method = Method.GET, path = "/" )
    @Poll( times = 3, interval = 2000 )
    public void checkRestfuseOnlineStatus() {
        System.out.println( "Attempt " + pollState.getTimes() );
        System.out.println( pollState.getTimes() + ". Responsecode = " + pollState.getResponse( pollState.getTimes() ).getStatus() );
    }

    @HttpTest( method = Method.GET, path = "/",
               headers = { @Header( name = "Accepted-Language", value = "en-en" ) } )
    public void checkRestfuseOnlineStatusWithHeader() {
        Assert.assertOk( response );
    }

//    @HttpTest( method = Method.GET, path = "/test" )
//    @Callback( port = 9090, path = "/asynchron", resource = TestCallbackResource.class, timeout = 10000 )
//         public void testMethod() {
//
//        Assert.assertAccepted( response );
//    }

    // http://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate
    // http://developer.eclipsesource.com/restfuse
    // http://frisbyjs.com
    // http://restcountries.eu
    // https://code.google.com/p/rest-assured/wiki/Usage
    // http://artoftesting.com/automationTesting/restAPIAutomationGetRequest.html
}
