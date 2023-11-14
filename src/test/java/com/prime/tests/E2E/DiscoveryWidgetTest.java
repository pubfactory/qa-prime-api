package com.prime.tests.E2E;

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

public class DiscoveryWidgetTest extends BaseTest {

    private MasterPage masterPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private ArticleCitationPage articleCitationPage;
    private String testCaseId;
    private String url = "";
    private String mainWindow;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"Discovery Widget"}, enabled = true, retryAnalyzer = Retry.class, description = "1722741 - Verify various Email button features")
    @Story("EPIC-973")

    public void VerifyVariousEmailButtonFeatures() throws Exception {
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
            mainWindow = Helper.INSTANCE.getWindow(driver);
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            masterPage.clickOnSearchMagnifyingLense();
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            String articleText = articleCitationPage.getArticleHeaderOnArticlePage();
            // Verifying Share via email button and Veriying Share link popup header
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyShareViaEmailButtonPresentOnArticlePage(), true, "Verifying the Share via Email button is present on Article Page");
            articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getShareLinkPopupHeader(), testData.get("sharelinkpopupHeader").toString(), "Verifying the Share Link pop up Header on Article page");
            articleCitationPage.clickOnCopyLinkButtonOnShareLinkOnArticlePage();

            // Verifying Link Copied successfully meassage displayed
            //			BaseTest.assertEquals(WebDriverManager.getDriver(),
            //					articleCitationPage.verifyLinkCopiedSuccessfullyMessageDisplayedOnPopupOnArticlePage(), true,
            //					"Verifying the Link copied message dispayed after clicking on copy link button on share link pop up on Article page");
            String directCopyLink = articleCitationPage.getCopyLinkDirectlyOnShareLinkPopupOnArticlePage();
            articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();

            // verifying same article open in new tab
            Helper.INSTANCE.openNewTab();
            Helper.INSTANCE.switchToWindowTab(1);
            WebDriverManager.getDriver().navigate().to(directCopyLink);
            String articleTextInNewTab = articleCitationPage.getArticleHeaderOnArticlePage();
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleText, articleTextInNewTab, "Verifying the same article is opened when copy the link from share Link pop up and paste in new tab");
            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            // Verifying share link popup close button
            articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
            articleCitationPage.clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage();
            articleCitationPage.hoverOnShareViaEmailButton();
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyShareLinkPopUpIsNotPresentOnArticlePageAfterClickingCloseButton(), true,
                    "Verifying the Share link pop up is Closed after clicking the close button of share link popup");

            //	        articleCitationPage.clickOnshareViaEmailButtonOnArticlePage();
            //	        articleCitationPage.clickOnEmailThisContentButtonOnShareLinkOnArticlePage();
            //	        Helper.INSTANCE.switchToWindowTab(0);

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"Discovery Widget"}, enabled = true, retryAnalyzer = Retry.class, description = "1722742 - Verify Google Scholar Button features")
    @Story("EPIC-973")
    public void VerifyGoogleScholarButtonFeatures() throws Exception {
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
            mainWindow = Helper.INSTANCE.getWindow(driver);
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            masterPage.clickOnSearchMagnifyingLense();
            // Verifying Google Scholar Button
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyGoogleScholarButtonPresentOnArticlePage(), true,
                    "Verifying the Google Scholar button is present at right hand side on Artical page");


            // Verifying Same author affiliation block authors present in Google scholar section
            articleCitationPage.clickOnGoogleScholarkButtonOnArticlePage();
            List<String> affiliationBlockAuthor = articleCitationPage.getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage();
            List<String> googleScholarAuthor = articleCitationPage.getAllAuthorNamesFromAuthorGoogleScholarSectionOnArticlePage();
            BaseTest.assertEquals(WebDriverManager.getDriver(), affiliationBlockAuthor.toString(), googleScholarAuthor.toString(),
                    "Verifying the similar author affiliation block authors is present in the Google Scholar section on Artical page");

            //Verifying Similar article hyper link present in Google scholar section and similar article open when click on it
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifySimilarArtcileHyperLinkPresentOnArticlePage(), true,
                    "Verifying the Similar Artcile Hyper Link is present in the Google Scholar section on Artical page");
            articleCitationPage.clickOnSimilarArticleInGoogleScholarHyperLink();
            Helper.INSTANCE.switchToWindowTab(1);
            BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("scholar"), "Verifying if google scholar tab is opened");
            WebDriverManager.getDriver().close();
            Helper.INSTANCE.switchToWindowTab(0);

            //Verifying similar author article open in new tab when clicked on author in Google scholar section
            String[] author = articleCitationPage.getFirstAuthorNamesFromGoogleScholarSectionOnArticlePage().split(" ");
            List<String> authorText = Helper.INSTANCE.convertArrayToList(author);
            String authorFirstName = authorText.get(0);
            articleCitationPage.clickOnFirstAuthorInGoogleScholarSectionOnArticlePage();
            Helper.INSTANCE.switchToWindowTab(1);

            BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL(authorFirstName), "Verifying Author name in google scholar tab when clicked on author in google schole section on article page");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"Discovery Widget"}, enabled = true, retryAnalyzer = Retry.class, description = "1722743 - Verify PubMed button features and Almetric page features")
    @Story("EPIC-973")
    public void VerifyPubMedButtonFeaturesAndAlmetricPageFeatures() throws Exception {

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
            mainWindow = Helper.INSTANCE.getWindow(driver);
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            masterPage.clickOnSearchMagnifyingLense();

            // Verifying PubMed Button
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyPubmedButtonPresentOnArticlePage(), true, "Verifying the PubMed button is present at right hand side on Artical page");

            // Verifying Same author affiliation block authors present in Pubmed section
            articleCitationPage.clickOnPubMedButtonOnArticlePage();
            List<String> affiliationBlockAuthor = articleCitationPage.getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage();
            List<String> pubMedAuthor = articleCitationPage.getAllAuthorNamesFromPubMedSectionOnArticlePage();
            BaseTest.assertEquals(WebDriverManager.getDriver(), affiliationBlockAuthor.toString(), pubMedAuthor.toString(),
                    "Verifying the similar author affiliation block authors is present in the PubMed section on Artical page");

            //Verifying similar author article open in new tab when clicked on author in PubMed section
            String[] author = articleCitationPage.getFirstAuthorNamesFromPubMedSectionOnArticlePage().split(" ");
            List<String> authorText = Helper.INSTANCE.convertArrayToList(author);
            String authorFirstName = authorText.get(0);
            articleCitationPage.clickOnFirstAuthorInPubMedSectionOnArticlePage();

            Helper.INSTANCE.switchToWindowTab(1);
            BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL(authorFirstName), "Verifying Same article open in new PubMed site when clicked on author in PubMed section on article page");

            //Verifying altmetric badge present on article page
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyAltmetricBadgePresentOnArticlePage(), true, "Verifying the altmetric badge is present on the artical page");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }
    }
}
