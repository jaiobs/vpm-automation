/**
 * 
 */
package com.vpm.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author maithili.s
 *
 */
public class LandingPage {

	@FindBy(css = "#accordionSidebar div.sidebar-brand-icon")
	public static WebElement brandIcon;
	
	@FindBy(css = "#accordionSidebar div.sidebar-brand-text")
	public static WebElement brandText;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(1) img")
	public static WebElement videosIcon;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(1) span")
	public static WebElement videosMenu;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(2) img")
	public static WebElement feedsIcon;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(2) span")
	public static WebElement feedsMenu;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(3) img")
	public static WebElement settingsIcon;
	
	@FindBy(css = "#accordionSidebar>li:nth-of-type(3) span")
	public static WebElement settingsMenu;
	
	@FindBy(css = "#accordionSidebar>li.active span")
	public static WebElement activeMenu;
	
	@FindBy(css = "div.video-content h1")
	public static WebElement activeMenuHeader;
	
	
}
