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
import com.vpm.modules.M_CreateRoomAction;
import com.vpm.modules.M_JoinRoomAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CommonMethods;
import com.vpm.pageObjects.CreateRoom;
import com.vpm.pageObjects.CreateRoom_IOS;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.Feeds_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.JoinRoom;
import com.vpm.pageObjects.JoinRoom_IOS;
import com.vpm.pageObjects.JoinRoom_Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
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
public class M_JoinRoomTestSteps {
	
	AppiumDriver<MobileElement> driver = null;
	WebDriver driver1 = null;
	M_JoinRoomAction joinRoomAction = new M_JoinRoomAction();
	CommonMethods command = new CommonMethods();
	SoftAssert softAssert = new SoftAssert();

	public M_JoinRoomTestSteps(BaseClass baseClass) {
		this.driver = (AppiumDriver<MobileElement>) baseClass.driver2;
		PageFactory.initElements(driver, JoinRoom_Mobile.class);
		PageFactory.initElements(driver, Feeds_Mobile.class);
		PageFactory.initElements(driver, JoinRoom_IOS.class);
	}

	@Given("^the joiner on a vpm landing page on mobile$")
	public void openJoinerLandingPage() {
		// driver.manage().window().maximize();
		System.out.println("------------Joiner Entered-----------");
	}

	@When("^the joiner enter into the join room form on mobile$")
	public void joinRoomForm() {
		joinRoomAction.clickJoinRoom(driver);
		command.delay(3000);
		try {
			command.hideKeyboard(driver);
		} catch(Exception e) {
			command.delay(3000);
			command.hideKeyboard(driver);
		}
	}

	@Then("^the joiner check if all the joiner default controls are enabled on mobile$")
	public void verifyDefaultControls() {
		try {
		joinRoomAction.getDefaultControls(driver);
		} catch(Exception e) {
			driver.hideKeyboard();
			joinRoomAction.getDefaultControls(driver);
		}
		for (Entry<String, Boolean> entry : M_JoinRoomAction.joiner_defaultControls.entrySet()) {
			if (entry.getKey().equals("Local Audio") || entry.getKey().equals("Remote Video")
					|| entry.getKey().equals("Remote Audio")) {
				Assert.assertTrue(entry.getKey() + " is not selected, by default", entry.getValue());
			}
			if (entry.getKey().equals("Local Video")) {
				Assert.assertFalse(entry.getKey() + " is selected, by default", entry.getValue());
			}
		}
		M_JoinRoomAction.joiner_mediaControls = M_JoinRoomAction.joiner_defaultControls;
	}

	@Then("^the joiner select the controls \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" on mobile$")
	public void selectControls(String lAudio, String lVideo, String rAudio, String rVideo) {
		joinRoomAction.selectMediaControls(lAudio, lVideo, rAudio, rVideo);
	}

	@Then("^the joiner enter the created room name and start on mobile$")
	public void enterRoom() {
		joinRoomAction.enterRoomNameAndStart(GlobalVariables.roomName, driver);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.textToBePresentInElement(CreateRoom.alertMsg,
		 * "Success"));
		 */
	}

