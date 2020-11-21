package com.vpm.stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vpm.pageObjects.BaseClass;
import com.vpm.pageObjects.GlobalVariables;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Step;
import io.cucumber.plugin.event.TestStepFinished;

public class Hooks {

	WebDriver driver = null; // Driver -- belongs to creator class
	WebDriver driver1 = null; // Driver -- belongs to another creator class, for creating two sessions at a
								// time
	WebDriver driver2 = null; // Driver -- belongs to joiner class

	private BaseClass baseClass;

	public Hooks(BaseClass baseClass) {
		this.baseClass = baseClass;
	}

	@Before // Driver settings as per the scenario considered
	public void launchBrowser(Scenario scenario) {
		GlobalVariables.scenarioName = scenario.getName();
		String temp="";
		if(scenario.getName().contains("sequential chat sessions") ||
				scenario.getName().contains("Joiner with multiple live sessions") 
				|| scenario.getName().contains("Stability check on the chatroom session")
				|| scenario.getName().contains("ZoomIn_ZoomOut")) {
			temp = GlobalVariables.Browser2;
			GlobalVariables.Browser2 = GlobalVariables.Browser1;					
		}
		baseClass.getDriver(scenario);
		if(scenario.getName().contains("sequential chat sessions") ||
				scenario.getName().contains("Joiner with multiple live sessions")) {
		GlobalVariables.Browser2 = temp;
		}
		if (scenario.getName().contains("Joiner with multiple live sessions")) {
			this.driver = baseClass.driver;
			this.driver1 = baseClass.driver1;
			this.driver2 = baseClass.driver2;
		} else if (scenario.getName().contains("Landing page") || scenario.getName().contains("Server Settings")
				|| scenario.getName().contains("Video Player")) {
			this.driver = baseClass.driver;
		} else {
			this.driver = baseClass.driver;
			this.driver2 = baseClass.driver2;
		}
	}

	@After
	/**
	 * Embed a screenshot in test report if test is marked as failed
	 */
	public synchronized void embedScreenshot(Scenario scenario) {
		if (!(driver instanceof AppiumDriver)) {
			driver.close();
		}
		if (scenario.getName().contains("Joiner with multiple live sessions")) {
			if (!(driver instanceof AppiumDriver)) {
				driver1.close();
				driver2.close();
			}
		} else {
			if (driver2 != null && !(driver2 instanceof AppiumDriver)) {
				driver2.close();
			}
		}
		if(driver instanceof AppiumDriver) {
			try {
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(driver2 instanceof AppiumDriver) {
			try {
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		GlobalVariables.roomName = "";
		GlobalVariables.roomName1 = "";
		GlobalVariables.firstMobileInstance = false;
		GlobalVariables.thirdMobileInstance = false;				
	}

	@AfterStep
	public void getScreenShot(Scenario scenario) {
		try {		
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png", "screenshot");
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		}
	}

}