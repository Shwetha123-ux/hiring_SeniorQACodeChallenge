package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class MenPage {
    private WebDriver driver;

    // Locators
    private By menTab = By.xpath("//span[.='Men']");
    private By TopsMenLink = By.xpath("//a[@href='https://magento.softwaretestingboard.com/men/tops-men.html']");
    private By hoodiesAndSweatShirtsMenLink = By.xpath("//a[@href='https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html']");

    // Constructor
    public MenPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to hover over "Men" tab, then "Bottoms," and click "Shorts"
    public void navigateToHoodiesAndSweatshirtsMen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        // Hover over "Men" tab
        WebElement menTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(menTab));
        actions.moveToElement(menTabElement).perform();

        // Hover over Men "Tops" option
        WebElement topsMenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TopsMenLink));
        actions.moveToElement(topsMenElement).perform();
        
        // Wait for the page to fully load
//        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
//            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
//        
        

        // Click "hoodies And SweatShirts" option
       
        WebElement hoodiesAndSweatShirtsElementMen = wait.until(ExpectedConditions.elementToBeClickable(hoodiesAndSweatShirtsMenLink));
        hoodiesAndSweatShirtsElementMen.click();
    }
}