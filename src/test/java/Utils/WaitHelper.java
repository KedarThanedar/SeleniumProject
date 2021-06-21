package Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	static WebDriver driver;
	public WaitHelper(WebDriver _driver)
	{
		driver=_driver;
	}
	
	public static boolean WaitUntilTitlePresent(String title,int timeoutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		Boolean isTitlePresent = wait.until(ExpectedConditions.titleContains(title));
		return isTitlePresent;
	}
	
	public static void WaitUntilElementPresent(WebElement element, int timeoutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));		
	}

}
