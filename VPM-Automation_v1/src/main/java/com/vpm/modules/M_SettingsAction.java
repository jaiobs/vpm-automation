/**
 * 
 */
package com.vpm.modules;

import org.junit.Assert;
import org.openqa.selenium.Dimension;

import com.vpm.pageObjects.CreateRoom_IOS;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.Settings;
import com.vpm.pageObjects.Settings_Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class M_SettingsAction {
	
	public void navigateToSettings(AppiumDriver driver) {
		if(driver instanceof IOSDriver) {
			CreateRoom_IOS.settingsIcon.click();
		} else {
		CreateRoom_Mobile.settingsIcon.click();
		}
	}
	
	public void selectSLServer(String server) {
		if (!server.equalsIgnoreCase("WebRTC")) {
			if (CreateRoom_Mobile.useSLServer.getAttribute("text").equals("OFF"))
				CreateRoom_Mobile.useSLServer.click();
		} else if (server.equalsIgnoreCase("WebRTC")) {
			if (CreateRoom_Mobile.useSLServer.getAttribute("text").equals("ON"))
				CreateRoom_Mobile.useSLServer.click();
		}
		CreateRoom_Mobile.backFromSettingsBtn.click(); 
	}
	
	public void validateField(AppiumDriver driver) {
		if(driver instanceof IOSDriver) {			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue("Server IP Address text is not enabled to enter value", 
					CreateRoom_IOS.serverIpAddressText.isEnabled());									
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} else {
			CreateRoom_Mobile.serverIpAddressMenu.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue("Server IP Address text is not enabled to enter value", 
					CreateRoom_Mobile.serverIpAddressText.isEnabled());
			CreateRoom_Mobile.roomServer_OkBtn.click();
			Assert.assertTrue("Server IP Address text popup not closed on clicking OK button", 
					CreateRoom_Mobile.serverIpAddressMenu.isDisplayed());
			CreateRoom_Mobile.serverIpAddressMenu.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CreateRoom_Mobile.roomServer_CancelBtn.click();
			Assert.assertTrue("Server IP Address text popup not closed on clicking CANCEL button", 
					CreateRoom_Mobile.serverIpAddressMenu.isDisplayed());
		}
	}
	
	public void selectOption(String option, AppiumDriver<MobileElement> driver) {
		swipeTo(driver, 0.2);
		if (option.equalsIgnoreCase("Load from vpm/videos")) {
			if (Settings_Mobile.loadFromVpmSwitch.getAttribute("text").equals("OFF"))
				Settings_Mobile.loadFromVpmSwitch.click();
			if(Settings_Mobile.loadHttpFromJsonSwitch.getAttribute("text").equals("ON"))
				Settings_Mobile.loadHttpFromJsonSwitch.click();
			if(Settings_Mobile.loadRtspFromJsonSwitch.getAttribute("text").equals("ON"))
				Settings_Mobile.loadRtspFromJsonSwitch.click();
		} else if (option.equalsIgnoreCase("Load HTTP from Json")) {
			if (Settings_Mobile.loadHttpFromJsonSwitch.getAttribute("text").equals("OFF"))
				Settings_Mobile.loadHttpFromJsonSwitch.click();
			if(Settings_Mobile.loadFromVpmSwitch.getAttribute("text").equals("ON"))
				Settings_Mobile.loadFromVpmSwitch.click();
			if(Settings_Mobile.loadRtspFromJsonSwitch.getAttribute("text").equals("ON"))
				Settings_Mobile.loadRtspFromJsonSwitch.click();
		} else if (option.equalsIgnoreCase("Load RTSP from Json")) {
			if (Settings_Mobile.loadRtspFromJsonSwitch.getAttribute("text").equals("OFF"))
				Settings_Mobile.loadRtspFromJsonSwitch.click();
			if(Settings_Mobile.loadFromVpmSwitch.getAttribute("text").equals("ON"))
				Settings_Mobile.loadFromVpmSwitch.click();
			if(Settings_Mobile.loadHttpFromJsonSwitch.getAttribute("text").equals("ON"))
				Settings_Mobile.loadHttpFromJsonSwitch.click();
		} else if(option.equalsIgnoreCase("All")) {
			if (Settings_Mobile.loadRtspFromJsonSwitch.getAttribute("text").equals("OFF"))
				Settings_Mobile.loadRtspFromJsonSwitch.click();
			if(Settings_Mobile.loadFromVpmSwitch.getAttribute("text").equals("OFF"))
				Settings_Mobile.loadFromVpmSwitch.click();
			if(Settings_Mobile.loadHttpFromJsonSwitch.getAttribute("text").equals("OFF"))
				Settings_Mobile.loadHttpFromJsonSwitch.click();
		}
	}

	public void backFromSettings() {
		Settings_Mobile.backFromSettingsBtn.click();
	}
	
	public void swipeTo(AppiumDriver<MobileElement> driver, Double swipeToValue) {
		TouchAction action = new TouchAction(driver);
		PointOption p1= new PointOption();
		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.6;
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * swipeToValue;
		int h2 = screenHeightEnd.intValue();
		action.press(p1.point(0, h1)).moveTo(p1.point(0,h2)).release().perform();
	}
}
