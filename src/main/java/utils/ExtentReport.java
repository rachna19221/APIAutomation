package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReport {
    public static ExtentReports extentReport;
    public static ExtentTest extentlog;




    public static void initialize(String path) throws IOException {

        if (extentReport == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(path);

            // Optionally load config XML (if you have one)
            spark.loadXMLConfig(new File(System.getProperty("user.dir") + "/resources/extent-config.xml"));

            extentReport = new ExtentReports();
            extentReport.attachReporter(spark);

            extentReport.setSystemInfo("Host Name", System.getProperty("user.name"));
            extentReport.setSystemInfo("OS", "MAC OS");
            extentReport.setSystemInfo("Environment", Helper.propertyReader(Helper.commonFilePath, "executionEnv"));


        }

    }

}
