package core;


import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.Helper;

import java.io.IOException;

public class BaseTest {

    @Parameters({ "ReportName", "FlowType" })
    @BeforeSuite(alwaysRun = true)
    public void config(@Optional("Optional name Automation ") String reportname, @Optional("API Report") String flow)
            throws IOException {

        //Create the path in which we will create folder to keep html reports
        String subfolderpath=System.getProperty("user.dir")+"/reports/"+ Helper.getTimestamp();
        //create sub folder
        Helper.createFolder(subfolderpath);

        ExtentReport.initialize(subfolderpath+"/"+"API_Automation.html");
    }


    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReport.extentlog.pass("Test Case: " + result.getName() + " is passed.");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.extentlog.fail("Test Case: " + result.getName() + " is failed.");
            ExtentReport.extentlog.fail("Failure Reason: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReport.extentlog.skip("Test Case: " + result.getName() + " was skipped.");
        }
        ExtentReport.extentReport.flush();
    }



    @AfterSuite(alwaysRun = true)
    public void endReport() {
        ExtentReport.extentReport.flush(); // This is the correct call for v5+
    }


}
