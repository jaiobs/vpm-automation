/**
 * 
 */
package com.vpm.pageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author maithili.s
 *
 */
public class CommonMethods {

	public void delay(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hideKeyboard(AppiumDriver<MobileElement> driver) {
		driver.hideKeyboard();
	}

	public void scrollDownToXPath(AppiumDriver<MobileElement> driver) {
		 Map<String, Object> params = new HashMap<>();
         params.put("start","40%,90%");
         params.put("end","40%,20%");
         params.put("duration","2");
         Object res= driver.executeScript("mobile:touch:swipe",params);
	}

	public void swipeTo(AppiumDriver<MobileElement> driver, Double swipeToValue) {
		TouchAction action = new TouchAction(driver);
		PointOption p1= new PointOption();
		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.6;
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * swipeToValue;
		int h2 = screenHeightEnd.intValue();
		action.press(p1.point(0, h1)).moveTo(p1.point(0,h2)).release().perform();
	}
}
