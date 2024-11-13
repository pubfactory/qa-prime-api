package com.prime.tests.userflows;

import org.testng.annotations.Test;
import com.prime.generics.BaseTest;
import com.prime.generics.UserFlowDef;
import com.prime.generics.WebDriverManager;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PublisherUserFlow extends UserFlowDef {

	private String testCaseId;

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"AP" }, enabled = true, retryAnalyzer = Retry.class, description = "1733756  - Verify Publisher User flows"
	      				+"1.Select Issue Drop Down in Journals Page \r\n"
					+ "2.Listing - Title ,Contributor(s), DOI, Abstract Button Check. \r\n"
					+ "3. UnAuthorised User Checks - Access Icon, Article Page\r\n"
					+ "4.Prev/Next Navigation\r\n"
					+ "5.Article Page - Full Text Tab Check \r\n"
					+ "6.Article Page - Figure Tab Check\r\n"
					+ "7.Article Page - Contributor(s)\r\n"
					+ "8.Article Page - Citation/Copy Preview Citation\r\n"
					+ "9.Article Page - Share Button Functionality \r\n"
					+ "10.Article Page - PDF Functionality\r\n"
					+ "11.Article Page - Google Scholar/PubMed Functionality\r\n"
					+ "12.Article Page - Article Information Tab Check\r\n"
					+ "13.Article Page - References Functionality\r\n"
					+ "14.Article Page - DOI Functionality")
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
		//verifyCitationFunctionalityAndSelectEachFormatOfCitationAndCopyToClipBoardAndMakeSureItMatchesTheCitationPreview();
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
