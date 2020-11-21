/**
 * 
 */
package com.vpm.managers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.vpm.enums.DriverType;
import com.vpm.enums.EnvironmentType;
import com.vpm.managers.ConfigManager;
import com.vpm.pageObjects.GlobalVariables;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * @author maithili.s
 *
 */
public class DriverManager {

	static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private DriverType driverType;
	private EnvironmentType environmentType;

	Process process1, process2, process3;
	int count=0;
	String path="";

	public DriverManager() {
		driverType = ConfigManager.getInstance().getBrowser();
		//environmentType = ConfigManager.getInstance().getEnvironment();
	}

	public static DriverManager getInstance() {
		return new DriverManager();
	}

	public synchronized void setDriver(WebDriver driverParam) {		
			driver.set(driverParam);				
	}

	public synchronized WebDriver getDriver() {
		return driver.get();
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	public synchronized WebDriver createLocalDriver() {
		WebDriver driver = null;
		switch (driverType) {
		case FIREFOX:
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "\\lib\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			return new FirefoxDriver();
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			if (GlobalVariables.server.get(0).equalsIgnoreCase("WebRTC")) {
				if (System.getProperty("os.name").contains("Mac")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/lib/chromedriver_mac64/chromedriver");
				} else {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/lib/chromedriver_win32/chromedriver.exe");
				}
				options.addArguments("--disable-web-security");
				options.addArguments("use-fake-ui-for-media-stream");
				options.addArguments("use-fake-device-for-media-stream");
				options.addArguments(
						"use-file-for-fake-audio-capture=" + System.getProperty("user.dir") + "/lib/AudioInput.wav");				
			} else {
				if (System.getProperty("os.name").contains("Mac")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/lib/chromedriver_mac64/chromedriver");
				} else {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/lib/chromedriver_win32/chromedriver.exe");
				}
				options.addArguments("use-fake-ui-for-media-stream");
				options.addArguments("use-fake-device-for-media-stream");
				// options.setExperimentalOption("w3c", false);
				options.addArguments(
						"use-file-for-fake-audio-capture=" + System.getProperty("user.dir") + "/lib/AudioInput.wav");
			}
			return new ChromeDriver(options);
		case EDGE:
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\lib\\edgedriver_win64\\msedgedriver.exe");
			return new EdgeDriver();
		case INTERNETEXPLORER:
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\lib\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		case ANDROID:
			AppiumDriverLocalService appiumService;
			String appiumServiceUrl;
			AppiumServiceBuilder builder;
			builder = new AppiumServiceBuilder();
			builder.withIPAddress("127.0.0.1");
			builder.usingAnyFreePort();
			HashMap<String, String> environment = new HashMap();
			environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
			environment.put("ANDROID_HOME", ConfigManager.getProperty("ANDROID_HOME"));
			try {
				/*try {
					path = ConfigManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
					if(path.endsWith(".jar")) {
						path = path.substring(0, path.indexOf(".jar"));
					}
					System.out.println(path);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				// Process proc = Runtime.getRuntime().exec("cmd.exe /c start dir /w");				
				switch (GlobalVariables.process) {
				case 1:					
					if (process1 == null) {
						process1 = Runtime.getRuntime().exec("cmd.exe /c \"start cd " + environment.get("ANDROID_HOME")
								+ " && emulator " + ConfigManager.getProperty("AVD_Name1") + "\"");
						process1.waitFor(10000, TimeUnit.MILLISECONDS);
						
						Process process = Runtime.getRuntime().exec("adb install /App/vpm.apk");
						process.waitFor(20000, TimeUnit.MILLISECONDS);						
					}
					break;
				case 2:
					if (process1 == null) {
						process1 = Runtime.getRuntime().exec("cmd.exe /c \"start cd " + environment.get("ANDROID_HOME")
								+ " && emulator " + ConfigManager.getProperty("AVD_Name1") + "\"");
						process1.waitFor(10000, TimeUnit.MILLISECONDS);
						Process process = Runtime.getRuntime().exec("adb install /App/vpm.apk");
						process.waitFor(20000, TimeUnit.MILLISECONDS);
					}
					if (process2 == null) {
						process2 = Runtime.getRuntime().exec("cmd.exe /c \"start cd " + environment.get("ANDROID_HOME")
								+ " && emulator " + ConfigManager.getProperty("AVD_Name2") + "\"");
						process2.waitFor(10000, TimeUnit.MILLISECONDS);
						Process process = Runtime.getRuntime().exec("adb install /App/vpm.apk");
						process.waitFor(20000, TimeUnit.MILLISECONDS);
					}
					break;
				case 3:
					if (process1 == null) {
						process1 = Runtime.getRuntime().exec("cmd.exe /c \"start cd " + environment.get("ANDROID_HOME")
								+ " && emulator " + ConfigManager.getProperty("AVD_Name1") + "\"");
						process1.waitFor(10000, TimeUnit.MILLISECONDS);
						Process process = Runtime.getRuntime().exec("adb install /App/vpm.apk");
						process.waitFor(20000, TimeUnit.MILLISECONDS);
					}
					if (process2 == null) {
						process2 = Runtime.getRuntime().exec("cmd.exe /c \"start cd " + environment.get("ANDROID_HOME")
								+ " && emulator " + ConfigManager.getProperty("AVD_Name2") + "\"");
						process2.waitFor(10000, TimeUnit.MILLISECONDS);
						Process process = Runtime.getRuntime().exec("adb install /App/vpm.apk");
						process.waitFor(20000, TimeUnit.MILLISECONDS);
					}
					if (process3 == null) {
						process3 = Runtime.getRuntime().exec("cmd.exe /c \"start cd " + environment.get("ANDROID_HOME")
								+ " && emulator " + ConfigManager.getProperty("AVD_Name3") + "\"");
						process3.waitFor(10000, TimeUnit.MILLISECONDS);
						Process process = Runtime.getRuntime().exec("adb install /App/vpm.apk");
						process.waitFor(20000, TimeUnit.MILLISECONDS);
					}

				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// -jar selenium-server-standalone-2.44.0.jar -role hub");
			builder.withEnvironment(environment);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DEVICE_NAME"));
			// cap.setCapability(MobileCapabilityType.DEVICE_NAME, "m21");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigManager.getProperty("PLATFORM_VERSION"));
			cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/App/vpm.apk");
			cap.setCapability("appPackage", "com.adt.vpm.testapp");
			cap.setCapability("appActivity", "com.adt.vpm.activity.HomeActivity");
			cap.setCapability("automationName", "UiAutomator1");
			cap.setCapability("newCommandTimeout", 300);
			cap.setCapability("autoGrantPermissions", true);
			cap.setCapability("use-fake-ui-for-media-stream", true);
			cap.setCapability("use-fake-device-for-media-stream", true);
			if (!GlobalVariables.firstMobileInstance) {
				if (GlobalVariables.thirdMobileInstance) {
					cap.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("UDID3"));
				} else {
					cap.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("UDID1"));
					// cap.setCapability(MobileCapabilityType.UDID, "RZ8NA10SMYV");
				}
			} else if (GlobalVariables.firstMobileInstance) {
				cap.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("UDID2"));
			}
			builder.withCapabilities(cap);
			appiumService = AppiumDriverLocalService.buildService(builder);
			appiumService.stop();
			appiumService.start();
			appiumServiceUrl = appiumService.getUrl().toString();
			System.out.println("Appium Service Address : - " + appiumServiceUrl);
			try {
				return new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case IOS:
			AppiumDriverLocalService appiumService1;
			String appiumServiceUrl1;
			AppiumServiceBuilder builder1;
			builder1 = new AppiumServiceBuilder();
			builder1.withIPAddress("127.0.0.1");
			builder1.usingAnyFreePort();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("IOS_DEVICE_NAME"));
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.6");
			capabilities.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("IOS_UDID1"));			
			//capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			//capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			//	capabilities.setCapability("useNewWDA", false);
			capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.obs.vpm");	
			capabilities.setCapability("autoGrantPermissions", true);
			if (!GlobalVariables.firstMobileInstance) {
				if (GlobalVariables.thirdMobileInstance) {
					capabilities.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("IOS_UDID3"));
				} else {
					capabilities.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("IOS_UDID1"));
					// cap.setCapability(MobileCapabilityType.UDID, "RZ8NA10SMYV");
				}
			} else if (GlobalVariables.firstMobileInstance) {
				capabilities.setCapability(MobileCapabilityType.UDID, ConfigManager.getProperty("IOS_UDID2"));
			}
			builder1.withCapabilities(capabilities);
			appiumService1 = AppiumDriverLocalService.buildService(builder1);
			appiumService1.stop();
			appiumService1.start();
			appiumServiceUrl1 = appiumService1.getUrl().toString();
			try {
				return new IOSDriver<IOSElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return driver;
	}

	/*
	 * public void maximizeBrowser(WebDriver driver) {
	 * driver.manage().window().maximize(); //
	 * driver.manage().timeouts().implicitlyWait(PropertyManager.getInstance().
	 * getImplicitlyWait(), // TimeUnit.SECONDS); }
	 */
}
