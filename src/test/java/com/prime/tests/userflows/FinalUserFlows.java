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
			"fpj" }, enabled = true, retryAnalyzer = Retry.class, description = "1111  - Verify Publisher User flows")
	@Story("EPIC-971")
	public void publisherUserFlow() throws Exception {
		SoftAssert soft = new SoftAssert();
		testCaseId = "1111";
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		testDataInIt(testCaseId);
		browserInit();

		// Identifying userflow to redirect to
		String userflow = BaseTest.properties.getProperty("userflow");
		String url = BaseTest.properties.getProperty(userflow);
		driver.get(url);
		selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList_VEENA();
		System.out.println("selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList_VEENA complete");
		verifyEachListingHasTitleContributorDOIAndAbstractButton();
		System.out.println("verifyEachListingHasTitleContributorDOIAndAbstractButton complete");
		verifyAccessIconIsLockedWhenIamNotLoggedIn();
		System.out.println("verifyAccessIconIsLockedWhenIamNotLoggedIn complete");
		verifyTheFirstArticleLoadsCorrectlyAndClickedLinkMatchesTitleOfLoadedArticle();
		System.out.println("verifyTheFirstArticleLoadsCorrectlyAndClickedLinkMatchesTitleOfLoadedArticle complete");
		VerifyAllArticleAreFromTheIssueWhileNavigatingTheNextPrevControl();
		System.out.println("VerifyAllArticleAreFromTheIssueWhileNavigatingTheNextPrevControl complete");		
		verifyGooglescholarAndPubmedSectionFunctionality(); 
		System.out.println("verifyGooglescholarAndPubmedSectionFunctionality complete");		
		driver.get(
				"https://meridian-anesthesiaprogress-draft.prime-dev.pubfactory.com/view/journals/anpr/67/2/article-p72.xml");
		System.out.println("after hittng the Article page");
		verifyFulltextIsNavigableThroughArticleContent();
		System.out.println("verifyFulltextIsNavigableThroughArticleContent complete");	
		verifyAllFiguresAreLoadingInTheFigureTab();
		System.out.println("verifyAllFiguresAreLoadingInTheFigureTab complete");	
		verifyFigureOpenInPowePointOnceClicksOnItAndAbleToDownloadTheFigureInPPTFormatIfClicksOnDownloadButton();
		System.out.println("verifyFigureOpenInPowePointOnceClicksOnItAndAbleToDownloadTheFigureInPPTFormatIfClicksOnDownloadButton complete");	
		clickContributorsAndPerformCurrentSiteSearch();
		System.out.println("clickContributorsAndPerformCurrentSiteSearch complete");	
		verifyCitationFunctionalityAndSelectEachFormatOfCitationAndCopyToClipBoardAndMakeSureItMatchesTheCitationPreview();
		System.out.println("verifyCitationFunctionalityAndSelectEachFormatOfCitationAndCopyToClipBoardAndMakeSureItMatchesTheCitationPreview complete");	
		verifyShareButtonIsPresentAndEachOptionPromptsTheUSerToLogInInToTheRespectiveService();
		System.out.println("verifyShareButtonIsPresentAndEachOptionPromptsTheUSerToLogInInToTheRespectiveService complete");	
		
		verifyPDFButonAvailableAndDownloadPDF();
		System.out.println("verifyPDFButonAvailableAndDownloadPDF complete");	
		
		verifyGooglescholarAndPubmedSectionFunctionality(); 
		System.out.println("verifyGooglescholarAndPubmedSectionFunctionality complete");	
		verifyThatTheAutoLaunchNewEmailFunctionality();
		System.out.println("verifyThatTheAutoLaunchNewEmailFunctionality complete");	
		//testDataInIt("1733708");
		verifyContentMetaDataServiceInReferences();
		System.out.println("verifyContentMetaDataServiceInReferences complete");
		//testDataInIt("1730101");
		verifyThatDOIShouldBeLinkAndHandlingDOIRedirectFunctionality();
		System.out.println("verifyThatDOIShouldBeLinkAndHandlingDOIRedirectFunctionality complete");
		verifyThatTheCitationLinkFunctionalityIsWorkingFine();
		System.out.println("verifyThatTheCitationLinkFunctionalityIsWorkingFine complete");
		assertClose();
	}
}
