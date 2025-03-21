package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WomenPage {
    private WebDriver driver;

    // Locators
    private By womenTab = By.linkText("Women");
    private By topsLink = By.linkText("Tops");
    private By jacketsLink = By.linkText("Jackets");

    // Constructor
    public WomenPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to hover over the "Women" tab
    public void hoverOverWomenTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(womenTab));

        Actions actions = new Actions(driver);
        actions.moveToElement(womenTabElement).perform();
    }

    // Method to hover over the "Tops" link and click "Jackets"
    public void clickJacketsLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Hover over "Tops"
        WebElement topsLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(topsLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(topsLinkElement).perform();

        // Click "Jackets"
        WebElement jacketsLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(jacketsLink));
        jacketsLinkElement.click();
    }
}