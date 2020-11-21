/**
 * 
 */
package com.vpm.modules;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.vpm.pageObjects.GlobalVariables;
import com.vpm.pageObjects.Videos_Mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author maithili.s
 *
 */
public class M_VideosAction {

	public void getVideoDuration() {
		String currentDuration = Videos_Mobile.videoPlayCurrentDuration.getText();
		String totalDuration = Videos_Mobile.videoPlayTotalDuration.getText().substring(2);
		DateFormat dateFormat = null;
		if (currentDuration.matches("\\d:\\d+")) {
			dateFormat = new SimpleDateFormat("s:mm");
		} else if (currentDuration.matches("\\d\\d:\\d+")) {
			dateFormat = new SimpleDateFormat("ss:mm");
		}
		try {
			GlobalVariables.currentDuration = dateFormat.parse(currentDuration);
			GlobalVariables.cDuration = dateFormat.format(GlobalVariables.currentDuration);
			GlobalVariables.totalDuration = dateFormat.parse(totalDuration);
			GlobalVariables.tDuration = dateFormat.format(GlobalVariables.totalDuration);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getCurrentDuration() {
		String currentDuration = Videos_Mobile.videoPlayCurrentDuration.getText();
		DateFormat dateFormat = null;
		if (currentDuration.matches("\\d:\\d+")) {
			dateFormat = new SimpleDateFormat("s:mm");
		} else if (currentDuration.matches("\\d\\d:\\d+")) {
			dateFormat = new SimpleDateFormat("ss:mm");
		}
		try {
			GlobalVariables.currentDuration = dateFormat.parse(currentDuration);
			GlobalVariables.cDuration = dateFormat.format(GlobalVariables.currentDuration);
		} catch (ParseException e) {
			System.out.println("Unable to capture the duration in the video");
		}
		return GlobalVariables.currentDuration;
	}

	public void DragProgressTo(WebElement seekBar, AppiumDriver<MobileElement> driver, Double seconds) {
		int startX = seekBar.getLocation().getX();
		System.out.println(startX);

		// Get vertical location of seekbar.
		int startY = seekBar.getLocation().getY();
		System.out.println(startY);

		// Get end point of seekbar.
		int endX = seekBar.getSize().getWidth();
		System.out.println(endX);

		// Set seekbar move to position.
		// endX * 0.6 means at 60% of seek bar width.
		// endX * 0.9 means at 100% of seek bar width.
		int moveToXDirectionAt = (int) (endX * seconds);
		System.out.println("Moving seek bar at " + moveToXDirectionAt + " In X direction.");

		// Moving seekbar using TouchAction class.
		TouchAction act = new TouchAction(driver);
		if (seconds == 0) {
			act.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(0, startY)).release().perform();
		} else {
			act.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(moveToXDirectionAt, startY))
					.release().perform();
		}
	}

	public Boolean isElementPresent(WebElement element) {
		try {
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void resetTheVideo() {
		if (isElementPresent(Videos_Mobile.videoPauseBtn)) {
			Videos_Mobile.videoPauseBtn.click();
		}
		getVideoDuration();
		if (!GlobalVariables.cDuration.equals("0:00")) {
			// videosAction.DragProgressTo(Videos_Mobile.videoSeekBar, driver, 0.0);
			Videos_Mobile.videoSeekBar.sendKeys("0");
		}
	}

	public void swipeTo(String fileName, AppiumDriver<MobileElement> driver) {
		WebElement abc = driver.findElement(MobileBy.xpath(
				"//android.widget.TextView[@text='" + fileName + "']/preceding-sibling::android.widget.FrameLayout"));
		driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).setAsVerticalList().scrollIntoView("
						+ "new UiSelector().text(\"" + fileName + "\"));"));
	}
}
