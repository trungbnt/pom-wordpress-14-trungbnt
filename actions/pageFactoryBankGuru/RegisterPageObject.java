package pageFactoryBankGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;
	
	public RegisterPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.NAME, using = "emailid")
	private WebElement emailID; 
	
	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userID;
	
	@FindBy(how = How.XPATH, using = "//td[text()='Password :']/following-sibling::td")
	private WebElement password;

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailID);
		sendkeyToElement(driver, emailID, email);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, submitButton);
		clickToElement(driver, submitButton);
	}

	public String getUserIDText() {
		waitForElementVisible(driver, userID);
		return getElementText(driver, userID);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, password);
		return getElementText(driver, password);
	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);	
	}
}
