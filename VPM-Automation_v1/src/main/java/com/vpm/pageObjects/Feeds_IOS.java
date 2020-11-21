/**
 * 
 */
package com.vpm.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author maithili.s
 *
 */
public class Feeds_IOS {

	@FindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeImage")
	public static List<WebElement> sessionHistory;

	public static By getLiveLabel() {
		return By.xpath("//android.widget.TextView[@resource-id='com.adt.vpm:id/tvStatus']");		
	}
	
	@FindBy(css = "div[class*=\"roomVpmSession\"] span.feed-label")
	public static List<WebElement> liveSessions;
}
