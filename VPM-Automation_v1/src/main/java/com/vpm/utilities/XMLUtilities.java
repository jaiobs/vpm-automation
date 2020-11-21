/**
 * 
 */
package com.vpm.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * @author maithilis
 *
 */
public class XMLUtilities {

	public static Properties configuration = new Properties();

	//Xml path to the specified in the static block for converting xml into properties
	static {
		try {
			XMLUtilities.convertXml2Properties("src/test/java/configuration/configuration.xml", configuration);
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//This method is to convert xml file to properties file
	public static void convertXml2Properties(String xmlFileLocation, Properties properties)
			throws InvalidPropertiesFormatException, IOException {
		InputStream is = new FileInputStream(xmlFileLocation);
		// load the xml file into properties format
		properties.loadFromXML(is);
	}
}
