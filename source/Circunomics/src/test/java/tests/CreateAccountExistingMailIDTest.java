package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.HomePage;
import utilities.BaseClass;
import utilities.ConfigReaderExistingMailID;
import utilities.ConfigReaderPlaceOrderTestWOmen;

@Listeners(utilities.TestListener.class)
public class CreateAccountExistingMailIDTest extends BaseClass {
    @Test
    public void testExistingEmailErrorMessage() {
        // Fetch test data from the properties file
        String url = ConfigReaderExistingMailID.getProperty("url");
        String firstName = ConfigReaderExistingMailID.getProperty("firstName");
        String lastName = ConfigReaderExistingMailID.getProperty("lastName");
        String existingEmail = ConfigReaderExistingMailID.getProperty("existingEmail");
        String password = ConfigReaderExistingMailID.getProperty("password");
        String confirmPassword = ConfigReaderExistingMailID.getProperty("confirmPassword");
        String expectedErrorMessage = ConfigReaderExistingMailID.getProperty("expectedErrorMessage");

        // Navigate to the homepage
        driver.get(url);

        // Initialize pages
        HomePage homePage = new HomePage(driver);
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        // Handle overlay by clicking the "Agree" button
        homePage.handleOverlay();

        // Click on "Create an Account" link
        homePage.clickCreateAccountLink();

        // Fill in the registration form with existing email
        createAccountPage.enterFirstName(firstName);
        createAccountPage.enterLastName(lastName);
        createAccountPage.enterEmail(existingEmail);
        createAccountPage.enterPassword(password);
        createAccountPage.enterConfirmPassword(confirmPassword);

        // Click on "Create an Account" button
        createAccountPage.clickCreateAccountButton();

        // Verify the error message
        String actualErrorMessage = createAccountPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match.");
        System.out.println("actualErrorMessage: " + actualErrorMessage + "\n" + "expectedErrorMessage: " + expectedErrorMessage + "\n" + 
        		"As Actual error message is same as Expected error message, Test case is passed");
        
    }
}