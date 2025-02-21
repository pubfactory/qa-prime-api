package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class AutoCompleteTypeAhead extends BaseTest {

	private MasterPage masterPage;
	private String url = "";
	private String testCaseId;

	// Created for PRIME-3436
	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"anesthesiaprogress" }, enabled = true, retryAnalyzer = Retry.class, description = "1729939 - Verify the autocomplete functionality - type ahead")
	@Story("EPIC-2719")
	public void verifythatTheAutoCompleteTypeAheadFunctionality() throws Exception {

		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());

		// Verifying the with option(s) that matches the user has typed.
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				masterPage.VerifyingAutoSuggetionContainSearchWord(testData.get("searchkeyword").toString()), true,
				"Verifying the with option(s) that matches the user has type");

		// Verifying the text that matches the search in the suggestion list should be
		// bold.
		Thread.sleep(10000);
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				masterPage.verifyMatchesSearchInTheSuggestionListIsBold(testData.get("searchkeyword").toString()), true,
				"Verifying the text that matches the search in the suggestion list is bold.");

		// Verifying the user can be navigated with the down keys in the autocomplete
		// option and user selects an option, the string in the search input is replaced
		// with the selected option.
		Helper.INSTANCE.pressDownArrowKey(masterPage.getAllAutoSuggestionList().size());

		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.getSearchBoxValue(),
				masterPage.getAllAutoSuggestionList().get(masterPage.getAllAutoSuggestionList().size() - 1),
				"Verifying the user can be navigated with the down keys in the autocomplete option and when user selects an option, the string in the search input is replaced with the selected option.");

		// Verifying the user can be navigated with the up keys in the autocomplete
		// option and user selects an option, the string in the search input is replaced
		// with the selected
		// option.Helper.INSTANCE.pressUpArrowKey(masterPage.getAllAutoSuggestionList().size());
		Helper.INSTANCE.pressUpArrowKey(masterPage.getAllAutoSuggestionList().size());
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.getSearchBoxValue(),
				masterPage.getAllAutoSuggestionList().get(masterPage.getAllAutoSuggestionList().size() - 2),
				"Verifying the user can be navigated with the up keys in the autocomplete option and when user selects an option, the string in the search input is replaced with the selected option.");

		// Verifying that in the autocomplete are highlighted in focus as the user
		// navigates between options.
		Helper.INSTANCE.pressUpArrowKey(masterPage.getAllAutoSuggestionList().size());
		System.out.println("masterPage.getAutoSuggestionFocusedText() : " + masterPage.getAutoSuggestionFocusedText());
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.getAutoSuggestionFocusedText(),
				masterPage.getSearchBoxValue(),
				"Verifying that in the autocomplete are highlighted in focus as the user navigates between options");

		// Verifying the Quick Search Form component is used on the site(form tag)
		System.out.println("ABC : " + masterPage.getQuickSearchBoxAttributeValue());
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.getQuickSearchBoxAttributeValue(),
				testData.get("quickSearchAttribute").toString(),
				"Verifying the Quick Search Form component is used on the site (form tag).");
		driver.navigate().refresh();
		masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchthreechar").toString());

		// Verifying After 3 characters are typed, display the suggestion list.
		BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySuggestionListIsDisplayed(), true,
				"Verifying After 3 characters are typed, display the suggestion list.");

		// If the mouse cursor is over the element, the background has to change, but it
		// is not selected.
		masterPage.mouseOverOnAutoCompleteSuggetions();
		String searchText = masterPage.getSearchBoxValue();
		String mouseHoverSuggestion = masterPage.getAllAutoSuggestionList()
				.get(masterPage.getAllAutoSuggestionList().size() - 1);
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				verifyStringContainsSpecificWord(searchText, mouseHoverSuggestion), false,
				"Verifying that if the mouse cursor is over on the suggestions, the background has to change, but it is not selected.");

		//verifying a maximum of 5 elements will be displayed in the suggestion list.
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				masterPage.getAllAutoSuggestionList().size(),5,
				"Verifying that a maximum of 5 elements will be displayed in the suggestion list.");


	}
}
