package pageFactoryBankGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//a[text() = 'here']")
	private WebElement hereLink;
	
	@FindBy(how = How.NAME, using = "uid")
	private WebElement userIDTextbox;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement loginButton;
	

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

	public void clickToHereLink() {
		waitForElementClickable(driver, hereLink);
		clickToElement(driver, hereLink);
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, userIDTextbox);
		sendkeyToElement(driver, userIDTextbox, userIDValue);
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, passwordValue);		
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}
}
