package com.prime.tests.E2E;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SaveSearchPage;
import com.prime.pageFactory.pages.fpj.SaveSearchTabPage;
import com.prime.pageFactory.pages.fpj.SearchPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SavedSearchTest extends BaseTest {
	MasterPage masterPage;
	SaveSearchPage saveSearchPage;
	SignInPage signInPage;
	SaveSearchTabPage saveSearchTabPage;
	JSONObject testData;
	SearchPage searchPage;
	private boolean exist;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "13 - Verify that the Save Button is visible on Search result page")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void VerifyTheSaveButtonIsVisibledOnSearchResultPage(@Optional String testCaseId) throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			navigateToUrl(BaseTest.properties.getProperty("APPURL"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
			masterPage.clickOnSearchMagnifyingLense();
			saveSearchPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchPage.class);
			saveSearchPage.verifySaveSearchbuttonPresentOnSaveSearchPopup();
			masterPage.clickOnsignInlinkOnHeader();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			saveSearchPage.clickOnSaveSearchResultButton();
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchPage.getHeaderTextOnSaveSearchPopup(),
					testData.get("savedsearchpopupheader").toString(), "Verifying the Header Text on Save search popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					saveSearchPage.getsaveThisSearchLabelTextonSaveSearchPopup(),
					testData.get("savethissearchlabel").toString(),
					"Verifying the save this search label Text on Save search popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchPage.getNotifyMeViaEmailOnSaveSearchPopup(),
					testData.get("notifymeviaemaillabel").toString(),
					"Verifying the Notify me via email label Text on Save search popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					saveSearchPage.getTitleForSearchLabelTextonSaveSearchPopup(),
					testData.get("titleforsearch").toString(), "Verifying the Title label Text on Save search popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchPage.getNotesLabelTextonSaveSearchPopup(),
					testData.get("notes").toString(), "Verifying the Notes label Text on Save search popup");
			saveSearchPage.verifySaveButtonOnSaveSearchPopup();
			saveSearchPage.verifyCancelButtonOnSaveSearchPopup();
			saveSearchPage.clickOnSaveButtonOnSaveSearchPopup();
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage.verifySavedSearchesTabPresentInMyStuffLink();
			saveSearchTabPage.clickOnSavedSearchesTab();
			saveSearchTabPage.verifyTitleNamePresentOnSavedSearchedResult(
					saveSearchTabPage.getSaveSearchesAllTitlesOnSavedSearchesTab(), testData.get("title").toString(),
					"Verifying the Saved searches Title is present on Saved searches Tab");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			saveSearchTabPage.clickOnRemoveButtonOnSavedSearchesTab(testData.get("searchtext").toString());
			saveSearchTabPage.clickOnContinueButtonOnRemovePopupOnSavedSearchesTab();
		}
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "14 - Verify that Saved Searches tab contains all the saved searches with filter")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void verifyThatSavedSearchesTabContainsAllTheSavedSearchesWithFilters(@Optional String testCaseId)
			throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
