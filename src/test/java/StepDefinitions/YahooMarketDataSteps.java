package StepDefinitions;

import io.cucumber.java.en.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Miscellaneous.Initialize;
import PageObjects.*;
import Utils.ExcelUtils;
import Utils.JsonUtils;
import Utils.WaitHelper;

public class YahooMarketDataSteps {
	FileInputStream fileStream = new FileInputStream("./DataAndConfigs/Credentials.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(fileStream);
	XSSFSheet worksheet1 = workbook.getSheet("Credentials");
	ExcelUtils Utils = new ExcelUtils(workbook, worksheet1);
	WebDriver driver;
	public YahooMarketDataSteps() throws IOException, InvalidFormatException, ParseException
	{
		driver=Initialize.GetWebDriver();
	}
	@Given("I login with a valid user")	
	public void LoginWithValidUser() throws IOException, ParseException {
		YahooLogin login=new YahooLogin(driver);
		String userName=Utils.GetCredentials(1,"username");//Get username from Credentials excel
		if((login.IsDisplayed_ConsentForm()))
		{
			login.clickAcceptAll();
		}
		
		login.clickSignIn();
		Assert.assertTrue(login.IsDisplayed_UserNameTextBox());
		login.EnterUserName(userName);
		login.ClickLogin();
		Assert.assertTrue(login.IsDisplayed_PasswordTextBox());
		login.EnterPassword(Utils.GetCredentials(1,"password"));//Get password from Credentials excel
		login.ClickLogin();
		Assert.assertTrue(login.IsDisplayed_UserName(userName.toLowerCase()));//Check that User name is displayed when hover over profile image after logon
	}
	
	@Then("I navigate to Market Data Calendars page and verify the title has {string}")
	public void NavigateToMarketDataPageAndVerifyTitle(String string) 
	{
		YahooFinance financePage = new YahooFinance(driver);
		financePage.ClickFinanceLink();
		financePage.ClickCalendarMenu();
		Assert.assertTrue(WaitHelper.WaitUntilTitlePresent(string,20));
		Assert.assertTrue(driver.getTitle().contains(string));//Verify the title is as expected		
	}

	@And("I get market data for {string}")
	public void GetMarketDataForDate(String string) throws java.text.ParseException {
		try
		{
			LocalDate requestDate = LocalDate.parse(string);
			String dayOfMarketData=string.split("-")[2];
			String monthOfMarketData=new DateFormatSymbols(Locale.ENGLISH).getShortMonths()[Integer.parseInt(string.split("-")[1])-1]; 
			String buttonName = NextOrPrevious(requestDate);//Decide whether to click Next or Prev button to navigate to a date within valid range
			YahooCalendar calendarPage = new YahooCalendar(driver);
			if(buttonName.equals("Next") || buttonName.equals("Prev"))
			{
				calendarPage.NavigateToDate(buttonName);				
			}
			
			List<String> retrievedMarketData = calendarPage.GetCalendarMarketData(dayOfMarketData, monthOfMarketData);
			Assert.assertTrue(retrievedMarketData.size()>0);//Check Market date is retrieved 
		}
		
		catch (DateTimeParseException e)
		{
			Assert.fail("Date must be in ISO8601 format yyyy-mm-dd");
		}
	
	}
	
	public String NextOrPrevious(LocalDate requestDate)
	{
		if(requestDate.compareTo(LocalDate.now().plusDays(-8))<0 || requestDate.compareTo(LocalDate.now().plusDays(11))>=0)
		{
			Assert.fail("Provide a date that is valid range. Valid range is: Min Date |"+LocalDate.now().plusDays(-8)+"|"+"Max Date |"+LocalDate.now().plusDays(11)+"|");
		}
		if(requestDate.compareTo(LocalDate.now().plusDays(6))>=0)
		{
			return "Next";
		}
		else if(requestDate.compareTo(LocalDate.now().plusDays(-1))<0 && requestDate.compareTo(LocalDate.now().plusDays(-8))>=0)
		{
			return "Prev";
		}

		return "none";
	}
	
	@And("I close browser")	
	public void closeBrowser() 
	{
		driver.quit();
	}
}