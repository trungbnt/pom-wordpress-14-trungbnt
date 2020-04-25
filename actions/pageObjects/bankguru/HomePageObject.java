package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankguru.HomePageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;
	
	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Home Page: " + driver.toString());
	}

	public boolean isWelcomeMessageDisplayed() {
		return isElementDisplayed(driver, HomePageUI.HOMEPAGE_MESSAGE);
	}
}