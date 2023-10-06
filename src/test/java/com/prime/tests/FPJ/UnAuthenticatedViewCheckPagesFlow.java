package com.prime.tests.FPJ;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.prime.api.helpers.LoginServiceHelper;
import com.prime.api.helpers.SearchServiceHelper;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.JournalPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;

public class UnAuthenticatedViewCheckPagesFlow extends BaseTest {
    private MasterPage masterPage;
    private SignInPage signInPage;
    private BasePage basePage;
    private IssuePage issuePage;
    private ArticleCitationPage articleCitationPage;
    private BrowseOrSearchPage browseOrSearchPage;
    private JournalPage journalPage;
    private SearchServiceHelper searchServiceHelper;
    private int totalResultsFromAPI;
    private int totalResultsFromWebPage;
    Response response;
    private String url = "";
    private String username;
    private String password;
    private LoginServiceHelper loginServiceHelper;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"SignIn"}, enabled = true, retryAnalyzer = Retry.class, description = "1721285 - Verify that view pages is displayed")
    @Story("EPIC-971")
    @Parameters({"testcaseid"})
    public void verifyThatViewPagesDisplayed(@Optional String testCaseId) throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
        // Identifying the application and its url to test
        String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);

        // Click on first article on the issue page

        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), basePage.getTitleFromWebPage(), testData.get("title").toString(), "Verifying the page title ");
        masterPage.clickOnViewThisIssueOnHomePage();
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), issuePage.getIssuePageHeaderText(), testData.get("issuespageheader").toString(), "Verifying Issue page Header");
        String articleHeader = issuePage.getFirstArticleTextOnIssuePageHeaderText();
        issuePage.clickOnFirstArticleOnIssuePage();

        // Verifying if article page is rendered 

        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), articleHeader, articleCitationPage.getArticleHeaderOnArticlePage(), "Verifying the Header Of Article page");
        WebDriverManager.getDriver().navigate().back();
        issuePage.clickOnAllIssuesOnIssuePage();
        BaseTest.verifyTextInURL(testData.get("journalpageurl").toString());
        journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.getJournalPageHeaderText(), testData.get("journalpageheader").toString(), "Verifying the Journal Page header");


    }

}
