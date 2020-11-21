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
public class JoinRoom {

	@FindBy(css = "div.add-btn")
	public static WebElement joinRoomBtn;
	
	@FindBy(id = "joinRoomId")
	public static WebElement roomName;
	
	@FindBy(xpath = "//div[@id=\"join-room-modal\"]//div//label[contains(text(), \"Local Audio\")]/preceding-sibling::input")
	public static WebElement localAudio_ChkBox;
	
	@FindBy(xpath = "//div[@id=\"join-room-modal\"]//div//label[contains(text(), \"Local Video\")]/preceding-sibling::input")
	public static WebElement localVideo_ChkBox;
	
	@FindBy(xpath = "//div[@id=\"join-room-modal\"]//div//label[contains(text(), \"Remote Audio\")]/preceding-sibling::input")
	public static WebElement remoteAudio_ChkBox;
	
	@FindBy(xpath = "//div[@id=\"join-room-modal\"]//div//label[contains(text(), \"Remote Video\")]/preceding-sibling::input")
	public static WebElement remoteVideo_ChkBox;
	
	@FindBy(id = "joinRoomSubmit")
	public static WebElement startBtn;
	
	@FindBy(css = "div.video-close>img.close-icon")
	public static WebElement sessionCloseIcon;
	
	@FindBy(css = "div.video-icons>img")
	public static WebElement videoIcon;
	
	@FindBy(css = "div.mike-icons>img")
	public static WebElement audioIcon;
	
	@FindBy(css = ".remote-video-icons>img")
	public static List<WebElement> remoteVideoMuteIcon;

	@FindBy(id = "zoom_in")
	public static WebElement zoomInBtn;
	
	@FindBy(id = "zoom_out")
	public static WebElement zoomOutBtn;
	
	@FindBy(id = "zoom_reset")
	public static WebElement zoomResetBtn;
	
	@FindBy(linkText = "Back")
	public static WebElement BackFromSession;
	
	@FindBy(css = "div.card span.feed-label")
	public static WebElement liveLabel_Feeds;
	
	@FindBy(css = "section.fullvideo-content h1")
	public static WebElement sessionHeader;

	@FindBy(css = "section.fullvideo-content span.live-label")
	public static WebElement liveLabel;
	
	@FindBy(css = "div.tostwrapper")
	public static WebElement alertMsg;
	
	@FindBy(css = "div[class*=\"roomVpmSession\"] span.feed-label")
	public static List<WebElement> liveSessions;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(2) span")
	public static WebElement feedsMenu;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(3) span")
	public static WebElement settingsMenu;
	
	@FindBy(id = "SLServerYes")
	public static WebElement useSLServer;
	
	@FindBy(id = "SLServerNo")
	public static WebElement doNotUseSLServer;
	
	@FindBy(id = "serverIp")
	public static WebElement serverIPAddress;
}
