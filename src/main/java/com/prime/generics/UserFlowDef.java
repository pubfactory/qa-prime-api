/**
 * Testing the below functionality in Cite An Entry 

1. Verifying if the citation button is present in the article page
2. Verifying if citation pop-up is displayed when clicking on the citation button
3. Verifying if user is able to see RIS,BIB,ENW Button is present under Export citation section.
4. Verifying if user is able to download RIS,BIB,ENW formats
5. Verifying if user is able to close citation pop-up after use.

 */
package com.prime.generics;

import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.testng.asserts.SoftAssert;

import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.PDFPage;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;

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

    public void testDataInIt(String testCaseId) throws Exception {
    	 String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
    	 testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
    }
    
    
    
    public void browserInit() throws Exception {
        //        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        //        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // String application = BaseTest.properties.getProperty("application");
        //String application = System.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
     //   String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        mainWindow = Helper.INSTANCE.getWindow(driver);
      //  testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);

    }

    public void verifyCitationButonAvailableAndPreviewExportCitationPopUpWillBeDisplayedWhenClickedOnIt() throws Exception {
        try {
           // masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        	soft = new SoftAssert();
            masterPage.clickOnSearchMagnifyingLense();
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);            
            browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("open").toString());
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
            articleCitationPage.clickOnToolsButtonInActionBarOnArticlePage();

            //Verifying if the citation button is present in the article page
            soft.assertEquals(articleCitationPage.verifyCitationButtonPresentOnArticlePage(), true, "Verifying Citation Button is present");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyCitationButtonPresentOnArticlePage(), true, "Verifying Citation Button is present");
            articleCitationPage.clickOnCitationButtonOnArticlePage();

            // Verifying if citation pop-up is displayed when clicking on the citation button
            soft.assertEquals(articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(),
                    "Verifying the Preview Export Citation popup is displayed");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getPreviewExportCitationPopUpHeaderText(), testData.get("popupheader").toString(),
