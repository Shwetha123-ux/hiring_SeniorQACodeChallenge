package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    // Capture screenshot and return the file path
    public static String captureScreenshot(WebDriver driver, String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/test-output/Screenshots/" + testName + "_" + timestamp + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(screenshotPath);
        FileUtils.copyFile(source, destination);

        return screenshotPath;
    }
}