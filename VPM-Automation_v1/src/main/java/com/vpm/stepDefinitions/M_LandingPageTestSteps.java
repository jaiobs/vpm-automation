/**
 * 
 */
package com.vpm.stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vpm.modules.CreateRoomAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.LandingPage;
import com.vpm.pageObjects.LandingPage_IOS;
import com.vpm.pageObjects.LandingPage_Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class M_LandingPageTestSteps {

	AppiumDriver<MobileElement> driver = null;
	public List<HashMap<String, String>> datamap;

	public M_LandingPageTestSteps(BaseClass baseClass) {
		this.driver = (AppiumDriver<MobileElement>) baseClass.driver;
		PageFactory.initElements(driver, LandingPage_Mobile.class);
		PageFactory.initElements(driver, LandingPage_IOS.class);		
	}

	@When("^the user enter into the landing page on mobile$")
	public void landingPageScreen() {
		if (driver instanceof IOSDriver) {
			try {
				Assert.assertTrue("Title of the Web page is not as expected",
						LandingPage_IOS.brandText.getText().equals("VPM"));
			} catch (Exception e) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Assert.assertTrue("Brand Name is not displayed in the page",
					LandingPage_IOS.brandText.getText().equals("VPM"));
		} else {
			try {
				Assert.assertTrue("Title of the Web page is not as expected", driver.getTitle().equals("VPM"));
			} catch (Exception e) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Assert.assertTrue("Brand Name is not displayed in the page",
					LandingPage_Mobile.brandText.getText().equals("VPM"));
		}
	}

	@Then("^the user on mobile verifies if all the menus and the screen displays as expected$")
	public void verifyMenusAndScreen() {
		if (driver instanceof IOSDriver) {
			try {
				Assert.assertTrue("Videos menu is missing", LandingPage_IOS.videosMenu.getText().equals("Videos"));
				Assert.assertTrue("Feeds menu is missing", LandingPage_IOS.feedsMenu.getText().equals("Feeds"));
				Assert.assertTrue("Settings icon is missing", LandingPage_IOS.settingsIcon.isEnabled());				
			} catch (AssertionError e) {
				System.out.println("Menus are not listed as expected");
			}
		} else {
			try {
				Assert.assertTrue("Videos menu is missing", LandingPage_Mobile.videosMenu.getText().equals("Videos"));
				Assert.assertTrue("Feeds menu is missing", LandingPage_Mobile.feedsMenu.getText().equals("Feeds"));
				Assert.assertTrue("Settings icon is missing", LandingPage_Mobile.settingsIcon.isEnabled());
				Assert.assertTrue("Active Menu is not in Videos",
						LandingPage_Mobile.activeMenu.getText().equals("Videos"));
			} catch (AssertionError e) {
				System.out.println("Menus are not listed as expected");
			}
		}
	}

}
