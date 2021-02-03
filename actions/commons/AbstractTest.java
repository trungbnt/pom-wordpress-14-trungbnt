package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	WebDriver driver;
	
	public WebDriver getBrowserDriver(String browserName) {
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", ".\\browserDriver\\msedgedriver.exe");
			WebDriverManager.edgedriver().arch64().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Please choose your browser!");
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		return driver;
	}
	


	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
}
