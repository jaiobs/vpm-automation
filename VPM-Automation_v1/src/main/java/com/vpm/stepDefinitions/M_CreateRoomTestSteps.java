/**
 * 
 */
package com.vpm.stepDefinitions;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.vpm.managers.ConfigManager;
import com.vpm.modules.CreateRoomAction;
import com.vpm.modules.M_CreateRoomAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CommonMethods;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.CreateRoom_IOS;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.Feeds_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.utilities.MediaUtilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

/**
 * @author maithili.s
 *
 */
public class M_CreateRoomTestSteps {

	AppiumDriver<MobileElement> driver = null;
	AppiumDriver<MobileElement> driver1 = null;
	M_CreateRoomAction createRoomAction = new M_CreateRoomAction();
	MediaUtilities mediaUtilities = new MediaUtilities();
	CommonMethods command = new CommonMethods();
	SoftAssert Assert = new SoftAssert();

	public M_CreateRoomTestSteps(BaseClass baseClass) {
		this.driver = (AppiumDriver<MobileElement>) baseClass.driver;
		PageFactory.initElements(driver, CreateRoom_Mobile.class);
		PageFactory.initElements(driver, CreateRoom_IOS.class);
		PageFactory.initElements(driver, Feeds.class);
		this.driver1 = (AppiumDriver<MobileElement>) baseClass.driver1;
	}

