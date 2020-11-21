/**
 * 
 */
package com.vpm.modules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CommonMethods;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.CreateRoom_IOS;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.Feeds_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;
import com.vpm.pageObjects.JoinRoom_IOS;
import com.vpm.pageObjects.JoinRoom_Mobile;
import com.vpm.pageObjects.Settings;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * @author maithili.s
 *
 */
public class M_JoinRoomAction extends BaseClass {

	public static Map<String, Boolean> joiner_defaultControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> joiner_mediaControls = new HashMap<String, Boolean>();
	CommonMethods command = new CommonMethods();

	public void clickJoinRoom(AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			JoinRoom_IOS.joinRoomBtn.click();
		} else if (driver instanceof AndroidDriver) {
			JoinRoom_Mobile.joinRoomBtn.click();
		}
	}

	public void getDefaultControls(AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			joiner_defaultControls.put("Local Audio",
					Boolean.parseBoolean(JoinRoom_IOS.localAudio_ChkBox.getAttribute("checked")));
			joiner_defaultControls.put("Local Video",
					Boolean.parseBoolean(JoinRoom_IOS.localVideo_ChkBox.getAttribute("checked")));
			joiner_defaultControls.put("Remote Audio",
					Boolean.parseBoolean(JoinRoom_IOS.remoteAudio_ChkBox.getAttribute("checked")));
			joiner_defaultControls.put("Remote Video",
					Boolean.parseBoolean(JoinRoom_IOS.remoteVideo_ChkBox.getAttribute("checked")));
		} else if (driver instanceof AndroidDriver) {
			joiner_defaultControls.put("Local Audio",
					Boolean.parseBoolean(JoinRoom_Mobile.localAudio_ChkBox.getAttribute("checked")));
			joiner_defaultControls.put("Local Video",
					Boolean.parseBoolean(JoinRoom_Mobile.localVideo_ChkBox.getAttribute("checked")));
			joiner_defaultControls.put("Remote Audio",
					Boolean.parseBoolean(JoinRoom_Mobile.remoteAudio_ChkBox.getAttribute("checked")));
			joiner_defaultControls.put("Remote Video",
					Boolean.parseBoolean(JoinRoom_Mobile.remoteVideo_ChkBox.getAttribute("checked")));
		}
	}

	public void enterRoomNameAndStart(String roomId, AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			JoinRoom_IOS.roomName.sendKeys(roomId);
			driver.hideKeyboard();
			JoinRoom_IOS.startBtn.click();
		} else if (driver instanceof AndroidDriver) {
			JoinRoom_Mobile.roomName.sendKeys(roomId);
			driver.hideKeyboard();
			driver.hideKeyboard();
			JoinRoom_Mobile.startBtn.click();
		}
		
		command.delay(5000);
	}

	public void selectMediaControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		joiner_mediaControls.clear();
		if (lAudio.equalsIgnoreCase("Yes")) {
			if (JoinRoom_Mobile.localAudio_ChkBox.getAttribute("checked").equals("false")) {
				JoinRoom_Mobile.localAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Local Audio", true);
		} else {
			if (JoinRoom_Mobile.localAudio_ChkBox.getAttribute("checked").equals("true")) {
				JoinRoom_Mobile.localAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Local Audio", false);
		}
		if (lVideo.equalsIgnoreCase("Yes")) {
			if (JoinRoom_Mobile.localVideo_ChkBox.getAttribute("checked").equals("false")) {
				JoinRoom_Mobile.localVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Local Video", true);
		} else {
			if (JoinRoom_Mobile.localVideo_ChkBox.getAttribute("checked").equals("true")) {
				JoinRoom_Mobile.localVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Local Video", false);
		}
		if (rAudio.equalsIgnoreCase("Yes")) {
			if (JoinRoom_Mobile.remoteAudio_ChkBox.getAttribute("checked").equals("false")) {
				JoinRoom_Mobile.remoteAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Audio", true);
		} else {
			if (JoinRoom_Mobile.remoteAudio_ChkBox.getAttribute("checked").equals("true")) {
				JoinRoom_Mobile.remoteAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Audio", false);
		}
		if (rVideo.equalsIgnoreCase("Yes")) {
			if (JoinRoom_Mobile.remoteVideo_ChkBox.getAttribute("checked").equals("false")) {
				JoinRoom_Mobile.remoteVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Video", true);
		} else {
			if (JoinRoom_Mobile.remoteVideo_ChkBox.getAttribute("checked").equals("true")) {
				JoinRoom_Mobile.remoteVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Video", false);
		}
	}

	public void terminateSession() {
		JoinRoom_Mobile.sessionCloseIcon.click();
	}

	public void clickBackFromSession() {
		JoinRoom_Mobile.BackFromSession.click();
		command.delay(5000);
	}

	public void backToLiveSession() {
		Feeds_Mobile.sessionHistory.get(0).click();
	}

	public void selectSLServer(String server, AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			if (!server.equalsIgnoreCase("WebRTC")) {
				if (JoinRoom_IOS.useSLServer.getAttribute("text").equals("OFF"))
					JoinRoom_IOS.useSLServer.click();
			} else if (server.equalsIgnoreCase("WebRTC")) {
				if (JoinRoom_IOS.useSLServer.getAttribute("text").equals("ON"))
					JoinRoom_IOS.useSLServer.click();
			}
			JoinRoom_IOS.backFromSettingsBtn.click();
		} else if (driver instanceof AndroidDriver) {
			if (!server.equalsIgnoreCase("WebRTC")) {
				if (JoinRoom_Mobile.useSLServer.getAttribute("text").equals("OFF"))
					JoinRoom_Mobile.useSLServer.click();
			} else if (server.equalsIgnoreCase("WebRTC")) {
				if (JoinRoom_Mobile.useSLServer.getAttribute("text").equals("ON"))
					JoinRoom_Mobile.useSLServer.click();
			}
			JoinRoom_Mobile.backFromSettingsBtn.click();
		}
	}

	public void validateField(String state) {
		/*
		 * if(state.equals("disabed")) Assert.
		 * assertFalse("Server IP Address is enabled to enter value on DoNotUseSLServer Option"
		 * , JoinRoom_Mobile.serverIPAddress.isEnabled()); else
		 * if(state.equals("enabled"))
		 * Assert.assertTrue("Server IP Address is disabled on UseSLServer Option",
		 * JoinRoom_Mobile.serverIPAddress.isEnabled());
		 */
	}

	public void navigateToMenu(String menu, AppiumDriver driver) {
		if (driver instanceof IOSDriver) {
			if (menu.equalsIgnoreCase("Feeds")) {
				JoinRoom_IOS.feedsMenu.click();

			} else if (menu.equalsIgnoreCase("Settings")) {
				JoinRoom_IOS.settingsIcon.click();
			}
		} else if (driver instanceof AndroidDriver) {
			if (menu.equalsIgnoreCase("Feeds")) {
				JoinRoom_Mobile.feedsMenu.click();

			} else if (menu.equalsIgnoreCase("Settings")) {
				JoinRoom_Mobile.settingsIcon.click();
			}
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

}
