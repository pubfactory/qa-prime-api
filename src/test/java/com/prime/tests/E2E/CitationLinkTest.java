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
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class CitationLinkTest extends BaseTest {

    private MasterPage masterPage;
    private BasePage basePage;
    private ArticleCitationPage articleCitationPage;
    private BrowseOrSearchPage browseOrSearchPage;
    private String url = "";
    private String testCaseId;
    private String mainWindow;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"CitationLink"}, enabled = true, retryAnalyzer = Retry.class, description = "102  - Verify that the Add Cited By functionality")
    @Story("EPIC-1940")
    public void VerifyThatTheCitationLinkFunctionalityIsWorkingFine() throws Exception {
        try {
            testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
            WebDriverManager.setTestcaseIdTestRail(testCaseId);
            //String application = BaseTest.properties.getProperty("application");
            url = BaseTest.properties.getProperty(application);
            String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
            navigateToUrlLink(url);
            JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
            masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
            mainWindow = Helper.INSTANCE.getWindow(driver);
            masterPage.clickOnSearchMagnifyingLense();
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            String articlePageURL = basePage.removeBasicAuthFromURLHomePage(basePage.getURLFromWebPage());

            //Verifying the Citations link is present
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.verifyCitationsLinkIsPresentInGoofglrScholarSectionOnArticlePage(), true,
                    "Verifying the Citations link is present in google scholar section on Article page");
            articleCitationPage.clickOnCitationsLinkInGoogleScholarSectionOnArticlePage();
            Helper.INSTANCE.switchToWindowTab(1);

            //Verifying the new tab opened after clicking on Citations link.
            BaseTest.assertEquals(WebDriverManager.getDriver(), Helper.INSTANCE.verifyingNewTabIsOpen(), true, "Verifying the new tab opened after clicking on Citation link");

            //verifying the google scholar search result is displayed with following search query "( https://scholar.google.com/scholar/?=link)"
            BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("https://scholar.google.com/scholar?q=link"),
                    "verifying the google scholar search result is displayed with following search query: https://scholar.google.com/scholar/?=link");
            String googleScholarPageURL = articleCitationPage.removeContainFromURL(basePage.getURLFromWebPage());

            //Verifying that the new tab is opened and it contains the URL of the article page.
            BaseTest.assertEquals(WebDriverManager.getDriver(), articlePageURL, googleScholarPageURL, "Verifying that the new tab is opened and it contains the URL of the article page.");


        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            Helper.INSTANCE.closeNewTab(mainWindow, WebDriverManager.getDriver());
            Helper.INSTANCE.switchToWindowTab(0);
        }

    }
}
