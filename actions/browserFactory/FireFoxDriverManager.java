package browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FireFoxDriverManager extends DriverManager {

	@Override
	protected void createDriver() {
		WebDriverManager.firefoxdriver().setup();
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--incognito");
		//capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new FirefoxDriver();
	}

}

