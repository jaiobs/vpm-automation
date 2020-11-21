package com.vpm.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateRoom_Mobile {
 
	@FindBy(xpath = "//android.widget.RelativeLayout/android.widget.ImageView[1]")
	public static WebElement settingsIcon;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Feeds']")
	public static WebElement feedsMenu;
	
	@FindBy(xpath = "//android.widget.TextView[@text='VPM']")
	public static WebElement brandText;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Videos']")
	public static WebElement videosMenu;
	
	@FindBy(xpath = "//android.widget.RelativeLayout/android.widget.ImageView[2]")
	public static WebElement createRoomBtn;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.adt.vpm.testapp:id/edtRoomName']")
	public static WebElement roomName;
	
	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.adt.vpm.testapp:id/cbLocalAudio']")
	public static WebElement localAudio_ChkBox;
	
	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.adt.vpm.testapp:id/cbLocalVideo']")
	public static WebElement localVideo_ChkBox;
	
	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.adt.vpm.testapp:id/cbRemoteAudio']")
	public static WebElement remoteAudio_ChkBox;
	
	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.adt.vpm.testapp:id/cbRemoteVideo']")
	public static WebElement remoteVideo_ChkBox;
	
	@FindBy(xpath = "//android.widget.Button[@resource-id='com.adt.vpm.testapp:id/btnStart']")
	public static WebElement startBtn;
	
	@FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.adt.vpm.testapp:id/btnCallDisconnect']")
	public static WebElement sessionCloseIcon;
	
	@FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.adt.vpm.testapp:id/btnCallToggleMic']")
	public static WebElement audioIcon;
	
	@FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.adt.vpm.testapp:id/btnVideoDisable']")
	public static WebElement videoIcon;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.adt.vpm.testapp:id/ivVideoPlaceHolder']")
	public static List<WebElement> remoteVideoMuteIcon;
	
	@FindBy(xpath = "//android.view.View[@resource-id='com.adt.vpm.testapp:id/svPipVideoView']")
	public static WebElement localVideoCam;	
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.adt.vpm.testapp:id/ivBackArrow']")
	public static WebElement BackFromSession;
	
	@FindBy(xpath = "//android.widget.Button[@text='OK']")
	public static WebElement ConfirmToCloseSession;
	
	@FindBy(xpath = "//android.widget.Button[@text='Cancel']")
	public static WebElement CancelSessionPopup;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.adt.vpm.testapp:id/tvContactNameCall']")
	public static WebElement sessionHeader;	
	
	@FindBy(xpath = "")
	public static WebElement alertMsg;	
	
	@FindBy(xpath = "//android.widget.LinearLayout[@content-desc='Videos']")
	public static WebElement activeMenu;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Use SL Server']/parent::android.widget.RelativeLayout/following-sibling::android.widget.LinearLayout/android.widget.Switch")
	public static WebElement useSLServer;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='android:id/edit']")
	public static WebElement serverIpAddressText;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Server IP Address']")
	public static WebElement serverIpAddressMenu;
	
	@FindBy(xpath = "//android.widget.Button[@text='OK']")
	public static WebElement roomServer_OkBtn;
	
	@FindBy(xpath = "//android.widget.Button[@text='Cancel']")
	public static WebElement roomServer_CancelBtn;		
	
	@FindBy(xpath = "//android.widget.TextView[@text='Settings']/preceding-sibling::android.widget.ImageView")
	public static WebElement backFromSettingsBtn;
		
	@FindBy(xpath = "//android.widget.TextView[@text='Settings']/preceding-sibling::android.widget.ImageView")
	public static WebElement loadFromVpmSwitch;
}
