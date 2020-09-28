/**
 * 
 */
package modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CreateRoom;
import pageObjects.GlobalVariables;

/**
 * @author maithili.s
 *
 */
public class CreateRoomAction {
	
	public static Map<String, Boolean> mediaControls = new HashMap<String, Boolean>();
	
	public void clickCreateRoom() {
		CreateRoom.createRoomBtn.click();
	}

	public void getDefaultControls() {
		mediaControls.put("Local Audio", CreateRoom.localAudio_ChkBox.isSelected());
		mediaControls.put("Local Video", CreateRoom.localVideo_ChkBox.isSelected());
		mediaControls.put("Remote Audio", CreateRoom.remoteAudio_ChkBox.isSelected());
		mediaControls.put("Remote Video", CreateRoom.remoteVideo_ChkBox.isSelected());		
	}
	
	public void enterRoomNameAndStart() {
		getDefaultControls();
		Random random = new Random(); 
		GlobalVariables.roomName = "Room_" +random.nextInt(10000);
		CreateRoom.roomName.sendKeys(GlobalVariables.roomName);
		CreateRoom.startBtn.click();	
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
