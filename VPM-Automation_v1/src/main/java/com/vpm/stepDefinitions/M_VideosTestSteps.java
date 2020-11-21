/**
 * 
 */
package com.vpm.stepDefinitions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.vpm.modules.M_SettingsAction;
import com.vpm.modules.M_VideosAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CommonMethods;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.Videos_Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class M_VideosTestSteps {

	AppiumDriver<MobileElement> driver = null;
	M_VideosAction videosAction = new M_VideosAction();
	SoftAssert Assert = new SoftAssert();
	CommonMethods command = new CommonMethods();
	Date currentDuration = null;
	Date forwardedDuration = null;

	public M_VideosTestSteps(BaseClass baseClass) {
		this.driver = (AppiumDriver<MobileElement>) baseClass.driver;
		PageFactory.initElements(driver, Videos_Mobile.class);
	}

	@Then("^the videos should display only the \"(.*?)\" videos on mobile$")
	public void videoDisplayList(String video) {
		List<WebElement> videolists = Videos_Mobile.videoLabel;
		Boolean videoLabelExpected = false;
		for (WebElement videolist : videolists) {
			if (video.equals("local")) {
				Assert.assertEquals("LOCAL", videolist.getText(),
						"Filtered video--" + video + "is not working as expected");
			} else if (video.equals("HTTP")) {
				if (videolist.getText().equals("LOCAL") || videolist.getText().equals("HLS")
						|| videolist.getText().equals("HTTP") || videolist.getText().equals("HTTPS")) {
					videoLabelExpected = true;
				}
				Assert.assertTrue(videoLabelExpected, "Filtered video-- HTTP is not working as expected");
			}
		}
	}

	@When("^the user clicks on the video named \"(.*?)\" on mobile$")
	public void clickOnTheVideo(String videoFile) {
		driver.findElement(Videos_Mobile.video(videoFile)).click();
	}

	@Then("^the user should be displayed with the video \"(.*?)\" playing on mobile$")
	public void verifyVideoDisplayed(String videoFile) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(Videos_Mobile.fileName(videoFile)));
		Assert.assertTrue(Videos_Mobile.fileName.getText().equals(videoFile),
				"Expected video file name not displayed");
		Pattern pattern = Pattern.compile("STATE*");
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.textMatches(Videos_Mobile.videoPlayBackStatus(), pattern));
		String playbackStatus = Videos_Mobile.videoPlayBackStatus.getText().trim();
		Assert.assertTrue(playbackStatus.contains("STATE_"), "No Playback status displays in the video");
	}

	@Then("^the user verifies if the video plays on clicking the play button on mobile$")
	public void verifyVideoPlays() {
		videosAction.resetTheVideo();
		Assert.assertTrue(Videos_Mobile.videoPlayBtn.isDisplayed(), "Play button not displays");
		Assert.assertTrue(Videos_Mobile.videoPlayBackStatus.getText().contains("STATE_READY"),
				"Playback status READY not displays");		
		Videos_Mobile.videoPlayBtn.click();
		command.delay(2000);
		String videoStatus = "";
		Assert.assertFalse(videosAction.isElementPresent(Videos_Mobile.videoPlayBtn), "Video not plays");
		if(videosAction.isElementPresent(Videos_Mobile.videoPlayBtn)) {
			Videos_Mobile.videoPlayBtn.click();		
			command.delay(2000);
			Videos_Mobile.video.click();
		}		
		videosAction.getVideoDuration();
		Assert.assertTrue(!GlobalVariables.cDuration.equals("0:00"),
				"Playback status PLAYING not displays on video playing");
	}
	
	@When("^the user clicks on Forward 10 seconds on the video on mobile$")
	public void clickOnForwardVideo() {
	 currentDuration = videosAction.getCurrentDuration();
	 currentDuration.toInstant().plusSeconds(10);
	 Videos_Mobile.videoBackwardBtn.click();
	 Videos_Mobile.videoForwardBtn.click();	 
	 forwardedDuration = videosAction.getCurrentDuration();			
	}
	
   	@Then("^the user verifies if the video is forwarded 10 seconds on mobile$")
   	public void verifyVideoForwarded() {
   		Assert.assertEquals(forwardedDuration, currentDuration, "Cannot forward the video for 10 seconds");	
   	}
	
   	@When("^the user clicks on Backward 10 seconds on the video on mobile$")
   	public void clickOnBackwardVideo() {
   	 currentDuration = videosAction.getCurrentDuration();
	 Videos_Mobile.videoBackwardBtn.click();
	 currentDuration.toInstant().minusSeconds(10);
	 forwardedDuration = videosAction.getCurrentDuration();
   	}
	
	@Then("^the user verifies if the video is pushed backward 10 seconds on mobile$")
	public void verifyVideoBackward() {
		Assert.assertEquals(forwardedDuration, currentDuration, "Cannot push the video backward for 10 seconds");	
	}
	
	@Then("the user verifies if the video info for \"(.*?)\" displays on mobile")
	public void videoInfo(String fileName) {
		Videos_Mobile.videoInfo.click();
		command.delay(3000);
		Assert.assertTrue(Videos_Mobile.videoInfo_FileName.getText().contains(fileName), 
				"File name in Info not matches with the video file name");
		Videos_Mobile.videoInfo_CloseBtn.click();
	}
	
	@Then("^the user verifies if the video play back returns to ready state at the end of the video \"(.*?)\" on mobile$")
	public void verifyReadyStateAtEnd(String fileName) {
		Videos_Mobile.videoPlayBtn.click();
		if(fileName.equals("foreman_assest.mp4")) {
			command.delay(11000);
		}
		Assert.assertTrue(Videos_Mobile.videoPlayBackStatus.getText().contains("STATE_READY"), "playback not reset to ready state");
	}
	
	@When("^the user plays the video File \"(.*?)\"$")
	public void playVideoFile(String fileName) {
		videosAction.swipeTo(fileName, driver);
		driver.findElement(Videos_Mobile.video(fileName)).click();
	}
	
	@Then("^the user should be displayed with the invalid url message$")
	public void verifyInvalidMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(Videos_Mobile.alertMessage));
		Assert.assertTrue(Videos_Mobile.alertTitle.getText().contains("Invalid Url"));
		if(videosAction.isElementPresent(Videos_Mobile.alertOkBtn)) {
			Videos_Mobile.alertOkBtn.click();
		}
	}
	
	 @Then("^the user should be displayed with only the audio of the file$")
	 public void verifyAudio() {
		 WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBePresentInElement(Videos_Mobile.videoPlayBackStatus, "STATE_PLAYING"));
		Assert.assertFalse(videosAction.isElementPresent(Videos_Mobile.video));
		Videos_Mobile.backFromVideoBtn.click();
		command.delay(3000);
	 }
	 
	 @Then("^the user should be displayed with only the video of the file$")
	 public void verifyVideo() {
		 WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBePresentInElement(Videos_Mobile.videoPlayBackStatus, "STATE_PLAYING"));
		Assert.assertTrue(videosAction.isElementPresent(Videos_Mobile.video));
		Videos_Mobile.backFromVideoBtn.click();
		command.delay(3000);
	 }
}
