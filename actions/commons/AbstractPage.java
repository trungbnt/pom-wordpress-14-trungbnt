package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void back(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}
	 
	public boolean areAllWinDowsClosedWithoutParentWindow(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;			
		} 
		return false;
	}
	
	public By byXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement findElementByXpath(WebDriver driver, String locator) {
		 return driver.findElement(byXpath(locator));
	}
	
	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		 return driver.findElements(byXpath(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}
	
	public void selectValueInDropDown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}
	
	public String getSelectedItemInDropDown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public void sleepInSeconds(long secondNumber) {
		try {
			Thread.sleep(secondNumber * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void Custom_Dropdown_Multi_Item(WebDriver driver, String parentXpath, String allItemXpath, String[] expectedValueItem, String itemsSelectedXpath) throws Exception {
		element = findElementByXpath(driver, parentXpath);
		
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		sleepInSeconds(1);
		
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemXpath)));
		
		elements = findElementsByXpath(driver, allItemXpath);
		for (WebElement childElement : elements) {
			if (childElement.getText().equals(expectedValueItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					js.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSeconds(1);
					js.executeScript("arguments[0].click();", childElement);
				}
				sleepInSeconds(1);
				break;
			}			
		}
	}
	
	public int countElementNumber(WebDriver driver, String locator) {
		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}
	
	public void checkToCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}
	
	public void switchToFrameOrIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}
	
	public void switchToDefautContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}
	
	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}
	
	public Object executeForBrowser(WebDriver driver, String javascript) {
		js = (JavascriptExecutor) driver;		
		return js.executeScript(javascript);
	}
	
	public boolean verifyTextInInnerText(WebDriver driver, String TextExpected) {
		js = (JavascriptExecutor) driver;
		String TextActual = (String) js.executeScript("return document.documentElement.innerText.match('"+TextExpected+"')[0]");	
		return TextActual.equals(TextExpected);
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scroolBy(0, document.body.scrollHeight)");
	}
	
	public void clickElementByJS(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
	}
	
	public void scrollToElement(WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}
	
	public void sendKeyByJS(WebDriver driver, String locator, String value) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '"+value+"');", findElementByXpath(driver, locator));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeName) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('"+attributeName+"');", findElementByXpath(driver, locator));
	}
	
	public boolean isImageLoaded (WebDriver driver, String locator) {
		js = (JavascriptExecutor) driver;
		boolean status = (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", findElementByXpath(driver, locator));
		if (status) {
			return true;
		} return false;
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}
	
	private Select select;
	private JavascriptExecutor js;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;
	//private shortTimeout = 5;
	private long longTimeout = 30;
}
