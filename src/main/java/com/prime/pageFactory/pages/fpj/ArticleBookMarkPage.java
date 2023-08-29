package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class ArticleBookMarkPage extends BasePage{
	
	/**
	 * This constructor initializes the ArticleBookMarkPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public ArticleBookMarkPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}
	
	/**
	 * This method used to check Save content(bookmark) button is present on  Article Page
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public void verifyPageSizeDropDownPresentOnSavedSearchesTab() throws Exception {
		isElementPresent(saveButton,"checking the Save content(bookmark) button is present on  Article Page");
	}
	
	/**
	 * This method used to click on Save button on Article Page
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public void clickOnSaveButtonOnArticlePage() throws Exception {
		clickOnElement(saveButton,"Clicking on Save button on Article Page");
	}
	
	/**
	 * This method used to click on Add Button on Save This Entry Popup on Article Page
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public void clickOnAddButtonOnSaveThisEntryPopupOnArticlePage() throws Exception {
		clickOnElement(saveButton,"Clicking on Add Button on Save This Entry Popup on Article Page");
	}
	
	/**
	 * This method used to click on Cancel Button on Save This Entry Popup on Article Page
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public void clickOnCancelButtonOnSaveThisEntryPopupOnArticlePage() throws Exception {
		clickOnElement(cancelButtonOnSaveThisEntryPopup,"Clicking on Cancel Button on Save This Entry Popup on Article Page");
	}
	
	/**
	 * This method used to click on Save Button on Save This Entry Popup on Article Page
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public void clickOnSaveButtonOnSaveThisEntryPopupOnArticlePage() throws Exception {
		clickOnElement(cancelButtonOnSaveThisEntryPopup,"Clicking on Save Button on Save This Entry Popup on Article Page");
	}
	
	/**
	 * This method return Save This Entry header label Text On Article Page(
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public String getSaveThisEntryHeaderLabelTextSaveThisEntryOnArticlePage() throws Exception {
		String SaveThisEntryText = getTextFromElement(saveThisEntryHeaderOnSaveThisEntryPopup);
		return SaveThisEntryText;
	}
	
	/**
	 * This method return Tags label Text On Save This Entry Popup On Article Page
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public String getTagsLabelTextOnSaveThisEntryPopupOnArticlePageOnArticlePage() throws Exception {
		String tagsText = getTextFromElement(TagsLabelOnSaveThisEntryPopup);
		return tagsText;
	}
		
	/**
	 * This method return Add new Tag label Text On Save This Entry Popup On Article Page
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public String getAddNewTagLabelTextOnSaveThisEntryPopupOnArticlePageOnArticlePage() throws Exception {
		String addNewTagsText = getTextFromElement(addNewTagLabelOnSaveThisEntryPopup);
		return addNewTagsText;
	}
	
	/**
	 * This method return the all Existing Tags Label Text On Save This Entry Popup On Article Page
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public List<String> getExistingTagsLabelTextOnSaveThisEntryPopupOnArticlePageOnArticlePage() throws Exception {
		List<String> existingTagsOnSaveThisEntryPopup = new ArrayList<String>();
		By locator = By.xpath("//header[text()='Save this entry']//parent::section//following-sibling::p[text()='Tags']//following-sibling::span");
		existingTagsOnSaveThisEntryPopup = getTextFindElements(locator);
		return existingTagsOnSaveThisEntryPopup;
	}
	
	/**
	 * This method used to add new tags on Save this Entry Popup On Article Page

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 08/08/2023
	 */
	public void addNewTagOnSaveThisEntryPopupOnArticlePageOnArticlePage(String newTag) throws Exception {
		typeOnElement(addNewTagTextBoxOnSaveThisEntryPopup,newTag,"Adding new tags on Save This Entry Popup");
	}
	
	
	@FindBy(xpath="//button[@title='Save']")
	private WebElement saveButton;
	@FindBy(xpath="//header[text()='Save this entry']")
	private WebElement saveThisEntryHeaderOnSaveThisEntryPopup;
	@FindBy(xpath="//header[text()='Save this entry']//parent::section//following-sibling::p[text()='Tags']")
	private WebElement TagsLabelOnSaveThisEntryPopup;
	@FindBy(xpath="//header[text()='Save this entry']//following::p[text()='Add new tag']")
	private WebElement addNewTagLabelOnSaveThisEntryPopup;
	@FindBy(xpath="//header[text()='Save this entry']//following::p[text()='Add new tag']//following::input") 
	private WebElement addNewTagTextBoxOnSaveThisEntryPopup;
	@FindBy(xpath="//header[text()='Save this entry']//following::p[text()='Add new tag']//following::button[text()='Add']")
	private WebElement AddButtonOnSaveThisEntryPopup;
	@FindBy(xpath="//header[text()='Save this entry']//following::p[text()='Add new tag']//following::button[text()='Cancel']")
	private WebElement cancelButtonOnSaveThisEntryPopup;
	@FindBy(xpath="//header[text()='Save this entry']//following::p[text()='Add new tag']//following::button[text()='Save']")
	private WebElement saveButtonOnSaveThisEntryPopup;
}
