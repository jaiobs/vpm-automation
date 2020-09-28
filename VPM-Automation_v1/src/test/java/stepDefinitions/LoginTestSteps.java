package stepDefinitions;

import helpers.DataHelper;

import java.util.HashMap;
import java.util.List;
import modules.LoginAction;

import pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.ConfigManager;

public class LoginTestSteps {
	public WebDriver driver;
	public List<HashMap<String, String>> datamap;	

	public LoginTestSteps() {
		driver = Hooks.driver;
		datamap = DataHelper.getWorkSheet("Login_TestData");		
		PageFactory.initElements(driver, LoginPage.class);
	}

	@When("^I open AX D365$")
	public void i_open_d365() throws Throwable {		
		driver.get(ConfigManager.getApplicationProperty("url"));
	}

	@When("^I sign in$")
	public void i_sign_in() throws Throwable {		
		LoginAction.Execute(driver, datamap);
	}

	
}