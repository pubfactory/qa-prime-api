package com.prime.tests.E2E;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ArticleCitationTest extends BaseTest{
	
	private ArticleCitationPage articleCitationPage;
	private MasterPage masterPage;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"Citation"},enabled = true,retryAnalyzer = Retry.class, description = "1 - Verify that the cite button available on current content page and  Preview/Export citation pop up will be displayed when clicked on it")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyCitationButonAvailableAndPreviewExportCitationPopUpWillBeDisplayedWhenClickedOnIt(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
		masterPage.clickOnSearchMagnifyingLense();
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(),ArticleCitationPage.class);
		articleCitationPage.clickArticleOnArticlePagewrtContentType(testData.get("accesstype").toString());   //Restricted Access
		articleCitationPage.verifyCitationButtonPresentOnArticlePage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getCitationButtonToolTip(), testData.get("tooltiptext").toString(), "Comparing the actual tootipText and expected ToolTipText");
		articleCitationPage.clickOnCitationButtonOnArticlePage();
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(), "Checking the Preview Export Citation popup is display");
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"Citation"},enabled = true,retryAnalyzer = Retry.class, description = "2 - Verify that the 'X' link or cancel button on Preview/Export citation pop up are visible to user and it will be closed when user clicked anywhere outside the pop up or click on 'X' link or cancel button")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyXLinkOrCancelButtonOnPreviewExportCitationPopupAreVisibleToUserAndItWillBeClosedWhenUserClickedAnywhereOutsideThePopupOrClickOnXLinkOrCancelButton (@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		//navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(),MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
		masterPage.clickOnSearchMagnifyingLense();
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(),ArticleCitationPage.class);
		articleCitationPage.clickArticleOnArticlePagewrtContentType(testData.get("accesstype").toString()) ;  //Open Access
		articleCitationPage.verifyCitationButtonPresentOnArticlePage();
		articleCitationPage.clickOnCitationButtonOnArticlePage();
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(), "Checking the Preview Export Citation popup is display");
		articleCitationPage.verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp();
		articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"Citation","Search"},enabled = true,retryAnalyzer = Retry.class, description = "3 - verify that the user able to select AMA and APA Citation format In Preview Export citation Popup For open access content")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	 public void verifyThatTheUserAbleToSelectAMAandAPACitationFormatInPreviewExportCitationPopupForOpenAccessContent(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		//navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
		masterPage.clickOnSearchMagnifyingLense();
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		articleCitationPage.clickArticleOnArticlePagewrtContentType(testData.get("accesstype").toString());
		articleCitationPage.clickOnCitationButtonOnArticlePage();
		articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
		articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());
	 }

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"Citation","Search"},enabled = true,retryAnalyzer = Retry.class, description = "4 - Verify that the user able to see .RIS, .BIB, .ENW Export citation button and user Export the citations in .RIS, .BIB, .ENW format in appropriate folder location or default download folder  location")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyThatTheUserAbleToSeeRISBIBENWExportCitationButtonAndUserExportTheCitationsInRISBIBENWFormatInAppropriateFolderLocationOrDefaultDownloadFolderLocation(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		//navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
		masterPage.clickOnSearchMagnifyingLense();
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		articleCitationPage.clickArticleOnArticlePagewrtContentType(testData.get("accesstype").toString());
			articleCitationPage.clickOnCitationButtonOnArticlePage();
		articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup();
		articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup();
		articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup();
		articleCitationPage.clickOnRISExportCitationFormat();
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getLatestDownloadFileRelatedToCitation(), testData.get("risbutton").toString(), "checking the file "+(testData.get("risbutton").toString())+ " is downloaded");
		articleCitationPage.clickOnBIBExportCitationFormat();
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getLatestDownloadFileRelatedToCitation(), testData.get("bibbutton").toString(), "checking the file "+(testData.get("bibbutton").toString())+ " is downloaded");
		articleCitationPage.clickOnENWExportCitationFormat();
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getLatestDownloadFileRelatedToCitation(), testData.get("enwbutton").toString(), "checking the file "+(testData.get("enwbutton").toString())+ " is downloaded");
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {"Citation","Search"},enabled = true,retryAnalyzer = Retry.class, description = "5 - verify The Export Citation Format Label on Citation Pop up")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyTheExportCitationFormatLabelOnCitationPopup(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		//navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
		masterPage.clickOnSearchMagnifyingLense();
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);		
		articleCitationPage.clickArticleOnArticlePagewrtContentType(testData.get("accesstype").toString());
		articleCitationPage.clickOnCitationButtonOnArticlePage();
		List<String> expRISCitationLabels = Arrays.asList(testData.get("risbuttonlabel").toString().split(","));
		List<String> expBIBCitationLabels = Arrays.asList(testData.get("bibbuttonlabel").toString().split(","));
		List<String> expENWCitationLabels = Arrays.asList(testData.get("enwbuttonlabel").toString().split(","));
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(),expRISCitationLabels.toString(), "Verifying the RIS button Labels on Export Ciatation Popup");
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(),expBIBCitationLabels.toString(), "Verifying the BIB button Labels on Export Ciatation Popup");
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(),expENWCitationLabels.toString(), "Verifying the ENW button Labels on Export Ciatation Popup");
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = "Citation",enabled = true,retryAnalyzer = Retry.class, description = "6 - verify The Abbreviated Journal Title Display On Export Citation Popup when AMA format seleted")
	@Story("EPIC-971")
	@Parameters({"testcaseid"})
	public void verifyTheAbbreviatedJournalTitleDisplayOnExportCitationPopupWhenAMAFormatSelected(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		//navigateToUrl(BaseTest.properties.getProperty("APPURL"));
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
		masterPage.clickOnSearchMagnifyingLense();
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		articleCitationPage.clickArticleOnArticlePagewrtContentType(testData.get("accesstype").toString());
		articleCitationPage.clickOnCitationButtonOnArticlePage();
		articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());  // May be in future Abbreviated title data could be change
		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingAMAFormat(),testData.get("abbreviatedtitle").toString(),"Checking the Abbreviated Journal Title is Displayed on Preview Export Citation PopUp");
	}
//	
//	@Severity(SeverityLevel.BLOCKER)
//	@Test(groups = {"CitationGroup"},enabled = true,retryAnalyzer = Retry.class, description = "7 - Verify that the cite button available on current content page and  Preview/Export citation pop up will be displayed when clicked on it")
//	@Story("EPIC-971")
//	@Parameters({"testcaseid"})
//	public void verifyCitationButonAvailableAndPreviewExportCitationPopUpWillBeDisplayedWhenClickedOnItOnHOmePageArticle(@Optional String testCaseId) throws Exception {
//		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
//		WebDriverManager.setTestcaseIdTestRail(testCaseId);
//		navigateToUrl(BaseTest.properties.getProperty("APPURL"));
//		JSONObject testData = getDetails(testCaseId);
//		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
//		masterPage.clickOnArticleUnderContentListOnHomePage();
//		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
//		articleCitationPage.verifyCitationButtonPresentOnArticlePage();
//		BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.getCitationButtonToolTip(), testData.get("tooltiptext").toString(), "Comparing the actual tootipText and expected ToolTipText");
//		articleCitationPage.clickOnCitationButtonOnArticlePage();
//		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(), "Checking the Preview Export Citation popup is display");
//	}
}
