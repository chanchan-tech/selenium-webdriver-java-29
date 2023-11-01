package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_13_Alerts {
	WebDriver driver;
	Alert alert;
	WebDriverWait expicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_Alert_Accept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// vua wait vua switch vao
		alert = expicitWait.until(ExpectedConditions.alertIsPresent());
		//get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//accept alert
		alert.accept();
		SleepInsecond(3);
		
		//Verify message tra ve
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
	}

	
	public void TC_02_Comfirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		// vua wait vua switch vao
		alert = expicitWait.until(ExpectedConditions.alertIsPresent());
		//get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//accept alert
		alert.accept();
		SleepInsecond(3);
		
		//Verify message tra ve
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Ok");
		
		
		//cancel alert
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		// vua wait vua switch vao
		alert = expicitWait.until(ExpectedConditions.alertIsPresent());
		//get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//cancel alert
		alert.dismiss();
		SleepInsecond(3);
		
		//Verify message tra ve
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
		
	}

	
	public void TC_03_JSPrompt() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		// vua wait vua switch vao
		alert = expicitWait.until(ExpectedConditions.alertIsPresent());
		//get text de verify
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//cancel alert
		alert.sendKeys("Selenium Alert");
		alert.accept();
		SleepInsecond(3);
		
		//Verify message tra ve
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Selenium Alert");
		
	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//p")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(passInforUrl(basicAuthenUrl));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//p")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	
	public String passInforUrl(String url) {
		String[] Urlvalue = url.split("//");
		url = Urlvalue[0] + "//" + "admin"+":"+"admin"+"@"+ Urlvalue[1];
		return url;
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