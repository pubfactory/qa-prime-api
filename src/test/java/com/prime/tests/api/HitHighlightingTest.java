//package com.prime.tests.api;
//
//import org.json.simple.JSONObject;
//import org.testng.annotations.Test;
//import com.prime.generics.BasePage;
//import com.prime.generics.BaseTest;
//import com.prime.generics.WebDriverManager;
//import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
//import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
//import com.prime.pageFactory.pages.fpj.IssuePage;
//import com.prime.pageFactory.pages.fpj.MasterPage;
//import com.prime.retryAnalyzers.Retry;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import io.qameta.allure.Story;
//
//public class HitHighlightingTest extends BaseTest {
//    private MasterPage masterPage;
//    private BasePage basePage;
//    private BrowseOrSearchPage browseOrSearchPage;
//    private ArticleCitationPage articleCitationPage;
//    private IssuePage issuePage;
//    private String testCaseId;
//    private String url = "";
//    private String mainWindow;
//
//    @Severity(SeverityLevel.BLOCKER)
//    @Test(groups = {"anesthesiaprogress","proxy"}, enabled = true, retryAnalyzer = Retry.class, description = "1729632 - Verify that the hit highlighting fuctionality")
//    @Story("EPIC-2720")
//
//
//    public void VerifyThattheHitHighlightingFuctionality() throws Exception {
//        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
//        WebDriverManager.setTestcaseIdTestRail(testCaseId);
//        // String application = BaseTest.properties.getProperty("application");
//        url = BaseTest.properties.getProperty(application);
//        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
//        navigateToUrlLink(url);
//        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
//        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
//        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
//        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
//        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
//        masterPage.clickOnSearchMagnifyingLense();
//        browseOrSearchPage.ClickOnArticleFromeRefineByType();
//        browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("openaccess").toString());
//        browseOrSearchPage.mouseHoverOnFirstcontentonSearchPage();
//        browseOrSearchPage.ClickOnShowMoreLinkIfAvailableBelowTheContent();
//        Thread.sleep(1000);
//        String firstSearchKeyword = browseOrSearchPage.getAbstract().get(0).toString().trim();
//        System.out.println("firstSearchKeyword : " + firstSearchKeyword);
//        String secondSearchKeyword = browseOrSearchPage.getAbstract().get(1).toString().trim();
//        System.out.println("secodSearchKeyword : " + secondSearchKeyword);
//        masterPage.enterTextInSearchBoxOnHomePage(firstSearchKeyword);
//        masterPage.clickOnSearchMagnifyingLense();
////        browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("openaccess").toString());
////        browseOrSearchPage.mouseHoverOnFirstcontentonSearchPage();
//        //Verifying the search keyword is displayed as hit highlighted  on Search or browse page
//        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(firstSearchKeyword), testData.get("backgroundcolor").toString(),
//                "Verifying the hit highlighting the search keyword in abstract tab on search result page");
//
//        //Verifying the search keyword is hit highlighted in fulltext or abstract tab on article page
////        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
////        articleCitationPage.clickOnFullTextOrAbstractTabOnArticlePage();
////        BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getBackgroundColor(firstSearchKeyword), testData.get("backgroundcolor").toString(),
////                "Verifying the hit highlighting the search keyword in abstract tab on article page");
////        String articleTitle = articleCitationPage.getArticleHeaderOnArticlePage();
////        System.out.println("First combination title : " + articleTitle);
////        masterPage.enterTextInSearchBoxOnHomePage(articleTitle);
////        masterPage.clickOnSearchMagnifyingLense();
////        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
////
////        //Verifying the Hit highlighted is not shows if the user direct navigated to same contecnt without using search keyword
////        //BaseTest.assertEquals(driver, articleCitationPage.verifySearchKeywordIsNotHitHighlighted(firstSearchKeyword), true, "Verifying the search keyword is not hit highlighting in abstract tab on article page after navigate the same content without search opearation");
////
////        //Verifying the Combination of hit highlighted is displayed and user can navigate to the same article repeatedly with a combination of searching 
////        masterPage.enterTextInSearchBoxOnHomePage(firstSearchKeyword);
////        masterPage.clickOnSearchMagnifyingLense();
////        browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
////        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueentertwo").toString(), secondSearchKeyword);
////        browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
////        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenterthree").toString(), articleTitle);
////        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
////        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
////        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(firstSearchKeyword), testData.get("backgroundcolor").toString(),
////                "Verifying the hit highlighting the search keyword in abstract tab on search result page");
////        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(secondSearchKeyword), testData.get("backgroundcolor").toString(),
////                "Verifying the hit highlighting the combination search keyword " + secondSearchKeyword + " in abstract tab on search result page");
////        String articleTitleAfterCombination = articleCitationPage.getArticleHeaderOnArticlePage();
////        BaseTest.assertEquals(driver, articleTitle, articleTitleAfterCombination, "Verifying the user can navigate to the same article repeatedly with a combination of searching");
////
////        //Verify that when the user navigates to an issue or journal page, the hit highlights are not displayed
////        articleCitationPage.clickOnJournalCoverOnArtcilePage();
////        issuePage.clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage();
////        issuePage.verifySearchKeywordIsNotHitHighlighted(secondSearchKeyword);
////        BaseTest.assertEquals(driver, articleCitationPage.verifySearchKeywordIsNotHitHighlighted(firstSearchKeyword), true,
////                "Verifying the search keyword is not hit highlighting in abstract tab on issue page while comes through search page");
//
//    }
//}
