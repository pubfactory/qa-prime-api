package com.prime.tests.FPJ;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.prime.api.helpers.SearchServiceHelper;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class IntegrationE2EFlow extends BaseTest {
	private MasterPage masterPage;
	private SignInPage signInPage;
	private BasePage basePage;
	private BrowseOrSearchPage browseOrSearchPage;
	private SearchServiceHelper searchServiceHelper;
	private int totalResultsFromAPI;
	private int totalResultsFromWebPage;
	Response response;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"SignIn" }, enabled = true, retryAnalyzer = Retry.class, description = "7 - Verify that Login(SIGN IN) link should available in header")
	@Story("EPIC-971")
	@Parameters({ "testcaseid" })
	public void verifyThatUserAbleToLaunchApplication(@Optional String testCaseId) throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
		String application = BaseTest.properties.getProperty("application");
		System.out.println("url=" + BaseTest.properties.getProperty(application));
		navigateToUrl(BaseTest.properties.getProperty("application"));
		JSONObject testData = getDetails(testCaseId);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(), basePage.getTitleFromWebPage(),
				testData.get("title").toString(), "Verifying the page title ");
		masterPage.clickOnSearchMagnifyingLense();
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBrowsePageLabelText(),
				testData.get("browsetext").toString(), "Verifying the Browse page title");
		totalResultsFromWebPage = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		System.out.println("Web COUNT=" + totalResultsFromWebPage);
		searchServiceHelper = new SearchServiceHelper();
		response = searchServiceHelper.fetchSearchResults(platform, application, status);
		JsonPath js = new JsonPath(response.asString());
		totalResultsFromAPI = Integer.parseInt(js.get("pagination.totalResults").toString());
		BaseTest.assertEquals(WebDriverManager.getDriver(), totalResultsFromAPI, totalResultsFromWebPage,
				"Total Search results from api and webpage mismatching");

	}

}
