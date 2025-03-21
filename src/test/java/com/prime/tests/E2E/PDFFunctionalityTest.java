/**
 * 
 **Testing the below functionality of the epic PDF-WORK

1.Verifying Pdf download button is present on article page
2.Verifying Button Downloaded
3.Verify Inline PDF tab is diplayed
4.Verifying the Default PDF Zoom value, ZoomIn and Zoom Out button in Inline PDF Tab
5.Verifying the same article is displayed in PDFViewer in Inline PDF tab
6.Verifying the dynamic watermark on pdf preview in Inline pdf tab
7.Verifying PDF contect is not available for restricted content 

 */

package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
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
	@Test(groups = {
			"PDF Work" }, enabled = true, retryAnalyzer = Retry.class, description = "1722758 - Verify that the PDF button available on current content page and  PDF Download will be successful when clicked on it")
	@Story("EPIC-1180")
	public void verifyPDFButonAvailableAndDownloadPDF() throws Exception {
		try {
			testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
			WebDriverManager.setTestcaseIdTestRail(testCaseId);
			// String application = BaseTest.properties.getProperty("application");
			url = BaseTest.properties.getProperty(application);
			System.out.println("!url=" + url);
			String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
			navigateToUrlLink(url);
			JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
			String pdfURL = testData.get("pdfurl").toString();
			driver.get(url + pdfURL);
			masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
			articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
//            masterPage.enterTextInSearchBoxOnHomePage("health");
//            masterPage.clickOnSearchMagnifyingLense();
			browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);

			// Verifying Pdf download button is present on article page
//            browseOrSearchPage.ClickOnArticleFromeRefineByType();
//            browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("open").toString());            
//            WebDriverManager.getDriver().navigate().refresh();
//            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
			pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
			BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyPDFButtonPresentOnArticlePage(), true,
					"Verifying PDF Download button is present on the article page");

			// Verifying Button Downloaded
			String articleHeader = articleCitationPage.getArticleTitleOnArticlePage();
			System.out.println("articleHeader : " + articleHeader);
			pdfPage.clickOnDownloadPDFButtonOnArticlePage();

			BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.toVerifyPDFFIleIsDownload(), true,
					"Pdf File is downloaded");
			deletedownloadedFiles(".pdf");
			deletedownloadedFiles(".crdownload");

			// Verify Inline PDF tab is diplayed
			BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyInlinePDFTabIsPresentOnArticlePage(),
					true, "Verifying Inline PDF tab is present on the article page");
			pdfPage.clickOnInlinePdfTabOnArticlePage();
			pdfPage.switchToFrame(WebDriverManager.getDriver());

			// Verifying the Default PDF Zoom value, ZoomIn and Zoom Out button in Inline
			// PDF Tab
			BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.getDefaultPDFZoomValueInInlinePDFTab(),
					testData.get("defaultzoomvalue").toString(),
					"Verifying Default zoom value in Inline PDF Tab on article page");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					pdfPage.verifyZoomInButtonIsPresentInInlinePDFTabOnArticlePage(), true,
					"Verifying ZoomIn button is present on Inline PDF tab ");
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					pdfPage.verifyZoomOutButtonIsPresentInInlinePDFTabOnArticlePage(), true,
					"Verifying ZoomOut button is present on Inline PDF tab");

			// Verifying the same article is displayed in PDFViewer in Inline PDF tab
			String partialArticleTitle = articleHeader.substring(0, 22).toString();
			String partialArticlePDFHeader = pdfPage.getPartialArticleTitleFromInlinePDFTab(partialArticleTitle);
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					BaseTest.verifyStringContainsSpecificWord(articleHeader, partialArticlePDFHeader), true,
					"Verifying that same article is displayed in PDF preview in inline PDF tab");

			// Verifying the dynamic watermark on pdf preview in Inline pdf tab
			BaseTest.assertEquals(WebDriverManager.getDriver(),
					pdfPage.verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(
							testData.get("watermarkappname").toString()),
					true,
					"Verifying that watermark is present on pdf in Pdf preview in Inline tab on the articla page");
			// Pickup Apps name or COnfig properly apps name

		} finally {

			deletedownloadedFiles(".pdf");
			deletedownloadedFiles(".crdownload");

		}
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(groups = {
			"PDF Work" }, enabled = true, retryAnalyzer = Retry.class, description = "1722759 - Verify PDF features in restricted access content page")
	@Story("EPIC-1180")

	public void VerifyPDFFeaturesInRestrictedAccessContentPage() throws Exception {

		testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
		WebDriverManager.setTestcaseIdTestRail(testCaseId);
		// String application = BaseTest.properties.getProperty("application");
		url = BaseTest.properties.getProperty(application);
		System.out.println("!url=" + url);
		String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
		navigateToUrlLink(url);
		JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
		masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
		browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
		pdfPage = BasePage.initialize(WebDriverManager.getDriver(), PDFPage.class);
		masterPage.clickOnSearchMagnifyingLense();
		browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(
				testData.get("fiftyvalue").toString());
		browseOrSearchPage.clickOnFirstRestrictedContentOnBrowseOrSearchPage();
		BaseTest.assertEquals(WebDriverManager.getDriver(),
				pdfPage.verifyPDFButtonIsNotPresentOnRestrictedArticleOnArticlePage(), true,
				"Verifying PDF Button is not present on restricted article");
		BaseTest.assertEquals(WebDriverManager.getDriver(), pdfPage.verifyInlinePDFTabIsNotPresentOnArticlePage(), true,
				"Verifying Inline PDF tab is not present on restricted article on article page");

	}
}