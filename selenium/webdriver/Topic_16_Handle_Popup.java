package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_16_Handle_Popup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	
	public void TC_01_fixed_popup_in_dom() {
		driver.get("https://www.zingpoll.com");
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		By SinginPopup = By.cssSelector(".modal_dialog_custom");
		
		Assert.assertTrue(driver.findElement(SinginPopup).isDisplayed());
		SleepInsecond(3);
		driver.findElement(By.xpath("//input[@id='loginEmail']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//input[@id='loginPassword']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//button[@id='button-login']")).click();
		SleepInsecond(3);
		driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
		//Assert.assertFalse(isElementDisplayed(SinginPopup));
	}

	@Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		if(isElementDisplayed(By.xpath("//div[@id='tve-p-scroller']"))) {
			driver.findElement(By.xpath("//a[@class='tve_p_lb_close']")).click();
			SleepInsecond(2);
		}
		
		
		
	}

	@Test
	public void TC_03_() {

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
	
	public boolean isElementDisplayed(By by) {
		try {
			//no se tim trong vong 10s
			//khi nao het 10s moi throw exception
			return driver.findElement(by).isDisplayed();
		}catch(Exception e) {
			//cach bat exception lai
			return false;
		}
	}
	

}