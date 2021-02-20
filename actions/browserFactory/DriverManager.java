package browserFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import commons.GlobalConstants;

public abstract class DriverManager {
	protected WebDriver driver;
	
	protected abstract void createDriver();
	
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
	public WebDriver getDriver() {
		//Singleton Pattern
		if (driver == null) {
			createDriver();
		}
		driver.get(GlobalConstants.WORDPRESS_URL);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
}
