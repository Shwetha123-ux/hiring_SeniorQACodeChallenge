
    package pages;

    import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.time.Duration;

    public class HomePage {
        private WebDriver driver;

        // Locators
        private By signInLink = By.linkText("Sign In");
        private By menTab = By.xpath("//span[.='Men']");
        private By womenTab = By.xpath("//span[.='Women']");
        private By gearTab = By.xpath("//span[.='Gear']");
     //  private By overlay = By.cssSelector("div.qc-cmp-ui-container"); // Locator for the overlay
        private By agreeButton = By.xpath("//button[.='AGREE']"); // Updated locator for the \"Agree\" button
        private By createAccountLink = By.xpath("//a[.='Create an Account']");
        // Constructor
        public HomePage(WebDriver driver) {
            this.driver = driver;
        }


        // Method to handle overlay (cookie consent banner)
        public void handleOverlay() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time

                // Wait for the overlay to be visible
              //  wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));

                // Locate the "Agree" button and click it
                WebElement agreeButtonElement = wait.until(ExpectedConditions.elementToBeClickable(agreeButton));
                agreeButtonElement.click();

                // Wait for the overlay to disappear
              // wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
            } catch (Exception e) {
               // System.out.println("No overlay found or it could not be handled.");
                e.printStackTrace(); // Print the stack trace for debugging
            }
        }
        
     // Method to click "Create an Account" link using JavaScript
        public void clickCreateAccountLink() {
            WebElement createAccountElement = driver.findElement(createAccountLink);

            // Scroll the element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createAccountElement);

            // Click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createAccountElement);
        }
        

        // Method to click "Sign In" link using JavaScript
        public void clickSignInLink() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signInElement = wait.until(ExpectedConditions.elementToBeClickable(signInLink));

            // Scroll the element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signInElement);

            // Click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signInElement);
        }

        // Method to click "Men" tab
        public void clickMenTab() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement menTabElement = wait.until(ExpectedConditions.elementToBeClickable(menTab));
            menTabElement.click();
        }
        
     // Method to click "Women" tab
        public void clickWomenTab() {
            WebElement womenTabElement = driver.findElement(womenTab);

            // Scroll the element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenTabElement);

            // Click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", womenTabElement);
        }
        
     // Method to click "Gear" tab
        public void clickGearTab() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement gearTabElement = wait.until(ExpectedConditions.elementToBeClickable(gearTab));
            gearTabElement.click();
        }
    }