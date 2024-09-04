package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Story;

public class AdvancedSearchToRefineTermsAndDate extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private BrowseOrSearchPage browseOrSearchPage;
	private ArticleCitationPage articleCitationPage;
	private SignInPage signInPage;
	private IssuePage issuePage;
	private String url = "";
	private String testCaseId;

	@Test(groups = {
			"anesthesiaprogress" }, enabled = true, retryAnalyzer = Retry.class, description = "1730099 - Verify advanced Refine terms in the search results page")
	@Story("EPIC-237")

	public void VerifyAdvancedFilterInRefineTermsDateInTheSearchResultsPage() throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.clickOnSearchMagnifyingLense();
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		int browseResultCount = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		// Verify Add value button is present below the refine terms filter box

		BaseTest.assertTrue(driver, browseOrSearchPage.verifyAddValueIsPresentOnBrowseOrSearchPage(),
				"Verifying that add value under Refine terms is visible");

		// Adding a full text filter option to Refine Terms

		browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(
				testData.get("testidvalueselect").toString(), testData.get("refinefilteroptionfulltext").toString());
		System.out.println("test data =" + testData.get("testidvalueenter"));
		browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(
				testData.get("testidvalueenter").toString(), testData.get("refinefiltervaluefulltext1").toString());
		browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
		WebDriverManager.getDriver().navigate().refresh();
		int fullTextResults = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		System.out.println("fullTextResults" + fullTextResults);
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				BaseTest.compareBrowserResultsCount(browseResultCount, fullTextResults), true,
				"Verifying the total result count after applying the fulltext filter from refine term filter on search page");
		// Verifying if adv-field[0] and adv-value[0]

		String[] fulltext = testData.get("refinefiltervaluefulltext1").toString().split(" ");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(fulltext[0]),
				"Verifying the fulltext filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-field[0]"),
				"Verifying adv-field[0] is seen on search result URL");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-value[0]"),
				"Verifying adv-value[0] is seen on search result URL");

		System.out.println("Vierified adv-field[0] and adv-value[0]");

		// Verify When clicking on Add Value and new filter box is rendered with pretext
		// OR

		browseOrSearchPage.clickOnAddValueButtonInRefineTermDDOnBrowseOrSearchPage();
		BaseTest.assertTrue(driver, browseOrSearchPage.verifySecondFilterboxIsPresentAfterClickingOnAddValue(),
				"Verifying that Second filter under Refine terms is visible");

		// Verify if OR text is getting displayed before the second filter box.

		BaseTest.assertTrue(driver, browseOrSearchPage.verifyORtextIsPresentWithSecondFilterBox(),
				"Verifying that OR text under Refine terms is visible at the second filter box");

		// Verifying if OR filter is working with the second filter

		browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(
				testData.get("testidvalueenterforOR").toString(),
				testData.get("refinefiltervaluefulltext2").toString());
		browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
		WebDriverManager.getDriver().navigate().refresh();
		int fullTextResults2 = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		System.out.println("fullTextResults2" + fullTextResults2);
		// Verifying after the OR is applied, the search results have increased from the
		// first

		BaseTest.assertEquals(WebDriverManager.getDriver(),
				BaseTest.compareBrowserResultsCount(fullTextResults2, fullTextResults), true,
				"Verifying after the OR filter is applied, the search results have increased from the first on search page");
		String[] fulltext2 = testData.get("refinefiltervaluefulltext2").toString().split(" "); // In this case we can
																								// assume more than one
																								// word
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(fulltext2[0]),
				"Verifying the fulltext filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(fulltext[0]),
				"Verifying the fulltext filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-field[0]"),
				"Verifying adv-field[0] is seen on search result URL");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-value[0]"),
				"Verifying adv-value[0] is seen on search result URL");

		// Verify if both search slugs are present

		// -- Add code to remove the second filter here

		browseOrSearchPage.clickOnCrossButtonOnAdvancedSearchFilterBrowseOrSearchPage(2);
		browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
		WebDriverManager.getDriver().navigate().refresh();
		// Thread.sleep(2000);

		// Verifying that removing second filter , url has only adv-field[0] and
		// adv-value[0]

		BaseTest.assertFalse(driver, driver.getCurrentUrl().contains(fulltext2[0]),
				"Verifying that the second filter text is now removed from the URL");
		// BaseTest.assertFalse(driver, BaseTest.verifyTextInURL(fulltext2[0]),
		// "Verifying " + fulltext2[0] + " is seen on search result URL");

		// Verify the number of results reduces to the first filter result after
		// removing the second filter

		int resultsAfterSecondFilterRemoval = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		BaseTest.assertEquals(driver, resultsAfterSecondFilterRemoval, fullTextResults,
				"Verifying that the number of search results reduces after removing OR filter");

		// Adding a new row
		browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();

		String authorEditor = browseOrSearchPage.getFirstAuthorNameOnFirstArticleOnBrowseOrSearchPage();

		browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(
				testData.get("testidvalueselecttwo").toString(), testData.get("refinefilteroptionauthor").toString());
		browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(
				testData.get("testidvalueentertwo").toString(), authorEditor);
		browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
		WebDriverManager.getDriver().navigate().refresh();
		int editorFilter = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				BaseTest.compareBrowserResultsCount(browseResultCount, editorFilter), true,
				"Verifying the total result count after applying the Author Editor filter from refine term filter on search Ppge");
		System.out.println("six " + BaseTest.getLastsixStringCharacter(authorEditor));
		String[] authoreditorwords = authorEditor.split(" ");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(authoreditorwords[0]),
				"Verifying the Author Editor filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-field[1]"),
				"Verifying adv-field[1] is seen on search result URL");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-value[1]"),
				"Verifying adv-value[1] is seen on search result URL");

	}

	@Test(groups = {
			"anesthesiaprogress" }, enabled = true, retryAnalyzer = Retry.class, description = "1730100 - Verify that Advanced Refine By Date filter Functionality")
	@Story("EPIC-237")

	public void verifyThatAdvancedRefineByDateFilterFunctionality() throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		masterPage.clickOnSearchMagnifyingLense();
		int totalResultCount = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		String fromDate = testData.get("fromdate").toString();
		String toDate = testData.get("todate").toString();
		String orFromDate = testData.get("ORfromdate").toString();
		String orToDate = testData.get("ORtodate").toString();

		// Verifying the Add Row button is available under By date filter on search or
		// browse page

		BaseTest.assertEquals(WebDriverManager.getDriver(),
				browseOrSearchPage.verifyAddRowbuttonIsAvailableForDateFilter(), true,
				"Verifying the Add Row button is available under By date filter on search or browse page");
		browseOrSearchPage.clickOnAddRowButtonForDateFilter();

		// Verifying the after clicking the Add Row, multi term date filter rows is
		// added in date filter
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				browseOrSearchPage.verifyMultiDateFilterTermAvailableAfterclickOnAddRowButton("1"), true,
				"Verifying the after clicking the Add Row, multi term date filter rows is added in date filter");
		browseOrSearchPage.clickOnCrossButtonForDateMultiTermFilter("1");

		// Verifying the after clicking cross button, multi term date filter rows is
		// removed from the date filter
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				browseOrSearchPage.verifyMultiDateFilterTermNotAvailableAfterclickOnCrossButton("1"), true,
				"Verifying the after clicking cross button, multi term date filter rows is removed from the date filter");

		// Verifying the date filter is applied with multi term date on search result
		// page
		browseOrSearchPage.selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(fromDate);
		browseOrSearchPage.selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(toDate);

		browseOrSearchPage.clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage();

		int afterFirstRowsDateFilter = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		browseOrSearchPage.clickOnAddRowButtonForDateFilter();
		browseOrSearchPage.selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage("1",
				orFromDate);
		browseOrSearchPage.selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage("1", orToDate);
		browseOrSearchPage.clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage();
		int afterSecondRowsDateFilter = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();

		// Verifying after the OR is applied, the search results have increased from the
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("fromDate[0]=" + fromDate),
				"Verifying the fromDate filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("toDate[0]=" + toDate),
				"Verifying the toDate filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("fromDate[1]=" + orFromDate),
				"Verifying the multi term fromDate filter is applied on search result page");
		BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("toDate[1]=" + orToDate),
				"Verifying the multi term toDate filter is applied on search result page");

		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsPresentOSearchPage(fromDate), true,
				"Verifying the fromDate filter search slug is present on search result page");
		BaseTest.assertEquals(driver, browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsPresentOSearchPage(toDate),
				true, "Verifying the toDate filter search slug is present on search result page");
		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsPresentOSearchPage(orFromDate), true,
				"Verifying the multi term fromDate filter search slug is present on search result page");
		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsPresentOSearchPage(orToDate), true,
				"Verifying the multi term toDate filter search slug is present on search result page");

		BaseTest.assertEquals(WebDriverManager.getDriver(),
				BaseTest.compareBrowserResultsCount(afterSecondRowsDateFilter, afterFirstRowsDateFilter), true,
				"Verifying the total result count after applying the multi term refine date filter on search page");

		// Verifying that the multi-term date filter is not present on the search page
		// once it is removed by removing the search slug.
		browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(orFromDate);
		driver.navigate().refresh();
		browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(orToDate);
		driver.navigate().refresh();
		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsNotPresentOSearchPage(orToDate), true,
				"Verifying that the multi-term date filter is not present on the search page once it is removed by removing the search slug.");
		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsNotPresentOSearchPage(orFromDate), true,
				"Verifying that the multi-term date filter is not present on the search page once it is removed by removing the search slug.");
		int afterSecondRowsDateFilterremoved = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(), afterFirstRowsDateFilter, afterSecondRowsDateFilterremoved,
				"Verifying the total result after removing the multi term date filter on search page");

		// Verifying the multi term refine date filter is not applied once the multi
		// term filter removed after clicking on te cross button in the refine by date
		// filter
		browseOrSearchPage.clickOnAddRowButtonForDateFilter();
		browseOrSearchPage.selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(fromDate);
		browseOrSearchPage.selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(toDate);
		browseOrSearchPage.clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage();
		browseOrSearchPage.clickOnCrossButtonForDateMultiTermFilter("1");
		browseOrSearchPage.clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage();
		int removedMultiTerm = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsNotPresentOSearchPage(orToDate), true,
				"Verifying the multi term refine toDate filter search slug is not present on search result page once multi term date filter removed");
		BaseTest.assertEquals(driver,
				browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsNotPresentOSearchPage(orFromDate), true,
				"Verifying the multi term refine fromDate filter search slug is not present on search result page once once multi term date filter removed");
		BaseTest.assertEquals(WebDriverManager.getDriver(), afterFirstRowsDateFilter, removedMultiTerm,
				"Verifying the total result after removing the multi term date filter from date after clicking on cross button on the search page");

	}

}
