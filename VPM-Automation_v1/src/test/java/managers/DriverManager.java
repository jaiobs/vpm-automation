/**
 * 
 */
package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import managers.ConfigManager;

import enums.DriverType;
import enums.EnvironmentType;

/**
 * @author maithili.s
 *
 */
public class DriverManager {
	
	public static WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	
	public DriverManager() {
		driverType = ConfigManager.getInstance().getBrowser();
		environmentType = ConfigManager.getInstance().getEnvironment();
	}

	public WebDriver getDriver() {		
		if (driver == null)
			driver = createDriver();			
			driver = driverSetup();
		return driver;
	}

	private WebDriver createDriver() {
		switch (environmentType) {
		case LOCAL:
			driver = createLocalDriver();
			break;
		case REMOTE:
			driver = createRemoteDriver();
			break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	
	private WebDriver createLocalDriver() {
		switch (driverType) {
		case FIREFOX:
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "\\lib\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			return new FirefoxDriver();
		case CHROME:
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\lib\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("use-fake-ui-for-media-stream");
			options.setExperimentalOption("w3c", false);
			return new ChromeDriver(options);
		case EDGE:
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\lib\\edgedriver_win64\\msedgedriver.exe");
			return new EdgeDriver();
		case INTERNETEXPLORER:
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\lib\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}			
		return driver;
	}

	public WebDriver driverSetup() {
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(PropertyManager.getInstance().getImplicitlyWait(), TimeUnit.SECONDS);
		return driver;
	}

}