//			navigateToUrl(BaseTest.properties.getProperty("APPURL"));
			navigateToUrl(BaseTest.properties.getProperty("application"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
			masterPage.clickOnSearchMagnifyingLense();
			saveSearchPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchPage.class);
			saveSearchPage.clickOnSaveSearchResultButton();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			saveSearchPage.clickOnSaveSearchResultButton();
			saveSearchPage.clickOnSaveButtonOnSaveSearchPopup();
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			saveSearchTabPage.clickOnSavedSearchesTab();
			saveSearchTabPage.verifysortByDropDownPresentOnSavedSearchesTab();
			saveSearchTabPage.verifyPageSizeDropDownPresentOnSavedSearchesTab();
			saveSearchTabPage.verifyDeleteAllButtonPresentOnSavedSearchesTab();
			saveSearchTabPage.verifyEditButtonPresentOnSavedSearchesTab(testData.get("searchtext").toString());
			saveSearchTabPage.verifyRemoveButtonPresentOnSavedSearchesTab(testData.get("searchtext").toString());
			saveSearchTabPage.verifyTitlePresentOnSavedSearchesTab(testData.get("searchtext").toString());
			saveSearchTabPage.verifyTitleTimeDatePresentOnSavedSearchesTab(testData.get("searchtext").toString());
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			saveSearchTabPage.clickOnRemoveButtonOnSavedSearchesTab(testData.get("searchtext").toString());
			saveSearchTabPage.clickOnContinueButtonOnRemovePopupOnSavedSearchesTab();
		}
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "15 - Verify that Save searches contains options Edit, delete and also verify the tooltip of Edit and delete And edit popup is displayed when clicked on Edit link for saved search")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void VerifySaveSearchesContainsOptionsEditRemoveAndVerifyTheTooltipofEditandRemoveOnsavedSearchTab(
			@Optional String testCaseId) throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			//navigateToUrl(BaseTest.properties.getProperty("APPURL"));
			navigateToUrl(BaseTest.properties.getProperty("application"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
			masterPage.clickOnSearchMagnifyingLense();
			saveSearchPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchPage.class);
			saveSearchPage.clickOnSaveSearchResultButton();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			saveSearchPage.clickOnSaveSearchResultButton();
			saveSearchPage.clickOnSaveButtonOnSaveSearchPopup();
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			saveSearchTabPage.clickOnSavedSearchesTab();
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					saveSearchTabPage.getEditButtonToolTipOnSavedSearchTab(testData.get("searchtext").toString()),
					testData.get("editbtntooltip").toString(), "Verifying the tooltip of Edit button on Saved Searhes tab");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					saveSearchTabPage.getRemoveButtonToolTipOnSavedSearchTab(testData.get("searchtext").toString()),
					testData.get("removebtntooltip").toString(), "Assertinng the tooltip of Remove button on Saved Searhes tab");
			saveSearchTabPage.clickOnEditButtonOnSavedSearchesTab(testData.get("searchtext").toString());
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getTitleLabelTextOnEditPopupOnSavedSearchesTab(),testData.get("titlelabel").toString(), "Verifying the Title label Text on Edit popup On Saved searched Tab");
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getNotifyMeViaEmailLabelTextOnEditPopupOnSavedSearchesTab(),testData.get("notifymeviaemaillabel").toString(), "Verifying the notify me via email label Text on Edit popup On Saved searched Tab");
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getNoteslLabelTextOnEditPopupOnSavedSearchesTab(),testData.get("noteslabel").toString(), "Verifying the notify me via email label Text on Edit popup On Saved searched Tab");
			saveSearchTabPage.verifyCancelButoonPresentOnEditPopUpOnSavedSearchesTab();
			saveSearchTabPage.verifySaveButtonPresentOnEditPopUpOnSavedSearchesTab();
			saveSearchTabPage.enterTitleOnEditPopUpOnSavedSearchesTab(testData.get("changetitle").toString());
			saveSearchTabPage.clickOnSaveButtonOnEditPopupOnSavedSearchesTab();
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getTitleNameOnSavedSearchesTab(testData.get("changetitle").toString()),testData.get("changetitle").toString(), "Verifying the Changed Title "+testData.get("changetitle").toString()+" is present on Saved searches Tab");
//			saveSearchTabPage.verifyTitleNamePresentOnSavedSearchedResult(
//					saveSearchTabPage.getSaveSearchesAllTitlesOnSavedSearchesTab(), testData.get("changetitle").toString(),
//					"Verifying the Changed Title "+testData.get("changetitle").toString()+" is present on Saved searches Tab");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			navigateToUrl(BaseTest.properties.getProperty("APPURL"));
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage.clickOnSavedSearchesTab();
			saveSearchTabPage.clickOnRemoveButtonOnSavedSearchesTab(testData.get("searchtext").toString());
			saveSearchTabPage.clickOnContinueButtonOnRemovePopupOnSavedSearchesTab();
		}
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "16 - Verify that Confirmation pop-up will display when click on delete or delete all option")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void VerifyThatConfirmationPopupWillDisplayWhenClickOnDeleteOrDeleteAllOption(@Optional String testCaseId) throws Exception {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			navigateToUrl(BaseTest.properties.getProperty("APPURL"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
			masterPage.clickOnSearchMagnifyingLense();
			saveSearchPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchPage.class);
			saveSearchPage.clickOnSaveSearchResultButton();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			saveSearchPage.clickOnSaveSearchResultButton();
			saveSearchPage.clickOnSaveButtonOnSaveSearchPopup();
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			saveSearchTabPage.clickOnSavedSearchesTab();
			saveSearchTabPage.clickOnRemoveButtonOnSavedSearchesTab(testData.get("searchtext").toString());
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getHeaderOnRemovePopupOnSavedSearchTab(), testData.get("headeronremovepopup").toString(),"Verifying the Header Label Text on Remove popup On Saved searched Tab");
			saveSearchTabPage.clickOnContinueButtonOnRemovePopupOnSavedSearchesTab();
			saveSearchTabPage.clickOnDeleteAllButtonOnSavedSearchesTab();
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getdeleteAllPopupHeaderLabelTextonSavedSearchesTab(),testData.get("headerondeleteallpopup").toString(),"Verifying popup label message post clicking delete all button on Saved searched Tab");
	}	
	
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "17 - Verify that Sort by dropdown is displayed with the filters in Saved Searches tab")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void VerifyThatSortByDropdownIsDisplayedWithTheFiltersInSavedSearchesTab(@Optional String testCaseId) throws Exception {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			navigateToUrl(BaseTest.properties.getProperty("application"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.clickOnsignInlinkOnHeader();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			saveSearchTabPage.clickOnSavedSearchesTab();
			List<String> sortByAllFilterValueList = Arrays.asList(testData.get("sortbyallfiltervalue").toString().split(","));
			List<String> PageSizeAllfilterValueList = Arrays.asList(testData.get("pagesizeallfiltervalue").toString().split(","));
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getSortByAllfilterValueOnSavedSearchesTab().toString(),sortByAllFilterValueList.toString(),"Verifying SortBy filter all values on Saved searched Tab");
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getPageSizeAllfilterValueOnSavedSearchesTab().toString(),PageSizeAllfilterValueList.toString(),"Verifying Page size filter all values on Saved searched Tab");
			saveSearchTabPage.selectsortValueFromSortByDropdownOnSavedSearchesTab(testData.get("titledesc").toString());
			List<String> titleDescList = Arrays.asList(testData.get("titledesclist").toString().split(","));
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getSaveSearchesAllTitlesOnSavedSearchesTab().toString(), titleDescList.toString(), "Verifying all Title got in desc format when select titledesc value in Sort by filter on Saved searched Tab");
			saveSearchTabPage.selectsortValueFromSortByDropdownOnSavedSearchesTab(testData.get("createdateasc").toString());
			List<String> createDateAscList = Arrays.asList(testData.get("createdateasclist").toString().split(","));
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getSaveSearchesAllSavedDateTimeOnSavedSearchesTab().toString(), createDateAscList.toString(), "Verifying all Title got in desc format when select titledesc value in Sort by filter on Saved searched Tab");
			saveSearchTabPage.selectsortValueFromSortByDropdownOnSavedSearchesTab(testData.get("createdatedesc").toString());
			List<String> createDateDescList = Arrays.asList(testData.get("createdatedesclist").toString().split(","));
			System.out.println("desc date :"+saveSearchTabPage.getSaveSearchesAllSavedDateTimeOnSavedSearchesTab());
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getSaveSearchesAllSavedDateTimeOnSavedSearchesTab().toString(), createDateDescList.toString(), "Verifying all Title got in desc format when select titledesc value in Sort by filter on Saved searched Tab");
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "18 - Verify that Page size dropdown is displayed with the filters in Saved Searches tab")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void VerifyThatPageSizeDropdownIsDisplayedWithTheFiltersSavedSearchesTab(@Optional String testCaseId) throws Exception {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			navigateToUrl(BaseTest.properties.getProperty("application"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.clickOnsignInlinkOnHeader();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			Thread.sleep(20000);
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			masterPage.clickOnMystuffLinkOnHeader();	
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			saveSearchTabPage.clickOnSavedSearchesTab();
			
			saveSearchTabPage.selectPageSizeValueFromPageSizeDropdownOnSavedSearchesTab(testData.get("selectmaxpagesizevalue").toString());
			int expectedPagInationSize=saveSearchTabPage.getPaginationSize(saveSearchTabPage.convertStringToInt(testData.get("selectmaxpagesizevalue").toString()));
			
			for(int i=1;i<=expectedPagInationSize;i++) {
				exist = saveSearchTabPage.verifyPagInationExist(i);
				BaseTest.assertTrue(WebDriverManager.getDriver(), exist, "Verifying pagination is available for "+i+" page number");
			}
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getpaginationsPageSizeNumberOnSavedSearchesTab(),String.valueOf(expectedPagInationSize), "Verifying total pagination size on Saved searched Tab");	

			List<String> PageSizeAllfilterValueList = Arrays.asList(testData.get("pagesizeallfiltervalue").toString().split(","));
			BaseTest.assertEquals(WebDriverManager.getDriver(), saveSearchTabPage.getPageSizeAllfilterValueOnSavedSearchesTab().toString(),PageSizeAllfilterValueList.toString(),"Verifying Page size filter all values on Saved searched Tab");
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getDefaultPageSizeDropdownValueSavedSearchTab(), testData.get("defaultpagesizeddvalue").toString(), "Verifying Page size filter default value on Saved searched Tab");
}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SavedSearches" }, enabled = true, retryAnalyzer = Retry.class, description = "19 - Verify that paginations are displayed when the Saved Searches  are more than the page size")
	@Story("EPIC-654")
	@Parameters({ "testcaseid" })
	public void VerifyThatPaginationsAreDisplayedWhenTheSavedSearchesAreMoreThanThePageSize(@Optional String testCaseId) throws Exception {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			navigateToUrl(BaseTest.properties.getProperty("application"));
			testData = getDetails(testCaseId);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.clickOnsignInlinkOnHeader();
			signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
			signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(
					testData.get("email").toString());
			signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("password").toString());
			signInPage.clickOnSUBMITButtonOnSIgnInPage();
			masterPage.clickOnMystuffLinkOnHeader();
			masterPage.clickOnMystuffLinkOnHeader();
			saveSearchTabPage = BasePage.initialize(WebDriverManager.getDriver(), SaveSearchTabPage.class);
			saveSearchTabPage.clickOnSavedSearchesTab();
			List<String> pagInationSize = Arrays.asList(testData.get("paginationsize").toString().split(","));
			BaseTest.assertEquals(WebDriverManager.getDriver(),saveSearchTabPage.getpaginationsPageSizeNumberOnSavedSearchesTab().toString(),pagInationSize.toString(), "Verifying page ination size on Saved searched Tab");	
			saveSearchTabPage.clickOnTitleOnSavedSearchesTab(testData.get("title").toString());
			searchPage = BasePage.initialize(WebDriverManager.getDriver(), SearchPage.class);
			System.out.println("Search actu : "+searchPage.getSearchResultTextOnSearchResultPage(testData.get("title").toString()));
			BaseTest.assertEquals(WebDriverManager.getDriver(),searchPage.getSearchResultTextOnSearchResultPage(testData.get("title").toString()),testData.get("searchresulttext").toString(), "Verifying the Search Result page post clicking on Title on Saved Searches Tab");
	}
}