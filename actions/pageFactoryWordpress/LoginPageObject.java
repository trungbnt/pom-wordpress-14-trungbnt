package pageFactoryWordpress;

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
	
	@FindBy(how = How.ID, using = "usernameOrEmail")
	private WebElement emailTextbox;
	
	@FindBy(how = How.XPATH, using = "//div[@class='login__form-action']/button")
	private WebElement loginButton;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.XPATH, using = "//div[@class='form-input-validation is-error']/span")
	private WebElement errorMessage;

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, email);
	}

	public void clickToContinueOrLoginButton() {
		waitForElementClickable(driver, emailTextbox);
		clickToElement(driver, emailTextbox);
	}

	public String getEmailErrorMessage() {
		waitForElementVisible(driver, errorMessage);
		return getElementText(driver, errorMessage);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);		
	}
	
	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);	
	}
}
