/**
 * 
 */
package com.vpm.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.MobileElement;

/**
 * @author maithili.s
 *
 */
public class Videos_Mobile {

	@FindBy(xpath="//android.widget.TextView[@text='Videos']")
	public static WebElement videosMenu;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvStatus']")
	public static List<WebElement> videoLabel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvRoomName']")
	public static List<WebElement> videoName;
	
	public static By video(String videoFileName) {
		return By.xpath("//android.widget.TextView[@text='"+videoFileName+"']/preceding-sibling::android.widget.FrameLayout");
	}
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvVideoFileName']")
	public static WebElement fileName;
	
	public static By fileName(String videoFileName) {
		return By.xpath("//android.widget.TextView[@text='"+videoFileName+"']");
	}
	
	@FindBy(xpath="//android.view.View[@resource-id='com.adt.vpm:id/exo_subtitles']")
	public static WebElement video;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivBackward']")
	public static WebElement videoBackwardBtn;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivBackward']/following-sibling::android.widget.TextView")
	public static WebElement videoBackwardViewInSeconds;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivForward']")
	public static WebElement videoForwardBtn;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivForward']/following-sibling::android.widget.TextView")
	public static WebElement videoForwardViewInSeconds;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivPlay']")
	public static WebElement videoPlayBtn;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivPause']")
	public static WebElement videoPauseBtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvPlaybackStatus']")
	public static WebElement videoPlayBackStatus;
	
	public static By videoPlayBackStatus() {
		return By.xpath("//android.widget.TextView[@resource-id='com.adt.vpm:id/tvPlaybackStatus']");		
	}
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvCurrentDuration']")
	public static WebElement videoPlayCurrentDuration;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvTotalDuration']")
	public static WebElement videoPlayTotalDuration;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivFullScreen']")
	public static WebElement videoFullScreen;
	
	@FindBy(xpath="//android.widget.SeekBar[@resource-id='com.adt.vpm:id/seekBarPlayer']")
	public static WebElement videoSeekBar;

	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivVideoBackBtn']")
	public static WebElement backFromVideoBtn;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivInfo']")
	public static WebElement videoInfo;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/alertTitle']")
	public static WebElement alertTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	public static WebElement alertMessage;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	public static WebElement alertOkBtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/tvValue']")
	public static WebElement videoInfo_FileName;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.adt.vpm:id/ivClose']")
	public static WebElement videoInfo_CloseBtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.adt.vpm:id/alertTitle']")
	public static WebElement invalidUrlTitle;
	
}
