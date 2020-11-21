/**
 * 
 */
package com.vpm.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author maithili.s
 *
 */
public class LandingPage_IOS {

	@FindBy(xpath="//XCUIElementTypeStaticText[@text='Videos']")
	public static WebElement videosMenu;
	
	@FindBy(xpath="//XCUIElementTypeStaticText[@text='Feeds']")
	public static WebElement feedsMenu;
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[@text='VPM']")
	public static WebElement brandText;
	
	@FindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]")
	public static WebElement settingsIcon;		
}
