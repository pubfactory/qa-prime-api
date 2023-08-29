package com.prime.generics;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverManager {
	private static Map<Integer, WebDriver> webDriver = new HashMap<Integer, WebDriver>();
	private static Map<Integer, Object> chkObject = new HashMap<Integer, Object>();
	private static Map<Integer, Set<Cookie>> getAllCookies = new HashMap<Integer, Set<Cookie>>();
	private static Map<Integer, Object> loginPageObject = new HashMap<Integer, Object>();
	private static Map<Integer, WebDriverWait> webDriverWait = new HashMap<Integer, WebDriverWait>();
	private static Map<String, FileUploadManager> fileObject = new HashMap<String, FileUploadManager>();
	private static Map<Integer, Boolean> flagChecker = new HashMap<Integer, Boolean>();
	private static Map<Integer, String> credentials = new HashMap<Integer, String>();
	private static Map<Integer, String> publishers = new HashMap<Integer, String>();
	
	/**
	 * This method is used to get the Publisher
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String getPublisher() {
		return publishers.get((int) (long) Thread.currentThread().getId());
	}

	/**
	 * This method is used to set the Publisher
	 * @param value
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void setPublisher(String value) {
	publishers.put((int) (long) Thread.currentThread().getId(), value);
	}
	
	/**
	 * This method is used to get the Credentials
	 * @return String
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static String getCredentials() {
		return credentials.get((int) (long) Thread.currentThread().getId());
	}

	/**
	 * This method is used to set the Credentials
	 * @param value
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void setCredentials(String value) {
	credentials.put((int) (long) Thread.currentThread().getId(), value);
	}
	
	/**
	 * This method is used to get the Flag Checker
	 * @return Boolean
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static Boolean getFlagChecker() {
		return flagChecker.get((int) (long) Thread.currentThread().getId());
	}

	/**
	 * This method is used to set the Flag Checker
	 * @param result
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static void setFlagChecker(boolean result) {
	flagChecker.put((int) (long) Thread.currentThread().getId(), result);
	}
	
	/**
	 * This method is used to reset the flag Map
	 * @return Map<Integer,Boolean>
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static Map<Integer, Boolean> resetFlagMap() {
	return flagChecker;
	}
	
	// private static ThreadLocal<WebDriver> webDriver = new
	// ThreadLocal<WebDriver>();
	// private static ArrayList<Boolean> flagMap=new ArrayList<Boolean>();

	/*
	 * public static synchronized WebDriver getDriver() { return
	 * webDriver.get(); }
	 * 
	 * public static synchronized void setWebDriver(WebDriver driver) {
	 * webDriver.set(driver); }
	 */

	/*
	 * public static synchronized void setloginStatus(boolean flag) {
	 * flagMap.add(flag); } public static synchronized boolean getLoginStatus()
	 * { return flagMap.get(0);
	 * 
	 * }
	 */
	
	/**
	 * This method is used to get the webdriver in synchronized manner
	 * @return WebDriver
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized WebDriver getDriver() {
		return (WebDriver) webDriver.get((int) (long) Thread.currentThread().getId());
	}

	/**
	 * This method is used to set the webdriver in synchronized manner
	 * @param driver
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized void setWebDriver(WebDriver driver) {
		webDriver.put((int) (long) Thread.currentThread().getId(), driver);
	}

	/**
	 * This method is used to get the cookies in synchronized manner
	 * @return Set<Cookie>
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized Set<Cookie> getCookies() {
		return getAllCookies.get((int) (long) Thread.currentThread().getId());
	}

	/**
	 * This method is used to set the cookies in synchronized manner
	 * @param cookies
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized void setCookies(Set<Cookie> cookies) {
		getAllCookies.put((int) (long) Thread.currentThread().getId(), cookies);
	}

	/**
	 * This method is used to quite the driver in synchronized manner
	 * @throws IOException
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized void closeDriver() throws IOException {
		try {
			for (Map.Entry<Integer, WebDriver> entry : webDriver.entrySet()) {
				if (entry.getValue() != null) {
					entry.getValue().quit();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
		//	Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		}
	}

	public static synchronized Object getLoginPageObject() {
		return loginPageObject.get((int) (long) Thread.currentThread().getId());
	}

	public static synchronized void setLoginPage(Object obj) {
		loginPageObject.put((int) (long) Thread.currentThread().getId(), obj);
	}
	
	/**
	 * This method is used to get the webdriver wait in synchronized manner
	 * @return WebDriverWait
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized WebDriverWait getWebdriverWait() {
		return webDriverWait.get((int) (long) Thread.currentThread().getId());
	}

	/**
	 * This method is used to set the webdriver wait in synchronized manner
	 * @param obj
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized void setWebdriverWait(WebDriverWait obj) {
		webDriverWait.put((int) (long) Thread.currentThread().getId(), obj);
	}
	
	/**
	 * This method is used to get the File Upload Object in synchronized manner
	 * @return FileUploadManager
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized FileUploadManager getFileUploadObject() {
		return fileObject.get("fileobject");
	}

	/**
	 * This method is used to set the file upload object in synchronized manner
	 * @param obj
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public static synchronized void setFileUploadObject(FileUploadManager obj) {
		fileObject.put("fileobject", obj);
	}
}

/*
 * private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
 * 
 * public synchronized static void setTLDriver (FirefoxOptions options) {
 * tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(options)); }
 * public synchronized static WebDriver getDriver () { return tlDriver.get(); }
 */
