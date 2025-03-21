package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.time.Duration;

public class BaseClass {

    protected static  WebDriver driver;
    protected BrowserMobProxy proxy;

    @BeforeSuite(groups = { "smokeTest", "regressionTest" })
    public void setUp1() {
        // Initialize ExtentReports
        ExtentReportUtils.initializeReport();
    }

    @BeforeTest(groups = { "smokeTest", "regressionTest" })
    public void BT() {
        System.out.println("Parallel Execution");
    }

    @BeforeClass
    public void setUp(ITestContext context) {
        // Step 1: Start BrowserMob Proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0); // Start on a random port

        // Step 2: Set up Selenium with the proxy
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions options1 = new ChromeOptions();
        options1.setProxy(seleniumProxy);

        // Step 3: Configure Chrome to ignore certificate errors
        options1.setAcceptInsecureCerts(true); // Accept insecure certificates
        options1.addArguments("--ignore-certificate-errors"); // Ignore certificate errors
        options1.addArguments("--disable-extensions");
        options1.addArguments("--disable-popup-blocking");
        options1.addArguments("--blink-settings=imagesEnabled=false");

        // Step 4: Use WebDriverManager to set up ChromeDriver
        WebDriverManager.chromedriver().setup(); // Automatically downloads and sets up ChromeDriver
        driver = new ChromeDriver(options1);

        // Step 5: Start capturing network traffic
        proxy.newHar("magentoDemo");

        // Step 6: Pass the WebDriver instance to the TestNG context
        context.setAttribute("driver", driver);

        // Step 7: Additional configurations
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void setUp() {
        // No changes needed here
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot and add it to the report
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            ExtentReportUtils.logTestStatus(Status.FAIL, result.getName());
            ExtentReportUtils.addScreenshotToReport(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportUtils.logTestStatus(Status.PASS, result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportUtils.logTestStatus(Status.SKIP, result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        // Step 8: Close the browser and stop the proxy
        if (driver != null) {
            driver.quit();
        }
        if (proxy != null) {
            proxy.stop();
        }
    }

    @AfterTest
    public void AT() {
        System.out.println("Parallel Execution Done");
    }

    @AfterSuite(groups = { "smokeTest", "regressionTest" })
    public void AS() {
        // Flush the Extent Report after all tests are executed
        ExtentReportUtils.flushReport();
        System.out.println("Close DataBase Connection");
    }
}