package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS_Path_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void Login_TC_01_Empty_Email_And_Password() {
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='Email-error']")).getText(), "Please enter your email");
		
	}

	@Test
	public void Login_TC_02_Invalid_Email_Address() {
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("123@134");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='message-error']")).getText(), "Please enter your email");
	}

	@Test
	public void Login_TC_03_Invalid_Password() {
		
	}
	
	@Test
	public void Login_TC_04_Invalid_Incorrect_Password() {
		
	}
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}