package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtils {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize ExtentReports and attach the HTML reporter
    public static void initializeReport() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // Create a test in the report
    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    // Log test status (PASS/FAIL/SKIP) in the report
    public static void logTestStatus(Status status, String message) {
        if (status == Status.FAIL) {
            test.log(status, MarkupHelper.createLabel(message + " - Test Case FAILED", ExtentColor.RED));
        } else if (status == Status.PASS) {
            test.log(status, MarkupHelper.createLabel(message + " - Test Case PASSED", ExtentColor.GREEN));
        } else if (status == Status.SKIP) {
            test.log(status, MarkupHelper.createLabel(message + " - Test Case SKIPPED", ExtentColor.YELLOW));
        }
    }

    // Add a screenshot to the report
    public static void addScreenshotToReport(String screenshotPath) {
        test.addScreenCaptureFromPath(screenshotPath);
    }

    // Flush the report (write to file)
    public static void flushReport() {
        extent.flush();
    }
}