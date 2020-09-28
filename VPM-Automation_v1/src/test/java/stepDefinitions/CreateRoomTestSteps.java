/**
 * 
 */
package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.DataHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import managers.ConfigManager;
import modules.CreateRoomAction;
import pageObjects.CreateRoom;
import pageObjects.GlobalVariables;
import pageObjects.LoginPage;

/**
 * @author maithili.s
 *
 */
public class CreateRoomTestSteps {

	public WebDriver driver;
	public List<HashMap<String, String>> datamap;
	CreateRoomAction createRoomAction = new CreateRoomAction();

	public CreateRoomTestSteps() {
		driver = Hooks.driver;
		PageFactory.initElements(driver, CreateRoom.class);
	}

	@Given("^I am on vpm landing page$")
	public void openLandingPage() {
		driver.get(ConfigManager.getApplicationProperty("url"));
	}

	@When("^I enter into the create room form and check if all the default controls are enabled$")
	public void createRoomForm() {
		createRoomAction.clickCreateRoom();
		for (Entry<String, Boolean> entry : createRoomAction.mediaControls.entrySet()) {
			if (entry.getKey().equals("Local Audio") || entry.getKey().equals("Local Video")
					|| entry.getKey().equals("Remote Audio")) {
				Assert.assertTrue(entry.getKey() + " is not enabled, by default", entry.getValue());
			}
			if (entry.getKey().equals("Remote Video")) {
				Assert.assertFalse(entry.getKey() + " is enabled, by default", entry.getValue());
			}
		}
	}

	@Then("^I enter the new room name and start$")
	public void enterRoom() {
		createRoomAction.enterRoomNameAndStart();
		/*WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(CreateRoom.alertMsg, "Success")); */
	}

	@Then("^I should have entered into the new room started$")
	public void verifyRoomEntered() {
		Assert.assertTrue("Room name not found in the session",
				CreateRoom.sessionHeader.getText().contains(GlobalVariables.roomName));
		Assert.assertTrue("Live Label not found in the session", CreateRoom.liveLabel.getText().equalsIgnoreCase("Live"));
		
		Assert.assertTrue("Video is in mute", CreateRoom.videoIcon.getAttribute("src").contains("video-cam.svg"));
		Assert.assertTrue("Audio is in mute", CreateRoom.audioIcon.getAttribute("src").contains("mike.svg"));						
	}

	@Then("^the user verifies if the video cam works$")
	public void verifyMediaControls() {
		/*String videoId = "localVideoVpmSession"+GlobalVariables.roomName;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById(videoId)");*/
		LogEntries logType = driver.manage().logs().get(LogType.DRIVER);
		for(LogEntry logentry : logType) {
			System.out.println("Console Messages -----------" +logentry);
		}
		
	}
			
}
