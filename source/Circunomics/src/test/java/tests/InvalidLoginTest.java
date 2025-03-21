package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BaseClass;
import utilities.ConfigReaderInvalidSignIn;

import java.time.Duration;

@Listeners(utilities.TestListener.class)
public class InvalidLoginTest extends BaseClass {
    @Test
    public void testInvalidLogin() {
        // Fetch test data from the properties file
        String URL = ConfigReaderInvalidSignIn.getProperty("url");
        String INVALIDEMAIL = ConfigReaderInvalidSignIn.getProperty("invalidEmail");
        String INVALIDPASSWORD = ConfigReaderInvalidSignIn.getProperty("invalidPassword");
        String EXPECTEDERRORMESSAGE = ConfigReaderInvalidSignIn.getProperty("expectedErrorMessage");

        // Navigate to the homepage
        driver.get(URL);

        // Handle the popup (if it appears)
        int popupCount = 0;
        while (popupCount < 2) { // Adjust the limit based on your observation
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='AGREE']")));
                agreeButton.click();
                popupCount++;
                System.out.println("Popup dismissed " + popupCount + " time(s)");
            } catch (Exception e) {
                System.out.println("Popup not found or could not be clicked: " + e.getMessage());
                break;
            }
        }

        // Wait for the popup to disappear
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[.='AGREE']")));
        } catch (Exception e) {
            System.out.println("Popup did not disappear: " + e.getMessage());
        }

        // Initialize Page Objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Click on "Sign In" link
        homePage.clickSignInLink();

        // Log in with invalid credentials
        loginPage.enterEmail(INVALIDEMAIL);
        loginPage.enterPassword(INVALIDPASSWORD);
        loginPage.clickSignInButton();

        // Verify the error message
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, EXPECTEDERRORMESSAGE, "Error message does not match!");

        System.out.println(
                "actualErrorMessage: " + actualErrorMessage + "\n" + "expectedErrorMessage: " + EXPECTEDERRORMESSAGE
                        + "\n" + "As Actual error message is same as Expected error message, Test case is passed");
    }
}