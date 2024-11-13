package com.prime.tests.userflows;

import org.testng.annotations.Test;

import com.prime.generics.BaseTest;
import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LibrarianUserFlow extends UserFlowDef{
	private String testCaseId;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"AP" }, enabled = true, retryAnalyzer = Retry.class, description = "1733809  - Verify Librarian User flows")
	      				
	@Story("EPIC-3818")
	public void librarianUserFlow() throws Exception {
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		testDataInIt(testCaseId);
		browserInit();
		verifyCurrentIssueVolumeOnHomeIsSameCurrentIssueIOnIssuePage();
		verifyHowWellSearchRefinementWorks();
		verifyIfUseTheRefineTermsToGetMoreSpecificInformationForSedationOfYoungerPatients();
		verifyRefineAllResultsToResearchArticlesFromByArticleTypeFilterWithinTheLastFourYears();
		//verifyPageThroughThe3PagesOfResultsAndMakeSureTheNumberOfReturnedResultsMatchTheNumberOfResultsOnTheSearchPageAndInTheFilters();
		verifyIncreaseTheNumberOfResultsPerPageTo50SoNoLongerHaveAnyPagination();
		verifyDefaultSortOrderIsRelevanceAndChangedToASCAndDESCAgainSetToBack();
		clearAllAppliedfilterOnBrowsePage();
		verifyTheRightOpenAccessArticleIsLoaded();
		clickOnContributorOfArticleAndVerifyMoreInformationAboutThem();
		verifyInFullTextTabContentIsAvailbale();
		clickOnKeywordsAndGetNewSearchesForThatKeywordsAgainClickOnBackButtonAndVerifyRetrunedToArticlePage();
		clickOnPDFTabAndScanTheInlinePDF();
		clickOnFiguresTabScanTheFigures();
		selectOneFigureAndExportItAsPPT();
		clickOnArticleInformationTabandScanTheSection();
		assertClose();
	}
}
