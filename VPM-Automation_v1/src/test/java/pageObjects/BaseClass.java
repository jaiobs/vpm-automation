package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import stepDefinitions.Hooks;
public abstract class BaseClass {	
	public static WebDriver driver;
	public static boolean bResult;		

	public  BaseClass(WebDriver driver){
		
		BaseClass.driver = driver;		
		BaseClass.bResult = true;
	}
	
	public WebElement getWebElement(String locator) {		
		String webElement_locator, bySelector = "";
		bySelector = locator.substring(0, locator.indexOf("="));
		webElement_locator = locator.substring(locator.indexOf("=")+1, locator.length());
		WebElement e = null;
		if(bySelector.equalsIgnoreCase("css")) {
			e = driver.findElement(By.cssSelector(webElement_locator));
		} else if(bySelector.equalsIgnoreCase("link"))	{
			e = driver.findElement(By.linkText(webElement_locator));
		}
		return e;
	}
    
    public List<WebElement> getWebElements(String locator) {
    	String webElement_locator, bySelector = "";
		bySelector = locator.substring(0, locator.indexOf("="));
		webElement_locator = locator.substring(locator.indexOf("=")+1, locator.length());
		List<WebElement> e = null;
		if(bySelector.equalsIgnoreCase("css")) {
			e = driver.findElements(By.cssSelector(webElement_locator));
		} else if(bySelector.equalsIgnoreCase("link"))	{
			e = driver.findElements(By.linkText(webElement_locator));
		}
		return e;
	}
}


