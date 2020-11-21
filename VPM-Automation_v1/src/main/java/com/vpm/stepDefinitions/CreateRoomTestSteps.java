/**
 * 
 */
package com.vpm.stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.vpm.helpers.DataHelper;
import com.vpm.managers.ConfigManager;
import com.vpm.managers.DriverManager;
import com.vpm.modules.CreateRoomAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;
import com.vpm.pageObjects.LandingPage;
import com.vpm.pageObjects.LoginPage;
import com.vpm.utilities.MediaUtilities;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;

/**
 * @author maithili.s
 *
 */
public class CreateRoomTestSteps {

	WebDriver driver = null;
	WebDriver driver1 = null;
	CreateRoomAction createRoomAction = new CreateRoomAction();
	SoftAssert Assert = new SoftAssert();

	public CreateRoomTestSteps(BaseClass baseClass) {
		this.driver = baseClass.driver;
		PageFactory.initElements(driver, CreateRoom.class);
		PageFactory.initElements(driver, Feeds.class);
		this.driver1 = baseClass.driver1;
	}

	@Given("^the user is on vpm landing page$")
	public void openLandingPage() {
		driver.manage().window().maximize();
		driver.get(ConfigManager.getApplicationProperty("url"));
	}

	@When("^the creator navigates to \"(.*?)\" page$")
	public void navigateTo(String menu) {
		createRoomAction.navigateToMenu(menu);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("^the creator selects the server in the Use SL Server option$")
	public void selectSLServerOption() {
		createRoomAction.selectSLServer(GlobalVariables.server.get(0));
	}

	@When("^the creator selects the \"(.*?)\" server in the Use SL Server option$")
	public void selectServer(String server) {
		createRoomAction.selectSLServer(server);
	}

	@Then("^the creator verifies if the server ip address box is \"(.*?)\"$")
	public void verifyIPAddressField(String state) {
		createRoomAction.validateField(state);
	}

	@When("^the creator enter into the create room form$")
	public void createRoomForm() {
		createRoomAction.clickCreateRoom();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Then("^the creator check if all the default controls are enabled$")
	public void verifyDefaultControls() {
		createRoomAction.getDefaultControls();
		for (Entry<String, Boolean> entry : CreateRoomAction.defaultControls.entrySet()) {
			if (entry.getKey().equals("Local Audio") || entry.getKey().equals("Local Video")
					|| entry.getKey().equals("Remote Audio")) {
				Assert.assertTrue(entry.getValue(), entry.getKey() + " is not selected, by default");
			}
			if (entry.getKey().equals("Remote Video")) {
				Assert.assertFalse(entry.getValue(), entry.getKey() + " is selected, by default");
			}
		}
		CreateRoomAction.mediaControls = CreateRoomAction.defaultControls;
	}

	@Then("^the creator enter the new room name and start$")
	public void enterRoom() {
		GlobalVariables.roomName = createRoomAction.enterRoomNameAndStart();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.textToBePresentInElement(CreateRoom.alertMsg,
		 * "Success"));
		 */
	}

	@Then("^the creator should have entered into the new room started$")
	public void verifyRoomEntered() {
		Assert.assertTrue(CreateRoom.sessionHeader.getText().contains(GlobalVariables.roomName),
				"Room name not found in the session");
		Assert.assertTrue(CreateRoom.liveLabel.getText().equalsIgnoreCase("Live"),
				"Live Label not found in the session");
		GlobalVariables.isRoomCreated = true;
	}

	@Then("^the creator select the controls \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void selectControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		createRoomAction.selectMediaControls(lAudio, lVideo, rAudio, rVideo);
	}

	@Then("^the creator verifies if the media controls works$")
	public void verifyMediaControls() {
		/*
		 * String videoId = "localVideoVpmSession"+GlobalVariables.roomName;
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("document.getElementById(videoId)");
		 */

		for (Entry<String, Boolean> entry : CreateRoomAction.mediaControls.entrySet()) {
			if (entry.getKey().equals("Local Audio")) {
				if (entry.getValue()) {
					Assert.assertTrue(CreateRoom.audioIcon.getAttribute("src").contains("mike.svg"),
							"Audio is in mute");
				} else {
					Assert.assertTrue(CreateRoom.audioIcon.getAttribute("src").contains("mike-mute.svg"),
							"Audio is in unmute");
				}
			}
			if (entry.getKey().equals("Local Video")) {
				if (entry.getValue()) {
					Assert.assertTrue(CreateRoom.videoIcon.getAttribute("src").contains("video-cam.svg"),
							"Video is in mute");
				} else {
					Assert.assertTrue(CreateRoom.videoIcon.getAttribute("src").contains("video-mute.svg"),
							"Video is in unmute");
				}
			}
			/*
			 * if (entry.getKey().equals("Remote Audio")) { if (entry.getValue()) {
			 * Assert.assertTrue("Audio is in mute",
			 * CreateRoom.audioIcon.getAttribute("src").contains("mike.svg")); } else {
			 * Assert.assertTrue("Audio is in unmute",
			 * CreateRoom.audioIcon.getAttribute("src").contains("mike-mute.svg")); } }
			 */
			if (entry.getKey().equals("Remote Video")) {
				if (entry.getValue()) {
					// Assert.assertTrue("Video is in mute",
					// CreateRoom.remoteVideoMuteIcon.isEmpty());
				} else {
					// Assert.assertFalse("Video is in unmute",
					// CreateRoom.remoteVideoMuteIcon.isEmpty());
				}
			}
		}
		LogEntries logType = driver.manage().logs().get(LogType.DRIVER);
		for (LogEntry logentry : logType) {
			System.out.println("Console Messages -----------" + logentry);
		}
		// createRoomAction.recordAudio();
		// createRoomAction.validateAudio();
	}

	@Then("^the creator terminates the session$")
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

	@Then("^the creator verifies if the video info displays in the feeds$")
	public void verifyFeedTileInfo() {
		String locator = "div.roomVpmSession" + GlobalVariables.roomName + " div.video-info";
		String videoImgOnTile = "//preceding-sibling::div/div[@class='video-img']/img";
		Assert.assertTrue(driver.findElement(By.cssSelector(locator)).findElement(By.xpath(videoImgOnTile))
				.getAttribute("src").contains("video.svg"), "Feed Tile does not contains video icon in it");
	}

	@When("^the creator clicks back from the session$")
	public void backFromSession() {
		createRoomAction.clickBackFromSession();
	}

	@Then("^the creator clicks \"(.*?)\" on the session exit popup$")
	public void actionOnTheSessionExit(String actionBtn) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createRoomAction.Alert_BackFromSession(driver, actionBtn);
	}

