package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.BaseClass;
import utilities.ConfigReaderMen;
@Listeners(utilities.TestListener.class)
//Failed TC
public class SubmitReviewTest extends BaseClass {
    @Test
    public void testSubmitReview() throws Throwable {
        // Fetch test data from the properties file
        String url = ConfigReaderMen.getProperty("url");
        String username = ConfigReaderMen.getProperty("username");
        String password = ConfigReaderMen.getProperty("password");
        String productName = ConfigReaderMen.getProperty("productName");
        String summary = ConfigReaderMen.getProperty("summary");
        String review = ConfigReaderMen.getProperty("review");

        // Navigate to the homepage
        driver.get(url);

        // Initialize pages
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MenPage menPage = new MenPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ReviewPage reviewPage = new ReviewPage(driver);

        // Handle overlay (cookie consent banner)
        homePage.handleOverlay();

        // Click on "Sign In" link using JavaScript
        homePage.clickSignInLink();

        // Log in with valid credentials
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();

        // Navigate to the "Men" tab, then to "Bottoms," and finally to "Shorts"
        menPage.navigateToHoodiesAndSweatshirtsMen();

        // Click on the product (e.g., "Pierce Gym Short")
        driver.findElement(By.linkText(productName)).click();

        // Click on "Be the first to review this product" link
        productPage.clickReviewLink();

        // Wait for the review form to load
        reviewPage.waitForReviewFormToLoad();

        // Provide a 5-star rating
      reviewPage.provideRating();

        // Enter summary (greater than 255 characters)
        reviewPage.enterSummary(summary);

        // Enter review text
        reviewPage.enterReview(review);

        // Click on "Submit Review" button
        reviewPage.clickSubmitButton();

        // Verify the error message
        String expectedErrorMessage = "Maximum 255 characters are allowed for the summary.";
        String actualErrorMessage = reviewPage.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message not displayed correctly.");
       
    }
}