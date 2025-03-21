package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReviewPage {
    private WebDriver driver;

    // Locators
    private By reviewForm = By.xpath("//a[@href='https://magento.softwaretestingboard.com/marco-lightweight-active-hoodie.html#review-form']"); // Locator for the review form
    private By ratingField = By.id("Rating_1_label");
    private By summaryField = By.id("summary_field");
    private By reviewField = By.id("review_field");
    private By submitButton = By.xpath("//button[@class='action submit primary']");
    private By errorMessage = By.xpath("//div[.='You submitted your review for moderation.']");

    // Constructor
    public ReviewPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to wait for the review form to load
    public void waitForReviewFormToLoad() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time
        wait.until(ExpectedConditions.visibilityOfElementLocated(reviewForm));
        Thread.sleep(3000);
    }

    // Method to provide a 5-star rating
    public void provideRating() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ratingElement = wait.until(ExpectedConditions.elementToBeClickable(ratingField));
        ratingElement.click();
    }

    // Method to enter summary
    public void enterSummary(String summary) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement summaryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(summaryField));
        summaryElement.sendKeys(summary);
    }

    // Method to enter review
    public void enterReview(String review) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement reviewElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reviewField));
        reviewElement.sendKeys(review);
    }

    // Method to click "Submit Review" button
    public void clickSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButtonElement.click();
    }

    // Method to get the error message
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return errorMessageElement.getText();
    }
}