package com.prime.pageFactory.pages.fpj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class BookmarksPage extends BasePage{

	/**
	 * This constructor initializes the BookmarksPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public BookmarksPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}
	
	
	
	
	
	
}
