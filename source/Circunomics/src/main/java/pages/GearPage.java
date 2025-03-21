package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GearPage {
    private WebDriver driver;

    // Locators
    private By gearTab = By.xpath("//span[text()='Gear']");
    private By watchesLink = By.xpath("//a[@href='https://magento.softwaretestingboard.com/gear/watches.html']");

    // Constructor
    public GearPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to hover over "Gear" tab, then "Watches," and click "Watches"
    public void navigateToWatches() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        // Hover over "Gear" tab
        WebElement gearTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(gearTab));
        actions.moveToElement(gearTabElement).perform();

        // Hover over "Watches" option
        WebElement watchesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(watchesLink));
        actions.moveToElement(watchesElement).perform();

        // Click "Watches" option
        watchesElement.click();
    }
}