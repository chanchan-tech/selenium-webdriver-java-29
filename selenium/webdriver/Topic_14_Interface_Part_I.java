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

public class Topic_14_Interface_Part_I {
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

	public void TC_01_Hover_1() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		//hover vào element
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		SleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	}


	public void TC_02_Hover_2() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		SleepInsecond(2);
		action.click(driver.findElement(By.xpath("//a[text()='Toys']"))).perform();
		//driver.findElement(By.xpath("//a[text()='Toys']")).click();
		SleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Toys Store']")).isDisplayed());
		
	}

	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector(".ui-selectee"));
		//index 0-11
		//element: 1-12
		action.clickAndHold(listNumber.get(0))  //click vao o so 1 chua nha chuot trai ra
		.moveToElement(listNumber.get(3))		//keo den o so 4
		.release()								// Nha chuot trai ra	
		.perform();								// Thuc thi
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(), 4);
	}
	
	@Test
	public void TC_04_Click_And_Hold_2() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector(".ui-selectee"));
		//index 0-11
		//element: 1-12
		action.keyDown(Keys.CONTROL).perform();  //click vao o so 1 chua nha chuot trai ra
		action.click(listNumber.get(0))		//keo den o so 4
		.click(listNumber.get(5))
		.click(listNumber.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(), 3);
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