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
public class Settings_IOS {
	
	@FindBy(xpath="//XCUIElementTypeSwitch[@text='Load from vpm/videos']")
	public static WebElement loadFromVpmSwitch;
	
	@FindBy(xpath="//XCUIElementTypeSwitch[@text='Load HTTP from json']")
	public static WebElement loadHttpFromJsonSwitch;
	
	@FindBy(xpath="//XCUIElementTypeSwitch[@text='Load RTSP from json']")
	public static WebElement loadRtspFromJsonSwitch;
		
	@FindBy(xpath = "//XCUIElementTypeButton[@text='Done']")
	public static WebElement backFromSettingsBtn;
}
