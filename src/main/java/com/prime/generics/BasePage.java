package com.prime.generics;

import static org.testng.Assert.assertEquals;
<<<<<<< HEAD
import static org.testng.Assert.fail;

=======
>>>>>>> dev
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
<<<<<<< HEAD
import com.prime.pageFactory.pages.fpj.MasterPage;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
=======
>>>>>>> dev
import io.qameta.allure.Allure;

public class BasePage {

<<<<<<< HEAD
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WebDriverWait otherWait;
	private Actions action;
	private String actual;
	private String error;
	public static List<String> csvData = null;
	public static String uploadCompleteStatus = "//*[@class='uploadstatus' and text()=' (Uploaded)']";
	public static String loadComplete = "//div[@id='divInprogressBackgroung' and contains(@style,'display: none')]";
	public static String pwd = System.getProperty("user.dir");
	public static String prodNumber = null;

	public BasePage(WebDriver driver) throws Exception {
		this.driver = driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		if (WebDriverManager.getWebdriverWait() == null) {
			WebDriverManager.setWebdriverWait(wait);
		}
	}

	public BasePage() {
	}

	/**
	 * This method return the random wait
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String randomWait() {
		int random = 0;
		try {
			Random r = new Random();
			random = r.nextInt((10 - 1) + 1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(random);
	}

	/**
	 * This method enters text/testData in text box field
	 * @param WebElement
	 * @param text
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean typeOnElement(WebElement element, String text) throws Exception {
		boolean flag = false;
		try {
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			action.moveToElement(element).build().perform();// Focus on element
			element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE) + text);
			Helper.INSTANCE.logEventToReport(driver, "pass", element, "TypeOnElement  -  " + text);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method performs select a value from drop down 
	 * @param drpObject
	 * @param value
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean selectValueFromDropdown(List<WebElement> drpObject, String value) throws Exception {
		boolean result = false;
		try {
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOfAllElements(drpObject));
			for (WebElement webElement : drpObject) {
				if (webElement.getText().equalsIgnoreCase(value)) {
					result = clickOnElement(webElement);
					Thread.sleep(5000);
					Helper.INSTANCE.logEventToReport(driver, "Pass", value, "Selected In Drop Down");
					result = true;
				}
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", value, e.getMessage());
		}
		return result;
	}

	/**
	 * This methods used to click on the element on webpage
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean clickOnElement(WebElement element) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			if (waitForElementVisible(element)) {
				WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				Helper.INSTANCE.logEventToReport(driver, "pass", element, "ClickOnElement");
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage() + Thread.currentThread().getId());
		}
		return flag;
	}

	/**
	 * This method performs to wait until the element visible in DOM of the page 
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public boolean waitForElementVisible(WebElement element) throws Exception {
		boolean elementPresent = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class)
					.ignoring(NoSuchElementException.class);
			elementPresent = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "Error", element, e.getMessage());
		}
		return elementPresent;
	}

	/**
	 * This method performs until the element is loaded with given seconds
	 * @param element
	 * @param max_Time
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean waitForElementLoadWithOutLog(String element, int max_Time) throws Exception {
		boolean flag = false;
		try {
			otherWait = new WebDriverWait(driver, max_Time);
			otherWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
			Helper.INSTANCE.logEventToReport(driver, "pass", element, "visibled");
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	/**
	 * This method navigates the specific URL
	 * @param url
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean navigate(String url) throws Exception {
		boolean flag = false;
		url = url.replaceAll("<ENV>", BaseTest.properties.getProperty("Environment"));
		try {
			driver.navigate().to(url);
			Allure.step("Navigated to " + url);
			flag = true;
		} catch (Exception e) {
			Assert.fail("Error while navigating to url: " + url);
		}
		return flag;
	}

	/**
	 * This method used to click on WebElement on webpage
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean clickOnElement(WebElement element, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			Allure.step(desc);
			flag = true;
		} catch (Exception e) {
			error = desc + " expected true but found false";
			Helper.INSTANCE.setErrorMessage(Helper.INSTANCE.getCurrentTestCaseId(), error);
			Assert.fail(error);
		}
		return flag;
	}

	/**
	 * This method is used to upload the file in portal Ex: PDF, CSV
	 * @param exePath
	 * @param fileName
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean uploadFile(String exePath, String fileName, WebElement element) throws Exception {
		boolean flag = false;
		try {
			assertEquals(uploadUsingThread(element, fileName), true);
			flag = true;

		} catch (AssertionError | Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}

		return flag;
	}

	/**
	 * This method performs file upload to corresponding browser thread
	 * @param element
	 * @param filePath
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	@SuppressWarnings("deprecation")
	protected boolean uploadUsingThread(WebElement element, String filePath) throws Exception {
		boolean flag = false;
		try {
			waitForElementVisible(element);
			Threadmanager t1 = new Threadmanager(WebDriverManager.getFileUploadObject(), element, filePath, driver);
			t1.start();
			t1.join();
			t1.stop();

			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Issue in uploading file");
		}
		return flag;

	}

	/**
	 * This method performs to wait until the element visible in DOM of the page using xpath locator and return the WebElement
	 * @param xpathExpression
	 * @return WebElement
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected WebElement waitAndReturnElementPresent(String xpathExpression) throws Exception {
		WebElement element = null;
		try {
			System.out.println("Before wait time :   "
					+ new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()));
			element = WebDriverManager.getWebdriverWait()
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
			new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class)
					.ignoring(NoSuchElementException.class);
//			Helper.INSTANCE.logEventToReport(driver, "Pass", xpathExpression, "Found And Returned");
//			System.out.println("After success wait time :   "
//					+ new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()));

		} catch (StaleElementReferenceException ste) {
			waitAndReturnElementPresent(xpathExpression);
		} catch (Exception e) {
			Assert.fail("XPATH => " + xpathExpression + " Not Found ");
//			System.out.println(
//					"After wait time :   " + new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()));
		}
		return element;
	}

	/**
	 * This method use to select dropdown value based on value provided in argument
	 * @param element
	 * @param value
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean selectByValue(WebElement element, String value, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByValue(value);
			Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "pass", desc + " : " + value);
			flag = true;
		} catch (NoSuchElementException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		} finally {
			// Thread.sleep(6000);
		}
		return flag;
	}

	/**
	 * This method performs verify the presence of element
	 * @param List<WebElement>
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementPresent(List<WebElement> element) throws Exception {
		boolean flag = false;
		if (element.size() > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	protected boolean isElementNotPresent(List<WebElement> element) throws Exception {
		boolean flag = true;
		if (element.size() > 0) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * This method performs verify the presents of element on webpage
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementPresentWithoutLog(WebElement element) throws Exception {
		boolean flag = false;
		try {
			waitForElementVisible(element);
			element.isDisplayed();
			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	/**
	 * It is used to wait until the file uploaded is completed Ex: PDF, CSV
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean waitTillUploadComplete(String element) throws Exception {
		boolean flag = false;
		try {
			otherWait = new WebDriverWait(driver, 150);
			otherWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
			Helper.INSTANCE.logEventToReport(driver, "pass", "File", "Uploaded");
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method performs wait until the element loading with specified time in seconds
	 * @param element
	 * @param max_Time
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean waitForElementLoad(String element, int max_Time) throws Exception {
		boolean flag = false;
		try {
			otherWait = new WebDriverWait(driver, max_Time);
			otherWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method is used to wait until the element is invisible on the DOM
	 * 
	 * @param element
	 * @param timeOut
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	public boolean waitForVisibleAndInVisibleElement(WebElement element, int timeOut) throws Exception {
		boolean elementPresent = false;
		try {
			// waitForDocumentReady();
			new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
			new WebDriverWait(driver, timeOut).ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			new WebDriverWait(driver, timeOut).ignoring(InvalidElementStateException.class)
					.ignoring(NoSuchElementException.class);
			elementPresent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elementPresent;
	}

	/**
	 * This method performs wail until the element is invisible on the DOM
	 * 
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	public boolean waitForInVisibleElementByXpath(String element) throws Exception {
		boolean elementPresent = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait()
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
			new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class)
					.ignoring(NoSuchElementException.class);
			Helper.INSTANCE.logEventToReport(driver, "Pass", element, "InVisible & Stable");
			elementPresent = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "Error", element, e.getMessage() + Thread.currentThread().getId());
		} finally {
			Thread.sleep(2000);
		}
		return elementPresent;
	}

	/**
	 * This method is used to wait for specified number of milliseconds
	 * @param inseconds
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected void waitFor(String inseconds) {
		try {
			Thread.sleep(Integer.parseInt(inseconds + "000"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method performs type operations by JavascriptExecutor
	 * @param element
	 * @param text
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean typeOnElementJs(WebElement element, String text) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			if (waitForElementVisible(element)) {
				WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				action.moveToElement(element).build().perform();// Focus on element
				js.executeScript("arguments[0].value='';", element);
				js.executeScript("arguments[0].value='" + text + "';", element);
				Helper.INSTANCE.logEventToReport(driver, "pass", element, "TypeOnElementByJS  -  " + text);
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method wait for page load by Javascript Executor
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean waitForDocumentReady() throws Exception {
		boolean result = false;
		try {
			if (ConfigurationManager.getSleepMode()) {
				Thread.sleep(ConfigurationManager.getSleepTimeout());
			}
			result = WebDriverManager.getWebdriverWait().until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
							.equals("complete")
							&& Boolean.valueOf(
									(boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.isReady"))
									.equals(true);
				}
			});
		} catch (Exception e) {
			if (e.getMessage().contains("jQuery is not defined")) {
				WebDriverManager.getWebdriverWait().until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver driver) {
						return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
								.equals("complete");
					}
				});
			} else {
				Thread.sleep(10000);
				// throw new Exception("Unable to complete the loading page !", e);
			}
		}
		return result;
	}

	/**
	 * This method handles browser based alert by accept/dismiss after clicking
	 * @param element
	 * @param text
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean alertHandlingAcceptorDismiss(WebElement element, String text) throws Exception {
		boolean flag = false;
		try {
			System.out.println("alert clicking !!!");
			element.click();
			wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			wait.ignoring(UnhandledAlertException.class).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			if (text.equalsIgnoreCase("accept")) {
				alert.accept();
				// Helper.INSTANCE.logEventToReport(driver, "Pass", element,
				// alert.getText()+"Alert Accepted");
				flag = true;
			} else if (text.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
				Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
		}
		return flag;
	}

	/**
	 * This method clicks on button, handles popup window without scrolling to element
	 * @param element
	 * @param text
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date 7/12/2022
	 */
	protected boolean clickOnElementWithoutScrolling(WebElement element, String text, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			if (waitForElementVisible(element)) {
				WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				if (text.equalsIgnoreCase("accept")) {
					alert.accept();
					Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Accepted");
					flag = true;
				} else if (text.equalsIgnoreCase("dismiss")) {
					alert.dismiss();
					Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
					flag = true;
				}
				Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "pass", desc);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage() + Thread.currentThread().getId());
		}
		return flag;
	}

	/**
	 * This method is used to select the value from drop down by visible text and
	 * @param element
	 * @param value
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date 7/12/2022	 
	 */
	protected boolean selectDropdownValueByVisibleText(WebElement element, String value, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			flag = true;
		} catch (NoSuchElementException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "fail", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method used to clear textbox value 
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	public boolean clearTextBox(WebElement element, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			element.clear();
			Helper.INSTANCE.logEventToReport(driver, "pass", desc);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method performs verify the presence of element on webpage
	 * 
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementPresent(WebElement element, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForElementVisible(element);
			element.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

	/**
	 * This method performs verify the presence of elements on webpage
	 * @param List<WebElement>
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementPresent(List<WebElement> element, String desc) throws Exception {
		boolean flag = false;
		try {
			if (element.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
			Helper.INSTANCE.logEventToReport(driver, "pass", desc);
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
			throw new Exception("Unable to determine if the element is present.", e);
		}
		return flag;
	}

	/**
	 * This method asserts expected and actual value and returns boolean 
	 * @param expected
	 * @param actual
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 14/12/2022
	 */
	protected boolean assertExpectedActualValuesNotMatching(String expected, String actual, String desc)
			throws Exception {
		boolean flag = false;
		String description = desc + " : Expected Result--> " + expected + "||" + "Actual Result-->" + actual;
		try {
			Assert.assertNotEquals(actual, expected, desc);
			Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "pass", description);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "fail", description);
			flag = false;
		}
		return flag;
	}

	/**
	 * This method asserts expected and actual value and returns boolean 
	 * @param actual
	 * @param expected
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 14/11/2022
	 */
	protected boolean assertExpectedActualValue(boolean actual, boolean expected, String desc) throws Exception {
		boolean flag = false;
		try {
			Assert.assertEquals(actual, expected, desc);
			Helper.INSTANCE.logEventToReportForTestSteps(driver, "pass", expected, actual, desc);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReportForTestSteps(driver, "fail", expected, actual, desc);
			flag = false;
		}
		return flag;
	}

	/**
	 * This method is used to extract all element's text having common xpath
	 * 
	 * @param elements
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 14/11/2022
	 */
	protected List<String> getMultipleWebElementText(List<WebElement> elements) throws Exception {
		List<String> list = new ArrayList<String>();
		try {
			for (WebElement ele : elements) {
				String text = ele.getText().trim();
				list.add(text);
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "Error", e.getMessage());
			throw new Exception("Unable to retreive value " + e);
		}
		return list;
	}

	/**
	 * This method performs mouse over on the element using Action class 
	 * @param WebElement
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 14/12/2022
	 */
	protected boolean mouseOver(WebElement element, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			action.moveToElement(element).build().perform();
			Helper.INSTANCE.logEventInfoToReport(driver, "pass", desc);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method verified number of tabs
	 * 
	 * @param WebElement
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 16/12/2022
	 */
	protected boolean verifyNoOfTabs(int timeout, int tabs) throws Exception {
		boolean flag = false;
		try {
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.numberOfWindowsToBe(tabs));
			;
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * This method performs based on the value set accept/dismiss to browser based alert
	 * @param element
	 * @param text
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 16/12/2022
	 */
	protected boolean alertHandling(WebElement element, String text) throws Exception {
		boolean flag = false;
		try {
			element.click();
			wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			if (text.equalsIgnoreCase("accept")) {
				alert.accept();
				Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Accepted");
				flag = true;
			} else if (text.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
				Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
		}
		return flag;
	}

	/**
	 * This method performs mouse over on webElement1 and click on webElement2
	 * @param WebElement1
	 * @param WebElement2
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean mouseOverandClick(WebElement element1, WebElement element2, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element1);
			action.moveToElement(element1).build().perform();
			waitForElementVisible(element2);
			wait.until(ExpectedConditions.elementToBeClickable(element2));
			Thread.sleep(4000);
			element2.click();
			Allure.step(desc);
			flag = true;
		} catch (ElementNotInteractableException e) {
			Assert.fail(desc);
		} catch (Exception e) {
			Assert.fail(desc);
		}
		return flag;
	}

	/**
	 * This method is used to select the value from drop down by value 
	 * @param element
	 * @param value
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean selectByValue(WebElement element, String value) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByValue(value);
			Helper.INSTANCE.logEventToReport(driver, "pass", element, "Drop Down Value: " + value + "Selected");
			waitFor("4");
			flag = true;
		} catch (NoSuchElementException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		} finally {
			// Thread.sleep(6000);
		}
		return flag;
	}

	/**
	 * This method returns list of value from CSV file for given column number
	 * @param filePath
	 * @param columnNo
	 * @return List<String>
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static List<String> readCSVFile(String filePath, int columnNo) {
		csvData = new ArrayList<>();
		try {
			FileReader filereader = new FileReader(new File(pwd + "/Assets/" + filePath));
			CSVReader csvReader = new CSVReaderBuilder(filereader).build();
			List<String[]> allData = csvReader.readAll();
			for (String[] row : allData) {
				for (int i = 0; i < row.length; i++) {
					if (i == columnNo && !row[i].equalsIgnoreCase("ISSUE"))
						csvData.add(row[i].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csvData;
	}

	/**
	 * This method performs click the element by move to element of Action class and click on element2
	 * @param element1
	 * @param element2
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean mouseOverandClick(WebElement element1, WebElement element2) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element1);
			action.moveToElement(element1).build().perform();
			waitForElementVisible(element2);
			wait.until(ExpectedConditions.elementToBeClickable(element2));
			Thread.sleep(4000);
			element2.click();
			Helper.INSTANCE.logEventToReport(driver, "pass", element1, "Mouse Over and Click Successful");
			flag = true;
		} catch (ElementNotInteractableException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element1, e.getMessage());
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element1, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method is used to check element is selected or not 
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 * 
	 */
	protected boolean isElementSelected(WebElement element) throws Exception {
		try {
			return element.isSelected();
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
			throw new Exception("Unable to determine if the element is present.", e);
		}
	}

	/**
	 * This method is used to perform the action by key Ex: TAB, ENTER
	 * @param element
	 * @param key
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean triggerKeyEvent(WebElement element, Keys key) throws Exception {
		boolean flag = true;
		try {
			action.moveToElement(element).sendKeys(key).build().perform();
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method enters text on specified WebElement on webpage
	 * @param element
	 * @param text
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean typeOnElement(WebElement element, String text, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			action.moveToElement(element).build().perform();// Focus on element
			element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE) + text);
			Allure.step(desc + " : " + text);
			flag = true;
		} catch (Exception e) {
			error = desc + " expected true but found false";
			Helper.INSTANCE.setErrorMessage(Helper.INSTANCE.getCurrentTestCaseId(), error);
			Assert.fail(error);
		}
		return flag;
	}

	/**
	 * This method is used to select the value from drop down by visible text 
	 * @param element
	 * @param value
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean selectByVisibleText(WebElement element, String value) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			// waitFor("5");
			Helper.INSTANCE.logEventToReport(driver, "pass", element, value + " Drop Down Value Selected");
			flag = true;
		} catch (NoSuchElementException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "fail", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method gets Text of specified WebElement in String format for specified field
	 * @param Element
	 * @param Field
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected String getTextFromElement(WebElement element, String Field) throws Exception {
		String text = "";
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			text = element.getText();
			Allure.step(Field + " : " + text);
		} catch (Exception e) {
			Assert.fail(Field + " : " + text);
		}
		return text;
	}
	
	/**
	 * This method gets Title of specified WebPage in String format for specified field
	 * @param Element
	 * @param Field
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public String getTitleFromWebPage() throws Exception {
		String title = "";
		try {
			 title=WebDriverManager.getDriver().getTitle();
			Allure.step("Fetching title from web page : " + title);
		} catch (Exception e) {
			Allure.step("Error while fetching title from web page : " + title);
		}
		return title;
	}

	/**
	 * This method is used to select the value from drop down by index  
	 * @param element
	 * @param index
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean selectByIndex(WebElement element, int index) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByIndex(index);
			// waitFor("5");
			Helper.INSTANCE.logEventToReport(driver, "pass", element, "Drop Down Value Selected");
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method is used to extract the text from element using WebDriver's method
	 * @param element
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected String getTextFromElement(WebElement element) throws Exception {
		String text = "";
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			text = element.getText();
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return text;
	}

	/**
	 * This method is used to extract element's text using JavaScript Executor
	 * @param element
	 * @return STring
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected String getInputValue(WebElement element) throws Exception {
		try {
			waitForElementVisible(element);
			return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", element);
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "Fail", element, e.getMessage());
			throw new Exception("Unable to retreive value " + e);
		}
	}

	/**
	 * This method return value of given attribute
	 * 
	 * @param element
	 * @param attributeValue
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected String getAttributeFromElement(WebElement element, String attributeValue) throws Exception {
		String text = "";
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
			text = element.getAttribute(attributeValue);
			Helper.INSTANCE.logEventInfoToReport(driver, "Attribute", text);
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return text;
	}

	/**
	 * This method asserts expected and actual value
	 * @param actual
	 * @param expected
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean assertExpectedActualValue(String actual, String expected, String desc) throws Exception {
		boolean flag = false;
		try {
			Assert.assertEquals(actual.trim(), expected.trim(), desc);
			Helper.INSTANCE.logEventToReportForTestSteps(driver, "pass", expected, actual, desc);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReportForTestSteps(driver, "fail", expected, actual, desc);
			flag = false;
		}
		return flag;
	}

	/**
	 * This method handles browser based alert by accept/dismiss after clicking
	 * @param element
	 * @param text
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean alertHandlingPresentORNot(WebElement element, String text) throws Exception {
		boolean flag = false;
		try {
			System.out.println("alert clicking !!!");
			element.click();
			wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			wait.ignoring(UnhandledAlertException.class).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			if (text.equalsIgnoreCase("accept")) {
				alert.accept();
				Helper.INSTANCE.logEventToReport(driver, "Pass", element, alert.getText() + "Alert Accepted");
				flag = true;
			} else if (text.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
				Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
		}
		return flag;
	}

	/**
	 * This method returns the split value for given regular expression
	 * @param splitBy
	 * @param value
	 * @return String[]
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String[] splitString(String splitBy, String value) {
		String array[] = value.split(splitBy);
		return array;
	}

	/**
	 * This method performs click operations by JavascriptExecutor
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean clickOnElementJs(WebElement element) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			if (waitForElementVisible(element)) {
				WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
				js.executeScript("arguments[0].click();", element);
				Helper.INSTANCE.logEventToReport(driver, "pass", element, "ClickonElementJs");
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
		}
		return flag;
	}

	/**
	 * It is used to wait until the file uploaded is completed Ex: PDF, CSV
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean waitTillUploadComplete(String element, String desc) throws Exception {
		boolean flag = false;
		try {
			otherWait = new WebDriverWait(driver, 150);
			otherWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
			Allure.step(desc);
			flag = true;
		} catch (Exception e) {
			Assert.fail(desc);
		}
		return flag;
	}

	/**
	 * This method performs select a value from drop down
	 * @param drpObject
	 * @param value
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean selectValueFromDropdown(List<WebElement> drpObject, String value, String desc) throws Exception {
		boolean result = false;
		try {
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOfAllElements(drpObject));
			for (WebElement webElement : drpObject) {
				if (webElement.getText().equalsIgnoreCase(value)) {
					clickOnElement(webElement, desc);
					Thread.sleep(5000);
					Allure.step(desc);
					result = true;
				}
			}
		} catch (Exception e) {
			Assert.fail(desc);
		}
		return result;
	}

	/**
	 * This method performs copy the file into manualdrop folder
	 * @param source
	 * @param destination
	 * @param option
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static boolean manualDrop(String source, String destination, String option) throws Exception {
		boolean flag = false;
		try {
			if (option.equalsIgnoreCase("multiple")) {
				FileUtils.copyDirectory(new File(source), new File(destination));
				Helper.INSTANCE.logEventInfoToReportForTestSteps(WebDriverManager.getDriver(), "pass",
						"Files has been dropped into Manualdrop folder");
				flag = true;
			} else if (option.equalsIgnoreCase("single")) {
				FileUtils.copyFileToDirectory(new File(source), new File(destination));
				Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "pass",
						"File has been dropped into Manualdrop folder" + " [Source]: " + source + " [Destination]: "
								+ destination);
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "error", "Manualdrop", e.getMessage());
		}
		return flag;
	}

	/**
	 * This method used to check file is present or not in specific folder
	 * @param filePath
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean isFilePresent(String filePath) throws Exception {
		boolean flag = false;
		File file = new File(filePath);
		try {
			waitingForFile(filePath);
			if (file.exists() && (filePath.length() > 1)) {
				waitFor("60");
				Helper.INSTANCE.logEventToReport(driver, "pass", filePath, "Found");
				flag = true;
			} else if (!file.exists() && (filePath.length() == 0)) {
				waitFor("60");
				Helper.INSTANCE.logEventToReport(driver, "pass", filePath, "Passed with wait");
				flag = true;
			} else {
				waitFor("60");
				Helper.INSTANCE.logEventToReport(driver, "fail", filePath, "Not Found");
			}
		} catch (FileNotFoundException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", filePath, e.getMessage());
		}
		return flag;
	}

	/**
	 * This method used to wait for File with extension zip or xml
	 * @param filePath
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected void waitingForFile(String filePath) throws Exception {

		File currentFile = new File(filePath);
		try {
			if (currentFile.getPath().endsWith("ZIP") || currentFile.getPath().endsWith("zip")) {
				waitFor("30");
				Helper.INSTANCE.logEventInfoToReport(driver, "Waiting for file", "File extenstion is zip");

			}
			if (currentFile.getPath().endsWith("XML") || currentFile.getPath().endsWith("xml")) {
				waitFor("60");
				Helper.INSTANCE.logEventInfoToReport(driver, "Waiting for file", "File extenstion is xml");

			} else {
				Helper.INSTANCE.logEventInfoToReport(driver, "Waiting for file", "File extenstion is not zip/xml");
			}
		} catch (FileNotFoundException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", "Waiting for file : " + filePath, e.getMessage());
		}
	}

	/**
	 * This method is used to select the value from drop down by visible text
	 * @param element
	 * @param value
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date 7/12/2022	 
	 */
	protected boolean selectByVisibleText(WebElement element, String value, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			// waitFor("5");
			Allure.step(desc);
			flag = true;
		} catch (NoSuchElementException e) {
			Assert.fail(desc);
		} catch (Exception e) {
			Assert.fail(desc);
		}
		return flag;
	}

	/**
	 * This methods used to click on the element on webpage by javascript executor
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean clickOnElementJs(WebElement element, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			if (waitForElementVisible(element)) {
				WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
				js.executeScript("arguments[0].click();", element);
				Allure.step(desc);
				flag = true;
			}
		} catch (Exception e) {
			Assert.fail(desc);
		}
		return flag;
	}

	/**
	 * This method filters csv data based on position
	 * @param csvAllData
	 * @param position
	 * @param status
	 * @return List<String>
	 * @author Rakesh.Shevale
	 * @Created Date : 10 Aug 2022
	 */
	public static List<String> filterCsvData(List<String[]> csvAllData, int position, String status) {
		List<String> csvListValue = new ArrayList<>();
		StringBuilder csvvalue = null;
		try {
			for (String[] row : csvAllData) {
				csvvalue = new StringBuilder();
				for (int i = 0; i < row.length; i++) {
					if (status.equals("single") && i == position) {
						csvvalue.append(row[i]);
					} else if (!row[i].isEmpty() && !status.equals("single") && i < position) // Remove Empty and
																								// multiple values
					{
						if (i == 0) {
							csvvalue.append(row[i]);
						} else {
							csvvalue.append(", ");
							csvvalue.append(row[i]);
						}
					}
				}
				csvListValue.add(csvvalue.toString());
			}
			System.out.println("====csvData=====" + csvData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(csvData);
		return csvListValue;
	}

	/**
	 * This method reads csv data based on filepath and type
	 * @param filePath
	 * @param type
	 * @return List<String>
	 * @author Rakesh.Shevale
	 * @Created Date : 10 Aug 2022
	 */
	public static List<String[]> readCSVFile(String filePath, String type) {
		// Object[] csvRow = new Object[1];
		List<String[]> csvRow = new ArrayList<>();
		try {
			FileReader filereader = new FileReader(new File(filePath));
			CSVReader csvReader = new CSVReaderBuilder(filereader).build();
			List<String[]> allData = csvReader.readAll();
			if (type.equals("with"))
				allData.remove(0);
			for (String[] row : allData) {
				csvRow.add(row);
			}
		} catch (Exception e) {
			Assert.fail("Unable to read csv File in FilePath: " + filePath);
		}
		System.out.println(csvRow);
		return csvRow;
	}

	/**
	 * This method asserts expected and actual value
	 * @param element
	 * @param expected
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean assertExpectedActualValue(WebElement element, String expected, String desc) throws Exception {
		boolean flag = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));

			actual = element.getText().trim();
			Assert.assertEquals(actual, expected, desc);
			Helper.INSTANCE.logEventToReportForTestSteps(driver, "pass", expected, actual, desc);
			flag = true;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReportForTestSteps(driver, "fail", expected, actual, desc);
			flag = false;
		}
		return flag;
	}

	/**
	 * This method handles browser based alert by accept/dismiss after clicking
	 * @param element
	 * @param text
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean alertHandlingAcceptorDismiss(WebElement element, String text, String desc) throws Exception {
		boolean flag = false;
		try {
			System.out.println("alert clicking !!!");
			clickOnElement(element, desc);
			wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
			wait.ignoring(UnhandledAlertException.class).until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			if (text.equalsIgnoreCase("accept")) {
				alert.accept();
				// Helper.INSTANCE.logEventToReport(driver, "Pass", element,
				// alert.getText()+"Alert Accepted");
				flag = true;
			} else if (text.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
				Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
				flag = true;
			}
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
		}
		return flag;
	}

	/**
	 * This method performs to wait until the element visible in DOM of the page
	 * @param locator
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public boolean waitForElementVisible(By locator, String desc) throws Exception {
		boolean elementPresent = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
			elementPresent = true;
		} catch (Exception e) {
			error = desc + " expected true but found false";
			Helper.INSTANCE.setErrorMessage(Helper.INSTANCE.getCurrentTestCaseId(), error);
			Assert.fail(error);
		}
		return elementPresent;
	}

	/**
	 * This method performs wait until the element is invisible on the DOM
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public boolean waitForInVisibleElementByXpath(String element, String desc) throws Exception {
		boolean elementPresent = false;
		try {
			waitForDocumentReady();
			WebDriverManager.getWebdriverWait()
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
			new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class)
					.ignoring(NoSuchElementException.class);
			elementPresent = true;
		} catch (Exception e) {
			Assert.fail(desc);
		} finally {
			Thread.sleep(2000);
		}
		return elementPresent;
	}

	/**
	 * This method clicks on WebElement on webpage
	 * @param WebElement
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected void clickOnElementNew(WebElement element, String desc) throws Exception {
		waitForDocumentReady();
		if (waitForElementVisible(element)) {
			WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			Allure.step(desc);
		}
	}

	/**
	 * This method performs verify the presence of element
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementNotPresent(WebElement element, String desc) throws Exception {
		boolean flag = false;
		try {
			Helper.INSTANCE.logEventToReport(driver, "pass", desc);
			flag = !element.isDisplayed();
		} catch (Exception e) {
			flag = true;
		}
		return flag;
	}

	/**
	 * This method used to fetch the latest download file
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	public String fetchLatestDownloadFile() throws InterruptedException {
		Thread.sleep(5000);
		String newfile = "";
		try {
			File downloadedDir = new File(System.getProperty("user.dir") + File.separator + "target\\Assets");
			File[] listofFiles = downloadedDir.listFiles();
			if (listofFiles == null || listofFiles.length == 0) {
				return "";
			}
			File LatestModifiedFile = listofFiles[0];
			for (int i = 1; i < listofFiles.length; i++) {
				if (LatestModifiedFile.lastModified() < listofFiles[i].lastModified()) {
					LatestModifiedFile = listofFiles[i];
				}
			}
			newfile = LatestModifiedFile.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newfile;
	}

	/**
	 * This method return text list from WebElement list
	 * @param locator
	 * @return List<String>
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	protected List<String> getTextFindElements(By locator) {
		List<String> list = new ArrayList();
		List<WebElement> elements = driver.findElements(locator);
		for (WebElement ele : elements) {
			list.add(ele.getText());
		}
		return list;
	}
	
	/**
	 * This method return tootip text from the button
	 * @param locator
	 * @param attributeName
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 10/07/2023
	 */
	protected String getToolTipText(By locator, String attributeName) {
		String toolTipText = driver.findElement(locator).getAttribute(attributeName);
		return toolTipText;
	}

	/**
	 * This method used for dynamic object creation of pages
	 * @param driver
	 * @param class<T> pageClass
	 * @return pageInstance
	 * @author Rakesh.Shevale
	 * @Created Date : 17/07/2023
	 */
	public static <T extends BasePage> T initialize(WebDriver driver, Class<T> pageClass) {
		try {
			T pageInstance = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
			return pageInstance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	protected String getDefaultDropDownValue(WebElement element) throws Exception {
		try {
			waitForDocumentReady();
			waitForElementVisible(element);
			Select select = new Select(element);
			Helper.INSTANCE.logEventToReport(driver, "pass", element, "Default value is Selected");
			return select.getFirstSelectedOption().getText();
		} catch (NoSuchElementException e) {
			Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
			return null;
		} catch (Exception e) {
			Helper.INSTANCE.logEventToReport(driver, "fail", element, e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method performs to wait until the element visible in DOM of the page 
	 * @param element
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public boolean waitForElementVisibleLimitedWait(WebElement element,int timeOut) throws Exception {
		boolean elementPresent = false;
		try {
			waitForDocumentReady();
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.ignoring(StaleElementReferenceException.class)
					.ignoring(NoSuchElementException.class);
			wait.ignoring(InvalidElementStateException.class)
					.ignoring(NoSuchElementException.class);
			elementPresent = true;
		} catch (Exception e) {
			elementPresent=false;
		}
		return elementPresent;
	}
	
	/**
	 * This method performs verify the presence of element on webpage
	 * 
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementNotPresentWithTimeOut(WebElement element, int timeOut,String desc) throws Exception {
		boolean flag = false;
		try {
			waitForElementVisibleLimitedWait(element,timeOut);
			element.isDisplayed();
			Helper.INSTANCE.logEventToReport(driver, "pass", desc);
			flag = false;
		} catch (Exception e) {
			flag = true;
		}
		return flag;
	}
	

	/**
	 * This method performs verify the presence of element on webpage
	 * 
	 * @param element
	 * @param desc
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/12/2022
	 */
	protected boolean isElementPresentWithTimeOut(WebElement element, int timeOut,String desc) throws Exception {
		boolean flag = false;
		try {
			waitForElementVisibleLimitedWait(element,timeOut);
			element.isDisplayed();
			Helper.INSTANCE.logEventToReport(driver, "pass", desc);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public List<String> splitStringAndRetunsList(List<String>list) {
		List<String> list1 = new ArrayList<>();
		for (String a : list) {
			list1.add(a.substring(11, a.length()));
		}
		return list1;
	}
	
	public String splitStringandReturnString(String str) {
		
			return (str.substring(11, str.length()));
	}
	
	
	
	
	
	
=======
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait otherWait;
    private Actions action;
    private String actual;
    private String error;
    public static List<String> csvData = null;
    public static String uploadCompleteStatus = "//*[@class='uploadstatus' and text()=' (Uploaded)']";
    public static String loadComplete = "//div[@id='divInprogressBackgroung' and contains(@style,'display: none')]";
    public static String pwd = System.getProperty("user.dir");
    public static String prodNumber = null;

    public BasePage(WebDriver driver) throws Exception {
        this.driver = driver;
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        if (WebDriverManager.getWebdriverWait() == null) {
            WebDriverManager.setWebdriverWait(wait);
        }
    }

    public BasePage() {}

    /**
     * This method return the random wait
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public static String randomWait() {
        int random = 0;
        try {
            Random r = new Random();
            random = r.nextInt((10 - 1) + 1) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(random);
    }

    /**
     * This method enters text/testData in text box field
     * @param WebElement
     * @param text
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean typeOnElement(WebElement element, String text) throws Exception {
        boolean flag = false;
        try {
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            action.moveToElement(element).build().perform();// Focus on element
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE) + text);
            Helper.INSTANCE.logEventToReport(driver, "pass", element, "TypeOnElement  -  " + text);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method performs select a value from drop down 
     * @param drpObject
     * @param value
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean selectValueFromDropdown(List<WebElement> drpObject, String value) throws Exception {
        boolean result = false;
        try {
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOfAllElements(drpObject));
            for (WebElement webElement : drpObject) {
                if (webElement.getText().equalsIgnoreCase(value)) {
                    result = clickOnElement(webElement);
                    Thread.sleep(5000);
                    Helper.INSTANCE.logEventToReport(driver, "Pass", value, "Selected In Drop Down");
                    result = true;
                }
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", value, e.getMessage());
        }
        return result;
    }

    /**
     * This methods used to click on the element on webpage
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean clickOnElement(WebElement element) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            if (waitForElementVisible(element)) {
                WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                Helper.INSTANCE.logEventToReport(driver, "pass", element, "ClickOnElement");
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage() + Thread.currentThread().getId());
        }
        return flag;
    }

    /**
     * This method performs to wait until the element visible in DOM of the page 
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public boolean waitForElementVisible(WebElement element) throws Exception {
        boolean elementPresent = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            elementPresent = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "Error", element, e.getMessage());
        }
        return elementPresent;
    }

    /**
     * This method performs until the element is loaded with given seconds
     * @param element
     * @param max_Time
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean waitForElementLoadWithOutLog(String element, int max_Time) throws Exception {
        boolean flag = false;
        try {
            otherWait = new WebDriverWait(driver, max_Time);
            otherWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            Helper.INSTANCE.logEventToReport(driver, "pass", element, "visibled");
            flag = true;
        } catch (Exception e) {
        }
        return flag;
    }

    /**
     * This method navigates the specific URL
     * @param url
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean navigate(String url) throws Exception {
        boolean flag = false;
        url = url.replaceAll("<ENV>", BaseTest.properties.getProperty("Environment"));
        try {
            driver.navigate().to(url);
            Allure.step("Navigated to " + url);
            flag = true;
        } catch (Exception e) {
            Assert.fail("Error while navigating to url: " + url);
        }
        return flag;
    }

    /**
     * This method used to click on WebElement on webpage
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean clickOnElement(WebElement element, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementPresenceSpecified(element, 120);
            waitForElementVisible(element);
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Allure.step(desc);
            flag = true;
        } catch (Exception e) {
            error = desc + " expected true but found false";
            Helper.INSTANCE.setErrorMessage(Helper.INSTANCE.getCurrentTestCaseId(), error);
            Assert.fail(error);
        }
        return flag;
    }

    /**
     * This method is used to upload the file in portal Ex: PDF, CSV
     * @param exePath
     * @param fileName
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean uploadFile(String exePath, String fileName, WebElement element) throws Exception {
        boolean flag = false;
        try {
            assertEquals(uploadUsingThread(element, fileName), true);
            flag = true;

        } catch (AssertionError | Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }

        return flag;
    }

    /**
     * This method performs file upload to corresponding browser thread
     * @param element
     * @param filePath
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    @SuppressWarnings("deprecation")
    protected boolean uploadUsingThread(WebElement element, String filePath) throws Exception {
        boolean flag = false;
        try {
            waitForElementVisible(element);
            Threadmanager t1 = new Threadmanager(WebDriverManager.getFileUploadObject(), element, filePath, driver);
            t1.start();
            t1.join();
            t1.stop();

            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Issue in uploading file");
        }
        return flag;

    }

    /**
     * This method performs to wait until the element visible in DOM of the page using xpath locator and return the WebElement
     * @param xpathExpression
     * @return WebElement
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected WebElement waitAndReturnElementPresent(String xpathExpression) throws Exception {
        WebElement element = null;
        try {
            System.out.println("Before wait time :   " + new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()));
            element = WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            //			Helper.INSTANCE.logEventToReport(driver, "Pass", xpathExpression, "Found And Returned");
            //			System.out.println("After success wait time :   "
            //					+ new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()));

        } catch (StaleElementReferenceException ste) {
            waitAndReturnElementPresent(xpathExpression);
        } catch (Exception e) {
            Assert.fail("XPATH => " + xpathExpression + " Not Found ");
            //			System.out.println(
            //					"After wait time :   " + new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime()));
        }
        return element;
    }

    /**
     * This method use to select dropdown value based on value provided in argument
     * @param element
     * @param value
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean selectByValue(WebElement element, String value, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForElementVisible(element);
            Select select = new Select(element);
            select.selectByValue(value);
            Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "pass", desc + " : " + value);
            flag = true;
        } catch (NoSuchElementException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        } finally {
            // Thread.sleep(6000);
        }
        return flag;
    }

    /**
     * This method performs verify the presence of element
     * @param List<WebElement>
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementPresent(List<WebElement> element) throws Exception {
        boolean flag = false;
        if (element.size() > 0) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    protected boolean isElementNotPresent(List<WebElement> element) throws Exception {
        boolean flag = true;
        if (element.size() > 0) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * This method performs verify the presents of element on webpage
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementPresentWithoutLog(WebElement element) throws Exception {
        boolean flag = false;
        try {
            waitForElementVisible(element);
            element.isDisplayed();
            flag = true;
        } catch (Exception e) {
        }
        return flag;
    }

    /**
     * It is used to wait until the file uploaded is completed Ex: PDF, CSV
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean waitTillUploadComplete(String element) throws Exception {
        boolean flag = false;
        try {
            otherWait = new WebDriverWait(driver, 150);
            otherWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
            Helper.INSTANCE.logEventToReport(driver, "pass", "File", "Uploaded");
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method performs wait until the element loading with specified time in seconds
     * @param element
     * @param max_Time
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean waitForElementLoad(String element, int max_Time) throws Exception {
        boolean flag = false;
        try {
            otherWait = new WebDriverWait(driver, max_Time);
            otherWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method is used to wait until the element is invisible on the DOM
     * 
     * @param element
     * @param timeOut
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public boolean waitForVisibleAndInVisibleElement(WebElement element, int timeOut) throws Exception {
        boolean elementPresent = false;
        try {
            // waitForDocumentReady();
            new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
            new WebDriverWait(driver, timeOut).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            new WebDriverWait(driver, timeOut).ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            elementPresent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementPresent;
    }

    /**
     * This method performs wail until the element is invisible on the DOM
     * 
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public boolean waitForInVisibleElementByXpath(String element) throws Exception {
        boolean elementPresent = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
            new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            Helper.INSTANCE.logEventToReport(driver, "Pass", element, "InVisible & Stable");
            elementPresent = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "Error", element, e.getMessage() + Thread.currentThread().getId());
        } finally {
            Thread.sleep(2000);
        }
        return elementPresent;
    }

    /**
     * This method is used to wait for specified number of milliseconds
     * @param inseconds
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected void waitFor(String inseconds) {
        try {
            Thread.sleep(Integer.parseInt(inseconds + "000"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method performs type operations by JavascriptExecutor
     * @param element
     * @param text
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean typeOnElementJs(WebElement element, String text) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            if (waitForElementVisible(element)) {
                WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
                JavascriptExecutor js = ((JavascriptExecutor) driver);
                action.moveToElement(element).build().perform();// Focus on element
                js.executeScript("arguments[0].value='';", element);
                js.executeScript("arguments[0].value='" + text + "';", element);
                Helper.INSTANCE.logEventToReport(driver, "pass", element, "TypeOnElementByJS  -  " + text);
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method wait for page load by Javascript Executor
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean waitForDocumentReady() throws Exception {
        boolean result = false;
        try {
            if (ConfigurationManager.getSleepMode()) {
                Thread.sleep(ConfigurationManager.getSleepTimeout());
            }
            result = WebDriverManager.getWebdriverWait().until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete")
                            && Boolean.valueOf((boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.isReady")).equals(true);
                }
            });
        } catch (Exception e) {
            if (e.getMessage().contains("jQuery is not defined")) {
                WebDriverManager.getWebdriverWait().until(new Function<WebDriver, Boolean>() {

                    public Boolean apply(WebDriver driver) {
                        return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
                    }

                });
            } else {
                Thread.sleep(10000);
                // throw new Exception("Unable to complete the loading page !", e);
            }
        }
        return result;
    }

    /**
     * This method handles browser based alert by accept/dismiss after clicking
     * @param element
     * @param text
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean alertHandlingAcceptorDismiss(WebElement element, String text) throws Exception {
        boolean flag = false;
        try {
            System.out.println("alert clicking !!!");
            element.click();
            wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
            wait.ignoring(UnhandledAlertException.class).until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (text.equalsIgnoreCase("accept")) {
                alert.accept();
                // Helper.INSTANCE.logEventToReport(driver, "Pass", element,
                // alert.getText()+"Alert Accepted");
                flag = true;
            } else if (text.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
                Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
        }
        return flag;
    }

    /**
     * This method clicks on button, handles popup window without scrolling to element
     * @param element
     * @param text
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022
     */
    protected boolean clickOnElementWithoutScrolling(WebElement element, String text, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            if (waitForElementVisible(element)) {
                WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                if (text.equalsIgnoreCase("accept")) {
                    alert.accept();
                    Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Accepted");
                    flag = true;
                } else if (text.equalsIgnoreCase("dismiss")) {
                    alert.dismiss();
                    Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
                    flag = true;
                }
                Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "pass", desc);
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage() + Thread.currentThread().getId());
        }
        return flag;
    }

    /**
     * This method is used to select the value from drop down by visible text and
     * @param element
     * @param value
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022	 
     */
    protected boolean selectDropdownValueByVisibleText(WebElement element, String value, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            Select select = new Select(element);
            select.selectByVisibleText(value);
            flag = true;
        } catch (NoSuchElementException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "fail", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to clear textbox value 
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public boolean clearTextBox(WebElement element, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            element.clear();
            Helper.INSTANCE.logEventToReport(driver, "pass", desc);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method performs verify the presence of element on webpage
     * 
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementPresent(WebElement element, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForElementVisible(element);
            element.isDisplayed();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * This method performs verify the presence of elements on webpage
     * @param List<WebElement>
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementPresent(List<WebElement> element, String desc) throws Exception {
        boolean flag = false;
        try {
            if (element.size() > 0) {
                flag = true;
            } else {
                flag = false;
            }
            Helper.INSTANCE.logEventToReport(driver, "pass", desc);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
            throw new Exception("Unable to determine if the element is present.", e);
        }
        return flag;
    }

    /**
     * This method asserts expected and actual value and returns boolean 
     * @param expected
     * @param actual
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    protected boolean assertExpectedActualValuesNotMatching(String expected, String actual, String desc) throws Exception {
        boolean flag = false;
        String description = desc + " : Expected Result--> " + expected + "||" + "Actual Result-->" + actual;
        try {
            Assert.assertNotEquals(actual, expected, desc);
            Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "pass", description);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventInfoToReportForTestSteps(driver, "fail", description);
            flag = false;
        }
        return flag;
    }

    /**
     * This method asserts expected and actual value and returns boolean 
     * @param actual
     * @param expected
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/11/2022
     */
    protected boolean assertExpectedActualValue(boolean actual, boolean expected, String desc) throws Exception {
        boolean flag = false;
        try {
            Assert.assertEquals(actual, expected, desc);
            Helper.INSTANCE.logEventToReportForTestSteps(driver, "pass", expected, actual, desc);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReportForTestSteps(driver, "fail", expected, actual, desc);
            flag = false;
        }
        return flag;
    }

    /**
     * This method is used to extract all element's text having common xpath
     * 
     * @param elements
     * @return List<String>
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/11/2022
     */
    protected List<String> getMultipleWebElementText(List<WebElement> elements) throws Exception {
        List<String> list = new ArrayList<String>();
        try {
            for (WebElement ele : elements) {
                String text = ele.getText().trim();
                list.add(text);
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "Error", e.getMessage());
            throw new Exception("Unable to retreive value " + e);
        }
        return list;
    }

    /**
     * This method performs mouse over on the element using Action class 
     * @param WebElement
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/12/2022
     */
    protected boolean mouseOver(WebElement element, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            action.moveToElement(element).build().perform();
            Helper.INSTANCE.logEventInfoToReport(driver, "pass", desc);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method verified number of tabs
     * 
     * @param WebElement
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/12/2022
     */
    protected boolean verifyNoOfTabs(int timeout, int tabs) throws Exception {
        boolean flag = false;
        try {
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.numberOfWindowsToBe(tabs));;
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method performs based on the value set accept/dismiss to browser based alert
     * @param element
     * @param text
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/12/2022
     */
    protected boolean alertHandling(WebElement element, String text) throws Exception {
        boolean flag = false;
        try {
            element.click();
            wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (text.equalsIgnoreCase("accept")) {
                alert.accept();
                Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Accepted");
                flag = true;
            } else if (text.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
                Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
        }
        return flag;
    }

    /**
     * This method performs mouse over on webElement1 and click on webElement2
     * @param WebElement1
     * @param WebElement2
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean mouseOverandClick(WebElement element1, WebElement element2, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element1);
            action.moveToElement(element1).build().perform();
            waitForElementVisible(element2);
            wait.until(ExpectedConditions.elementToBeClickable(element2));
            Thread.sleep(4000);
            element2.click();
            Allure.step(desc);
            flag = true;
        } catch (ElementNotInteractableException e) {
            Assert.fail(desc);
        } catch (Exception e) {
            Assert.fail(desc);
        }
        return flag;
    }

    /**
     * This method is used to select the value from drop down by value 
     * @param element
     * @param value
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean selectByValue(WebElement element, String value) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            Select select = new Select(element);
            select.selectByValue(value);
            Helper.INSTANCE.logEventToReport(driver, "pass", element, "Drop Down Value: " + value + "Selected");
            waitFor("4");
            flag = true;
        } catch (NoSuchElementException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        } finally {
            // Thread.sleep(6000);
        }
        return flag;
    }

    /**
     * This method returns list of value from CSV file for given column number
     * @param filePath
     * @param columnNo
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public static List<String> readCSVFile(String filePath, int columnNo) {
        csvData = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(new File(pwd + "/Assets/" + filePath));
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                for (int i = 0; i < row.length; i++) {
                    if (i == columnNo && !row[i].equalsIgnoreCase("ISSUE"))
                        csvData.add(row[i].toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvData;
    }

    /**
     * This method performs click the element by move to element of Action class and click on element2
     * @param element1
     * @param element2
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean mouseOverandClick(WebElement element1, WebElement element2) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element1);
            action.moveToElement(element1).build().perform();
            waitForElementVisible(element2);
            wait.until(ExpectedConditions.elementToBeClickable(element2));
            Thread.sleep(4000);
            element2.click();
            Helper.INSTANCE.logEventToReport(driver, "pass", element1, "Mouse Over and Click Successful");
            flag = true;
        } catch (ElementNotInteractableException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element1, e.getMessage());
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element1, e.getMessage());
        }
        return flag;
    }

    /**
     * This method is used to check element is selected or not 
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     * 
     */
    protected boolean isElementSelected(WebElement element) throws Exception {
        try {
            return element.isSelected();
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
            throw new Exception("Unable to determine if the element is present.", e);
        }
    }

    /**
     * This method is used to perform the action by key Ex: TAB, ENTER
     * @param element
     * @param key
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean triggerKeyEvent(WebElement element, Keys key) throws Exception {
        boolean flag = true;
        try {
            action.moveToElement(element).sendKeys(key).build().perform();
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method enters text on specified WebElement on webpage
     * @param element
     * @param text
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean typeOnElement(WebElement element, String text, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            action.moveToElement(element).build().perform();// Focus on element
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE) + text);
            Allure.step(desc + " : " + text);
            flag = true;
        } catch (Exception e) {
            error = desc + " expected true but found false";
            Helper.INSTANCE.setErrorMessage(Helper.INSTANCE.getCurrentTestCaseId(), error);
            Assert.fail(error);
        }
        return flag;
    }

    /**
     * This method is used to select the value from drop down by visible text 
     * @param element
     * @param value
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean selectByVisibleText(WebElement element, String value) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            Select select = new Select(element);
            select.selectByVisibleText(value);
            // waitFor("5");
            Helper.INSTANCE.logEventToReport(driver, "pass", element, value + " Drop Down Value Selected");
            flag = true;
        } catch (NoSuchElementException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "fail", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method gets Text of specified WebElement in String format for specified field
     * @param Element
     * @param Field
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected String getTextFromElement(WebElement element, String Field) throws Exception {
        String text = "";
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            text = element.getText();
            Allure.step(Field + " : " + text);
        } catch (Exception e) {
            Assert.fail(Field + " : " + text);
        }
        return text;
    }

    /**
     * This method gets Title of specified WebPage in String format for specified field
     * @param Element
     * @param Field
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public String getTitleFromWebPage() throws Exception {
        String title = "";
        try {
            title = WebDriverManager.getDriver().getTitle();
            Allure.step("Fetching title from web page : " + title);
        } catch (Exception e) {
            Allure.step("Error while fetching title from web page : " + title);
        }
        return title;
    }

    /**
     * This method is used to select the value from drop down by index  
     * @param element
     * @param index
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean selectByIndex(WebElement element, int index) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            Select select = new Select(element);
            select.selectByIndex(index);
            // waitFor("5");
            Helper.INSTANCE.logEventToReport(driver, "pass", element, "Drop Down Value Selected");
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * This method is used to extract the text from element using WebDriver's method
     * @param element
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected String getTextFromElement(WebElement element) throws Exception {
        String text = "";
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            text = element.getText();
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return text;
    }

    /**
     * This method is used to extract element's text using JavaScript Executor
     * @param element
     * @return STring
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected String getInputValue(WebElement element) throws Exception {
        try {
            waitForElementVisible(element);
            return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", element);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "Fail", element, e.getMessage());
            throw new Exception("Unable to retreive value " + e);
        }
    }

    /**
     * This method return value of given attribute
     * 
     * @param element
     * @param attributeValue
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected String getAttributeFromElement(WebElement element, String attributeValue) throws Exception {
        String text = "";
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));
            text = element.getAttribute(attributeValue);
            Helper.INSTANCE.logEventInfoToReport(driver, "Attribute", text);
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return text;
    }

    /**
     * This method asserts expected and actual value
     * @param actual
     * @param expected
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean assertExpectedActualValue(String actual, String expected, String desc) throws Exception {
        boolean flag = false;
        try {
            Assert.assertEquals(actual.trim(), expected.trim(), desc);
            Helper.INSTANCE.logEventToReportForTestSteps(driver, "pass", expected, actual, desc);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReportForTestSteps(driver, "fail", expected, actual, desc);
            flag = false;
        }
        return flag;
    }

    /**
     * This method handles browser based alert by accept/dismiss after clicking
     * @param element
     * @param text
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean alertHandlingPresentORNot(WebElement element, String text) throws Exception {
        boolean flag = false;
        try {
            System.out.println("alert clicking !!!");
            element.click();
            wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
            wait.ignoring(UnhandledAlertException.class).until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (text.equalsIgnoreCase("accept")) {
                alert.accept();
                Helper.INSTANCE.logEventToReport(driver, "Pass", element, alert.getText() + "Alert Accepted");
                flag = true;
            } else if (text.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
                Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
        }
        return flag;
    }

    /**
     * This method returns the split value for given regular expression
     * @param splitBy
     * @param value
     * @return String[]
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public static String[] splitString(String splitBy, String value) {
        String array[] = value.split(splitBy);
        return array;
    }

    /**
     * This method performs click operations by JavascriptExecutor
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean clickOnElementJs(WebElement element) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            if (waitForElementVisible(element)) {
                WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
                JavascriptExecutor js = ((JavascriptExecutor) driver);
                js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
                js.executeScript("arguments[0].click();", element);
                Helper.INSTANCE.logEventToReport(driver, "pass", element, "ClickonElementJs");
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
        }
        return flag;
    }

    /**
     * It is used to wait until the file uploaded is completed Ex: PDF, CSV
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean waitTillUploadComplete(String element, String desc) throws Exception {
        boolean flag = false;
        try {
            otherWait = new WebDriverWait(driver, 150);
            otherWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
            Allure.step(desc);
            flag = true;
        } catch (Exception e) {
            Assert.fail(desc);
        }
        return flag;
    }

    /**
     * This method performs select a value from drop down
     * @param drpObject
     * @param value
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean selectValueFromDropdown(List<WebElement> drpObject, String value, String desc) throws Exception {
        boolean result = false;
        try {
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOfAllElements(drpObject));
            for (WebElement webElement : drpObject) {
                if (webElement.getText().equalsIgnoreCase(value)) {
                    clickOnElement(webElement, desc);
                    Thread.sleep(5000);
                    Allure.step(desc);
                    result = true;
                }
            }
        } catch (Exception e) {
            Assert.fail(desc);
        }
        return result;
    }

    /**
     * This method performs copy the file into manualdrop folder
     * @param source
     * @param destination
     * @param option
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public static boolean manualDrop(String source, String destination, String option) throws Exception {
        boolean flag = false;
        try {
            if (option.equalsIgnoreCase("multiple")) {
                FileUtils.copyDirectory(new File(source), new File(destination));
                Helper.INSTANCE.logEventInfoToReportForTestSteps(WebDriverManager.getDriver(), "pass", "Files has been dropped into Manualdrop folder");
                flag = true;
            } else if (option.equalsIgnoreCase("single")) {
                FileUtils.copyFileToDirectory(new File(source), new File(destination));
                Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "pass", "File has been dropped into Manualdrop folder" + " [Source]: " + source + " [Destination]: " + destination);
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(WebDriverManager.getDriver(), "error", "Manualdrop", e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to check file is present or not in specific folder
     * @param filePath
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean isFilePresent(String filePath) throws Exception {
        boolean flag = false;
        File file = new File(filePath);
        try {
            waitingForFile(filePath);
            if (file.exists() && (filePath.length() > 1)) {
                waitFor("60");
                Helper.INSTANCE.logEventToReport(driver, "pass", filePath, "Found");
                flag = true;
            } else if (!file.exists() && (filePath.length() == 0)) {
                waitFor("60");
                Helper.INSTANCE.logEventToReport(driver, "pass", filePath, "Passed with wait");
                flag = true;
            } else {
                waitFor("60");
                Helper.INSTANCE.logEventToReport(driver, "fail", filePath, "Not Found");
            }
        } catch (FileNotFoundException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", filePath, e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to wait for File with extension zip or xml
     * @param filePath
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected void waitingForFile(String filePath) throws Exception {

        File currentFile = new File(filePath);
        try {
            if (currentFile.getPath().endsWith("ZIP") || currentFile.getPath().endsWith("zip")) {
                waitFor("30");
                Helper.INSTANCE.logEventInfoToReport(driver, "Waiting for file", "File extenstion is zip");

            }
            if (currentFile.getPath().endsWith("XML") || currentFile.getPath().endsWith("xml")) {
                waitFor("60");
                Helper.INSTANCE.logEventInfoToReport(driver, "Waiting for file", "File extenstion is xml");

            } else {
                Helper.INSTANCE.logEventInfoToReport(driver, "Waiting for file", "File extenstion is not zip/xml");
            }
        } catch (FileNotFoundException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", "Waiting for file : " + filePath, e.getMessage());
        }
    }

    /**
     * This method is used to select the value from drop down by visible text
     * @param element
     * @param value
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date 7/12/2022	 
     */
    protected boolean selectByVisibleText(WebElement element, String value, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            Select select = new Select(element);
            select.selectByVisibleText(value);
            // waitFor("5");
            Allure.step(desc);
            flag = true;
        } catch (NoSuchElementException e) {
            Assert.fail(desc);
        } catch (Exception e) {
            Assert.fail(desc);
        }
        return flag;
    }

    /**
     * This methods used to click on the element on webpage by javascript executor
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean clickOnElementJs(WebElement element, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            if (waitForElementVisible(element)) {
                WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
                JavascriptExecutor js = ((JavascriptExecutor) driver);
                js.executeScript("window.scrollTo(" + element.getLocation().x + "," + element.getLocation().y + ")");
                js.executeScript("arguments[0].click();", element);
                Allure.step(desc);
                flag = true;
            }
        } catch (Exception e) {
            Assert.fail(desc);
        }
        return flag;
    }

    /**
     * This method filters csv data based on position
     * @param csvAllData
     * @param position
     * @param status
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date : 10 Aug 2022
     */
    public static List<String> filterCsvData(List<String[]> csvAllData, int position, String status) {
        List<String> csvListValue = new ArrayList<>();
        StringBuilder csvvalue = null;
        try {
            for (String[] row : csvAllData) {
                csvvalue = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    if (status.equals("single") && i == position) {
                        csvvalue.append(row[i]);
                    } else if (!row[i].isEmpty() && !status.equals("single") && i < position) // Remove Empty and
                                                                                              // multiple values
                    {
                        if (i == 0) {
                            csvvalue.append(row[i]);
                        } else {
                            csvvalue.append(", ");
                            csvvalue.append(row[i]);
                        }
                    }
                }
                csvListValue.add(csvvalue.toString());
            }
            System.out.println("====csvData=====" + csvData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(csvData);
        return csvListValue;
    }

    /**
     * This method reads csv data based on filepath and type
     * @param filePath
     * @param type
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date : 10 Aug 2022
     */
    public static List<String[]> readCSVFile(String filePath, String type) {
        // Object[] csvRow = new Object[1];
        List<String[]> csvRow = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(new File(filePath));
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> allData = csvReader.readAll();
            if (type.equals("with"))
                allData.remove(0);
            for (String[] row : allData) {
                csvRow.add(row);
            }
        } catch (Exception e) {
            Assert.fail("Unable to read csv File in FilePath: " + filePath);
        }
        System.out.println(csvRow);
        return csvRow;
    }

    /**
     * This method asserts expected and actual value
     * @param element
     * @param expected
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected boolean assertExpectedActualValue(WebElement element, String expected, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.visibilityOf(element));

            actual = element.getText().trim();
            Assert.assertEquals(actual, expected, desc);
            Helper.INSTANCE.logEventToReportForTestSteps(driver, "pass", expected, actual, desc);
            flag = true;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReportForTestSteps(driver, "fail", expected, actual, desc);
            flag = false;
        }
        return flag;
    }

    /**
     * This method handles browser based alert by accept/dismiss after clicking
     * @param element
     * @param text
     * @param desc
     * @return boolean
     * @throws Exception
     * @Created Date : 01 Aug 2022
     */
    protected boolean alertHandlingAcceptorDismiss(WebElement element, String text, String desc) throws Exception {
        boolean flag = false;
        try {
            System.out.println("alert clicking !!!");
            clickOnElement(element, desc);
            wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
            wait.ignoring(UnhandledAlertException.class).until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (text.equalsIgnoreCase("accept")) {
                alert.accept();
                // Helper.INSTANCE.logEventToReport(driver, "Pass", element,
                // alert.getText()+"Alert Accepted");
                flag = true;
            } else if (text.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
                Helper.INSTANCE.logEventToReport(driver, "Pass", element, "Alert Dismissed");
                flag = true;
            }
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "error", "Alert", e.getMessage());
        }
        return flag;
    }

    /**
     * This method performs to wait until the element visible in DOM of the page
     * @param locator
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public boolean waitForElementVisible(By locator, String desc) throws Exception {
        boolean elementPresent = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            elementPresent = true;
        } catch (Exception e) {
            error = desc + " expected true but found false";
            Helper.INSTANCE.setErrorMessage(Helper.INSTANCE.getCurrentTestCaseId(), error);
            Assert.fail(error);
        }
        return elementPresent;
    }

    /**
     * This method performs wait until the element is invisible on the DOM
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public boolean waitForInVisibleElementByXpath(String element, String desc) throws Exception {
        boolean elementPresent = false;
        try {
            waitForDocumentReady();
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
            new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            new WebDriverWait(driver, 60).ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            elementPresent = true;
        } catch (Exception e) {
            Assert.fail(desc);
        } finally {
            Thread.sleep(2000);
        }
        return elementPresent;
    }

    /**
     * This method clicks on WebElement on webpage
     * @param WebElement
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    protected void clickOnElementNew(WebElement element, String desc) throws Exception {
        waitForDocumentReady();
        if (waitForElementVisible(element)) {
            WebDriverManager.getWebdriverWait().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            Allure.step(desc);
        }
    }

    /**
     * This method performs verify the presence of element
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementNotPresent(WebElement element, String desc) throws Exception {
        boolean flag = false;
        try {
            Helper.INSTANCE.logEventToReport(driver, "pass", desc);
            flag = !element.isDisplayed();
        } catch (Exception e) {
            flag = true;
        }
        return flag;
    }

    /**
     * This method used to fetch the latest download file
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    public String fetchLatestDownloadFile() throws InterruptedException {
        Thread.sleep(5000);
        String newfile = "";
        try {
            File downloadedDir = new File(System.getProperty("user.dir") + File.separator + "target\\Assets");
            File[] listofFiles = downloadedDir.listFiles();
            if (listofFiles == null || listofFiles.length == 0) {
                return "";
            }
            File LatestModifiedFile = listofFiles[0];
            for (int i = 1; i < listofFiles.length; i++) {
                if (LatestModifiedFile.lastModified() < listofFiles[i].lastModified()) {
                    LatestModifiedFile = listofFiles[i];
                }
            }
            newfile = LatestModifiedFile.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newfile;
    }

    /**
     * This method return text list from WebElement list
     * @param locator
     * @return List<String>
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    protected List<String> getTextFindElements(By locator) {
        List<String> list = new ArrayList();
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement ele : elements) {
            list.add(ele.getText());
        }
        return list;
    }

    /**
     * This method return tootip text from the button
     * @param locator
     * @param attributeName
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    protected String getToolTipText(By locator, String attributeName) {
        String toolTipText = driver.findElement(locator).getAttribute(attributeName);
        return toolTipText;
    }

    /**
     * This method used for dynamic object creation of pages
     * @param driver
     * @param class<T> pageClass
     * @return pageInstance
     * @author Rakesh.Shevale
     * @Created Date : 17/07/2023
     */
    public static <T extends BasePage> T initialize(WebDriver driver, Class<T> pageClass) {
        try {
            T pageInstance = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
            return pageInstance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String getDefaultDropDownValue(WebElement element) throws Exception {
        try {
            waitForDocumentReady();
            waitForElementVisible(element);
            Select select = new Select(element);
            Helper.INSTANCE.logEventToReport(driver, "pass", element, "Default value is Selected");
            return select.getFirstSelectedOption().getText();
        } catch (NoSuchElementException e) {
            Helper.INSTANCE.logEventToReport(driver, "error", element, e.getMessage());
            return null;
        } catch (Exception e) {
            Helper.INSTANCE.logEventToReport(driver, "fail", element, e.getMessage());
            return null;
        }
    }

    /**
     * This method performs to wait until the element visible in DOM of the page 
     * @param element
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01 Aug 2022
     */
    public boolean waitForElementVisibleLimitedWait(WebElement element, int timeOut) throws Exception {
        boolean elementPresent = false;
        try {
            waitForDocumentReady();
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.ignoring(InvalidElementStateException.class).ignoring(NoSuchElementException.class);
            elementPresent = true;
        } catch (Exception e) {
            elementPresent = false;
        }
        return elementPresent;
    }

    /**
     * This method performs verify the presence of element on webpage
     * 
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementNotPresentWithTimeOut(WebElement element, int timeOut, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForElementVisibleLimitedWait(element, timeOut);
            element.isDisplayed();
            Helper.INSTANCE.logEventToReport(driver, "pass", desc);
            flag = false;
        } catch (Exception e) {
            flag = true;
        }
        return flag;
    }


    /**
     * This method performs verify the presence of element on webpage
     * 
     * @param element
     * @param desc
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/12/2022
     */
    protected boolean isElementPresentWithTimeOut(WebElement element, int timeOut, String desc) throws Exception {
        boolean flag = false;
        try {
            waitForElementVisibleLimitedWait(element, timeOut);
            element.isDisplayed();
            Helper.INSTANCE.logEventToReport(driver, "pass", desc);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * This method waits for a particular element in the webpage
     * 
     * @param element
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 7/11/2022
     */
    public void waitForElementPresenceSpecified(WebElement element, int timeOut) {

        try {
            System.out.println("Waiting in fluencyyyyyy");

            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(WebDriverManager.getDriver());

            wait.pollingEvery(timeOut, TimeUnit.SECONDS);

            wait.withTimeout(30, TimeUnit.MILLISECONDS);

            Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()

            {

                public Boolean apply(WebDriver driver) {

                    //driver.findElement();
                    element.isDisplayed();
                    System.out.println("Waiting in fluency in isDisplayed");

                    return true;

                }

            };

            wait.until(function);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

>>>>>>> dev
}
