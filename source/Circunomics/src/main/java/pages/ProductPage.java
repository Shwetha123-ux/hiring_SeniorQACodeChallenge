package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;

    // Locators
    private By sizeOption = By.xpath("//div[@option-label='S']");
    private By colorOption = By.xpath("//div[@option-label='Green']");
    private By quantityField = By.id("qty");
    private By addToCartButton = By.id("product-addtocart-button");
    private By cartIcon = By.xpath("//a[@class='action showcart']");
    private By proceedToCheckoutButton = By.id("top-cart-btn-checkout");
    private By reviewLink = By.xpath("//a[@href='https://magento.softwaretestingboard.com/marco-lightweight-active-hoodie.html#review-form']");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void selectSize() {
        driver.findElement(sizeOption).click();
    }

    public void selectColor() {
        driver.findElement(colorOption).click();
    }

    public void enterQuantity(String quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(quantity);
    }

    // Method to click "Add to Cart" button
    public void clickAddToCartButton() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartElement.click();
        Thread.sleep(2000);
    }

    // Method to click "Cart" icon
    public void clickCartIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement cartIconElement = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIconElement.click();
    }

    public void clickProceedToCheckoutButton() {
        driver.findElement(proceedToCheckoutButton).click();
    }
    
    // Method to click "Be the first to review this product" link
    public void clickReviewLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement reviewLinkElement = wait.until(ExpectedConditions.elementToBeClickable(reviewLink));
        reviewLinkElement.click();
    }
}