package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Method_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//ham mo ra 1 browser nao do
		driver = new FirefoxDriver();
		

	}

	@Test
	public void TC_01_() {
		// mo ra 1 cai url
		driver.get("https://demo.nopcommerce.com/");	//*
		
		//dong tab
		driver.close();
		
		//dong ca browser
		driver.quit();	//*
		
		//lay url tai thoi diem minh dang get
		String HomepageUrl = driver.getCurrentUrl();	//*
		
		//lay ra cais title taij thoi diem dang dung
		String HomeTitle = driver.getTitle();	//*
		
		//lay ra source code tai trang minh dang dung
		String sourceCode = driver.getPageSource();
		
		//Window/tab ID hien tai
		String homePageTabID = driver.getWindowHandle();	//*
		
		//get ra tat car tab/ID dang co
		Set<String> AllWindowID = driver.getWindowHandles();	//*
		
		//chờ cho element có thể được tìm thấy trong 1 khoảng time 15s
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	//*
		
		//chờ cho page có thể được load thành công trong 1 khoảng time 15s
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		//Chờ cho 1 đoạn JS script được inject thành công
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//phóng to browser
		driver.manage().window().maximize();	//*
		
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		// History luu trang thai tot hơn
		driver.navigate().to("https://demo.nopcommerce.com/");
		
		//Alert/Window / Frame
		driver.switchTo().alert();		//*
		driver.switchTo().window("");		//*
		driver.switchTo().frame(1);		//*
		
	
	}
	

	@Test
	public void TC_02_() {
		// Login Page title
		
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}