/**
 * 
 */
package com.vpm.modules;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;
import com.vpm.pageObjects.Settings;
import com.vpm.utilities.MediaUtilities;

/**
 * @author maithili.s
 *
 */
public class JoinRoomAction {
	
	public static Map<String, Boolean> joiner_defaultControls = new HashMap<String, Boolean>();
	public static Map<String, Boolean> joiner_mediaControls = new HashMap<String, Boolean>();
	MediaUtilities mediaUtilities = new MediaUtilities();
	
	public void clickJoinRoom() {
		JoinRoom.joinRoomBtn.click();
	}
	
	public void getDefaultControls() {
		joiner_defaultControls.put("Local Audio", JoinRoom.localAudio_ChkBox.isSelected());
		joiner_defaultControls.put("Local Video", JoinRoom.localVideo_ChkBox.isSelected());
		joiner_defaultControls.put("Remote Audio", JoinRoom.remoteAudio_ChkBox.isSelected());
		joiner_defaultControls.put("Remote Video", JoinRoom.remoteVideo_ChkBox.isSelected());
	}
	
	public void enterRoomNameAndStart(String roomId) {
		if(GlobalVariables.isRoomCreated) {
			try {			
					JoinRoom.roomName.sendKeys(roomId);
					JoinRoom.startBtn.click();													
			} catch (Exception e) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(GlobalVariables.isRoomCreated) {
					JoinRoom.roomName.sendKeys(roomId);
					JoinRoom.startBtn.click();
				// TODO Auto-generated catch block
				}
			}
		  }	else {
			  try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} if(GlobalVariables.isRoomCreated) {
			  JoinRoom.roomName.sendKeys(roomId);
			JoinRoom.startBtn.click();
			}
		  }
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
	
	public void selectMediaControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		joiner_mediaControls.clear();
		if (lAudio.equalsIgnoreCase("Yes")) {
			if (!JoinRoom.localAudio_ChkBox.isSelected()) {
				JoinRoom.localAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Local Audio", true);
		} else {
			if (JoinRoom.localAudio_ChkBox.isSelected()) {
				JoinRoom.localAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Local Audio", false);
		}
		if (lVideo.equalsIgnoreCase("Yes")) {
			if (!JoinRoom.localVideo_ChkBox.isSelected()) {
				JoinRoom.localVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Local Video", true);
		} else {
			if (JoinRoom.localVideo_ChkBox.isSelected()) {
				JoinRoom.localVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Local Video", false);
		}
		if (rAudio.equalsIgnoreCase("Yes")) {
			if (!JoinRoom.remoteAudio_ChkBox.isSelected()) {
				JoinRoom.remoteAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Audio", true);
		} else {
			if (JoinRoom.remoteAudio_ChkBox.isSelected()) {
				JoinRoom.remoteAudio_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Audio", false);
		}
		if (rVideo.equalsIgnoreCase("Yes")) {
			if (!JoinRoom.remoteVideo_ChkBox.isSelected()) {
				JoinRoom.remoteVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Video", true);
		} else {
			if (JoinRoom.remoteVideo_ChkBox.isSelected()) {
				JoinRoom.remoteVideo_ChkBox.click();
			}
			joiner_mediaControls.put("Remote Video", false);
		}
	}

	public void terminateSession() {
		JoinRoom.sessionCloseIcon.click();
	}	
	
	public void clickBackFromSession() {
		JoinRoom.BackFromSession.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void backToLiveSession() {
		Feeds.sessionHistory.get(0).click();
	}
	
	public void selectSLServer(String server) {
		if(!server.equalsIgnoreCase("WebRTC")) {
			JoinRoom.useSLServer.click();
		}
			else if(server.equalsIgnoreCase("WebRTC"))
				JoinRoom.doNotUseSLServer.click();
	}
	
	public void validateField(String state) {
		if(state.equals("disabed")) 
			Assert.assertFalse("Server IP Address is enabled to enter value on DoNotUseSLServer Option", 
					JoinRoom.serverIPAddress.isEnabled());
		else if(state.equals("enabled"))
			Assert.assertTrue("Server IP Address is disabled on UseSLServer Option", 
					JoinRoom.serverIPAddress.isEnabled());
	}

	public void recordAudio() {
		JoinRoom.audioIcon.click();
		mediaUtilities.audioCapture("RecordAudio_Joiner.wav");
	}
	
	public void validateAudio() {
		AudioFile f = null;		
		try {		
			f = AudioFileIO.read(new File("RecordAudio_Joiner.wav"));
		} catch (Exception e) {
			System.out.println("Cannot read the audio file provided");
		}
        AudioHeader ah = f.getAudioHeader();
        System.out.println("****Joiner File**********"+ah.getTrackLength()+"*******************");
        try {
			AudioInputStream in = AudioSystem.getAudioInputStream(new File("Record_Joiner.wav"));
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
