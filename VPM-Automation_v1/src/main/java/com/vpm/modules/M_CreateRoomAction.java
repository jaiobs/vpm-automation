/**
 * 
 */
package com.vpm.modules;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CommonMethods;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.CreateRoom_IOS;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;
import com.vpm.pageObjects.Settings;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * @author maithili.s
 *
 */
public class M_CreateRoomAction extends BaseClass {

	public static Map<String, Boolean> defaultControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> mediaControls = new HashMap<String, Boolean>();
	CommonMethods command = new CommonMethods();

	public synchronized void clickCreateRoom(AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			CreateRoom_IOS.createRoomBtn.click();
		} else if (driver instanceof AndroidDriver) {
			CreateRoom_Mobile.createRoomBtn.click();
		}
	}

	public synchronized void getDefaultControls(AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			defaultControls.put("Local Audio",
					Boolean.parseBoolean(CreateRoom_IOS.localAudio_ChkBox.getAttribute("checked")));
			defaultControls.put("Local Video",
					Boolean.parseBoolean(CreateRoom_IOS.localVideo_ChkBox.getAttribute("checked")));
			defaultControls.put("Remote Audio",
					Boolean.parseBoolean(CreateRoom_IOS.remoteAudio_ChkBox.getAttribute("checked")));
			defaultControls.put("Remote Video",
					Boolean.parseBoolean(CreateRoom_IOS.remoteVideo_ChkBox.getAttribute("checked")));
		} else if (driver instanceof AndroidDriver) {
			defaultControls.put("Local Audio",
					Boolean.parseBoolean(CreateRoom_Mobile.localAudio_ChkBox.getAttribute("checked")));
			defaultControls.put("Local Video",
					Boolean.parseBoolean(CreateRoom_Mobile.localVideo_ChkBox.getAttribute("checked")));
			defaultControls.put("Remote Audio",
					Boolean.parseBoolean(CreateRoom_Mobile.remoteAudio_ChkBox.getAttribute("checked")));
			defaultControls.put("Remote Video",
					Boolean.parseBoolean(CreateRoom_Mobile.remoteVideo_ChkBox.getAttribute("checked")));
		}
	}

	public String enterRoomNameAndStart(AppiumDriver driver) {		
		Random random = new Random();
		String roomName = "Session" + random.nextInt(10000);
		if (driver instanceof IOSDriver) {
			CreateRoom_IOS.roomName.sendKeys(roomName);
			driver.hideKeyboard();
			CreateRoom_IOS.startBtn.click();
		} else if (driver instanceof AndroidDriver) {
		CreateRoom_Mobile.roomName.sendKeys(roomName);		
			driver.hideKeyboard();
		CreateRoom_Mobile.startBtn.click();
		}
		command.delay(5000);
		return roomName;
	}

	public void selectMediaControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		mediaControls.clear();
		if (lAudio.equalsIgnoreCase("Yes")) {
			if (CreateRoom_Mobile.localAudio_ChkBox.getAttribute("checked").equals("false")) {
				CreateRoom_Mobile.localAudio_ChkBox.click();
			}
			mediaControls.put("Local Audio", true);
		} else {
			if (CreateRoom_Mobile.localAudio_ChkBox.getAttribute("checked").equals("true")) {
				CreateRoom_Mobile.localAudio_ChkBox.click();
			}
			mediaControls.put("Local Audio", false);
		}
		if (lVideo.equalsIgnoreCase("Yes")) {
			if (CreateRoom_Mobile.localVideo_ChkBox.getAttribute("checked").equals("false")) {
				CreateRoom_Mobile.localVideo_ChkBox.click();
			}
			mediaControls.put("Local Video", true);
		} else {
			if (CreateRoom_Mobile.localVideo_ChkBox.getAttribute("checked").equals("true")) {
				CreateRoom_Mobile.localVideo_ChkBox.click();
			}
			mediaControls.put("Local Video", false);
		}
		if (rAudio.equalsIgnoreCase("Yes")) {
			if (CreateRoom_Mobile.remoteAudio_ChkBox.getAttribute("checked").equals("false")) {
				CreateRoom_Mobile.remoteAudio_ChkBox.click();
			}
			mediaControls.put("Remote Audio", true);
		} else {
			if (CreateRoom_Mobile.remoteAudio_ChkBox.getAttribute("checked").equals("true")) {
				CreateRoom_Mobile.remoteAudio_ChkBox.click();
			}
			mediaControls.put("Remote Audio", false);
		}
		if (rVideo.equalsIgnoreCase("Yes")) {
			if (CreateRoom_Mobile.remoteVideo_ChkBox.getAttribute("checked").equals("false")) {
				CreateRoom_Mobile.remoteVideo_ChkBox.click();
			}
			mediaControls.put("Remote Video", true);
		} else {
			if (CreateRoom_Mobile.remoteVideo_ChkBox.getAttribute("checked").equals("true")) {
				CreateRoom_Mobile.remoteVideo_ChkBox.click();
			}
			mediaControls.put("Remote Video", false);
		}
	}

	public synchronized void terminateSession() {
		CreateRoom_Mobile.sessionCloseIcon.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickBackFromSession() {
		CreateRoom_Mobile.BackFromSession.click();
	}

	public void Alert_BackFromSession(AppiumDriver<MobileElement> driver, String action) {
		if (action.equalsIgnoreCase("cancel")) {
			CreateRoom_Mobile.CancelSessionPopup.click();
		} else if (action.equalsIgnoreCase("close")) {
			CreateRoom_Mobile.ConfirmToCloseSession.click();
		}
	}

	public Double getZoomScaleValue() {
		Double value = null;
		String zoomScale = CreateRoom.zoomScale.getAttribute("style").split("transform: ")[1];
		System.out.println("----------------" + zoomScale);
		String regex = "(scale)(\\()([0-9]*)(\\.*)([0-9]*)(\\))(.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(zoomScale);
		if (m.matches()) {
			value = Double.parseDouble(m.group(3) + m.group(4) + m.group(5));
		} else {
			System.out.println(m.group(0));
		}
		return value;
	}

	public void zoomAction(AppiumDriver<MobileElement> driver, String action) {
		if (action.equals("in")) {
			// driver.ge
		} else if (action.equals("out")) {
			CreateRoom.zoomOutBtn.click();
		} else if (action.equals("reset")) {
			CreateRoom.zoomResetBtn.click();
		}
	}

	public void navigateToMenu(String menu, AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			if (menu.equalsIgnoreCase("Feeds")) {
				CreateRoom_IOS.feedsMenu.click();

			} else if (menu.equalsIgnoreCase("Settings")) {
				CreateRoom_IOS.settingsIcon.click();
			}
		} else if (driver instanceof AndroidDriver) {
			if (menu.equalsIgnoreCase("Feeds")) {
				CreateRoom_Mobile.feedsMenu.click();

			} else if (menu.equalsIgnoreCase("Settings")) {
				CreateRoom_Mobile.settingsIcon.click();
			}
		}
	}

	public void navigateToSettings() {
		CreateRoom_Mobile.settingsIcon.click();
	}

	public void selectSLServer(String server, AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			if (!server.equalsIgnoreCase("WebRTC")) {
				if (CreateRoom_IOS.useSLServer.getAttribute("text").equals("OFF"))
					CreateRoom_IOS.useSLServer.click();
			} else if (server.equalsIgnoreCase("WebRTC")) {
				if (CreateRoom_IOS.useSLServer.getAttribute("text").equals("ON"))
					CreateRoom_IOS.useSLServer.click();
			}
			CreateRoom_IOS.backFromSettingsBtn.click();
		} else if (driver instanceof AndroidDriver) {
			if (!server.equalsIgnoreCase("WebRTC")) {
				if (CreateRoom_Mobile.useSLServer.getAttribute("text").equals("OFF"))
					CreateRoom_Mobile.useSLServer.click();
			} else if (server.equalsIgnoreCase("WebRTC")) {
				if (CreateRoom_Mobile.useSLServer.getAttribute("text").equals("ON"))
					CreateRoom_Mobile.useSLServer.click();
			}
			CreateRoom_Mobile.backFromSettingsBtn.click();
		}
	}

	public String getImage(String image) {
		// Eyes eyes = new Eyes();
		String img = null;
		// eyes.setApiKey("L98ZH45GlWv108lhO4wE44Uy66mcvNXg57q3VDGUrSxqs110");

		try {
			img = getReferenceImageB64(image);
		} catch (Exception e) {

		}
		return img;
	}

	public By sessionOnFeeds() {
		String xpathExpression = "xpath=//android.widget.TextView[@text='" + GlobalVariables.roomName + "']";
		System.out.println(xpathExpression);		
		return By.xpath(xpathExpression);
	}
}
