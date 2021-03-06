/**
 * 
 */
package com.vpm.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author maithili.s
 *
 */
public class JoinRoom_IOS {
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='nounPlus']")
	public static WebElement joinRoomBtn;
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[@text='Enter the room name']/following-sibling::XCUIElementTypeTextField")
	public static WebElement roomName;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='Local Audio']")
	public static WebElement localAudio_ChkBox;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='Local Video']")
	public static WebElement localVideo_ChkBox;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='Remote Audio']")
	public static WebElement remoteAudio_ChkBox;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='Remote Video']")
	public static WebElement remoteVideo_ChkBox;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='Create']")
	public static WebElement startBtn;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='hangupCall']")
	public static WebElement sessionCloseIcon;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='noun mic']")
	public static WebElement audioIcon;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='noun Video 1']")
	public static WebElement videoIcon;
	
	@FindBy(xpath = "//XCUIElementTypeImage")
	public static List<WebElement> remoteVideoMuteIcon;
	
	@FindBy(xpath = "")
	public static WebElement localVideoCam;		
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='noun Next']")
	public static WebElement BackFromSession;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.adt.vpm:id/tvStatus']")
	public static WebElement liveLabel_Feeds;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='OK']")
	public static WebElement ConfirmToCloseSession;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@id='Cancel']")
	public static WebElement CancelSessionPopup;
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[1]")
	public static WebElement sessionHeader;		
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.adt.vpm:id/tvStatus']")
	public static List<WebElement> liveSessions;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Feeds']")
	public static WebElement feedsMenu;
	
	@FindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]")
	public static WebElement settingsIcon;	
	
	@FindBy(xpath = "//XCUIElementTypeSwitch[@id='Use SL Server']")
	public static WebElement useSLServer;
	
	@FindBy(xpath = "//XCUIElementTypeTextField[@class='UIATextField']")
	public static WebElement serverIpAddressText;	
	
	@FindBy(xpath = "//XCUIElementTypeButton[@text='Done']")
	public static WebElement backFromSettingsBtn;
		
	@FindBy(xpath = "//XCUIElementTypeSwitch[@text='Load from vpm/videos']")
	public static WebElement loadFromVpmSwitch;
}
