package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.Helper;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class HitHighlightingTest  extends BaseTest{
	private MasterPage masterPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private ArticleCitationPage articleCitationPage;
    private IssuePage issuePage;
    private String testCaseId;
    private String url = "";
    private String mainWindow;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"Hit highlighting for fulltext and abstract"}, enabled = true, retryAnalyzer = Retry.class, description = "103 - Verify various Email button features")
    @Story("EPIC-2720")


    public void VerifyThattheHitHighlightingFuctionality() throws Exception {
            testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
            WebDriverManager.setTestcaseIdTestRail(testCaseId);
            String application = BaseTest.properties.getProperty("application");
            url = BaseTest.properties.getProperty(application);
            String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
            navigateToUrlLink(url);
            JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
            masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
            browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
            articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
            issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
            masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());
            masterPage.clickOnSearchMagnifyingLense();            
            browseOrSearchPage.clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage();
            
            //Verifying the search keyword is displayed as hit highlighted   
            BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(testData.get("searchkeyword").toString()),testData.get("backgroundcolor").toString(), "Verifying the hit highlighting the search keyword in abstract tab on search result page");
            String secodSearchKeyword=browseOrSearchPage.getAbstract().get(0).toString();
            String thirdSearchKeyword = browseOrSearchPage.getAbstract().get(1).toString();
            browseOrSearchPage.clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage();
            
            //Verifying the search keyword is hit highlighted in fulltext or abstract tab on article page
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            articleCitationPage.clickOnFullTextOrAbstractTabOnArticlePage();
            BaseTest.assertEquals(WebDriverManager.getDriver(), articleCitationPage.getBackgroundColor(testData.get("searchkeyword").toString()),testData.get("backgroundcolor").toString(), "Verifying the hit highlighting the search keyword in abstract tab on article page");
            String articleTitle=articleCitationPage.getArticleHeaderOnArticlePage();
            System.out.println("First combination title : "+articleTitle);
            masterPage.enterTextInSearchBoxOnHomePage(articleTitle);
            masterPage.clickOnSearchMagnifyingLense();    
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            BaseTest.assertEquals(driver, articleCitationPage.verifySearchKeywordIsNotHitHighlighted(testData.get("searchkeyword").toString()), true, "Verifying the search keyword is not  hit highlighting in abstract tab on article page after navigate the same content without search opearation");
            
            //Verifying the user can navigate to the same article repeatedly with a combination of searching 
            masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());
            masterPage.clickOnSearchMagnifyingLense(); 
            browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
            browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueentertwo").toString(), secodSearchKeyword);
            browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
            browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenterthree").toString(), thirdSearchKeyword);
            browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
            browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenterfour").toString(), articleTitle);
            browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
            
            browseOrSearchPage.clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage();
            BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(testData.get("searchkeyword").toString()),testData.get("backgroundcolor").toString(), "Verifying the hit highlighting the search keyword in abstract tab on search result page");
            BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(secodSearchKeyword),testData.get("backgroundcolor").toString(), "Verifying the hit highlighting the combination search keyword "+secodSearchKeyword+" in abstract tab on search result page");
            BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBackgroundColor(thirdSearchKeyword),testData.get("backgroundcolor").toString(), "Verifying the hit highlighting the combination search keyword "+thirdSearchKeyword+"in abstract tab on search result page");
            
            browseOrSearchPage.clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage();
            browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
            String articleTitleAfterCombination=articleCitationPage.getArticleHeaderOnArticlePage();     
            BaseTest.assertEquals(driver, articleTitle, articleTitleAfterCombination, "Verifying the user can navigate to the same article repeatedly with a combination of searching");
            
            //Verify that when the user navigates to an issue or journal page, the hit highlights are not displayed
            articleCitationPage.clickOnJournalCoverOnArtcilePage();
            issuePage.clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage();
            issuePage.verifySearchKeywordIsNotHitHighlighted(thirdSearchKeyword);
            BaseTest.assertEquals(driver, articleCitationPage.verifySearchKeywordIsNotHitHighlighted(thirdSearchKeyword), true, "Verifying the search keyword is not hit highlighting in abstract tab on issue page while comes through search page");
          
            
    }
}