	@Then("^the creator should stay back in the session$")
	public void verifySession_StayBack() {
		Assert.assertTrue(CreateRoom.sessionHeader.getText().contains(GlobalVariables.roomName),
				"Room name not found in the session");
	}

	@When("^the creator zoom \"(.*?)\" the video$")
	public void actionZoom(String action) {
		GlobalVariables.previousZoomValue = createRoomAction.getZoomScaleValue();
		createRoomAction.zoomAction(action);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalVariables.zoomValue = createRoomAction.getZoomScaleValue();
	}

	@Then("the creator verifies if the video zoom \"(.*?)\"$")
	public void verifyVideoZoom(String action) {
		if (action.equals("in")) {
			if (GlobalVariables.zoomValue > GlobalVariables.previousZoomValue) {
				Assert.assertTrue(true, "Zoom in not works as expected");
			} else {
				Assert.assertTrue(false, "Zoom in not works as expected");
			}
		} else if (action.equals("out")) {
			if (GlobalVariables.zoomValue < GlobalVariables.previousZoomValue) {
				Assert.assertTrue(true, "Zoom out not works as expected");
			} else {
				Assert.assertTrue(false, "Zoom out not works as expected");
			}
		} else if (action.equals("reset")) {
			if (GlobalVariables.zoomValue == 1) {
				Assert.assertTrue(true, "Zoom reset not works as expected");
			} else {
				Assert.assertTrue(false, "Zoom reset not works as expected");
			}
		}
	}

	@Then("^the creator is displayed with \"(.*?)\" session feeds on the history$")
	public void verifySessionHistory(int number) {
		int noOfSessions = Feeds.sessionHistory.size();
		if (noOfSessions != number) {
			Assert.assertTrue(false,
					"Number of records in the session history not matches with the " + "number of sessions occured");
		}
	}

	@When("^the creator clicks on the \"(.*?)\" menu$")
	public void clickOnMenu(String menu) {
		createRoomAction.navigateToMenu(menu);
	}

	public final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			driver1.close();
		}
	};

	@When("^the creator creates a new session$")
	public void createNewSession() {
		PageFactory.initElements(driver1, CreateRoom.class);
		PageFactory.initElements(driver1, Feeds.class);
		driver1.get(ConfigManager.getApplicationProperty("url"));
		createRoomAction.navigateToMenu("Settings");
		driver1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		createRoomAction.selectSLServer(GlobalVariables.server.get(0));
		createRoomAction.navigateToMenu("Feeds");
		driver1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		createRoomAction.clickCreateRoom();
		driver1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		GlobalVariables.roomName1 = createRoomAction.enterRoomNameAndStart();
		WebDriverWait wait = new WebDriverWait(driver1, 20);
		wait.until(ExpectedConditions.textToBePresentInElement(CreateRoom.sessionHeader, GlobalVariables.roomName1));
	}
}
