package myTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Iframes {
	
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://ui.vision/demo/webtest/frames/");
		driver.manage().window().maximize();
		
		//1st way of approach using switchTo frames
		WebElement frame1=driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Entered text in frame1");
		
		//2nd way of approach using Explicit Wait
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("//frame[@src='frame_1.html']"));
		//driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Entered text in frame1 using ExplicitWait");
		
	}

}
