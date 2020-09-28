package modules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;

/**
 * @author maithili.s
 *
 */
public class Page {

	static Page page = new Page();

	public static Page getInstance() {
		return page;
	}

	public Timeouts pageLoadTimeOut(WebDriver driver) {
		return driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	public void delay(int milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Timeouts implicitlyWait(WebDriver driver) {
		return driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}		
	
	public void scrollPage(WebDriver Driver, int x, int y){
		String jsCommand;
		JavascriptExecutor jsEngine;		
		jsEngine = (JavascriptExecutor) Driver;		
		jsCommand = String.format("window.scrollTo(%d, %d)", x,y);		
		jsEngine.executeScript(jsCommand);
	}
	

}
