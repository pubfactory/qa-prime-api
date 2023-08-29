package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;
import com.prime.generics.Helper;

import io.qameta.allure.Allure;

public class SaveSearchTabPage extends BasePage {
	
	private int sizeCount;
	private int remainder;
	private int actualCount;
	/**
	 * This constructor initializes the SaveSearchTabPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public SaveSearchTabPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}
		
	/**
	 * This method used to check the Save Saved Searches Tab is present or not

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 01/08/2023
	 */
	public void verifySavedSearchesTabPresentInMyStuffLink() throws Exception {
		isElementPresent(savedSearchesTab,
				"checking the Save Saved Searches Tab is present or not");
	}
	
	/**
	 * This method used to click on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnSavedSearchesTab() throws Exception {
		clickOnElement(savedSearchesTab,"Clicking on Saved Searches Tab");
	}
		
	/**
	 * This method used to check the DeleteAll button is present on Saved Searches Tab

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void verifyDeleteAllButtonPresentOnSavedSearchesTab() throws Exception {
		isElementPresent(deleteAllButton,
				"checking the DeleteAll button is present on Saved Searches Tab");
	}
	
	/**
	 * This method used to check the Edit button is present on Saved Searches Tab
	 * @param searchText
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void verifyEditButtonPresentOnSavedSearchesTab(String searchText) throws Exception {
		WebElement ele= driver.findElement(By.xpath("//a[text()='"+searchText+"']//parent::p//following::button[@title='Edit']"));
		isElementPresent(ele,
				"checking the Edit button is present on Saved Searches Tab");
	}
	
	/**
	 * This method used to check the Remove button is present on Saved Searches Tab
	 * @param searchText
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void verifyRemoveButtonPresentOnSavedSearchesTab(String searchText) throws Exception {
		WebElement ele= driver.findElement(By.xpath("//a[text()='"+searchText+"']//parent::p//following::button[@title='Remove']"));
		isElementPresent(ele,
				"checking the Remove button is present on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on DeleteAll Button on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnDeleteAllButtonOnSavedSearchesTab() throws Exception {
		clickOnElement(deleteAllButton,"Clicking on DeleteAll Button on Saved Searches Tab");
	}
	

	/**
	 * This method used to click on Edit Button on Saved Searches Tab
	 * @param searchText
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnEditButtonOnSavedSearchesTab(String searchText) throws Exception {
		WebElement ele= driver.findElement(By.xpath("//a[text()='"+searchText+"']//parent::p//following::button[@title='Edit']"));
		clickOnElement(ele,"Clicking on Edit Button on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on Remove Button on Saved Searches Tab
	 * @param searchText
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnRemoveButtonOnSavedSearchesTab(String searchText) throws Exception {
		WebElement ele= driver.findElement(By.xpath("//a[text()='"+searchText+"']//parent::p//following::button[@title='Remove']"));
		clickOnElement(ele,"Clicking on Remove Button on Saved Searches Tab");
	}
	
	/**
	 * This method returns Save date text on Saved Searches Tab
	 * @param searchText
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public String getSavedSearchesSavedDateOnSavedSerchesTab(String searchText) throws Exception {
		WebElement ele = driver.findElement(By.xpath("//a[text()='"+searchText+"']//parent::p//parent::div//following-sibling::div//child::p"));
		String SavedDate = getTextFromElement(ele);
		return SavedDate;
	}
	
	/**
	 * This method used to select the sort value from sort by dropdown on Saved Searches Tab
	 * 
	 * @param value
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void selectsortValueFromSortByDropdownOnSavedSearchesTab(String value) throws Exception {
		selectByValue(sortByDropDown, value,
				"Selecting the value " + value + " from sort by dropdown on Saved Searches Tab");
	}
	
	/**
	 * This method used to select the Page Size value from page size dropdown on Saved Searches Tab
	 * 
	 * @param value
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void selectPageSizeValueFromPageSizeDropdownOnSavedSearchesTab(String value) throws Exception {
		selectByValue(pageSizeDropDown, value,
				"Selecting filter value from Page size dropdown on Saved Searches Tab");
	}
	
	/**
	 * This method returns Delete all popup header label text on Delete all popup on Saved Searches Tab
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public String getdeleteAllPopupHeaderLabelTextonSavedSearchesTab() throws Exception {
		String deleteAllPopupHeader = getTextFromElement(deleteAllPopupHeaderLabel);
		return deleteAllPopupHeader;
	}	
	
	/**
	 * This method used to check the Cancel button is present on Delete all popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void verifyCancelButtonPresentOnDeleteAllPopupOnSavedSearchesTab() throws Exception {
		isElementPresent(cancelButtonOnDeleteAllpopup,"checking the Cancel button is present on Delete all popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to check the continue button is present on Delete all popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void verifyContinueButtonPresentOnDeleteAllPopupOnSavedSearchesTab() throws Exception {
		isElementPresent(continueButtonOnDeleteAllpopup,"checking the Continue button is present on Delete all popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on Cancel button on Delete all popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnCancelButtonOnDeleteAllpopupOnSavedSearchesTab() throws Exception {
		clickOnElement(cancelButtonOnDeleteAllpopup,"Clicking on Cancel button on Delete all popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on Continue button on Delete all popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnContinueButtonOnDeleteAllpopupOnSavedSearchesTab() throws Exception {
		clickOnElement(cancelButtonOnDeleteAllpopup,"Clicking on Continue button on Delete all popup on Saved Searches Tab");
	}
	
	/**
	 * This method returns Title Label Text on Edit popup on Saved Searches Tab
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public String getTitleLabelTextOnEditPopupOnSavedSearchesTab() throws Exception {
		String titleText = getTextFromElement(TitleLabelOnEditPopupOn);
		return titleText;
	}
	

	/**
	 * This method returns notify Me Via Email Label Text on Edit popup on Saved Searches Tab
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public String getNotifyMeViaEmailLabelTextOnEditPopupOnSavedSearchesTab() throws Exception {
		String notifyMeViaEmailText = getTextFromElement(NotifyMeViaEmailLabelwithCheckBoxOnEditPopUp);
		return notifyMeViaEmailText;
	}
	
	/**
	 * This method returns Notes Label Text on Edit popup on Saved Searches Tab
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public String getNoteslLabelTextOnEditPopupOnSavedSearchesTab() throws Exception {
		String notesText = getTextFromElement(notesLabelOnEditPopUp);
		return notesText;
	}
	
	/**
	 * This method returns Title TextBox Title on Edit popup on Saved Searches Tab
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public String getTitleTextBoxTextOnEditPopupOnSavedSearchesTab() throws Exception {
		String titleTextBoxText = getTextFromElement(titleTextBoxfieldOnEditPopUp);
		return titleTextBoxText;
	}
	
	/**
	 * This method used to check the Cancel Button is present On Edit Popup On Saved Searches Tab

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public void verifyCancelButoonPresentOnEditPopUpOnSavedSearchesTab() throws Exception {
		isElementPresent(cancelButtonOnEditPopUp,"checking the Cancel Button is present on Edit popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to check the Save Button is present On Edit Popup On Saved Searches Tab

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public void verifySaveButtonPresentOnEditPopUpOnSavedSearchesTab() throws Exception {
		isElementPresent(saveButtonOnEditPopUp,"checking the Save Button is present on Edit popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on Cancel button on Edit popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnCancelButtonOnEditPopupOnSavedSearchesTab() throws Exception {
		clickOnElement(cancelButtonOnEditPopUp,"Clicking on Cancel button on Edit popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on Save button on Edit popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void clickOnSaveButtonOnEditPopupOnSavedSearchesTab() throws Exception {
		clickOnElement(saveButtonOnEditPopUp,"Clicking on Save button on Edit popup on Saved Searches Tab");
	}
	
	/** 
	 * This method return Header text On Remove Popup On Saved Search Tab
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public String getHeaderOnRemovePopupOnSavedSearchTab() throws Exception {
		String HeaderOnRemovePopup = getTextFromElement(headerOnRemovePopup);
		return HeaderOnRemovePopup;
	}
	
	/**
	 * This method used to click on Cancel button on Remove popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 03/08/2023
	 */
	public void clickOnCancelButtonOnRemovePopupOnSavedSearchesTab() throws Exception {
		clickOnElement(cancelButtonOnRemovePopUp,"Clicking on Cancel button on Remove popup on Saved Searches Tab");
	}
	
