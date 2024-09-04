package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.AboutStaticPage;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.JournalPage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ContentMetaTagsTest extends BaseTest {
    private MasterPage masterPage;
    private BasePage basePage;
    private ArticleCitationPage articleCitationPage;
    private BrowseOrSearchPage browseOrSearchPage;
    private IssuePage issuePage;
    private JournalPage journalPage;
    private AboutStaticPage aboutStaticPage;
    private String url = "";
    private String testCaseId;

    // Created for PRIME-1028
    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "108 - Verify the Content Meta Tag Fuctionality For Article Page when the user directly navigated to the articlePage")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForArticlePageWhenTheUserDirectlyNavigatedToTheArticlePage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        String searchValue = testData.get("searchtext").toString();
        navigateToUrlLink(url + searchValue);

        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);


        //System.out.println("boolean : " + articleCitationPage.ogURLMetaTagisPresentOnArticlePage());
        // Verifying the og:url meta tag is present on article page and og:URL meta tag
        // content atribute value
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, articleCitationPage.ogURLMetaTagisPresentOnArticlePage(), true, "Verifying the og:URL meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on article page and og:site meta
        // tag content atribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogSiteNameMetaTagisPresentOnArticlePage(), true, "Verifying the og:site name meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on article page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogTypeMetaTagisPresentOnArticlePage(), true, "Verifying the og:type meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on article page and og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogLocaleMetaTagisPresentOnArticlePage(), true, "Verifying the og:locale meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on article page and og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogImageMetaTagisPresentOnArticlePage(), true, "Verifying the og:image meta tag is present on the article page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(articleCitationPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter card meta tag is present on article page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyTwitterCardMetaTagisPresentOnArticlePage(), true, "Verifying the twitter card meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on article page and twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyTwitterTitleMetaTagisPresentOnArticlePage(), true, "Verifying the twitter title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on article page and Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyOgDescriptionMetaTagisPresentOnArticlePage(), true, "Verifying the Og Description meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on article page and
        // twitter description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyTwitterDescriptionMetaTagisPresentOnArticlePage(), true, "Verifying the twitter Description meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

        // Verifying the og title meta tag is present on article page and og title meta
        // tag content attribute value
        String ogTitle = testData.get("ogtitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyOgTitleMetaTagisPresentOnArticlePage(), true, "Verifying the og title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgTitleMetaTagPropertyValue(), ogTitle, "Verifying the og title meta tag content atribute value");

        // Verifying the article author meta tag is present on article page and article
        // author meta tag content attribute value
        String articleAuthour = articleCitationPage.getFirstAuthorNameText();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticleAuthorMetaTagisPresentOnArticlePage(), true, "Verifying the article author meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getArticleAuthorMetaTagProperytValue(), articleAuthour, "Verifying the article author meta tag content atribute value");

        // Verifying the publish time meta tag is present on article page and publish
        // time meta tag content attribute value
        String publishTime = testData.get("publishtime").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticlePublishTimeMetaTagisPresentOnArticlePage(), true, "Verifying the article publish time meta tag is present on the article page");
        //                          BaseTest.assertEquals(driver, articleCitationPage.getArticlePublishTimeMetaTagPropertyValue(), publishTime,
        //                                                      "Verifying the article publish time meta tag content atribute value");

        // Verifying the article section meta tag is present on article page and article
        // section meta tag content attribute value
        String articleSection = testData.get("articlesection").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticleSectionMetaTagisPresentOnArticlePage(), true, "Verifying the article section meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getArticleSectionMetaTagPropertyValue(), articleSection, "Verifying the article section meta tag content atribute value");

        // Verifying the citation issue meta tag is present on article page and citation
        // issue meta tag content attribute value
        String citationIssue = testData.get("citationissue").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationIssueMetaTagisPresentOnArticlePage(), true, "Verifying the citation issue meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationIssueMetaTagPropertyValue(), citationIssue, "Verifying the citation issue meta tag content atribute value");

        // Verifying the citation language meta tag is present on article page and
        // citation language meta tag content attribute value
        String citationLanguage = testData.get("citationlanguage").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationLanguageMetaTagisPresentOnArticlePage(), true, "Verifying the citation language meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationLanguageMetaTagPropertyValue(), citationLanguage, "Verifying the citation language meta tag content atribute value");

        // Verifying the citation title meta tag is present on article page and citation
        // title meta tag content attribute value
        String citationTitle = testData.get("citationtitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationTitleMetaTagisPresentOnArticlePage(), true, "Verifying the citation title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationTitleMetaTagPropertyValue(), citationTitle, "Verifying the citation title meta tag content atribute value");

        // Verifying the citation XML URL meta tag is present on article page and
        // citation XML URL meta tag content attribute value
        String xlurl = testData.get("xmlurlendpart").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationXMLURLMetaTagisPresentOnArticlePage(), true, "Verifying the citation XML URL meta tag is present on the article page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(articleCitationPage.getCitationXMLURLMetaTagPropertyValue(), xlurl), true, "Verifying the citation XML URL meta tag content attribute value");

        // Verifying the citation volume meta tag is present on article page and
        // citation volume meta tag content attribute value
        String ciationVolume = testData.get("citationvolume").toString();
        BaseTest.assertEquals(driver, articleCitationPage.verifyCitationVolumeMetaTagisPresentOnArticlePage(), true, "Verifying the citation volume meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationVolumeMetaTagPropertyValue(), ciationVolume, "Verifying the citation volume meta tag content atribute value");

        // Verifying the citation PDF URL meta tag is present on article page and
        // citation PDF URL meta tag content attribute value
        String citationPDFURL = testData.get("citatinpdfurlendpart").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationPDFURLMetaTagisPresentOnArticlePage(), true, "Verifying the citation PDF URL meta tag is present on the article page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(articleCitationPage.getCitationPDFURLMetaTagPropertyValue(), citationPDFURL), true,
                "Verifying the citation PDF URL meta tag content attribute value");

        // Verifying the citation issn meta tag is present on article page and citation
        // issn meta tag content attribute value
        String citationISSN = testData.get("citationissn").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationISSNMetaTagisPresentOnArticlePage(), true, "Verifying the citation issn meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationISSNMetaTagPropertyValue(), citationISSN, "Verifying the citation issn meta tag content attribute value");

        // Verifying the citation first page meta tag is present on article page and
        // citation first page meta tag content attribute value
        String citationFirstPage = testData.get("citationfirstpage").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationFirstPageMetaTagisPresentOnArticlePage(), true, "Verifying the citation first page meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationFirstPagePageMetaTagPropertyValue(), citationFirstPage, "Verifying the citation first page meta tag content attribute value");

        // Verifying the citation publisher meta tag is present on article page and
        // citation publisher meta tag content attribute value
        String citationPublisher = testData.get("citationpublisher").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationPublisherMetaTagisPresentOnArticlePage(), true, "Verifying the citation publisher meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationPublisherMetaTagPropertyValue(), citationPublisher, "Verifying the citation publisher meta tag content attribute value");

        // Verifying the citation journal title meta tag is present on article page and
        // citation journal title meta tag content attribute value
        String citationJournalTitle = testData.get("citationjournaltitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationJournalTitleMetaTagisPresentOnArticlePage(), true, "Verifying the citation journal title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationJournalTilteMetaTagPropertyValue(), citationJournalTitle, "Verifying the citation journal title meta tag content attribute value");

        // Verifying the citation publication date meta tag is present on article page
        // and citation publication date meta tag content attribute value
        String citationPublicationDate = testData.get("citationpublicationdate").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationPublicationDateMetaTagisPresentOnArticlePage(), true, "Verifying the citation publication date meta tag is present on the article page");
        //                          BaseTest.assertEquals(driver, articleCitationPage.getCitationPublicationDateMetaTagPropertyValue(),
        //                                                      citationPublicationDate, "Verifying the citation publication date meta tag content attribute value");

        // Verifying the citation author meta tag is present on article page and
        // citation author meta tag content attribute value
        String citationAuthor = articleCitationPage.getFirstAuthorNameText();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationAuthorMetaTagisPresentOnArticlePage(), true, "Verifying the citation author meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationAuthorMetaTagPropertyValue(), citationAuthor, "Verifying the citation author meta tag content attribute value");

        // Verifying the citation DOI meta tag is present on article page and citation
        // DOI meta tag content attribute value
        String citationDOI = testData.get("citationdoi").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationDOIMetaTagisPresentOnArticlePage(), true, "Verifying the citation DOI meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationDOIMetaTagPropertyValue(), citationDOI, "Verifying the citation DOI meta tag content attribute value");

        // Verifying the article tag meta tag is present on article page and article tag
        // meta tag content attribute value
        String articleTag = testData.get("articletag").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticleTagMetaTagisPresentOnArticlePage(), true, "Verifying the article tag meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getArticleTagMetaTagPropertyValue(), articleTag, "Verifying the article tag meta tag content attribute value");

        // Verifying the article Reference meta tag is present on article page and
        // articleReference meta tag content attribute value
        String articleReference = testData.get("articlereference").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationReferenceMetaTagisPresentOnArticlePage(), true, "Verifying the article Reference meta tag is present on the article page");
       // BaseTest.assertEquals(driver, articleCitationPage.getCitationReferenceMetaTagPropertyValue(), articleReference, "Verifying the article Reference meta tag content attribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "109 - Verify the Content Meta Tag Fuctionality For Article Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForArticlePage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);

        // navigateToUrlLink(url+searchValue);

        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        // String currentURL = basePage.getURLFromWebPage();

        navigateToUrlLink(url);
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());
        masterPage.clickOnSearchMagnifyingLense();
        //        browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage("Open Access");
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();

        System.out.println("boolean : " + articleCitationPage.ogURLMetaTagisPresentOnArticlePage());
        // Verifying the og:url meta tag is present on article page and og:URL meta tag
        // content atribute value
        // String url =testData.get("url").toString();
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, articleCitationPage.ogURLMetaTagisPresentOnArticlePage(), true, "Verifying the og:URL meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on article page and og:site meta
        // tag content atribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogSiteNameMetaTagisPresentOnArticlePage(), true, "Verifying the og:site name meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on article page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogTypeMetaTagisPresentOnArticlePage(), true, "Verifying the og:type meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on article page and og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogLocaleMetaTagisPresentOnArticlePage(), true, "Verifying the og:locale meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on article page and og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, articleCitationPage.ogImageMetaTagisPresentOnArticlePage(), true, "Verifying the og:image meta tag is present on the article page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(articleCitationPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter card meta tag is present on article page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyTwitterCardMetaTagisPresentOnArticlePage(), true, "Verifying the twitter card meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on article page and twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyTwitterTitleMetaTagisPresentOnArticlePage(), true, "Verifying the twitter title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on article page and Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyOgDescriptionMetaTagisPresentOnArticlePage(), true, "Verifying the Og Description meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on article page and
        // twitter description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyTwitterDescriptionMetaTagisPresentOnArticlePage(), true, "Verifying the twitter Description meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

        // Verifying the og title meta tag is present on article page and og title meta
        // tag content attribute value
        String ogTitle = testData.get("ogtitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyOgTitleMetaTagisPresentOnArticlePage(), true, "Verifying the og title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getOgTitleMetaTagPropertyValue(), ogTitle, "Verifying the og title meta tag content atribute value");

        // Verifying the article author meta tag is present on article page and article
        // author meta tag content attribute value
        String articleAuthour = articleCitationPage.getFirstAuthorNameText();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticleAuthorMetaTagisPresentOnArticlePage(), true, "Verifying the article author meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getArticleAuthorMetaTagProperytValue(), articleAuthour, "Verifying the article author meta tag content atribute value");

        // Verifying the publish time meta tag is present on article page and publish
        // time meta tag content attribute value
        String publishTime = testData.get("publishtime").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticlePublishTimeMetaTagisPresentOnArticlePage(), true, "Verifying the article publish time meta tag is present on the article page");
        //                          BaseTest.assertEquals(driver, articleCitationPage.getArticlePublishTimeMetaTagPropertyValue(), publishTime,
        //                                                      "Verifying the article publish time meta tag content atribute value");

        // Verifying the article section meta tag is present on article page and article
        // section meta tag content attribute value
        String articleSection = testData.get("articlesection").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticleSectionMetaTagisPresentOnArticlePage(), true, "Verifying the article section meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getArticleSectionMetaTagPropertyValue(), articleSection, "Verifying the article section meta tag content atribute value");

        // Verifying the citation issue meta tag is present on article page and citation
        // issue meta tag content attribute value
        String citationIssue = testData.get("citationissue").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationIssueMetaTagisPresentOnArticlePage(), true, "Verifying the citation issue meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationIssueMetaTagPropertyValue(), citationIssue, "Verifying the citation issue meta tag content atribute value");

        // Verifying the citation language meta tag is present on article page and
        // citation language meta tag content attribute value
        String citationLanguage = testData.get("citationlanguage").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationLanguageMetaTagisPresentOnArticlePage(), true, "Verifying the citation language meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationLanguageMetaTagPropertyValue(), citationLanguage, "Verifying the citation language meta tag content atribute value");

        // Verifying the citation title meta tag is present on article page and citation
        // title meta tag content attribute value
        String citationTitle = testData.get("citationtitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationTitleMetaTagisPresentOnArticlePage(), true, "Verifying the citation title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationTitleMetaTagPropertyValue(), citationTitle, "Verifying the citation title meta tag content atribute value");

        // Verifying the citation XML URL meta tag is present on article page and
        // citation XML URL meta tag content attribute value
        String xlurl = testData.get("xmlurlendpart").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationXMLURLMetaTagisPresentOnArticlePage(), true, "Verifying the citation XML URL meta tag is present on the article page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(articleCitationPage.getCitationXMLURLMetaTagPropertyValue(), xlurl), true, "Verifying the citation XML URL meta tag content attribute value");

        // Verifying the citation volume meta tag is present on article page and
        // citation volume meta tag content attribute value
        String ciationVolume = testData.get("citationvolume").toString();
        BaseTest.assertEquals(driver, articleCitationPage.verifyCitationVolumeMetaTagisPresentOnArticlePage(), true, "Verifying the citation volume meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationVolumeMetaTagPropertyValue(), ciationVolume, "Verifying the citation volume meta tag content atribute value");

        // Verifying the citation PDF URL meta tag is present on article page and
        // citation PDF URL meta tag content attribute value
        String citationPDFURL = testData.get("citatinpdfurlendpart").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationPDFURLMetaTagisPresentOnArticlePage(), true, "Verifying the citation PDF URL meta tag is present on the article page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(articleCitationPage.getCitationPDFURLMetaTagPropertyValue(), citationPDFURL), true,
                "Verifying the citation PDF URL meta tag content attribute value");

        // Verifying the citation issn meta tag is present on article page and citation
        // issn meta tag content attribute value
        String citationISSN = testData.get("citationissn").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationISSNMetaTagisPresentOnArticlePage(), true, "Verifying the citation issn meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationISSNMetaTagPropertyValue(), citationISSN, "Verifying the citation issn meta tag content attribute value");

        // Verifying the citation first page meta tag is present on article page and
        // citation first page meta tag content attribute value
        String citationFirstPage = testData.get("citationfirstpage").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationFirstPageMetaTagisPresentOnArticlePage(), true, "Verifying the citation first page meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationFirstPagePageMetaTagPropertyValue(), citationFirstPage, "Verifying the citation first page meta tag content attribute value");

        // Verifying the citation publisher meta tag is present on article page and
        // citation publisher meta tag content attribute value
        String citationPublisher = testData.get("citationpublisher").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationPublisherMetaTagisPresentOnArticlePage(), true, "Verifying the citation publisher meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationPublisherMetaTagPropertyValue(), citationPublisher, "Verifying the citation publisher meta tag content attribute value");

        // Verifying the citation journal title meta tag is present on article page and
        // citation journal title meta tag content attribute value
        String citationJournalTitle = testData.get("citationjournaltitle").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationJournalTitleMetaTagisPresentOnArticlePage(), true, "Verifying the citation journal title meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationJournalTilteMetaTagPropertyValue(), citationJournalTitle, "Verifying the citation journal title meta tag content attribute value");

        // Verifying the citation publication date meta tag is present on article page
        // and citation publication date meta tag content attribute value
        String citationPublicationDate = testData.get("citationpublicationdate").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationPublicationDateMetaTagisPresentOnArticlePage(), true, "Verifying the citation publication date meta tag is present on the article page");
        //                          BaseTest.assertEquals(driver, articleCitationPage.getCitationPublicationDateMetaTagPropertyValue(),
        //                                                      citationPublicationDate, "Verifying the citation publication date meta tag content attribute value");

        // Verifying the citation author meta tag is present on article page and
        // citation author meta tag content attribute value
        String citationAuthor = articleCitationPage.getFirstAuthorNameText();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationAuthorMetaTagisPresentOnArticlePage(), true, "Verifying the citation author meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationAuthorMetaTagPropertyValue(), citationAuthor, "Verifying the citation author meta tag content attribute value");

        // Verifying the citation DOI meta tag is present on article page and citation
        // DOI meta tag content attribute value
        String citationDOI = testData.get("citationdoi").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationDOIMetaTagisPresentOnArticlePage(), true, "Verifying the citation DOI meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getCitationDOIMetaTagPropertyValue(), citationDOI, "Verifying the citation DOI meta tag content attribute value");

        // Verifying the article tag meta tag is present on article page and article tag
        // meta tag content attribute value
        String articleTag = testData.get("articletag").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyArticleTagMetaTagisPresentOnArticlePage(), true, "Verifying the article tag meta tag is present on the article page");
        BaseTest.assertEquals(driver, articleCitationPage.getArticleTagMetaTagPropertyValue(), articleTag, "Verifying the article tag meta tag content attribute value");

        // Verifying the article Reference meta tag is present on article page and
        // articleReference meta tag content attribute value
        String articleReference = testData.get("articlereference").toString();
        BaseTest.assertEquals(driver, articleCitationPage.VerifyCitationReferenceMetaTagisPresentOnArticlePage(), true, "Verifying the article Reference meta tag is present on the article page");
        //BaseTest.assertEquals(driver, articleCitationPage.getCitationReferenceMetaTagPropertyValue(), articleReference, "Verifying the article Reference meta tag content attribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "110 - Verify the Content Meta Tag Fuctionality For home Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForHomePage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url);

        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);

        // Verifying the og:url meta tag is present on home page and og:URL meta tag
        // content atribute value
        // String url =testData.get("url").toString();
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, masterPage.ogURLMetaTagisPresentOnHomePage(), true, "Verifying the og:URL meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on home page and og:site meta
        // tag content atribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, masterPage.ogSiteNameMetaTagisPresentOnHomePage(), true, "Verifying the og:site name meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on home page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, masterPage.ogTypeMetaTagisPresentOnHomePage(), true, "Verifying the og:type meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on home page and og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, masterPage.ogLocaleMetaTagisPresentOnHomePage(), true, "Verifying the og:locale meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on home page and og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, masterPage.ogImageMetaTagisPresentOnHomePage(), true, "Verifying the og:image meta tag is present on the home page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(masterPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on home page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, masterPage.VerifyTwitterCardMetaTagisPresentOnHomePage(), true, "Verifying the twitter card meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on home page and twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, masterPage.VerifyTwitterTitleMetaTagisPresentOnHomePage(), true, "Verifying the twitter title meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on home page and Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, masterPage.VerifyOgDescriptionMetaTagisPresentOnHomePage(), true, "Verifying the Og Description meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on home page and Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, masterPage.VerifyDescriptionMetaTagisPresentOnHomePage(), true, "Verifying the Description meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on home page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, masterPage.VerifyTwitterCardMetaTagisPresentOnHomePage(), true, "Verifying the twitter Description meta tag is present on the home page");
        BaseTest.assertEquals(driver, masterPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class,
            description = "111 - verify that the content meta tag fuctionality for search page when the user directly navigated to the search Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForSearchPageWhenTheUserDirectlyNavigatedToTheSearchPage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url + "/search");

        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);

        // Verifying the og:url meta tag is present on home page and og:URL meta tag
        // content atribute value
        // String url =testData.get("url").toString();
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogURLMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:URL meta tag is present on the home page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on browse Or Search page and
        // og:site meta
        // tag content atribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogSiteNameMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:site name meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on home page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogTypeMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:type meta tag is present on the home page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on browse Or Search page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogLocaleMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:locale meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on browse Or Search page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogImageMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:image meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(browseOrSearchPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on home page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyTwitterCardMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the twitter card meta tag is present on the home page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on browse Or Search page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyTwitterTitleMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the twitter title meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on browse Or Search page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyOgDescriptionMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the Og Description meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on browse Or Search page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyDescriptionMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the Description meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on browse Or Search
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyTwitterCardMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the twitter Description meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "112 - Verify the Content Meta Tag Fuctionality For search Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForSearchPage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url);

        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        // Verifying the og:url meta tag is present on browse Or Search page and og:URL meta tag
        // content atribute value
        // String url =testData.get("url").toString();
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogURLMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:URL meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on browse Or Search page and
        // og:site meta
        // tag content atribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogSiteNameMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:site name meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on browse Or Search page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogTypeMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:type meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on browse Or Search page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogLocaleMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:locale meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on browse Or Search page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.ogImageMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the og:image meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(browseOrSearchPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on browse Or Search page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyTwitterCardMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the twitter card meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on browse Or Search page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyTwitterTitleMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the twitter title meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on browse Or Search page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyOgDescriptionMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the Og Description meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on browse Or Search page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyDescriptionMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the Description meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on browse Or Search
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, browseOrSearchPage.VerifyTwitterCardMetaTagisPresentOnBrowseOrSearchPage(), true, "Verifying the twitter Description meta tag is present on the browse Or Search page");
        BaseTest.assertEquals(driver, browseOrSearchPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "113 - Verify the Content Meta Tag Fuctionality For Issue Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForIssuePage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);

        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());
        masterPage.clickOnSearchMagnifyingLense();
        //        browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage("Open Access");
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
        articleCitationPage.clickOnJournalCoverOnArtcilePage();

        // Verifying the og:url meta tag is present on issue page and og:URL meta tag
        // content attribute value
        // String url =testData.get("url").toString();
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, issuePage.ogURLMetaTagisPresentOnIssuePage(), true, "Verifying the og:URL meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on issue page and
        // og:site meta
        // tag content attribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, issuePage.ogSiteNameMetaTagisPresentOnIssuePage(), true, "Verifying the og:site name meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on issue page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, issuePage.ogTypeMetaTagisPresentOnIssuePage(), true, "Verifying the og:type meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on issue page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, issuePage.ogLocaleMetaTagisPresentOnIssuePage(), true, "Verifying the og:locale meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on issue page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, issuePage.ogImageMetaTagisPresentOnIssuePage(), true, "Verifying the og:image meta tag is present on the issue page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(issuePage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on issue page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyTwitterCardMetaTagisPresentOnIssuePage(), true, "Verifying the twitter card meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on issue page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyTwitterTitleMetaTagisPresentOnIssuePage(), true, "Verifying the twitter title meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on issue page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyOgDescriptionMetaTagisPresentOnIssuePage(), true, "Verifying the Og Description meta tag is present on the issue page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(issuePage.getOgDescriptionMetaTagPropertyValue(), OgDescription), true, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on issue page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyDescriptionMetaTagisPresentOnIssuePage(), true, "Verifying the Description meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on issue
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyTwitterCardMetaTagisPresentOnIssuePage(), true, "Verifying the twitter Description meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

        // Verifying the og title meta tag is present on issue
        // page and og title meta tag content attribute value
        String ogtitle = testData.get("ogtitle").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyOgTitleMetaTagisPresentOnIssuePage(), true, "Verifying the Og Title meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgTitleMetaTagPropertyValue(), ogtitle, "Verifying the Og Title meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "114 - Verify the Content Meta Tag Fuctionality For Issue Page When the user directly navigated to the Issue Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForIssuePageWhenTheUserDirectlyNavigatedToTheIssuePage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        String searchValue = testData.get("searchtext").toString();
        navigateToUrlLink(url + searchValue);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);


        // Verifying the og:url meta tag is present on issue page and og:URL meta tag
        // content attribute value
        // String url =testData.get("url").toString();
        String currentURL = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, issuePage.ogURLMetaTagisPresentOnIssuePage(), true, "Verifying the og:URL meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgURLMetaTagPropertyValue(), currentURL, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on issue page and
        // og:site meta
        // tag content attribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, issuePage.ogSiteNameMetaTagisPresentOnIssuePage(), true, "Verifying the og:site name meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on issue page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, issuePage.ogTypeMetaTagisPresentOnIssuePage(), true, "Verifying the og:type meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on issue page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, issuePage.ogLocaleMetaTagisPresentOnIssuePage(), true, "Verifying the og:locale meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on issue page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, issuePage.ogImageMetaTagisPresentOnIssuePage(), true, "Verifying the og:image meta tag is present on the issue page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(issuePage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on issue page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyTwitterCardMetaTagisPresentOnIssuePage(), true, "Verifying the twitter card meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on issue page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyTwitterTitleMetaTagisPresentOnIssuePage(), true, "Verifying the twitter title meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on issue page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyOgDescriptionMetaTagisPresentOnIssuePage(), true, "Verifying the Og Description meta tag is present on the issue page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(issuePage.getOgDescriptionMetaTagPropertyValue(), OgDescription), true, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on issue page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyDescriptionMetaTagisPresentOnIssuePage(), true, "Verifying the Description meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on issue
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyTwitterCardMetaTagisPresentOnIssuePage(), true, "Verifying the twitter Description meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

        // Verifying the og title meta tag is present on issue
        // page and og title meta tag content attribute value
        String ogtitle = testData.get("ogtitle").toString();
        BaseTest.assertEquals(driver, issuePage.VerifyOgTitleMetaTagisPresentOnIssuePage(), true, "Verifying the Og Title meta tag is present on the issue page");
        BaseTest.assertEquals(driver, issuePage.getOgTitleMetaTagPropertyValue(), ogtitle, "Verifying the Og Title meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "115 - Verify the Content Meta Tag fuctionality For Journal Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForJournalPage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);

        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchkeyword").toString());
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
        articleCitationPage.clickOnJournalCoverOnArtcilePage();
        issuePage.clickOnAllIssuesOnIssuePage();

        // Verifying the og:url meta tag is present on journal page and og:URL meta tag
        // content attribute value
        // String url =testData.get("url").toString();

        String ogUrl = testData.get("ogurl").toString();
        BaseTest.assertEquals(driver, journalPage.ogURLMetaTagisPresentOnJournalPage(), true, "Verifying the og:URL meta tag is present on the journal page");
        System.out.println("Og URL : " + journalPage.getOgURLMetaTagPropertyValue());
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(journalPage.getOgURLMetaTagPropertyValue(), ogUrl), true, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on journalPage page and
        // og:site meta
        // tag content attribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, journalPage.ogSiteNameMetaTagisPresentOnJournalPage(), true, "Verifying the og:site name meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on journal page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, journalPage.ogTypeMetaTagisPresentOnJournalPage(), true, "Verifying the og:type meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on journal page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, journalPage.ogLocaleMetaTagisPresentOnJournalPage(), true, "Verifying the og:locale meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on journal page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, journalPage.ogImageMetaTagisPresentOnJournalPage(), true, "Verifying the og:image meta tag is present on the journal page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(journalPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on journalPage page and
        // twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyTwitterCardMetaTagisPresentOnJournalPage(), true, "Verifying the twitter card meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on journal page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyTwitterTitleMetaTagisPresentOnJournalPage(), true, "Verifying the twitter title meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on journal page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyOgDescriptionMetaTagisPresentOnJournalPage(), true, "Verifying the Og Description meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on journalPage page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyDescriptionMetaTagisPresentOnJournalPage(), true, "Verifying the Description meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on journalPage
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyTwitterCardMetaTagisPresentOnJournalPage(), true, "Verifying the twitter Description meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

        // Verifying the og title meta tag is present on journalPage
        // page and og title meta tag content attribute value
        String ogtitle = testData.get("ogtitle").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyOgTitleMetaTagisPresentOnJournalPage(), true, "Verifying the Og Title meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgTitleMetaTagPropertyValue(), ogtitle, "Verifying the Og Title meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class,
            description = "116 - Verify the Content Meta Tag Fuctionality For Journal Page when the user directly navigated to the Journal Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForJournalPageWhenTheUserDirectlyNavigatedToTheJournalPage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        String searchValue = testData.get("searchtext").toString();
        navigateToUrlLink(url + searchValue);
        journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);

        // Verifying the og:url meta tag is present on journal page and og:URL meta tag
        // content attribute value
        // String url =testData.get("url").toString();

        String ogUrl = testData.get("ogurl").toString();
        BaseTest.assertEquals(driver, journalPage.ogURLMetaTagisPresentOnJournalPage(), true, "Verifying the og:URL meta tag is present on the journal page");
        System.out.println("Og URL : " + journalPage.getOgURLMetaTagPropertyValue());
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(journalPage.getOgURLMetaTagPropertyValue(), ogUrl), true, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on journalPage page and
        // og:site meta
        // tag content attribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, journalPage.ogSiteNameMetaTagisPresentOnJournalPage(), true, "Verifying the og:site name meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on journal page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, journalPage.ogTypeMetaTagisPresentOnJournalPage(), true, "Verifying the og:type meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on journal page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, journalPage.ogLocaleMetaTagisPresentOnJournalPage(), true, "Verifying the og:locale meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on journal page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, journalPage.ogImageMetaTagisPresentOnJournalPage(), true, "Verifying the og:image meta tag is present on the journal page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(journalPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on journalPage page and
        // twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyTwitterCardMetaTagisPresentOnJournalPage(), true, "Verifying the twitter card meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on journal page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyTwitterTitleMetaTagisPresentOnJournalPage(), true, "Verifying the twitter title meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on journal page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyOgDescriptionMetaTagisPresentOnJournalPage(), true, "Verifying the Og Description meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on journalPage page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyDescriptionMetaTagisPresentOnJournalPage(), true, "Verifying the Description meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on journalPage
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyTwitterCardMetaTagisPresentOnJournalPage(), true, "Verifying the twitter Description meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");

        // Verifying the og title meta tag is present on journalPage
        // page and og title meta tag content attribute value
        String ogtitle = testData.get("ogtitle").toString();
        BaseTest.assertEquals(driver, journalPage.VerifyOgTitleMetaTagisPresentOnJournalPage(), true, "Verifying the Og Title meta tag is present on the journal page");
        BaseTest.assertEquals(driver, journalPage.getOgTitleMetaTagPropertyValue(), ogtitle, "Verifying the Og Title meta tag content atribute value");

    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class,
            description = "117 - Verify the Content Meta Tag Fuctionality For About static Page when the user directly navigated to the About static Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForStaticAboutPageWhenTheUserDirectlyNavigatedToTheStaticAboutPage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url + "page/about");
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);
        aboutStaticPage = BasePage.initialize(WebDriverManager.getDriver(), AboutStaticPage.class);

        // Verifying the og:url meta tag is present on About static page and og:URL meta tag
        // content attribute value
        // String url =testData.get("url").toString();

        String ogUrl = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, aboutStaticPage.ogURLMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:URL meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgURLMetaTagPropertyValue(), ogUrl, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on About static page and
        // og:site meta
        // tag content attribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogSiteNameMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:site name meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on About static page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogTypeMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:type meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on About static page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogLocaleMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:locale meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on About static page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogImageMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:image meta tag is present on the About static page");

        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(aboutStaticPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on About static page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyTwitterCardMetaTagisPresentOnAboutStaticPage(), true, "Verifying the twitter card meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on About static page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyTwitterTitleMetaTagisPresentOnAboutStaticPage(), true, "Verifying the twitter title meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on About static page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyOgDescriptionMetaTagisPresentOnAboutStaticPage(), true, "Verifying the Og Description meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on About static page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyDescriptionMetaTagisPresentOnAboutStaticPage(), true, "Verifying the Description meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on About static page
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyTwitterCardMetaTagisPresentOnAboutStaticPage(), true, "Verifying the twitter Description meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "118 - Verify the Content Meta Tag Fuctionality For About static Page")
    @Story("EPIC-1028")
    public void verifyThatTheContentMetaTagFuctionalityForStaticAboutPage() throws Exception {

        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        navigateToUrlLink(url);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        journalPage = BasePage.initialize(WebDriverManager.getDriver(), JournalPage.class);
        aboutStaticPage = BasePage.initialize(WebDriverManager.getDriver(), AboutStaticPage.class);

        masterPage.clickOnAboutStaticButtonAtFooter();

        // Verifying the og:url meta tag is present on About static page and og:URL meta tag
        // content attribute value
        // String url =testData.get("url").toString();

        String ogUrl = basePage.getURLFromWebPage();
        BaseTest.assertEquals(driver, aboutStaticPage.ogURLMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:URL meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgURLMetaTagPropertyValue(), ogUrl, "Verifying the og:URL meta tag content atribute value");

        // Verifying the og:site meta tag is present on About static page and
        // og:site meta
        // tag content attribute value
        String sitename = testData.get("sitename").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogSiteNameMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:site name meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgSiteNameMetaTagPropertyValue(), sitename, "Verifying the og:site Name meta tag content atribute value");

        // Verifying the og:type meta tag is present on About static page and og:type meta
        // tag content atribute value
        String ogType = testData.get("ogtype").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogTypeMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:type meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgTypeMetaTagPropertyValue(), ogType, "Verifying the og:type meta tag content atribute value");

        // Verifying the og:locale meta tag is present on About static page and
        // og:locale
        // meta tag content attribute value
        String ogLocale = testData.get("oglocale").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogLocaleMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:locale meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgLocaleMetaTagPropertyValue(), ogLocale, "Verifying the og:locale meta tag content atribute value");

        // Verifying the og:image meta tag is present on About static page and
        // og:image meta
        // tag content attribute value
        String ogImage = testData.get("ogimage").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.ogImageMetaTagisPresentOnAboutStaticPage(), true, "Verifying the og:image meta tag is present on the About static page");
        BaseTest.assertEquals(driver, verifyStringContainsSpecificWord(aboutStaticPage.getOgImageMetaTagPropertyValue(), ogImage), true, "Verifying the og:image meta tag content atribute value");

        // Verifying the twitter title meta tag is present on About static page and twitter
        // title meta tag content attribute value
        String twitterCard = testData.get("twittercard").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyTwitterCardMetaTagisPresentOnAboutStaticPage(), true, "Verifying the twitter card meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getTwitterCardMetaTagPropertyValue(), twitterCard, "Verifying the twitter card meta tag content atribute value");

        // Verifying the twitter title meta tag is present on About static page and
        // twitter
        // title meta tag content attribute value
        String twitterTitle = testData.get("twittertitle").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyTwitterTitleMetaTagisPresentOnAboutStaticPage(), true, "Verifying the twitter title meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getTwitterTitleMetaTagPropertyValue(), twitterTitle, "Verifying the twitter title meta tag content atribute value");

        // Verifying the Og description meta tag is present on About static page and
        // Og
        // description meta tag content attribute value
        String OgDescription = testData.get("ogdescription").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyOgDescriptionMetaTagisPresentOnAboutStaticPage(), true, "Verifying the Og Description meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getOgDescriptionMetaTagPropertyValue(), OgDescription, "Verifying the Og Description meta tag content atribute value");

        // Verifying the Og description meta tag is present on About static page and
        // Og
        // description meta tag content attribute value
        String descriptionTag = testData.get("descriptiontag").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyDescriptionMetaTagisPresentOnAboutStaticPage(), true, "Verifying the Description meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getDescriptionMetaTagPropertyValue(), descriptionTag, "Verifying the Description meta tag content atribute value");

        // Verifying the twitter description meta tag is present on About static page
        // page and Og
        // description meta tag content attribute value
        String twitterDescription = testData.get("twitterdescription").toString();
        BaseTest.assertEquals(driver, aboutStaticPage.VerifyTwitterCardMetaTagisPresentOnAboutStaticPage(), true, "Verifying the twitter Description meta tag is present on the About static page");
        BaseTest.assertEquals(driver, aboutStaticPage.getTwitterDescriptionMetaTagPropertyValue(), twitterDescription, "Verifying the twitter Description meta tag content atribute value");
    }


}
