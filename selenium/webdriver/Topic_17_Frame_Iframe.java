package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_17_Frame_Iframe {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver,15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		
		//Switch vao iframe cua facebook
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		String numberLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(numberLike, "165K followers");
		//switch ve lai trang parent
		driver.switchTo().defaultContent();
		
		//vao ifram chat
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		driver.findElement(By.cssSelector(".border_overlay")).click();
		driver.findElement(By.cssSelector(".input_name")).sendKeys("Huyen DTT");
		driver.findElement(By.cssSelector(".input_phone")).sendKeys("0936281628");
		
		selectItem_Editable_Dropdown("//select[@id='serviceSelect']","//select[@id='serviceSelect']/option","TƯ VẤN TUYỂN SINH");
		
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java");
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		driver.switchTo().defaultContent();
		
		//thao tac search
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//form[@id='search-form']//button")).click();
		List<WebElement> TitleBook = driver.findElements(By.xpath("//div[@class='content']//h4"));
		//Assert.assertTrue(TitleBook.getText().contains("excel"));
		
		for (WebElement title : TitleBook) {
			Assert.assertTrue(title.getText().contains("Excel"));
		}
		
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("automationtest");
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
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
	
	public void selectItem_Editable_Dropdown(String parentXpath, String childXpath, String expectedEextItem){
		//1. click vao textbox de no hien thi con tro chuot len
		driver.findElement(By.xpath(parentXpath)).click();
		SleepInsecond(2);
		
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedEextItem);
		SleepInsecond(2);
		
		//2. cho cho tat ca cac item duoc load ra het (WebdriverWait)
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		
		//3. neu muon chon 1 item nao do --> getText cua item do ra so sanh voi text ma minh mong muon
		// neu nhu bang voi cai minh muon thi scroll xuong va click vao -> thoat khoi vong lap
		// neu nhu chua bang thi duyet cai item tiep theo
		 for (WebElement item : allItems) {
			 System.out.println(item.getText());
			if(item.getText().equals(expectedEextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				SleepInsecond(2);
				item.click();
				//break;
			}
		}
		
		
		
		//4. kiem tra item do da duoc chon thanh cong
	}
	

}