	/**
	 * This method used to click on continue button on Remove popup on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 03/08/2023
	 */
	public void clickOnContinueButtonOnRemovePopupOnSavedSearchesTab() throws Exception {
		clickOnElement(continueButtonOnRemovePopUp,"Clicking on Continue button on Remove popup on Saved Searches Tab");
	}
	
	
	/**
	 * This method return the Save Searches All Titles On Saved Searches Tab
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public List<String> getSaveSearchesAllTitlesOnSavedSearchesTab() throws Exception {
		List<String> actualAllTitlesList = new ArrayList<String>();
		By locator = By.xpath("//button[text()='Delete All']//parent::div//following::a[@target='_self']");
		actualAllTitlesList = getTextFindElements(locator);
		return actualAllTitlesList;
	}
	
	/**
	 * This method return the Save Searches Date time On Saved Searches Tab
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public List<String> getSaveSearchesAllSavedDateTimeOnSavedSearchesTab() throws Exception {
		List<String> savedDateTimeList = new ArrayList<String>();
		By locator = By.xpath("//a[@target='_self']//parent::p//parent::p//following::p[contains(text(),'Saved')]");
		savedDateTimeList = getTextFindElements(locator);
		return savedDateTimeList;
	}
	
	/**
	 * This method return the pagination Page Size Number On Saved Searches Tab
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public String getpaginationsPageSizeNumberOnSavedSearchesTab() throws Exception {
		List<String> paginationPageSizeNumber = new ArrayList<String>();
		By locator = By.xpath("//span[contains(text(),'Page')]//parent::div//ul//li");
		paginationPageSizeNumber = getTextFindElements(locator);
		return String.valueOf(paginationPageSizeNumber.size());
	}	
	
	/**
	 * This method return pagination size on Saved Searches tab
	 * 
	 * @return int
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/08/2023
	 */
	public int getPaginationSize(int filtersizecount) throws Exception {
		actualCount=this.getSaveSearchesAllTitlesOnSavedSearchesTab().size();
		this.selectPageSizeValueFromPageSizeDropdownOnSavedSearchesTab(String.valueOf(filtersizecount));
		int count = 0;
		if (actualCount > filtersizecount) {
			int size = actualCount / filtersizecount;
			for (int i = 0; i < size; i++) {
				sizeCount = actualCount - filtersizecount;
				actualCount = sizeCount;
				count++;
				if (actualCount < filtersizecount && actualCount>0) {
					count++;
				}
			}
			System.out.println("count :" + count);
		} else if (actualCount == filtersizecount) {
			count++;
			System.out.println("count :" + count);
		}
		return count;
	}

	
	/**
	 * This method used to enter the Title on Edit popup on Saved Search Tab

	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 02/08/2023
	 */
	public void enterTitleOnEditPopUpOnSavedSearchesTab(String changeTitle) throws Exception {
		typeOnElement(titleTextBoxfieldOnEditPopUp,changeTitle,"Changing the existing title");
	}
	
