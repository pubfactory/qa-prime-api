package com.prime.generics;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import net.bytebuddy.utility.RandomString;

public class FileUploadManager {
	public static String FILEEXEPATH = BasePage.pwd + "/Assets/FileUpload.exe";
	public static String ASSETSFOLDER = BasePage.pwd + "/Assets/";

	/**
	 * This method is used to upload file by sendkeys
	 * @param element
	 * @param filePath
	 * @param driver
	 * @param extentObj
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	// private final Lock lock = new ReentrantLock();
	public synchronized void fileUpload(WebElement element, String filePath, WebDriver driver, ExtentTest extentObj)
			throws Exception {
		try {
			WebDriverWait driverWait = new WebDriverWait(driver, 180);
			driverWait.until(ExpectedConditions.visibilityOf(element));
			// logEventInfoToReport(driver, "pass", element, "Upload button visibled");
			element.sendKeys(filePath);
			// logEventToReport(extentObj, driver, "pass", element, "TypeOnElement");
		} catch (AssertionError | Exception e) {
			e.printStackTrace();

			// logEventToReport(extentObj,driver, "error", "fileUploadThread",
			// e.getMessage());
		} finally {
		}
	}

	/**
	 * This method is used to get the error stream for AutoIt
	 * @param inputStream
	 * @param driver
	 * @param extentObj
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected boolean getErrorStreamForAutoIt(InputStream inputStream, WebDriver driver, ExtentTest extentObj)
			throws Exception {
		boolean flag = false;
		StringBuilder text = new StringBuilder();
		try {
			for (int i = 0; i < inputStream.available(); i++) {
				System.out.println(inputStream.read());
				text.append(inputStream.read());
			}
			if (text.length() == 0) {
				flag = true;
			} else {
				logEventToReport(extentObj, driver, "error", "getErrorStreamForAutoIt",
						"Problem in auto it Fileupload exe");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			logEventToReport(extentObj, driver, "error", "getErrorStreamForAutoIt", e.getMessage());
		}
		return flag;
	}

	/**
	 * This method is used to copy the old file into new file and returns the AbsolutePath of new file
	 * @param oldfileName
	 * @param extentObj
	 * @param driver
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	protected String duplicateAutoitexe(String oldfileName, ExtentTest extentObj, WebDriver driver) throws Exception {
		File oldFile = new File(oldfileName);
		File newFile = new File(oldFile.getParent() + "\\" + FilenameUtils.getBaseName(oldfileName)
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + new RandomString().nextString() + ".exe");
		FileUtils.copyFile(oldFile, newFile);
		System.out.println("File copied");
		System.out.println(newFile.getAbsolutePath());
		System.out.println("Generate duplicate exe " + newFile.getAbsolutePath() + " is generated");
		logEventInfoToReport(extentObj, driver, "duplicateAutoitexe",
				"Generate duplicate exe " + newFile.getAbsolutePath() + " is generated");
		return newFile.getAbsolutePath();
	}

	/**
	 * This method log status Information To Extent and Allure Report For Test Steps in synchronized manner
	 * @param extentObj
	 * @param d
	 * @param status
	 * @param element
	 * @param description
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public synchronized void logEventToReport(ExtentTest extentObj, WebDriver d, String status, Object element,
			String description) throws Exception {
		try {
			element = byPassWebElement(element);
			if (status.equalsIgnoreCase("pass")) {
				extentObj.log(LogStatus.PASS,
						"<b>[" + element + "] - <span style='color:green'>" + StringUtils.capitalize(description)
								+ "</span></b>" + Thread.currentThread().getId() + " -Fileupload thread");
			} else if (status.equalsIgnoreCase("fail")) {
				extentObj.log(LogStatus.FAIL,
						"<b>[" + element + "] - <span style='color:red'>" + StringUtils.capitalize(description)
								+ "</span></b>" + Thread.currentThread().getId() + " -Fileupload thread");
				extentObj.log(LogStatus.INFO,
						extentObj.addBase64ScreenShot(Helper.INSTANCE.addScreenShot(d, "./reports/image.png"))
								+ Thread.currentThread().getId() + " -Fileupload thread");
			} else if (status.equalsIgnoreCase("error")) {
				extentObj.log(LogStatus.ERROR, description);
				extentObj.log(LogStatus.INFO,
						extentObj.addBase64ScreenShot(Helper.INSTANCE.addScreenShot(d, "./reports/error.png"))
								+ Thread.currentThread().getId() + " -Fileupload thread");
			}
		} catch (Exception e) {
			System.out.println("error block report");
			extentObj.log(LogStatus.ERROR, element.toString());
			extentObj.log(LogStatus.ERROR, e.getMessage());
			extentObj.log(LogStatus.INFO,
					extentObj.addBase64ScreenShot(Helper.INSTANCE.addScreenShot(d, "./reports/error.png"))
							+ Thread.currentThread().getId() + " -Fileupload thread");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method log event Information To Extent and Allure Report For Test Steps in synchronized manner
	 * @param extentObj
	 * @param d
	 * @param key
	 * @param description
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/12/2022
	 */
	public synchronized void logEventInfoToReport(ExtentTest extentObj, WebDriver d, String key, String description)
			throws Exception {
		extentObj.log(LogStatus.INFO,
				"<b>[" + key + "] - <span style='color:blue'>" + StringUtils.capitalize(description) + "</span></b>");
	}

	
	public String byPassWebElement(Object value) {
		String logs = "";
		String[] loggerValues;
		if (value.toString().contains("->")) {
			try {
				loggerValues = value.toString().split("->");
//				logs = loggerValues[1].replace("]]", "");
				logs = loggerValues[1];
			} catch (Exception e) {
				e.getMessage();
			}
		} else if (value.toString().contains("Proxy element for: DefaultElementLocator")) {
			try {
				loggerValues = value.toString().split("Proxy element for: DefaultElementLocator");
//				logs = loggerValues[1].replace("']", "'");
				logs = loggerValues[1];
			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			logs = String.valueOf(value).toString();
		}
		return logs;
	}

	/**
	 * This method is used to upload file by Javascript Executor(drag and drop)
	 * @param target
	 * @param fileName
	 * @param driver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public synchronized void dragAndDropFileUpload(WebElement target, String fileName, WebDriver driver)
			throws Exception {
		try {
			File filePath = new File(fileName);
			if (filePath.exists()) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				WebDriverWait wait = new WebDriverWait(driver, 30);

				String JS_DROP_FILE = "var target = arguments[0]," + "    offsetX = arguments[1],"
						+ "    offsetY = arguments[2]," + "    document = target.ownerDocument || document,"
						+ "    window = document.defaultView || window;" + ""
						+ "var input = document.createElement('INPUT');" + "input.type = 'file';"
						+ "input.style.display = 'none';" + "input.onchange = function () {"
						+ "  var rect = target.getBoundingClientRect(),"
						+ "      x = rect.left + (offsetX || (rect.width >> 1)),"
						+ "      y = rect.top + (offsetY || (rect.height >> 1)),"
						+ "      dataTransfer = { files: this.files };" + ""
						+ "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {"
						+ "    var evt = document.createEvent('MouseEvent');"
						+ "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);"
						+ "    evt.dataTransfer = dataTransfer;" + "    target.dispatchEvent(evt);" + "  });" + ""
						+ "  setTimeout(function () { document.body.removeChild(input); }, 25);" + "};"
						+ "document.body.appendChild(input);" + "return input;";

				WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, target, target.getLocation().getX(),
						target.getLocation().getY());
				input.sendKeys(filePath.getAbsoluteFile().toString());
				wait.until(ExpectedConditions.stalenessOf(input));
			} else {
				// logEventToReport(extentObj, driver, "error", "Upload file path",
				// filePath.getAbsolutePath());
			}
		} catch (Exception e) {
			// logEventToReport(extentObj, driver, "error", "Upload file path",
			// e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
