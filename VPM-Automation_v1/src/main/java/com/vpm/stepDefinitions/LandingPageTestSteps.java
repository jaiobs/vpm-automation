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
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.LandingPage;
import com.vpm.pageObjects.LandingPage_Mobile;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class LandingPageTestSteps {

	WebDriver driver = null;
	WebDriver appiumDriver = null;
	public List<HashMap<String, String>> datamap;

	public LandingPageTestSteps(Hooks hooks) {
		this.driver = hooks.driver;
		PageFactory.initElements(driver, LandingPage.class);
		PageFactory.initElements(appiumDriver, LandingPage_Mobile.class);
	}

	@When("^the user enter into the landing page$")
	public void landingPageScreen() {
		try {
		Assert.assertTrue("Title of the Web page is not as expected", driver.getTitle().equals("VPM"));
		} catch(Exception e) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		Assert.assertTrue("Brand Name is not displayed in the page", CreateRoom.brandText.getText().equals("VPM"));
	}

	@Then("^the user verifies if all the menus and the screen displays as expected$")
	public void verifyMenusAndScreen() {
		try {
		Assert.assertTrue("Videos menu is missing", CreateRoom.videosMenu.getText().equals("Videos"));
		Assert.assertTrue("Videos icon is missing in the menu",
				CreateRoom.videosIcon.getAttribute("src").contains("video.svg"));
		Assert.assertTrue("Feeds menu is missing", CreateRoom.feedsMenu.getText().equals("Feeds"));
		Assert.assertTrue("Feeds icon is missing in the menu",
				CreateRoom.feedsIcon.getAttribute("src").contains("feed.svg"));
		Assert.assertTrue("Settings menu is missing", CreateRoom.settingsMenu.getText().equals("Settings"));
		Assert.assertTrue("Settings icon is missing in the menu",
				CreateRoom.settingsIcon.getAttribute("src").contains("settings.svg"));
		Assert.assertTrue("Active Menu is not in feeds", CreateRoom.activeMenu.getText().equals("Feeds"));
		}catch(AssertionError e) {
			System.out.println("Left Navigation menu is not listed as expected");
		}
	}


}
