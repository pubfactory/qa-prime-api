/**
 * Testing the below functionality in Cite An Entry 

1. Verifying if the citation button is present in the article page
2. Verifying if citation pop-up is displayed when clicking on the citation button
3. Verifying if user is able to see RIS,BIB,ENW Button is present under Export citation section.
4. Verifying if user is able to download RIS,BIB,ENW formats
5. Verifying if user is able to close citation pop-up after use.

 */
package com.prime.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONObject;
import org.testng.asserts.SoftAssert;

import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.JournalPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.PDFPage;

import io.qameta.allure.Allure;

public class UserFlowDef extends BaseTest {

	private MasterPage masterPage;
	private BasePage basePage;
	private ArticleCitationPage articleCitationPage;
	private BrowseOrSearchPage browseOrSearchPage;
	private PDFPage pdfPage;
	private String url = "";
	private String testCaseId;
	public JSONObject testData;
	public String mainWindow;
	public SoftAssert soft;
	private JournalPage journalPage;
	private IssuePage issuePage;
	List<String> titleList;
	private String articleUrl;

	public void testDataInIt(String testCaseId) throws Exception {
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
	}

	public void browserInit() throws Exception {
		// testCaseId = retrieveTCID(new
		// Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		// WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		// String application = System.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		// String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		mainWindow = Helper.INSTANCE.getWindow(driver);
		// testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);

	}

	public void VerifyVariousEmailButtonFeatures() throws Exception {
		try {
			soft = new SoftAssert();
			driver.navigate().refresh();
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			String articleText = articleCitationPage.getArticleHeaderOnArticlePage();

			// Verifying Share via email button and Veriying Share link popup header
			soft.assertEquals(articleCitationPage.verifyShareViaEmailButtonPresentOnArticlePage(), false,
					"Verifying the Share via Email button is present on Article Page");

			Allure.step("Verifying the Share via Email button is present on Article Page");

			articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
			soft.assertEquals(articleCitationPage.getShareLinkPopupHeader(),
					testData.get("sharelinkpopupHeader").toString(),
					"Verifying the Share Link pop up Header on Article page");
			Allure.step("Verifying the Share Link pop up Header on Article page");

			articleCitationPage.clickOnCopyLinkButtonOnShareLinkOnArticlePage();

			// Verifying Link Copied successfully meassage displayed

			String directCopyLink = articleCitationPage.getCopyLinkDirectlyOnShareLinkPopupOnArticlePage();
			articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();

			// verifying same article open in new tab
			Helper.INSTANCE.openNewTab();
			Helper.INSTANCE.switchToWindowTab(1);
			WebDriverManager.getDriver().navigate().to(directCopyLink);
			String articleTextInNewTab = articleCitationPage.getArticleHeaderOnArticlePage();
			soft.assertEquals(articleText, articleTextInNewTab,
					"Verifying the same article is opened when copy the link from share Link pop up and paste in new tab");
			Allure.step(
					"Verifying the same article is opened when copy the link from share Link pop up and paste in new tab");

			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

			// Verifying share link popup close button
			articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
			articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();
			articleCitationPage.hoverOnShareViaEmailButton();
			soft.assertEquals(
					articleCitationPage.verifyShareLinkPopUpIsNotPresentOnArticlePageAfterClickingCloseButton(), true,
					"Verifying the Share link pop up is Closed after clicking the close button of share link popup");
			Allure.step(
					"Verifying the Share link pop up is Closed after clicking the close button of share link popup");
			// soft.assertAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);
		}
	}

	public void assertClose() {
		soft.assertAll();
	}

	public void selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList() throws Exception {
		soft = new SoftAssert();
		issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
		basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
		issuePage.clickOnIssueSelector();
		String recentVolume = issuePage.getTotalVolumeListOnJournalPage().get(0);
		String secondRecentVolume = issuePage.getTotalVolumeListOnJournalPage().get(1);

		int recentVolumeNum = issuePage.getVolumeNumberFromSelectIssueDDOnJournalPage(recentVolume);
		int secondRecentVolumeNum = issuePage.getVolumeNumberFromSelectIssueDDOnJournalPage(secondRecentVolume);
		// Verify the most recent volume list is displayed at the top.
		soft.assertEquals(Helper.INSTANCE.compareIntValue(recentVolumeNum, secondRecentVolumeNum), true,
				"verifying the recent volume is displayed on the select issue DD at the top.");
		Allure.step("verifying the recent volume is displayed on the select issue DD at the top.");
		issuePage.clickOnVolumeFromSelectIssueDDOnJournalPage(recentVolume);
		// Thread.sleep(2000);
		String recentIssue = issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(recentVolume).get(0);
		int secondIssueNum = 0;

		if (issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(recentVolume).size() > 1) {
			System.out.println("size: "
					+ issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(recentVolume).size());
			String secondIssue = issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(recentVolume)
					.get(1);
			secondIssueNum = issuePage.getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(secondIssue);
		} else {
			secondIssueNum = 0;
		}
		int recentIssueNum = issuePage.getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(recentIssue);
		System.out.println("recentIssue: " + recentIssueNum);
		System.out.println("secondIssueNum: " + secondIssueNum);

		// Verify the most recent issue list is displayed at the top.
		soft.assertEquals(Helper.INSTANCE.compareIntValue(recentIssueNum, secondIssueNum), true,
				"verifying the recent Issue is displayed under Recent Volume on the select Issue DD at the top.");
		Allure.step("verifying the recent Issue is displayed under Recent Volume on the select Issue DD at the top.");
		// Verify we expect to be redirected to the latest issue. (Steps 2)
		url = BaseTest.properties.getProperty(application);
		soft.assertEquals(Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(basePage.getURLFromWebPage()),
				Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(url) + "view/journals/anpr/"
						+ Integer.toString(recentVolumeNum) + "/" + Integer.toString(recentIssueNum) + "/anpr."
						+ Integer.toString(recentVolumeNum) + ".issue-" + Integer.toString(recentIssueNum) + ".xml",
				"Verifying we expect to be redirected to the latest issue.");
		Allure.step("Verifying we expect to be redirected to the latest issue.");

	}

