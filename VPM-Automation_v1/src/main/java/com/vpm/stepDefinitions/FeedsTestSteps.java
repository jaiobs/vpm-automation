/**
 * 
 */
package com.vpm.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.vpm.pageObjects.Feeds;
import com.vpm.pageObjects.GlobalVariables;

import io.cucumber.java.en.Then;

/**
 * @author maithili.s
 *
 */
public class FeedsTestSteps {

	public WebDriver driver;
	public WebDriver driver2;
	
	public FeedsTestSteps() {
	//	driver = Hooks.driver;
	//	driver2 = Hooks.driver2;
	//	PageFactory.initElements(driver, Feeds.class);
	}
	
	/*@Then("^the user verifies if the video info displays in the feeds$")
	public void verifyFeedTileInfo() {
		Assert.assertTrue("Feed Tile does not contains video icon in it",
				Feeds.feedTile().findElement(By.xpath(Feeds.videoImgOnTile)).getAttribute("src").contains("video.svg"));
	}
	
	@Then("^I verify if the joined video info displays in the feeds$")
	public void verifyFeedTileInfo_Joiner() {
		while(GlobalVariables.isSessionTerminated) {
		Assert.assertTrue("Feed Tile does not contains joined session icon in it",
				Feeds.feedTile().findElement(By.xpath(Feeds.videoImgOnTile)).getAttribute("src").contains("join.svg"));
		GlobalVariables.isSessionTerminated = false;
		} 
	}*/
	
}