	public void verifyTitleNamePresentOnSavedSearchedResult(List<String>list,String target,String desc) throws Exception {
		try {
			list.contains(target);
			Allure.step(desc);
		} catch (Exception e) {
			Allure.step(e.getMessage());
		}
	}
	
	/**
	 * This method used to check sort by dropdown(filter) is present on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 05/08/2023
	 */
	public void verifysortByDropDownPresentOnSavedSearchesTab() throws Exception {
		isElementPresent(sortByDropDown,"checking the sort by dropdown(filter) is present on Saved Searches Tab");
	}
	
	/**
	 * This method return sort by filter all values in list On Saved Search Tab
	 * 
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 06/08/2023
	 */
	public List<String> getSortByAllfilterValueOnSavedSearchesTab() throws Exception {
		List<String> sortByAllfilterValue = new ArrayList<String>();
		By locator = By.xpath("//label[text()='Sort by:']//following::select[@id='sortOrder']//child::option");
		sortByAllfilterValue = getTextFindElements(locator);
		return sortByAllfilterValue;
	}	
	
	/**
	 * This method return Page size filter all values in list On Saved Search Tab
	 * 
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 06/08/2023
	 */
	public List<String> getPageSizeAllfilterValueOnSavedSearchesTab() throws Exception {
		List<String> pageSize = new ArrayList<String>();
		By locator = By.xpath("//label[text()='Sort by:']//following::select[@id='pageSize']//child::option");
		pageSize = getTextFindElements(locator);
		return pageSize;
	}
	
