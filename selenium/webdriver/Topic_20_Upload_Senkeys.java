package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_20_Upload_Senkeys {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");
	String googleFileName = "Google.JPG";
	String facebookFileName = "Facebook.JPG";
	String appleFileName = "Apple.JPG";
	String googleFilePath = projectPath + "\\Upload\\" + googleFileName;
	String facebookFilePath = projectPath + "\\Upload\\" + facebookFileName;
	String appleFilePath = projectPath + "\\Upload\\" + appleFileName;

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Sendkey() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@type='file']")).isDisplayed());
		
		//Load file khong can mo open file dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath);
		
		//click start upload button
		driver.findElement(By.cssSelector(".start")).click();
		
		//verify upload success
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name']//a")).getText(), googleFileName);
	}

	@Test
	public void TC_02_Mutiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Load file khong can mo open file dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath + "\n" + facebookFilePath + "\n" + appleFilePath);
		
		//verify before upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + googleFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + facebookFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + appleFileName + "']")).isDisplayed());
		
		
		//click start upload button
		List<WebElement> startUploadButton =  driver.findElements(By.cssSelector("table .start"));
		
		for (WebElement startButton : startUploadButton) {
			startButton.click();
			SleepInsecond(2);
		}
		
		//verify upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + googleFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[ext()='" + facebookFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + appleFileName + "']")).isDisplayed());
		
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		//ddriver.quit();
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