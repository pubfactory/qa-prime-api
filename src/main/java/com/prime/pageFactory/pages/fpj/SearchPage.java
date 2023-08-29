package com.prime.pageFactory.pages.fpj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class SearchPage extends BasePage {

	/**
	 * This constructor initializes the MasterPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	public SearchPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}
	
	
	public String getSearchResultTextOnSearchResultPage(String title) throws Exception {
		WebElement SearchResultEle =driver.findElement(By.xpath("//strong[text()='"+title+"']//preceding::h1[text()='Search Results']"));
		String SearchResultText=getTextFromElement(SearchResultEle);
		return SearchResultText;
		}

	/**
	 * This method used to click on Article title on Search page
	 * 
	 * @param title
	 * @return SearchPage Object
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	public void clickOnArticleTitleOnSearchPage(String title) throws Exception {
		WebElement ele_title = driver.findElement(By.xpath("//div[contains(text(),'" + title + "')])"));
		clickOnElement(ele_title, "clicking on " + title + " title on search Page");
	}

	@FindBy(xpath = "(//span[contains(text(),'Open access')])[1]")
	private WebElement openAccessArticle;
}
