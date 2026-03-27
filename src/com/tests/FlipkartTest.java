package com.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class FlipkartTest {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"D:\\selenium_workspace\\selenium_project\\drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.flipkart.com");

		Thread.sleep(3000);

		// CLOSE LOGIN POPUP
		try {
			driver.findElement(By.xpath("//button[text()='✕']")).click();
		} catch (Exception e) {
			System.out.println("Popup not present");
		}

		// SEARCH
		driver.findElement(By.name("q")).sendKeys("Bluetooth Speakers");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		Thread.sleep(4000);

		driver.findElement(By.xpath("//div[contains(text(),'Brand')]")).click();

		// BRAND FILTER boAt
		driver.findElement(By.xpath("//div[text()='boAt']")).click();

		Thread.sleep(4000);

		// RATING FILTER
		driver.findElement(By.xpath("//div[text()='4★ & above']")).click();

		Thread.sleep(4000);

		// SORT LOW TO HIGH
		driver.findElement(By.xpath("//div[text()='Price -- Low to High']")).click();

		Thread.sleep(4000);

		// Click first product
		WebElement firstProduct1 = driver.findElement(By.xpath("(//a[contains(@href,'/p/')])[1]"));

		String parent1 = driver.getWindowHandle();
		firstProduct1.click();

		// Switch to new tab
		Set<String> tabs1 = driver.getWindowHandles();
		for (String tab : tabs1) {
			if (!tab.equals(parent1)) {
				driver.switchTo().window(tab);
			}
		}

		Thread.sleep(4000);

		// Check Available Offers
		try {
			WebElement offersTitle = driver.findElement(By.xpath("//span[text()='Available offers']"));

			if (offersTitle.isDisplayed()) {

				List<WebElement> offersList = driver.findElements(By.xpath("//li[contains(@class,'_1R3GMa')]"));

				System.out.println("Number of offers = " + offersList.size());
			}

		} catch (Exception e) {
			System.out.println("Available offers section not present");
		}

		Thread.sleep(3000);

		// CHECK OFFERS
		try {
			List<WebElement> offers = driver.findElements(By.xpath("//li[contains(@class,'_1R3GMa')]"));
			System.out.println("Number of offers = " + offers.size());
		} catch (Exception e) {
			System.out.println("Offers not present");
		}

		try {

			WebElement addToCart = driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));

			if (addToCart.isDisplayed() && addToCart.isEnabled()) {

				addToCart.click();

				// TAKE SCREENSHOT FIRST
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				src.renameTo(new File("cart_result.png"));

				System.out.println("Product added successfully");

			} else {

				System.out.println("Product unavailable — could not be added to cart.");

				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				src.renameTo(new File("result.png"));
			}

		} catch (Exception e) {

			System.out.println("Product unavailable — could not be added to cart.");

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			src.renameTo(new File("result.png"));
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		try {

		    // Wait till Add To Cart visible
		    WebElement addToCart = wait.until(
		            ExpectedConditions.visibilityOfElementLocated(
		                    By.xpath("//div[contains(text(),'Add to cart')]")));

		    // Scroll to button
		    ((JavascriptExecutor)driver).executeScript(
		            "arguments[0].scrollIntoView(true);", addToCart);

		    Thread.sleep(2000);

		    // Click using JS (Most Powerful Click)
		    ((JavascriptExecutor)driver).executeScript(
		            "arguments[0].click();", addToCart);

		    System.out.println("Add To Cart Clicked");

		} catch (Exception e) {

		    System.out.println("Add To Cart not clickable / Product unavailable");
		}
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

		Thread.sleep(2000);   // small wait after Add To Cart

		// Step 1: Check if already redirected to cart page
		if(driver.getCurrentUrl().contains("viewcart")) {

		    System.out.println("Already on Cart Page");

		} else {

		    try {

		        // Step 2: Wait for Go To Cart button
		        WebElement goToCart = wait1.until(
		                ExpectedConditions.elementToBeClickable(
		                        By.xpath("//div[contains(text(),'Go to cart')]")));

		        // Scroll to button
		        ((JavascriptExecutor)driver).executeScript(
		                "arguments[0].scrollIntoView(true);", goToCart);

		        Thread.sleep(1000);

		        // JS Click (most stable)
		        ((JavascriptExecutor)driver).executeScript(
		                "arguments[0].click();", goToCart);

		        System.out.println("Clicked Go To Cart");

		    } catch (Exception e) {

		        System.out.println("Go To Cart button not found — maybe auto redirected");
		    }
		}

		Thread.sleep(4000);

		// Step 3: Take Screenshot of Cart Page
		// WAIT till Cart URL loads
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait2.until(ExpectedConditions.urlContains("viewcart"));

		// WAIT till Page Completely Loaded (Very Important)
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 0; i < 10; i++) {

		    String state = js.executeScript("return document.readyState").toString();

		    if (state.equals("complete")) {
		        break;
		    }

		    Thread.sleep(6000);
		}

		System.out.println("Cart Page Fully Loaded");

		// TAKE SCREENSHOT
		Thread.sleep(10000);
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File dest = new File("D:\\selenium_workspace\\selenium_project\\screenshot\\cart_result.png");

		src.renameTo(dest);

		System.out.println("Screenshot Saved Successfully");
		// QUIT ONLY AT END
		 driver.quit();
	}
}