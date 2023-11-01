package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Textbox_TextArea {
	WebDriver driver;
	By Visit_Register = By.xpath("//a[@href='http://demo.guru99.com/']");
	String EmailAddress;
	String LoginUrl, User_id, Password, Customer_ID;
	String name, dob, address, city, state, pin, phone,addressEdit,cityEdit,stateEdit,pinEdit,phoneEdit,EmailEdit;
	By NameTextbox = By.name("name");
	By GenderRadio = By.xpath("//input[@value='m']");
	By DobTextbox = By.name("dob");
	By AddressTextArea = By.name("addr");
	By CityTextbox = By.name("city");
	By StateTextbox = By.name("state");
	By PinTextbox = By.name("pinno");
	By PhoneTextbox = By.name("telephoneno");
	By EmailTextbox = By.name("emailid");
	By PasswordTextbox = By.name("password");
	By Submit_Button = By.name("sub");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.guru99.com/v4/");
		EmailAddress = getRandomEmail();
		name = "Huyen Dang";
		dob = "1994-10-29";
		address = "27 Co Nhue Ha Noi";
		city = "Ha Noi";
		state = "Viet Nam";
		pin = "836192";
		phone = "0936202192";
		
		addressEdit = "27 Co Nhue Ha Noi";
		cityEdit = "Ha Noi";
		stateEdit = "Viet Nam";
		pinEdit = "836192";
		phoneEdit = "0936202192";
		EmailEdit = getRandomEmail();
	}

	@Test
	public void TC_01_Register() {
		LoginUrl = driver.getCurrentUrl();
		Element_Click(Visit_Register);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(EmailAddress);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		User_id = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		// mngr506000-gEhudAr
	}

	@Test
	public void TC_02_Login() {
		driver.get(LoginUrl);
		driver.findElement(By.name("uid")).sendKeys(User_id);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(NameTextbox).sendKeys(name);
		driver.findElement(GenderRadio).click();
		driver.findElement(DobTextbox).sendKeys(dob);
		driver.findElement(AddressTextArea).sendKeys(address);
		driver.findElement(CityTextbox).sendKeys(city);
		driver.findElement(StateTextbox).sendKeys(state);
		driver.findElement(PinTextbox).sendKeys(pin);
		driver.findElement(PhoneTextbox).sendKeys(phone);
		driver.findElement(EmailTextbox).sendKeys(EmailAddress);
		driver.findElement(PasswordTextbox).sendKeys(Password);
		driver.findElement(Submit_Button).click();
		Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//p")).getText(), "Customer Registered Successfully!!!");

		Customer_ID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		//51792
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), EmailAddress );
	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(Customer_ID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(NameTextbox).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(AddressTextArea).getText(),address);
		//addressEdit,cityEdit,stateEdit,pinEdit,phoneEdit;
		
		driver.findElement(AddressTextArea).clear();
		driver.findElement(AddressTextArea).sendKeys(addressEdit);
		driver.findElement(CityTextbox).clear();
		driver.findElement(CityTextbox).sendKeys(cityEdit);
		driver.findElement(StateTextbox).clear();
		driver.findElement(StateTextbox).sendKeys(stateEdit);
		driver.findElement(PinTextbox).clear();
		driver.findElement(PinTextbox).sendKeys(pinEdit);
		driver.findElement(PhoneTextbox).clear();
		driver.findElement(PhoneTextbox).sendKeys(phoneEdit);
		driver.findElement(EmailTextbox).clear();
		driver.findElement(EmailTextbox).sendKeys(EmailEdit);
		
		driver.findElement(By.name("sub")).click();
		
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void Element_Click(By by) {
		driver.findElement(by).click();
	}

	public void Element_Sendkey(By by, String value) {
		driver.findElement(by).sendKeys(value);
	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "huyendtt" + rand.nextInt(9999999) + "@gmail.com";
	}

	public void SleepInsecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}