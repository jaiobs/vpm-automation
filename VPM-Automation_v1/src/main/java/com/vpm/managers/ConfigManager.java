package com.vpm.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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

import com.vpm.enums.DriverType;
import com.vpm.enums.EnvironmentType;
import com.vpm.pageObjects.GlobalVariables;

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
		String path = "";
		try {
			try {
				path = ConfigManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
				if(path.endsWith(".jar")) {
					path = path.substring(0, path.indexOf(".jar"));
				}
				System.out.println(path);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//InputStream in = FileInputStream.class.getResourceAsStream(path+"\\com\\vpm\\configuration\\config.properties");
			
			FileReader reader = new FileReader(
					new File(path+"/com/vpm/configuration/config.properties"));
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
		String browserName = "";	
		if (GlobalVariables.scenarioName.contains("Landing page") || GlobalVariables.scenarioName.contains("Server Settings")
				|| GlobalVariables.scenarioName.contains("Video Player")) {
			browserName = GlobalVariables.Browser1;
		}else {
		if(GlobalVariables.Browser1.contains("invoked")) {
			browserName = GlobalVariables.Browser2;
			GlobalVariables.Browser1 = GlobalVariables.Browser1.substring(0, GlobalVariables.Browser1.indexOf(" invoked"));
		} else {
			browserName = GlobalVariables.Browser1;
			GlobalVariables.Browser1 = GlobalVariables.Browser1 + " invoked";
		}
		}
		if (browserName == null || browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else if (browserName.equalsIgnoreCase("ie"))
			return DriverType.INTERNETEXPLORER;
		else if (browserName.equalsIgnoreCase("android"))
			return DriverType.ANDROID;
		else if(browserName.equalsIgnoreCase("ios"))
			return DriverType.IOS;
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
	
	public static String getProperty(String propertyText) {
		Properties properties = new Properties();
		String propString = "";
		FileReader reader;
		try {
			reader = new FileReader(
					new File("classpath.properties"));
			properties.load(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		propString = properties.getProperty(propertyText);
		if (propString == null) {
			System.out.println("WARNING : Could not find the key :'" + propertyText + "' in properties file");
			propString = "Error";
	}
		return propString;
	}

}
