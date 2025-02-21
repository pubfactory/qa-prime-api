package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.PDFPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class AddMetricTest extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String url = "";
	private String testCaseId;
	private String mainWindow;
	private PDFPage pdfPage;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"proxy" }, enabled = true, retryAnalyzer = Retry.class, description = "105  - Verify that the open URL Route functionality")
	@Story("EPIC-1137")
	public void VerifyThatAddMetricFunctionalityFunctionality() throws Exception {

		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		String addMetricurl = testData.get("addmetricurl").toString();
		driver.get(url + addMetricurl);
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyArticleMetricsTableBarIsPresent(),
				true, "Verifying the table bar article metric is present.");
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyArticleMetricsPieChartIsPresent(),
				true, "Verifying the pie chart article metric is present.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getTextForAllTimeColumnOfTableBarArticleMetrics(),
				testData.get("alltimetext").toString(), "Verifying the All time column of table bar article metric is present.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getTextForPastYearColumnOfTableBarArticleMetrics(),
				testData.get("pastyeartext").toString(), "Verifying the past year column of table bar article metric is present.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getTextForPast30DaysColumnOfTableBarArticleMetrics(),
				testData.get("past30daystext").toString(), "Verifying the past 30 days column of table bar article metric is present.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getTextForFullTextRowOfTableBarArticleMetrics(),
				testData.get("fulltexttext").toString(), "Verifying the Fulltext row of table bar article metric is present.");
		
		BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getTextForPDFDownloadsRowOfTableBarArticleMetrics(),
				testData.get("pdfdownloadtext").toString(), "Verifying the PDF downlads row of table bar article metric is present.");
		
		articleCitationPage.clickOnFullTextOrAbstractTabOnArticlePage();
		Thread.sleep(5000);
		
		
		
		
//		pdfPage.clickOnDownloadPDFButtonOnArticlePage();
//		driver.navigate().refresh();
//		Thread.sleep(50000);

	}
}
