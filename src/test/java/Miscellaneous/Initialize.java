package Miscellaneous;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.JsonUtils;

public class Initialize {
	
	public static WebDriver GetWebDriver() throws IOException, ParseException {
		String projectPath = System.getProperty("user.dir");
		String testDataFilePath=projectPath+"/DataAndConfigs/TestData.json";
		String webDriverProperty=JsonUtils.ReadDataFromJson(testDataFilePath,"WebDriverProperty");
		String webDriverLocation=JsonUtils.ReadDataFromJson(testDataFilePath,"WebDriverLocation");
		String Url=JsonUtils.ReadDataFromJson(testDataFilePath,"URL");
		System.setProperty(webDriverProperty,projectPath+webDriverLocation);
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(Url);
		driver.manage().window().maximize();
		return driver;
	}
}
