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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_11_Custom_Dropdown_I {
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
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//hanh vi chon 1 item trong dropdown
		selectItem_customDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","5");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		
		selectItem_customDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","19");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		
		
	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItem_customDropdown("//div[@id='root']//div[@role='listbox']", "//div[@class='visible menu transition']/div", "Justen Kitsune");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()= 'Justen Kitsune']")).isDisplayed());
		
		selectItem_customDropdown("//div[@id='root']//div[@role='listbox']", "//div[@class='visible menu transition']/div", "Elliot Fu");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()= 'Elliot Fu']")).isDisplayed());
		
		selectItem_customDropdown("//div[@id='root']//div[@role='listbox']", "//div[@class='visible menu transition']/div", "Christian");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()= 'Christian']")).isDisplayed());
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItem_customDropdown("//span[@class='caret']", "//ul[@class='dropdown-menu']/li", "First Option");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItem_customDropdown("//span[@class='caret']", "//ul[@class='dropdown-menu']/li", "Second Option");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItem_customDropdown("//span[@class='caret']", "//ul[@class='dropdown-menu']/li", "Third Option");
		SleepInsecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	}

	//isSelected: checkbox (input), radio_button (input), Dropdown list (option)
	
	@Test
	public void TC_04_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItem_Editable_Dropdown("//input[@class='search']", "//div[@role='option']//span", "Afghanistan");
		SleepInsecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(text(),'Afghanistan')]")).isDisplayed());
		
		selectItem_Editable_Dropdown("//input[@class='search']", "//div[@role='option']//span", "Albania");
		SleepInsecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(text(),'Albania')]")).isDisplayed());
		
		selectItem_Editable_Dropdown("//input[@class='search']", "//div[@role='option']//span", "Belgium");
		SleepInsecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and contains(text(),'Belgium')]")).isDisplayed());
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	
	public void selectItem_customDropdown(String parentXpath, String childXpath, String expectedEextItem){
		//1. click vao the (cha) cho no so ra cac item ben trong
		driver.findElement(By.xpath(parentXpath)).click();
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
	
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "huyendtt" + rand.nextInt(9999999) + "@gmail.com";
	}
	
	public void SleepInsecond(long time) {
		try {
			Thread.sleep(time*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	

}