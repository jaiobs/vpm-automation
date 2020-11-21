/**
 * 
 */
package com.vpm.cucumber;

import org.openqa.selenium.WebDriver;

import com.vpm.managers.DriverManager;

/**
 * @author maithili.s
 *
 */
public class TestContext {
	
	private DriverManager driverManager;
	
	
	public TestContext() {
		driverManager = new DriverManager();
	}

	public DriverManager getDriver() {
		return driverManager;
	}
}
