package myTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SeleniumInterviewPrepScenarios {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void dynamicWebElementsTest() {
		driver.get("https://www.cakebites.com/");
		// driver.findElement(By.xpath("((//button//*[local-name()='path'])[2]")).click();
		driver.findElement(By.xpath("//button[starts-with(.,'Allow a')]")).click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,350)", "");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement frostedChocolate = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Frosted Chocolate')]")));
		frostedChocolate.click();
		WebElement frostedChocolate1 = driver.findElement(By.xpath("//h1[contains(.,'Frosted')]"));
		System.out.println(frostedChocolate1.getText());
		if (frostedChocolate1.isDisplayed()) {
			driver.navigate().back();
			String actualTitle = driver.getTitle();
			System.out.println(actualTitle);
			String expectedTitle = "The Original CakeBites – The World’s Best Rainbow Cookies is now available on the go! Classic Italian Rainbow, Ultimate Party Cake & Cinna-Crumb flavors now available!, rainbow cookies";
			Assert.assertEquals(actualTitle, expectedTitle, "Titles are not matching");
		}

		// sql queries
		// service now
		// confident
	}

	@Test()
	public void softAssertionTest() {
		driver.get("https://www.google.com");
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(driver.getTitle(), "Googl", "Titles are not matching");
		driver.findElement(By.id("APjFqb")).sendKeys("Selenium Testing", Keys.ENTER);
		driver.findElement(By.xpath("//h3[starts-with(.,'Selenium W')]")).click();
		softassert.assertEquals(driver.getTitle(), "Seleniu", "Titles are not matching");
		boolean txt = driver.findElement(By.xpath("//span[contains(.,'Down')]")).isDisplayed();
		softassert.assertEquals(txt, false);
		System.out.println(softassert.getClass());
		softassert.assertAll();
	}

	@Test()
	public void youtubeVideoPlayTest() throws InterruptedException {
		driver.get("https://www.youtube.com");
		Thread.sleep(3000);
		WebElement search = driver.findElement(By.xpath("//input[@id='search']"));
		if (search.isDisplayed() && search.isEnabled()) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(search).perform();
				search.sendKeys("pushpa 2 trailer telugu", Keys.ENTER);
			} finally {
				driver.findElement(By.xpath("(//a[contains(@title,'Pushpa 2 The Rule Trailer (Telugu)')])[1]")).click();
				Thread.sleep(6000);
				driver.findElement(By.xpath("//div[text()='Skip']")).click();
				driver.findElement(By.xpath("//button[@title='Full screen (f)']")).click();
			}
		} else {
			System.out.println("search is not displayed");
		}
	}
	
	@Test()
	public void incognitoModeTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.get("https://www.google.com");
		WebDriver driver = new EdgeDriver();
		EdgeOptions options1 = new EdgeOptions();
		driver = new EdgeDriver(options1);
		options1.addArguments("--inprivate");
		driver.get("https://www.google.com");
	}
	
	//Q:How to find all the links on the page?
	@Test
	public void getAllLinksInaWebPageTest() {
		driver.get("https://www.cakebites.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement link: links ){
			System.out.println(link.getAttribute("href"));
		}
	}
	
	//Q:How to take screenshots in Selenium?
	@Test
	public void takesScreenshotTest() throws IOException {
		driver.get("https://www.cakebites.com/");
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("C:\\Users\\Prash\\eclipse-workspace\\SeleniumChallenges\\src\\test\\resources\\screenshots\\cakebiteshomepage.png");
		FileUtils.copyFile(srcFile, destFile);
	}
	
	@Test
	public void windowHandlesTest() {
		driver.get("https://www.hyrtutorials.com/");
		WebElement seleniumpractice = driver.findElement(By.xpath("//a[contains(.,'Selenium Practice')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(seleniumpractice).perform();
		WebElement windowHandles=driver.findElement(By.xpath("//a[normalize-space()='Window Handles']"));
		actions.moveToElement(windowHandles).click();
	}
	
	//Q:How to handle certification(SSLErrors) in Selenium?
	@Test
	public void handleSSLErrorTest() {
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);// Accept insecure certificates
		driver = new ChromeDriver(options);
		driver.get("https://expired.badssl.com/");
		System.out.println("Title: " + driver.getTitle());
		Sysytem.out.println("Title is printed above");
	}

}
