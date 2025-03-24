package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SourceFilterJournalTest extends BaseTest {
	private MasterPage masterPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private String testCaseId;
	private String url = "";

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"Source flter journal" }, enabled = true, retryAnalyzer = Retry.class, description = "1729970 - Verify that the By Journal filter functionality")
	@Story("EPIC-2399")

	public void VerifyThatTheByJournalFilterFunctionality() throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		masterPage.clickOnSearchMagnifyingLense();

		// Verifying the Journal filter is present on search page
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getJournalFiterText(),
				testData.get("journalfiltertext").toString(), "Verifying the Journal filter is present on search page");

		//Verifying the more than one journal are present in the journal filter
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.VerifyMoreThanOneJournalISPresent(), true, "Verifying the more than one journal are present in the journal filter");
		
		// verifying journal title from journal filter will NOT be a region.
		masterPage.clickOnDevToolSetting();
		masterPage.clickOnRegionKeySetting();
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getJournalFiterFirstvalueText(testData.get("journalname").toString()),
				testData.get("journalname").toString(),
				"verifying journal title from journal filter is not a region");

		masterPage.clickOnDevToolSetting();
		masterPage.clickOnRegionKeySetting();

		// Verify the count is present in front of journal in journal filter
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				browseOrSearchPage.verifyCountIsPresntInFrontOfJournalTitleInJournalFilter(), true,
				"Verifying the count is present in front of journal in journal filter");

		// Verify the By Journal filter Search Slug Value is present on Search Or Browse
		// Page
		browseOrSearchPage.clickOnFirstJournalFilterValueFromByJournalFilterOnBrowseOrSearchPage(testData.get("journalname").toString());
		browseOrSearchPage.getFirstJournalAttributeValue();
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				browseOrSearchPage.verifyByJournalFilterSearchSlugValueIsPresentOnSearchOrBrowsePage("By Journal",
						browseOrSearchPage.getFirstJournalAttributeValue()),
				true,
				"Verifying the By Journal filter Search Slug Value is present on Search Or Browse Page after clicking on journal filter value");

		// Verify when the user clicks on the journal title from the journal filter,
		// which returns only results that are a child of the clicked title.
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				browseOrSearchPage.getNumberOfFilteredResultsFrontOfJournalFilterValueOnBrowseOrSearchPage(),
				browseOrSearchPage.getTotatResultOnBrowseOrSearchPage(),
				"Verifying when the user clicks on the journal title from the journal filter, which returns only results that are a child of the clicked title.");

		// Verify search slug label is region
			masterPage.clickOnDevToolSetting();
			masterPage.clickOnRegionKeySetting();
		BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifySearchSlugLabelIsRegion(testData.get("regionlabeljournal").toString()), true,
				"Verify search slug label is region after applying the region key setting");

	}
}