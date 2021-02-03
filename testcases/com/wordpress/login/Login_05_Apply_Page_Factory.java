package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageObjects.wordpress.DashboardPageObject;
import pageObjects.wordpress.LoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_05_Apply_Page_Factory extends AbstractPage {	
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		
		//loginPage = new LoginPageObject(driver);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		openUrl(driver, "https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		loginPage.inputToEmailTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		loginPage.inputToEmailTextbox("123@123.123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		loginPage.inputToEmailTextbox("automation" + randomNumber() + "@gmail.com");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextbox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		
		dashboardPage = PageGeneratorManager.getDashboardPage(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
}
