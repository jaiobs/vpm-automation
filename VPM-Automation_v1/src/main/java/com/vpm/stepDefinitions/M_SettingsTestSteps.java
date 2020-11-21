/**
 * 
 */
package com.vpm.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vpm.modules.CreateRoomAction;
import com.vpm.modules.M_SettingsAction;
import com.vpm.modules.SettingsAction;
import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.CreateRoom_IOS;
import com.vpm.pageObjects.CreateRoom_Mobile;
import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.Settings;
import com.vpm.pageObjects.Settings_IOS;
import com.vpm.pageObjects.Settings_Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class M_SettingsTestSteps {

	AppiumDriver<MobileElement> driver = null;
	M_SettingsAction settingsAction = new M_SettingsAction();

	public M_SettingsTestSteps(BaseClass baseClass) {
		this.driver = (AppiumDriver<MobileElement>) baseClass.driver;
		PageFactory.initElements(driver, CreateRoom_Mobile.class);
		PageFactory.initElements(driver, Settings_Mobile.class);
		PageFactory.initElements(driver, CreateRoom_IOS.class);
		PageFactory.initElements(driver, Settings_IOS.class);
		
	}
	
	@When("^the user navigates to Settings page on mobile$")
	public void navigateTo() {
		settingsAction.navigateToSettings(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
    @When("^the user selects \"(.*?)\" in the Use SL Server option on mobile$")
    public void selectSLServerOption(String option) {
    	settingsAction.selectSLServer(GlobalVariables.server.get(0));
	} 
    
    @Then("^the user verifies the server ip address box on mobile$")
    public void verifyIPAddressField() {
    	settingsAction.validateField(driver);
	}
    
    @When("^the user selects \"(.*?)\" from settings on mobile$")
    public void selectVideo(String option) {
    	settingsAction.selectOption(option, driver);
    	settingsAction.backFromSettings();
    }
}