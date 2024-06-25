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

public class AddCiteddByTest extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String url = "";
	private String testCaseId;
	private String mainWindow;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"CitationLink" }, enabled = true, retryAnalyzer = Retry.class, description = "104  - Verify that the Add Cited By Functionality")
	@Story("EPIC-2692")
	public void VerifyThattheAddCitedByFunctionality() throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			String application = BaseTest.properties.getProperty("application");
			url = BaseTest.properties.getProperty(application);
			String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
			navigateToUrlLink(url);
			JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);

			// By using query param for ASTMH Instance
			String URL = basePage.getURLFromWebPage();
			driver.get(URL + "view/journals/tpmd/103/1/article-p69.xml");

			// By using Search opearation
			// masterPage.clickOnSearchMagnifyingLense();
			// browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
			// String expectedNumberOfCItation
			// =testData.get("numbercitationele").toString();

			// Verifying the Number of Citation element is present below the content on
			// Article page
			mainWindow = Helper.INSTANCE.getWindow(driver);
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					articleCitationPage.getNumberOfCitationTextOnArticlePae().trim(),
					testData.get("numbercitationele").toString(),
					"Verifying the Number of Citation element is present below the content on Article page");
			int totalCitationNumber = articleCitationPage.getCountOfNumberOfCitationOnArticlePage();
			// Verifying the Cited By element is present on Article page
			if (articleCitationPage.getCountOfNumberOfCitationOnArticlePage() > 0) {
				BaseTest.assertEquals(WebDriverManager.getDriver(),
						articleCitationPage.getCitedByTextOnArticlePage().trim(), testData.get("citedby").toString(),
						"Verifying the Cited By element is present on Article page");
				articleCitationPage.clickOnCitedByElementOnArticlePage();

				// Verifying the not provided ascending number before the each citation
				BaseTest.assertEquals(WebDriverManager.getDriver(),
						articleCitationPage.verifyCitationIndexIsNotPresentInCitedByOnArticlePage(), true,
						"Verifying the ascending number before the each citation is not provided");

				// Verifying pagination style will be a string of numbers, matching search
				// pagination.
				if (articleCitationPage.verifyPaginationStyleWillBeAStringOfNumbersInCitedByOnArticlePage() == true) {
					BaseTest.assertEquals(WebDriverManager.getDriver(),
							articleCitationPage.verifyPaginationStyleWillBeAStringOfNumbersInCitedByOnArticlePage(),
							true, "Verifying the pagination is a string of numbers, matching search pagination.");
					articleCitationPage.clickOnSecondPaginationLinkInCitedByElementOnArticlePage();
					int totalResultDiplayedInInCitedByElemen = articleCitationPage
							.getAppropriateCitationResultCountInCitedByElementOnArticlePage();
					String defaultText = articleCitationPage.getDefaultYouAreLookingForTextInCitedByElement();
					int startNumber = articleCitationPage
							.getStartingAndEndingResultTextOfYouAreLookingInCitedByElement(1);
					int endNumber = articleCitationPage.getStartingAndEndingResultTextOfYouAreLookingInCitedByElement(
							totalResultDiplayedInInCitedByElemen);
					BaseTest.assertEquals(WebDriverManager.getDriver(), defaultText,
							"You are looking at " + startNumber + "-" + endNumber + " of " + totalCitationNumber
									+ "citations",
							"Verifying if the users clicks on the Pagination links, its shows the appropriate results.");

					// Verifying pagination should be displayed on the top and the bottom of the
					// component.
					BaseTest.assertEquals(WebDriverManager.getDriver(),
							articleCitationPage.verifyPagInationInTopInCitedByElementIsPresentOnArticlePage(), true,
							"Verifying Verifying the pagInation in top of cited by element is present on article page.");
					BaseTest.assertEquals(WebDriverManager.getDriver(),
							articleCitationPage.verifyPagInationInBottomInCitedByElementIsPresentOnArticlePage(), true,
							"Verifying the pagInation in bottom of cited by element is present on article page.");
				}

				// Verifying the citation will have a “CrossRef” link below each
				BaseTest.assertEquals(WebDriverManager.getDriver(),
						articleCitationPage.getCrossRefTextInCitedByElement(), testData.get("crossref").toString(),
						"Verifying the crossref link is present below each citation result in cited by element on article page");

				// Verify if the user clicks on the crossref link, it should be redirect the DOI
				// user to the citing article in a new browser tab.
				articleCitationPage.clickOnCrossRefLinkInCitedByElementOnArticlePage();
				Helper.INSTANCE.switchToWindowTab(1);
				BaseTest.assertEquals(WebDriverManager.getDriver(),
						articleCitationPage.verifyDOIUserIsPresentInNewTab(), true,
						"Verifying if the user clicks on the crossref link, it should be redirect the DOI user to the citing article in a new browser tab.");
				Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
				Helper.INSTANCE.switchToWindowTab(0);

				// Verifying the "You are looking at start result number - end result number of
				// total result " should be displayed in "Cited by" component.
				int totalResultDiplayedInInCitedBy = articleCitationPage
						.getAppropriateCitationResultCountInCitedByElementOnArticlePage();
				String defaultText = articleCitationPage.getDefaultYouAreLookingForTextInCitedByElement();
				int startNumberdefault = articleCitationPage
						.getStartingAndEndingResultTextOfYouAreLookingInCitedByElement(1);
				int endNumberDefault = articleCitationPage
						.getStartingAndEndingResultTextOfYouAreLookingInCitedByElement(totalResultDiplayedInInCitedBy);
				BaseTest.assertEquals(WebDriverManager.getDriver(), defaultText,
						"You are looking at " + startNumberdefault + "-" + endNumberDefault + " of "
								+ totalCitationNumber + "citations",
						"Verifying the You are looking at " + startNumberdefault + " -" + endNumberDefault + " of "
								+ totalCitationNumber + " citation is displayed in Cited by component");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);
		}
	}

}