	@Given("the user is on vpm landing page on mobile")
	public void openLandingPage() {
		if (driver instanceof IOSDriver) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(CreateRoom_IOS.settingsIcon));
			Assert.assertTrue(CreateRoom_IOS.settingsIcon.isDisplayed(), "vpm landing page loads slow/not loaded");
		} else {
			Assert.assertTrue(CreateRoom_Mobile.settingsIcon.isDisplayed(), "vpm landing page loads slow/not loaded");
		}
	}

	@When("^the creator navigates to \"(.*?)\" page on mobile$")
	public void navigateTo(String menu) {
		createRoomAction.navigateToMenu(menu, driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("^the creator selects the \"(.*?)\" server in the Server option on mobile$")
	public void selectServer(String server) {
		createRoomAction.selectSLServer(server, driver);
	}

	@When("^the creator selects the server in the Server option on mobile$")
	public void selectSLServerOption() {
		createRoomAction.selectSLServer(GlobalVariables.server.get(0), driver);
	}

	@When("^the creator enter into the create room form on mobile$")
	public void createRoomForm() {
		createRoomAction.clickCreateRoom(driver);
		command.delay(5000);
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			command.delay(2000);
			driver.hideKeyboard();
		}
	}

	@Then("^the creator check if all the default controls are enabled on mobile$")
	public void verifyDefaultControls() {
		try {
			createRoomAction.getDefaultControls(driver);
		} catch (Exception e) {
			command.delay(5000);
			driver.hideKeyboard();
			createRoomAction.getDefaultControls(driver);
		}
		driver.hideKeyboard();
		for (Entry<String, Boolean> entry : M_CreateRoomAction.defaultControls.entrySet()) {
			if (entry.getKey().equals("Local Audio") || entry.getKey().equals("Local Video")
					|| entry.getKey().equals("Remote Audio")) {
				Assert.assertTrue(entry.getValue(), entry.getKey() + " is not selected, by default");
			}
			if (entry.getKey().equals("Remote Video")) {
				Assert.assertFalse(entry.getValue(), entry.getKey() + " is selected, by default");
			}
		}
		M_CreateRoomAction.mediaControls = M_CreateRoomAction.defaultControls;
	}

	@Then("^the creator enter the new room name and start on mobile$")
	public void enterRoom() {
		GlobalVariables.roomName = createRoomAction.enterRoomNameAndStart(driver);
	}

	@Then("^the creator should have entered into the new room started on mobile$")
	public void verifyRoomEntered() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if (driver instanceof IOSDriver) {
			wait.until(ExpectedConditions.visibilityOf(CreateRoom_IOS.sessionHeader));
			Assert.assertTrue(CreateRoom_IOS.sessionHeader.getText().contains(GlobalVariables.roomName),
					"Room name not found in the session");
		} else {
			wait.until(ExpectedConditions.visibilityOf(CreateRoom_Mobile.sessionHeader));
			Assert.assertTrue(CreateRoom_Mobile.sessionHeader.getText().contains(GlobalVariables.roomName),
					"Room name not found in the session");
		}
		GlobalVariables.isRoomCreated = true;

	}

	@Then("^the creator select the controls \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" on mobile$")
	public void selectControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		createRoomAction.selectMediaControls(lAudio, lVideo, rAudio, rVideo);
	}

	@Then("^the creator verifies if the media controls works on mobile$")
	public void verifyMediaControls() {
		System.out.println(driver.getPageSource());
		for (Entry<String, Boolean> entry : M_CreateRoomAction.mediaControls.entrySet()) {
			if (entry.getKey().equals("Local Audio")) {
				if (entry.getValue()) {
					Assert.assertTrue(
							driver.findElementByImage(createRoomAction.getImage("MikeMuteIcon.png")).isDisplayed(),
							"Audio is in mute");
				} else {
					Assert.assertTrue(
							driver.findElementByImage(createRoomAction.getImage("MikeIcon.png")).isDisplayed(),
							"Audio is in unmute");
				}
			}
			if (entry.getKey().equals("Local Video")) {
				if (entry.getValue()) {
					Assert.assertTrue(
							driver.findElementByImage(createRoomAction.getImage("VideoMuteIcon.png")).isDisplayed(),
							"Video is in mute");
				} else {
					Assert.assertTrue(
							driver.findElementByImage(createRoomAction.getImage("VideoIcon.png")).isDisplayed(),
							"Video is in unmute");
				}
			}
			if (entry.getKey().equals("Remote Video")) {/*
				if (entry.getValue()) {
					if (driver instanceof IOSDriver) {
						Assert.assertTrue(CreateRoom_IOS.remoteVideoMuteIcon.isEmpty(), "Video is in mute");
					} else if (driver instanceof AndroidDriver) {
						Assert.assertTrue(CreateRoom_Mobile.remoteVideoMuteIcon.isEmpty(), "Video is in mute");
					}
				} else {
					if (driver instanceof IOSDriver) {
						Assert.assertFalse(CreateRoom_IOS.remoteVideoMuteIcon.isEmpty(), "Video is in unmute");
					} else if (driver instanceof AndroidDriver) {
						Assert.assertFalse(CreateRoom_Mobile.remoteVideoMuteIcon.isEmpty(), "Video is in unmute");
					}
				}
			*/}
		}
	}

	@Then("^the creator terminates the session on mobile$")
	public void terminateSession() {
		if (GlobalVariables.isJoined) {
			try {
				Thread.sleep(5000);
				createRoomAction.terminateSession();
				GlobalVariables.isSessionTerminated = true;
				System.out.println("----------------------Session Terminated---------------------------");
			} catch (Exception e) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				createRoomAction.terminateSession();
				GlobalVariables.isSessionTerminated = true;
			}
		} else {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(2, TimeUnit.MINUTES)
					.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.textToBePresentInElement(CreateRoom.alertMsg, "Peer: Connected"));
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (GlobalVariables.isJoined) {
				createRoomAction.terminateSession();
				GlobalVariables.isSessionTerminated = true;
				System.out.println("----------------------Session Terminated---------------------------");
			}
		}
		// TODO Auto-generated catch block
	}

	@Then("^the creator verifies if the video info displays in the feeds on mobile$")
	public void verifyFeedTileInfo() {
		/*Assert.assertTrue(driver.findElement(createRoomAction.sessionOnFeeds()).isDisplayed(),
				"Camera Icon on feeds not present");*/
	}

	@When("^the creator clicks back from the session on mobile$")
	public void backFromSession() {
		createRoomAction.clickBackFromSession();
	}

	@Then("^the creator clicks \"(.*?)\" on the session exit popup on mobile$")
	public void actionOnTheSessionExit(String actionBtn) {
		command.delay(5000);
		createRoomAction.Alert_BackFromSession(driver, actionBtn);
	}

	@Then("^the creator should stay back in the session on mobile$")
	public void verifySession_StayBack() {
		Assert.assertTrue(CreateRoom_Mobile.sessionHeader.getText().contains(GlobalVariables.roomName),
				"Room name not found in the session");
	}

	@When("^the creator zoom \"(.*?)\" the video on mobile$")
	public void actionZoom(String action) {
		GlobalVariables.previousZoomValue = createRoomAction.getZoomScaleValue();
		// createRoomAction.zoomAction(action);
		GlobalVariables.zoomValue = createRoomAction.getZoomScaleValue();
	}

	@Then("the creator verifies if the video zoom \"(.*?)\" on mobile$")
	public void verifyVideoZoom(String action) {
		if (action.equals("in")) {
			if (GlobalVariables.zoomValue > GlobalVariables.previousZoomValue) {
				Assert.assertTrue(true, "Zoom in not works as expected");
			} else {
				Assert.assertTrue(false, "Zoom in works as expected");
			}
		} else if (action.equals("out")) {
			if (GlobalVariables.zoomValue < GlobalVariables.previousZoomValue) {
				Assert.assertTrue(true, "Zoom out not works as expected");
			} else {
				Assert.assertTrue(false, "Zoom out works as expected");
			}
		} else if (action.equals("reset")) {
			if (GlobalVariables.zoomValue == 1) {
				Assert.assertTrue(true, "Zoom reset not works as expected");
			} else {
				Assert.assertTrue(false, "Zoom reset works as expected");
			}
		}
	}

	@Then("^the creator is displayed with \"(.*?)\" session feeds on the history on mobile$")
	public void verifySessionHistory(int number) {
		int noOfSessions = Feeds_Mobile.sessionHistory.size();
		command.swipeTo(driver, 0.2);
		noOfSessions = noOfSessions + Feeds_Mobile.sessionHistory.size();
		if (noOfSessions+1 != number) {
			Assert.assertTrue(false,
					"Number of records in the session history not matches with the " + "number of sessions occured");
		}
	}

	@When("^the creator clicks on the \"(.*?)\" menu on mobile$")
	public void clickOnMenu(String menu) {
		createRoomAction.navigateToMenu(menu, driver);
	}

	public final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			driver1.close();
		}
	};

	@When("^the creator creates a new session on mobile$")
	public void createNewSession() {
		GlobalVariables.thirdMobileInstance = true;
		PageFactory.initElements(driver1, CreateRoom_Mobile.class);
		PageFactory.initElements(driver1, Feeds_Mobile.class);
		createRoomAction.navigateToMenu("Settings", driver1);
		command.delay(3000);
		createRoomAction.selectSLServer(GlobalVariables.server.get(0), driver1);
		command.delay(3000);
		createRoomAction.clickCreateRoom(driver1);
		command.delay(3000);
		command.hideKeyboard(driver1);
		GlobalVariables.roomName1 = createRoomAction.enterRoomNameAndStart(driver1);
		command.delay(3000);
	}
}
