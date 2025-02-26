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
    private String last;
    private String first;
    private String firstKeyword;
    private int beforefilterTotalResult;
    private int beforefilterResearchArticleNumber;

    public void testDataInIt(String testCaseId) throws Exception {
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
    }

    /**
     * This method used to initialized the browser
     * 
     * @throws Exception
     */
    public void browserInit() throws Exception {
        url = BaseTest.properties.getProperty(application);
        navigateToUrlLink(url);
        mainWindow = Helper.INSTANCE.getWindow(driver);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);

    }

    // /**
    // *
    // * Returns void Function to verify various Email Button Features
    // *
    // * @author Rakesh.Shevale
    // */
    // public void VerifyVariousEmailButtonFeatures() throws Exception {
    // try {
    // soft = new SoftAssert();
    // driver.navigate().refresh();
    // articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(),
    // ArticleCitationPage.class);
    // String articleText = articleCitationPage.getArticleHeaderOnArticlePage();
    //
    // // Verifying Share via email button and Veriying Share link popup header
    // soft.assertEquals(articleCitationPage.verifyShareViaEmailButtonPresentOnArticlePage(),
    // false,
    // "Verifying the Share via Email button is present on Article Page");
    //
    // Allure.step("Verifying the Share via Email button is present on Article
    // Page");
    //
    // articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
    // soft.assertEquals(articleCitationPage.getShareLinkPopupHeader(),
    // testData.get("sharelinkpopupHeader").toString(),
    // "Verifying the Share Link pop up Header on Article page");
    // Allure.step("Verifying the Share Link pop up Header on Article page");
    //
    // articleCitationPage.clickOnCopyLinkButtonOnShareLinkOnArticlePage();
    //
    // // Verifying Link Copied successfully meassage displayed
    // String directCopyLink =
    // articleCitationPage.getCopyLinkDirectlyOnShareLinkPopupOnArticlePage();
    // articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();
    //
    // // verifying same article open in new tab
    // Helper.INSTANCE.openNewTab();
    // Helper.INSTANCE.switchToWindowTab(1);
    // WebDriverManager.getDriver().navigate().to(directCopyLink);
    // String articleTextInNewTab =
    // articleCitationPage.getArticleHeaderOnArticlePage();
    // soft.assertEquals(articleText, articleTextInNewTab,
    // "Verifying the same article is opened when copy the link from share Link pop
    // up and paste in new tab");
    // Allure.step(
    // "Verifying the same article is opened when copy the link from share Link pop
    // up and paste in new tab");
    //
    // WebDriverManager.getDriver().close();
    // Helper.INSTANCE.switchToWindowTab(0);
    //
    // // Verifying share link popup close button
    // articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
    // articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();
    // articleCitationPage.hoverOnShareViaEmailButton();
    // soft.assertEquals(
    // articleCitationPage.verifyShareLinkPopUpIsNotPresentOnArticlePageAfterClickingCloseButton(),
    // true,
    // "Verifying the Share link pop up is Closed after clicking the close button of
    // share link popup");
    // Allure.step(
    // "Verifying the Share link pop up is Closed after clicking the close button of
    // share link popup");
    // // soft.assertAll();
    // } catch (Exception e) {
    // e.getStackTrace();
    // } finally {
    // Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
    // Helper.INSTANCE.switchToWindowTab(0);
    // }
    // }

    /**
     * Function to verify Drop Down of select issue in journals page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */
    public void selectIssueDDAndVerifyCurrentIssueOnMostRecentVolumeList() throws Exception {
        soft = new SoftAssert();
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        issuePage.clickOnIssueSelector();
        String firstVolume = issuePage.getTotalVolumeListOnJournalPage().get(0);
        String secondRecentVolume = issuePage.getTotalVolumeListOnJournalPage().get(1);
        ArrayList<Integer> volumeList = new ArrayList<>();
        int recentVolumeNum;
        for (int i = 0; i < issuePage.getTotalVolumeListOnJournalPage().size(); i++) {
            recentVolumeNum = issuePage.getVolumeNumberFromSelectIssueDDOnJournalPage(issuePage.getTotalVolumeListOnJournalPage().get(i));
            volumeList.add(recentVolumeNum);
        }
        int largestVolumeNumber = Collections.max(volumeList);
        int latestVolumeNum = issuePage.getVolumeNumberFromSelectIssueDDOnJournalPage(firstVolume);

        // Verify the most recent volume list is displayed at the top.
        assertEqualsoftAssert(soft, driver, largestVolumeNumber, latestVolumeNum, "Verifying the latest volume is displayed on the select issue drop down at the top.");

        issuePage.clickOnVolumeFromSelectIssueDDOnJournalPage(firstVolume);
        Thread.sleep(2000);
        String recentIssue = issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).get(0);
        int recentIssueNum;
        ArrayList<Integer> issueList = new ArrayList<>();
        int largestIssueNumber = 0;
        if (issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).size() >= 1) {
            for (int i = 0; i < issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).size(); i++) {
                recentIssueNum = issuePage.getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(issuePage.getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(firstVolume).get(i));
                issueList.add(recentIssueNum);
            }
            largestIssueNumber = Collections.max(issueList);
        }
        int latestIssueNum = issuePage.getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(recentIssue);
        assertEqualsoftAssert(soft, driver, largestIssueNumber, latestIssueNum, "verifying the recent Issue is displayed under Recent Volume on the select Issue DD at the top.");

        // Verify we expect to be redirected to the latest issue. (Steps 2)
        url = BaseTest.properties.getProperty(application);
        assertEqualsoftAssert(
                soft, driver, Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(basePage.getURLFromWebPage()), Helper.INSTANCE.removeBasicAuthFromURLFromWebAppURL(url) + "view/journals/anpr/"
                        + Integer.toString(latestVolumeNum) + "/" + Integer.toString(latestIssueNum) + "/anpr." + Integer.toString(latestVolumeNum) + ".issue-" + Integer.toString(latestIssueNum) + ".xml",
                "Verifying we expect to be redirected to the latest issue.");
    }

    /**
     * Function to verify if each listing(article) has DOI, Title, Contributor
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyEachListingHasTitleContributorDOIAndAbstractButton() throws Exception {
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        issuePage.clickOnIssueSelector();
        titleList = issuePage.getTotalContentTitleListOnJournalPage();
        int totalTitle = issuePage.getTotalContentTitleListOnJournalPage().size();
        int contributor = issuePage.getTotalContentContributorListOnJournalPage().size();
        int DOI = issuePage.getTotalContentDOIListOnJournalPage().size();
        int abstractButton = issuePage.getTotalContentAbstractButtonListOnJournalPage().size();
//        assertEqualsoftAssert(soft, driver, totalTitle, contributor, "Verifying each listing has a contributor(s).");
        assertEqualsoftAssert(soft, driver, totalTitle, DOI, "Verifying each listing has a DOI.");
        assertEqualsoftAssert(soft, driver, totalTitle, abstractButton, "Verifying each listing has a Abstract Button.");

    }

    /**
     * Function to verify is Access Icon is locked for unauthorized user
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */
    public void verifyAccessIconIsLockedWhenIamNotLoggedIn() throws Exception {
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        String signInText = masterPage.getSignInButtonText();
        String accessIcon = issuePage.getAccessIconValue();
        assertEqualsoftAssert(soft, driver, signInText, "Sign in", "Verifying the user has is not logged In.");

        assertEqualsoftAssert(soft, driver, accessIcon, "Restricted access", "Verifying the access icon is locked when user is not logged in.");
    }

    /**
     * Function to verify the first article loads correctly and verify title.
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyTheFirstArticleLoadsCorrectlyAndClickedLinkMatchesTitleOfLoadedArticle() throws Exception {
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        String actualTitle = issuePage.getFirstArticleTextOnIssuePageHeaderText();
        issuePage.clickOnFirstArticleOnIssuePage();
        String expectedTitle = articleCitationPage.getArticleHeaderOnArticlePage();
        assertEqualsoftAssert(soft, driver, actualTitle, expectedTitle, "Verifying user clicked link matches the title of the loaded article.");

        String actualresult = articleCitationPage.getTheAriaExpandedValueForAbstractTabOrPDFTabIfNoAbstract();
        assertEqualsoftAssert(soft, driver, actualresult, "true", "Verifying user land on abstract tab or the PDF Preview tab if there is no abstract tab.");
    }

    /**
     * Function to verify prev/next navigation for journals page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */
    public void verifyAllArticleAreFromTheIssueWhileNavigatingTheNextPrevControl() throws Exception {
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyArticleAreFromTheIssueWhileNavigatingTheNextArticle(titleList), true,
                "Verifying all the Article are from The Issue while navigating the Next control.");

        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyArticleAreFromTheIssueWhileNavigatingThePrevArticle(titleList), true,
                "Verifying all the Article are from The Issue while navigating the Prev control.");

    }

    /**
     * 
     * Function to verify Full Text Is Navigable Through Article Content.
     * 
     * @throws Exception
     */
    public void verifyFulltextIsNavigableThroughArticleContent() throws Exception {
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        articleCitationPage.clickOnArticleInformationTabOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getTheAriaExpandedValueForarticleInformationTab(), "true", "Verifying user on article information tab.");
        articleCitationPage.clickOnResultsAticleContentLinkOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getTheAriaExpandedValueForFulltextTab(), "true", "Verifying once user clicks on the Results article content link, user navigates on Full Text tab");

        articleCitationPage.clickOnArticleInformationTabOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getTheAriaExpandedValueForarticleInformationTab(), "true", "Verifying user on article information tab.");

        articleCitationPage.clickOnConclusionAticleContentLinkOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getTheAriaExpandedValueForFulltextTab(), "true", "Verifying once user clicks on the Conclusion article content link, user navigates on Full Text tab");
        articleCitationPage.clickOnArticleInformationTabOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getTheAriaExpandedValueForarticleInformationTab(), "true", "Verifying user on article information tab");
        articleCitationPage.clickOnReferenceAticleContentLinkOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getTheAriaExpandedValueForFulltextTab(), "true", "Verifying once user clicks on the References article content link, user navigates on Full Text tab");
    }

    /**
     * Function to verify all figures are loading in the Figure tab in the article
     * page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyAllFiguresAreLoadingInTheFigureTab() throws Exception {
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        articleCitationPage.clickOnFiguresTabOnArticlePage();
        articleCitationPage.verifyAllFiguresAreLoadingInTheFigureTabOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyAllFiguresAreLoadingInTheFigureTabOnArticlePage(), true, "Verifying all figures are loaded in figures tab on article page.");
    }

    /**
     * 
     * Function to verify that Figure Opens in Power point and user is able to
     * download.
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyFigureOpenInPowePointOnceClicksOnItAndAbleToDownloadTheFigureInPPTFormatIfClicksOnDownloadButton() throws Exception {
        try {
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            articleCitationPage.closeHypothesisView();
            articleCitationPage.clickOnSelectFirstFigureFromTheFiguresTabOnArticlePage();

            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyFiguresDiplayedInPPTFormat(), true, "verifying figures is diplayed in PPT format");
            articleCitationPage.clickOnNextButtonForSlidePPT();
            assertEqualsoftAssert(soft, driver, articleCitationPage.getTheSrcAttributeValueOfImageOrFigure(), url + "view/journals/anpr/67/2/full-i0003-3006-67-2-72-f02.png",
                    "verifying the figure changed once user click on next button in slide PPT");

            articleCitationPage.clickOnPreviousButtonForSlidePPT();
            assertEqualsoftAssert(soft, driver, articleCitationPage.getTheSrcAttributeValueOfImageOrFigure(), url + "view/journals/anpr/67/2/full-i0003-3006-67-2-72-f01.png",
                    "Verifying the figure changed once user click on previous button in slide PPT.");
            // clicking on the figure is not working on UAT env right now, that's why this
            // case failing on uat

            articleCitationPage.clickOnSlidePPTCloseButton();
            articleCitationPage.clickOnExportFiguresButtonUnderFigureTab();
            articleCitationPage.clickOnSelectFirstFigureFromTheFiguresTabOnArticlePage();
            articleCitationPage.clickOnFiguresDownloadButton();
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyFiguresIsDownloadedInPPTFormat(".pptx"), true, "Verifying the figures is downloaded in PPT format.");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            deletedownloadedFiles(".pptx");
        }
    }

    /**
     * Function to click on Contributor and perform current site search
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void clickContributorsAndPerformCurrentSiteSearch() throws Exception {
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        String expectedURL=basePage.getURLFromWebPage();
        articleCitationPage.clickOnFirstAuthorBelowTheArticleTitleOnArticlePage();
        articleCitationPage.clickOnCurrentSiteLinkOnAuthorAffiliationPopup();
        String authorEditor = browseOrSearchPage.getFirstAuthorNameOnFirstArticleOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage("author", authorEditor), true,
                "Verifying the once click on Current site, Refine term filter for Author is displayed on browse/search result page.");
        Allure.step("Verifying the once click on Current site, Refine term filter for Author is displayed on browse/search result page.");
        for(int i=0;i<=10;i++) {
			driver.navigate().back();
			String actualURL=basePage.getURLFromWebPage();
			if(expectedURL.equalsIgnoreCase(actualURL)) {
				break;
			}
	}
    }

    /**
     * Function to verify Citation functionality of the article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyCitationFunctionalityAndSelectEachFormatOfCitationAndCopyToClipBoardAndMakeSureItMatchesTheCitationPreview() throws Exception {
        try {
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
            articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();

            // Verifying if the citation button is present in the article page
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyCitationButtonPresentOnArticlePage(), true, "Verifying Citation Button is present.");

            articleCitationPage.clickOnCitationButtonOnArticlePage();
            articleUrl = basePage.getURLFromWebPage();

            // Verifying if citation pop-up is displayed when clicking on the citation
            // button
            assertEqualsoftAssert(soft, driver, articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(), "Verifying the Preview Export Citation popup is displayed");

            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());

            // Verifying if user is able to see RIS,BIB,ENW Button is present under Export
            // citation section.
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the RIS Button is present under Export citation section on Preview Export Citation PopUp");
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the BIB Button is present under Export citation section on Preview Export Citation PopUp");
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the ENW Button is present under Export citation section on Preview Export Citation PopUp");
            articleCitationPage.clickOnRISExportCitationFormat();

            // Verifying if user is able to download RIS,BIB,ENW formats
            assertEqualsoftAssert(soft, driver, articleCitationPage.toVerifyCitationFormatFileIsDownload(".ris"), true, "Verifying the file .ris format file is downloaded");

            deletedownloadedFiles(".ris");
            articleCitationPage.clickOnBIBExportCitationFormat();
            assertEqualsoftAssert(soft, driver, articleCitationPage.toVerifyCitationFormatFileIsDownload(".bib"), true, "Verifying the file .ris format file is downloaded");

            deletedownloadedFiles(".bib");
            articleCitationPage.clickOnENWExportCitationFormat();

            assertEqualsoftAssert(soft, driver, articleCitationPage.toVerifyCitationFormatFileIsDownload(".enw"), true, "Verifying the file .ris format file is downloaded");

            deletedownloadedFiles(".enw");
            List<String> expRISCitationLabels = Arrays.asList(testData.get("risbuttonlabel").toString().split(","));
            List<String> expBIBCitationLabels = Arrays.asList(testData.get("bibbuttonlabel").toString().split(","));
            List<String> expENWCitationLabels = Arrays.asList(testData.get("enwbuttonlabel").toString().split(","));

            assertEqualsoftAssert(soft, driver, articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(), expRISCitationLabels.toString(),
                    "Verifying RIS button Labels on Export Ciatation Popup");
            assertEqualsoftAssert(soft, driver, articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(), expBIBCitationLabels.toString(),
                    "Verifying BIB button Labels on Export Ciatation Popup");

            assertEqualsoftAssert(soft, driver, articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(), expENWCitationLabels.toString(),
                    "Verifying ENW button Labels on Export Ciatation Popup");

            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp(), true,
                    "Verifying Preview Export Citation PopUp Close Button is present on Preview Export Citation PopUp");
            articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();

            // Verifying the copy to clip board functionality (copy citation)
            basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
            articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();
            articleCitationPage.clickOnCitationButtonOnArticlePage();

            // Verifying the Copy to ClipBoard button is present on preview Export Citation
            // popup
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyCopyToClipBoardButtonIsPresentOnPreviewExportCitationPopup(), true,
                    "Verifying the Copy to ClipBoard button is present on preview Export Citation popup");
            articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();

            String headLess = BaseTest.properties.getProperty("headLess");
            if (headLess.equalsIgnoreCase("N")) {
                assertEqualsoftAssert(soft, driver, articleCitationPage.verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButton(), true,
                        "Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");

                articleCitationPage.clickOnToastMessagePopupCloseButton();
            } else {
                assertEqualsoftAssert(soft, driver, articleCitationPage.verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButtonInHeadless(), true,
                        "Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
            }

            // Verifying the APA format is displayed on toast message and the APA format is
            // copied and pasted
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
            String abbreviatedTitleAPA = articleCitationPage.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingFormat();
            articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();
            articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
            masterPage.clickOnSearchMagnifyingLense();
            browseOrSearchPage.copiedMessagePasteIntoTextBox();
            browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
            String pastedAPAValued = browseOrSearchPage.getRefineTermTextBoxValue();

            assertEqualsoftAssert(soft, driver, BaseTest.verifyStringContainsSpecificWord(abbreviatedTitleAPA, pastedAPAValued), true, "Verifying the correct format value is copied and pasted");

            driver.get(articleUrl);

            // Verifying the AMA format is displayed on toast message and the AMA format is
            // copied and pasted
            // masterPage.clickOnSearchMagnifyingLense();
            // browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();
            articleCitationPage.clickOnCitationButtonOnArticlePage();
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());
            String abbreviatedTitleAMA = articleCitationPage.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingFormat();
            articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();
            articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
            masterPage.clickOnSearchMagnifyingLense();
            browseOrSearchPage.copiedMessagePasteIntoTextBox();
            browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
            String pastedAMAValued = browseOrSearchPage.getRefineTermTextBoxValue();

            assertEqualsoftAssert(soft, driver, BaseTest.verifyStringContainsSpecificWord(abbreviatedTitleAMA, pastedAMAValued), true, "Verifying the correct format value is copied and pasted");

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

    /**
     * Function to verify Share Button and its features
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyShareButtonIsPresentAndEachOptionPromptsTheUSerToLogInInToTheRespectiveService() throws Exception {
        try {
            System.out.println("In share test case");
            WebDriverManager.getDriver().get(articleUrl);
            System.out.println("After naviate to the articleUrl");
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            assertEqualsoftAssert(soft, driver, articleCitationPage.getShareButtonTextOnArticlePage(), "Share", "Verifying the share button is present on article page");

            articleCitationPage.clickOnShareButtonOnArticlePage();

            assertTruesoftAssert(soft, driver, articleCitationPage.verifySharingPlatformDataNetworkUnderShareButton("facebook"), "Verifying data-network for facebook attribute is present");

            articleCitationPage.clickingSharingPlatformButtonIsPresentOnArticlePageUnderShareButton("facebook");
            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("facebook"), "Verifying if facebook window is opened");

            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            assertTruesoftAssert(soft, driver, articleCitationPage.verifySharingPlatformDataNetworkUnderShareButton("linkedin"), "Verifying data-network for linkedin attribute is present");

            articleCitationPage.clickingSharingPlatformButtonIsPresentOnArticlePageUnderShareButton("linkedin");
            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("linkedin"), "Verifying if linkedin window is opened");
            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            assertTruesoftAssert(soft, driver, articleCitationPage.verifySharingPlatformDataNetworkUnderShareButton("twitter"), "Verifying data-network for twitter attribute is present");

            articleCitationPage.clickingSharingPlatformButtonIsPresentOnArticlePageUnderShareButton("twitter");
            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("x.com"), "Verifying if twitter window is opened");

            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }

    /**
     * Function to check PDF Download Functionality
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */
    public void verifyPDFButonAvailableAndDownloadPDF() throws Exception {
        try {
            driver.navigate().refresh();
            pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
            assertEqualsoftAssert(soft, driver, pdfPage.verifyPDFButtonPresentOnArticlePage(), true, "Verifying PDF Button is present on the article page.");

            // Verifying Button Downloaded
            String articleHeader = articleCitationPage.getArticleHeaderOnArticlePage();
            pdfPage.clickOnDownloadPDFButtonOnArticlePage();

            assertEqualsoftAssert(soft, driver, pdfPage.toVerifyPDFFIleIsDownload(), true, "Pdf File is downloaded");

            deletedownloadedFiles(".pdf");
            deletedownloadedFiles(".crdownload");

            // Verify Inline PDF tab is diplayed
            assertEqualsoftAssert(soft, driver, pdfPage.verifyInlinePDFTabIsPresentOnArticlePage(), true, "Verifying Inline PDF tab is present on the article page");

            pdfPage.clickOnInlinePdfTabOnArticlePage();
            pdfPage.switchToFrame(WebDriverManager.getDriver());

            // Verifying the Default PDF Zoom value, ZoomIn and Zoom Out button in Inline
            // PDF Tab
            assertEqualsoftAssert(soft, driver, pdfPage.getDefaultPDFZoomValueInInlinePDFTab(), testData.get("defaultzoomvalue").toString(), "Verifying Default zoom value in Inline PDF Tab on article page");

            assertEqualsoftAssert(soft, driver, pdfPage.verifyZoomInButtonIsPresentInInlinePDFTabOnArticlePage(), true, "Verifying ZoomIn button is present on Inline PDF tab");

            assertEqualsoftAssert(soft, driver, pdfPage.verifyZoomOutButtonIsPresentInInlinePDFTabOnArticlePage(), true, "Verifying ZoomOut button is present on Inline PDF tab");

            // Verifying the same article is displayed in PDFViewer in Inline PDF tab
            String partialArticleHeader = pdfPage.getPartialArticleTitleFromInlinePDFTab();
            assertEqualsoftAssert(soft, driver, BaseTest.verifyStringContainsSpecificWord(articleHeader, partialArticleHeader), true, "Verifying that same article is displayed in PDF preview in inline PDF tab");

           // String applicationName = BaseTest.properties.getProperty("application");

            // Verifying the dynamic watermark on pdf preview in Inline pdf tab
            assertEqualsoftAssert(soft, driver, pdfPage.verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(application), true,
                    "Verifying that watermark is present on pdf in Pdf preview in Inline tab on the articla page");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            deletedownloadedFiles(".pdf");
            deletedownloadedFiles(".crdownload");
        }
    }

    /**
     * Function to verify Google Scholar And Pubmed Section Functionality
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     *
     */

    public void verifyGooglescholarAndPubmedSectionFunctionality() {
        try {
            driver.navigate().refresh();
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyGoogleScholarButtonPresentOnArticlePage(), true, "Verifying the Google Scholar button is present at right hand side on Artical page");

            // Verifying Same author affiliation block authors present in Google scholar
            // section
            articleCitationPage.clickOnGoogleScholarkButtonOnArticlePage();
            List<String> affiliationBlockAuthor = articleCitationPage.getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage();
            List<String> googleScholarAuthor = articleCitationPage.getAllAuthorNamesFromAuthorGoogleScholarSectionOnArticlePage();
            assertEqualsoftAssert(soft, driver, affiliationBlockAuthor.toString(), googleScholarAuthor.toString(),
                    "Verifying the similar author affiliation block authors is present in the Google Scholar section on Artical page");

            // Verifying Similar article hyper link present in Google scholar section and
            // similar article open when click on it
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifySimilarArtcileHyperLinkPresentOnArticlePage(), true,
                    "Verifying the Similar Artcile Hyper Link is present in the Google Scholar section on Artical page");

            articleCitationPage.clickOnSimilarArticleInGoogleScholarHyperLink();
            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("scholar"), "Verifying if google scholar tab is opened");

            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            // Verifying similar author article open in new tab when clicked on author in
            // Google scholar section
            String[] author = articleCitationPage.getFirstAuthorNamesFromGoogleScholarSectionOnArticlePage().split(" ");
            List<String> authorText = Helper.INSTANCE.convertArrayToList(author);
            String authorFirstName = authorText.get(0);
            articleCitationPage.clickOnFirstAuthorInGoogleScholarSectionOnArticlePage();
            Helper.INSTANCE.switchToWindowTab(1);

            soft.assertTrue(BaseTest.verifyTextInURL(authorFirstName), "Verifying Author name in google scholar tab when clicked on author in google schole section on article page");
            Allure.step("Verifying Author name in google scholar tab when clicked on author in google schole section on article page.");
            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            // Verifying PubMed section section is present on article page
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyPubmedButtonPresentOnArticlePage(), true, "Verifying the PubMed button is present at right hand side on Artical page");

            // Verifying Same author affiliation block authors present in PubMed section
            articleCitationPage.clickOnPubMedButtonOnArticlePage();
            List<String> affiliationBlockAuthorPub = articleCitationPage.getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage();
            List<String> pubMedAuthor = articleCitationPage.getAllAuthorNamesFromPubMedSectionOnArticlePage();
            assertEqualsoftAssert(soft, driver, affiliationBlockAuthorPub.toString(), pubMedAuthor.toString(), "Verifying the similar author affiliation block authors is present in the PubMed section on Artical page");

            // Verifying similar author article open in new tab when clicked on author in
            // PubMed section
            String[] authorPub = articleCitationPage.getFirstAuthorNamesFromPubMedSectionOnArticlePage().split(" ");
            List<String> authorTextPub = Helper.INSTANCE.convertArrayToList(authorPub);
            String authorFirstNamePub = authorTextPub.get(0);
            articleCitationPage.clickOnFirstAuthorInPubMedSectionOnArticlePage();

            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL(authorFirstNamePub), "Verifying Same article open in new PubMed site when clicked on author in PubMed section on article page");
            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * function to verify auto launch New Email functionality
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyThatTheAutoLaunchNewEmailFunctionality() throws Exception {
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        System.out.println("Going to click Article Information");
        articleCitationPage.clickOnArticleInformationTabOnArticlePage();
        System.out.println("Clicked on Article Information");

        // Verifying the Email address is present under contributor Notes section in
        // Article
        // information tab on Article Page
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyEmailElementIsPresentUnderArticleInformationTabOnArticlePage(), true,
                "Verifying the Email address is present under contributor Notes section in Article information tab Article Page");

        // Verifying the Email address is hyper link
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyEmailAddressIsHyperLinkUnderArticleInformationTab(), true, "Verying the Email address is hyper link under Article Information tab on article page");
    }

    /**
     * Function to verify various content meta data in references.
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */

    public void verifyContentMetaDataServiceInReferences() {
        try {
            driver.navigate().refresh();
            articleCitationPage.clickOnFullTextOrAbstractTabOnArticlePage();
            // Verifying the Search pubmed link is present under every references on article
            // page and Pubmed site is opened in new tab when user clicked on it.
            assertEqualsoftAssert(soft, driver, testData.get("searchpubmed").toString(), articleCitationPage.getFirstSearchPubMedTextUnderReferecesSectionOnArticlePage(),
                    "Verifying the Search pubmed link is present under every references on article page.");

            articleCitationPage.clickOnFirstSearchPubMedUnderReferecesSection();
            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("pubmed"), "Verifying pubmed site is opened in new tab when user clicked on the search Pubmed link under references section on Article page");

            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyPubmedSiteIsLoaded(), true, "Verifying pubmed site is loaded");

            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            // Verifying the Search Google Scholar link is present under every references on
            // article page and Google Scholar site is opened in new tab when user clicked
            // on it.
            assertEqualsoftAssert(soft, driver, testData.get("searchgooglescholar").toString(), articleCitationPage.getFirstSearchGoogleScholarTextUnderReferecesSectionOnArticlePage(),
                    "Verifying the Search Google Scholar link is present under every references on article page.");
            articleCitationPage.clickOnFirstSearchGoogleScholarUnderReferecesSection();
            Helper.INSTANCE.switchToWindowTab(1);
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("scholar"),
                    "Verifying Google Scholar site is opened in new tab when user clicked on the search google scholar link under references section on Article page");
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyGoogleScholarSiteIsLoaded(), true, "Verifying Google Scholar site is loaded");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }

    /**
     * Function to verify DOI functionality and its redirect functionality
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     */
    public void verifyThatDOIShouldBeLinkAndHandlingDOIRedirectFunctionality() throws Exception {
        try {
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            String DOIArticle = articleCitationPage.getDOIMetaDataOnArticlePage();
            mainWindow = WebDriverManager.getDriver().getWindowHandle();
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyDOIMetaDataIsLink(), true, "Verifying the DOI META data is Link");
            articleCitationPage.clickOnDOIMETADataOnArticlePage();
            Helper.INSTANCE.switchToWindowTab(WebDriverManager.getDriver(), mainWindow);
            BaseTest.waitUntilTheURLGetLoads("kglmeridian");
            basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
            basePage.clickOnCookiesPopup();
            String DOIMainSite = articleCitationPage.getDOIMetaDataOnArticlePage();
            assertEqualsoftAssert(soft, driver, DOIArticle, DOIMainSite, "Verifying that clicking on DOI value in the current environment will take the user to the live environment and should render the same DOI value");
            String actualArticleTitlewithoutSlash = articleCitationPage.getArticleHeaderOnArticlePage();
            String expectedArticleTitle = testData.get("articletitle").toString();
            assertEqualsoftAssert(soft, driver, actualArticleTitlewithoutSlash, expectedArticleTitle, "Verifying the proper content page is displayed if the URL contains only the actual DOI value.");
            String withExtraSlashDOI = testData.get("withextraslashdoi").toString();
            navigateToUrlLink(url + withExtraSlashDOI);
            String actualArticleTitlewithSlash = articleCitationPage.getArticleHeaderOnArticlePage();
            assertEqualsoftAssert(soft, driver, actualArticleTitlewithSlash, expectedArticleTitle, "Verifying that the correct content page is displayed if an extra path is added after the DOI.");
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }

    /**
     * Function to verify Citations Link in Google Scholar Section.
     * 
     * @throws Exception
     * @author Rakesh.Shevale 
     */
    public void verifyThatTheCitationLinkFunctionalityIsWorkingFine() throws Exception {
        try {
            driver.navigate().refresh();
            String articlePageURL = basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage());

            // Verifying the Citations link is present
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyCitationsLinkIsPresentInGoofglrScholarSectionOnArticlePage(), true,
                    "Verifying the Citations link is present in google scholar section on Article page");
            articleCitationPage.clickOnGoogleScholarkButtonOnArticlePage();
            articleCitationPage.clickOnCitationsLinkInGoogleScholarSectionOnArticlePage();
            Helper.INSTANCE.switchToWindowTab(1);

            // Verifying the new tab opened after clicking on Citations link.
            assertEqualsoftAssert(soft, driver, Helper.INSTANCE.verifyingNewTabIsOpen(), true, "Verifying the new tab opened after clicking on Citation link");

            // verifying the google scholar search result is displayed with following search
            // query "( https://scholar.google.com/scholar/?=link)"
            assertTruesoftAssert(soft, driver, BaseTest.verifyTextInURL("https://scholar.google.com/scholar?q=link"),
                    "Verifying the google scholar search result is displayed with following search query: https://scholar.google.com/scholar/?=link");
            String googleScholarPageURL = articleCitationPage.removeContainFromURL(basePage.getURLFromWebPage());

            // Verifying that the new tab is opened and it contains the URL of the article
            // page.
            assertEqualsoftAssert(soft, driver, articlePageURL, googleScholarPageURL, "Verifying that the new tab is opened and it contains the URL of the article page.");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }

    public void verifyCurrentIssueVolumeOnHomeIsSameCurrentIssueIOnIssuePage() throws Exception {
        soft = new SoftAssert();
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        String CurrentIssueVolumeOnHomePage = masterPage.getCurrentIssueVolumeTextOnHomePage();
        System.out.println("CurrentIssueVolumeOnHomePage : " + CurrentIssueVolumeOnHomePage);
        masterPage.clickOnViewThisIssueOnHomePage();
        String CurrentIssueVolumeOnIssuePage = issuePage.getCurrentIssueVolumeTextOnIssuePage();
        System.out.println("CurrentIssueVolumeOnIssuePage : " + CurrentIssueVolumeOnIssuePage);
        assertEqualsoftAssert(soft, driver, CurrentIssueVolumeOnHomePage, CurrentIssueVolumeOnIssuePage,
                "Verifying that once user clicks on the latest issue link [view this issue] user will navigate to current issue page which is same as Volume/Issue list as the current one on the homepage");
    }

    public void verifyHowWellSearchRefinementWorks() throws Exception {
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        url = BaseTest.properties.getProperty(application);
        String actualSearch = testData.get("searchkeyword").toString();
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());
        masterPage.clickOnSearchMagnifyingLense();
        beforefilterTotalResult = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        beforefilterResearchArticleNumber = browseOrSearchPage.getNumberOfFilteredResultsFrontOfArticleFilterValueOnBrowseOrSearchPage(testData.get("articletypevalue").toString());
        String searchURL = basePage.getURLFromWebPage();
        assertEqualsoftAssert(soft, driver, searchURL, url + "search?q[0]=" + actualSearch, "Verifying I do a broad term search with the word sedation so I can see how well search refinement works");
    }

    public void verifyIfUseTheRefineTermsToGetMoreSpecificInformationForSedationOfYoungerPatients() throws Exception {
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselecttwo").toString(), testData.get("refinefilteroptionfulltext").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueentertwo").toString(), testData.get("refinefiltervaluefulltexttwo").toString());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        assertEqualsoftAssert(soft, driver, browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage("fulltext", testData.get("refinefiltervaluefulltexttwo").toString()), true,
                "Verifying the if the use Refine Terms to get more specific information for sedation of younger patients and the filter for same creating on search results page.");
    }

    public void verifyRefineAllResultsToResearchArticlesFromByArticleTypeFilterWithinTheLastFourYears() throws Exception {
        browseOrSearchPage.clickOnArticleTypeFilterValueOnBrowseOrSearchPage(testData.get("articletypevalue").toString());
        assertEqualsoftAssert(soft, driver, browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage("By Article Type", testData.get("articletypevalue").toString()), true,
                "Verifying the if the user clicks on the Research article from By Article type filter and By article type filter for same value is created on search results page.");
        String four = browseOrSearchPage.getTotalDateListFromRefineByDateFilter().get(browseOrSearchPage.getTotalDateListFromRefineByDateFilter().size() - 4);
        System.out.println("four : " + four);
        last = browseOrSearchPage.getTotalDateListFromRefineByDateFilter().get(browseOrSearchPage.getTotalDateListFromRefineByDateFilter().size() - 1);
        System.out.println("last : " + last);
        browseOrSearchPage.selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(four);
        browseOrSearchPage.selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(last);
        browseOrSearchPage.clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage();

        assertEqualsoftAssert(soft, driver, browseOrSearchPage.VerifyRefineByDateFilterSearchSlugIsPresentOSearchPage(four, last), true,
                "Verifying the if the last four year filter is appliad from refie by date filter, the filter for same value is created on search results page.");
        Thread.sleep(5000);
    }

    public void verifyPageThroughThe3PagesOfResultsAndMakeSureTheNumberOfReturnedResultsMatchTheNumberOfResultsOnTheSearchPageAndInTheFilters() throws Exception {
        int totalResultPagInation = browseOrSearchPage.getTotalResultFromPageInation();
        int totalResult = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        int filterResult = browseOrSearchPage.getNumberOfFilteredResultsFrontOfArticleFilterValueOnBrowseOrSearchPage(testData.get("articletypevalue").toString());
        assertEqualsoftAssert(soft, driver, totalResultPagInation, totalResult,
                "Verifying page through the 3 pages of results and make sure the number of returned results match the number of results on the search page.");
        assertEqualsoftAssert(soft, driver, totalResultPagInation, filterResult, "Verifying page through the 3 pages of results and make sure the number of returned results match the number of results in the filters.");
    }

    public void verifyIncreaseTheNumberOfResultsPerPageTo50SoNoLongerHaveAnyPagination() throws Exception {
        browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(testData.get("pageperitem").toString());
        driver.navigate().refresh();
        assertEqualsoftAssert(soft, driver, browseOrSearchPage.verifyPagInationIsNotPresentOnBrowseOrSearchPage(), true, "verifying the pagInation is not present on Search or browse page.");
    }

    public void verifyDefaultSortOrderIsRelevanceAndChangedToASCAndDESCAgainSetToBack() throws Exception {
        browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage("10");
        assertEqualsoftAssert(soft, driver, browseOrSearchPage.verifyRelevanceIsSelectedInTheSortByDDOnBrowseOrSearchPage(), true, "veriying the relevance is selected in sort by dd ");
        driver.navigate().refresh();
        String beforeAsc = browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage();
        System.out.println("beforeAsc : " + beforeAsc);
        browseOrSearchPage.SelectSortDateAscFromSortByDropdownOnSearchOrBrowsePage();
        driver.navigate().refresh();
        List<String> dateListASC = browseOrSearchPage.getPublicationDateYearForAllContentOnsarchResultsPage();
        assertEqualsoftAssert(soft, driver, dateListASC.toString(), Helper.INSTANCE.sortListAscending(dateListASC).toString(), "Verifying the All content are sorted in Ascending order after applied sort by dd");

        browseOrSearchPage.SelectSortDateDescFromSortByDropdownOnSearchOrBrowsePage();
        driver.navigate().refresh();
        List<String> dateListDSC = browseOrSearchPage.getPublicationDateYearForAllContentOnsarchResultsPage();
        assertEqualsoftAssert(soft, driver, dateListDSC.toString(), Helper.INSTANCE.sortListDescending(dateListDSC).toString(), "Verifying the All content are sorted in Descending order after applied sort by dd");

        browseOrSearchPage.SelectRelevanceFromSortByDropdownOnSearchOrBrowsePage();
        driver.navigate().refresh();
        waitForLoad(driver);
        String AfterRelevance = browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage();
        System.out.println("AfterRelevance : " + AfterRelevance);
        assertEqualsoftAssert(soft, driver, AfterRelevance, beforeAsc, "Verifying after applied the relevance in sort by , the set all order back to original sort order");
    }

    public void clearAllAppliedfilterOnBrowsePage() throws Exception {
        browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(last);
        browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(testData.get("articletypevalue").toString());
        browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(testData.get("refinefiltervaluefulltexttwo").toString());

        driver.navigate().refresh();
        int afterRemovedFilterResearchArticleNumber = browseOrSearchPage.getNumberOfFilteredResultsFrontOfArticleFilterValueOnBrowseOrSearchPage(testData.get("articletypevalue").toString());
        int afterRmovedFilterTotalResult = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        assertEqualsoftAssert(soft, driver, beforefilterTotalResult, afterRmovedFilterTotalResult, "Verifying the total count is upadted after removing the applied filters.");
        assertEqualsoftAssert(soft, driver, beforefilterResearchArticleNumber, afterRemovedFilterResearchArticleNumber, "Verifying the total count for the filter is upadted after removing the applied filters.");
    }

    public void verifyTheRightOpenAccessArticleIsLoaded() throws Exception {
        browseOrSearchPage.ScrollAndClickOnOpenAccessArticle(testData.get("openaccesstitle").toString());
        String articleURL = basePage.getURLFromWebPage();
        assertEqualsoftAssert(soft, driver, url + testData.get("openaccesurl").toString(), articleURL, "Verifying the proper article page is displayed.");
    }

    public void clickOnContributorOfArticleAndVerifyMoreInformationAboutThem() throws Exception {
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        String firstAuthorName = articleCitationPage.getFirstAuthorNameText();
        articleCitationPage.clickOnFirstAuthorBelowTheArticleTitleOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyAuthorAffiliationPopUpIsDisplayed(), true, "Verifying the Affilaition popup is displayed aftet clicking on the author name.");
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifySameAuthornameIsPresentOnAuthorAffiliationPopup(firstAuthorName), true, "Verifying the same author name is present on the popup.");
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyMoreInformationIsPresentOnAuthorAffiliationPopup(), true, "Verifying the more informaion is present on the popup.");
    }

    public void verifyInFullTextTabContentIsAvailbale() throws Exception {
        assertEqualsoftAssert(soft, driver, articleCitationPage.getReferenceTextFromTheFulltextTab(), testData.get("referencestext").toString(), "Verifying the referenes header is present in fulttext tab.");
        assertEqualsoftAssert(soft, driver, articleCitationPage.getDownloadPDFTextFromTheFulltextTab(), testData.get("keywordstext").toString(), "Verifying the download PDF button is present in fulttext tab.");
        assertEqualsoftAssert(soft, driver, articleCitationPage.getKeywordsTextFromTheFulltextTab(), testData.get("downloadpdf").toString(), "Verifying the Keyword text is present in fulttext tab.");

    }

    public void clickOnKeywordsAndGetNewSearchesForThatKeywordsAgainClickOnBackButtonAndVerifyRetrunedToArticlePage() throws Exception {
        firstKeyword = articleCitationPage.getFirstKeywordsTextFromTheFulltextTab();
        articleCitationPage.clickOnFirstKeywordInFulltextTabOnArticlePage();
        assertEqualsoftAssert(soft, driver, browseOrSearchPage.verifySearchSlugSignIsPresentOnBrowseOrSearchPage(firstKeyword), true,
                "Verifying the new search and new filter is created for first keywords on Search Results Page.");
        WebDriverManager.getDriver().navigate().back();
        String articleUrl = basePage.getURLFromWebPage();
        assertEqualsoftAssert(soft, driver, url + testData.get("openaccesurl").toString(), articleUrl, "Verifying the after clicking the back button, article page is displayed.");
    }

    public void clickOnPDFTabAndScanTheInlinePDF() throws Exception {
	driver.navigate().refresh();
        String articleHeader = articleCitationPage.getArticleHeaderOnArticlePage();
        pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
        articleCitationPage.clickOnPDFTabOnArticlePage();
        pdfPage.switchToFrame(WebDriverManager.getDriver());
        String partialArticleHeader = pdfPage.getPartialArticleTitleFromInlinePDFTab();
        String allKeywords = pdfPage.getKeywordsFromThePDFTab();
        assertEqualsoftAssert(soft, driver, BaseTest.verifyStringContainsSpecificWord(articleHeader, partialArticleHeader), true, "Verifying that same article is displayed in inline PDF in PDF tab");
       // String applicationName = BaseTest.properties.getProperty("application");
        assertEqualsoftAssert(soft, WebDriverManager.getDriver(), pdfPage.verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(application), true,
                "Verifying that watermark is present on inline pdf in Pdf tab on the articla page");
        assertEqualsoftAssert(soft, driver, BaseTest.verifyStringContainsSpecificWord(allKeywords, firstKeyword), true, "Verifying that same keyword is displayed in inline PDF in PDF tab");
    }

    public void clickOnFiguresTabScanTheFigures() throws Exception {
        driver.navigate().refresh();
        articleCitationPage.clickOnFiguresTabOnArticlePage();
        Thread.sleep(4000);
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyAllFiguresAreLoadingInTheFigureTabOnArticlePage(), true, "Verifying all figures are loaded in figures tab on article page.");
    }

    public void selectOneFigureAndExportItAsPPT() {
        try {
            articleCitationPage.clickOnExportFiguresButtonUnderFigureTab();
            articleCitationPage.clickOnSelectFirstFigureFromTheFiguresTabOnArticlePage();
            articleCitationPage.clickOnFiguresDownloadButton();
            assertEqualsoftAssert(soft, driver, articleCitationPage.verifyFiguresIsDownloadedInPPTFormat(".pptx"), true, "Verifying the figures is downloaded in PPT format.");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            deletedownloadedFiles(".pptx");
        }
    }

    public void clickOnArticleInformationTabandScanTheSection() throws Exception {
        articleCitationPage.clickOnArticleInformationTabOnArticlePage();
        assertEqualsoftAssert(soft, driver, articleCitationPage.getContributorNotesTextFromArticleInformationTab(), testData.get("contributornotes").toString(),
                "Verifying the contributor header is present in Article Information tab.");
        assertEqualsoftAssert(soft, driver, articleCitationPage.verifyEmailElementIsPresentUnderArticleInformationTabOnArticlePage(), true,
                "Verifying the contributor email addess is present in Article Information tab.");
    }

