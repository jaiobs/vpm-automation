package com.vpm.pageObjects;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vpm.managers.DriverManager;
import com.vpm.utilities.WorkArea;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Scenario;
import net.masterthought.cucumber.ReportBuilder;

public class BaseClass {

	public WebDriver driver = null; // Driver - belongs to Creator class
	public WebDriver driver1 = null; // Driver - belongs to another creator class
	public WebDriver driver2 = null; // Driver - belongs to Joiner class

	public static String image;
	String permission = "";

	public void getDriver(Scenario scenario) {
		if (scenario.getName().contains("Joiner with multiple live sessions")) {
			GlobalVariables.process = 3;
			driver = DriverManager.getInstance().createLocalDriver();
			DriverManager.getInstance().setDriver(driver);
			driver = DriverManager.getInstance().getDriver();
			if (driver instanceof AppiumDriver) {
				permission = "driver";
				allowAppPermissions((AppiumDriver) driver);
				GlobalVariables.firstMobileInstance = true;
				GlobalVariables.process = 0;
			}
			driver1 = DriverManager.getInstance().createLocalDriver();
			DriverManager.getInstance().setDriver(driver1);
			driver1 = DriverManager.getInstance().getDriver();
			if (driver1 instanceof AppiumDriver) {
				permission = "driver1";
				allowAppPermissions((AppiumDriver) driver1);
				GlobalVariables.thirdMobileInstance = true;
				GlobalVariables.firstMobileInstance = false;
			}
			driver2 = DriverManager.getInstance().createLocalDriver();
			DriverManager.getInstance().setDriver(driver2);
			driver2 = DriverManager.getInstance().getDriver();
			if (driver2 instanceof AppiumDriver) {
				permission = "driver2";
				allowAppPermissions((AppiumDriver) driver2);
			}
		} else if (scenario.getName().contains("Landing page") || scenario.getName().contains("Server Settings")
				|| scenario.getName().contains("Video player")) {
			GlobalVariables.process = 1;
			driver = DriverManager.getInstance().createLocalDriver();
			DriverManager.getInstance().setDriver(driver);
			driver = DriverManager.getInstance().getDriver();
			if (driver instanceof AppiumDriver) {
				permission = "driver";
				allowAppPermissions((AppiumDriver) driver);
			}
		} else {
			GlobalVariables.process = 0;
			if (GlobalVariables.Browser1.equals("Android") || GlobalVariables.Browser1.equals("IOS")) {
				GlobalVariables.process++;
			}
			if (GlobalVariables.Browser2.equals("Android") || GlobalVariables.Browser2.equals("IOS")) {
				GlobalVariables.process++;
			}
			driver = DriverManager.getInstance().createLocalDriver();
			DriverManager.getInstance().setDriver(driver);
			driver = DriverManager.getInstance().getDriver();
			if (driver instanceof AppiumDriver) {
				permission = "driver";
				allowAppPermissions((AppiumDriver) driver);
				GlobalVariables.firstMobileInstance = true;
				GlobalVariables.process = 0;
			}
			driver2 = DriverManager.getInstance().createLocalDriver();
			DriverManager.getInstance().setDriver(driver2);
			driver2 = DriverManager.getInstance().getDriver();
			if (driver2 instanceof AppiumDriver) {
				permission = "driver2";
				allowAppPermissions((AppiumDriver) driver2);
			}
		}
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		String webElement_locator, bySelector = "";
		bySelector = locator.substring(0, locator.indexOf("="));
		webElement_locator = locator.substring(locator.indexOf("=") + 1, locator.length());
		WebElement e = null;
		if (bySelector.equalsIgnoreCase("css")) {
			e = driver.findElement(By.cssSelector(webElement_locator));
		} else if (bySelector.equalsIgnoreCase("link")) {
			e = driver.findElement(By.linkText(webElement_locator));
		} else if (bySelector.equalsIgnoreCase("xpath")) {
			e = driver.findElement(By.xpath(webElement_locator));
		}
		return e;
	}

	public List<WebElement> getWebElements(String locator) {
		String webElement_locator, bySelector = "";
		bySelector = locator.substring(0, locator.indexOf("="));
		webElement_locator = locator.substring(locator.indexOf("=") + 1, locator.length());
		List<WebElement> e = null;
		if (bySelector.equalsIgnoreCase("css")) {
			e = driver.findElements(By.cssSelector(webElement_locator));
		} else if (bySelector.equalsIgnoreCase("link")) {
			e = driver.findElements(By.linkText(webElement_locator));
		}
		return e;
	}

	public Boolean isElementPresent(String locator, AppiumDriver driver) {
		try {
			 WebElement element = driver.findElement(MobileBy.xpath(locator));
			 if(element.isDisplayed())
				return true;
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void allowAppPermissions(AppiumDriver<MobileElement> driver) {
		if (driver instanceof IOSDriver) {
			if (isElementPresent("//XCUIElementTypeButton[@id='OK']", driver)) {
				driver.findElement(MobileBy.xpath("//XCUIElementTypeButton[@id='OK']")).click();				
			}
		} else {
			if (driver instanceof AndroidDriver) {
				if (isElementPresent("//*[@class='android.widget.Button']", driver)) {
					driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][@text='No']")).click();					
				}
			}
		}
	}

	Path getResource(String fileName) throws URISyntaxException {
		String paths = WorkArea.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		// System.out.println(path);
		File reader = new File(paths + "/com/vpm/images/" + fileName);
		File refImgFile = Paths.get(reader.toURI()).toFile();
		Path path = refImgFile.toPath();
		return path;
	}

	private String getResourceB64(String fileName) throws URISyntaxException, IOException {
		return Base64.getEncoder().encodeToString(Files.readAllBytes(getResource(fileName)));
	}

	public String getReferenceImageB64(String fileName) throws URISyntaxException, IOException {
		return getResourceB64(fileName);
	}

}
