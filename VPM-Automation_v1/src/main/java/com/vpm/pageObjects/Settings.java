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
public class Settings {
	
	@FindBy(css = "a[onclick*=\"settingsPage\"]")
	public static WebElement settingsMenu;
	
	@FindBy(id = "SLServerYes")
	public static WebElement useSLServer;
	
	@FindBy(id = "SLServerNo")
	public static WebElement doNotUseSLServer;
	
	@FindBy(id = "serverIp")
	public static WebElement serverIPAddress;
	
	
}
