package com.bankguru.login;

import org.testng.annotations.Test;

import pageFactoryBankGuru.HomePageObject;
import pageFactoryBankGuru.LoginPageObject;
import pageFactoryBankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Login_02_Selenium_PageFactory {
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	String userIDValue, passwordValue, loginPageUrl;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://demo.guru99.com/v4/");
		
		loginPage = new LoginPageObject(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {
		loginPage.clickToHereLink();
		
		registerPage = new RegisterPageObject(driver);
		
		registerPage.inputToEmailTextbox("trungcr@gmail.com");
		registerPage.clickToSubmitButton();
		
		userIDValue = registerPage.getUserIDText();
		passwordValue = registerPage.getPasswordText();
		
		registerPage.openLoginPage(loginPageUrl);
		
		loginPage = new LoginPageObject(driver);
	}
	
	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
