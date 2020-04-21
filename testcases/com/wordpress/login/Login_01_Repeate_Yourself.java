package com.wordpress.login;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01_Repeate_Yourself {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='usernameOrEmail']");
	By passTextbox = By.xpath("//input[@id='password']");
	By loginButton = By.xpath("//div[@class='login__form-action']/button");
	By emailErrorMsg = By.xpath("//div[@class='form-input-validation is-error']/span");
	By passErrorMsg = By.xpath("//div[@class='form-input-validation is-error']/span");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		driver.findElement(emailTextbox).sendKeys("");
		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(emailErrorMsg).getText().trim(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		driver.findElement(emailTextbox).sendKeys("123@123.123");
		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(emailErrorMsg).getText().trim(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {
		driver.findElement(emailTextbox).sendKeys("automation" + randomNumber() + "@gmail.com");
		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(emailErrorMsg).getText().trim(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {
		driver.findElement(emailTextbox).sendKeys("automationeditor");
		driver.findElement(loginButton).click();

		driver.findElement(passTextbox).sendKeys("");
		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(passErrorMsg).getText().trim(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {
		driver.findElement(emailTextbox).sendKeys("automationeditor");
		driver.findElement(loginButton).click();

		driver.findElement(passTextbox).sendKeys("123");
		driver.findElement(loginButton).click();

		Assert.assertEquals(driver.findElement(passErrorMsg).getText().trim(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {
		driver.findElement(emailTextbox).sendKeys("automationeditor");
		driver.findElement(loginButton).click();

		driver.findElement(passTextbox).sendKeys("automationfc");
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text() = 'Dashboard']")).isDisplayed());
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
