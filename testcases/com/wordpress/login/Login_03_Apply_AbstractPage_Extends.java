package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_03_Apply_AbstractPage_Extends extends AbstractPage {	
	WebDriver driver;
	
	String emailTextbox = "//input[@id='usernameOrEmail']";
	String passTextbox = "//input[@id='password']";
	String loginButton = "//div[@class='login__form-action']/button";
	String emailErrorMsg = "//div[@class='form-input-validation is-error']/span";
	String passErrorMsg = "//div[@class='form-input-validation is-error']/span";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		openUrl(driver, "https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		sendkeyToElement(driver, emailTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, emailErrorMsg).trim(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		sendkeyToElement(driver, emailTextbox, "123@123.123");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, emailErrorMsg).trim(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		sendkeyToElement(driver, emailTextbox, "automation" + randomNumber() + "@gmail.com");
		clickToElement(driver, loginButton);
		
		Assert.assertEquals(getElementText(driver, emailErrorMsg).trim(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		sendkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);

		sendkeyToElement(driver, passTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, passErrorMsg).trim(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		sendkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);

		sendkeyToElement(driver, passTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementText(driver, passErrorMsg).trim(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		sendkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);

		sendkeyToElement(driver, passTextbox, "automationfc");
		clickToElement(driver, loginButton);

		Assert.assertTrue(isElementDisplayed(driver, "//h1[text() = 'Dashboard']"));
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
