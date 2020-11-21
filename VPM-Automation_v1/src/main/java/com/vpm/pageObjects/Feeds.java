/**
 * 
 */
package com.vpm.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.vpm.stepDefinitions.Hooks;

/**
 * @author maithili.s
 *
 */
public class Feeds {

	@FindBy(css = "div[class*=\"roomVpmSession\"]")
	public static List<WebElement> sessionHistory;

	public static By getLiveLabel() {
		return By.cssSelector("span.feed-label");		
	}
	
	@FindBy(css = "div[class*=\"roomVpmSession\"] span.feed-label")
	public static List<WebElement> liveSessions;

}
