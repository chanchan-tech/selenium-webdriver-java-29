package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_15_Interface_Part_II {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		SleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	@Test
	public void TC_02_Drag_and_drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		Assert.assertEquals(driver.findElement(By.xpath("droptarget")).getText(), "You did great!");
		//#03a9f4
		//Assert.assertEquals(bigCircle.getCssValue("background-color"),"#03a9f4");
		
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