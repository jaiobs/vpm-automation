package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.Hooks;

public class LoginPage {
		
	@FindBy(css = "input[type='email']")
	public static WebElement email;

	@FindBy(css = "input[value='Next']")
	public static WebElement Next;

	@FindBy(name = "passwd")
	public static WebElement password;

	@FindBy(css = "input[value='Sign in']")
	public static WebElement signin_button;
	
	@FindBy(id = "passwordError")
	public static WebElement loginError;
	
	@FindBy(css = ".text-title")
	public static WebElement staySignIn;
	
	@FindBy(css = "input[value='No']")
	public static WebElement staySignIn_No;
	
	@FindBy(css = "input[value='Yes']")
	public static WebElement staySignIn_Yes;
	
	@FindBy(xpath = "//*[contains(@class,'userInitials')]")
	public static WebElement signOut_Menu;
	
	@FindBy(xpath = "//span[contains(@id, 'SignOut')]")
	public static WebElement signOut_Link;	
	
	/*public WebElement usernameTxtField;
	public WebElement nextBtn;
	public WebElement passwordTxtField;
	public WebElement signInBtn;
	public WebElement passwordError;
	public WebElement staySignIn;
	public WebElement staySignInNoBtn;
	public WebElement staySignInYesBtn;
	public WebElement signOutMenu;
	public WebElement signOutLink;
	
	public WebElement usernameTxtField() {

		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement nextBtn() {
		return (getWebElement(UILocatorUtilities.getLocator()));	
	}
	
	public WebElement passwordTxtField() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement signInBtn() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement passwordError() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement staySignIn() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement staySignInNoBtn() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement staySignInYesBtn() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement signOutMenu() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}
	
	public WebElement signOutLink() {
		return (getWebElement(UILocatorUtilities.getLocator()));
	}*/
	
}