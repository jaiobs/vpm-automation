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
public class Settings_Mobile {
	
	@FindBy(xpath="//android.widget.TextView[@text='Load from vpm/videos']/parent::android.widget.RelativeLayout/following-sibling::android.widget.LinearLayout/android.widget.Switch")
	public static WebElement loadFromVpmSwitch;
	
	@FindBy(xpath="//android.widget.TextView[@text='Load HTTP from Json']/parent::android.widget.RelativeLayout/following-sibling::android.widget.LinearLayout/android.widget.Switch")
	public static WebElement loadHttpFromJsonSwitch;
	
	@FindBy(xpath="//android.widget.TextView[@text='Load RTSP from Json']/parent::android.widget.RelativeLayout/following-sibling::android.widget.LinearLayout/android.widget.Switch")
	public static WebElement loadRtspFromJsonSwitch;
		
	@FindBy(xpath = "//android.widget.TextView[@text='Settings']/preceding-sibling::android.widget.ImageView")
	public static WebElement backFromSettingsBtn;
}
