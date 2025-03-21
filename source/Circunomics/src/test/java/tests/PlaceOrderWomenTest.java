package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.BaseClass;
import utilities.ConfigReaderPlaceOrderTestWOmen;

@Listeners(utilities.TestListener.class)
public class PlaceOrderWomenTest extends BaseClass {
    @Test
    public void testPlaceOrderWomen() throws Throwable {
        // Fetch test data from the properties file
        String url = ConfigReaderPlaceOrderTestWOmen.getProperty("url");
        String username = ConfigReaderPlaceOrderTestWOmen.getProperty("username");
        String password = ConfigReaderPlaceOrderTestWOmen.getProperty("password");
        String productName = ConfigReaderPlaceOrderTestWOmen.getProperty("productName");
        String size = ConfigReaderPlaceOrderTestWOmen.getProperty("size");
        String color = ConfigReaderPlaceOrderTestWOmen.getProperty("color");
        String quantity = ConfigReaderPlaceOrderTestWOmen.getProperty("quantity");

        // Navigate to the homepage
        driver.get(url);

        // Initialize pages
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        WomenPage womenPage = new WomenPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Handle overlay (if any)
        homePage.handleOverlay();

        // Click on "Sign In" link
        homePage.clickSignInLink();

        // Log in with valid credentials
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();

        // Navigate to the "Women" tab
        homePage.clickWomenTab();

        // Hover over "Women" tab and click "Jackets"
        womenPage.hoverOverWomenTab();
        womenPage.clickJacketsLink();

        // Click on the "Juno Jacket" product
        driver.findElement(By.linkText(productName)).click();

        // Select size, color, and quantity
        productPage.selectSize();
        productPage.selectColor();
        productPage.enterQuantity(quantity);

        // Add the product to the cart
        productPage.clickAddToCartButton();

        // Wait for the cart to update (optional, if needed)
        try {
            Thread.sleep(2000); // Wait for 2 seconds (replace with explicit wait if needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Proceed to checkout
        productPage.clickCartIcon();
        productPage.clickProceedToCheckoutButton();

        // Complete the checkout process
        checkoutPage.clickNextButton();
        checkoutPage.clickPlaceOrderButton();

        // Verify the order is placed successfully
        String expectedMessage = "Thank you for your purchase!"; // Updated expected message
        String actualMessage = checkoutPage.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Order was not placed successfully. Actual message: " + actualMessage);
    }
}