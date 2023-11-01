package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_Method_III{
	//khai bao
	WebDriver driver;
	WebElement element;
	
	//Khai bao + Khoi tao
	By emailTextboxBy = By.xpath("//input");

	@BeforeClass
	public void beforeClass() {
		// khởi tạo driver
		//open browser
		driver = new FirefoxDriver();
		// chờ cho element có thể tìm thấy trong 1 khoảng thời gian
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_verify_url() {
		//open link cần test
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// 1  element không tạo biến dùng 1 lần
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("danghuyenit10@gmail.com");
		
		// 1 element dùng nhiều lần - cần tạo biến dùng gọn hơn
		WebElement EmailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		EmailTextbox.clear();
		EmailTextbox.sendKeys("danghuyenit10@gmail.com");
		
		// nhiều element
		
		
		
	}
	
	//@Test
	public void TC_02_method_elemet() {
		element = driver.findElement(By.xpath(""));
		
		// dùng cho textbox/textarer/ dropdown list (edit table)
		element.clear();
		
		// nhập dữ liệu cho textbox/textarer/ dropdown list (edit table)
		element.sendKeys("");
		
		// checkbox, button, radiobutton, link
		element.click();
		
		// lấy giá trị của attribute
		element.getAttribute("placeholder");
		
		// lấy giá trị css của các attribute nó sẽ ra giá trị của css background
		element.getCssValue("background");
		
		//trả về vị trí của element so với browser
		element.getLocation();
		
		// chiều rộng/ cao của element
		element.getSize();
		
		//lưu hình trong quá trình lỗi .png, .jpg, base 64
		//element.getScreenshotAs("");
		
		// lấy thẻ mà css hay xpath đang thao tác, có thể lấy biến để làm đầu vào của locator khác
		element.getTagName();
		
		// lấy ra được text của lable, header, li, span, .... cứ có text là get được
		element.getText();
		
		//
		element.isDisplayed();
		element.isEnabled();
		element.isSelected();
		
		// cần verify xem 1 element có/không hiển thị hay không apply voi tat ca cac loai element
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		// cần verify xem 1 element có/không thể thao tác hay không apply voi tat ca cac loa element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// cần verify xem 1 element có/không được chọn hay không (radio/checkbox/dropdown)
		////isSelected: checkbox (input), radio_button (input), Dropdown list (option)
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		//dùng apply tương ứng với hành động nhập xong click enter (form)
		element.submit();
		
	}
	@Test
	public void TC_03_submit() {
		//open link cần test
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		// 1  element không tạo biến dùng 1 lần
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("danghuyenit10@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("danghuyenit10@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).submit();
		
	}
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	
	


}