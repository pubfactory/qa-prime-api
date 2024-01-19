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

public class DOITest extends BaseTest {

	private MasterPage masterPage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String url = "";
	private String testCaseId;
	private String mainWindowDOI;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"Citation" }, enabled = true, retryAnalyzer = Retry.class, description = "88  - Verify that DOI meta data should be link")
	@Story("EPIC-1403")
	public void verifyThatMetaDataShouldBeLink() throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			String application = BaseTest.properties.getProperty("application");
			// String application = System.getProperty("application");
			url = BaseTest.properties.getProperty(application);
			System.out.println("!url=" + url);
			String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
			navigateToUrlLink(url);
			JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			masterPage.clickOnSearchMagnifyingLense();
			browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
			browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			String DOIArticle = articleCitationPage.getDOIMetaDataOnArticlePage();
			mainWindowDOI = WebDriverManager.getDriver().getWindowHandle();
			BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyDOIMetaDataIsLink(), true,
					"Verifying the DOI META data is Link or not ");
			articleCitationPage.clickOnDOIMETADataOnArticlePage();
			Helper.INSTANCE.switchToWindowTab(WebDriverManager.getDriver(), mainWindowDOI);
			BaseTest.waitUntilTheURLGetLoads("meridian");
			String DOIAllen = articleCitationPage.getDOIMetaDataOnMeridianAllenPressSite();
			BaseTest.assertEquals(WebDriverManager.getDriver(), "https://doi.org/" + DOIArticle, DOIAllen,
					"Verifying the DOI META data is Link or not ");
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindowDOI, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);

		}
	}
}
