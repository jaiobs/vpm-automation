/**
 * 
 */
package com.vpm.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.vpm.modules.CreateRoomAction;
import com.vpm.modules.SettingsAction;
import com.vpm.pageObjects.Settings;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author maithili.s
 *
 */
public class SettingsTestSteps {

	WebDriver driver = null;
	SettingsAction settingsAction = new SettingsAction();

	public SettingsTestSteps(Hooks hooks) {
		this.driver = hooks.driver;
		PageFactory.initElements(driver, Settings.class);
	}
	
	@When("^the user navigates to Settings page$")
	public void navigateTo() {
		settingsAction.navigateToSettings();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
    @When("^the user selects \"(.*?)\" in the Use SL Server option$")
    public void selectSLServerOption(String option) {
		settingsAction.selectSLServer(option);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
    
    @Then("^the user verifies if the server ip address box is \"(.*?)\"$")
    public void verifyIPAddressField(String state) {
    	settingsAction.validateField(state);
	}
}
