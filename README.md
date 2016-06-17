Mobile UI Automation [![Build Status](https://jenkins.symantec.com/squish/squish.png?branch=master)](https://jenkins.symantec.com/squish)
==========

[Mobile UI Automation](http://gitlab.symantec.com/quality/Mobile/tree/master) is an end-to-end test framework for Mobile Test Automation stack.  Appium/Selenium java is used for the base test framework.  It runs tests against your 
application running in a iOS/Android simulators, interacting with it as a user would on the phone device. 


Getting Started
---------------

The Mobile documentation is located in the [Mobile/docs](http://gitlab.symantec.com/quality/Mobile/tree/master/doc) folder.

To get set up and running quickly:

- The [Mobile Github](http://gitlab.symantec.com/quality/Mobile/blob/master/README.md#GettingStarted)
- Work through the [Tutorial](http://gitlab.symantec.com/quality/Mobile/blob/master/README.md#Tutorial)
- Take a look at the [Table of Contents](http://gitlab.symantec.com/quality/Mobile/blob/master/README.md)

Once you are familiar with the tutorial, you’re ready to move on. To modify your environment, see the Setup docs. To start writing tests, see the Tests docs.


Getting Help
------------

Check the
[Mobile FAQ](http://gitlab.symantec.com/quality/Mobile/blob/master/faq.md)

For Contributors
----------------
Clone the github repository:

    git clone https://symantec.github.com/quality/rover/mobile.git
    cd rover
    git checkout master
    ...

By default, the tests expect the selenium server to be running at `http://localhost:4444/wd/hub`.

Squish's test suite runs against the included test application. Start that up with

    mvn -DskipTests clean install

Then run the tests with

    mvn clean test


# Symantec Quality
------------------
## Project: Mobile

## Mobile UI Test Automation (Appium/Selenium Java)

### [test location]

    com.symantec.qa.rover.test.*

### [directory structure]

    src/test/java
       com/symantec/qa/rover
              common
                  ...
              model
                  data
                  ui
              test
                  e2e
                  functional
                  performance
                  services

### [to run tests]

    mvn clean test                                  # run all tests in the suite
    mvn -Dtest=LoginTest clean test                 # run all tests in class
    mvn -Dtest=LoginTest#tryAdminUser clean test    # run one test method

# Web Controls
--------------

     abbrev		fullname    control type
     ------     --------    ------------
     btn        button      Button
     txt        text        Text
     sec        section     Section
     lnk        link        Hyperlink
     inp        input       Inputbox
     tre        tree        Tree
     lst        list        List
     img        image       Image
     frm        form        Form
     chk        checkbox    Checkbox
     rdo        radio       Radio Button
     lbl        label       Label
     mnu        menu        Menu

# Test Framework Enhancements
-----------------------------

     [should-have] implement custom step comment annotator to work with auto.highlight feature
     [must-have]   deploy seleniumgrid to be able to do cross-browser functionality tests
     [must-have]   deploy tests to run continuously in jenkins - send email with failures to look at
     [must-have]   generate testng reports to show failures
     [should-have] add logger capability to log steps for retracement if needed
     [could-have]  have a way to file a defect in jira of test failures, to demonstrate automation is catching regression issues
 
# Prioritization Definition
---------------------------
    
    Must have   --> Requirements labeled as MUST are critical to the current delivery timebox in order for it to be a success. If even one MUST requirement is not included, the project delivery should be considered a failure (note: requirements can be downgraded from MUST, by agreement with all relevant stakeholders; for example, when new requirements are deemed more important). MUST can also be considered a backronym for the Minimum Usable Subset.

    Should have --> Requirements labeled as SHOULD are important but not necessary for delivery in the current delivery timebox. While SHOULD requirements can be as important as MUST, they are often not as time-critical or there may be another way to satisfy the requirement, so that it can be held back until a future delivery timebox.

    Could have  --> Requirements labeled as COULD are desirable but not necessary, and could improve user experience or customer satisfaction for little development cost. These will typically be included if time and resources permit.

    Won't have  --> Requirements labeled as WON'T have been agreed by stakeholders as the least-critical, lowest-payback items, or not appropriate at that time. As a result, WON'T requirements are not planned into the schedule for the delivery timebox. WON'T requirements are either dropped or reconsidered for inclusion in later timeboxes. (Note: occasionally the term Would like is substituted, to give a clearer understanding of this choice).

   
