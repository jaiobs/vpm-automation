package com.vpm.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.vpm.managers.ConfigManager;
import com.vpm.pageObjects.LoginPage;

public class LoginAction {		
	
	List<HashMap<String, String>> status = new ArrayList<HashMap<String, String>>(); 
	public static void Execute(WebDriver driver, List<HashMap<String, String>> map) throws Exception {	
		Page.getInstance().implicitlyWait(driver);				
		try {
		for (int i = 0; i < map.size(); i++) {	
			System.out.println("Login Test Data Numbers : "+map.size());
			if(!map.get(i).isEmpty()) {
			LoginPage.email.sendKeys(map.get(i).get("username").toString());									
			LoginPage.Next.click();
			Page.getInstance().implicitlyWait(driver);
			LoginPage.password.sendKeys(map.get(i).get("password").toString());
			LoginPage.signin_button.click();
			Page.getInstance().implicitlyWait(driver);
			/*LoginPage.signin_button.click();
			if (map.get(i).get("Valid?").toString().equals("Invalid")) {
				verifyInvalidLogin(map, i);
				
			} else */ if (map.get(i).get("Valid?").toString().equals("Valid")) {
				verifyValidLogin(map, i);
				LoginPage.staySignIn_No.click();
				Page.getInstance().implicitlyWait(driver);				
			}			
			Page.getInstance().pageLoadTimeOut(driver);
		  }
		}
		}catch(WebDriverException we) {
			
		}
		Page.getInstance().pageLoadTimeOut(driver);
	}

	public static void verifyInvalidLogin(List<HashMap<String, String>> map, int index) {
		try {
		//Assert.assertTrue(LoginPage.loginError.getText().contains("Your account or password is incorrect"));
		}catch(AssertionError ae) {
			ae.getLocalizedMessage();	
			map.get(index).put("Status", "Failed");
		}
	}
	
	public static void verifyValidLogin(List<HashMap<String, String>> map, int index) {
		try {
		//	Assert.assertTrue(LoginPage.staySignIn.isDisplayed());
		}catch(AssertionError ae) {
			ae.getLocalizedMessage();	
			map.get(index).put("Status", "Failed");
		}		
	}
}