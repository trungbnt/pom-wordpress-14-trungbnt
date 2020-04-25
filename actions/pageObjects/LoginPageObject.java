package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickToContinueOrLoginButton() {
		waitForElementClickable(driver, LoginPageUI.COUNTINUE_OR_LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.COUNTINUE_OR_LOGIN_BUTTON);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);		
	}
	
}
