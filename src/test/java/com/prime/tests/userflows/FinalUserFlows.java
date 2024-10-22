package com.prime.tests.userflows;

import org.testng.annotations.Test;
import com.prime.generics.BaseTest;
import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class FinalUserFlows extends UserFlowDef {

	private String testCaseId;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"AP" }, enabled = true, retryAnalyzer = Retry.class, description = "1733756  - Verify Publisher User flows")
	@Story("EPIC-3818")
	public void publisherUserFlow() throws Exception {
		// SoftAssert soft = new SoftAssert();
		// testCaseId = "1733756";
		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		testDataInIt(testCaseId);
		System.out.println("testcaseId =" + testCaseId);
		browserInit();
		String url = BaseTest.properties.getProperty(application);
		String journalCurrentPage = testData.get("journalcurrentpage").toString();
		driver.get(url + journalCurrentPage);
		selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList();
		verifyEachListingHasTitleContributorDOIAndAbstractButton();
		verifyAccessIconIsLockedWhenIamNotLoggedIn();
		verifyTheFirstArticleLoadsCorrectlyAndClickedLinkMatchesTitleOfLoadedArticle();
		verifyAllArticleAreFromTheIssueWhileNavigatingTheNextPrevControl();
		System.out.println("testcaseId at the end =" + testCaseId);
		String openAccessContent = testData.get("openaccesscontent").toString();
		driver.get(url + openAccessContent);
		verifyFulltextIsNavigableThroughArticleContent();
		verifyAllFiguresAreLoadingInTheFigureTab();
		verifyFigureOpenInPowePointOnceClicksOnItAndAbleToDownloadTheFigureInPPTFormatIfClicksOnDownloadButton();
		clickContributorsAndPerformCurrentSiteSearch();
		verifyCitationFunctionalityAndSelectEachFormatOfCitationAndCopyToClipBoardAndMakeSureItMatchesTheCitationPreview();
		verifyShareButtonIsPresentAndEachOptionPromptsTheUSerToLogInInToTheRespectiveService();
		verifyPDFButonAvailableAndDownloadPDF();
		verifyGooglescholarAndPubmedSectionFunctionality();
		verifyThatTheAutoLaunchNewEmailFunctionality();
		verifyContentMetaDataServiceInReferences();
		verifyThatDOIShouldBeLinkAndHandlingDOIRedirectFunctionality();
		verifyThatTheCitationLinkFunctionalityIsWorkingFine();
		assertClose();
	}
}