//    public void descriptionMetaTag() throws Exception {
//        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
//        BaseTest.assertEquals(driver, masterPage.VerifyDescriptionMetaTagisPresentOnHomePage(), true, "Verifying the Description meta tag is present on the home page");
//        BaseTest.assertEquals(driver, masterPage.getDescriptionMetaTagPropertyValue(), "anesthesiaprogress", "Verifying the Description meta tag content atribute value");
//    }

    public void verifyTheSSRIsLoaded() throws Exception {
    	soft = new SoftAssert();
    	String pageSource=driver.getPageSource();
    	System.out.println(" return : "+pageSource.contains("SSR, generated by the SSR"));
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), pageSource.contains("SSR, generated by the SSR"), true,"verifying the server side is rendering.");
//    	 BaseTest.assertEquals(WebDriverManager.getDriver(), pageSource.contains("Computer Okay"), true,"verifying the server side is rendering.");
    }
    
    public void clicKOnTheFirstArticleFromOpenAccessArticle() throws Exception{
    	 masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
    	 masterPage.clickOnThefirstArticleFromTheOpenAccessArticles();
    }
    
    public void verifytheMetaTagsForArticlePage() throws Exception {
       	
    	articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.ogURLMetaTagisPresentOnArticlePage(), true,"verifying the og URL meta tag is present on article page when enabled SSR.");
    	String ogURL =basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage());
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getOgURLMetaTagPropertyValue(),ogURL.replace("https:", "http:"),"verifying the og URL meta tag with content value is present on article page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.ogSiteNameMetaTagisPresentOnArticlePage(), true, "verifying the og site name meta tag is present on article page when enabled SSR.");
    	String ogSiteName = testData.get("ogsitename").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getOgSiteNameMetaTagPropertyValue(),ogSiteName, "verifying the og site name meta tag with content value is present on article page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.ogTypeMetaTagisPresentOnArticlePage(), true, "verifying the og Type meta tag is present on article page when enabled SSR.");
    	String ogType = testData.get("ogtype").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getOgTypeMetaTagPropertyValue(),ogType, "verifying the og Type meta tag with content value is present on article page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.ogLocaleMetaTagisPresentOnArticlePage(), true, "verifying the og locale meta tag is present on article page when enabled SSR.");
    	String ogLocale = testData.get("oglocale").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getOgLocaleMetaTagPropertyValue(),ogLocale, "verifying the og locale meta tag with content value is present on article page when enabled SSR.");

    	
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.ogImageMetaTagisPresentOnHomePage(), true, "verifying the og Image meta tag is present on home page when enabled SSR.");
//    	String ogImage = testData.get("ogimagearticle").toString();
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgImageMetaTagPropertyValue(),ogURL+ogImage, "verifying the og Image meta tag with content value is present on home page when enabled SSR.");
//
//    	System.out.println("Actual : "+ masterPage.getOgImageMetaTagPropertyValue());
//    	System.out.println("Expected : "+ ogURL+ogImage);
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyTwitterCardMetaTagisPresentOnArticlePage(), true,"verifying the twitter card meta tag is present on article page when enabled SSR.");
    	String twitterCard=testData.get("twittercard").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getTwitterCardMetaTagPropertyValue(),twitterCard,"verifying the twitter card meta tag with content value is present on article page when enabled SSR.");
    	   	   	 	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyTwitterTitleMetaTagisPresentOnArticlePage(), true,"verifying the twitter title meta tag is present on article page when enabled SSR.(property attribute)");
    	String twittertitleProp=testData.get("twittertitleprop").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getTwitterTitleMetaTagPropertyValue(),twittertitleProp,"verifying the twitter title meta tag with content value is present on article page when enabled SSR.(property attribute)");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyTwitterTitleSSRMetaTagisPresentOnArticlePage(), true, "verifying the twitter title meta tag is present on article page when enabled SSR.(name attribute)");
    	String twittertitleName = testData.get("twittertitlename").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getTwitterTitleSSRMetaTagPropertyValue(), twittertitleName, "verifying the twitter title meta tag with content value is present on article page when enabled SSR.(name attribute)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyOgDescriptionMetaTagisPresentOnArticlePage(), true, "verifying the og description meta tag is present on article page when enabled SSR.(first index)");
    	String ogdescOne = testData.get("ogdescriptionarticleone").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getOgDescriptionMetaTagPropertyValue(), ogdescOne, "verifying the og description meta tag with content value is present on article page when enabled SSR.(first index)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyOgDescriptionSecondIndexMetaTagisPresentOnArticlePage(), true, "verifying the og description meta tag is present on article page when enabled SSR.(second index)");
    	String ogdescTwo = testData.get("ogdescriptionarticletwo").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), verifyStringContainsSpecificWord(articleCitationPage.getOgDescriptionSecondIndexMetaTagPropertyValue(), ogdescTwo), true, "verifying the og description meta tag with content value is present on article page when enabled SSR.(second index)");
	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyDescriptionFirstIndexNameAttributeMetaTagisPresentOnArticlePage(), true, "verifying the description name attribute meta tag is present on article page when enabled SSR.(first index)");
    	String descriptionOneName = testData.get("descriptionnameattrione").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getDescriptionFirstIndexNameAttrubuteMetaTagValue(), descriptionOneName, "verifying the description name attribute meta tag with content value is present on artile page when enabled SSR.(first index)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyDescriptionSecondIndexNameAttributeMetaTagisPresentOnArticlePage(), true, "verifying the description name attribute meta tag is present on article page when enabled SSR.(second index)");
    	String descriptionTwoName = testData.get("descriptionnameattritwo").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getDescriptionScondIndexNameAttrubuteMetaTagValue(), descriptionTwoName, "verifying the description name attribute meta tag with content value is present on article page when enabled SSR.(second index)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyDescriptionThirdIndexNameAttributeMetaTagisPresentOnArticlePage(), true, "verifying the description name attribute meta tag is present on article page when enabled SSR.(second index)");
    	String descriptionThreeName = testData.get("descriptionnameattrithree").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), verifyStringContainsSpecificWord(articleCitationPage.getDescriptionThirdIndexNameAttrubuteMetaTagValue(), descriptionThreeName),true, "verifying the description name attribute meta tag with content value is present on article page when enabled SSR.(second index)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyTwitterDescriptionSSRAttributeMetaTagisPresentOnArticlePage(), true, "verifying the twitter description meta tag name attribute is present on article page when enabled SSR.");
    	String twitterDescriptionName = testData.get("twitterdescriptionarticlename").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), verifyStringContainsSpecificWord(articleCitationPage.getTwitterDescriptionSSRMetaTagPropertyValue(),twitterDescriptionName), true, "verifying the twitter description meta tag name attribute with content value is present on article page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyTwitterDescriptionMetaTagisPresentOnArticlePage(), true, "verifying the twitter description meta tag property attribute is present on article page when enabled SSR.");
    	String twitterDescriptionProp = testData.get("twitterdescriptionarticleprop").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.getTwitterDescriptionMetaTagPropertyValue(),twitterDescriptionProp , "verifying the twitter description meta tag property attribute with content value is present on article page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyDcIdentifierMetaTagisPresentOnArticlePage(), true, "verifying the dc identifier meta tag is present on article page when enabled SSR.");
    	String dcIdentifier = testData.get("dcidentifier").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.getDcidentifierMetaTagValue(),dcIdentifier , "verifying the dc identifier meta tag with content value is present on article page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyOgTitleMetaTagisPresentOnArticlePage(), true, "verifying the og title meta tag is present on article page when enabled SSR.");
    	String ogtitlearticle = testData.get("ogtitlearticle").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.getOgTitleMetaTagPropertyValue(),ogtitlearticle , "verifying the og title meta tag with content value is present on article page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyArticleAuthorMetaTagisPresentOnArticlePage(), true,"verifying the artcile author meta tag is present on article page when enabled SSR.");
    	String articleauthor=testData.get("articleauthor").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getArticleAuthorMetaTagProperytValue(),articleauthor,"verifying the article author meta tag with content value is present on article page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyArticlePublishTimeMetaTagisPresentOnArticlePage(), true,"verifying the article publish time date meta tag is present on article page when enabled SSR.");
