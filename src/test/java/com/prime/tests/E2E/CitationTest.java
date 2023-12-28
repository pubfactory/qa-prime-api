/**
 * Testing the below functionality in Cite An Entry 

1. Verifying if the citation button is present in the article page
2. Verifying if citation pop-up is displayed when clicking on the citation button
3. Verifying if user is able to see RIS,BIB,ENW Button is present under Export citation section.
4. Verifying if user is able to download RIS,BIB,ENW formats
5. Verifying if user is able to close citation pop-up after use.

 */
package com.prime.tests.E2E;

import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class CitationTest extends BaseTest {

    private MasterPage masterPage;
    private BasePage basePage;
    private ArticleCitationPage articleCitationPage;
    private BrowseOrSearchPage browseOrSearchPage;
    private String url = "";
    private String testCaseId;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"Citation"}, enabled = true, retryAnalyzer = Retry.class,
            description = "1721296  - Verify that the cite button available on current content page and  Preview/Export citation pop up will be displayed when clicked on it")
    @Story("EPIC-971")
    public void verifyCitationButonAvailableAndPreviewExportCitationPopUpWillBeDisplayedWhenClickedOnIt() throws Exception {
        try {
            testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
            Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
            String application = BaseTest.properties.getProperty("application");
            url = BaseTest.properties.getProperty(application);
            System.out.println("!url=" + url);
            String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
            navigateToUrlLink(url);
            System.out.println(WebDriverManager.getDriver().getPageSource());
            JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
            masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
            System.out.println("HI");
            //masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
            masterPage.clickOnSearchMagnifyingLense();
            System.out.println("HI2");
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
            articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();

            //Verifying if the citation button is present in the article page

            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyCitationButtonPresentOnArticlePage(), true, "Verifying Citation Button is present");
            articleCitationPage.clickOnCitationButtonOnArticlePage();

            // Verifying if citation pop-up is displayed when clicking on the citation button

            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(),
                    "Verifying the Preview Export Citation popup is displayed");
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());

            //Verifying if user is able to see RIS,BIB,ENW Button is present under Export citation section.

            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the RIS Button is present under Export citation section on Preview Export Citation PopUp");
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the BIB Button is present under Export citation section on Preview Export Citation PopUp");
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the ENW Button is present under Export citation section on Preview Export Citation PopUp");
            articleCitationPage.clickOnRISExportCitationFormat();

            //Verifying if user is able to download RIS,BIB,ENW formats
            
//            String ris= articleCitationPage.getLatestDownloadFileRelatedToCitation(".ris");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), ris, testData.get("risbuttonformat").toString(),
//                    "Verifying the file " + (testData.get("risbuttonformat").toString()) + " is downloaded");
            BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.toVerifyCitationFormatFileIsDownload(".ris"), true,"Verifying the file .ris format file is downloaded");
            deletedownloadedFiles(".ris");
            articleCitationPage.clickOnBIBExportCitationFormat();
            
//            String bib= articleCitationPage.getLatestDownloadFileRelatedToCitation(".bib");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), bib, testData.get("bibbuttonformat").toString(),
//                    "Verifying the file " + (testData.get("bibbuttonformat").toString()) + " is downloaded");
            BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.toVerifyCitationFormatFileIsDownload(".bib"), true,"Verifying the file .ris format file is downloaded");
            
            deletedownloadedFiles(".bib");
            articleCitationPage.clickOnENWExportCitationFormat();
            
//            String enw= articleCitationPage.getLatestDownloadFileRelatedToCitation(".enw");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), enw, testData.get("enwbuttonformat").toString(),
//                    "Verifying the file " + (testData.get("enwbuttonformat").toString()) + " is downloaded");
            BaseTest.assertEquals(WebDriverManager.getDriver(),articleCitationPage.toVerifyCitationFormatFileIsDownload(".enw"), true,"Verifying the file .ris format file is downloaded");
            
            deletedownloadedFiles(".enw");
            List<String> expRISCitationLabels = Arrays.asList(testData.get("risbuttonlabel").toString().split(","));
            List<String> expBIBCitationLabels = Arrays.asList(testData.get("bibbuttonlabel").toString().split(","));
            List<String> expENWCitationLabels = Arrays.asList(testData.get("enwbuttonlabel").toString().split(","));
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(), expRISCitationLabels.toString(),
                    "Verifying RIS button Labels on Export Ciatation Popup");
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(), expBIBCitationLabels.toString(),
                    "Verifying BIB button Labels on Export Ciatation Popup");
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(), expENWCitationLabels.toString(),
                    "Verifying ENW button Labels on Export Ciatation Popup");
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp(), true,
                    "Verifying Preview Export Citation PopUp Close Button is present on Preview Export Citation PopUp");

            //Verifying if user is able to close citation pop-up after use.
            articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
        } finally {
            deletedownloadedFiles(".ris");
            deletedownloadedFiles(".bib");
            deletedownloadedFiles(".enw");
        }
    }
}
