package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_18_Window_TAB {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}


	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//click vao google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		SwitchTabByTitle("Google");
		SleepInsecond(2);
		//driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		
		//switch vao parent tab
		SwitchTabByTitle("Selenium WebDriver");
		
		//click vao facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		SleepInsecond(2);
		SwitchTabByTitle("Facebook – log in or sign up");
		
		//switch vao parent tab
		SwitchTabByTitle("Selenium WebDriver");
		
		
		
		
	}

	
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		String ParentID = driver.getWindowHandle();
		SwitchTabByTitle("Kyna.vn | Facebook");
		SleepInsecond(2);
		//switch qua parent page
		SwitchTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		SleepInsecond(2);
		
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		SwitchTabByTitle("Kyna.vn - YouTube");
		SleepInsecond(2);
		driver.findElement(By.xpath("//*[@id='search']")).sendKeys("Selenium");
		SleepInsecond(2);
		driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
		SleepInsecond(2);
		
		closeAllTab_WithoutParent(ParentID);
		
		
	}

	@Test
	public void TC_03_Liveguru() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();
		//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']
		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		String ParentID = driver.getWindowHandle();
		//SwitchTab(ParentID);
		SwitchTabByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		SleepInsecond(3);
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public void SleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void SwitchTab(String TabID) {
		Set<String> ListTab = driver.getWindowHandles();
		System.out.println(ListTab.size());
		for (String id : ListTab) {
			if(!id.equals(TabID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void SwitchTabByTitle(String tabTitle) {
		Set<String> ListTab = driver.getWindowHandles();
		for (String Id : ListTab) {
			driver.switchTo().window(Id);
			String ActualTitle = driver.getTitle();
			if(ActualTitle.equals(tabTitle)) {
				break;
			}
			
		}
	}
	
	public void closeAllTab_WithoutParent(String ParentID) {
		Set<String> ListTab = driver.getWindowHandles();
		for (String Id : ListTab) {
			if(!Id.equals(ParentID)) {
				driver.switchTo().window(Id);
				driver.close();
			}
			driver.switchTo().window(ParentID);
		}
	}
	
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "huyendtt" + rand.nextInt(9999999) + "@gmail.com";
	}

}