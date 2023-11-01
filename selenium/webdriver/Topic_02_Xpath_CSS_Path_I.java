package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS_Path_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		// để thao tác với 1 element ở trên page
		driver.findElement(By.id("FirstName")).sendKeys("Nguyen");
		driver.findElement(By.id("LastName")).sendKeys("Hong Vinh");
		driver.findElement(By.id("Email")).sendKeys("nguyenhongvinh@gmail.com");
	}

	@Test
	public void TC_02_ClassName() {
		driver.findElement(By.className("search-box-text")).sendKeys("Lumia");;
		
	}

	@Test
	public void TC_04_Tagname() {
		//đếm số thẻ tagname
		System.out.println(driver.findElements(By.tagName("Select")).size());
	}
	
	@Test
	public void TC_05_Name() {
		driver.findElement(By.name("Password")).sendKeys("huyen12:");
	}
	
	@Test
	public void TC_06_Linktext() {
		//driver.findElement(By.linkText("")).click();
		//link text gia tri la tuyet doi
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
	}
	
	@Test
	public void TC_07_Partial_Linktext() {
		//link text gia tri la tuyet doi
		Assert.assertTrue(driver.findElement(By.partialLinkText("Digital")).isDisplayed());
	}
	
	@Test
	public void TC_08_css() {
		driver.findElement(By.cssSelector("#Company")).sendKeys("123123");
	}

	@Test
	public void TC_09_Xpath() {
		driver.findElement(By.xpath("//input[@id='Company']")).clear();
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("123123");
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}