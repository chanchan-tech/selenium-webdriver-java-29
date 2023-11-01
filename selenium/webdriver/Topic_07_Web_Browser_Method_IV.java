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

public class Topic_07_Web_Browser_Method_IV {
	WebDriver driver;
	
	By UserMail = By.xpath("//input[@name='user_email']");
	By Under18 = By.xpath("//input[@id='under_18']");
	By Education = By.xpath("//textarea[@id='edu']");
	By NameUser = By.xpath("//h5[text()='Name: User1']");
	By Job1 = By.xpath("//select[@id='job1']");
	By Job2 = By.xpath("//select[@id='job2']");
	By Development = By.xpath("//input[@id='development']");
	By Slider_1 = By.xpath("//input[@id='slider-1']");
	
	By Disable_Pass = By.xpath("//input[@id='disable_password']");
	By Disable_radio = By.xpath("//input[@id='radio-disabled']");
	By Job3 = By.xpath("//select[@id='job3']");
	By Disable_bio = By.xpath("//textarea[@id='bio']");
	By Check_disable = By.xpath("//input[@id='check-disbaled']");
	By Slide_2 = By.xpath("//input[@id='slider-2']");

	By Java = By.xpath("//input[@id='java']");
	
	By My_Acc = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By Username = By.xpath("//input[@id='email']");
	By Password = By.xpath("//input[@id='pass']");
	By Button_Login = By.xpath("//button[@id='send2']");
	
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	//@Test
	public void TC_01_Element_Isdisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(Element_isDisplay(UserMail));
		Element_Senkey(UserMail,"Automation Testing");
		
		Assert.assertTrue(Element_isDisplay(Under18));
		Element_Click(Under18);
		
		Assert.assertTrue(Element_isDisplay(Education));
		Element_Senkey(Education,"Automation Testing");
		
		Assert.assertFalse(Element_isDisplay(NameUser));			
		
	}

	//@Test
	public void TC_02_Element_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//cac phan tu enable tren trang
		Assert.assertTrue(Element_isEnabled(UserMail));
		Assert.assertTrue(Element_isEnabled(Under18));
		Assert.assertTrue(Element_isEnabled(Education));
		Assert.assertTrue(Element_isEnabled(Job1));
		Assert.assertTrue(Element_isEnabled(Job2));
		Assert.assertTrue(Element_isEnabled(Development));
		Assert.assertTrue(Element_isEnabled(Slider_1));

		
		//cac phan tu disable tren trang
		Assert.assertFalse(Element_isEnabled(Disable_Pass));
		Assert.assertFalse(Element_isEnabled(Disable_radio));
		Assert.assertFalse(Element_isEnabled(Job3));
		Assert.assertFalse(Element_isEnabled(Disable_bio));
		Assert.assertFalse(Element_isEnabled(Check_disable));
		Assert.assertFalse(Element_isEnabled(Slide_2));	

	}

	//@Test
	public void TC_03_Element_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Element_Click(Under18);
		Element_Click(Java);
		
		Assert.assertTrue(Element_isSelected(Under18));
		Assert.assertTrue(Element_isSelected(Java));
		Element_Click(Java);
		Assert.assertFalse(Element_isSelected(Java));
	}
	
	
	@Test
	public void TC_04_Login_empty_Email_Pass() {
		driver.get("http://live.techpanda.org/");
		//
		Element_Click(My_Acc);
		Element_Senkey(Username, "");
		Element_Senkey(Password, "");
		Element_Click(Button_Login);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@id = 'advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@id = 'advice-required-entry-pass']")).getText(), "This is a required field.");
		
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