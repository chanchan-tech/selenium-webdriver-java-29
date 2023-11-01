package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_12_Button_Radio_Checkbox {
	WebDriver driver;
	WebElement element;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	By loginTab = By.xpath("//a[contains(text(),'Đăng nhập')]");
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");
	By emailTextbox = By.xpath("//input[@id='login_username']");
	By passwordTextbox = By.xpath("//input[@id='login_password']");
	

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}


	public void TC_01_() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(loginTab).click();
		
		element = driver.findElement(loginButton);
		
		//verify button login disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		//verify button login disable la mau xam
		String ColorBackground_Disable = element.getCssValue("background");
		Assert.assertEquals(ColorBackground_Disable, "rgba(0, 0, 0, 0) linear-gradient(90deg, rgb(224, 224, 224) 0%, rgb(224, 224, 224) 100%) repeat scroll 0% 0% / auto padding-box border-box");
		
		
		driver.findElement(emailTextbox).sendKeys("danghuyenit10@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123456");
		//verify button login enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		//verify button login disable la mau xam
		String ColorBackground_Enable = element.getCssValue("background");
		Assert.assertEquals(ColorBackground_Enable, "rgba(201, 33, 39, 0.345) none repeat scroll 0% 0% / auto padding-box border-box");
		
		//remove attribute disable bang JS
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", loginButton);
		driver.findElement(loginButton).click();
		
		
	}


	public void TC_02_Checkbox_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).click();
		SleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected());
		
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).click();
		SleepInsecond(3);
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input")).isSelected());
	
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input")).click();
		SleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input")).isSelected());
		
	}


	public void TC_03_Select_All_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		List<WebElement> All_Checkbox = driver.findElements(By.xpath("//input[@type='checkbox' and not (@disable)]"));
		
		//chon tat ca cac checkbox
		for (WebElement checkbox : All_Checkbox) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				SleepInsecond(2);
			}
			Assert.assertTrue(checkbox.isSelected());
		}
		
		// Bo chon tat ca cac checkbox
		for (WebElement checkbox : All_Checkbox) {
			if(checkbox.isSelected()) {
				checkbox.click();
				SleepInsecond(2);
			}
			Assert.assertFalse(checkbox.isSelected());
		}
	}

	public void TC_04_Custom_Radio_Checkbox() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		//radio button the input bi an nen khong click duoc, phai dung javascript de click sau do verify
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input")));
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div//input")).isSelected());
		
	}
	
	@Test
	public void TC_05_Custom_Radio_Checkbox_Google_Form() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		driver.findElement(By.xpath("//div[@aria-label='Hà Nội']")).click();
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @aria-checked='true']")).isDisplayed());
		
//		List<WebElement> ListCheckbox = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng')and @role='checkbox' and @aria-checked='false']//div[@class='uHMk6b fsHoPb']"));
//		for (WebElement checkbox : ListCheckbox) {
//			if(!checkbox.isDisplayed()) {
//				checkbox.click();
//			}
//			Assert.assertTrue(driver.findElement(By.xpath("//div[starts-with(@aria-label,'Quảng')and @role='checkbox' and @aria-checked='true']")).isDisplayed());
//		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void SleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "huyendtt" + rand.nextInt(9999999) + "@gmail.com";
	}

}