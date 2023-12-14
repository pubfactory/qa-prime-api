/**
 * Journal TOC functionality. Below is the flow being tested 
 *
Clicking on View This Issue On HomePage
Once in Issue Page , clicking on All Issues on Issue Page
Verifying if the journal page header is present
Verifying that issue TOC is present on journal page
Verifying volume section is displayed in the Journal Page header
Click on Journal Section on Journal Page
Verifying volume links are expanded and collapsed on clicking
Verifying Issue is displayed under Volume section on Journal page
Verifying the content title name is present on browse or search page
Verifying the content title is as hyperlink available on issue page
Verifying the content Author is present on Content
 */
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
import com.prime.pageFactory.pages.fpj.JournalPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ToCFunctionalityTest extends BaseTest {
    private MasterPage masterPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private ArticleCitationPage articleCitationPage;
    private SignInPage signInPage;
    private IssuePage issuePage;
    private JournalPage journalPage;
    private String url = "";
    private String testCaseId;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"TOC Functionality"}, enabled = true, retryAnalyzer = Retry.class, description = "1722740 - Verify Journal ToC features")
    @Story("EPIC-427")
    public void VerifyJournalToCFeatures() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        Helper.INSTANCE.setCurrentTestCaseId(testCaseId);
        String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);
        //Clicking on View This Issue On HomePage
        masterPage.clickOnViewThisIssueOnHomePage();
        //Once in Issue Page , clicking on All Issues on Issue Page
        issuePage.clickOnAllIssuesOnIssuePage();
        //Verifying if the journal page header is present
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.getJournalPageHeaderText(), testData.get("journalpageheader").toString(), "Verifying the Journal Page header");
        //Verifying that issue TOC is present on journal page
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyIssueToCIsPresentOnJournalPage(), true, "Verifying the Issue ToC is displayed on Journal Page header");
        //Verifying volume section is displayed in the Journal Page header
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyVolumeSectionIsDisplayed(testData.get("volumevalue").toString()), true, "Verifying the volume section is dipslayed on Journal Page header");
        //Click on Journal Section on Journal Page
        journalPage.clickOnVolumeSectionOnJournalPage(testData.get("volumevalue").toString());
        //Verifying volume links are expanded and collapsed on clicking 
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyVolumeLinkExpandOnJournalPage(testData.get("volumevalue").toString()), true, "Verifying Volume link are Expanded on Journal page");
        journalPage.clickOnVolumeSectionOnJournalPage(testData.get("volumevalue").toString());
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyVolumeLinkCollapseOnJournalPage(testData.get("volumevalue").toString()), true, "Verifying Volume link are collapse on Journal page");
        journalPage.clickOnVolumeSectionOnJournalPage(testData.get("volumevalue").toString());
        //Verifying Issue is displayed under Volume section on Journal page
        BaseTest.assertEquals(WebDriverManager.getDriver(), journalPage.verifyIssueIsDisplayedUnderVolumeSectionOnJornalPage(testData.get("volumevalue").toString()), true,
                "Verifying Issue is displayed under Volume section on Journal page");
        journalPage.clickOnIssueUnderVolumeSectionOnJornalPage(testData.get("volumevalue").toString(), testData.get("issuevalue").toString());
        //		BaseTest.assertEquals(WebDriverManager.getDriver(),issuePage.getIssuePageHeaderText(),testData.get("issuepageheader").toString(),"Verifying the Issue page is displayed");
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("issue"), "Verifying the Issue page is displayed");
        //Verifying the content title name is present on browse or search page
        BaseTest.assertEquals(WebDriverManager.getDriver(), issuePage.verifyContentTitleNameIsPresentOnIssuePage(), true, "Verifying the content title name is present on browse or search page");
        //Verifying the content title is as hyperlink available on issue page
        BaseTest.assertEquals(WebDriverManager.getDriver(), issuePage.ClickingOnContentToCheckHyperLinkOrNot(), true, "Verifying the content title is as hyperlink available on issue page");
        WebDriverManager.getDriver().navigate().back();
        //Verifying the content Author is present on Content
        BaseTest.assertEquals(WebDriverManager.getDriver(), issuePage.verifyContentAuthorIsPresentOnIssuePage(), true, "Verifying the content Author is present on Content");
        //        BaseTest.assertEquals(WebDriverManager.getDriver(), issuePage.verifyContentVolumeIssueIsPresentOnIssuePage(), true, "Verifying the content Volume-Issue is present on Content");
        BaseTest.assertEquals(WebDriverManager.getDriver(), issuePage.verifyContentDOIIsPresentOnIssuePage(), true, "Verifying the content DOI is present on Content");
    }
}
