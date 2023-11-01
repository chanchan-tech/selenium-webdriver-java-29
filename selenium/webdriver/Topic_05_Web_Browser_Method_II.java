package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Method_II {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String Login_home_Url = driver.getCurrentUrl();
		Assert.assertEquals(Login_home_Url, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		String CreateAcc = driver.getCurrentUrl();
		Assert.assertEquals(CreateAcc, "http://live.techpanda.org/index.php/customer/account/create/");
		
	}

	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String Login_home_Title =driver.getTitle();
		Assert.assertEquals(Login_home_Title, "Customer Login");
		
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		String CreateAccTitle = driver.getTitle();
		Assert.assertEquals(CreateAccTitle, "Create New Customer Account");
			
	}

	@Test
	public void TC_03_Navigate_funtion() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		
		String CreateAcc = driver.getCurrentUrl();
		Assert.assertEquals(CreateAcc, "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		
		String Login_home_Url = driver.getCurrentUrl();
		Assert.assertEquals(Login_home_Url, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		String CreateAccTitle = driver.getTitle();
		Assert.assertEquals(CreateAccTitle, "Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Get_PageSource_Code() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
			
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}