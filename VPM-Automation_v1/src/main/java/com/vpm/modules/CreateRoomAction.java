/**
 * 
 */
package com.vpm.modules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;
import com.vpm.pageObjects.Settings;
import com.vpm.utilities.AudioUtilities;
import com.vpm.utilities.MediaUtilities;

/**
 * @author maithili.s
 *
 */
public class CreateRoomAction {

	public static Map<String, Boolean> defaultControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> mediaControls = new HashMap<String, Boolean>();
	AudioUtilities mediaUtilities = new AudioUtilities();

	public synchronized void clickCreateRoom() {
		CreateRoom.createRoomBtn.click();
	}

	public synchronized void getDefaultControls() {
		defaultControls.put("Local Audio", CreateRoom.localAudio_ChkBox.isSelected());
		defaultControls.put("Local Video", CreateRoom.localVideo_ChkBox.isSelected());
		defaultControls.put("Remote Audio", CreateRoom.remoteAudio_ChkBox.isSelected());
		defaultControls.put("Remote Video", CreateRoom.remoteVideo_ChkBox.isSelected());
	}

	public String enterRoomNameAndStart() {
		Random random = new Random();
		String roomName = "Session" + random.nextInt(10000);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CreateRoom.roomName.sendKeys(roomName);
		CreateRoom.startBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomName;
	}

	public void selectMediaControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		mediaControls.clear();
		if (lAudio.equalsIgnoreCase("Yes")) {
			if (!CreateRoom.localAudio_ChkBox.isSelected()) {
				CreateRoom.localAudio_ChkBox.click();
			}
			mediaControls.put("Local Audio", true);
		} else {
			if (CreateRoom.localAudio_ChkBox.isSelected()) {
				CreateRoom.localAudio_ChkBox.click();
			}
			mediaControls.put("Local Audio", false);
		}
		if (lVideo.equalsIgnoreCase("Yes")) {
			if (!CreateRoom.localVideo_ChkBox.isSelected()) {
				CreateRoom.localVideo_ChkBox.click();
			}
			mediaControls.put("Local Video", true);
		} else {
			if (CreateRoom.localVideo_ChkBox.isSelected()) {
				CreateRoom.localVideo_ChkBox.click();
			}
			mediaControls.put("Local Video", false);
		}
		if (rAudio.equalsIgnoreCase("Yes")) {
			if (!CreateRoom.remoteAudio_ChkBox.isSelected()) {
				CreateRoom.remoteAudio_ChkBox.click();
			}
			mediaControls.put("Remote Audio", true);
		} else {
			if (CreateRoom.remoteAudio_ChkBox.isSelected()) {
				CreateRoom.remoteAudio_ChkBox.click();
			}
			mediaControls.put("Remote Audio", false);
		}
		if (rVideo.equalsIgnoreCase("Yes")) {
			if (!CreateRoom.remoteVideo_ChkBox.isSelected()) {
				CreateRoom.remoteVideo_ChkBox.click();
			}
			mediaControls.put("Remote Video", true);
		} else {
			if (CreateRoom.remoteVideo_ChkBox.isSelected()) {
				CreateRoom.remoteVideo_ChkBox.click();
			}
			mediaControls.put("Remote Video", false);
		}		
	}

	public synchronized void terminateSession() {
		CreateRoom.sessionCloseIcon.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickBackFromSession() {
		CreateRoom.BackFromSession.click();
	}

	public void Alert_BackFromSession(WebDriver driver, String action) {
		if (action.equalsIgnoreCase("cancel")) {
			CreateRoom.CancelSessionPopup.click();
		} else if (action.equalsIgnoreCase("close")) {
			CreateRoom.ConfirmToCloseSession.click();
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

	public void zoomAction(String action) {
		if (action.equals("in")) {
			CreateRoom.zoomInBtn.click();
		} else if (action.equals("out")) {
			CreateRoom.zoomOutBtn.click();
		} else if (action.equals("reset")) {
			CreateRoom.zoomResetBtn.click();
		}
	}

	public void navigateToMenu(String menu) {
		if (menu.equalsIgnoreCase("Feeds")) {
			CreateRoom.feedsMenu.click();

		} else if (menu.equalsIgnoreCase("Settings")) {
			CreateRoom.settingsMenu.click();
		}
	}

	public void navigateToSettings() {
		Settings.settingsMenu.click();
	}

	public void selectSLServer(String server) {
		if (!server.equalsIgnoreCase("WebRTC")) {
			CreateRoom.useSLServer.click();
		} else if (server.equalsIgnoreCase("WebRTC")) {
			CreateRoom.doNotUseSLServer.click();
		}

	}

	public void clickBackFromServerSettings() {

	}

	public void validateField(String state) {
		if (state.equals("disabed"))
			Assert.assertFalse("Server IP Address is enabled to enter value on DoNotUseSLServer Option",
					CreateRoom.serverIPAddress.isEnabled());
		else if (state.equals("enabled"))
			Assert.assertTrue("Server IP Address is disabled on UseSLServer Option",
					CreateRoom.serverIPAddress.isEnabled());
	}

	public void recordAudio() {
		CreateRoom.audioIcon.click();
		mediaUtilities.audioCapture("RecordAudio_Creator");
		CreateRoom.audioIcon.click();
	}
	
	public void validateAudio() {
		AudioFile f = null;		
		try {		
			f = AudioFileIO.read(new File("RecordAudio_Creator.wav"));
		} catch (Exception e) {
			System.out.println("Cannot read the audio file provided");
		}
      //  AudioHeader ah = f.getAudioHeader();
      //  System.out.println("****Creator File**********"+ah.getTrackLength()+"*******************");
	}
}
