package myTests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();//it will open alert
		//1st way of approach to using alert class
		
		 //Alert alert = driver.switchTo().alert(); 
		 //Thread.sleep(3000); 
		 //alert.accept();
		 
		
		//2nd way of approach using Explicit wait
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//alert.accept();
		
		//3rd way of approach using JavaScriptExecutor
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		try {
			jse.executeScript("Window.alert=function{};");
		}catch(Exception e) {
			System.out.println("Alert closed");
		}	
		
	}

}
