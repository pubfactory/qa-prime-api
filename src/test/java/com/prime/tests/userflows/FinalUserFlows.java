package com.prime.tests.userflows;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
		SoftAssert soft = new SoftAssert();
		testCaseId = "1733756";
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		testDataInIt(testCaseId);
		browserInit();

		// Identifying userflow to redirect to
//		String userflow = BaseTest.properties.getProperty("userflow");
//		String url = BaseTest.properties.getProperty(userflow);
		String url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String journalCurrentPage=testData.get("journalcurrentpage").toString();
		driver.get(url+journalCurrentPage);
		selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList_VEENA();
		verifyEachListingHasTitleContributorDOIAndAbstractButton();
		verifyAccessIconIsLockedWhenIamNotLoggedIn();
		verifyTheFirstArticleLoadsCorrectlyAndClickedLinkMatchesTitleOfLoadedArticle();
		VerifyAllArticleAreFromTheIssueWhileNavigatingTheNextPrevControl();				
		String openAccessContent=testData.get("openaccesscontent").toString();
		driver.get(url+openAccessContent);
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
