package webdriver;

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

public class Topic_19_JavaScript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
	}

	
	public void TC_01_Live_Guru() {
		navigateToUrlByJS("http://live.techpanda.org/"); //su dung Js de truy cap vao trang
		
		String DomainLiveGuru = (String) executeForBrowser("return document.domain;"); //lay ra domain cua web
		Assert.assertEquals(DomainLiveGuru, "live.techpanda.org");
		
		String UrlLiveGuru = (String) executeForBrowser("return document.URL;"); //lay ra url cua web
		Assert.assertEquals(UrlLiveGuru, "http://live.techpanda.org/");
		
		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
		
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//div[@class='footer']//a[text()='Customer Service']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", getRandomEmail());
		clickToElementByJS("//button[@title='Subscribe']");
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		explicitWait.until(ExpectedConditions.urlToBe("http://demo.guru99.com/v4/"));
		Assert.assertEquals((String) executeForBrowser("return document.URL;"), "demo.guru99.com");
	}

	@Test
	public void TC_02_htmtValidation() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		clickToElementByJS("//input[@type='submit']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		
		sendkeyToElementByJS("//input[@id='fname']","huyendang");
		clickToElementByJS("//input[@type='submit']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
		
		sendkeyToElementByJS("//input[@id='pass']","12345");
		clickToElementByJS("//input[@type='submit']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
		
		sendkeyToElementByJS("//input[@id='em']","123@456");
		clickToElementByJS("//input[@type='submit']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");
		
		sendkeyToElementByJS("//input[@id='em']","danghuyenit10@gmail.com");
		clickToElementByJS("//input[@type='submit']");
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	
	public void scrollToBottomPage() {		//hay dung
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		SleepInsecond(3);
	}

	public void hightlightElement(String locator) {		//hay dung
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		SleepInsecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {		//hay dung
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		SleepInsecond(3);
	}

	public void scrollToElementOnTop(String locator) {		//hay dung
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {		//hay dung
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {		//hay dung
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {		//thinh thoang dung
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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