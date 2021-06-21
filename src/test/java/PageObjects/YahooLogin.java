package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.WaitHelper;

import org.openqa.selenium.By;

public class YahooLogin {
	WebDriver driver;
	WaitHelper waitHelper;
	public YahooLogin(WebDriver _driver)
	{
		this.driver=_driver;		
		waitHelper=new WaitHelper(_driver);
	}
	By consent_form = By.xpath("//*[@class='consent-form']");//cookies accept form
	By btn_AcceptAll = By.xpath("//button[@class='btn primary']");//Accept all button on cookies accept form
	By btn_SignIn = By.xpath("//*[@class='_yb_qwfgw']");//Sign in
	By txt_username=By.id("login-username");//username text box
	By btn_Login=By.id("login-signin");//login button
	By txt_password=By.id("login-passwd");//password text box
	////span[contains(@class,' _yb') and contains(text(),'firsttestlogin_12')]
	By input_user = By.id("ybarAccountMenu");
	
	public boolean IsDisplayed_ConsentForm()
	{
		return driver.findElement(consent_form).isDisplayed();		
	}
	
	public void clickAcceptAll()
	{
		driver.findElement(btn_AcceptAll).click();
	}
	
	public void clickSignIn()
	{
		driver.findElement(btn_SignIn).click();
	}
	
	public boolean IsDisplayed_UserNameTextBox()
	{
		return driver.findElement(txt_username).isDisplayed();		
	}
	public void ClickLogin()
	{
		driver.findElement(btn_Login).click();
	}
	public void EnterUserName(String userName)
	{
		driver.findElement(txt_username).sendKeys(userName);
	}
	public boolean IsDisplayed_PasswordTextBox()
	{
		return driver.findElement(txt_password).isDisplayed();		
	}
	public void EnterPassword(String password)
	{
		driver.findElement(txt_password).sendKeys(password);
	}
	
	public boolean IsDisplayed_UserName(String userName)
	{
		String xpath=String.format("//span[contains(@class,' _yb') and contains(text(),'%s')]", userName);
		WebElement element=driver.findElement(input_user);
		Actions newAction = new Actions(driver);
		newAction.moveToElement(element).build().perform();
		WebElement userNameLabel=driver.findElement(By.xpath(xpath));
		waitHelper.WaitUntilElementPresent(userNameLabel, 10);
		return userNameLabel.isDisplayed();
	}
	
}
