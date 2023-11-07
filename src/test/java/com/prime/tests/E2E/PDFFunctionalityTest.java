
package com.prime.tests.E2E;
 
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.PDFPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
 
public class PDFFunctionalityTest extends BaseTest {
 
    private MasterPage masterPage;
    private BasePage basePage;
    private ArticleCitationPage articleCitationPage;
    private PDFPage pdfPage;
    private BrowseOrSearchPage browseOrSearchPage;
    private String url = "";
    private String testCaseId;
 
    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"Citation"}, enabled = true, retryAnalyzer = Retry.class, description = " 75 - Verify that the PDF button available on current content page and  PDF Download will be successful when clicked on it")
    @Story("EPIC-1180")
 
    public void verifyPDFButonAvailableAndDownloadPDF() throws Exception {
        try {
            testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
            Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
            String application = BaseTest.properties.getProperty("application");
            url = BaseTest.properties.getProperty(application);
            System.out.println("!url=" + url);
            String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
            navigateToUrlLink(url);
            JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
            masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
            masterPage.clickOnSearchMagnifyingLense();
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
             
            // Click on the PDF tab
            browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("open").toString());
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyPDFButtonPresentOnArticlePage(), true, "Verifying PDF Button is present on article page .");
         
            //Verifying Button Downloaded
            pdfPage.clickOnDownloadPDFButtonOnArticlePage();
            String donwloladFileName=pdfPage.getLatestDownloadFileRelatedToPDF();
            BaseTest.assertTrue(WebDriverManager.getDriver(),BaseTest.verifyTextInURL(donwloladFileName),"Verifying the downloaded file name");
           
            //Verify Inline PDF tab is diplayed
            BaseTest.assertEquals(WebDriverManager.getDriver(),pdfPage.verifyInlinePDFTabIsPresentOnArticlePage(),true,"Verifying Inline PDF tab is present on article page");
            pdfPage.clickOnInlinePdfTabOnArticlePage();
            System.out.println("zoom : "+pdfPage.getDefaultPDFZoomValueInInlinePDFTab());
//            BaseTest.assertEquals(WebDriverManager.getDriver(),pdfPage.getDefaultPDFZoomValueInInlinePDFTab(),testData.get("defaultzoomvalue").toString(),"Verifying IDefault zoom value in Inline PDF Tab on article page");

            
           
        } finally {
            BaseTest.deleteDonwloadedFile();
        }
    }
}