package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage {

	private WebDriver driver;

	// Locators
	private By firstNameField = By.id("firstname");
	private By lastNameField = By.id("lastname");
	private By emailField = By.id("email_address");
	private By passwordField = By.id("password");
	private By confirmPasswordField = By.id("password-confirmation");
	private By createAccountButton = By.cssSelector("button[title='Create an Account']");
	private By errorMessage = By.cssSelector("div.message-error");

	// Constructor
	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	// Methods
	public void enterFirstName(String firstName) {
		driver.findElement(firstNameField).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		driver.findElement(lastNameField).sendKeys(lastName);
	}

	public void enterEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
	}

	public void clickCreateAccountButton() {
		driver.findElement(createAccountButton).click();
	}

	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}
}