//    	String articlepublishtime=testData.get("articlepublishtime").toString();
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getArticlePublishTimeMetaTagPropertyValue(),articlepublishtime,"verifying the article publish time meta tag with content value is present on article page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyArticleSectionMetaTagisPresentOnArticlePage(), true,"verifying the article section meta tag is present on article page when enabled SSR.");
    	String articlesection=testData.get("articlesection").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getArticleSectionMetaTagPropertyValue(),articlesection,"verifying the article section meta tag with content value is present on article page when enabled SSR.");   	
    	    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationLastPageMetaTagisPresentOnArticlePage(), true,"verifying the citation last page meta tag is present on artile page when enabled SSR.");
    	String citationLastPage=testData.get("citationlastpage").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationLastPageMetaTagPropertyValue(),citationLastPage,"verifying the citation last page meta tag with content value is present on artile page when enabled SSR.");
    	  	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationIssueMetaTagisPresentOnArticlePage(), true,"verifying the citation issue meta tag is present on artile page when enabled SSR.");
     	String citationIssue=testData.get("citationissue").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationIssueMetaTagPropertyValue(),citationIssue,"verifying the citation issue meta tag with content value is present on artile page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationLanguageMetaTagisPresentOnArticlePage(), true,"verifying the citation language meta tag is present on artile page when enabled SSR.");
     	String citationLanguage=testData.get("citationlanguage").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationLanguageMetaTagPropertyValue(),citationLanguage,"verifying the citation language meta tag with content value is present on artile page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationTitleMetaTagisPresentOnArticlePage(), true,"verifying the citation Title meta tag is present on artile page when enabled SSR.");
     	String citationTitle=testData.get("citationtitle").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationTitleMetaTagPropertyValue(),citationTitle,"verifying the citation Title meta tag with content value is present on artile page when enabled SSR.");
    	
    	basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
    	String[] mainUrl = basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage()).split("/view");  
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationXMLURLMetaTagisPresentOnArticlePage(), true,"verifying the citation XML URL meta tag is present on artile page when enabled SSR.");
      	String xmlURLDoc=testData.get("xmlurlPart").toString();
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationXMLURLMetaTagPropertyValue(),mainUrl[0].replace("https", "http")+xmlURLDoc,"verifying the citation XML URL meta tag with content value is present on artile page when enabled SSR.");
     	
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.verifyCitationVolumeMetaTagisPresentOnArticlePage(), true,"verifying the citation volume meta tag is present on artile page when enabled SSR.");
      	String citationVolume=testData.get("citationvolume").toString();
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationVolumeMetaTagPropertyValue(),citationVolume,"verifying the citation volume meta tag with content value is present on artile page when enabled SSR.");
     	
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationPDFURLMetaTagisPresentOnArticlePage(), true,"verifying the citation PDF meta tag is present on artile page when enabled SSR.");
      	String citationPDF=testData.get("citationpdf").toString();
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationPDFURLMetaTagPropertyValue(),mainUrl[0]+citationPDF,"verifying the citation PDF meta tag with content value is present on artile page when enabled SSR.");
     	
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationISSNMetaTagisPresentOnArticlePage(), true,"verifying the citation ISSN meta tag is present on artile page when enabled SSR.");
      	String citationissn=testData.get("citationissn").toString();
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationISSNMetaTagPropertyValue(),citationissn,"verifying the citation ISSN meta tag with content value is present on artile page when enabled SSR..");
     	
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationISSNSecondIndexMetaTagisPresentOnArticlePage(), true,"verifying the citation ISSN second index meta tag is present on artile page when enabled SSR.");
      	String citationissnSecondIndex=testData.get("citationissnsecondindex").toString();
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationISSNSecondIndexMetaTagPropertyValue(),citationissnSecondIndex,"verifying the citation ISSN second index meta tag with content value is present on artile page when enabled SSR.");
     	
     	
     	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationFirstPageMetaTagisPresentOnArticlePage(), true,"verifying the citation first page meta tag is present on artile page when enabled SSR.");
    	String citatioFirstPage=testData.get("citationfirstpage").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationFirstPagePageMetaTagPropertyValue(),citatioFirstPage,"verifying the citation first page meta tag with content value is present on artile page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationPublisherMetaTagisPresentOnArticlePage(), true,"verifying the citation publisher meta tag is present on artile page when enabled SSR.");
    	String publisher=testData.get("citationpublisher").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationPublisherMetaTagPropertyValue(),publisher,"verifying the citation publisher meta tag with content value is present on artile page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationJournalTitleMetaTagisPresentOnArticlePage(), true,"verifying the citation journal title meta tag is present on artile page when enabled SSR.");
    	String citationjournaltitle=testData.get("citationjournaltitle").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationJournalTilteMetaTagPropertyValue(),citationjournaltitle,"verifying the citation journal title meta tag with content value is present on artile page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationPublicationDateMetaTagisPresentOnArticlePage(), true,"verifying the citation publication date meta tag is present on artile page when enabled SSR.");
