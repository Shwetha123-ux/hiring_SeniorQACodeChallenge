package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    // Locators
    private By shippingCost = By.xpath("//span[@data-bind='text: getFormattedPrice(method.price_excl_tax)']");
    private By grandTotal = By.xpath("//tr/td/strong/span[@class='price']");
    private By nextButton = By.xpath("//button[@class='button action continue primary']");
    private By placeOrderButton = By.xpath("//button[@title='Place Order']");
    private By successMessage = By.xpath("//span[.='Thank you for your purchase!']");

  //*[@id="opc-sidebar"]/div[1]/table/tbody/tr[3]/td/strong/span
    
    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

 // Method to get the shipping cost
    public String getShippingCost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement shippingCostElement = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingCost));
        return shippingCostElement.getText();
    }
    
    // Method to get the grand total
    public String getGrandTotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement grandTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(grandTotal));
        return grandTotalElement.getText();
    }

    
    // Method to click "Next" button
    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the "Next" button to be clickable
        WebElement nextButtonElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        // Scroll the "Next" button into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButtonElement);

        // Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonElement);
    }

    // Method to click "Place Order" button
    public void clickPlaceOrderButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the "Place Order" button to be clickable
        WebElement placeOrderButtonElement = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));

        // Scroll the "Place Order" button into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButtonElement);

        // Click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButtonElement);
        Thread.sleep(2000);
    }

    // Method to get the success message
    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time
        WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successMessageElement.getText();
    }
}