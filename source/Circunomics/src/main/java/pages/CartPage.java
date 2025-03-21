package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Robot;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By subtotal = By.xpath("//span[@class='price-wrapper' and @data-bind='html: cart().subtotal_excl_tax']");
    private By quantityField = By.xpath("//input[@class='item-qty cart-item-qty']");
    private By updateButton = By.xpath("//span[text()='Update']");
    private By proceedToCheckoutButton = By.xpath("//button[@class='action primary checkout']");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the cart subtotal
    public String getSubtotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement subtotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotal));
        return subtotalElement.getText();
    }

    // Method to update the quantity
    public void updateQuantity(String quantity) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField));
        //quantityElement.clear();
        quantityElement.sendKeys(Keys.CONTROL + "a");
        quantityElement.sendKeys(Keys.DELETE);
        
        Thread.sleep(2000);
        quantityElement.sendKeys(quantity);
        Thread.sleep(2000);
        WebElement updateElement = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        updateElement.click();
        Thread.sleep(2000);
    }

    // Method to click "Proceed to Checkout" button
    public void clickProceedToCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutButtonElement = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        checkoutButtonElement.click();
    }
}