# Test Automation Framework Guide

## Table of Contents
1. [Project Overview](#project-overview)
2. [Selenium WebDriver: The Backbone of Our Automation](#selenium-webdriver-the-backbone-of-our-automation)
3. [Java: The Engine That Drives Our Tests](#java-the-engine-that-drives-our-tests)
4. [Maven: Our Project Organizer and Dependency Saver](#maven-our-project-organizer-and-dependency-saver)
5. [TestNG: The Turbo Boost for Our Tests](#testng-the-turbo-boost-for-our-tests)
6. [WebDriverManager: The Setup Smoother](#webdrivermanager-the-setup-smoother)
7. [ExtentReports: Reporting That Looks as Good as It Works](#extentreports-reporting-that-looks-as-good-as-it-works)
8. [RestAssured: Our API Testing Wingman](#restassured-our-api-testing-wingman)
9. [BrowserMob Proxy: Digging Into Network Traffic](#browsermob-proxy-digging-into-network-traffic)
10. [Parallel Execution: Faster Tests, Happier Teams](#parallel-execution-faster-tests-happier-teams)
11. [Property Files: Keeping Our Data Separate and Clean](#property-files-keeping-our-data-separate-and-clean)
12. [Setting Up the Automation Suite](#setting-up-the-automation-suite)
13. [Running the Tests](#running-the-tests)
14. [Test Reports](#test-reports)
15. [API Testing](#api-testing)
16. [Troubleshooting](#troubleshooting)
17. [Future Improvements](#future-improvements)

## Project Overview
When building a solid automation framework, it’s not just about picking fancy tools—it’s about making smart choices that help us work faster and deliver reliable results. Here’s why we handpicked each tool and how they come together to create a powerful automation suite for both UI and API testing.

## Selenium WebDriver: The Backbone of Our Automation
Selenium is the industry go-to for web automation. It works across all major browsers (Chrome, Firefox, Edge, Safari) and has massive community support. Its open-source nature makes it highly customizable, and it integrates seamlessly with tools like Jenkins, Maven, and TestNG.

## Java: The Engine That Drives Our Tests
Java powers this framework with its rich set of libraries and frameworks. As one of the most popular languages in automation, Java helps us write reusable, maintainable, and scalable test code. Its strong community means faster troubleshooting and endless learning resources.

## Maven: Our Project Organizer and Dependency Saver
Maven helps us manage all project dependencies effortlessly through the `pom.xml` file. It automates the build process and ensures a consistent project structure across the board. Plus, it smoothly integrates with IDEs like IntelliJ and Eclipse, as well as CI/CD pipelines.

## TestNG: The Turbo Boost for Our Tests
TestNG brings next-level flexibility—grouping tests, running them in parallel, and allowing easy parameterization. Its reporting capabilities and integration with other tools make it an essential part of this framework.

## WebDriverManager: The Setup Smoother
Forget manual driver downloads—WebDriverManager handles it for us! It detects browser versions and automatically configures the appropriate drivers, saving setup time and reducing human error.

## ExtentReports: Reporting That Looks as Good as It Works
We use ExtentReports to generate professional-looking, interactive HTML reports. These reports provide detailed insights like test steps, screenshots for failures, logs, and easy-to-read pass/fail statuses that even non-technical stakeholders can understand.

## RestAssured: Our API Testing Wingman
RestAssured is perfect for REST API testing. Its fluent Java API lets us validate JSON and XML responses and create robust API test flows that plug right into our Selenium framework.

## BrowserMob Proxy: Digging Into Network Traffic
BrowserMob Proxy helps us capture and manipulate HTTP traffic for security and performance testing. It allows us to record HAR files and modify HTTP requests/responses, making it ideal for hybrid UI and API tests.

## Parallel Execution: Faster Tests, Happier Teams
Running tests in parallel cuts execution time drastically. With TestNG’s parallel execution feature, we boost test efficiency, speed up release cycles, and make the most of available resources.

## Property Files: Keeping Our Data Separate and Clean
Test data lives in centralized property files, keeping logic and data separate. This setup makes it easy to maintain test data and switch between environments without touching the actual test code.

## Setting Up the Automation Suite

### Prerequisites
Make sure these tools are installed on your system before getting started:
1. JDK 8 or higher - [Download Here](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Maven 3.8.x - [Download Here](https://maven.apache.org/download.cgi)
3. IntelliJ IDEA or Eclipse - [IntelliJ](https://www.jetbrains.com/idea/download/) | [Eclipse](https://www.eclipse.org/downloads/)
4. Google Chrome (latest version)
5. Git - [Download Here](https://git-scm.com/downloads)

### Project Setup Steps
1. Clone the repo:
   ```bash
   git clone https://github.com/Shwetha123-ux/hiring_SeniorQACodeChallenge
   cd "hiring_SeniorQACodeChallenge/source/Circunomics - Shwetha Manjunatha"
   ```

2. Import into your IDE as a Maven project.
3. Maven will auto-resolve dependencies via the pom.xml. If not, run:
   ```bash
   mvn clean install
   ```
4. WebDriverManager will automatically handle browser driver setup.
5. Configure your test data in these files located under `src/test/resources`:
   - testdata.properties
   - testdataInvalidSignIn.properties
   - testdatamen.properties
   - testdatawatches.properties
   - testdatawomen.properties

## Running the Tests

### Command Line
Run all tests:
```bash
mvn clean test
```
Run a specific test suite:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Parallel Execution
Update the `testng.xml` or `testing-parallel.xml`:
```xml
<suite name="Parallel Suite" parallel="classes" thread-count="5">
```

### Within IDE
Right-click `testng.xml` or a specific test class.
Choose **RunAs TestNG Suite**.

## Test Reports
- ExtentReports generates reports in `test-output/`.
- Example report: `ExtentReport_2025.03.20.23.37.29.html`
- Failed test screenshots are saved under `test-output/screenshots/`.

## API Testing
- API tests are located in `\src\test\java\tests\ExternalAPIInteractionTest.java`.
- To run API tests, execute the `ExternalAPIInteractionTest` class.

Run the class directly from your IDE or via Maven to execute API tests powered by RestAssured and BrowserMob Core.

## Troubleshooting

### Common Fixes
- **Driver Mismatch**: Ensure browser and driver versions match (WebDriverManager helps here).
- **Dependencies Not Resolving?** Run:
  ```bash
  mvn clean install
  ```
- **API Not Working?** Check API endpoint, credentials, and network/firewall settings.

## Future Improvements
- Add cross-browser testing (Firefox, Edge, Safari).
- Integrate with Jenkins/GitHub Actions for CI/CD.
- Enhance reports with video recordings.
- Expand data-driven testing using Apache POI with Excel.

## Given more time, I would do the following :Expanding Test Coverage for an eCommerce Website

1. Write a Large Number of Test Cases
Functional Test Cases: Ensure all critical eCommerce features, such as product search, adding items to the cart, checkout, and payment processing, work as expected.
Boundary Test Cases: Focus on edge cases for things like input fields (e.g., entering the maximum number of characters in the search bar, applying a discount code, or checking product quantity limits).
Regression Test Cases: As new features are added (like new payment methods or a new shipping option), re-run tests on existing features to ensure nothing else is broken.

2. Include Edge Cases
Unexpected Inputs: Test how the site handles invalid inputs such as special characters, extremely long product names, or invalid coupon codes.
Product Availability: Ensure the website correctly handles scenarios where a product goes out of stock after it’s added to the cart or when the stock quantity is updated.
Shipping Address Validations: Test edge cases like incorrect address formats or unsupported shipping regions to ensure proper error handling.

3. Write Negative Test Cases
Invalid Payments: Test what happens when a user enters incorrect payment information or tries to process a payment without sufficient funds.
Account Login Failures: Simulate invalid login attempts or attempts to access restricted pages without proper authentication to ensure security measures are in place.
Checkout Failure: Test scenarios where the checkout process is interrupted, such as network issues, session timeouts, or unresponsive payment gateways.

4. Handle Multiple User Scenarios
Concurrency Testing: Test how the website performs when multiple users are browsing, searching, and checking out simultaneously, especially during high-traffic periods like sales events or holidays.
Session Management: Test for scenarios like users who abandon their shopping carts and come back later to ensure the session data persists correctly (e.g., cart items remain saved).

5. Cross-Browser & Cross-Platform Testing
Browser Compatibility: Test that the eCommerce site works well across different browsers (Chrome, Firefox, Safari, Edge), as customers could be using any browser to access your site.
Mobile Testing: Since many users shop from mobile devices, ensure your site is fully responsive and works smoothly on a variety of smartphones and tablets.

6. Test Different Environments
Test Across Devices: Make sure that the mobile version and desktop version of the website are both fully functional.
Payment Gateways: Verify that the integration with different payment systems (credit cards, PayPal, digital wallets) works seamlessly across multiple environments (test, staging, and production).

7. Performance and Load Testing
Load Testing: Simulate high traffic conditions to test how well the site performs during heavy traffic periods (e.g., Black Friday sales or seasonal promotions).
Stress Testing: Push the website beyond its normal load limits to see if it crashes or slows down, helping to identify system weaknesses.
Scalability Testing: Ensure the website can handle increased traffic, especially during peak hours like sales events, holiday seasons, or product launches.

8. Test Automation Maintenance
Update Test Cases Regularly: As the website adds new features (like a new payment method, product sorting option, or shipping preference), ensure test cases are updated to include these functionalities.
Reusable Code: Refactor test cases so they can be reused across different tests, making it easier to maintain and scale your automation efforts.
CI/CD Integration: Integrate your test cases into continuous integration/continuous deployment (CI/CD) pipelines to run automated tests each time a new change is made to the website.

9. Write Data-Driven Test Cases
Test With Multiple Data Sets: Use data-driven testing to cover multiple scenarios, such as different shipping addresses, various payment methods, and different product categories (clothing, electronics, home goods, etc.).
Testing Promotional Discounts: Ensure discount codes, sales prices, and promotions are applied correctly at checkout, including time-sensitive discounts.

10. Use Advanced Tools and Techniques
Parallel Test Execution: Run tests in parallel across multiple devices and browsers to speed up test execution, especially during high-volume test scenarios like checkout.
Mocking and Stubbing: Use mocking tools to simulate external services (like third-party payment gateways or inventory systems) to ensure that your tests don’t rely on these services being available all the time.

11. Collaborate with Developers
Discuss Test Coverage: Work closely with developers to ensure all parts of the eCommerce website are tested thoroughly, including critical areas like the cart functionality, order completion, and product recommendation engines.

12. Write Detailed Reports
Test Results Reporting: Generate detailed reports that show which tests have passed, failed, and why. This is crucial for both developers and business stakeholders.
Logs and Screenshots: Capture logs and screenshots in case of failures