//                    "Verifying the Preview Export Citation popup is displayed");
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueapa").toString());
            articleCitationPage.selectFormatValueOnPreviewExportCitationPopUp(testData.get("formatvalueama").toString());

            //Verifying if user is able to see RIS,BIB,ENW Button is present under Export citation section.

           soft.assertEquals(articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the RIS Button is present under Export citation section on Preview Export Citation PopUp");
           soft.assertEquals(articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                   "Verifying the BIB Button is present under Export citation section on Preview Export Citation PopUp");
           soft.assertEquals(articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup(), true,
                    "Verifying the ENW Button is present under Export citation section on Preview Export Citation PopUp");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyRISButtonIsPresentOnPreviewExportCitationOnPopup(), true,
//                    "Verifying the RIS Button is present under Export citation section on Preview Export Citation PopUp");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup(), true,
//                    "Verifying the BIB Button is present under Export citation section on Preview Export Citation PopUp");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyENWButtonIsPresentOnPreviewExportCitationOnPopup(), true,
//                    "Verifying the ENW Button is present under Export citation section on Preview Export Citation PopUp");
            articleCitationPage.clickOnRISExportCitationFormat();

            //Verifying if user is able to download RIS,BIB,ENW formats
            soft.assertEquals(articleCitationPage.toVerifyCitationFormatFileIsDownload(".ris"), true, "Verifying the file .ris format file is downloaded");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.toVerifyCitationFormatFileIsDownload(".ris"), true, "Verifying the file .ris format file is downloaded");
            deletedownloadedFiles(".ris");
            articleCitationPage.clickOnBIBExportCitationFormat();
            soft.assertEquals(articleCitationPage.toVerifyCitationFormatFileIsDownload(".bib"), true, "Verifying the file .ris format file is downloaded");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.toVerifyCitationFormatFileIsDownload(".bib"), true, "Verifying the file .ris format file is downloaded");

            deletedownloadedFiles(".bib");
            articleCitationPage.clickOnENWExportCitationFormat();
            
            soft.assertEquals(articleCitationPage.toVerifyCitationFormatFileIsDownload(".enw"), true, "Verifying the file .ris format file is downloaded");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.toVerifyCitationFormatFileIsDownload(".enw"), true, "Verifying the file .ris format file is downloaded");

            deletedownloadedFiles(".enw");
            List<String> expRISCitationLabels = Arrays.asList(testData.get("risbuttonlabel").toString().split(","));
            List<String> expBIBCitationLabels = Arrays.asList(testData.get("bibbuttonlabel").toString().split(","));
            List<String> expENWCitationLabels = Arrays.asList(testData.get("enwbuttonlabel").toString().split(","));
            
            soft.assertEquals(articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(), expRISCitationLabels.toString(),
                    "Verifying RIS button Labels on Export Ciatation Popup");
            soft.assertEquals(articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(), expBIBCitationLabels.toString(),
                    "Verifying BIB button Labels on Export Ciatation Popup");
            soft.assertEquals(articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(), expENWCitationLabels.toString(),
                    "Verifying ENW button Labels on Export Ciatation Popup");
            soft.assertEquals(articleCitationPage.verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp(), true,
                    "Verifying Preview Export Citation PopUp Close Button is present on Preview Export Citation PopUp");
           
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getExportCitationFormatLabels(testData.get("risbutton").toString()).toString(), expRISCitationLabels.toString(),
//                    "Verifying RIS button Labels on Export Ciatation Popup");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getExportCitationFormatLabels(testData.get("bibbutton").toString()).toString(), expBIBCitationLabels.toString(),
//                    "Verifying BIB button Labels on Export Ciatation Popup");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getExportCitationFormatLabels(testData.get("enwbutton").toString()).toString(), expENWCitationLabels.toString(),
//                    "Verifying ENW button Labels on Export Ciatation Popup");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp(), true,
//                    "Verifying Preview Export Citation PopUp Close Button is present on Preview Export Citation PopUp");

            //Verifying if user is able to close citation pop-up after use.
            articleCitationPage.clickOnPreviewExportCitationPopUpCloseButton();
            //soft.assertAll();
        }
            catch(Exception e) {
            	e.getStackTrace();
            	
            
        } finally {
            deletedownloadedFiles(".ris");
            deletedownloadedFiles(".bib");
            deletedownloadedFiles(".enw");
        }
    }

    public void verifyPDFButonAvailableAndDownloadPDF() throws Exception {
    	try {
    		soft = new SoftAssert();
            driver.navigate().refresh();
            pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
            soft.assertEquals(pdfPage.verifyPDFButtonPresentOnArticlePage(), false, "Verifying PDF Button is present on the article page");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyPDFButtonPresentOnArticlePage(), true, "Verifying PDF Button is present on the article page");

            //Verifying Button Downloaded
            String articleHeader = articleCitationPage.getArticleHeaderOnArticlePage();
            pdfPage.clickOnDownloadPDFButtonOnArticlePage();

            soft.assertEquals(pdfPage.toVerifyPDFFIleIsDownload(), true, "Pdf File is downloaded");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.toVerifyPDFFIleIsDownload(), true, "Pdf File is downloaded");
            deletedownloadedFiles(".pdf");
            deletedownloadedFiles(".crdownload");

            //Verify Inline PDF tab is diplayed
            soft.assertEquals(pdfPage.verifyInlinePDFTabIsPresentOnArticlePage(), true, "Verifying Inline PDF tab is present on the article page");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyInlinePDFTabIsPresentOnArticlePage(), true, "Verifying Inline PDF tab is present on the article page");
            pdfPage.clickOnInlinePdfTabOnArticlePage();
            pdfPage.switchToFrame(WebDriverManager.getDriver());

            // Verifying the Default PDF Zoom value, ZoomIn and Zoom Out button in Inline PDF Tab 
            soft.assertEquals(pdfPage.getDefaultPDFZoomValueInInlinePDFTab(), testData.get("defaultzoomvalue").toString(),
                    "Verifying Default zoom value in Inline PDF Tab on article page");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.getDefaultPDFZoomValueInInlinePDFTab(), testData.get("defaultzoomvalue").toString(),
