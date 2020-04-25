package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.DashboardPageUI;

public class DashboardPageObject extends AbstractPage{
	WebDriver driver;
	
	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashboardPageUI.HEADER_TEXT);
	}
	
}
