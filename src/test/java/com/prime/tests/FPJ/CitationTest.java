package com.prime.tests.FPJ;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.prime.api.helpers.LoginServiceHelper;
import com.prime.api.helpers.SearchServiceHelper;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.JournalPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;

public class CitationTest extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String url = "";

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SignIn" }, enabled = true, retryAnalyzer = Retry.class, description = "55 - Verify that the cite button available on current content page and  Preview/Export citation pop up will be displayed when clicked on it")
	@Story("EPIC-971")
	@Parameters({ "testcaseid" })
	public void verifyCitationButonAvailableAndPreviewExportCitationPopUpWillBeDisplayedWhenClickedOnIt(
			@Optional String testCaseId) throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
			String application = BaseTest.properties.getProperty("application");
			url = BaseTest.properties.getProperty(application);
			System.out.println("!url=" + url);
			String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
			navigateToUrlLink(url);
			JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
			masterPage.clickOnSearchMagnifyingLense();
			browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
			browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			articleCitationPage.verifyCitationButtonPresentOnArticlePage();
			articleCitationPage.clickOnCitationButtonOnArticlePage();
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getPreviewExportCitationPopUpHeaderText(),
					testData.get("popupheader").toString(), "Verifying the Preview Export Citation popup is display");
			articleCitationPage
					.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
			articleCitationPage
					.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());
			articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup();
			articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup();
			articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup();
			articleCitationPage.clickOnRISExportCitationFormat();
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getLatestDownloadFileRelatedToCitation(),
					testData.get("risbuttonformat").toString(),
					"Verifying the file " + (testData.get("risbuttonformat").toString()) + " is downloaded");
			articleCitationPage.clickOnBIBExportCitationFormat();
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getLatestDownloadFileRelatedToCitation(),
					testData.get("bibbuttonformat").toString(),
					"Verifying the file " + (testData.get("bibbuttonformat").toString()) + " is downloaded");
			articleCitationPage.clickOnENWExportCitationFormat();
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getLatestDownloadFileRelatedToCitation(),
					testData.get("enwbuttonformat").toString(),
					"Verifying the file " + (testData.get("enwbuttonformat").toString()) + " is downloaded");
			List<String> expRISCitationLabels = Arrays.asList(testData.get("risbuttonlabel").toString().split(","));
			List<String> expBIBCitationLabels = Arrays.asList(testData.get("bibbuttonlabel").toString().split(","));
			List<String> expENWCitationLabels = Arrays.asList(testData.get("enwbuttonlabel").toString().split(","));
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(),
					expRISCitationLabels.toString(), "Verifying the RIS button Labels on Export Ciatation Popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(),
					expBIBCitationLabels.toString(), "Verifying the BIB button Labels on Export Ciatation Popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(),
					expENWCitationLabels.toString(), "Verifying the ENW button Labels on Export Ciatation Popup");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingAMAFormat(),
					testData.get("abbreviatedtitle").toString(),
					"Verifying the Abbreviated Journal Title is Displayed on Preview Export Citation PopUp");
			articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseTest.deleteDonwloadedFile();
		}
	}

}
