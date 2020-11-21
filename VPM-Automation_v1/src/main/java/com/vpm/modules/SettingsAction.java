/**
 * 
 */
package com.vpm.modules;

import org.junit.Assert;

import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.Settings;

import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class SettingsAction {
	
	public void navigateToSettings() {		
		Settings.settingsMenu.click();
	}
	
	public void selectSLServer(String option) {
		if(option.equalsIgnoreCase("Yes")) {
			CreateRoom.useSLServer.click();
		}
			else if(option.equalsIgnoreCase("No")) {
				CreateRoom.doNotUseSLServer.click();
			} 
	}
	
	public void validateField(String state) {
		if(state.equals("disabed")) 
			Assert.assertFalse("Server IP Address is enabled to enter value on DoNotUseSLServer Option", 
					CreateRoom.serverIPAddress.isEnabled());
		else if(state.equals("enabled"))
			Assert.assertTrue("Server IP Address is disabled on UseSLServer Option", 
					CreateRoom.serverIPAddress.isEnabled());
	}

}
