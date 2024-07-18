package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.prime.api.helpers.LoginServiceHelper;
import com.prime.api.helpers.SearchServiceHelper;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.pojo.login.LoginUserAccessResponse;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AuthenticateTestCase extends BaseTest {
    private MasterPage masterPage;
    private SignInPage signInPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private SearchServiceHelper searchServiceHelper;
    private int totalResultsFromAPI;
    private int totalResultsFromWebPage;
    Response response;
    private String username;
    private String password;
    private LoginServiceHelper loginServiceHelper;
    JSONObject testData;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"SignIn"}, enabled = true, retryAnalyzer = Retry.class, description = "1721043 - Verify that the user is able to do blank search and compare that the search results API is giving the same results")
    @Story("EPIC-971")
    @Parameters({"testcaseid"})
    public void verifyThatUserAbleToLaunchApplication(@Optional String testCaseId) throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // Identifying the application and its url to test
        //String application = BaseTest.properties.getProperty("application");
        System.out.println("url=" + BaseTest.properties.getProperty(application));
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrl(BaseTest.properties.getProperty("application"));
        // Launch the application and verify if the launch has been successful
        testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), basePage.getTitleFromWebPage(), testData.get("title").toString(), "Verifying the page title ");

        //Add UI user login method calls


        // Verify if user is able to login and retrieve the accountID
        username = testData.get("username").toString();
        password = testData.get("password").toString();
        System.out.println(application + platform + status);
        loginServiceHelper = new LoginServiceHelper();
        response = loginServiceHelper.fetchUserAccessDescriptionToken(platform, application, status, username, password);
        LoginUserAccessResponse loginuseraccessresponse = response.as(LoginUserAccessResponse.class);
        String token = loginuseraccessresponse.getUserAccessDescriptor().toString();
        response = loginServiceHelper.fetchUserInfoUsingToken(platform, application, status, token);
        JsonPath js = new JsonPath(response.asString());
        System.out.println("AccountID=" + js.get("accountAccessDescriptors[0].accountId").toString());
        String accountID = js.get("accountAccessDescriptors[0].accountId").toString();

        // Click on Magnifying glass and click on RefineByUser Access

        BaseTest.assertEquals(WebDriverManager.getDriver(), basePage.getTitleFromWebPage(), testData.get("title").toString(), "Verifying the page title ");
        // Do a blank search and verify if user is taken to Browse Page
        masterPage.clickOnSearchMagnifyingLense();
        System.out.println("CLICK SUCCESSFUL!!");
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBrowsePageLabelText(), testData.get("browsetext").toString(), "Verifying the Browse page title");

        // Get all content accessible to the user 

        response = loginServiceHelper.fetchUserAccessibleContent(platform, application, status, accountID);
        js = new JsonPath(response.asString());
        String noOfUserContentRecords = js.get("accountAccessDescriptors[0].accountId").toString();//This will depend on how the API response looks like

        //Get number of records displayed in UI

        int totalResultsFromWebPage = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        //Assert noOfUserContentRecords and totalResultsFromWebPage

        //Access an article from the list



    }



}
