package myTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class CrossBrowserTesting {
	
	    WebDriver driver;

	    // Step 1: Create a DataProvider method for browser names
	    @DataProvider(name = "browserProvider")
	    public Object[][] crossBrowsers() {
	        return new Object[][] {
	            {"chrome"},
	            {"edge"}
	        };
	    }

	    // Step 2: Create a Test Method that uses @DataProvider
	    @Test(dataProvider = "browserProvider")
	    public void testOnMultipleBrowsers(String browser) {
	        // Initialize WebDriver based on the browser parameter
	        if (browser.equalsIgnoreCase("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        }
	        // Step 3: Perform some action - open a webpage
	        driver.get("https://www.cakebites.com/");
	        driver.manage().window().maximize();
	        System.out.println("Page Title is: " + driver.getTitle());

	        // Close the driver at the end of the test
	        driver.quit();
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

