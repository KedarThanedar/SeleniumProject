package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.*;

import org.openqa.selenium.By;

public class YahooCalendar {
	WebDriver driver;
	public YahooCalendar(WebDriver _driver) {
		this.driver= _driver;
	}		
	
	public List<String> GetCalendarMarketData(String str1, String str2)
	{
		String xpathLi=String.format("//li[contains(@class,'D(ib) Va(t)')]/div/span[contains(.,'%s')]/following::span[2][contains(text(),'%s')]/ancestor::li/descendant::a",str1,str2);
		List<WebElement> elementList= driver.findElements(By.xpath(xpathLi));
		List<String> MarketData=new ArrayList<String>();
		for(int i=0;i<elementList.size();i++)
		{
			MarketData.add(elementList.get(i).getText());
		}
		for (String string : MarketData) {
			System.out.println(string);
		}
		return MarketData;
	}
	
	public void NavigateToDate(String matchString)
	{
		driver.findElement(By.partialLinkText(matchString)).click();
	}
	
}