	/**
	 * This method used to check PageSize dropdown(filter) is present on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 05/08/2023
	 */
	public void verifyPageSizeDropDownPresentOnSavedSearchesTab() throws Exception {
		isElementPresent(pageSizeDropDown,"checking the page size dropdown(filter) is present on Saved Searches Tab");
	}
	
	/**
	 * This method used to check title is present on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/08/2023
	 */
	public void verifyTitlePresentOnSavedSearchesTab(String title) throws Exception {
		WebElement ele = driver.findElement(By.xpath("//a[text()='"+title+"']"));
		isElementPresent(ele,"checking title "+title+" is present on Saved Searches Tab");
	}

	public void verifyTitleTimeDatePresentOnSavedSearchesTab(String title) throws Exception {
		WebElement ele = driver.findElement(By.xpath("//a[text()='"+title+"']//parent::p//parent::div//following-sibling::div//p"));
		isElementPresent(ele,"checking Time Date is present on title "+title +" on Saved Searches Tab");
	}
	
	/**
	 * This method return Edit Button ToolTip text On Saved Search Tab
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 03/08/2023
	 */
	public String getEditButtonToolTipOnSavedSearchTab(String searchText) throws Exception {
		String editButtonText = getToolTipText(By.xpath("//a[text()='"+searchText+"']//parent::p//following::button[@title='Edit']"),"title");
		return editButtonText;
	}
	
	/**
	 * This method return Remove Button ToolTip text On Saved Search Tab
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 03/08/2023
	 */
	public String getRemoveButtonToolTipOnSavedSearchTab(String searchText) throws Exception {
		String removeButtonText = getToolTipText(By.xpath("//a[text()='"+searchText+"']//parent::p//following::button[@title='Remove']"),"title");
		return removeButtonText;
	}
	
	/**
	 * This method return default page size filter(dropdown) value On Saved Search Tab
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 06/08/2023
	 */
	public String getDefaultPageSizeDropdownValueSavedSearchTab() throws Exception {
	String defaultValue=getDefaultDropDownValue(pageSizeDropDown);
	return defaultValue;
	}
	
	
//	public boolean verifyPageSizeFilterResult(int pagesize) throws Exception {
//		if(pagesize>=this.getSaveSearchesAllTitlesOnSavedSearchesTab().size()){
//			return true;
//		}
//		else 
//			return false;
//	}
//	
	/**
	 * This method used to Convert string to Int 
	 * @return int
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/08/2023
	 */
	public int convertStringToInt(String result) {
		int resultCount=Integer.parseInt(result);
		return resultCount;
	}
	
