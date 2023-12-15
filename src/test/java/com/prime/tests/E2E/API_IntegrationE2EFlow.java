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
import com.prime.pojo.searchResults.SearchResultsResponse;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;

public class API_IntegrationE2EFlow extends BaseTest {
    private MasterPage masterPage;
    private SignInPage signInPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private SearchServiceHelper searchServiceHelper;
    private LoginServiceHelper loginServiceHelper;
    private int totalResultsFromAPI;
    private Response response;
    private String username;
    private String password;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"SignIn"}, enabled = true, retryAnalyzer = Retry.class, description = "7 - Verify that Login(SIGN IN) link should available in header")
    @Story("EPIC-971")
    @Parameters({"testcaseid"})
    public void verifyThatUserAbleToLaunchApplication(@Optional String testCaseId) throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // ---------- to go in before each test case -----------------------------------
        //	String application=BaseTest.properties.getProperty("application");
        //   String baseURI=BaseTest.properties.getProperty("baseURI_Search");
        //   String platform=BaseTest.properties.getProperty("platform");
        String status = BaseTest.properties.getProperty("status");

        // APIUtils apiutils = new APIUtils();
        // apiutils.getTestDataForApplication(platform, application, status);

        // -------------------------------------------------------
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        // System.out.println("Filename=" + testDataFileName);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        // System.out.println("TestData=" + testData);
        //		username = testData.get("username").toString();
        //		password = testData.get("password").toString();
        //		System.out.println(application + platform + status);
        //		loginServiceHelper = new LoginServiceHelper();
        //		response = loginServiceHelper.fetchUserAccessDescriptionToken(platform, application, status, username,
        //				password);
        //		JsonPath js = new JsonPath(response.asString());
        //		String token = js.get("userAccessDescriptor").toString();
        //		response = loginServiceHelper.fetchUserInfoUsingToken(platform, application, status, token);
        //		js = new JsonPath(response.asString());
        //		System.out.println("AccountID=" + js.get("accountAccessDescriptors[0].accountId").toString());
        //   APIUtils apiutils=new APIUtils();
        //   RequestSpecification request=apiutils.requestSpecification(baseURI,endpoint, platform, application, status);
        //   RequestSpecification req=given().spec(request).log().all();
        //   ResponseSpecification res=apiutils.responseSpecification(); 
        //   Response response=req.when().get(endpoint).then().log().all().spec(res).extract().response();

        searchServiceHelper = new SearchServiceHelper();
        response = searchServiceHelper.fetchSearchResults(platform, application, status);
        // System.out.println("BLOOP 1");
        //		JsonPath js = new JsonPath(response.asString());
        //		totalResultsFromAPI = Integer.parseInt(js.get("pagination.totalResults").toString());
        // ResponseBody responsebody = response.getBody();
        SearchResultsResponse searchResultsResponse = response.as(SearchResultsResponse.class);
        //		Assert.assertEquals(searchResultsResponse.getPagination().getTotalResults(), 830);
        //   //Create request spec
        //   
        //   RestAssured.baseURI="http://prime-search.dev.prime.pubfactory.net";
        ////   RequestSpecification request=new RequestSpecBuilder().setBaseUri("http://prime-search.dev.prime.pubfactory.net").addPathParam("platform", "meridian").build();
        ////   RequestSpecification res=given().spec(request);
        ////   Response responses=res.when().get();
        ////   
        //   String response=given().log().all().pathParam("platform", "meridian").pathParam("site", "fpj").pathParam("status", "draft").header("accept","application/json")
        //		.when().get("/{platform}/{site}/{status}/rest/search/v1/search")
        //			.then().log().all().assertThat().statusCode(200).extract().response().asString();
        //			System.out.println("response : "+ response);
        //			JsonPath js = new JsonPath(response);

        // Request body :

        /*
         * { "username": "veena.mathew@kwglobal.com", "password": "Default@123" }'
         */

        // Login Authentication API

        // base url
        // request headers
        // request body
        // get/post/
        // condition

        //		masterPage.verifySignInLinkpresentOnHeader();
        //		masterPage.clickOnsignInlinkOnHeader();
        //		signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
        //		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getSignInPersonalProfilePageHeaderText(),testData.get("signinpageheader").toString(), "Comparing the actual header and expected header of signIn page");
        //		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("invalidemailaddress").toString());
        //		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("invalidpassword").toString());
        //		signInPage.clickOnSUBMITButtonOnSIgnInPage();
        //		BaseTest.assertEquals(driver, signInPage.getHeaderErrorMessageOnSignInPage(), testData.get("headererrormsg").toString(), "Checking the header error message on signIn page");
        //		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("validemailaddress").toString());
        //		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("emptypassword").toString());
        //		signInPage.clickOnSUBMITButtonOnSIgnInPage();
        //		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessage(testData.get("passworderrmsg").toString()),testData.get("passworderrormessage").toString(), "Checking the password error message");
        //		signInPage.enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(testData.get("emptyemailaddress").toString());
        //		signInPage.enterPasswordInPasswordTextBoxOnSignINPage(testData.get("emptypassword").toString());
        //		signInPage.clickOnSUBMITButtonOnSIgnInPage();
        //		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessage(testData.get("passworderrmsg").toString()),testData.get("passworderrormessage").toString(), "Checking the password error message");
        //		BaseTest.assertEquals(WebDriverManager.getDriver(),signInPage.getErrorMessage(testData.get("emailiderrmsg").toString()),testData.get("usernameerrormessage").toString(), "Checking the username or email Address error message");
    }

}
