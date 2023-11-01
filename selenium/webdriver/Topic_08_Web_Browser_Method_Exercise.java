package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Browser_Method_Exercise {
	WebDriver driver;
	
	By My_Acc = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By Username = By.xpath("//input[@id='email']");
	By Password = By.xpath("//input[@id='pass']");
	By Button_Login = By.xpath("//button[@id='send2']");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	
	@Test
	public void TC_01_Empty_Email_Pass() {
		driver.get("http://live.techpanda.org/");
		Element_Click(My_Acc);
		Element_Senkey(Username, "");
		Element_Senkey(Password, "");
		Element_Click(Button_Login);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@id = 'advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@id = 'advice-required-entry-pass']")).getText(), "This is a required field.");
		SleepInsecond(5);
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		Element_Click(My_Acc);
		Element_Senkey(Username, "1234@1234");
		Element_Senkey(Password, "123456");
		Element_Click(Button_Login);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@id = 'advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		SleepInsecond(5);
	}
	
	@Test
	public void TC_03_Pass_under6_char_Password() {
		driver.get("http://live.techpanda.org/");
		Element_Click(My_Acc);
		Element_Senkey(Username, "huyendtt@gmail.com");
		Element_Senkey(Password, "12345");
		Element_Click(Button_Login);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@id = 'advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		SleepInsecond(5);
	}
	
	@Test
	public void TC_04_Incorrect_Email_and_Pass() {
		driver.get("http://live.techpanda.org/");
		Element_Click(My_Acc);
		Element_Senkey(Username, "huyendtt@gmail.com");
		Element_Senkey(Password, "123456");
		Element_Click(Button_Login);
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='login-form']//preceding-sibling::ul//span")).getText(), "Invalid login or password.");
		SleepInsecond(5);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean Element_isDisplay(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by +"- is Display");
			return true;
		} else {
			System.out.println(by +"- is not Display");
			return false;
		}
	}
	
	public boolean Element_isEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by +"- is Enabled");
			return true;
		} else {
			System.out.println(by +"- is not Enable");
			return false;
		}
	}
	
	public boolean Element_isSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println(by +"- is Selected");
			return true;
		} else {
			System.out.println(by +"- is not Selected");
			return false;
		}
	}
	
	public void Element_Click(By by) {
		driver.findElement(by).click();
	}
	
	public void Element_Senkey(By by, String value) {
		driver.findElement(by).sendKeys(value);
	}	
	
	
	public void SleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}