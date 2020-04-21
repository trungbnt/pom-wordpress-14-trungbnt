package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_02_Apply_AbstractPage_Init {
	AbstractPage abstractPage;
	
	WebDriver driver;
	By emailTextboxBy = By.xpath("//input[@id='usernameOrEmail']");
	By passTextboxBy = By.xpath("//input[@id='password']");
	By loginButtonBy = By.xpath("//div[@class='login__form-action']/button");
	By emailErrorMsgBy = By.xpath("//div[@class='form-input-validation is-error']/span");
	By passErrorMsgBy = By.xpath("//div[@class='form-input-validation is-error']/span");
	
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
		//driver.get("https://automationfc.wordpress.com/wp-admin/");
		abstractPage.openUrl(driver, "https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		//driver.findElement(emailTextboxBy).sendKeys("");
		abstractPage.sendkeyToElement(driver, emailTextbox, "");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText().trim(), "Please enter a username or email address.");
		Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMsg).trim(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		//driver.findElement(emailTextboxBy).sendKeys("123@123.123");
		abstractPage.sendkeyToElement(driver, emailTextbox, "123@123.123");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText().trim(), "Please log in using your WordPress.com username instead of your email address.");
		Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMsg).trim(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		//driver.findElement(emailTextboxBy).sendKeys("automation" + randomNumber() + "@gmail.com");
		abstractPage.sendkeyToElement(driver, emailTextbox, "automation" + randomNumber() + "@gmail.com");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);
		
		//Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText().trim(), "User does not exist. Would you like to create a new account?");
		Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMsg).trim(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		//driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//driver.findElement(passTextboxBy).sendKeys("");
		abstractPage.sendkeyToElement(driver, passTextbox, "");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//Assert.assertEquals(driver.findElement(passErrorMsgBy).getText().trim(), "Don't forget to enter your password.");
		Assert.assertEquals(abstractPage.getElementText(driver, passErrorMsg).trim(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		//driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//driver.findElement(passTextboxBy).sendKeys("123");
		abstractPage.sendkeyToElement(driver, passTextbox, "");
		//driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//Assert.assertEquals(driver.findElement(passErrorMsgBy).getText().trim(), "Oops, that's not the right password. Please try again!");
		Assert.assertEquals(abstractPage.getElementText(driver, passErrorMsg).trim(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		//driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//driver.findElement(passTextboxBy).sendKeys("automationfc");
		abstractPage.sendkeyToElement(driver, passTextbox, "automationfc");
		// driver.findElement(loginButtonBy).click();
		abstractPage.clickToElement(driver, loginButton);

		//Assert.assertTrue(driver.findElement(By.xpath("//h1[text() = 'Dashboard']")).isDisplayed());
		Assert.assertTrue(abstractPage.isElementDisplayed(driver, "//h1[text() = 'Dashboard']"));
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
