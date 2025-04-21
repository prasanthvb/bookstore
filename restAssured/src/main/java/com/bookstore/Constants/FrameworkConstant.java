package com.bookstore.Constants;


import java.io.File;
import java.time.Duration;
import java.util.Random;

/**
 *
 * Represents framework specific property/constant values.
 *
 */
public final class FrameworkConstant {

    // Project root directory
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RESOURCE_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "com" + File.separator + "bookstore" + File.separator
            + "resources" + File.separator;

  

    // Property file
    public static final String PROPERTYFILE_PATH = RESOURCE_PATH + "config.properties";
 
    // Wait timing
    public static final Duration WAIT_TIME = Duration.ofMillis(13000);
    public static final int EXPLICIT_MAXWAIT = 10;
    public static final int EXPLICIT_MINWAIT = 2;
    public static final long PAGE_LOAD_TIMEOUT = 86;
    public static final long IMPLICIT_WAIT = 20;

    public static final int SHORT_WAIT = 3000;
    public static final int MEDIUM_WAIT = 6000;
    public static final int LONG_WAIT = 10000;


    // Extent reporting
    public static final String EXTENTREPORT_PATH = PROJECT_PATH + File.separator + "ExtentResults"
            + File.separator;
    public static final String EDIT_EXTENTREPORT_PATH = EXTENTREPORT_PATH + "ExtentReport.html";
    public static final String EXTENT_CONFIG_PATH = RESOURCE_PATH + "extentreport.xml";

    public static final String newExtentReportPath(String currentDate) {
        return EXTENTREPORT_PATH + "ExtentReport-" + currentDate + ".html";
    }

    public static final String REPORT_TITLE = "API Test Report";
    public static final String AUTHOR = "Nandhini";
    public static final String ENVIRONMENT = "Test";


}

