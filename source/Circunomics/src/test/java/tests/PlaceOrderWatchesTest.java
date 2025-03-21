package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.BaseClass;
import utilities.ConfigReaderWatches;

@Listeners(utilities.TestListener.class)
public class PlaceOrderWatchesTest extends BaseClass {
	@Test
	public void testPlaceOrderWatches() throws Throwable {
		// Fetch test data from the properties file
		String url = ConfigReaderWatches.getProperty("url");
		String username = ConfigReaderWatches.getProperty("username");
		String password = ConfigReaderWatches.getProperty("password");
		String productName = ConfigReaderWatches.getProperty("productName");
		String initialSubtotal = ConfigReaderWatches.getProperty("initialSubtotal");
		String updatedSubtotal = ConfigReaderWatches.getProperty("updatedSubtotal");

		// Navigate to the homepage
		driver.get(url);

		// Initialize pages
		HomePage homePage = new HomePage(driver);
		LoginPage loginPage = new LoginPage(driver);
		GearPage gearPage = new GearPage(driver);
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		// Handle overlay (cookie consent banner)
		homePage.handleOverlay();

		// Click on "Sign In" link
		homePage.clickSignInLink();

		// Log in with valid credentials
		loginPage.enterEmail(username);
		loginPage.enterPassword(password);
		loginPage.clickSignInButton();

		// Navigate to the "Gear" tab, then to "Watches," and click "Watches"
		gearPage.navigateToWatches();

		// Click on the product (e.g., "Clamber Watch")
		driver.findElement(By.linkText(productName)).click();

		// Click on "Add to Cart" button
		productPage.clickAddToCartButton();

		// Click on "Cart" icon
		productPage.clickCartIcon();

		// Verify the initial cart subtotal
		String actualSubtotal = cartPage.getSubtotal();

		Assert.assertTrue(actualSubtotal.contains(initialSubtotal),
				"Initial subtotal is not correct. Actual: " + actualSubtotal);

		// Update the quantity to 2
		cartPage.updateQuantity("2");

		// Verify the updated cart subtotal
		String updatedActualSubtotal = cartPage.getSubtotal();
		System.out.println("updatedActualSubtotal:" + updatedActualSubtotal);
		Assert.assertTrue(updatedActualSubtotal.contains(updatedSubtotal),
				"Updated subtotal is not correct. Actual: " + updatedActualSubtotal);

		// Click on "Proceed to Checkout" button
		cartPage.clickProceedToCheckoutButton();

		// Extract the shipping cost
		String shippingCost = checkoutPage.getShippingCost();
		System.out.println("Shipping cost: " + shippingCost);

		// Click on "Next" button
		checkoutPage.clickNextButton();

		// Extract the grand total
		String grandTotal = checkoutPage.getGrandTotal();
		System.out.println("Grand total: " + grandTotal);

		// Calculate the expected grand total
		double subtotalValue = Double.parseDouble(updatedSubtotal.replace("$", ""));
		double shippingCostValue = Double.parseDouble(shippingCost.replace("$", ""));
		double expectedGrandTotal = subtotalValue + shippingCostValue;

		// Verify the grand total
		double actualGrandTotalValue = Double.parseDouble(grandTotal.replace("$", ""));
		Assert.assertEquals(actualGrandTotalValue, expectedGrandTotal, "Grand total is not correct.");

		// Click on "Place Order" button
		checkoutPage.clickPlaceOrderButton();

		// Verify the success message
		String expectedSuccessMessage = "Thank you for your purchase!";
		String actualSuccessMessage = checkoutPage.getSuccessMessage();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage), "Order was not placed successfully.");
		System.out.println(
				"Custom Assertions to validate complex conditions have been successfully tested at 4 levels below :"
						+ "\n" + "1) Initial Cart Subtotal" + "\n"
						+ "2) Updated Cart Subtotal after updatingthe quantity of the item" + "\n"
						+ "3) Grandtotal = shipping cost + cart subtotal" + "\n" + "4) Successful order of the item");
	}
}