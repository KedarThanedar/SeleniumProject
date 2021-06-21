package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class YahooFinance {
	WebDriver driver;
	Actions action;
	
	public YahooFinance(WebDriver _driver)
	{
		this.driver=_driver;
		this.action = new Actions(this.driver);
	}
	
	By Link_Finance = By.xpath("//a[contains(text(),'Finance')]");//Finance link
	By Link_MarketData = By.xpath("//div[contains(text(),'Market Data')]");//Market data menu
	By Link_Calendar = By.linkText("Calendar");//Calendar menu
	By Link_Videos = By.xpath("//*[contains(text(),'Videos')]");
	
	public void ClickFinanceLink()
	{
		driver.findElement(Link_Finance).click();		
	}
	
	public void ClickCalendarMenu()
	{
		Actions newAction = new Actions(driver);
		WebElement element = driver.findElement(Link_MarketData);
		WebElement elementNext = driver.findElement(Link_Videos);
		
		for(int retry=0;retry<20;retry++)
		{
			newAction.moveToElement(elementNext).build().perform();
			newAction.moveToElement(element).build().perform();
			if(IsDisplayed_CalendarLink())
			{
				break;
			}
		}
		WebElement calendarLink = driver.findElement(Link_Calendar);
		newAction.moveToElement(calendarLink).build().perform();
		driver.findElement(Link_Calendar).click();
	}
	
	public boolean IsTitlePresent(String title)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Boolean isTitlePresent = wait.until(ExpectedConditions.titleContains(title));
		return isTitlePresent;
	}
	
	public boolean IsDisplayed_CalendarLink()
	{
		try {
			return driver.findElement(Link_Calendar).isDisplayed();			
		}
		catch (Exception e) {
			return false;
		}
		
	}

}