	public void selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList_VEENA() throws Exception {
		soft = new SoftAssert();
		issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
		basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
		issuePage.clickOnIssueSelector();
		String firstVolume = issuePage.getTotalVolumeListOnJournalPage().get(0);
		String secondRecentVolume = issuePage.getTotalVolumeListOnJournalPage().get(1);
		ArrayList<Integer> volumeList = new ArrayList<>();
		int recentVolumeNum;
		for (int i = 0; i < issuePage.getTotalVolumeListOnJournalPage().size(); i++) {
			recentVolumeNum = issuePage
					.getVolumeNumberFromSelectIssueDDOnJournalPage(issuePage.getTotalVolumeListOnJournalPage().get(i));
			volumeList.add(recentVolumeNum);
		}
		int largestVolumeNumber = Collections.max(volumeList);
		int latestVolumeNum = issuePage.getVolumeNumberFromSelectIssueDDOnJournalPage(firstVolume);

		// Verify the most recent volume list is displayed at the top.
		soft.assertEquals(largestVolumeNumber, latestVolumeNum,
				"Verifying the latest volume is displayed on the select issue drop down at the top.");
		Allure.step("Verifying the latest volume is displayed on the select issue drop down at the top.");

		issuePage.clickOnVolumeFromSelectIssueDDOnJournalPage(firstVolume);
		Thread.sleep(2000);
		String recentIssue = issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).get(0);
		int recentIssueNum;
		ArrayList<Integer> issueList = new ArrayList<>();
		int largestIssueNumber = 0;
		if (issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).size() >= 1) {
			for (int i = 0; i < issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume)
					.size(); i++) {
				recentIssueNum = issuePage.getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(
						issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).get(i));
				issueList.add(recentIssueNum);
			}
			largestIssueNumber = Collections.max(issueList);
		}
		int latestIssueNum = issuePage.getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(recentIssue);
		soft.assertEquals(largestIssueNumber, latestIssueNum,
				"verifying the recent Issue is displayed under Recent Volume on the select Issue DD at the top.");
		Allure.step("verifying the recent Issue is displayed under Recent Volume on the select Issue DD at the top.");

		// Verify we expect to be redirected to the latest issue. (Steps 2)
		url = BaseTest.properties.getProperty(application);
		soft.assertEquals(Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(basePage.getURLFromWebPage()),
				Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(url) + "view/journals/anpr/"
						+ Integer.toString(latestVolumeNum) + "/" + Integer.toString(latestIssueNum) + "/anpr."
						+ Integer.toString(latestVolumeNum) + ".issue-" + Integer.toString(latestIssueNum) + ".xml",
				"Verifying we expect to be redirected to the latest issue.");
		Allure.step("Verifying we expect to be redirected to the latest issue.");

	}

	public void verifyEachListingHasTitleContributorDOIAndAbstractButton() throws Exception {

		issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
		issuePage.clickOnIssueSelector();
		titleList = issuePage.getTotalContentTitleListOnJournalPage();
		int totalTitle = issuePage.getTotalContentTitleListOnJournalPage().size();
		int contributor = issuePage.getTotalContentContributorListOnJournalPage().size();
		int DOI = issuePage.getTotalContentDOIListOnJournalPage().size();
		int abstractButton = issuePage.getTotalContentAbstractButtonListOnJournalPage().size();
		soft.assertEquals(totalTitle, contributor, "Verifying each listing has a contributor(s).");
		Allure.step("Verifying each listing has a contributor(s).");
		soft.assertEquals(totalTitle, DOI, "Verifying each listing has a DOI.");
		Allure.step("Verifying each listing has a DOI.");
		soft.assertEquals(totalTitle, abstractButton, "Verifying each listing has a Abstract Button.");
		Allure.step("Verifying each listing has a Abstract Button.");

	}

	public void verifyAccessIconIsLockedWhenIamNotLoggedIn() throws Exception {
		issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		String signInText = masterPage.getSignInButtonText();
		String accessIcon = issuePage.getAccessIconValue();
		soft.assertEquals(signInText, "Sign in", "Verifying the user has is not logged In.");
		Allure.step("Verifying the user has is not logged In.");
		soft.assertEquals(accessIcon, "Restricted access",
				"Verifying the access icon is locked when user is not logged in");
		Allure.step("Verifying the access icon is locked when user is not logged in");

	}

	public void verifyTheFirstArticleLoadsCorrectlyAndClickedLinkMatchesTitleOfLoadedArticle() throws Exception {
		issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		String actualTitle = issuePage.getFirstArticleTextOnIssuePageHeaderText();
		issuePage.clickOnFirstArticleOnIssuePage();
		String expectedTitle = articleCitationPage.getArticleHeaderOnArticlePage();
		soft.assertEquals(actualTitle, expectedTitle,
				"Verifying user clicked link matches the title of the loaded article.");
		Allure.step("Verifying user clicked link matches the title of the loaded article.");
		String actualresult = articleCitationPage.getTheAriaExpandedValueForAbstractTabOrPDFTabIfNoAbstract();
		soft.assertEquals(actualresult, "true",
				"Verifying user land on abstract tab or the PDF Preview tab if there is no abstract tab.");
		Allure.step("Verifying user land on abstract tab or the PDF Preview tab if there is no abstract tab.");
	}

	public void VerifyAllArticleAreFromTheIssueWhileNavigatingTheNextPrevControl() throws Exception {
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		soft.assertEquals(articleCitationPage.verifyArticleAreFromTheIssueWhileNavigatingTheNextArticle(titleList),
				true, "Verifying all the Article are from The Issue while navigating the Next control.");
		Allure.step("Verifying all the Article are from The Issue while navigating the Next control.");

		soft.assertEquals(articleCitationPage.verifyArticleAreFromTheIssueWhileNavigatingThePrevArticle(titleList),
				true, "Verifying all the Article are from The Issue while navigating the Prev control.");
		Allure.step("Verifying all the Article are from The Issue while navigating the Prev control.");

	}

	public void verifyFulltextIsNavigableThroughArticleContent() throws Exception {
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		articleCitationPage.clickOnArticleInformationTabOnArticlePage();
		soft.assertEquals(articleCitationPage.getTheAriaExpandedValueForarticleInformationTab(), "true",
				"Verifying user on article information tab");
		Allure.step("Verifying user on article information tab");
		articleCitationPage.clickOnResultsAticleContentLinkOnArticlePage();
		soft.assertEquals(articleCitationPage.getTheAriaExpandedValueForFulltextTab(), "true",
				"Verifying once user clicks on the Results article content link, user navigates on Full Text tab");
		Allure.step("Verifying once user clicks on the Results article content link, user navigates on Full Text tab");
		articleCitationPage.clickOnArticleInformationTabOnArticlePage();
		soft.assertEquals(articleCitationPage.getTheAriaExpandedValueForarticleInformationTab(), "true",
				"Verifying user on article information tab");
		Allure.step("Verifying user on article information tab");

		articleCitationPage.clickOnConclusionAticleContentLinkOnArticlePage();
		soft.assertEquals(articleCitationPage.getTheAriaExpandedValueForFulltextTab(), "true",
				"Verifying once user clicks on the Conclusion article content link, user navigates on Full Text tab");
		Allure.step(
				"Verifying once user clicks on the Conclusion article content link, user navigates on Full Text tab");
		articleCitationPage.clickOnArticleInformationTabOnArticlePage();
		soft.assertEquals(articleCitationPage.getTheAriaExpandedValueForarticleInformationTab(), "true",
				"Verifying user on article information tab");
		Allure.step("Verifying user on article information tab");
		articleCitationPage.clickOnReferenceAticleContentLinkOnArticlePage();
		soft.assertEquals(articleCitationPage.getTheAriaExpandedValueForFulltextTab(), "true",
				"Verifying once user clicks on the References article content link, user navigates on Full Text tab");
		Allure.step(
				"Verifying once user clicks on the References article content link, user navigates on Full Text tab");
	}

	public void verifyAllFiguresAreLoadingInTheFigureTab() throws Exception {
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		articleCitationPage.clickOnFiguresTabOnArticlePage();
		articleCitationPage.verifyAllFiguresAreLoadingInTheFigureTabOnArticlePage();
		soft.assertEquals(articleCitationPage.verifyAllFiguresAreLoadingInTheFigureTabOnArticlePage(), true,
				"Verifying all figures are loaded in figures tab on article page.");
		Allure.step("Verifying all figures are loaded in figures tab on article page.");
	}

	public void verifyFigureOpenInPowePointOnceClicksOnItAndAbleToDownloadTheFigureInPPTFormatIfClicksOnDownloadButton()
			throws Exception {
		try {
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			articleCitationPage.clickOnSelectFirstFigureFromTheFiguresTabOnArticlePage();
			soft.assertEquals(articleCitationPage.verifyFiguresDiplayedInPPTFormat(), true,
					"verifying figures is diplayed in PPT format");
			Allure.step("Verifying figures is diplayed in PPT format");
			articleCitationPage.clickOnNextButtonForSlidePPT();
			soft.assertEquals(articleCitationPage.getTheSrcAttributeValueOfImageOrFigure(),
					Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(url)
							+ "view/journals/anpr/67/2/full-i0003-3006-67-2-72-f02.png",
					"verifying the figure changed once user click on next button in slide PPT");
			Allure.step("Verifying the figure changed once user click on next button in slide PPT.");
			articleCitationPage.clickOnPreviousButtonForSlidePPT();
			soft.assertEquals(articleCitationPage.getTheSrcAttributeValueOfImageOrFigure(),
					Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(url)
							+ "view/journals/anpr/67/2/full-i0003-3006-67-2-72-f01.png",
					"Verifying the figure changed once user click on previous button in slide PPT.");
			Allure.step("Verifying the figure changed once user click on previous button in slide PPT.");

			articleCitationPage.clickOnSlidePPTCloseButton();
			articleCitationPage.clickOnExportFiguresButtonUnderFigureTab();
			articleCitationPage.clickOnSelectFirstFigureFromTheFiguresTabOnArticlePage();
			articleCitationPage.clickOnFiguresDownloadButton();

			soft.assertEquals(articleCitationPage.verifyFiguresIsDownloadedInPPTFormat(".pptx"), true,
					"Verifying the figures is downloaded in PPT format.");
			Allure.step("Verifying the figures is downloaded in PPT format.");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			deletedownloadedFiles(".pptx");
		}
	}

	public void clickContributorsAndPerformCurrentSiteSearch() throws Exception {
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		articleCitationPage.clickOnFirstAuthorBelowTheArticleTitleOnArticlePage();
		articleCitationPage.clickOnCurrentSiteLinkOnAuthorAffiliationPopup();
		String authorEditor = browseOrSearchPage.getFirstAuthorNameOnFirstArticleOnBrowseOrSearchPage();
//		BaseTest.assertEquals(WebDriverManager.getDriver(),
//				browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage("author", authorEditor), true,
//				"Verifying the once click on Current site, Refine term filter for Author is displayed on browse/search result page.");
		Allure.step("Verifying the once click on Current site, Refine term filter for Author is displayed on browse/search result page.");
		driver.navigate().back();
	}

	public void verifyCitationFunctionalityAndSelectEachFormatOfCitationAndCopyToClipBoardAndMakeSureItMatchesTheCitationPreview()
			throws Exception {
		try {

			// masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
			soft = new SoftAssert();
//			masterPage.clickOnSearchMagnifyingLense();
			browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
//			browseOrSearchPage
//					.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("open").toString());
//			browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
			articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();

			// Verifying if the citation button is present in the article page
			soft.assertEquals(articleCitationPage.verifyCitationButtonPresentOnArticlePage(), true,
					"Verifying Citation Button is present.");
			Allure.step("Verifying Citation Button is present.");

			articleCitationPage.clickOnCitationButtonOnArticlePage();
			String articleUrl = basePage.getURLFromWebPage();

			// Verifying if citation pop-up is displayed when clicking on the citation
			// button
			soft.assertEquals(articleCitationPage.getPreviewExportCitationPopUpHeaderText(),
					testData.get("popupheader").toString(), "Verifying the Preview Export Citation popup is displayed");
			Allure.step("Verifying the Preview Export Citation popup is displayed.");

			articleCitationPage
					.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
			articleCitationPage
					.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());

			// Verifying if user is able to see RIS,BIB,ENW Button is present under Export
			// citation section.

			soft.assertEquals(articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup(), true,
					"Verifying the RIS Button is present under Export citation section on Preview Export Citation PopUp");
			Allure.step(
					"Verifying the RIS Button is present under Export citation section on Preview Export Citation PopUp");
			soft.assertEquals(articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup(), true,
					"Verifying the BIB Button is present under Export citation section on Preview Export Citation PopUp");
			Allure.step(
					"Verifying the BIB Button is present under Export citation section on Preview Export Citation PopUp.");
			soft.assertEquals(articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup(), true,
					"Verifying the ENW Button is present under Export citation section on Preview Export Citation PopUp");
			Allure.step(
					"Verifying the ENW Button is present under Export citation section on Preview Export Citation PopUp");
			articleCitationPage.clickOnRISExportCitationFormat();

			// Verifying if user is able to download RIS,BIB,ENW formats
			soft.assertEquals(articleCitationPage.toVerifyCitationFormatFileIsDownload(".ris"), true,
					"Verifying the file .ris format file is downloaded");
			Allure.step("Verifying the file .ris format file is downloaded.");

			deletedownloadedFiles(".ris");
			articleCitationPage.clickOnBIBExportCitationFormat();
			soft.assertEquals(articleCitationPage.toVerifyCitationFormatFileIsDownload(".bib"), true,
					"Verifying the file .ris format file is downloaded");
			Allure.step("Verifying the file .ris format file is downloaded");

			deletedownloadedFiles(".bib");
			articleCitationPage.clickOnENWExportCitationFormat();

			soft.assertEquals(articleCitationPage.toVerifyCitationFormatFileIsDownload(".enw"), true,
					"Verifying the file .ris format file is downloaded");
			Allure.step("Verifying the file .ris format file is downloaded");

			deletedownloadedFiles(".enw");
			List<String> expRISCitationLabels = Arrays.asList(testData.get("risbuttonlabel").toString().split(","));
			List<String> expBIBCitationLabels = Arrays.asList(testData.get("bibbuttonlabel").toString().split(","));
			List<String> expENWCitationLabels = Arrays.asList(testData.get("enwbuttonlabel").toString().split(","));

			soft.assertEquals(
					articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(),
					expRISCitationLabels.toString(), "Verifying RIS button Labels on Export Ciatation Popup");
			Allure.step("Verifying RIS button Labels on Export Ciatation Popup");
			soft.assertEquals(
					articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(),
					expBIBCitationLabels.toString(), "Verifying BIB button Labels on Export Ciatation Popup");
			Allure.step("Verifying BIB button Labels on Export Ciatation Popup");
			soft.assertEquals(
					articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(),
					expENWCitationLabels.toString(), "Verifying ENW button Labels on Export Ciatation Popup");
			Allure.step("Verifying ENW button Labels on Export Ciatation Popup");
			soft.assertEquals(
					articleCitationPage.verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp(),
					true,
					"Verifying Preview Export Citation PopUp Close Button is present on Preview Export Citation PopUp");
			Allure.step(
					"Verifying Preview Export Citation PopUp Close Button is present on Preview Export Citation PopUp");
			// Verifying if user is able to close citation pop-up after use.
			articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();

			// Verifying the copy to clip board functionality (copy citation)
			basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
			articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();
			articleCitationPage.clickOnCitationButtonOnArticlePage();

			// Verifying the Copy to ClipBoard button is present on preview Export Citation
			// popup
			soft.assertEquals(articleCitationPage.verifyCopyToClipBoardButtonIsPresentOnPreviewExportCitationPopup(),
					true, "Verifying the Copy to ClipBoard button is present on preview Export Citation popup");
			Allure.step("Verifying the Copy to ClipBoard button is present on preview Export Citation popup.");
			// Verifying the chakra toast message is displayed
			articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();

			String headLess = BaseTest.properties.getProperty("headLess");
			if (headLess.equalsIgnoreCase("N")) {
				soft.assertEquals(
						articleCitationPage.verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButton(),
						true,
						"Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
				Allure.step(
						"Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
				articleCitationPage.clickOnToastMessagePopupCloseButton();
			} else {
				soft.assertEquals(
						articleCitationPage
								.verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButtonInHeadless(),
						true,
						"Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
				Allure.step(
						"Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
			}

			// Verifying the APA format is displayed on toast message and the APA format is
			// copied and pasted
			articleCitationPage
					.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
			String abbreviatedTitleAPA = articleCitationPage
					.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingFormat();
			articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();
			articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
			masterPage.clickOnSearchMagnifyingLense();
			browseOrSearchPage.copiedMessagePasteIntoTextBox();
			browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
			String pastedAPAValued = browseOrSearchPage.getRefineTermTextBoxValue();

			soft.assertEquals(BaseTest.verifyStringContainsSpecificWord(abbreviatedTitleAPA, pastedAPAValued), true,
					"Verifying the correct format value is copied and pasted");
			Allure.step("Verifying the correct format value is copied and pasted");
			driver.get(articleUrl);

			// Verifying the AMA format is displayed on toast message and the AMA format is
			// copied and pasted
//			masterPage.clickOnSearchMagnifyingLense();
//			browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
			articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();
			articleCitationPage.clickOnCitationButtonOnArticlePage();
			articleCitationPage
					.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());
			String abbreviatedTitleAMA = articleCitationPage
					.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingFormat();
			articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();
			articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
			masterPage.clickOnSearchMagnifyingLense();
			browseOrSearchPage.copiedMessagePasteIntoTextBox();
			browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
			String pastedAMAValued = browseOrSearchPage.getRefineTermTextBoxValue();
			// BaseTest.assertEquals(WebDriverManager.getDriver(), abbreviatedTitleAMA,
			// pastedAMAValued, "Verifying the correct format value is copied and pasted");
			soft.assertEquals(BaseTest.verifyStringContainsSpecificWord(abbreviatedTitleAMA, pastedAMAValued), true,
					"Verifying the correct format value is copied and pasted");
			Allure.step("Verifying the correct format value is copied and pasted.");
			driver.get(articleUrl);
			// soft.assertAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			deletedownloadedFiles(".ris");
			deletedownloadedFiles(".bib");
			deletedownloadedFiles(".enw");
		}
	}

	public void verifyShareButtonIsPresentAndEachOptionPromptsTheUSerToLogInInToTheRespectiveService()
			throws Exception {
		try {
			driver.navigate().refresh();
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			soft.assertEquals(articleCitationPage.getShareButtonTextOnArticlePage(), "Share",
					"Verifying the share button is present on article page");
			Allure.step("Verifying the share button is present on article page");
			articleCitationPage.clickOnShareButtonOnArticlePage();
			soft.assertTrue(articleCitationPage.verifySharingPlatformDataNetworkUnderShareButton("facebook"),
					"Verifying data-network for facebook attribute is present");
			Allure.step("Verifying data-network for facebook attribute is present");
			articleCitationPage.clickingSharingPlatformButtonIsPresentOnArticlePageUnderShareButton("facebook");
			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL("facebook"), "Verifying if facebook window is opened");
			Allure.step("Verifying if facebook window is opened.");
			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

			soft.assertTrue(articleCitationPage.verifySharingPlatformDataNetworkUnderShareButton("linkedin"),
					"Verifying data-network for linkedin attribute is present");
			Allure.step("Verifying data-network for linkedin attribute is present");
			articleCitationPage.clickingSharingPlatformButtonIsPresentOnArticlePageUnderShareButton("linkedin");
			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL("linkedin"), "Verifying if linkedin window is opened");
			Allure.step("Verifying if linkedin window is opened");
			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

			soft.assertTrue(articleCitationPage.verifySharingPlatformDataNetworkUnderShareButton("twitter"),
					"Verifying data-network for twitter attribute is present");
			Allure.step("Verifying data-network for twitter attribute is present");
			articleCitationPage.clickingSharingPlatformButtonIsPresentOnArticlePageUnderShareButton("twitter");
			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL("x.com"), "Verifying if twitter window is opened");
			Allure.step("Verifying if twitter window is opened");
			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);
		}
	}

	public void verifyPDFButonAvailableAndDownloadPDF() throws Exception {
		try {
			// soft = new SoftAssert();
			driver.navigate().refresh();
			pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
			soft.assertEquals(pdfPage.verifyPDFButtonPresentOnArticlePage(), true,
					"Verifying PDF Button is present on the article page");
			Allure.step("Verifying PDF Button is present on the article page");

			// Verifying Button Downloaded
			String articleHeader = articleCitationPage.getArticleHeaderOnArticlePage();
			pdfPage.clickOnDownloadPDFButtonOnArticlePage();

			soft.assertEquals(pdfPage.toVerifyPDFFIleIsDownload(), true, "Pdf File is downloaded");
			Allure.step("Pdf File is downloaded");

			deletedownloadedFiles(".pdf");
			deletedownloadedFiles(".crdownload");

			// Verify Inline PDF tab is diplayed
			soft.assertEquals(pdfPage.verifyInlinePDFTabIsPresentOnArticlePage(), true,
					"Verifying Inline PDF tab is present on the article page");
			Allure.step("Verifying Inline PDF tab is present on the article page");

			pdfPage.clickOnInlinePdfTabOnArticlePage();
			pdfPage.switchToFrame(WebDriverManager.getDriver());

			// Verifying the Default PDF Zoom value, ZoomIn and Zoom Out button in Inline
			// PDF Tab
			soft.assertEquals(pdfPage.getDefaultPDFZoomValueInInlinePDFTab(),
					testData.get("defaultzoomvalue").toString(),
					"Verifying Default zoom value in Inline PDF Tab on article page");
			Allure.step("Verifying Default zoom value in Inline PDF Tab on article page.");

			soft.assertEquals(pdfPage.verifyZoomInButtonIsPresentInInlinePDFTabOnArticlePage(), false,
					"Verifying ZoomIn button is present on Inline PDF tab");
			Allure.step("Verifying ZoomIn button is present on Inline PDF tab");
			soft.assertEquals(pdfPage.verifyZoomOutButtonIsPresentInInlinePDFTabOnArticlePage(), true,
					"Verifying ZoomOut button is present on Inline PDF tab");
			Allure.step("Verifying ZoomOut button is present on Inline PDF tab");

			// Verifying the PageSize changes when click on ZoomOut(Minus) or ZoomIn(Plus)
			// button
			// pdfPage.ClickOnAutomaticZoomFromPDFZoomScaleSelectorDD();
			// pdfPage.clickOnZoomOutButton();
			// BaseTest.assertEquals(WebDriverManager.getDriver(),pdfPage.VerifyPDFSizeChangesWhenClickOnZoomOutButtonAtEightyPercentZoom(),
			// true, "Verifying View page size changed after clicking on zoomOut button on
			// Inline PDF tab");
			// pdfPage.ClickOnAutomaticZoomFromPDFZoomScaleSelectorDD();
			// pdfPage.clickOnZoomInButton();
			// BaseTest.assertEquals(WebDriverManager.getDriver(),pdfPage.VerifyPDFSizeChangesWhenClickOnZoomInButtonAtHundredPercentZoom(),
			// true, "Verifying View page size changed after clinking on zoomIn button on
			// Inline PDF tab");

			// Verifying the same article is displayed in PDFViewer in Inline PDF tab
			String partialArticleHeader = pdfPage.getPartialArticleTitleFromInlinePDFTab();
			soft.assertEquals(BaseTest.verifyStringContainsSpecificWord(articleHeader, partialArticleHeader), true,
					"Verifying that same article is displayed in PDF preview in inline PDF tab");
			Allure.step("Verifying that same article is displayed in PDF preview in inline PDF tab");

			String applicationName = BaseTest.properties.getProperty("application");

			// Verifying the dynamic watermark on pdf preview in Inline pdf tab
			soft.assertEquals(pdfPage.verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(applicationName), true,
					"Verifying that watermark is present on pdf in Pdf preview in Inline tab on the articla page");
			Allure.step("Verifying that watermark is present on pdf in Pdf preview in Inline tab on the articla page.");

			// soft.assertAll();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			deletedownloadedFiles(".pdf");
			deletedownloadedFiles(".crdownload");
		}
	}

	public void verifyGooglescholarAndPubmedSectionFunctionality() {
		try {
			driver.navigate().refresh();
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			soft.assertEquals(articleCitationPage.verifyGoogleScholarButtonPresentOnArticlePage(), true,
					"Verifying the Google Scholar button is present at right hand side on Artical page");
			Allure.step("Verifying the Google Scholar button is present at right hand side on Artical page.");

			// Verifying Same author affiliation block authors present in Google scholar
			// section
			articleCitationPage.clickOnGoogleScholarkButtonOnArticlePage();
			List<String> affiliationBlockAuthor = articleCitationPage
					.getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage();
			List<String> googleScholarAuthor = articleCitationPage
					.getAllAuthorNamesFromAuthorGoogleScholarSectionOnArticlePage();
			soft.assertEquals(affiliationBlockAuthor.toString(), googleScholarAuthor.toString(),
					"Verifying the similar author affiliation block authors is present in the Google Scholar section on Artical page");
			Allure.step(
					"Verifying the similar author affiliation block authors is present in the Google Scholar section on Artical page.");

			// Verifying Similar article hyper link present in Google scholar section and
			// similar article open when click on it
			soft.assertEquals(articleCitationPage.verifySimilarArtcileHyperLinkPresentOnArticlePage(), true,
					"Verifying the Similar Artcile Hyper Link is present in the Google Scholar section on Artical page");
			Allure.step(
					"Verifying the Similar Artcile Hyper Link is present in the Google Scholar section on Artical page.");
			articleCitationPage.clickOnSimilarArticleInGoogleScholarHyperLink();
			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL("scholar"), "Verifying if google scholar tab is opened");
			Allure.step("Verifying if google scholar tab is opened.");

			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

			// Verifying similar author article open in new tab when clicked on author in
			// Google scholar section
			String[] author = articleCitationPage.getFirstAuthorNamesFromGoogleScholarSectionOnArticlePage().split(" ");
			List<String> authorText = Helper.INSTANCE.convertArrayToList(author);
			String authorFirstName = authorText.get(0);
			articleCitationPage.clickOnFirstAuthorInGoogleScholarSectionOnArticlePage();
			Helper.INSTANCE.switchToWindowTab(1);

			soft.assertTrue(BaseTest.verifyTextInURL(authorFirstName),
					"Verifying Author name in google scholar tab when clicked on author in google schole section on article page");
			Allure.step(
					"Verifying Author name in google scholar tab when clicked on author in google schole section on article page.");
			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

			// Verifying PubMed section section is present on article page
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			soft.assertEquals(articleCitationPage.verifyPubmedButtonPresentOnArticlePage(), true,
					"Verifying the PubMed button is present at right hand side on Artical page");
			Allure.step("Verifying the PubMed button is present at right hand side on Artical page.");

			// Verifying Same author affiliation block authors present in PubMed section
			articleCitationPage.clickOnPubMedButtonOnArticlePage();
			List<String> affiliationBlockAuthorPub = articleCitationPage
					.getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage();
			List<String> pubMedAuthor = articleCitationPage.getAllAuthorNamesFromPubMedSectionOnArticlePage();
			soft.assertEquals(affiliationBlockAuthorPub.toString(), pubMedAuthor.toString(),
					"Verifying the similar author affiliation block authors is present in the PubMed section on Artical page");
			Allure.step(
					"Verifying the similar author affiliation block authors is present in the PubMed section on Artical page.");

			// Verifying similar author article open in new tab when clicked on author in
			// PubMed section
			String[] authorPub = articleCitationPage.getFirstAuthorNamesFromPubMedSectionOnArticlePage().split(" ");
			List<String> authorTextPub = Helper.INSTANCE.convertArrayToList(authorPub);
			String authorFirstNamePub = authorTextPub.get(0);
			articleCitationPage.clickOnFirstAuthorInPubMedSectionOnArticlePage();

			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL(authorFirstNamePub),
					"Verifying Same article open in new PubMed site when clicked on author in PubMed section on article page");
			Allure.step(
					"Verifying Same article open in new PubMed site when clicked on author in PubMed section on article page.");

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void verifyThatTheAutoLaunchNewEmailFunctionality() throws Exception {
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
		articleCitationPage.clickOnArticleInformationTabOnArticlePage();

		// Verifying the Email address is present under contributor Notes section in
		// Article
		// information tab on Article Page
		soft.assertEquals(articleCitationPage.verifyEmailElementIsPresentUnderArticleInformationTabOnArticlePage(),
				true,
				"Verifying the Email address is present under contributor Notes section in Article information tab Article Page");
		Allure.step(
				"Verifying the Email address is present under contributor Notes section in Article information tab Article Page.");

		// Verifying the Email address is hyper link
		soft.assertEquals(articleCitationPage.verifyEmailAddressIsHyperLinkUnderArticleInformationTab(), true,
				"Verying the Email address is hyper link under Article Information tab on article page");
		Allure.step("Verying the Email address is hyper link under Article Information tab on article page.");

	}

	public void verifyContentMetaDataServiceInReferences() {
		try {
			driver.navigate().refresh();
			articleCitationPage.clickOnFullTextOrAbstractTabOnArticlePage();
			// Verifying the Search pubmed link is present under every references on article
			// page and Pubmed site is opened in new tab when user clicked on it.
			soft.assertEquals(testData.get("searchpubmed").toString(),
					articleCitationPage.getFirstSearchPubMedTextUnderReferecesSectionOnArticlePage(),
					"Verifying the Search pubmed link is present under every references on article page.");
			Allure.step("Verifying the Search pubmed link is present under every references on article page.");
			articleCitationPage.clickOnFirstSearchPubMedUnderReferecesSection();
			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL("pubmed"),
					"Verifying pubmed site is opened in new tab when user clicked on the search Pubmed link under references section on Article page");
			Allure.step(
					"Verifying pubmed site is opened in new tab when user clicked on the search Pubmed link under references section on Article page.");
			soft.assertEquals(articleCitationPage.verifyPubmedSiteIsLoaded(), true, "Verifying pubmed site is loaded");
			WebDriverManager.getDriver().close();
			Helper.INSTANCE.switchToWindowTab(0);

			// Verifying the Search Google Scholar link is present under every references on
			// article page and Google Scholar site is opened in new tab when user clicked
			// on it.
			soft.assertEquals(testData.get("searchgooglescholar").toString(),
					articleCitationPage.getFirstSearchGoogleScholarTextUnderReferecesSectionOnArticlePage(),
					"Verifying the Search Google Scholar link is present under every references on article page.");
			Allure.step("Verifying the Search Google Scholar link is present under every references on article page.");
			articleCitationPage.clickOnFirstSearchGoogleScholarUnderReferecesSection();
			Helper.INSTANCE.switchToWindowTab(1);
			soft.assertTrue(BaseTest.verifyTextInURL("scholar"),
					"Verifying Google Scholar site is opened in new tab when user clicked on the search google scholar link under references section on Article page");
			Allure.step(
					"Verifying Google Scholar site is opened in new tab when user clicked on the search google scholar link under references section on Article page.");
			soft.assertEquals(articleCitationPage.verifyGoogleScholarSiteIsLoaded(), true,
					"Verifying Google Scholar site is loaded");
			Allure.step("Verifying Google Scholar site is loaded.");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);
		}
	}

	public void verifyThatDOIShouldBeLinkAndHandlingDOIRedirectFunctionality() throws Exception {
		try {
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
			String DOIArticle = articleCitationPage.getDOIMetaDataOnArticlePage();
			mainWindow = WebDriverManager.getDriver().getWindowHandle();
			soft.assertEquals(articleCitationPage.verifyDOIMetaDataIsLink(), true,
					"Verifying the DOI META data is Link");
			Allure.step("Verifying the DOI META data is Link");
			articleCitationPage.clickOnDOIMETADataOnArticlePage();
			Helper.INSTANCE.switchToWindowTab(WebDriverManager.getDriver(), mainWindow);
			BaseTest.waitUntilTheURLGetLoads("kglmeridian");
			basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
			basePage.clickOnCookiesPopup();
			String DOIMainSite = articleCitationPage.getDOIMetaDataOnArticlePage();
			soft.assertEquals(DOIArticle, DOIMainSite,
					"Verifying that clicking on DOI value in the current environment will take the user to the live environment and should render the same DOI value");
			Allure.step(
					"Verifying that clicking on DOI value in the current environment will take the user to the live environment and should render the same DOI value.");
			String actualArticleTitlewithoutSlash = articleCitationPage.getArticleHeaderOnArticlePage();
			String expectedArticleTitle = testData.get("articletitle").toString();
			soft.assertEquals(actualArticleTitlewithoutSlash, expectedArticleTitle,
					"Verifying the proper content page is displayed if the URL contains only the actual DOI value.");
			Allure.step("Verifying the proper content page is displayed if the URL contains only the actual DOI value.");
			String withExtraSlashDOI = testData.get("withextraslashdoi").toString();
			navigateToUrlLink(url + withExtraSlashDOI);
			String actualArticleTitlewithSlash = articleCitationPage.getArticleHeaderOnArticlePage();
			soft.assertEquals(actualArticleTitlewithSlash, expectedArticleTitle,
					"Verifying that the correct content page is displayed if an extra path is added after the DOI.");
			Allure.step("Verifying that the correct content page is displayed if an extra path is added after the DOI.");
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);
		}
	}

	public void verifyThatTheCitationLinkFunctionalityIsWorkingFine() throws Exception {
		try {
			driver.navigate().refresh();
			String articlePageURL = basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage());

			// Verifying the Citations link is present
			soft.assertEquals(articleCitationPage.verifyCitationsLinkIsPresentInGoofglrScholarSectionOnArticlePage(),
					true, "Verifying the Citations link is present in google scholar section on Article page");
			Allure.step("Verifying the Citations link is present in google scholar section on Article page.");
			articleCitationPage.clickOnCitationsLinkInGoogleScholarSectionOnArticlePage();
			Helper.INSTANCE.switchToWindowTab(1);

			// Verifying the new tab opened after clicking on Citations link.
			soft.assertEquals(Helper.INSTANCE.verifyingNewTabIsOpen(), true,
					"Verifying the new tab opened after clicking on Citation link");
			Allure.step("Verifying the new tab opened after clicking on Citation link");

			// verifying the google scholar search result is displayed with following search
			// query "( https://scholar.google.com/scholar/?=link)"
			soft.assertTrue(BaseTest.verifyTextInURL("https://scholar.google.com/scholar?q=link"),
					"Verifying the google scholar search result is displayed with following search query: https://scholar.google.com/scholar/?=link");
			Allure.step("Verifying the google scholar search result is displayed with following search query: https://scholar.google.com/scholar/?=link");
			String googleScholarPageURL = articleCitationPage.removeContainFromURL(basePage.getURLFromWebPage());

			// Verifying that the new tab is opened and it contains the URL of the article
			// page.
			soft.assertEquals(articlePageURL, googleScholarPageURL,
					"Verifying that the new tab is opened and it contains the URL of the article page.");
			Allure.step("Verifying that the new tab is opened and it contains the URL of the article page.");
			
//			softAssertEquals(WebDriverManager.getDriver(),articlePageURL, googleScholarPageURL,
//					"Verifying that the new tab is opened and it contains the URL of the article page.");

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
			Helper.INSTANCE.switchToWindowTab(0);
		}

	}

}
