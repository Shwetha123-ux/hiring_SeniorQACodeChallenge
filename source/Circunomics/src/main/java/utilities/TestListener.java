package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Create a test in the Extent Report
        ExtentReportUtils.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test as passed in the Extent Report
        ExtentReportUtils.logTestStatus(Status.PASS, result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test as failed in the Extent Report and capture a screenshot
        ExtentReportUtils.logTestStatus(Status.FAIL, result.getName());
        try {
            String screenshotPath = ScreenshotUtils.captureScreenshot(BaseClass.driver, result.getName());
            ExtentReportUtils.addScreenshotToReport(screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test as skipped in the Extent Report
        ExtentReportUtils.logTestStatus(Status.SKIP, result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the Extent Report after all tests are executed
        ExtentReportUtils.flushReport();
    }

    // Other methods from ITestListener (can be left empty if not needed)
    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}