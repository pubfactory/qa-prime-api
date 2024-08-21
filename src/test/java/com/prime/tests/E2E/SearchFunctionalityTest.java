package com.prime.tests.E2E;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.prime.generics.BasePage;
import com.prime.generics.BaseTest;
import com.prime.generics.WebDriverManager;
import com.prime.pageFactory.pages.fpj.ArticleCitationPage;
import com.prime.pageFactory.pages.fpj.BrowseOrSearchPage;
import com.prime.pageFactory.pages.fpj.IssuePage;
import com.prime.pageFactory.pages.fpj.MasterPage;
import com.prime.pageFactory.pages.fpj.SignInPage;
import com.prime.retryAnalyzers.Retry;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class SearchFunctionalityTest extends BaseTest {

    private MasterPage masterPage;
    private BasePage basePage;
    private BrowseOrSearchPage browseOrSearchPage;
    private ArticleCitationPage articleCitationPage;
    private SignInPage signInPage;
    private IssuePage issuePage;
    private String url = "";
    private String testCaseId;

    @Severity(SeverityLevel.BLOCKER)
    @Test(groups = {"fpj"}, enabled = true, retryAnalyzer = Retry.class, description = "1722541 - Verify search page top panel")
    @Story("EPIC-28")

    public void VerifySearchPageTopPanel() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        //String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchBoxIsPresentOnHeader(), true, "Verifying the Searchbox is presented on header");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        System.out.println(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        basePage = BasePage.initialize(WebDriverManager.getDriver(), BasePage.class);
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getSearchPageLabelText(), testData.get("searchpagetitle").toString(), "Verifying search result page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifySearchSlugSignIsPresentOnBrowseOrSearchPage(testData.get("searchtext").toString()), true,
                "Verifying Search Slug(-) Sign is Present On browse or SearchPage");
        browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(testData.get("searchtext").toString());
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBrowsePageLabelText(), testData.get("browserpagetitle").toString(), "Verifying Browse page");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyClearAllButtonIsPresentOnBrowseOrSearchPage(), true, "Verifying Clear All Button is present on Browse or search page");
        browseOrSearchPage.clickOnClearAllOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBrowsePageLabelText(), testData.get("browserpagetitle").toString(), "Verifying Browse page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyItemPerPageDDIsPresentOnBrowseOrSearchPage(), true, "Verifying the Item per page dropdown is present on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getDefaultItemPerPageDDValueOnBrowseOrSearchPage(), testData.get("itemperpagedefaultval").toString(),
                "Verifying the Item per page dropdown default value");
        browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(testData.get("itemperpagetwenty").toString());
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("pageSize=20"), "Verifying item per page is 20 on browse or search result page");
        browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(testData.get("itemperpagefifty").toString());
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("pageSize=50"), "Verifying item per page is 50 on browse or search result page");
        //          browseOrSearchPage.waitForAllItemVisible("20");
        //          BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getTotalItemCountOnPage(),testData.get("itemperpagefifty").toString(),"Verifying the "+testData.get("itemperpagefifty").toString()+" Item per page is present on page");         
    }

    @Test(groups = {"fpj"}, enabled = true, retryAnalyzer = Retry.class, description = "1722542 - Verify pagination and sorting options in the search results page")
    @Story("EPIC-28")

    public void VerifyPpaginationAndSortingOptionsIntheSearchResultsPage() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        //String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        browseOrSearchPage.SelectSortDateAscFromSortByDropdownOnSearchOrBrowsePage();
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("sort=date"), "Verifying the search results are sorted in ascending order");
        browseOrSearchPage.SelectSortDateDescFromSortByDropdownOnSearchOrBrowsePage();
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("sort=datedescending"), "Verifying the search results are sorted in Descending order");

        int defaultItemPerPage = browseOrSearchPage.getLastItemOfPaginationLinks("10");
        System.out.println("defaultItemPerPage" + defaultItemPerPage);
        browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(testData.get("itemperpagetwenty").toString());
        driver.navigate().refresh();
        int twentyItemPerPage = browseOrSearchPage.getLastItemOfPaginationLinks("20");
        System.out.println("twentyItemPerPage" + twentyItemPerPage);
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(defaultItemPerPage, twentyItemPerPage), true,
                "Verifying the pagInation link size is changed after selecting the twenty item per page from item per page dropdown");
        browseOrSearchPage.selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(testData.get("itemperpagefifty").toString());
        driver.navigate().refresh();
        int fiftyItemPerPage = browseOrSearchPage.getLastItemOfPaginationLinks("50");
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(twentyItemPerPage, fiftyItemPerPage), true,
                "Verifying the pagInation link size is changed after selecting the twenty item per page from item per page dropdown");
    }

    @Test(groups = {"fpj"}, enabled = true, retryAnalyzer = Retry.class, description = "1722543 - Verify that  search results details , toolbar options , Save options are as expected")
    @Story("EPIC-28")

    public void VerifyThatSsearchResultsDetailsToolbarOptionsSaveOptionsAreAsExpected() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        //String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        //masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentTitleNameIsPresentOnBrosweOrSearchPage(), true, "Verifying the content title name is present on browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentAccessIconIsPresentOnBrosweOrSearchPage(), true, "Verifying the content Access is present on Content");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentAuthorIsPresentOnBrosweOrSearchPage(), true, "Verifying the content Author is present on Content");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentVolumeIssueIsPresentOnBrosweOrSearchPage(), true, "Verifying the content Volume-Issue is present on Content");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentDOIIsPresentOnBrosweOrSearchPage(), true, "Verifying the content DOI is present on Content");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentOnlinePublicationDateIsPresentOnBrosweOrSearchPage(), true,
                "Verifying the content Online Publication Date is present on Content");
        //        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyContentAbstractIsPresentOnBrosweOrSearchPage(), true, "Verifying the content Abstract is present on Content");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifySaveButtonIsPresentOnBrowseOrSearchPage(), true, "Verifying the Save Button is present on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyShareButtonIsPresentOnBrowseOrSearchPage(), true, "Verifying the Share Button is present on Browse or search page");
        browseOrSearchPage.clickOnSaveButtonOnBrowseOrSearchPage();
        signInPage = BasePage.initialize(WebDriverManager.getDriver(), SignInPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), signInPage.getSignInPersonalProfilePageHeaderText(), testData.get("signinpageheader").toString(), "Verifying the SignIn page Header on Sign in page");
        WebDriverManager.getDriver().navigate().back();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineTermFilterTextOnSearchOrBrowsePage(), testData.get("refineterm").toString(),
                "Verifying the Refine terms filter text on search or Browse Page");
        //        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByTypeFilterTextonSearchOrBrowsePage(), testData.get("refinetype").toString(),
        //                "Verifying the Refine by type filter text on search or Browse Page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByArticleTypeFilterTextonSearchOrBrowsePage(), testData.get("refinearticletype").toString(),
                "Verifying the Refine by artcile type filter text on search or Browse Page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByDateFilterTextonSearchOrBrowsePage(), testData.get("refinedate").toString(),
                "Verifying the Refine by date filter text on search or Browse Page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFromDateDropdownIsPresentOnBrosweOrSearchPage(), true,
                "Verifying the from date dropdown in refine Date filter is present on search or Browse Page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyToDateDropdownIsPresentOnBrosweOrSearchPage(), true,
                "Verifying the to date dropdown in refine Date filter is present on search or Browse Page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByAccessFilterTextonSearchOrBrowsePage(), testData.get("refineaccess").toString(),
                "Verifying the Refine by Access filter text on search or Browse Page");
    }

    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "1722544 - Verify various Refine terms in the search results page")
    @Story("EPIC-28")

    public void VerifyVariousRefineTermsInTheSearchResultsPage() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        int browseResultCount = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        String authorEditor = browseOrSearchPage.getFirstAuthorNameOnFirstArticleOnBrowseOrSearchPage();

        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptionauthor").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), authorEditor);
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        int editorFilter = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(browseResultCount, editorFilter), true,
                "Verifying the total result count after applying the Author Editor filter from refine term filter on search Ppge");
        System.out.println("six " + BaseTest.getLastsixStringCharacter(authorEditor));
        String[] authoreditorwords = authorEditor.split(" ");
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(authoreditorwords[0]), "Verifying the Author Editor filter is applied on search result page");


        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptionfulltext").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), testData.get("refinefiltervaluefulltext").toString());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        int fullText = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(browseResultCount, fullText), true,
                "Verifying the total result count after applying the fulltext filter from refine term filter on search apge");
        String[] fulltext = testData.get("refinefiltervaluefulltext").toString().split(" ");
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(fulltext[0]), "Verifying the fulltext filter is applied on search result page");

        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptionisbndoi").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), browseOrSearchPage.getFirstDOIValueOnBrowseOrSearchPage());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        int isbnDOI = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(browseResultCount, isbnDOI), true,
                "Verifying the total result count after applying the ISBN/ISSN/DOI filter from refine term filter on search apge");
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(BaseTest.getLastsixStringCharacter(browseOrSearchPage.getFirstDOIValueOnBrowseOrSearchPage())),
                "Verifying the ISBN/ISSN/DOI filter is applied on search result page");


        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptionabstract").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), testData.get("refinefiltervalueabstract").toString());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        int abstractCount = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(browseResultCount, abstractCount), true,
                "Verifying the total result count after applying the Abstract filter from refine term filter on search apge");
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL("adv-field[0]=abstract&adv-value[0]=intravenous+anesthetic"), "Verifying the Abstract filter is applied on search result page");

        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptiontitle").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        int titleResultCount = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.VerifyPagInationLinksizeChange(browseResultCount, titleResultCount), true,
                "Verifying the total result count after applying the Title filter from refine term filter on search apge");

        String[] titleText = browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage().toString().split(" ");
        BaseTest.assertTrue(driver, BaseTest.verifyTextInURL(titleText[0]), "Verifying the Title filter is applied on search result page");

        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptionaffiliation").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), testData.get("refinefiltervalueaffiliation").toString());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        // Thread.sleep(5000);
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
        articleCitationPage.clickFirstAuthorOnArticlePage();
        String authorlabel = articleCitationPage.getauthorAffiliationPopupLabel();
        System.out.println("authorlabel : "+ authorlabel);
        BaseTest.assertEquals(WebDriverManager.getDriver(), BaseTest.verifyStringContainsSpecificWord(authorlabel, testData.get("refinefiltervalueaffiliation").toString()), true,
                "Verifying affiliation search keyword is exist in author affilaition popup");
    }

    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class,
            description = "1722545 - Verify that user is able to perform search with the combination of refine terms by adding, clearing , removing more than one rows.")
    @Story("EPIC-28")

    public void VerifyUserIsAbleToPerformSearchWithTheCombinationOfRefineTermsByAddingClearingRemovingMoreThanOneRows() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        //String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        String firstTitle = browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage();
        String firstDOI = browseOrSearchPage.getFirstDOIValueOnBrowseOrSearchPage();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptiontitle").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), firstTitle);
        browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselecttwo").toString(), testData.get("refinefilteroptionisbndoi").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueentertwo").toString(), firstDOI);
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage(firstTitle), true,
                "Verifying the Title filter from Refine terms filter is presented on search/browse result page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage(firstDOI), true,
                "Verifying the DOI filter Refine terms filter is presented on search/browse result page");

        browseOrSearchPage.clickOnClearAllOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyClearButtonFromRefineTermIsPresentOnBrowseOrSearchPage(), true,
                "Verifying the Clear button from Refine terms is present on Browse or search page");
        browseOrSearchPage.clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermValueOptionIsPresentOnBrowseOrSearchPage(testData.get("testidvalueselecttwo").toString()), true,
                "Verifying the additional Row Refine terms option is present");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermTextBoxIsPresentOnBrowseOrSearchPage(testData.get("testidvalueentertwo").toString()), true,
                "Verifying the additional Row Refine terms textbox is present");
        browseOrSearchPage.clickOnCancelButtonFrontOfRefineTermTextBoxInRefineTermDDOnBrowseOrSearchPage(testData.get("rowcancelbuttton").toString());
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermValueOptionIsNotPresentOnBrowseOrSearchPage(testData.get("testidvalueselecttwo").toString()), true,
                "Verifying the additional Row Refine terms textbox is not present");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermTextBoxIsNotPresentOnBrowseOrSearchPage(testData.get("testidvalueentertwo").toString()), true,
                "Verifying the additional Row Refine terms option is not present");

        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptiontitle").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage());
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermFilterSearchKewordIsPresentOnSearchOrBrowsePage(browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage()), true,
                "Verifying the refine term filter search keyword is presented on the search/browse page before click on the ClearAll button on the search/browse page");
        browseOrSearchPage.clickOnClearAllOnBrowseOrSearchPage();
        WebDriverManager.getDriver().navigate().refresh();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermFilterSearchKewordIsNotPresentOnSearchOrBrowsePage(browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage()), true,
                "Verifying the refine term filter search keyword is not presented on the search/browse page after clicked on the ClearAll button on the search/browse page");
        //
        masterPage.clickOnSearchMagnifyingLense();
        String title = browseOrSearchPage.getFirstArticleTitleOnBrowseOrSearchPage();
        browseOrSearchPage.selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(testData.get("testidvalueselect").toString(), testData.get("refinefilteroptiontitle").toString());
        browseOrSearchPage.enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(testData.get("testidvalueenter").toString(), title);
        browseOrSearchPage.clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermFilterSearchKewordIsPresentOnSearchOrBrowsePage(title), true,
                "Verifying the refine term filter search keyword is presented on the search/browse page before click on the refine term search slug sign on the search/browse page");
        browseOrSearchPage.clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(title);
        WebDriverManager.getDriver().navigate().refresh();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyRefineTermFilterSearchKewordIsNotPresentOnSearchOrBrowsePage(title), true,
                "Verifying the refine term filter search keyword is not presented on the search/browse page after clicked on the refine term search slug sign on the search/browse page");
    }

    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class,
            description = "1722546 - Verify that the appropriate result is displayed when user select the Article type filter and access type filter from index card")
    @Story("EPIC-28")

    public void VerifyThatTheAppropriateResultIsDisplayedWhenUserSelectTheArticleTypeFilterAndAccessTypeFilterFromIndexCard() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByArticleTypeFilterTextonSearchOrBrowsePage(), testData.get("refinearticletype").toString(),
                "Verifying the Refine by artcile type filter text on search or Browse Page");
        browseOrSearchPage.clickOnArticleTypeFilterValueOnBrowseOrSearchPage(testData.get("articletypename").toString());
        System.out.println("Current URL : " + driver.getCurrentUrl());
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("articleType=" + testData.get("articletypename").toString().toLowerCase()),
                "Verifying the article type result is displayed on search result page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage(testData.get("articletypename").toString()), true,
                "Verifying the article type is displayed on search/browse result page");

        masterPage.clickOnSearchMagnifyingLense();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByAccessFilterTextonSearchOrBrowsePage(), testData.get("refineaccess").toString(),
                "Verifying the Refine by Access filter text on search or Browse Page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.VerifyfilterOptionInRefineByAccessFilterIsPresentOnBrowseOrSearchPage(testData.get("allaccess").toString()), true,
                "verifying the all access option in Refine By Access filter is present on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.VerifyfilterOptionInRefineByAccessFilterIsPresentOnBrowseOrSearchPage(testData.get("useraccess").toString()), true,
                "verifying the user access option in Refine By Access filter is present on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.VerifyfilterOptionInRefineByAccessFilterIsPresentOnBrowseOrSearchPage(testData.get("openaccess").toString()), true,
                "verifying the open access option in Refine By Access filter is present on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.VerifyfilterOptionInRefineByAccessFilterIsPresentOnBrowseOrSearchPage(testData.get("freeaccess").toString()), true,
                "verifying the free access option in Refine By Access filter is present on Browse or search page");

        browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("openaccess").toString());
        WebDriverManager.getDriver().navigate().refresh();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage(testData.get("openaccess").toString()), true,
                "Verifying the open access type result is displayed on search/browse result page");
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("access=" + testData.get("open").toString()), "Verifying the open access type result is displayed on search/browse result page");
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(testData.get("freeaccess").toString());
        WebDriverManager.getDriver().navigate().refresh();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage(testData.get("freeaccess").toString()), true,
                "Verifying the free access type result is displayed on search/browse result page");
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("access=" + testData.get("free").toString().toUpperCase()),
                "Verifying the open access type result is displayed on search/browse result page");

        //This funct available in dev not in UAT (Refine by type)
        //        masterPage.clickOnSearchMagnifyingLense();
        //        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getRefineByTypeFilterTextonSearchOrBrowsePage(), testData.get("refinetype").toString(),
        //                "Verifying the Refine by type filter text on search or Browse Page");
        //        browseOrSearchPage.clickOnRefineByTypeFilterValueOnBrowseOrSearchPage(testData.get("refinetypevalue").toString());
        //        WebDriverManager.getDriver().navigate().refresh();
        //        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyFilterValueIsPresentOnBrowseOrSearchPage(testData.get("refinetypevalue").toString().toUpperCase()), true,
        //                "Verifying the free access type result is displayed on search/browse result page");
        //        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("type=" + testData.get("refinetypevalue").toString().toUpperCase()),
        //                "Verifying the open access type result is displayed on search/browse result page");
    }

    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "1722547 - Verify that the various Share button features.")
    @Story("EPIC-28")

    public void VerifyThatTheVariousShareButtonFeatures() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifyShareButtonIsPresentOnBrowseOrSearchPage(), true, "Verifying the Share Button is present on Browse or search page");

        browseOrSearchPage.clickOnShareButtonOnBrowseOrSearchPage();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifySharingPlatformButtonIsPresentOnBrowseOrSearchPage(testData.get("facebookplatform").toString()), true,
                "Verifying sharing platform " + testData.get("facebookplatform").toString() + " is present when clicks on share button on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifySharingPlatformButtonIsPresentOnBrowseOrSearchPage(testData.get("twitterplatform").toString()), true,
                "Verifying sharing platform " + testData.get("twitterplatform").toString() + " is present when clicks on share button on Browse or search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.verifySharingPlatformButtonIsPresentOnBrowseOrSearchPage(testData.get("linkedinplatform").toString()), true,
                "Verifying sharing platform " + testData.get("linkedinplatform").toString() + " is present when clicks on share button on Browse or search page");
    }

    @Test(groups = {"anesthesiaprogress"}, enabled = true, retryAnalyzer = Retry.class, description = "1722548 - Verify  Refine by Date Section and that search box is accessible in all pages")
    @Story("EPIC-28")

    public void VerifyRefineByDateSectionAndThatSearchBoxIsAccessibleInAllPages() throws Exception {
        testCaseId = retrieveTCID(new Exception().getStackTrace()[0].getMethodName().split("-")[0].trim());
        WebDriverManager.setTestcaseIdTestRail(testCaseId);
        // String application = BaseTest.properties.getProperty("application");
        url = BaseTest.properties.getProperty(application);
        System.out.println("!url=" + url);
        String testDataFileName = application.toUpperCase() + "_" + "TestData.json";
        navigateToUrlLink(url);
        JSONObject testData = getTestDataDetailsWithFileName(testCaseId, testDataFileName);
        masterPage = BasePage.initialize(WebDriverManager.getDriver(), MasterPage.class);
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage = BasePage.initialize(WebDriverManager.getDriver(), BrowseOrSearchPage.class);
        articleCitationPage = BasePage.initialize(WebDriverManager.getDriver(), ArticleCitationPage.class);
        issuePage = BasePage.initialize(WebDriverManager.getDriver(), IssuePage.class);
        int totalResultCount = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        browseOrSearchPage.selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(browseOrSearchPage.getFromDateOption());
        browseOrSearchPage.selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(browseOrSearchPage.getToDateOption());
        browseOrSearchPage.clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage();
        int afterDateFilterUse = browseOrSearchPage.getTotatResultOnBrowseOrSearchPage();
        System.out.println("Browse" + totalResultCount);
        System.out.println("After" + afterDateFilterUse);
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL("fromDate[0]=" + browseOrSearchPage.getFromDateOption() + "&toDate[0]=" + browseOrSearchPage.getToDateOption()),
                "Verifying Refine by Date filter is applied on search or browse page");

        masterPage.clickOnSearchMagnifyingLense();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getBrowsePageLabelText(), "Browse", "verifying browse page is displayed");
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchBoxIsPresentOnHeader(), true, "verifying search box is present on header and accessible from browse page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchMagnifyingLenseIsPresentOnHeader(), true, "verifying magnifying lense is present on header and accessible from browse page");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        BaseTest.assertEquals(WebDriverManager.getDriver(), browseOrSearchPage.getSearchPageLabelText(), "Search Results", "verifying search page is displayed");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchBoxIsPresentOnHeader(), true, "verifying search box is present on header and accessible from search page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchMagnifyingLenseIsPresentOnHeader(), true, "verifying magnifying lense is present on header and accessible from search page");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        browseOrSearchPage.clickOnFirstArticleOnSearchOrBrowsePage();
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL(testData.get("articlepage").toString()), "Verifying the article page is displayed");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        WebDriverManager.getDriver().navigate().back();
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchBoxIsPresentOnHeader(), true, "verifying search box is present on header and accessible from article page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchMagnifyingLenseIsPresentOnHeader(), true, "verifying magnifying lense is present on header and accessible from article page");
        articleCitationPage.clickOnJournalCoverOnArtcilePage();
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL(testData.get("issuepage").toString()), "Verifying the issue page is displayed");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        WebDriverManager.getDriver().navigate().back();
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchBoxIsPresentOnHeader(), true, "verifying search box is present on header and accessible from issue page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchMagnifyingLenseIsPresentOnHeader(), true, "verifying magnifying lense is present on header and accessible from issue page");
        issuePage.clickOnAllIssuesOnIssuePage();
        BaseTest.assertTrue(WebDriverManager.getDriver(), BaseTest.verifyTextInURL(testData.get("journalpage").toString()), "Verifying the Journal page is displayed");
        masterPage.enterTextInSearchBoxOnHomePage(testData.get("searchtext").toString());
        masterPage.clickOnSearchMagnifyingLense();
        WebDriverManager.getDriver().navigate().back();
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchBoxIsPresentOnHeader(), true, "verifying search box is present on header and accessible from Journal page");
        BaseTest.assertEquals(WebDriverManager.getDriver(), masterPage.verifySearchMagnifyingLenseIsPresentOnHeader(), true, "verifying magnifying lense is present on header and accessible from Journal page");

    }
}


