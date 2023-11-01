package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.Assert;

public class Topic_10_Default_Dropdown {
	WebDriver driver;
	Select dayDropdown, monthDropdown, yearDropdown;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='gender-female']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Dang");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Thi Thu Huyen");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(getRandomEmail());
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Cong ty cong nghe Sotatek");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");

		// khoi tao
		dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));
		
		// chon ngay 31
		dayDropdown.selectByVisibleText("31");
		//Kiem tra da chon duoc ngay 31 hay chua
		Assert.assertEquals(dayDropdown.getFirstSelectedOption().getText(), "31");
		monthDropdown.selectByVisibleText("October");
		yearDropdown.selectByVisibleText("1994");
		
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		//SleepInsecond(20);
		
		//Assert.assertEquals(driver.findElement(By.cssSelector("//div[class='result']")).getText(), "Your registration completed");
	}

	@Test
	public void TC_02_() {
		// Login Page title
		
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "huyendtt" + rand.nextInt(9999999) + "@gmail.com";
	}
	
	public void SleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}