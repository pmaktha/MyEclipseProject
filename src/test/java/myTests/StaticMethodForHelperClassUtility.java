package myTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class StaticMethodForHelperClassUtility {
	
	public static WebDriver getDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			return new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			return new EdgeDriver();
		}else {
			 throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		
	}

}
