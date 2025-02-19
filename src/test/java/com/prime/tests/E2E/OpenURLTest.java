package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class OpenURLTest extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String url = "";
	private String testCaseId;
	private String mainWindow;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"proxy" }, enabled = true, retryAnalyzer = Retry.class, description = "1736541  - Verify that the open URL Route functionality")
	@Story("EPIC-1137")
	public void VerifyThatOpenURLRouteFunctionality() throws Exception {

		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		String openURL = testData.get("openurl").toString();
		driver.get(url + openURL);
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getOpenURLPageHeader(),
				testData.get("openurlheader").toString(), "Verifying the Open URL route link is working.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), Helper.INSTANCE.VerifyListConainsSpecificString(browseOrSearchPage.getAllVolumeWithIssueForOpenURL(),testData.get("issueNumber").toString()),
				true, "Verifying the Open URL route result shows for the volume with proper issue 1.");
		
		browseOrSearchPage.clickOnissueSearchSlugForOpenURLROUTE();
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyOpenURLHeaderIsNotPresent(),
				true, "Verifying the Open URL header is not present after removed the search slug for issue.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getNoSearchResultsHeader(),
				testData.get("noresult").toString(), "Verifying the Open URL header is not present after clearing the search slug for issue.");
		
	
	
	}

}
