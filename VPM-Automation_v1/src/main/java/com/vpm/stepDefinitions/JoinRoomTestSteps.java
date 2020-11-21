/**
 * 
 */
package com.vpm.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.vpm.managers.ConfigManager;
import com.vpm.modules.JoinRoomAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author maithili.s
 *
 */
public class JoinRoomTestSteps {

	WebDriver driver = null;
	JoinRoomAction joinRoomAction = new JoinRoomAction();
	SoftAssert Assert = new SoftAssert();

	public JoinRoomTestSteps(BaseClass baseClass) {
		this.driver = baseClass.driver2;
		PageFactory.initElements(driver, JoinRoom.class);
		PageFactory.initElements(driver, Feeds.class);
	}

	@Given("^the joiner on a vpm landing page$")
	public void openJoinerLandingPage() {
		// driver.manage().window().maximize();
		driver.get(ConfigManager.getApplicationProperty("url"));
	}

	@When("^the joiner enter into the join room form$")
	public void joinRoomForm() {
		joinRoomAction.clickJoinRoom();
	}

	@Then("^the joiner check if all the joiner default controls are enabled$")
	public void verifyDefaultControls() {
		joinRoomAction.getDefaultControls();
		for (Entry<String, Boolean> entry : JoinRoomAction.joiner_defaultControls.entrySet()) {
			if (entry.getKey().equals("Local Audio") || entry.getKey().equals("Remote Video")
					|| entry.getKey().equals("Remote Audio")) {
				Assert.assertTrue(entry.getValue(), entry.getKey() + " is not selected, by default");
			}
			if (entry.getKey().equals("Local Video")) {
				Assert.assertFalse(entry.getValue(), entry.getKey() + " is selected, by default");
			}
		}
		JoinRoomAction.joiner_mediaControls = JoinRoomAction.joiner_defaultControls;
	}

	@Then("^the joiner select the controls \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void selectControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		joinRoomAction.selectMediaControls(lAudio, lVideo, rAudio, rVideo);
	}

