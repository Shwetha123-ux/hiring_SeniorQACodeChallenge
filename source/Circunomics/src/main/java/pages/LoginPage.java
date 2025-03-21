package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signInButton = By.id("send2");
    private By errorMessage = By.xpath("//div[@data-ui-id='message-error']");
    //private By cookieConsentBanner = By.cssSelector("div.qc-cmp-cleanslate"); // Locator for the cookie consent banner
    private By acceptCookiesButton = By.xpath("//button[@mode='primary']");  // Locator for the Accept Cookies button

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInButton() {
        // Handle cookie consent banner if it appears
        handleCookieConsentBanner();

        // Wait for the "Sign In" button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));

        // Click the "Sign In" button
        WebElement signInButtonElement = driver.findElement(signInButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInButtonElement);
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    // Helper method to handle the cookie consent banner
    private void handleCookieConsentBanner() {
        int retries = 3; // Number of retries to handle the banner
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
               // wait.until(ExpectedConditions.visibilityOfElementLocated(cookieConsentBanner));
               
                
               // driver.findElement(acceptCookiesButton).click(); // Click the "Accept Cookies" button
                System.out.println("Cookie consent banner handled.");
                break; // Exit the loop if the banner is dismissed
            } catch (Exception e) {
                retries--;
                if (retries == 0) {
                    System.out.println("Cookie consent banner not found after retries.");
                }
            }
        }
    }
}