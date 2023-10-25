package com.prime.tests.FPJ;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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

public class ToCFunctionalityTest extends BaseTest{
	private MasterPage masterPage;
	private BasePage basePage;
	private BrowseOrSearchPage browseOrSearchPage;
	private ArticleCitationPage articleCitationPage;
	private SignInPage signInPage;
	private IssuePage issuePage;
	private JournalPage journalPage;
	private String url = "";

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"TOC Functionality" }, enabled = true, retryAnalyzer = Retry.class, description = "64 - Verify Journal ToC features")
	@Story("EPIC-427")
	@Parameters({ "testcaseid" })
	public void VerifyJournalToCFeatures(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
		journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);
		masterPage.clickOnViewThisIssueOnHomePage();
		issuePage.clickOnAllIssuesOnIssuePage();
		BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.getJournalPageHeaderText(),testData.get("journalpageheader").toString(), "Verifying the Journal Page header");
		BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyIssueToCIsPresentOnJournalPage(),true,"Verifying the Issue ToC is displayed on Journal Page header");
		BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyVolumeSectionIsDisplayed(testData.get("volumevalue").toString()), true,"Verifying the volume section is dipslayed on Journal Page header");
		journalPage.clickOnVolumeSectionOnJournalPage(testData.get("volumevalue").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyVolumeLinkExpandOnJournalPage(testData.get("volumevalue").toString()), true,"Verifying Volume link are Expanded on Journal page");
		journalPage.clickOnVolumeSectionOnJournalPage(testData.get("volumevalue").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyVolumeLinkCollapseOnJournalPage(testData.get("volumevalue").toString()), true,"Verifying Volume link are collapse on Journal page");
		journalPage.clickOnVolumeSectionOnJournalPage(testData.get("volumevalue").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyIssueIsDisplayedUnderVolumeSectionOnJornalPage(testData.get("volumevalue").toString()), true,"Verifying Issue is displayed under Volume section on Journal page");
		journalPage.clickOnIssueUnderVolumeSectionOnJornalPage(testData.get("volumevalue").toString(),testData.get("issuevalue").toString());
		
		
	}
}