//    	String citationPublicationDate=testData.get("citationpublicationdate").toString();
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationPublicationDateMetaTagPropertyValue(),citationPublicationDate,"verifying the citation publication date meta tag with content value is present on artile page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationAuthorMetaTagisPresentOnArticlePage(), true,"verifying the citation author meta tag is present on artile page when enabled SSR.");
    	String citationAuthor=testData.get("citationauthor").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationAuthorMetaTagPropertyValue(),citationAuthor,"verifying the citation author meta tag with content value is present on artile page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationDOIMetaTagisPresentOnArticlePage(), true,"verifying the citation DOI meta tag is present on artile page when enabled SSR.");
    	String citationDOI=testData.get("citationdoi").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getCitationDOIMetaTagPropertyValue(),citationDOI,"verifying the citation DOI meta tag with content value is present on artile page when enabled SSR.");   	
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),articleCitationPage.VerifyArticleTagMetaTagisPresentOnArticlePage(), true, "verifying the article tag meta tag is present on artile page when enabled SSR.");
    	String articleTag = testData.get("articletag").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.getArticleTagMetaTagPropertyValue(),articleTag, "verifying the article tag meta tag with content value is present on artile page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), articleCitationPage.VerifyCitationReferenceMetaTagisPresentOnArticlePage(), true,"verifying the citation references meta tag is present on artile page when enabled SSR.");
    	String citationReference=testData.get("citationreference").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), verifyStringContainsSpecificWord(articleCitationPage.getCitationReferenceMetaTagPropertyValue(),citationReference),true,"verifying the citation references meta tag with content value is present on artile page when enabled SSR.");
      	
    }
    
    public void verifytheMetaTagsForHomePage() throws Exception {
    	
    	masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
    	basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.ogURLMetaTagisPresentOnHomePage(), true, "verifying the og URL meta tag is present on home page when enabled SSR.");
    	String ogURL =basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage());
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgURLMetaTagPropertyValue(),ogURL.replace("https:", "http:"), "verifying the og URL meta tag with content value is present on home page when enabled SSR.");
    	
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.ogSiteNameMetaTagisPresentOnHomePage(), true, "verifying the og site name meta tag is present on home page when enabled SSR.");
    	String ogSiteName = testData.get("ogsitename").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgSiteNameMetaTagPropertyValue(),ogSiteName, "verifying the og site name meta tag with content value is present on home page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.ogTypeMetaTagisPresentOnHomePage(), true, "verifying the og type meta tag is present on home page when enabled SSR.");
    	String ogType = testData.get("ogtype").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgTypeMetaTagPropertyValue(),ogType, "verifying the og type meta tag with content value is present on home page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.ogLocaleMetaTagisPresentOnHomePage(), true, "verifying the og locale meta tag is present on home page when enabled SSR.");
    	String ogLocale = testData.get("oglocale").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgLocaleMetaTagPropertyValue(),ogLocale, "verifying the og locale meta tag with content value is present on home page when enabled SSR.");

