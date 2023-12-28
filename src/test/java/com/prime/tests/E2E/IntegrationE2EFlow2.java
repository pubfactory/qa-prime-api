package com.prime.tests.E2E;

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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class IntegrationE2EFlow2 extends BaseTest {
    private MasterPage masterPage;
    private SignInPage signInPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private SearchServiceHelper searchServiceHelper;
    private int totalResultsFromAPI;
    private int totalResultsFromWebPage;
    Response response;
    private String url = "";
    private String username;
    private String password;
    private LoginServiceHelper loginServiceHelper;
    private IssuePage issuePage;
    private ArticleCitationPage articleCitationPage;
    private JournalPage journalPage;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"SignIn"}, enabled = true, retryAnalyzer = Retry.class, description = "1721284 - Verify that the user is able to do blank search and compare that the search results API is giving the same results")
    @Story("EPIC-971")
    @Parameters({"testcaseid"})
    public void verifyThatUserAbleToLaunchApplication(@Optional String testCaseId) throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // Identifying the application and its url to test
        String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        // Launch the application and verify if the launch has been successful
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), basePage.getTitleFromWebPage(), testData.get("title").toString(), "Verifying the page title ");
        // Do a blank search and verify if user is taken to Browse Page
        masterPage.clickOnSearchMagnifyingLense();
        System.out.println("CLICK SUCCESSFUL!!");
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBrowsePageLabelText(), testData.get("browsetext").toString(), "Verifying the Browse page title");
        // Verify if number of items in a search results is same as the number returned
        // in search results api
        totalResultsFromWebPage = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        System.out.println("Web COUNT=" + totalResultsFromWebPage);
        searchServiceHelper = new SearchServiceHelper();
        response = searchServiceHelper.fetchSearchResults(platform, application, status);
        BaseTest.assertEquals(WebDriverManager.getDriver(), response.getStatusCode(), 200, "Verifying search results API");
        JsonPath js = new JsonPath(response.asString());
        totalResultsFromAPI = Integer.parseInt(js.get("pagination.totalResults").toString());
        BaseTest.assertEquals(WebDriverManager.getDriver(), totalResultsFromAPI, totalResultsFromWebPage, "Total Search results from api and webpage");

        testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);

        //        // Verify if user is able to login and retrieve the accountID
        //        username = testData.get("username").toString();
        //        password = testData.get("password").toString();
        //        System.out.println(application + platform + status);
        //        loginServiceHelper = new LoginServiceHelper();
        //        response = loginServiceHelper.fetchUserAccessDescriptionToken(platform, application, status, username, password);
        //        LoginUserAccessResponse loginuseraccessresponse = response.as(LoginUserAccessResponse.class);
        //        String token = loginuseraccessresponse.getUserAccessDescriptor().toString();
        //        response = loginServiceHelper.fetchUserInfoUsingToken(platform, application, status, token);
        //        js = new JsonPath(response.asString());
        //        System.out.println("AccountID=" + js.get("accountAccessDescriptors[0].accountId").toString());


        /*Verify that the pagination links displayed are functional and the number of pagination
         *  links displayed changes as per items per page dropdown is selected.*/

        BaseTest.assertEquals(driver, browseOrSearchPage.getStatusPaginationLink().toString(), "true", "Verifying if pagination link is active");
        int numberOfItemsPerPage = browseOrSearchPage.getItemsPerPage();
        double noOfPaginationLinksD = Math.ceil(totalResultsFromWebPage / (double) numberOfItemsPerPage);
        Double dnp = new Double(noOfPaginationLinksD);
        int noOfPaginationLinks = dnp.intValue();
        BaseTest.assertEquals(driver, browseOrSearchPage.getLastItemOfPaginationLinks(), noOfPaginationLinks, "Verifying if the number of pagination is as expected");

        /* Verify if search results page can be sorted in ascending and descending
         */
        browseOrSearchPage.SelectSortDateAscFromSortByDropdownOnSearchOrBrowsePage();
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("sort=date"), "Verifying if search results are sorted in ascending order");
        browseOrSearchPage.SelectSortDateDescFromSortByDropdownOnSearchOrBrowsePage();
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("sort=datedescending"), "Verifying if search results are sorted in descending order");

        // Adding a search text in the search text box

        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getSearchPageLabelText(), testData.get("searchpage").toString(), "Verifying Search Result page Header");

        // Check returned results after applying article type filters

        int totalResultOnsearchPage = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        browseOrSearchPage.clickOnArticleTypeFilterValueOnBrowseOrSearchPage(testData.get("articletypename").toString());

        // Verifying the article type name and verifying number of results after applying filter

        BaseTest.verifyTextInURL(testData.get("articletypename").toString());
        System.out.println("Article type name=" + testData.get("articletypename").toString());
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getNumberOfFilteredResultsFrontOfArticleFilterValueOnBrowseOrSearchPage(testData.get("articletypename").toString()),
                browseOrSearchPage.getTotatResultOnBrowseOrSearchPage(), "Verifying number of filtered results are returned after applying Article type filter");
        System.out.println("total result actual webpage after article filter apply:" + browseOrSearchPage.getTotatResultOnBrowseOrSearchPage());
    }


    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"JournalHomePageIssues"}, enabled = true, retryAnalyzer = Retry.class, description = "1721285 - Verify if Article, Issue, and Journal page renders correctly when user try to access it from homepage.")
    @Story("EPIC-971")
    @Parameters({"testcaseid"})
    public void verifyThatViewPagesDisplayed(@Optional String testCaseId) throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // Identifying the application and its url to test
        String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
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
