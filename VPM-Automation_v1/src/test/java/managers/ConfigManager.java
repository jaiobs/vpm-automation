package managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import enums.DriverType;
import enums.EnvironmentType;

/**
 * @author maithili.s
 *
 */
public class ConfigManager {

	public static Properties configuration = new Properties();
	private static ConfigManager configManager = new ConfigManager();

	public static ConfigManager getInstance() {
		return configManager;
	}

	public static String getApplicationProperty(String property) {

		// Property file reader

		String propString = "";
		try {
			FileReader reader = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\java\\configuration\\config.properties");
			configuration.load(reader);
			propString = configuration.getProperty(property);
			if (propString == null) {
				System.out.println("WARNING : Could not find the key :'" + property + "' in properties file");
				propString = "Error";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return propString;
	}

	// This method is to convert xml file to properties file
	public static void convertXml2Properties(String xmlFileLocation, Properties properties)
			throws InvalidPropertiesFormatException, IOException {
		InputStream is = new FileInputStream(xmlFileLocation);
		// load the xml file into properties format
		properties.loadFromXML(is);
	}

	/*
	 * public static void propertyReader(String fileLocation) throws IOException {
	 * FileReader reader = new
	 * FileReader("src/test/java/configuration/CurrentTestEnvironment.properties");
	 * Properties properties = new Properties(); properties.load(reader); }
	 */

	public DriverType getBrowser() {
		String browserName = getApplicationProperty("BROWSER");
		if (browserName == null || browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equalsIgnoreCase("ie"))
			return DriverType.INTERNETEXPLORER;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public EnvironmentType getEnvironment() {
		String environmentName = getApplicationProperty("ENVIRONMENT");
		if (environmentName == null || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equals("remote"))
			return EnvironmentType.REMOTE;
		else
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

	public long getImplicitlyWait() {
		String implicitlyWait = getApplicationProperty("implicitlyWait");
		if (implicitlyWait != null) {
			try {
				return Long.parseLong(implicitlyWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;
	}

}
