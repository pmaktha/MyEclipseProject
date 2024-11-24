package myTests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumAllCommands {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@AfterMethod(enabled = true)
	public void tearDown() {
		driver.quit();
	}

	@Test(enabled = false)
	public void allLinks() throws Exception {
		driver.get("https://www.apple.com/");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		List<WebElement> images = driver.findElements(By.tagName("img"));
		int totalimages = images.size();
		int totallinks = links.size();
		System.out.println("total number of links:" + totallinks + "\ntotal number of images:" + totalimages);
		Thread.sleep(2000);
		WebElement mac = driver.findElement(By.xpath("//a[@class='unit-link']/following::h2[contains(.,'Mac mini')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(mac).click().perform();
		String currenturl = driver.getCurrentUrl();
		String Expectedurl = "https://www.apple.com/mac-mini/";
		Assert.assertEquals(currenturl, Expectedurl, "Both urls are not matching");
		boolean macmini = driver.findElement(By.xpath("//h1[normalize-space()='Mac mini']")).isDisplayed();
		System.out.println(macmini);
		// System.out.println(driver.getPageSource());
	}

	@Test(enabled = false)
	public void checkboxTest() {
		driver.get("https://testautomationpractice.blogspot.com/");
		WebElement male = driver.findElement(By.xpath("//input[@value='male']"));
		WebElement female = driver.findElement(By.xpath("//input[@value='female']"));
		// boolean m = male.isDisplayed();
		if (male.isDisplayed() && female.isDisplayed()) {
			male.click();
			female.click();

			WebElement chkboxFriday = driver.findElement(By.xpath("//input[@value='friday']"));
			if (chkboxFriday.isDisplayed()) {
				chkboxFriday.click();
			}
		}
	}

	@Test
	public void synchronization() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123", Keys.ENTER);
	}

	@Test
	public void naviagtionalCommandsTest() {
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.navigate().back();
		System.out.println(driver.getCurrentUrl());
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys("Prashanth");
		WebElement Nametxt = driver.findElement(By.xpath("//label[contains(.,'me:')]"));
		System.out.println(Nametxt.getText());
		driver.navigate().forward();
		String forwardtitle = driver.getCurrentUrl();
		System.out.println(forwardtitle);
		driver.navigate().back();
		String backwardtitle = driver.getCurrentUrl();
		System.out.println(backwardtitle);
		driver.navigate().refresh();

	}

	@Test
	public void HandleBrowserWindows() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//a[contains(.,'Inc')]")).click();
		Set<String> windowIds = driver.getWindowHandles();
		// Approach 1:
		/*
		 * List<String> windowList = new ArrayList(windowIds); String
		 * parentID=windowList.get(0); String childID=windowList.get(1);
		 * driver.switchTo().window(childID); System.out.println(driver.getTitle());
		 * driver.switchTo().window(parentID); System.out.println(driver.getTitle());
		 */

		// Approach2
		for (String winID : windowIds) {
			String title = driver.switchTo().window(winID).getTitle();
			if (title.equals("OrangeHRM")) {
				System.out.println(driver.getCurrentUrl());
			}
			driver.close();
		}

	}

	@Test
	public void assignmentOnWindowHandlesTest() {
		driver.get("https://testautomationpractice.blogspot.com/");
		String parentHandle = driver.getWindowHandle();
		System.out.println("parent window : " + parentHandle);
		driver.findElement(By.xpath("//span[@class='wikipedia-input-box']//child::input")).sendKeys("Selenium",
				Keys.ENTER);
		List<WebElement> links = driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']"));
		for (WebElement link : links) {
			 System.out.println(link.getText()); 
			 WebElement selenium =driver.findElement(By.xpath("//a[contains(.,'Selenium')]")); 
			if(link.getText().contains("Selenium")) 
				 
					 
			 selenium.click(); 
			 
			driver.switchTo().window(parentHandle);
			
			if(link.getText().contains("Selenium in biology")) 
				 
				 
				 selenium.click(); 
			 
			
		
		Set<String> childhandles = driver.getWindowHandles();
		for (String handle : childhandles) {
			System.out.println("child handles: " + handle);
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				if (driver.getTitle().equals("Selenium - Wikipedia")) {
					driver.close();
				}
			}
		}
	}
	}
}
