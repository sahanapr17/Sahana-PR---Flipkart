🛒 Flipkart Automation using Selenium Java

This project automates the end-to-end user flow of searching and adding a product to the cart on Flipkart using Selenium WebDriver with Java.

The script performs product search, applies filters (Brand: boAt, Rating: 4★ & above), sorts results by price (Low to High), opens the first product in a new tab, checks available offers, and handles product availability scenarios.

If the product is available, it adds the item to the cart, navigates to the cart page, waits for complete page load, and captures a screenshot. If the product is unavailable, it logs an appropriate message and still captures a screenshot of the product page.

This project demonstrates real-time automation concepts like dynamic waits, tab handling, JavaScript scrolling/clicking, conditional flows, and screenshot handling.

Tech Stack: Java • Selenium WebDriver • ChromeDriver • Eclipse • Apache Commons IO

▶️ How to Run This Project

Follow the below steps to run the automation script:

1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/flipkart-automation.git
```

2️⃣ Open Project in Eclipse

* Open Eclipse IDE
* Click **File → Open Projects from File System**
* Select the cloned project folder

3️⃣ Add Selenium Dependencies

* Download Selenium Java JAR files
* Right Click Project → **Build Path → Configure Build Path → Add External JARs**
* Add all Selenium JARs

4️⃣ Add ChromeDriver

* Download ChromeDriver matching your Chrome version
* Place it inside the `drivers` folder
* Update path in code:

```java
System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
```

5️⃣ Run the Script

* Open `FlipkartTest.java`
* Right Click → **Run As → Java Application**

6️⃣ View Output

* Console will show execution logs
* Screenshot will be saved inside:

```
selenium_project/screenshot/
```
