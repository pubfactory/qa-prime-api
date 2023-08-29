package com.prime.generics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

public class Threadmanager extends Thread {

	private FileUploadManager fileObject;
	private WebElement element;
	private String filePath;
	private WebDriver driver;
	private ExtentTest extentObj;

	 /**
	 * This constructor initializes the Threadmanager class object
	 * @param obj
	 * @param element
	 * @param filePath
	 * @param driver
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public Threadmanager(FileUploadManager obj, WebElement element, String filePath, WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.fileObject = obj;
		this.filePath = filePath;
		this.element = element;
		this.driver = driver;
	}

	/**
	 * This method is used to upload file on web Page
	 * 
	 * @author Rakesh.Shevale
	 * @Created Date : 01 Aug 2022
	 */
	public synchronized void run() {
		System.out.println("FILEOBJNAME   " + fileObject);
		synchronized (fileObject) {
			try {
				if (!element.getAttribute("id").contains("AsyncFileUpload1")) {
					fileObject.dragAndDropFileUpload(element, filePath, driver);
				} else {
					fileObject.fileUpload(element, filePath, driver, extentObj);
				}
				System.out.println("Fileupload ended   " + fileObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