	@Then("^the joiner enter the created room name and start$")
	public void enterRoom() {
		joinRoomAction.enterRoomNameAndStart(GlobalVariables.roomName);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.textToBePresentInElement(CreateRoom.alertMsg,
		 * "Success"));
		 */
	}

	@Then("^the joiner should have entered into the created room$")
	public void verifyRoomEntered() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			Assert.assertTrue(JoinRoom.sessionHeader.getText().contains(GlobalVariables.roomName),
					"Room name not found in the session");
		} catch (AssertionError ae) {

		}
		Assert.assertTrue(JoinRoom.liveLabel.getText().equalsIgnoreCase("Live"), "Live Label not found in the session");
		GlobalVariables.isJoined = true;
	}

	@Then("^the joiner verify if the media controls works$")
	public void verifyMediaControls() {
		for (Entry<String, Boolean> entry : JoinRoomAction.joiner_mediaControls.entrySet()) {
			if (entry.getKey().equals("Local Audio")) {
				if (entry.getValue()) {
					Assert.assertTrue(JoinRoom.audioIcon.getAttribute("src").contains("mike.svg"), "Audio is in mute");
				} else {
					Assert.assertTrue(JoinRoom.audioIcon.getAttribute("src").contains("mike-mute.svg"),
							"Audio is in unmute");
				}
			}
			if (entry.getKey().equals("Local Video")) {
				if (entry.getValue()) {
					Assert.assertTrue(JoinRoom.videoIcon.getAttribute("src").contains("video-cam.svg"),
							"Video is in mute");
				} else {
					Assert.assertTrue(JoinRoom.videoIcon.getAttribute("src").contains("video-mute.svg"),
							"Video is in unmute");
				}
			}
			if (entry.getKey().equals("Remote Video")) {
				if (entry.getValue()) {
					// Assert.assertTrue("Video is in mute",
					// JoinRoom.remoteVideoMuteIcon.isEmpty());
				} else {
					// Assert.assertTrue("Video is in unmute",
					// !JoinRoom.remoteVideoMuteIcon.isEmpty());
				}
			}
		}
		/*
		 * LogEntries logType = driver2.manage().logs().get(LogType.DRIVER);
		 * for(LogEntry logentry : logType) {
		 * System.out.println("Console Messages -----------" +logentry); }
		 */
		// joinRoomAction.recordAudio();
		// joinRoomAction.validateAudio();
	}

	@Then("^the joiner verify if the joined video info displays in the feeds$")
	public void verifyFeedTileInfo_Joiner() {
		if (GlobalVariables.isSessionTerminated) {
			String locator = "div.roomVpmSession" + GlobalVariables.roomName + " div.video-info";
			String videoImgOnTile = "//preceding-sibling::div/div[@class='video-img']/img";
			Assert.assertTrue(
					driver.findElement(By.cssSelector(locator)).findElement(By.xpath(videoImgOnTile))
							.getAttribute("src").contains("join.svg"),
					"Feed Tile does not contains joined session in it");
		} else {
			try {
				Thread.sleep(10000);
				if (GlobalVariables.isSessionTerminated) {
					String locator = "div.roomVpmSession" + GlobalVariables.roomName + " div.video-info";
					String videoImgOnTile = "//preceding-sibling::div/div[@class='video-img']/img";
					Assert.assertTrue(
							driver.findElement(By.cssSelector(locator)).findElement(By.xpath(videoImgOnTile))
									.getAttribute("src").contains("join.svg"),
							"Feed Tile does not contains joined session in it");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GlobalVariables.isSessionTerminated = false;
	}

	@Then("^the joiner terminates the joined session$")
	public void terminateSession() {
		joinRoomAction.terminateSession();
	}

	@When("^the joiner clicks back from the session$")
	public void backFromSession() {
		joinRoomAction.clickBackFromSession();
	}

	@Then("^the joiner should be displayed with the Live label in the feeds$")
	public void verifyLiveLabelOnFeeds() {
		Assert.assertTrue(JoinRoom.liveLabel_Feeds.getText().equalsIgnoreCase("Live"),
				"Feed Tile does not contain live label on the session");
	}

	@Then("^the joiner stays for \"([^\"]*)\" minutes in the session$")
	public void stabilityCheck(int minutes) {
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(minutes, TimeUnit.MINUTES)
					.pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOf(CreateRoom.sessionHeader));
		} catch (TimeoutException te) {
			Assert.assertTrue(true);
		}
		watch.stop();
		System.out.println("--------------------" + TimeUnit.MILLISECONDS.toMinutes(watch.getTime())
				+ "----------------------------");
		int recMinutes = Integer.parseInt(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(watch.getTime())));
		if (recMinutes < minutes) {
			Assert.assertTrue(false, "Stability check failed");
		}
	}

	@Then("^the joiner is displayed with \"(.*?)\" session feeds on the history$")
	public void verifySessionHistory(int number) {
		int noOfSessions = Feeds.sessionHistory.size();
		if (noOfSessions != number) {
			Assert.assertTrue(false,
					"Number of records in the session history not matches with the " + "number of sessions occured");
		}
	}

	@Then("^the joiner should be displayed with the live session at the top of the list$")
	public void verifyLiveSessionInTheFeeds() {
		Assert.assertTrue(
				Feeds.sessionHistory.get(0).findElement(Feeds.getLiveLabel()).getText().equalsIgnoreCase("Live"),
				"Live Session is not at the top of the list");
	}

	@When("^the joiner clicks to go back to the live session$")
	public void goBackToLiveSession() {
		joinRoomAction.backToLiveSession();
	}

	@Then("^the joiner enters into the second new session created$")
	public void enterIntoSecondNewSession() {
		joinRoomAction.clickJoinRoom();
		joinRoomAction.enterRoomNameAndStart(GlobalVariables.roomName1);
	}

	@Then("^the joiner should be displayed with Live label on 2 sessions$")
	public void liveSessionLabel() {
		Assert.assertEquals(Integer.parseInt("2"), JoinRoom.liveSessions.size(), "Two Live sessions not displayed");
	}

	@Then("^the joiner should be able to go into any of the live session$")
	public void joinTheLiveSesion() {
		for (WebElement liveSession : JoinRoom.liveSessions) {
			liveSession.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Assert.assertTrue(
					(JoinRoom.sessionHeader.getText().contains(GlobalVariables.roomName)
							|| JoinRoom.sessionHeader.getText().contains(GlobalVariables.roomName1)),
					"Joiner cannot enter into the live session");
			JoinRoom.BackFromSession.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}

	@When("^the joiner navigates to \"(.*?)\" page$")
	public void navigateTo(String menu) {
		if (menu.equalsIgnoreCase("Feeds")) {
			JoinRoom.feedsMenu.click();

		} else if (menu.equalsIgnoreCase("Settings")) {
			JoinRoom.settingsMenu.click();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("^the joiner selects the server in the Use SL Server option$")
	public void selectSLServerOption() {
		joinRoomAction.selectSLServer(GlobalVariables.server.get(0));
	}

	@When("^the joiner selects the \"(.*?)\" server in the Use SL Server option$")
	public void selectServer(String server) {
		joinRoomAction.selectSLServer(server);
	}

	@Then("^the joiner verifies if the server ip address box is \"(.*?)\"$")
	public void verifyIPAddressField(String state) {
		joinRoomAction.validateField(state);
	}

	@Then("^the joiner should not have entered into the created room$")
	public void checkSessionEntered() {
		Assert.assertTrue(!JoinRoom.sessionHeader.isDisplayed(), "Entered into the session");		
	}
}
