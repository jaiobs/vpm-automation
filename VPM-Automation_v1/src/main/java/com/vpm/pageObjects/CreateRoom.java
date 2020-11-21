/**
 * 
 */
package com.vpm.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author maithili.s
 *
 */
public class CreateRoom {

	@FindBy(css = "a[class*=\"room-btn\"]")
	public static WebElement createRoomBtn;
	
	@FindBy(id = "videoRoomId")
	public static WebElement roomName;
	
	@FindBy(xpath = "//div[@id=\"room-modal\"]//div//label[contains(text(), \"Local Audio\")]/preceding-sibling::input")
	public static WebElement localAudio_ChkBox;
	
	@FindBy(xpath = "//div[@id=\"room-modal\"]//div//label[contains(text(), \"Local Video\")]/preceding-sibling::input")
	public static WebElement localVideo_ChkBox;
	
	@FindBy(xpath = "//div[@id=\"room-modal\"]//div//label[contains(text(), \"Remote Audio\")]/preceding-sibling::input")
	public static WebElement remoteAudio_ChkBox;
	
	@FindBy(xpath = "//div[@id=\"room-modal\"]//div//label[contains(text(), \"Remote Video\")]/preceding-sibling::input")
	public static WebElement remoteVideo_ChkBox;
	
	@FindBy(id = "videoRoomSubmit")
	public static WebElement startBtn;
	
	@FindBy(css = "div.video-close>img.close-icon")
	public static WebElement sessionCloseIcon;
	
	@FindBy(css = "div.video-icons>img")
	public static WebElement videoIcon;
	
	@FindBy(css = "div.mike-icons>img")
	public static WebElement audioIcon;
	
	@FindBy(css = ".remote-video-icons>img")
	public static List<WebElement> remoteVideoMuteIcon;
	
	@FindBy(css = "div.mike-icons>img")
	public static WebElement localVideoCam;

	@FindBy(id = "zoom_in")
	public static WebElement zoomInBtn;
	
	@FindBy(id = "zoom_out")
	public static WebElement zoomOutBtn;
	
	@FindBy(id = "zoom_reset")
	public static WebElement zoomResetBtn;
	
	@FindBy(id = "panzoom")
	public static WebElement zoomScale;
	
	@FindBy(linkText = "Back")
	public static WebElement BackFromSession;
	
	@FindBy(css = "button[onclick*=\"ConfirmToClose\"]")
	public static WebElement ConfirmToCloseSession;
	
	@FindBy(css = "#warning-modal button.btn:nth-of-type(1)")
	public static WebElement CancelSessionPopup;
	
	@FindBy(css = "section.fullvideo-content h1")
	public static WebElement sessionHeader;

	@FindBy(css = "section.fullvideo-content span.live-label")
	public static WebElement liveLabel;
	
	@FindBy(css = "div.tostwrapper")
	public static WebElement alertMsg;
	
	@FindBy(css = "#accordionSidebar div.sidebar-brand-icon")
	public static WebElement brandIcon;
	
	@FindBy(css = "#accordionSidebar div.sidebar-brand-text")
	public static WebElement brandText;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(1) img")
	public static WebElement videosIcon;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(1) span")
	public static WebElement videosMenu;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(2) img")
	public static WebElement feedsIcon;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(2) span")
	public static WebElement feedsMenu;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(3) img")
	public static WebElement settingsIcon;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(3) span")
	public static WebElement settingsMenu;
	
	@FindBy(css = "#accordionSidebar>li.active span")
	public static WebElement activeMenu;
	
	@FindBy(css = "div.video-content h1")
	public static WebElement activeMenuHeader;
	
	@FindBy(id = "SLServerYes")
	public static WebElement useSLServer;
	
	@FindBy(id = "SLServerNo")
	public static WebElement doNotUseSLServer;
	
	@FindBy(id = "serverIp")
	public static WebElement serverIPAddress;
}
