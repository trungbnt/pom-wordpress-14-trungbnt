package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankguru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;
	
	public RegisterPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Register Page: " + driver.toString());
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_ID, email);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDText() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID);
		return getElementText(driver, RegisterPageUI.USER_ID);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD);
		return getElementText(driver, RegisterPageUI.PASSWORD);
	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);	
	}
}
