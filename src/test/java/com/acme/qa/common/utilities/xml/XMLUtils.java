/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.utilities.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class XMLUtils {
    /**
     * Loads an xml file on disk into a document
     *
     * @param  xmlFileName - name of the file on disk, including path
     * @return Document    - loaded from the specified xml file on disk
     */
    public static Document loadXmlFromFile(String xmlFileName) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);

        try {
            return docFactory.newDocumentBuilder().parse( new File( xmlFileName ) );
        }
        catch (ParserConfigurationException e) {
            throw new RuntimeException("Error setting up UI xml page model parser -- " + e.getMessage());
        }
        catch (SAXException e) {
            throw new RuntimeException("Error parsing UI xml page model file [" + xmlFileName + "]  -- " + e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException("Error opening or reading UI xml page model file [" + xmlFileName + "]  -- " + e.getMessage());
        }
    }

    /***
     * Returns the string value of evaluated xpath
     *
     * @param  document    - XML Document to check
     * @param  xpathString - xpath to evaluate
     * @return String evaluation of the xpath
     */
    public static String getValueFromXPath(Document document, String xpathString) {
        try {
            return (String) XPathFactory.newInstance().
                    newXPath().evaluate( xpathString, document, XPathConstants.STRING );
        }
        catch (XPathExpressionException e) {
            throw new RuntimeException("Error evaluating xpath [" + xpathString + "] -- " + e.getMessage());
        }
    }

}
