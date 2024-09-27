package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class AutoLaunchNewEmailTest extends BaseTest {

	private MasterPage masterPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private ArticleCitationPage articleCitationPage;
	private String url = "";
	private String testCaseId;

	// Created for PRIME-3436
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"anesthesiaprogress" }, enabled = true, retryAnalyzer = Retry.class, description = "1730120 - verify that the Auto Launch new Email functionality")
	@Story("Feature-2937")
	public void verifyThatTheAutoLaunchNewEmailFunctionality() throws Exception {

		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		masterPage.clickOnSearchMagnifyingLense();
		browseOrSearchPage
				.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("openaccess").toString());
		browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
		articleCitationPage.clickOnArticleInformationTabOnArticlePage();

		// Verifying the Email address is present under contributor Notes section in Article
		// information tab on Article Page
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				articleCitationPage.verifyEmailElementIsPresentUnderArticleInformationTabOnArticlePage(), true, "Verifying the Email address is present under contributor Notes section in Article information tab Article Page");
		
		// Verifying the Email address is hyper link
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				articleCitationPage.verifyEmailAddressIsHyperLinkUnderArticleInformationTab(),true, "Verying the Email address is hyper link under Article Information tab on article page");

		//articleCitationPage.clickOnEmailAddressUnderInformationTabOnArticlePage();
		
	}
}