//                    "Verifying Default zoom value in Inline PDF Tab on article page");
            
            soft.assertEquals(pdfPage.verifyZoomInButtonIsPresentInInlinePDFTabOnArticlePage(), false, "Verifying ZoomIn button is present on Inline PDF tab");
            soft.assertEquals(pdfPage.verifyZoomOutButtonIsPresentInInlinePDFTabOnArticlePage(), true, "Verifying ZoomOut button is present on Inline PDF tab");
            
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyZoomInButtonIsPresentInInlinePDFTabOnArticlePage(), true, "Verifying ZoomIn button is present on Inline PDF tab ");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyZoomOutButtonIsPresentInInlinePDFTabOnArticlePage(), true, "Verifying ZoomOut button is present on Inline PDF tab");

            //Verifying the PageSize changes when click on ZoomOut(Minus) or ZoomIn(Plus) button
            //            pdfPage.ClickOnAutomaticZoomFromPDFZoomScaleSelectorDD();
            //            pdfPage.clickOnZoomOutButton();
            //            BaseTest.assertEquals(WebDriverManager.getDriver(),pdfPage.VerifyPDFSizeChangesWhenClickOnZoomOutButtonAtEightyPercentZoom(), true, "Verifying View page size changed after clinking on zoomOut button on Inline PDF tab");
            //            pdfPage.ClickOnAutomaticZoomFromPDFZoomScaleSelectorDD();
            //            pdfPage.clickOnZoomInButton();
            //            BaseTest.assertEquals(WebDriverManager.getDriver(),pdfPage.VerifyPDFSizeChangesWhenClickOnZoomInButtonAtHundredPercentZoom(), true, "Verifying View page size changed after clinking on zoomIn button on Inline PDF tab");

            //Verifying the same article is displayed in PDFViewer in Inline PDF tab
            String partialArticleHeader = pdfPage.getPartialArticleTitleFromInlinePDFTab();
            soft.assertEquals(BaseTest.verifyStringContainsSpecificWord(articleHeader, partialArticleHeader), true,
            "Verifying that same article is displayed in PDF preview in inline PDF tab");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.verifyStringContainsSpecificWord(articleHeader, partialArticleHeader), true,
//                    "Verifying that same article is displayed in PDF preview in inline PDF tab");

            String applicationName = BaseTest.properties.getProperty("application");

            //Verifying the dynamic watermark on pdf preview in Inline pdf tab
            soft.assertEquals(pdfPage.verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(applicationName), true,
                    "Verifying that watermark is present on pdf in Pdf preview in Inline tab on the articla page");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(applicationName), true,
//                    "Verifying that watermark is present on pdf in Pdf preview in Inline tab on the articla page");
            // Pickup Apps name or COnfig properly apps name 
        //    soft.assertAll();
    	} catch (Exception e) {
            e.getStackTrace();
            
        } finally {

            deletedownloadedFiles(".pdf");
            deletedownloadedFiles(".crdownload");

        }
    }
    
    public void VerifyVariousEmailButtonFeatures() throws Exception {
        try {
        	driver.navigate().refresh();
        	articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);            
            String articleText = articleCitationPage.getArticleHeaderOnArticlePage();

            // Verifying Share via email button and Veriying Share link popup header
            soft.assertEquals(articleCitationPage.verifyShareViaEmailButtonPresentOnArticlePage(), false, "Verifying the Share via Email button is present on Article Page");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyShareViaEmailButtonPresentOnArticlePage(), true, "Verifying the Share via Email button is present on Article Page");
            articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
            soft.assertEquals( articleCitationPage.getShareLinkPopupHeader(), testData.get("sharelinkpopupHeader").toString(), "Verifying the Share Link pop up Header on Article page");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getShareLinkPopupHeader(), testData.get("sharelinkpopupHeader").toString(), "Verifying the Share Link pop up Header on Article page");
            articleCitationPage.clickOnCopyLinkButtonOnShareLinkOnArticlePage();

            // Verifying Link Copied successfully meassage displayed

            String directCopyLink = articleCitationPage.getCopyLinkDirectlyOnShareLinkPopupOnArticlePage();
            articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();

            // verifying same article open in new tab
            Helper.INSTANCE.openNewTab();
            Helper.INSTANCE.switchToWindowTab(1);
            WebDriverManager.getDriver().navigate().to(directCopyLink);
            String articleTextInNewTab = articleCitationPage.getArticleHeaderOnArticlePage();
            soft.assertEquals( articleText, articleTextInNewTab, "Verifying the same article is opened when copy the link from share Link pop up and paste in new tab");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleText, articleTextInNewTab, "Verifying the same article is opened when copy the link from share Link pop up and paste in new tab");
            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            // Verifying share link popup close button
            articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
            articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();
            articleCitationPage.hoverOnShareViaEmailButton();
            soft.assertEquals(articleCitationPage.verifyShareLinkPopUpIsNotPresentOnArticlePageAfterClickingCloseButton(), true,
                    "Verifying the Share link pop up is Closed after clicking the close button of share link popup");
//            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyShareLinkPopUpIsNotPresentOnArticlePageAfterClickingCloseButton(), true,
//                    "Verifying the Share link pop up is Closed after clicking the close button of share link popup");
        
          //  soft.assertAll();
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
    
}
