/**
 * 
 */
package pageObjects;

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

	@FindBy(id = "zoom_in")
	public static WebElement zoomInBtn;
	
	@FindBy(id = "zoom_out")
	public static WebElement zoomOutBtn;
	
	@FindBy(id = "zoom_reset")
	public static WebElement zoomResetBtn;
	
	@FindBy(css = "section.fullvideo-content h1")
	public static WebElement sessionHeader;

	@FindBy(css = "section.fullvideo-content span.live-label")
	public static WebElement liveLabel;
	
	@FindBy(css = "div.tostwrapper")
	public static WebElement alertMsg;
}
