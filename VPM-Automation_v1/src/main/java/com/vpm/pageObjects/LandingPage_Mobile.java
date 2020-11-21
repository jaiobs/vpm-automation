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
public class LandingPage_Mobile {	
	
	@FindBy(xpath = "//android.widget.TextView[@text='VPM']")
	public static WebElement brandText;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Videos']")
	public static WebElement videosMenu;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Feeds']")
	public static WebElement feedsMenu;
	
	@FindBy(xpath = "//android.widget.RelativeLayout/android.widget.ImageView[1]")
	public static WebElement settingsIcon;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@content-desc='Videos']")
	public static WebElement activeMenu;			
}