	@Then("^the joiner should have entered into the created room on mobile$")
	public void verifyRoomEntered() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		if(driver instanceof IOSDriver) {
			wait.until(ExpectedConditions.visibilityOf(JoinRoom_IOS.sessionHeader));
			softAssert.assertTrue(JoinRoom_IOS.sessionHeader.getText().contains(GlobalVariables.roomName),
					"Room name not found in the session");
		} else {
			wait.until(ExpectedConditions.visibilityOf(JoinRoom_Mobile.sessionHeader));
			softAssert.assertTrue(JoinRoom_Mobile.sessionHeader.getText().contains(GlobalVariables.roomName),
					"Room name not found in the session");
		}				
		GlobalVariables.isJoined = true;
		System.out.println(driver.getPageSource());
	}

	@Then("^the joiner verify if the media controls works on mobile$")
	public void verifyMediaControls() {		
		for (Entry<String, Boolean> entry : M_JoinRoomAction.joiner_mediaControls.entrySet()) {
			if (entry.getKey().equals("Local Audio")) {
				if (entry.getValue()) {
					softAssert.assertTrue(
							driver.findElementByImage(joinRoomAction.getImage("MikeMuteIcon.png")).isDisplayed(),
							"Audio is in mute");
				} else {
					softAssert.assertTrue(
							driver.findElementByImage(joinRoomAction.getImage("MikeIcon.png")).isDisplayed(),
							"Audio is in unmute");
				}
			}
			if (entry.getKey().equals("Local Video")) {
				if (entry.getValue()) {
					softAssert.assertTrue(
							driver.findElementByImage(joinRoomAction.getImage("VideoMuteIcon.png")).isDisplayed(),
							"Video is in mute");
				} else {
					softAssert.assertTrue(
							driver.findElementByImage(joinRoomAction.getImage("VideoIcon.png")).isDisplayed(),
							"Video is in unmute");
				}
			}
			if (entry.getKey().equals("Remote Video")) {
				/*if (entry.getValue()) {
					if (driver instanceof IOSDriver) {
						softAssert.assertTrue(CreateRoom_IOS.remoteVideoMuteIcon.isEmpty(), "Video is in mute");
					} else if (driver instanceof AndroidDriver) {
						softAssert.assertTrue(CreateRoom_Mobile.remoteVideoMuteIcon.isEmpty(), "Video is in mute");
					}
				} else {
					if (driver instanceof IOSDriver) {
						softAssert.assertFalse(CreateRoom_IOS.remoteVideoMuteIcon.isEmpty(), "Video is in unmute");
					}
					else if (driver instanceof AndroidDriver) {
					softAssert.assertFalse(CreateRoom_Mobile.remoteVideoMuteIcon.isEmpty(), "Video is in unmute");
					}
				}*/
			}
		}

	}

	@Then("^the joiner verify if the joined video info displays in the feeds on mobile$")
	public void verifyFeedTileInfo_Joiner() {
		/*if (GlobalVariables.isSessionTerminated) {
			String locator = "div.roomVpmSession" + GlobalVariables.roomName + " div.video-info";
			String videoImgOnTile = "//preceding-sibling::div/div[@class='video-img']/img";
			Assert.assertTrue("Feed Tile does not contains joined session in it",
					driver.findElement(By.cssSelector(locator)).findElement(By.xpath(videoImgOnTile))
							.getAttribute("src").contains("join.svg"));
		} else {
			try {
				Thread.sleep(10000);
				if (GlobalVariables.isSessionTerminated) {
					String locator = "div.roomVpmSession" + GlobalVariables.roomName + " div.video-info";
					String videoImgOnTile = "//preceding-sibling::div/div[@class='video-img']/img";
					Assert.assertTrue("Feed Tile does not contains joined session in it",
							driver.findElement(By.cssSelector(locator)).findElement(By.xpath(videoImgOnTile))
									.getAttribute("src").contains("join.svg"));
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GlobalVariables.isSessionTerminated = false;*/
	}

	@Then("^the joiner terminates the joined session on mobile$")
	public void terminateSession() {
		joinRoomAction.terminateSession();
	}

	@When("^the joiner clicks back from the session on mobile$")
	public void backFromSession() {
		joinRoomAction.clickBackFromSession();
	}

	@Then("^the joiner should be displayed with the Live label in the feeds on mobile$")
	public void verifyLiveLabelOnFeeds() {
		Assert.assertTrue("Feed Tile does not contain live label on the session",
				JoinRoom_Mobile.liveLabel_Feeds.getText().equalsIgnoreCase("Live"));
	}

	@Then("^the joiner stays for \"([^\"]*)\" minutes in the session on mobile$")
	public void stabilityCheck(int minutes) {
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver).withTimeout(minutes, TimeUnit.MINUTES)
					.pollingEvery(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOf(JoinRoom_Mobile.sessionHeader));
		} catch (TimeoutException te) {
			Assert.assertTrue(true);
		}
		watch.stop();
		System.out.println("--------------------" + TimeUnit.MILLISECONDS.toMinutes(watch.getTime())
				+ "----------------------------");
		int recMinutes = Integer.parseInt(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(watch.getTime())));
		if (recMinutes < minutes) {
			Assert.assertTrue("Stability check failed", false);
		}
	}

	@Then("^the joiner is displayed with \"(.*?)\" session feeds on the history on mobile$")
	public void verifySessionHistory(int number) {
		int noOfSessions = Feeds_Mobile.sessionHistory.size();
		command.swipeTo(driver, 0.2);
		noOfSessions = noOfSessions + Feeds_Mobile.sessionHistory.size();
		if (noOfSessions+1 != number) {
			Assert.assertTrue(
					"Number of records in the session history not matches with the " + "number of sessions occured",
					false);
		}
	}

	@Then("^the joiner should be displayed with the live session at the top of the list on mobile$")
	public void verifyLiveSessionInTheFeeds() {
		Assert.assertTrue("Live Session is not at the top of the list",
				Feeds_Mobile.sessionHistory.get(0).findElement(Feeds_Mobile.getLiveLabel()).getText().equalsIgnoreCase("Live"));
	}

	@When("^the joiner clicks to go back to the live session on mobile$")
	public void goBackToLiveSession() {
		joinRoomAction.backToLiveSession();
	}

	@Then("^the joiner enters into the second new session created on mobile$")
	public void enterIntoSecondNewSession() {
		joinRoomAction.clickJoinRoom(driver);
		command.delay(3000);
		command.hideKeyboard(driver);
		joinRoomAction.enterRoomNameAndStart(GlobalVariables.roomName1, driver);
	}

	@Then("^the joiner should be displayed with Live label on 2 sessions on mobile$")
	public void liveSessionLabel() {
		Assert.assertEquals("Two Live sessions not displayed", Integer.parseInt("2"), JoinRoom_Mobile.liveSessions.size());
	}

	@Then("^the joiner should be able to go into any of the live session on mobile$")
	public void joinTheLiveSesion() {
		for (WebElement liveSession : JoinRoom_Mobile.liveSessions) {
			liveSession.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Assert.assertTrue("Joiner cannot enter into the live session",
					(JoinRoom_Mobile.sessionHeader.getText().contains(GlobalVariables.roomName)
							|| JoinRoom_Mobile.sessionHeader.getText().contains(GlobalVariables.roomName1)));
			JoinRoom_Mobile.BackFromSession.click();
			command.delay(5000);
		}
	}
	
	@When("^the joiner navigates to \"(.*?)\" page on mobile$")
	public void navigateTo(String menu) {		
		joinRoomAction.navigateToMenu(menu, driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@When("^the joiner selects the server in the Server option on mobile$")
	public void selectSLServerOption() {
		joinRoomAction.selectSLServer(GlobalVariables.server.get(0), driver);
	}
	
	 @When("^the joiner selects the \"(.*?)\" server in the Server option on mobile$")
	 public void selectServer(String server) {
			joinRoomAction.selectSLServer(server, driver);
	 }
	
	@Then("^the joiner verifies if the server ip address box is \"(.*?)\" on mobile$")
	public void verifyIPAddressField(String state) {
		joinRoomAction.validateField(state);
	}
	
	@Then("^the joiner should not have entered into the created room on mobile$")
	public void checkSessionEntered() {
		Assert.assertTrue("Entered into the session", !JoinRoom.sessionHeader.isDisplayed());
		  /*File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  String path = System.getProperty("user.dir")+"\\toastmessage.png";		    
		  try {
			FileHandler.copy(scr, new File(path));		
		    ITesseract image = new Tesseract();
				String text = image.doOCR(new File(path));
				System.out.println("Toast Message -------------------- :"+text);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  driver.manage().*/
	}
}
