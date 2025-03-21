package tests;

import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarPostData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.BaseClass;

@Listeners(utilities.TestListener.class)
public class ExternalAPIInteractionTest extends BaseClass {

	@Test
	public void testMagentoDemoSite() throws InterruptedException {
	    // Step 1: Navigate to the Magento demo site
	   driver.get("https://magento.softwaretestingboard.com/");

	    // Step 2: Handle the "AGREE" pop-up if it appears
	    try {
	        WebElement agreeButton = driver.findElement(By.xpath("//span[text()='AGREE']"));
	        if (agreeButton.isDisplayed()) {
	            agreeButton.click();
	            System.out.println("Clicked on the 'AGREE' pop-up.");
	        }
	    } catch (Exception e) {
	        System.out.println("No pop-up appeared or could not find the 'AGREE' button.");
	    }

	    // Step 3: Click on the "Sign In" link
	    driver.findElement(By.linkText("Sign In")).click();

	    // Step 4: Wait for a few seconds to ensure all requests are captured
	    Thread.sleep(5000); // Wait for 5 seconds (adjust as needed)

	    // Step 5: Get the HAR log (captured network traffic)
	    HarLog harLog = proxy.getHar().getLog();

	    // Step 6: Search for the Google Analytics API request
	    boolean gaRequestFound = false;
	    for (HarEntry entry : harLog.getEntries()) {
	        String requestUrl = entry.getRequest().getUrl();
	        if (requestUrl.contains("https://region1.analytics.google.com/g/collect") ||
	            requestUrl.contains("https://www.google-analytics.com/g/collect")) {
	            gaRequestFound = true;

	            // Step 7: Validate the API request
	            int statusCode = entry.getResponse().getStatus();
	            System.out.println("Google Analytics API Request Found:");
	            System.out.println("URL: " + requestUrl);
	            System.out.println("Status Code: " + statusCode);

	            // Step 8: Allow both 200 and 204 status codes
	            if (statusCode == 200 || statusCode == 204) {
	                System.out.println("Google Analytics API request validated successfully.");
	            } else {
	                Assert.fail("Unexpected status code: " + statusCode);
	            }

	            // Step 9: Handle GET and POST requests
	            if ("GET".equalsIgnoreCase(entry.getRequest().getMethod())) {
	                // Validate query parameters for GET requests
	                System.out.println("Google Analytics API Request Query Parameters:");
	                System.out.println(requestUrl); // The query parameters are part of the URL

	                // Add assertions to validate the query parameters
	                Assert.assertTrue(requestUrl.contains("en=page_view"), "Query parameters do not contain 'en=page_view'");
	            } else if ("POST".equalsIgnoreCase(entry.getRequest().getMethod())) {
	                // Handle POST requests
	                HarPostData postData = entry.getRequest().getPostData();
	                if (postData != null) {
	                    // Validate the payload for POST requests with a payload
	                    String requestPayload = postData.getText();
	                    System.out.println("Google Analytics API Request Payload:");
	                    System.out.println(requestPayload);

	                    // Add assertions to validate the payload
	                    Assert.assertTrue(requestPayload.contains("page_view"), "Payload does not contain 'page_view' event");
	                } else {
	                    // Skip payload validation for POST requests with no payload
	                    System.out.println("Google Analytics API Request has no payload.");
	                }
	            }
	            break;
	        }
	    }

	    // Step 10: Assert that the Google Analytics request was found
	    Assert.assertTrue(gaRequestFound, "Google Analytics API request not found");
	}}