//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.ogImageMetaTagisPresentOnHomePage(), true, "verifying the og Image meta tag is present on home page when enabled SSR.");
//    	String ogImage = testData.get("ogimage").toString();
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgImageMetaTagPropertyValue(),ogURL+ogImage, "verifying the og Image meta tag with content value is present on home page when enabled SSR.");
//
//    	System.out.println("Actual : "+ masterPage.getOgImageMetaTagPropertyValue());
//    	System.out.println("Expected : "+ ogURL+ogImage);
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyTwitterCardMetaTagisPresentOnHomePage(), true, "verifying the twitter card meta tag is present on home page when enabled SSR.");
    	String twitterCard = testData.get("twittercard").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getTwitterCardMetaTagPropertyValue(),twitterCard, "verifying the twitter Card meta tag with content value is present on home page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyTwitterTitleMetaTagisPresentOnHomePage(), true, "verifying the twitter Title meta tag is present on home page when enabled SSR.");
    	String twitterTitle = testData.get("twittertitle").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getTwitterTitleMetaTagPropertyValue(),twitterTitle, "verifying the twitter Title meta tag with content value is present on home page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyOgDescriptionMetaTagisPresentOnHomePage(), true, "verifying the og description meta tag is present on artile page when enabled SSR.");
    	String ogDescription = testData.get("ogdescription").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgDescriptionMetaTagPropertyValue(),ogDescription, "verifying the og description meta tag with content value is present on artile page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyDescriptionMetaTagisPresentOnHomePage(), true, "verifying the name description meta tag is present on artile page when enabled SSR.");
    	String description = testData.get("namedescription").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getDescriptionMetaTagPropertyValue(),description, "verifying the name description meta tag with content value is present on artile page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyTwitterDescriptionMetaTagisPresentOnHomePage(), true, "verifying the twitter description meta tag is present on artile page when enabled SSR.");
    	String twitterDescription = testData.get("twitterdescriptionhome").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getTwitterDescriptionMetaTagPropertyValue(),twitterDescription, "verifying the twitter description meta tag with content value is present on artile page when enabled SSR.");

    	
    }
    
    public void verifytheMetaTagsForIssuePage() throws Exception {
    	String url=BaseTest.properties.getProperty(application);
    	String issuURL=testData.get("issuurl").toString();
    	driver.get(url+issuURL);	
    	issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
    	  	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.ogURLMetaTagisPresentOnIssuePage(), true, "verifying the og URL meta tag is present on Issue page when enabled SSR.");
    	String ogURL =basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage());
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getOgURLMetaTagPropertyValue(),ogURL.replace("https:", "http:"), "verifying the og URL meta tag with content value is present on Issue page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.ogSiteNameMetaTagisPresentOnIssuePage(), true, "verifying the og site name meta tag is present on Issue page when enabled SSR.");
    	String ogSiteName = testData.get("ogsitename").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getOgSiteNameMetaTagPropertyValue(),ogSiteName, "verifying the og site name meta tag with content value is present on Issue page when enabled SSR.");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.ogTypeMetaTagisPresentOnIssuePage(), true, "verifying the og type meta tag is present on Issue page when enabled SSR.");
    	String ogType = testData.get("ogtype").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getOgTypeMetaTagPropertyValue(),ogType, "verifying the og type meta tag with content value is present on Issue page when enabled SSR.");
    
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.ogLocaleMetaTagisPresentOnIssuePage(), true, "verifying the og locale meta tag is present on Issue page when enabled SSR.");
    	String ogLocale = testData.get("oglocale").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getOgLocaleMetaTagPropertyValue(),ogLocale, "verifying the og locale meta tag with content value is present on Issue page when enabled SSR.");
    	
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.ogImageMetaTagisPresentOnIssuePage(), true, "verifying the og Image meta tag is present on Issue page when enabled SSR.");
//    	String ogimageIssue = testData.get("ogimageissue").toString();
//    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getOgImageMetaTagPropertyValue(),ogURL+ogimageIssue, "verifying the og Image meta tag with content value is present on Issue page when enabled SSR.");
//
//    	System.out.println("Actual : "+ masterPage.getOgImageMetaTagPropertyValue());
//    	System.out.println("Expected : "+ ogURL+ogimageIssue);
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyTwitterCardMetaTagisPresentOnIssuePage(), true, "verifying the twitter card meta tag is present on Issue page when enabled SSR.");
    	String twitterCard = testData.get("twittercard").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getTwitterCardMetaTagPropertyValue(),twitterCard, "verifying the twitter Card meta tag with content value is present on Issue page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyTwitterTitleMetaTagisPresentOnIssuePage(), true, "verifying the twitter Title meta tag is present on Issue page when enabled SSR.");
    	String twitterTitle = testData.get("twittertitle").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getTwitterTitleMetaTagPropertyValue(),twitterTitle, "verifying the twitter Title meta tag with content value is present on Issue page when enabled SSR.");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyOgDescriptionMetaTagisPresentOnHomePage(), true, "verifying the og description meta tag is present on Issue page when enabled SSR.(index first)");
    	String ogDescriptionOne = testData.get("ogdescriptionissueone").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgDescriptionMetaTagPropertyValue(),ogDescriptionOne, "verifying the og description meta tag with content value is present on Issue page when enabled SSR.(index first)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),masterPage.VerifyOgDescriptionIndexSecodMetaTagisPresentOnHomePage(), true, "verifying the og description meta tag is present on Issue page when enabled SSR.(index second)");
    	String ogDescriptionTwo = testData.get("ogdescriptionissuetwo").toString();
    	String orignalOgDescriptionIssue=ogDescriptionTwo.replaceAll("&quot;", "\"");
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), masterPage.getOgDescriptionIndexSecondMetaTagPropertyValue(),orignalOgDescriptionIssue, "verifying the og description meta tag with content value is present on Issue page when enabled SSR.(index second)");
    	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyDescriptionMetaTagisPresentOnIssuePage(), true, "verifying the description meta tag is present on issue page when enabled SSR.(index one)");
    	String descriptionIssueOne = testData.get("descriptioissuenone").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getDescriptionMetaTagPropertyValue(),descriptionIssueOne, "verifying the description meta tag with content value is present on issue page when enabled SSR.(index one)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyDescriptionNameMetaTagisPresentOnIssuePage(), true, "verifying the description meta tag is present on issue page when enabled SSR.(index two)");
    	String descriptionIssueTwo = testData.get("descriptionissuetwo").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getDescriptionNameMetaTagPropertyValue(),descriptionIssueTwo, "verifying the description meta tag with content value is present on issue page when enabled SSR.(index two)");
 	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyDescriptionSSRMetaTagisPresentOnIssuePage(), true, "verifying the description meta tag is present on issue page when enabled SSR.(index third)");
    	String descriptionIssueThird = testData.get("descriptionissuethird").toString();
    	String orignalDescriptionIssue=descriptionIssueThird.replaceAll("&quot;", "\"");
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getDescriptionSSRMetaTagPropertyValue(),orignalDescriptionIssue, "verifying the description meta tag with content value is present on issue page when enabled SSR.(index third)");
	
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyTwitterDescriptionSSRMetaTagisPresentOnIssuePage(), true, "verifying the twitter description meta tag is present on issue page when enabled SSR.(name attribute)");
    	String twitterDescriptionIssue = testData.get("twitterdescriptionissuename").toString();
    	String orignalTwitterdescriptionissue=twitterDescriptionIssue.replaceAll("&quot;", "\"");
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getTwitterDescriptionSSRMetaTagPropertyValue(),orignalTwitterdescriptionissue, "verifying the twitter description meta tag with content value is present on issue page when enabled SSR.(name attribute)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyTwitterDescriptionMetaTagisPresentOnIssuePage(), true, "verifying the twitter description meta tag is present on issue page when enabled SSR. (property attribute)");
    	String twitterDescriptionIssuename = testData.get("twitterdescriptionissueprop").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getTwitterDescriptionMetaTagPropertyValue(),twitterDescriptionIssuename, "verifying the twitter description meta tag with content value is present on issue page when enabled SSR.(property attribute)");

    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(),issuePage.VerifyOgTitleMetaTagisPresentOnIssuePage(), true, "verifying the og title meta tag is present on issue page when enabled SSR.");
    	String ogTitle = testData.get("ogtitle").toString();
    	assertEqualsoftAssert(soft,WebDriverManager.getDriver(), issuePage.getOgTitleMetaTagPropertyValue(),ogTitle, "verifying the og title meta tag with content value is present on issue page when enabled SSR.");

    	
    }
    
    
    
    /**
     * Function to Assert all soft Assertion
     */
    public void assertClose() {
        soft.assertAll();
    }

}
