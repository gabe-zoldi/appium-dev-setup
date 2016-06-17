/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.fitnesse;

import com.symantec.qa.common.framework.FrameworkObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLConnection;
import java.net.URL;
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class FitnesseRunner extends FrameworkObject {

    private StringBuilder resultMessage;
    private boolean testResult = true;

    public boolean executeSuite(String host, String port, String suiteName) {
        // TODO: add error checking for http: and https:
        try {
            String address = String.format( "%s:%s/%s?suite&format=xml", host, port, suiteName );
            resultMessage = new StringBuilder();

            String xmlResult = openConnection(address);
            Document docResults = parseResults(xmlResult);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            int finalRight      = Integer.parseInt( xpath.evaluate( "//finalCounts/right", docResults ) );
            int finalWrong      = Integer.parseInt( xpath.evaluate( "//finalCounts/wrong", docResults ) );
            int finalIgnores    = Integer.parseInt( xpath.evaluate( "//finalCounts/ignores", docResults ) );
            int finalExceptions = Integer.parseInt( xpath.evaluate( "//finalCounts/exceptions", docResults ) );

            resultMessage.append(
                    String.format(
                            "%s : Right - %d, Wrong - %d, Ignored - %d, Exceptions - %d\r\n",
                            suiteName,
                            finalRight,
                            finalWrong,
                            finalIgnores,
                            finalExceptions
                    )
            );

            if ((finalWrong > 0) || (finalIgnores > 0) || (finalExceptions > 0)) {
                testResult = false;
                NodeList resultNodes = (NodeList) xpath.evaluate("//testResults/result", docResults, XPathConstants.NODESET);

                for (int i = 0; i < resultNodes.getLength(); i++) {
                    int right      = Integer.parseInt( xpath.evaluate( "counts/right", resultNodes.item(i) ) );
                    int wrong      = Integer.parseInt( xpath.evaluate( "counts/wrong", resultNodes.item(i) ) );
                    int ignores    = Integer.parseInt( xpath.evaluate( "counts/ignores", resultNodes.item(i) ) );
                    int exceptions = Integer.parseInt( xpath.evaluate( "counts/exceptions", resultNodes.item(i) ) );

                    String testName = (String) xpath.evaluate("relativePageName", resultNodes.item(i));

                    if ((wrong > 0) || (ignores > 0) || (exceptions > 0)) {
                        resultMessage.append(
                                String.format(
                                        "\t%s : Right - %d, Wrong - %d, Ignored - %d, Exceptions - %d\r\n",
                                        testName,
                                        right,
                                        wrong,
                                        ignores,
                                        exceptions
                                )
                        );
                    }
                }
            }

            return testResult;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean executeTest(String host, String port, String testName) {
        // TODO: add error checking for http: and https:
        try {
            String address = String.format( "%s:%s/%s?testng&format=xml", host, port, testName );
            resultMessage = new StringBuilder();

            String xmlResult = openConnection(address);
            Document docResults = parseResults(xmlResult);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            int right      = Integer.parseInt( xpath.evaluate( "//counts/right", docResults ) );
            int wrong      = Integer.parseInt( xpath.evaluate( "//counts/wrong", docResults ) );
            int ignores    = Integer.parseInt( xpath.evaluate( "//counts/ignores", docResults ) );
            int exceptions = Integer.parseInt( xpath.evaluate( "//counts/exceptions", docResults ) );

            resultMessage.append(
                    String.format(
                            "%s : Right - %d, Wrong - %d, Ignored - %d, Exceptions - %d\r\n",
                            testName,
                            right,
                            wrong,
                            ignores,
                            exceptions
                    )
            );

            if ((wrong > 0) || (ignores > 0) || (exceptions > 0))
                testResult = false;

            return testResult;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String openConnection(String address) {
        try {
            URLConnection urlConn = new URL(address).openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setAllowUserInteraction(false);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty( "Content-Type", "text/xml;charset=UTF-8" );

            BufferedReader input = new BufferedReader(
                    new InputStreamReader( urlConn.getInputStream() )
            );

            StringBuilder xmlReturn = new StringBuilder();
            String result;

            while ( null != ( result = input.readLine() ) )
                xmlReturn.append(result);

            return xmlReturn.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Document parseResults(String xml) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware( true );
        try {
            xml = xml.replaceAll( ">\\s+<", "><" );

            StringReader reader = new StringReader(xml);
            InputSource is = new InputSource(reader);

            return docFactory.newDocumentBuilder().parse(is);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getResultMessage() {
        return resultMessage.toString();
    }

}