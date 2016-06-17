/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.framework.logging;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Reporter;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class Log {

    private static Logger logger = null;
    private static String screenShotDir = "";
    private static final String logConfigFileName = "log4j.xml";

    private static Logger getLogger() {
        if (logger == null) {
            initializeLog(Log.class, logConfigFileName);
        }
        return logger;
    }

    /**
     * Initializes the logging object based on the class passed in from
     * configuration xml specified.
     *
     * @param logClass	the class in the configuration xml to use
     * @param logConfigFile	the configuration xml to create the logging
     * 						object from
     */
    private static void initializeLog(Class<?> logClass, String logConfigFile) {
        logger = Logger.getLogger(logClass);
        DOMConfigurator.configure(logConfigFile);
    }

    /**
     * Uses the logger to post a basic information message.  Automatically
     * appends a date-time stamp, the message level, and the calling method
     * to the message passed in.
     *
     * @param message the text to post using the log object.
     */
    public static void info(Object message) {
        String methodName = getCallingMethod(3);
        getLogger().info("[" + methodName + "] " + message);
    }

    /**
     * Uses the logger to post a warning message.  Automatically
     * appends a date-time stamp, the message level, and the calling method
     * to the message passed in.
     *
     * @param message the text to post using the log object.
     */
    public static void warn(Object message) {
        String methodName = getCallingMethod(3);
        getLogger().warn("[" + methodName + "] " + message);
    }

    /**
     * Uses the logger to post a fatal message and take a screenshot if
     * requested.  Automatically appends a date-time stamp, the message
     * level, and the calling method to the message passed in.
     *
     * @param message the text to post using the log object.
     * @param takeScreenshot flag to indicate to take a screenshot or not.
     */
    public static void fatal(Object message, boolean takeScreenshot) {
        String methodName = getCallingMethod(3);
        if (takeScreenshot) {
            takeScreenshot(methodName + "_fatal", true);
        }
        getLogger().fatal("[" + methodName + "] " + message);
    }

    /**
     * Uses the logger to post a debug message.  Automatically
     * appends a date-time stamp, the message level, and the calling method
     * to the message passed in.
     *
     * @param message the text to post using the log object.
     */
    public static void debug(Object message) {
        debug(message, false);
    }

    /**
     * Uses the logger to post a debug message and take a screenshot if
     * requested.  Automatically appends a date-time stamp, the message
     * level, and the calling method to the message passed in.
     *
     * @param message the text to post using the log object
     * @param takeScreenshot flag to take screenshot during debugging
     *
     */
    public static void debug(Object message, boolean takeScreenshot) {
        String methodName = getCallingMethod(3);
        if ( methodName.contentEquals( getCallingMethod(2) ) ) {
            methodName = getCallingMethod(4);
        }
        if (takeScreenshot) {
            takeScreenshot(methodName + "_debug", true);
        }
        getLogger().debug("[" + methodName + "] " + message);
    }

    /**
     * Uses the logger to post a error message.
     *
     * @param message the text to post using the log object.
     */
    public static void error(Object message) {
        error(message, false);
    }

    /**
     * Uses the logger to post a error message and take a screenshot if
     * requested.  Automatically appends a date-time stamp, the message
     * level, and the calling method to the message passed in.
     *
     * @param message the text to post using the log object.
     * @param takeScreenshot flag to indicate to take a screenshot or not.
     */
    public static void error(Object message, boolean takeScreenshot) {
        String methodName = getCallingMethod(3);
        if ( methodName.contentEquals( getCallingMethod(2) ) ) {
            methodName = getCallingMethod(4);
        }
        if (takeScreenshot) {
            takeScreenshot(methodName + "_error", true);
        }
        getLogger().error("[" + methodName + "] " + message);
    }

    /**
     * Takes a screenshot of the current desktop and saves it the location
     * and name specified.
     *
     * @param fileName 	path and name of the file to save the screenshot as.
     * @param appendTimeStamp	flag for appending a time and date to the
     * 							file name specified.
     */
    public static void takeScreenshot(String fileName, boolean appendTimeStamp) {
        String imageName = fileName;
        if (appendTimeStamp) {
            imageName = imageName + getDateForFileName();
        }
        if ( !screenShotDir.isEmpty() ) {
            imageName = screenShotDir + "\\" + imageName ;
        }

        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);
            Robot robot = new Robot();
            File file = new File(imageName + ".png");
            BufferedImage image = robot.createScreenCapture(screenRect);

            ImageIO.write(image, "png", file);
            Reporter.log("<a href=\"" + file.getAbsolutePath() + "\">Screenshot (" + fileName + ") </a>");
        }
        catch (AWTException e) {
            getLogger().error("Error taking screenshot - " + e.getMessage());
        }
        catch (IOException e) {
            getLogger().error("Error writing screenshot - " + e.getMessage());
        }
    }

    /**
     * Creates a string using the current date and time for use in a
     * file name.
     *
     * @return a date-time formatted string for file name usage.
     */
    private static String getDateForFileName() {
        Calendar calendar = Calendar.getInstance();
        String dateString = "";
        dateString =  dateString + "_" + String.format("%tF", calendar);
        dateString = dateString + "_" + String.format("%tT", calendar);
        dateString = dateString.replace(':', '_');
        return dateString;
    }

    /**
     * Gets the specified calling method name at the depth requested
     * by the parameter.
     *
     * @param callStackDepth the depth of method in the stack to retrieve.
     * @return the name of the method at the stack depth specified.
     */
    private static String getCallingMethod(int callStackDepth) {
        String className = Thread.currentThread().getStackTrace()[callStackDepth].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[callStackDepth].getMethodName();
        return className + "." + methodName;
    }

    public static void setScreenshotDir(String outputDir) {
        screenShotDir  = outputDir;
    }

}
