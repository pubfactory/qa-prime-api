package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class CopyPreviewCitationTest extends BaseTest {
    private MasterPage masterPage;
    private BasePage basePage;
    private ArticleCitationPage articleCitationPage;
    private BrowseOrSearchPage browseOrSearchPage;
    private String url = "";
    private String testCaseId;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"CopyPreviewCitation"}, enabled = true, retryAnalyzer = Retry.class, description = "1729633  - Verify that User able to perform copy preview citation operation")
    @Story("EPIC-2789")
    public void VerifyThatUserAbleToPerformCopyPreviewCitationOperation() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        //String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();
        articleCitationPage.clickOnCitationButtonOnArticlePage();

        //Verifying the Copy to ClipBoard button is present on preview Export Citation popup
        BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyCopyToClipBoardButtonIsPresentOnPreviewExportCitationPopup(), true,
                "Verifying the Copy to ClipBoard button is present on preview Export Citation popup");

        //Verifying the chakra toast message is displayed
        articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();

        String headLess = BaseTest.properties.getProperty("headLess");
        if (headLess.equalsIgnoreCase("N")) {
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButton(), true,
                    "Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
            articleCitationPage.clickOnToastMessagePopupCloseButton();
        } else {
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButtonInHeadless(), true,
                    "Verifying the chakra toast message is displyed after clinking on the copy to clipboard button on Export Citation popup");
        }

        //Verifying the APA format is displayed on toast message and the APA format is copied and pasted
        articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
        String abbreviatedTitleAPA = articleCitationPage.getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingFormat();
        articleCitationPage.clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup();
        articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.copiedMessagePasteIntoTextBox();
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        String pastedAPAValued = browseOrSearchPage.getRefineTermTextBoxValue();
        // BaseTest.assertEquals(WebDriverManager.getDriver(), abbreviatedTitleAPA, pastedAPAValued, "Verifying the correct format value is copied and pasted");
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.verifyStringContainsSpecificWord(abbreviatedTitleAPA, pastedAPAValued), true, "Verifying the correct format value is copied and pasted");


        //Verifying the AMA format is displayed on toast message and the AMA format is copied and pasted
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
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
        //BaseTest.assertEquals(WebDriverManager.getDriver(), abbreviatedTitleAMA, pastedAMAValued, "Verifying the correct format value is copied and pasted");
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.verifyStringContainsSpecificWord(abbreviatedTitleAMA, pastedAMAValued), true, "Verifying the correct format value is copied and pasted");


    }
}
