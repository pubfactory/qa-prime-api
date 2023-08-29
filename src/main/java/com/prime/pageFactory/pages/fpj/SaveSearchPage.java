package com.prime.pageFactory.pages.fpj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class SaveSearchPage extends BasePage{

	/**
	 * This constructor initializes the SaveSearchPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public SaveSearchPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}
	
	/**
	 * This method used to click on Save search button article browse or search page
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void clickOnSaveSearchResultButton() throws Exception {
		clickOnElement(saveSearchbutton,"Clicking on Save search button article browse or search page");
	}
	
	/**
	 * This method used to check Save search button is present or not on browse or search page

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void verifySaveSearchbuttonPresentOnSaveSearchPopup() throws Exception {
		isElementPresent(saveSearchbutton,
				"checking the Save search button is present or not on browse or search page");
	}
	
	/**
	 * This method returns Header Text on Save search pop up header
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public String getHeaderTextOnSaveSearchPopup() throws Exception {
		String headerText = getTextFromElement(SaveSearchPopupHeader);
		return headerText;
	}	
	
	/**
	 * This method returns Search result on Save search pop up header
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public String getSearchResultonSaveSearchPopup() throws Exception {
		String searchResultText = getTextFromElement(NumberOfResultLabelOnSaveSearchPopup);
		return searchResultText;
	}	
		
	/**
	 * This method returns save this Search label text on Save search pop up header
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public String getsaveThisSearchLabelTextonSaveSearchPopup() throws Exception {
		String saveThisSearchText = getTextFromElement(saveThisSearchLabelwithONOFFStatusOnSaveSearchPopup);
		return saveThisSearchText;
	}	
	
	/**
	 * This method returns Notify Me Via Email label text on Save search pop up header
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public String getNotifyMeViaEmailOnSaveSearchPopup() throws Exception {
		String NotifyMeViaEmailText = getTextFromElement(NotifyMeViaEmailLabelwithONOFFStatusOnSaveSearchPopup);
		return NotifyMeViaEmailText;
	}	
	
	/**
	 * This method returns save this Search label text on Save search pop up header
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public String getTitleForSearchLabelTextonSaveSearchPopup() throws Exception {
		String titleForSearchText = getTextFromElement(titleForSearchLabelOnSaveSearchPopup);
		return titleForSearchText;
	}	
		
	/**
	 * This method returns Notes Label text on Save search pop up header
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public String getNotesLabelTextonSaveSearchPopup() throws Exception {
		String NotesText = getTextFromElement(notesLabelOnSaveSearchPopup);
		return NotesText;
	}
	
	/**
	 * This method used to enter text in the notes text box on Save search pop up header

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void enterTextInNotesTextBoxOnSignUpPage() throws Exception {
		typeOnElement(notesTextBoxfieldOnSaveSearchPopup,"This is first Note");
	}
	
	/**
	 * This method used to check the Cancel button is present or not on save search popup

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void verifyCancelButtonOnSaveSearchPopup() throws Exception {
		isElementPresent(cancelButtonOnSaveSearchPopup,
				"checking the Cancel button is present or not on save search popup");
	}
	
	/**
	 * This method used to check the Save button is present or not on save search popup

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void verifySaveButtonOnSaveSearchPopup() throws Exception {
		isElementPresent(cancelButtonOnSaveSearchPopup,
				"checking the Save button is present or not on save search popup");
	}
	
	/**
	 * This method used to click on Cancel Button on Save Search pop up
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void clickOnCancelButtonOnSaveSearchPopup() throws Exception {
		clickOnElement(cancelButtonOnSaveSearchPopup,"Clicking on Cancel Button on Save Search pop up");
	}
	
	/**
	 * This method used to click on Save Button on Save Search pop up
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void clickOnSaveButtonOnSaveSearchPopup() throws Exception {
		clickOnElement(saveButtonOnSaveSearchPopup,"Clicking on Save Button on Save Search pop up");
	}
	
	/**
	 * This method used to click on Save Search pop up close button
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void clickOnSaveSearchPopupCloseButtont() throws Exception {
		clickOnElement(saveSearchPopupCloseButton,"Clicking on Save Search pop up close button");
	}
	
	
	
	
	@FindBy(xpath="//button[@title='Save']")
	private WebElement saveSearchbutton;
	@FindBy(xpath="//label[contains(text(),'Save this search')]//ancestor::div//child::p[contains(text(),'Save Search')]")
	private WebElement SaveSearchPopupHeader;
	@FindBy(xpath="//p[contains(text(),'Save Search')]//following::input//preceding::div//label[contains(text(),'Save this search')]")
	private WebElement saveThisSearchLabelwithONOFFStatusOnSaveSearchPopup;
	@FindBy (xpath="//p[contains(text(),'Save Search')]//following::input//preceding::div//label[contains(text(),'Notify me via email of new results from this search')]")
	private WebElement NotifyMeViaEmailLabelwithONOFFStatusOnSaveSearchPopup;
	@FindBy (xpath="//label[contains(text(),'Save this search')]//ancestor::div//child::p[contains(text(),'Title for Search')]")
	private WebElement titleForSearchLabelOnSaveSearchPopup;
	@FindBy (xpath="//p[contains(text(),'Notes')]//following::footer//button[1]")
	private WebElement cancelButtonOnSaveSearchPopup;
	@FindBy (xpath="//p[contains(text(),'Notes')]//following::footer//button[2]")
	private WebElement saveButtonOnSaveSearchPopup;
	@FindBy(xpath="//label[contains(text(),'Save this search')]//ancestor::div//child::p[2]")
	private WebElement NumberOfResultLabelOnSaveSearchPopup;
	@FindBy (xpath="//p[contains(text(),'Save Search')]//parent::div//following::textarea")
	private WebElement notesTextBoxfieldOnSaveSearchPopup;
	@FindBy (xpath="//textarea//parent::div//following::p[contains(text(),'Notes')]")
	private WebElement notesLabelOnSaveSearchPopup;
	@FindBy (xpath="//p[contains(text(),'Save Search')]//parent::div//preceding::button[@aria-label=\"Close\"]")
	private WebElement saveSearchPopupCloseButton;
}