	/**
	 * This method used to click on Title of Saved search result on Saved Searches Tab
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/08/2023
	 */
	public void clickOnTitleOnSavedSearchesTab(String title) throws Exception {
		WebElement titleEle =driver.findElement(By.xpath("//button[text()='Delete All']//following::a[text()='"+title+"']"));
		clickOnElement(titleEle,"Clicking on Title on Saved Searches Tab");
	}
	
	/**
	 * This method return title text On Saved Search Tab
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/08/2023
	 */
	public String getTitleNameOnSavedSearchesTab(String title) throws Exception {
		WebElement titleEle =driver.findElement(By.xpath("//button[text()='Delete All']//following::a[text()='"+title+"']"));
		String titleName=getTextFromElement(titleEle);
		return titleName;
		}
	
	/**
	 * This method used to check the pagination element is present on Saved Searches tab 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/08/2023
	 */
	public boolean verifyPagInationExist(int i) throws Exception {
		WebElement paginationEle=driver.findElement(By.xpath("//span[text()='Page:']//following-sibling::ul//li[text()='"+i+"']"));
		return isElementPresentWithoutLog(paginationEle);
	}
	
	
	@FindBy(xpath="//div[@role]//button[contains(text(),'Saved Searches')]")
	private WebElement savedSearchesTab;
	@FindBy(xpath="//button[contains(text(),'Saved Searches')]//following::button[text()='Delete All']")
	private WebElement deleteAllButton;
	@FindBy (xpath="//button[contains(text(),'Saved Searches')]//parent::div//following::div//select[@id='sortOrder']")
	private WebElement sortByDropDown;
	@FindBy (xpath="//button[contains(text(),'Saved Searches')]//parent::div//following::div//select[@id='pageSize']")
	private WebElement pageSizeDropDown;
	@FindBy (xpath="//button[text()='Cancel']//preceding::div[contains(text(),'Are you sure you wish to delete all saved searches')]")
	private WebElement deleteAllPopupHeaderLabel;
	@FindBy (xpath="//div[contains(text(),'Are you sure you wish to delete all saved searches')]//following::button[text()='Cancel']")
	private WebElement cancelButtonOnDeleteAllpopup;
	@FindBy (xpath="//div[contains(text(),'Are you sure you wish to delete all saved searches')]//following::button[text()='Continue']")
	private WebElement continueButtonOnDeleteAllpopup;
	@FindBy (xpath="//p[text()='Notes']//preceding-sibling::p[text()='Title']")
	private WebElement TitleLabelOnEditPopupOn;
	@FindBy (xpath="//input[@type='checkbox']//following-sibling::span[contains(text(),'Notify me via email of new results from this search')]")
	private WebElement NotifyMeViaEmailLabelwithCheckBoxOnEditPopUp;
	@FindBy (xpath="//p[text()='Title']//following-sibling::p[text()='Notes']")
	private WebElement notesLabelOnEditPopUp;
	@FindBy (xpath="//p[text()='Title']//parent::div//following::button[text()='Cancel']")
	private WebElement cancelButtonOnEditPopUp;
	@FindBy (xpath="//p[text()='Title']//parent::div//following::button[text()='Save']")
	private WebElement saveButtonOnEditPopUp;
	@FindBy (xpath="//p[text()='Title']//following-sibling::input")
	private WebElement titleTextBoxfieldOnEditPopUp;
	@FindBy(xpath="//div[text()='Are you sure you wish to delete this saved search?']")
	private WebElement headerOnRemovePopup;
	@FindBy (xpath="//div[text()='Are you sure you wish to delete this saved search?']//following::button[text()='Cancel']")
	private WebElement cancelButtonOnRemovePopUp;
	@FindBy (xpath="//div[text()='Are you sure you wish to delete this saved search?']//following::button[text()='Continue']")
	private WebElement continueButtonOnRemovePopUp;
	
	
	
}
