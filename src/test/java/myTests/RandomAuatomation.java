package myTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RandomAuatomation {
	
	WebDriver driver;
	
	@Test
	public void nasdaqTest() {
		driver = new ChromeDriver();
		driver.get("https://www.nasdaq.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/child::button")).click();
		WebElement table = driver.findElement(By.xpath("(//table[@class='jupiter22-c-symbol-table__table jupiter22-3-column-layout'])[1]"));
		List<WebElement> rows = table.findElements(By.xpath("//table/tbody/tr[@class='jupiter22-c-symbol-table__row jupiter22-c-up-arrow']"));
		for(WebElement row:rows) {
			List<WebElement> cells = row.findElements(By.xpath("//tr[@class='jupiter22-c-symbol-table__row jupiter22-c-up-arrow']/td"));
			 for (WebElement cell : cells) {
	                System.out.print(cell.getText() + "\t"); // Print cell data with a tab space
	                
	            }
	            System.out.println(); // Move to the next line after each row
	        }
		driver.quit();
		
		}
	
	

}
