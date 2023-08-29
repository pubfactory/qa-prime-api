package com.prime.pageFactory.pages.fpj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class BrowseOrSearchPage extends BasePage {

	/**
	 * 
	 * This constructor initializes the ArticleCitationPage class object
	 * 
	 * 
	 * 
	 * @param WebDriver
	 * 
	 * @throws Exception
	 * 
	 * @author Rakesh.Shevale
	 * 
	 * @Created Date : 07/07/2023
	 * 
	 */

	public BrowseOrSearchPage(WebDriver driver) throws Exception {

		super(driver);

		PageFactory.initElements(driver, this);

		waitForDocumentReady();

	}

	/**
	 * 
	 * This method returns Browse label text on Browse Page
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 * 
	 * @author Rakesh.Shevale
	 * 
	 * @Created Date : 22/08/2023
	 * 
	 */

	public String getBrowsePageLabelText() throws Exception {

		String browsetext = getTextFromElement(browseText);

		return browsetext;

	}

	/**
	 * 
	 * This method returns total search results count
	 * 
	 * @return int
	 * 
	 * @throws Exception
	 * 
	 * @author Rakesh.Shevale
	 * 
	 * @Created Date : 22/08/2023
	 * 
	 */
	public int getTotatResultOnBrowseOrSearchPage() throws Exception {
		String totalResult = getTextFromElement(totalResultCount);
		int totalResultcount = Integer.parseInt(totalResult);
		return totalResultcount;
	}

	@FindBy(xpath = "//h1[text()='Browse']")
	private WebElement browseText;

	@FindBy(xpath = "//span[text()='of ']//following::span[1]")
	private WebElement totalResultCount;
}
