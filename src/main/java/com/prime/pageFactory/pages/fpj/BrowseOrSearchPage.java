package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

public class BrowseOrSearchPage extends BasePage {

    /**
     * 
     * This constructor initializes the BrowsePage class object
     * 
     * 
     * 
     * @param WebDriver
     * 
     * @throws Exception
     * 
     * @author Rakesh.Shevale
     * 
     * @Created Date : 07/07/2023
     * 
     */

    public BrowseOrSearchPage(WebDriver driver) throws Exception {

        super(driver);

        PageFactory.initElements(driver, this);

        waitForDocumentReady();

    }

    /**
     * 
     * This method returns Browse label text on Browse Page
     * 
     * @return String
     * 
     * @throws Exception
     * 
     * @author Rakesh.Shevale
     * 
     * @Created Date : 22/08/2023
     * 
     */

    public String getBrowsePageLabelText() throws Exception {

        String browsetext = getTextFromElement(browseText);

        return browsetext;

    }

    /**
     * 
     * This method returns first article title on Browse/search Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/10/2023
     * 
     */

    public String getFirstArticleTitleOnBrowseOrSearchPage() throws Exception {
        String firstArticle = getTextFromElement(firstArticleOnBrowseOrSearchPage);
        return firstArticle;
    }

    /**
     * 
     * This method returns first DOI Value on Browse/search Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/10/2023
     * 
     */

    public String getFirstDOIValueOnBrowseOrSearchPage() throws Exception {
        WebElement firstDOIvalue = driver.findElement(By.xpath("(//span[contains(text(),'DOI')])[1]//following::span[1]"));
        String firstDOIValue = getTextFromElement(firstDOIvalue);
        return firstDOIValue;
    }

    /**
     * This used to check the more than one journal are present in the journal
     * filter
     * 
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 26/10/2023
     */
    public boolean VerifyMoreThanOneJournalISPresent() {
        boolean flag = false;
        List<WebElement> element = driver.findElements(By.xpath("//button[@data-facet-parametername='journalKey']"));
        if (element.size() >= 2) {
            return true;
        }
        return flag;
    }

    /**
     * 
     * This method returns first Author Value on Browse/search Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/10/2023
     * 
     */

    public String getFirstAuthorNameOnFirstArticleOnBrowseOrSearchPage() throws Exception {
        String firstAuthor = getTextFromElement(firstAuthorFirstArticle);
        return firstAuthor;
    }

    /**
     * 
     * This method returns total search results count
     * 
     * @return int
     * 
     * @throws Exception
     * 
     * @author Rakesh.Shevale
     * 
     * @Created Date : 22/08/2023
     * 
     */
    public int getTotatResultOnBrowseOrSearchPage() throws Exception {
        String totalResult = getTextFromElement(totalResultCount);
        int totalResultcount = Integer.parseInt(totalResult);
        return totalResultcount;
    }

    /**
     * 
     * This method returns true is Pagination link is clickable
     * 
     * @return true
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public Boolean getStatusPaginationLink() throws Exception {
        System.out.println("IN GETSTATUS");
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Page:']//following-sibling::ul[@role='list']//child::li")));
        System.out.println("PAGINATION ARRAY SIZE=" + paginationArray.size());
        WebElement paginationClickLink = paginationArray.get(1);
        return mouseOver(paginationClickLink, "pagination link is active");
    }

    /**
     * 
     * This method returns items per page
     * 
     * @return true
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public int getItemsPerPage() throws Exception {
        String numberOfItemsPerPageString = getDefaultDropDownValue(itemsPerPage);
        System.out.println("NOOFITEMS=" + numberOfItemsPerPageString);
        int numberOfItemsPerPage = Integer.parseInt(numberOfItemsPerPageString);
        return numberOfItemsPerPage;
    }

    /**
     * 
     * This method returns number of pagination links
     * 
     * @return int
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public int getTotalNoOfPaginationLinks() throws Exception {
        String noOfPaginationLinksString = getTextFromElement(noOfPaginationLinks);
        int noOfPaginationLinks = Integer.parseInt(noOfPaginationLinksString);
        return noOfPaginationLinks;
    }

    /**
     * 
     * This method returns last item in the list of pagination links
     * 
     * @return List<String>
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public int getLastItemOfPaginationLinks(String pageSize) throws Exception {
        // WebElement lastWebElementOfPaginationLinks =
        // paginationArray.get(paginationArray.size() - 1);
        // String lastItemOfPaginationLinksString =
        // getTextFromElement(lastWebElementOfPaginationLinks);
        // int lastPageOfPagination = Integer.parseInt(lastItemOfPaginationLinksString);
        // return lastPageOfPagination;
        getTotatResultOnBrowseOrSearchPage(); // 746/10 = 6 == q 74 +1

        int quotient = getTotatResultOnBrowseOrSearchPage() / Integer.parseInt(pageSize);
        int remainder = getTotatResultOnBrowseOrSearchPage() % Integer.parseInt(pageSize);

        if (remainder != 0) {
            quotient += 1;
        }
        return quotient;
    }

    /**
     * 
     * This method returns selecting acesnding sorting option from drop down
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public void SelectSortDateAscFromSortByDropdownOnSearchOrBrowsePage() throws Exception {
        clickOnElement(sortDateAsc, "Changing the drop down option to Sort ASC in search results page");
    }

    /**
     * 
     * This method is used for selecting descending sorting option from drop down
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */

    public void SelectSortDateDescFromSortByDropdownOnSearchOrBrowsePage() throws Exception {
        clickOnElement(sortDateDesc, "Changing the drop down option to Sort DESC in search results page");

    }

    public void selectSortByValueSortByDropdownOnSearchOrBrowsePage(String value) throws Exception {
        selectByValue(sortByDropdown, value, "Selecting the value " + value + " from sort by dropdown on search or browse page");
    }

    /**
     * 
     * This method clicks on refine by user filter
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public void clickOnRefineByAccessAsUser() throws Exception {
        clickOnElement(refineByUserAccess, "Clicking on refine by Access and option as User");

    }

    /**
     * 
     * This method clicks on refine by user filter
     * 
     * @throws Exception
     * 
     * @author Veena.Mathew
     * 
     * @Created Date : 20/09/2023
     * 
     */
    public void clickOnFirstArtcleFromSearchResults() throws Exception {

        clickOnElement(searchResults.get(1), "Clicking on first article on search results page");

    }

    /**
     * 
     * This method clicks on first article on Browse or Search Page
     * 
     * @throws Exception
     * 
     * @author Rakesh.Shevale
     * 
     * @Created Date : 04/10/2023
     * 
     */
    public void clickOnFirstArticleOnSearchOrBrowsePage() throws Exception {
        clickOnElement(firstArticleOnBrowseOrSearchPage, "Click On First Article On Search Or Browse Page");

        // WebDriverWait wait = new WebDriverWait(driver, 20);
        // wait.until(new ExpectedCondition<Boolean>() {
        //
        // public Boolean apply(WebDriver driver) {
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // System.out.println("Full page load : "+js.executeScript("return
        // document.readyState").equals("complete"));
        // return js.executeScript("return document.readyState").equals("complete");
        // }
        // });
        //
    }

    /**
     * This method returns Search label text on Browse Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 27/09/2023
     */
    public String getSearchPageLabelText() throws Exception {
        String browsetext = getTextFromElement(SearchResultText);
        return browsetext;
    }

    /**
     * This method clicks on article type filter value
     * 
     * @param articleType
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 27/09/2023
     */
    public void clickOnArticleTypeFilterValueOnBrowseOrSearchPage(String articleType) throws Exception {
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'By Article Type')]//following::ul[1]//li//button//span[text()='" + articleType + "']"));
        clickOnElement(element, "Clicking on " + articleType + " Artcile type");
    }

    /**
     * This method number of filtered results From left hand side
     * 
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 27/09/2023
     */
    public int getNumberOfFilteredResultsFrontOfArticleFilterValueOnBrowseOrSearchPage(String articleType) throws Exception {
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'By Article Type')]"));
        mouseOver(element, "Hovering on article type");
        String filterResult = getTextFromElement(driver.findElement(By.xpath("//span[contains(text(),'By Article Type')]//following::ul[1]//li//button//span[text()='" + articleType + "']//following-sibling::span")));
        System.out.println("filterResult=" + filterResult);
        String s = "";
        for (int i = 1; i < filterResult.length() - 1; i++) {
            s += filterResult.charAt(i);
        }
        System.out.println("s=" + s);
        int result = Integer.parseInt(s);
        return result;
    }

    /**
     * This method used to select the Refine term value from Refine term drop down
     * on Browse or Search Result Page
     * 
     * @param value
     * @param refineTermOption
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(String testidvalue, String refineTermOption) throws Exception {
        WebElement selectValueFromRefineTermDD = driver.findElement(By.xpath("//select[@data-testid='" + testidvalue + "']//option[text()='" + refineTermOption + "']"));
        clickOnElement(selectValueFromRefineTermDD, "Selecting the " + refineTermOption + " value from Refine Term dropdown on Browse or Search Page");
    }

    /**
     * This method returns all Refine term filter values from Refine term drop down
     * on Browse or Search Result Page
     * 
     * @return List<String>
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     */
    public List<String> getAllRefineTermValuesFromRefineTermDDOnBrowseOrSearchResultPage() throws Exception {
        List<String> filterValues = getTextFindElements(By.xpath("//select[@data-testid='AdvanceSearchFilter-select-0']//child::option"));
        return filterValues;
    }

    /**
     * This method used to entering the refine term value in refine term text box on
     * Search or browse page
     * 
     * @param testIDValue
     * @param enterRefineTermValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     */
    public void enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(String testidvalue, String enterRefineTermValue) throws Exception {
        WebElement refineTermValueTxtBox = driver.findElement(By.xpath("//input[contains(@data-testid,'" + testidvalue + "')]"));
        typeOnElement(refineTermValueTxtBox, enterRefineTermValue, "Entering the Refine Term Value in Refine Term Textbox on Search or browse page");
    }

    /**
     * This method used to verify the refine term textbox in refine term filter is
     * present on Search or browse page
     * 
     * @param testIDValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyRefineTermTextBoxIsPresentOnBrowseOrSearchPage(String testIDValue) throws Exception {
        List<WebElement> refineTermValueTxtBox = driver.findElements(By.xpath("//input[contains(@data-testid,'" + testIDValue + "')]"));
        return isElementPresent(refineTermValueTxtBox);
    }

    /**
     * This method used to verify the refine term textbox in refine term filter is
     * not present on Search or browse page
     * 
     * @param testIDValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyRefineTermTextBoxIsNotPresentOnBrowseOrSearchPage(String testIDValue) throws Exception {
        List<WebElement> refineTermValueTxtBox = driver.findElements(By.xpath("//input[@data-testid='" + testIDValue + "']"));
        return isElementNotPresent(refineTermValueTxtBox);
    }

    /**
     * This method used to verify the refine term options field in refine term
     * filter is present on Search or browse page
     * 
     * @param testidvalue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyRefineTermValueOptionIsPresentOnBrowseOrSearchPage(String testidvalue) throws Exception {
        List<WebElement> selectValueFromRefineTermDD = driver.findElements(By.xpath("//select[@data-testid='" + testidvalue + "']"));
        return isElementPresent(selectValueFromRefineTermDD);
    }

    /**
     * This method used to verify the refine term options field in refine term
     * filter is not present on Search or browse page
     * 
     * @param testidvalue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyRefineTermValueOptionIsNotPresentOnBrowseOrSearchPage(String testidvalue) throws Exception {
        List<WebElement> selectValueFromRefineTermDD = driver.findElements(By.xpath("//select[@data-testid='" + testidvalue + "']"));
        return isElementNotPresent(selectValueFromRefineTermDD);
    }

    /**
     * This method clicks on AddRow Button In Refine Term DD On Browse Or SearchPage
     * 
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnAddRowButtonInRefineTermDDOnBrowseOrSearchPage() throws Exception {
        clickOnElement(AddRowRefineTerm, "Clicking on addrow button in Refine term DD on Browse or search page");
    }

    /**
     * This method used to clicks on cancel button front of Refine term textbox In
     * Refine term DD
     * 
     * @param testIDValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnCancelButtonFrontOfRefineTermTextBoxInRefineTermDDOnBrowseOrSearchPage(String testIDValue) throws Exception {
        WebElement refineTermTxtBoxCancelButton = driver.findElement(By.xpath("//button[contains(@data-testid,'" + testIDValue + "')]"));
        clickOnElement(refineTermTxtBoxCancelButton, "Clicking on Cancel button in front of Refine Term text box in Refine term DD on Browse or search page");
    }

    /**
     * This method used to clicks on Clear button In Refine term DD
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnClearButtonInRefineTermDDOnBrowseOrSearchPage() throws Exception {
        clickOnElement(clearButtonRefineTerm, "Clicking on clear button in Refine term DD on Browse or search page");
    }

    /**
     * This method used to clicks on Search button in Refine term filter on Browse
     * or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnSearchButtonInRefineTermDDOnBrowseOrSearchPage() throws Exception {
        clickOnElement(searchButtonRefineTerm, "Clicking on Search button in Refine term DD on Browse or search page");
    }

    /**
     * This method clicks on Refine by type filter value
     * 
     * @param byType
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnRefineByTypeFilterValueOnBrowseOrSearchPage(String byType) throws Exception {
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Refine by Type')]//following::ul[1]//li//button//span[text()='" + byType + "']"));
        clickOnElement(element, "Clicking on " + byType + " Refine by type");
    }

    /**
     * This method used to select the FromDate value from FromDate drop down In
     * Refine by date filter on Browse or Search Result Page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(String value) throws Exception {
        selectByValue(selectDateFromDD, value, "Selecting the value " + value + " from fromDate dropdown in refine by Date filter on Browse or Search Page");
    }

    /**
     * This method used to select the toDate value from FromDate drop down In Refine
     * by date filter on Browse or Search Result Page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(String value) throws Exception {
        selectByValue(selectDateToDD, value, "Selecting the value " + value + " from toDate dropdown in refine by Date filter on Browse or Search Page");
    }

    /**
     * This method used to clicks on Submit button in Refine by Date filter on
     * Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage() throws Exception {
        clickOnElement(submitBtnRefineByDate, "Clicking on Submit button in Refine By Date filter on Browse or search page");
    }

    /**
     * This method used to clicks on accesstype article in Refine By Access filter
     * on Browse or search page
     * 
     * @param accessType
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public void clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(String accessType) throws Exception {
        WebElement accessTypeEle = driver.findElement(By.xpath("(//span[contains(text(),'Refine by Access')]//following::span[text()='" + accessType + "'])[1]"));
        clickOnElement(accessTypeEle, "Clicking on " + accessTypeEle + " accesstype article in Refine By Access filter on Browse or search page");
    }

    /**
     * This method used to verify the accesstype in Refine By Access filter is
     * present on Browse or search page
     * 
     * @param accessType
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public boolean VerifyfilterOptionInRefineByAccessFilterIsPresentOnBrowseOrSearchPage(String accessType) throws Exception {
        List<WebElement> accessTypeEle = driver.findElements(By.xpath("(//span[contains(text(),'Refine by Access')]//following::span[text()='" + accessType + "'])[1]"));
        return isElementPresent(accessTypeEle);
    }

    /**
     * This method used to clicks on Clear All button on Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public void clickOnClearAllOnBrowseOrSearchPage() throws Exception {
        clickOnElement(clearAllButton, "Clicking on Clear All Button on Browse or search page");
    }

    /**
     * This method used to Verifying the Clear all button is present on Browse or
     * search page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 10/10/2023
     */
    public boolean verifyClearAllButtonIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> clearAll = driver.findElements(By.xpath("//button[text()='Clear All']"));
        return isElementPresent(clearAll);
    }

    /**
     * This method used to Verify refine term filter search keyword present on
     * search/browse page
     * 
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 18/10/2023
     */
    public boolean verifyRefineTermFilterSearchKewordIsPresentOnSearchOrBrowsePage(String valueName) throws Exception {
        List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[contains(text(),'" + valueName + "')]//parent::span//following-sibling::button"));
        System.out.println("SearchSlugSign" + SearchSlugSign.get(0));
        System.out.println("isElementPresent(SearchSlugSign)" + isElementPresent(SearchSlugSign));
        return isElementPresent(SearchSlugSign);
    }

    /**
     * This method used to Verify refine term filter search keyword present on
     * search/browse page
     * 
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 18/10/2023
     */
    public boolean verifyRefineTermFilterSearchKewordIsNotPresentOnSearchOrBrowsePage(String valueName) throws Exception {
        List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[text()='" + valueName + "']//parent::span//following-sibling::button"));
        return isElementNotPresent(SearchSlugSign);
    }

    /**
     * This method used to clicks on Searchslug (-) on search page
     * 
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public void clickOnSearchKeywordSearchSlugOrFilterValueSearchSlugOnBrowseOrSearchPage(String valueName) throws Exception {
        WebElement SearchSlugSign = driver.findElement(By.xpath("//strong[contains(text(),'" + valueName + "')]//parent::span//following-sibling::button"));
        clickOnElement(SearchSlugSign, "Clicking on - sign which is availbale in backside of " + valueName + " on Browse or search page");
    }

    /**
     * This method used to Verifying Search Slug(-) Sign is Present On browse or
     * SearchPage
     *
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 11/10/2023
     */
    public boolean verifySearchSlugSignIsPresentOnBrowseOrSearchPage(String valueName) throws Exception {
        // List<WebElement> SearchSlugSign =
        // driver.findElements(By.xpath("//strong[text()='" + valueName +
        // "']//parent::span//following-sibling::button"));
        List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[text()='Term']//following::strong[text()='" + valueName + "']"));
        return isElementPresent(SearchSlugSign);
    }

    /**
     * This method used to Verifying Search Slug(-) Sign is Present On browse or
     * SearchPage
     *
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 18/10/2023
     */
    public boolean verifyFilterValueIsPresentOnBrowseOrSearchPage(String filterName, String valueName) throws Exception {
        List<WebElement> filterValue = driver.findElements(By.xpath("//strong[contains(text(),'" + filterName + "')]//following::strong[contains(text(),'" + valueName + "')]"));
        return isElementPresent(filterValue);
    }

    /**
     * This method used to clicks on Save button on Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public void clickOnSaveButtonOnBrowseOrSearchPage() throws Exception {
        clickOnElement(saveButton, "Clicking on Save button Button on Browse or search page");
    }

    /**
     * This method used to Verifying the Share button is present on Browse or search
     * page
     *
     * @throws Exception
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 11/10/2023
     */
    public boolean verifyShareButtonIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> share = driver.findElements(By.xpath("//span[@title='Share']//parent::button[@role='button']"));
        return isElementPresent(share);
    }

    /**
     * This method used to clicks on Share button on Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/10/2023
     */
    public void clickOnShareButtonOnBrowseOrSearchPage() throws Exception {
        clickOnElement(shareButton, "Clicking on Share Button on Browse or search page");
    }

    /**
     * This method used to Verifying the save button is present on Browse or search
     * page
     *
     * @throws Exception
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 11/10/2023
     */
    public boolean verifySaveButtonIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> save = driver.findElements(By.xpath("//button[@title='Save']"));
        return isElementPresent(save);
    }

    /**
     * This method used to Verifying sharing platform is present when clicks on
     * share button on Browse or search page
     *
     * @param platformName
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 11/10/2023
     */
    public boolean verifySharingPlatformButtonIsPresentOnBrowseOrSearchPage(String platformName) throws Exception {
        List<WebElement> platform = driver.findElements(By.xpath("//button[contains(text(),'Share on " + platformName + "')]"));
        return isElementPresent(platform);
    }

    /**
     * This method used to Verifying Item per page dropdown is present on Browse or
     * search page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 11/10/2023
     */
    public boolean verifyItemPerPageDDIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> itemperpage = driver.findElements(By.xpath("//select[@id='itemsPerPage']"));
        return isElementPresent(itemperpage);
    }

    /**
     * This method returns the item per page dropdown default value.
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @return String
     * @Created Date : 11/10/2023
     */
    public String getDefaultItemPerPageDDValueOnBrowseOrSearchPage() throws Exception {
        String defaultValue = getDefaultDropDownValue(itemsPerPage);
        return defaultValue;
    }

    /**
     * This method used to select the item per page from item per page dropdown on
     * browse or search result page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 11/10/2023
     */
    public void selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(String value) throws Exception {
        selectByValue(itemsPerPage, value, "Selecting filter value from item per page dropdown on browse or search page");
    }

    /**
     * This method returns the total item count on page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @return String
     * @Created Date : 11/10/2023
     */
    public String getTotalItemCountOnPage() {
        List<String> list = getTextFindElements(By.xpath("//a[@target='_self']"));
        String totalCount = String.valueOf(list.size());
        return totalCount;
    }

    /**
     * This method is used to check content title name present on browse or search
     * page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentTitleNameIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> TitleName = driver.findElements(By.xpath("(//div[@class='title'])[1]"));
        return isElementPresent(TitleName);
    }

    /**
     * This method is used to check content Access Icon is present on browse or
     * search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentAccessIconIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> accessIcon = driver.findElements(By.xpath("(//span[contains(@title,'access')])[1]"));
        return isElementPresent(accessIcon);
    }

    /**
     * This method is used to check content Author is present on browse or search
     * page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentAuthorIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> author = driver.findElements(By.xpath("(//div[contains(@data-testid,'contributors')])[1]"));
        return isElementPresent(author);
    }

    /**
     * This method is used to check content Volume Issue is present on browse or
     * search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentVolumeIssueIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> volumeIssue = driver.findElements(By.xpath("(//span[contains(text(),'Volume/Issue')])[1]"));
        return isElementPresent(volumeIssue);
    }

    /**
     * This method is used to check content DOI is present on browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentDOIIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> DOI = driver.findElements(By.xpath("(//span[contains(text(),'DOI')])[1]"));
        return isElementPresent(DOI);
    }

    /**
     * This method is used to check content Online Publication Date is present on
     * browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentOnlinePublicationDateIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> onlinepublication = driver.findElements(By.xpath("(//span[contains(text(),'Online Publication Date')])[1]"));
        return isElementPresent(onlinepublication);
    }

    /**
     * This method is used to check content abstract is present on browse or search
     * page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyContentAbstractIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> abstractele = driver.findElements(By.xpath("((//div[@class='title'])[1]//following::span[text()='Abstract'])[1]"));
        return isElementPresent(abstractele);
    }

    /**
     * 
     * This method returns Refine terms filter text on Browse Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     * 
     */

    public String getRefineTermFilterTextOnSearchOrBrowsePage() throws Exception {
        String refineTermsText = getTextFromElement(refineTerms);
        return refineTermsText;
    }

    /**
     * 
     * This method returns Refine by type filter text on Browse Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     * 
     */

    public String getRefineByTypeFilterTextonSearchOrBrowsePage() throws Exception {
        String refineByTypeText = getTextFromElement(refineByType);
        return refineByTypeText;
    }

    /**
     * 
     * This method returns Refine by article type filter text on Browse Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     * 
     */

    public String getRefineByArticleTypeFilterTextonSearchOrBrowsePage() throws Exception {
        String refineByArticleTypeText = getTextFromElement(refineByArticleType);
        return refineByArticleTypeText;
    }

    /**
     * 
     * This method returns Refine by Date filter text on Browse Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     * 
     */

    public String getRefineByDateFilterTextonSearchOrBrowsePage() throws Exception {
        String refineByDateText = getTextFromElement(refineByDate);
        return refineByDateText;
    }

    /**
     * This method is used to from Date dropdown in refine date filter is present on
     * browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyFromDateDropdownIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> fromDateDD = driver.findElements(By.xpath("//select[@name='fromDate']"));
        return isElementPresent(fromDateDD);
    }

    /**
     * This method is used to to Date dropdown in refine date filter is present on
     * browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 16/10/2023
     */
    public boolean verifyToDateDropdownIsPresentOnBrosweOrSearchPage() throws Exception {
        List<WebElement> toDateDD = driver.findElements(By.xpath("//select[@name='toDate']"));
        return isElementPresent(toDateDD);
    }

    /**
     * 
     * This method returns Refine by Access filter text on Browse Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     * 
     */

    public String getRefineByAccessFilterTextonSearchOrBrowsePage() throws Exception {
        String refineByAccessText = getTextFromElement(refineByAccess);
        return refineByAccessText;
    }

    /**
     * This method used to Verify the Clear button from Refine terms is present on
     * Browse or search page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyClearButtonFromRefineTermIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> clearbutton = driver.findElements(By.xpath("//button[@title='Clear']"));
        return isElementPresent(clearbutton);
    }

    /**
     * This method used to clicks on First Restricted Content on Browse or search
     * page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 09/11/2023
     */
    public void clickOnFirstRestrictedContentOnBrowseOrSearchPage() throws Exception {
        clickOnElement(firstRestrictedContent, "Clicking on First Restricted Content on Browse or search page");
    }

    /**
     * This method used to clicks on the Abstract button below the search result on
     * search result page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/11/2023
     */
    public void clickOnAbstractTabOfFirstActileOnBrowsePageOrSearchPage() throws Exception {
        clickOnElement(abstractButton, "Clicking on Abstract button below the search result on search result page");
    }

    /**
     * This method return the single fromDate option
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/01/2024
     */
    public String getFromDateOption() throws Exception {
        List<WebElement> fromdateElement = driver.findElements(By.xpath("//select[@name='fromDate']//child::option"));
        List<String> fromdateText = getMultipleWebElementText(fromdateElement);
        String fromDate = fromdateText.get(1);
        return fromDate;
    }

    /**
     * This method return the single toDate option
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/01/2024
     */
    public String getToDateOption() throws Exception {
        String toDate = getFromDateOption();
        String toDateUpdate = Integer.toString(Integer.parseInt(toDate) + 1);
        return toDateUpdate;
    }

    /**
     * This method used to copied message paste into textbox
     * 
     * @author Rakesh.Shevale
     * @created Date : 30/05/24
     */
    public void copiedMessagePasteIntoTextBox() {
        copiedTextPasteIntoTextBox(defaultRefinetermTextbox);
    }

    /**
     * This method return the attribute value from refine term textbox
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @created Date : 30/05/24
     */
    public String getRefineTermTextBoxValue() throws Exception {
        return getAttributeFromElement(defaultRefinetermTextbox, "value");
    }

    /**
     * This method return the background color of element
     * 
     * @return String
     * @author Rakesh.Shevale
     * @created Date : 12/06/24
     */
    public String getBackgroundColor(String searchKeyword) {
        WebElement element = driver.findElement(By.xpath("(//span[@class='hi' ][contains(text(),'" + searchKeyword + "')])[1]"));
        String backgroundColor = element.getCssValue("background-color");
        return backgroundColor;
    }

    /**
     * This method return the list of last two word with maximum character
     * 
     * @return List<String>
     * @throws Exception
     * @author Rakesh.Shevale
     * @created Date : 13/06/24
     */
    public List<String> getAbstract() throws Exception {
        WebElement wholeAbstract = driver.findElement(By.xpath("(//div[@data-identifier='abstract']//p)[1]"));
        String abstractPara = getTextFromElement(wholeAbstract);
        System.out.println("Whole Abstract : " + abstractPara);
        String[] words = (abstractPara.split(" "));

        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        List<String> list = new ArrayList<>();

        for (String word : words) {
            if (pattern.matcher(word).matches()) {
                list.add(word);
            }
        }

        List<String> list1 = new ArrayList<>();

        int maxLength1 = 0;
        int maxIndex1 = -1;
        int maxLength2 = 0;
        int maxIndex2 = -1;

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            int length = str.length();

            if (length > maxLength1) {
                maxLength2 = maxLength1;
                maxIndex2 = maxIndex1;
                maxLength1 = length;
                maxIndex1 = i;
            } else if (length > maxLength2) {
                // Update the second max if it's larger than the current second max
                maxLength2 = length;
                maxIndex2 = i;
            }
        }
        list1.add(list.get(maxIndex1));
        System.out.println("maxIndex1 : " + maxIndex1);
        list1.add(list.get(maxIndex2));
        System.out.println("maxIndex2 : " + maxIndex2);
        return list1;
    }

    public void ClickOnShowMoreLinkIfAvailableBelowTheContent() throws Exception {
        List<WebElement> showMoreLinkList = driver.findElements(By.xpath("(//button[text()=' ... Show More'])[1]"));
        if (showMoreLinkList.size() > 0) {
            WebElement showMoreLink = driver.findElement(By.xpath("(//button[text()=' ... Show More'])[1]"));
            clickOnElement(showMoreLink, "Clicking on Show more link below the content on search or browse page");
        }
    }

    /**
     * This method is used to check og:url meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogURLMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        Thread.sleep(4000);
        List<WebElement> OgURL = driver.findElements(By.xpath("//meta[@property='og:url']"));
        return isElementPresent(OgURL);
    }

    /**
     * This method returns the og:url meta tag content attribute value on Search
     * page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgURLMetaTagPropertyValue() throws Exception {
        String URL = getMetaTagAttribute(ogURLMetaTag, "content");
        return URL;
    }

    /**
     * This method is used to check og:site_name meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogSiteNameMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> OgSiteName = driver.findElements(By.xpath("//meta[@property='og:site_name']"));
        return isElementPresent(OgSiteName);
    }

    /**
     * This method returns the og:site_name meta tag content attribute value on
     * Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgSiteNameMetaTagPropertyValue() throws Exception {
        String siteName = getMetaTagAttribute(ogSiteNameMetaTag, "content");
        return siteName;
    }

    /**
     * This method is used to check og:type meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogTypeMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> ogType = driver.findElements(By.xpath("//meta[@property='og:type']"));
        return isElementPresent(ogType);
    }

    /**
     * This method returns the og:type meta tag content attribute value on Search
     * page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgTypeMetaTagPropertyValue() throws Exception {
        String ogType = getMetaTagAttribute(ogTypeMetaTag, "content");
        return ogType;
    }

    /**
     * This method is used to check og:locale meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogLocaleMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> ogLocale = driver.findElements(By.xpath("//meta[@property='og:locale']"));
        return isElementPresent(ogLocale);
    }

    /**
     * This method returns the og:locale meta tag content attribute value on Search
     * page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgLocaleMetaTagPropertyValue() throws Exception {
        String ogLocale = getMetaTagAttribute(ogLocaleMetaTag, "content");
        return ogLocale;
    }

    /**
     * This method is used to check og:image meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogImageMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> ogImage = driver.findElements(By.xpath("//meta[@property='og:image']"));
        return isElementPresent(ogImage);
    }

    /**
     * This method returns the og:image meta tag content attribute value on Search
     * page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgImageMetaTagPropertyValue() throws Exception {
        String ogImage = getMetaTagAttribute(ogImageMetaTag, "content");
        return ogImage;
    }

    /**
     * This method is used to check twitter:card meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyTwitterCardMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> twitterCard = driver.findElements(By.xpath("//meta[@property='twitter:card']"));
        return isElementPresent(twitterCard);
    }

    /**
     * This method returns the twitter:card meta tag content attribute value on
     * Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getTwitterCardMetaTagPropertyValue() throws Exception {
        String twitterCard = getMetaTagAttribute(twitterCardMetaTag, "content");
        return twitterCard;
    }

    /**
     * This method is used to check twitter:title meta tag is present on
     * BrowseOrSearc page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyTwitterTitleMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> twitterTitle = driver.findElements(By.xpath("//meta[@property='twitter:title']"));
        return isElementPresent(twitterTitle);
    }

    /**
     * This method returns the twitter:title meta tag content attribute value on
     * Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getTwitterTitleMetaTagPropertyValue() throws Exception {
        String twitterTitle = getMetaTagAttribute(twitterTitleMetaTag, "content");
        return twitterTitle;
    }

    /**
     * This method is used to check og:description meta tag is present on Search
     * page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyOgDescriptionMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> ogDescription = driver.findElements(By.xpath("(//meta[@property='og:description'])[1]"));
        return isElementPresent(ogDescription);
    }

    /**
     * This method returns the og:description meta tag content attribute value on
     * Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgDescriptionMetaTagPropertyValue() throws Exception {
        String ogDescription = getMetaTagAttribute(ogDescriptionMetaTag, "content");
        return ogDescription;
    }

    /**
     * This method is used to check og:description meta tag is present on Search
     * page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyDescriptionMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> Description = driver.findElements(By.xpath("(//meta[@name='description'])[1]"));
        return isElementPresent(Description);
    }

    /**
     * This method returns the og:description meta tag content attribute value on
     * Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getDescriptionMetaTagPropertyValue() throws Exception {
        String ogDescription = getMetaTagAttribute(descriptionMetaTag, "content");
        return ogDescription;
    }

    /**
     * This method is used to check twitter:description meta tag is present on
     * Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyTwitterDescriptionMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> twitterDescription = driver.findElements(By.xpath("//meta[@property='twitter:description']"));
        return isElementPresent(twitterDescription);
    }

    /**
     * This method returns the twitter:description meta tag content attribute value
     * on Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getTwitterDescriptionMetaTagPropertyValue() throws Exception {
        String twitterDescription = getMetaTagAttribute(twitterDescriptionMetaTag, "content");
        return twitterDescription;
    }

    /**
     * This method is used to check og:title meta tag is present on Search page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyOgTitleMetaTagisPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> ogTitle = driver.findElements(By.xpath("//meta[@property='og:title']"));
        return isElementPresent(ogTitle);
    }

    /**
     * This method returns the og:title meta tag content attribute value on search
     * page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgTitleMetaTagPropertyValue() throws Exception {
        String ogTitle = getMetaTagAttribute(ogTitleMetaTag, "content");
        return ogTitle;
    }

    public void mouseHoverOnFirstcontentonSearchPage() {
        try {
            mouseOver(firstArticleOnBrowseOrSearchPage, "Hovering mouse on first article");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * This method is used get By filter text on Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 22/08/2024
     */
    public String getJournalFiterText() throws Exception {
        String journalFilter = getTextFromElement(byJournalFilter);
        return journalFilter;
    }

    /**
     * This method clicks on first journal filter value from By Journal filter on
     * Browse or Search page
     * 
     * @param articleType
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 22/08/2024
     */
    public void clickOnFirstJournalFilterValueFromByJournalFilterOnBrowseOrSearchPage() throws Exception {
        clickOnElement(firstJournalFilterValue, "Clicking on first journal filter value from By Journal filter On Browse or Search page");
    }

    /**
     * This method used to get the number of results front of journal filter value
     * on Browse or Search page
     * 
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 22/08/2024
     */
    public int getNumberOfFilteredResultsFrontOfJournalFilterValueOnBrowseOrSearchPage() throws Exception {
        mouseOver(firstJournalFilterValueCount, "Hovering mouse on Journal filter result");
        String filterResult = getTextFromElement(firstJournalFilterValueCount);
        String str = "";
        for (int i = 1; i < filterResult.length() - 1; i++) {
            str += filterResult.charAt(i);
        }
        int result = Integer.parseInt(str);
        return result; // 12
    }

    /**
     * This method is used get By filter text on Search page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 22/08/2024
     */
    public String getJournalFiterFirstvalueText() throws Exception {
        String journalFilter = getTextFromElement(firstJournalFilterValue);
        return journalFilter;
    }

    /**
     * This method is return the First Journal data-facet-value attribute Value
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 23/08/2024
     */
    public String getFirstJournalAttributeValue() throws Exception {
        WebElement element = driver.findElement(By.xpath("//span[text()='By Journal']//following::span[@data-testid='treeFilter']//following::button[1]"));
        String attributeValue = getAttributeFromElement(element, "data-facet-value");
        return attributeValue;
    }

    /**
     * This method used to Verify refine term filter search keyword present on
     * search/browse page
     * 
     * @param valueName
     * @throws Exception
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 23/08/2024
     */
    public boolean verifyByJournalFilterSearchSlugValueIsPresentOnSearchOrBrowsePage(String filterName, String valueName) throws Exception {
        List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[contains(text(),'" + filterName + "')]//following::strong[contains(text(),'" + valueName + "')]"));
        return isElementPresent(SearchSlugSign);
    }

    /**
     * This method used to Verify search slug label is region
     * 
     * @param valueName
     * @throws Exception
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 23/08/2024
     */
    public boolean verifySearchSlugLabelIsRegion() throws Exception {
        List<WebElement> SearchSlugSign =
                driver.findElements(By.xpath("//strong[contains(text(),'search.filter.by-journal-key.label')]//following::strong[contains(text(),'search.filter.by-journal-key.brainmed.label')]"));
        return isElementPresent(SearchSlugSign);
    }

    public boolean verifyCountIsPresntInFrontOfJournalTitleInJournalFilter() throws Exception {
        boolean flag = false;
        WebElement countNum = driver.findElement(By.xpath("//span[text()='Brain Medicine']//following::span[1]"));
        String countNumber = getTextFromElement(countNum);
        if (countNumber.startsWith("(") && countNumber.endsWith(")")) {
            flag = true;
        }
        return flag;
    }

    /**
     * This method used to Verifying Add Value[+] Sign is Present On browse or
     * SearchPage under Refine Terms.
     *
     * @param valueName
     * @throws Exception
     * @author Veena.Mathew
     * @return
     * @Created Date : 27/08/2024
     */
    public boolean verifyAddValueIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> AddValueField = driver.findElements(By.xpath("//button[contains(@data-testid,'AdvanceSearchFilter-add-new-value-0')]"));
        return isElementPresent(AddValueField);
    }

    /**
     * This method clicks on AddValue Button In Refine Term DD On Browse Or
     * SearchPage
     * 
     * @return void
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 27/08/2024
     */
    public void clickOnAddValueButtonInRefineTermDDOnBrowseOrSearchPage() throws Exception {
        clickOnElement(AddValueRefineTerm, "Clicking on Add Value button in Refine term DD on Browse or search page");
    }

    /**
     * This method used to Verifying Add Value[+] Sign is Present On browse or
     * SearchPage under Refine Terms.
     *
     * @param valueName
     * @throws Exception
     * @author Veena.Mathew
     * @return
     * @Created Date : 27/08/2024
     */
    public boolean verifySecondFilterboxIsPresentAfterClickingOnAddValue() throws Exception {
        List<WebElement> SecondFilterBox = driver.findElements(By.xpath("//input[contains(@data-testid,'AdvanceSearchFilter-input-0-1')]"));
        return isElementPresent(SecondFilterBox);
    }

    /**
     * This method used to Verifying Add Value[+] Sign is Present On browse or
     * SearchPage under Refine Terms.
     *
     * @param valueName
     * @throws Exception
     * @author Veena.Mathew
     * @return
     * @Created Date : 27/08/2024
     */
    public boolean verifyORtextIsPresentWithSecondFilterBox() throws Exception {
        List<WebElement> ORText = driver.findElements(By.xpath("//input[contains(@data-testid,'AdvanceSearchFilter-input-0-1')]/parent::div/preceding-sibling::div[1]/p"));
        if (isElementPresent(ORText))
            if (ORText.get(0).getText().equalsIgnoreCase("OR"))
                return true;
            else
                return false;
        else
            return false;
    }

    /**
     * This method used to clicks on Cross(Close) button on Advanced Search Filter
     * on Browse or search page
     * 
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 27/08/2024
     */
    public void clickOnCrossButtonOnAdvancedSearchFilterBrowseOrSearchPage(int itemRow) throws Exception {
        itemRow--;
        clickOnElement(driver.findElement(By.xpath("//button[contains(@data-testid,'AdvanceSearchFilter-remove-cta-0-" + itemRow + "')]")),
                "Clicking on Cross button Button on Advanced Search Filter Under Refine Terms Browse or search page");
    }

    /**
     * This method used to Verifying Add Row[+] Sign is Present On browse or
     * SearchPage under Refine By Date.
     *
     * @param valueName
     * @throws Exception
     * @author Veena.Mathew
     * @return
     * @Created Date : 27/08/2024
     */
    public boolean verifySecondFilterboxForDateAfterClickingOnAddRow() throws Exception {
        List<WebElement> SecondFilterBox = driver.findElements(By.xpath("//button[contains(@data-testid,'dateSearchFilter-add-new-row')]"));
        return isElementPresent(SecondFilterBox);
    }

    /**
     * this method is used to check the add row button is present on search page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public boolean verifyAddRowbuttonIsAvailableForDateFilter() throws Exception {
        List<WebElement> addRow = driver.findElements(By.xpath("//span[@data-testid='dataFilter']//following::button[@title='[+] Add row']"));
        return isElementPresent(addRow);
    }

    /**
     * This method used to clicks on Add row button in date filter
     * on Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public void clickOnAddRowButtonForDateFilter() throws Exception {
        clickOnElement(addRowForDateFilter, "Clicking on add row button for the date filter");
    }

    /**
     * this method is used to check the multi term refine date filter is present on search page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public boolean verifyMultiDateFilterTermAvailableAfterclickOnAddRowButton(String rowNum) throws Exception {
        List<WebElement> secondRow = driver.findElements(By.xpath("  //span[@data-testid='dataFilter']//following::button[@data-testid='dateSearchFilter-remove-cta-" + rowNum + "']"));
        return isElementPresent(secondRow);
    }

    /**
     * this method is used to check the multi term refine date filter is not present on search page with date filter value
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public boolean verifyMultiDateFilterTermNotAvailableAfterclickOnCrossButton(String rowNum) throws Exception {
        List<WebElement> secondRow = driver.findElements(By.xpath("  //span[@data-testid='dataFilter']//following::button[@data-testid='dateSearchFilter-remove-cta-" + rowNum + "']"));
        return isElementNotPresent(secondRow);
    }

    /**
     * This method used to clicks on cross button in date filter
     * on Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public void clickOnCrossButtonForDateMultiTermFilter(String intRow) throws Exception {
        clickOnElement(driver.findElement(By.xpath("//span[@data-testid='dataFilter']//following::button[@data-testid='dateSearchFilter-remove-cta-" + intRow + "']")),
                "Clicking on Cross button Button on Advanced Search Filter Under By Date Terms filter on Browse or search page");
    }

    /**
     * This method used to select the FromDate value from FromDate drop down In
     * Refine by date filter on Browse or Search Result Page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public void selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(String rowNum, String value) throws Exception {
        WebElement selectDateFrom = driver.findElement(By.xpath("(//span[contains(text(),'Refine by Date')]//following::select[@data-testid='dateSearchFilter-select-fromDate-" + rowNum + "'])"));
        selectByValue(selectDateFrom, value, "Selecting the value " + value + " from fromDate dropdown in refine by Date filter on Browse or Search Page");
    }

    /**
     * This method used to select the toDate value from FromDate drop down In Refine
     * by date filter on Browse or Search Result Page
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public void selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(String rowNum, String value) throws Exception {
        WebElement selectDateTo = driver.findElement(By.xpath("(//span[contains(text(),'Refine by Date')]//following::select[@data-testid='dateSearchFilter-select-toDate-" + rowNum + "'])"));
        selectByValue(selectDateTo, value, "Selecting the value " + value + " from toDate dropdown in refine by Date filter on Browse or Search Page");
    }

    /**
     * this method is used to check the search slug is present on search page with date filter value
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public boolean VerifyRefineByDateFilterSearchSlugIsPresentOSearchPage(String fromDateValue, String toDateValue) throws Exception {
        List<WebElement> dateElement = driver.findElements(By.xpath("//strong[text()='Refine by Date']//following::strong[text()='" + fromDateValue + " - " + toDateValue + "']"));
        return isElementPresent(dateElement);
    }

    /**
     * this method is used to check the search slug is not present on search page with date filter value is removed
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/09/2024
     */
    public boolean VerifyRefineByDateFilterSearchSlugIsNotPresentOSearchPage(String fromDateValue, String toDateValue) throws Exception {
        List<WebElement> dateElement = driver.findElements(By.xpath("//strong[text()='Refine by Date']//following::strong[text()='" + fromDateValue + " - " + toDateValue + "']"));
        return isElementNotPresent(dateElement);
    }

    /**
     * This method used to verify the first article is present on search or browse result page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 26/09/2024
     */
    public boolean verifyFirstArticleIsPresentOnBrowseOrSearchPage() throws Exception {
        List<WebElement> firstArticle = driver.findElements(By.xpath("(//div[@class='title'])[1]"));
        return isElementPresent(firstArticle);
    }


	/**
	 * This method used to returns the list of date from refine by date filter
	 * 
	 * @return List <String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 04/11/2024
	 */
	public List<String> getTotalDateListFromRefineByDateFilter() throws Exception {
		List<WebElement> dateList = driver.findElements(By.xpath("//select[@name='fromDate']//option"));
		return getMultipleWebElementText(dateList);
	}

	/*
	 * This method used to get the total result till the pagInation link on browse
	 * or search results page.
	 * 
	 * @return integer
	 * 
	 * @throws Exception
	 * 
	 * @author Rakesh.Shevale
	 * 
	 * @Created Date : 05/11/2024
	 */
	public int getTotalResultFromPageInation() throws Exception {
		int result = 0;
		result = result + Integer.parseInt(getTotalItemCountOnPage());
		List<WebElement> pagInation = driver.findElements(By.xpath("//nav[@aria-label='Pagination']//li"));
		int pagInationPage = getMultipleWebElementText(pagInation).size();
		for (int i = 2; i <= pagInationPage; i++) {
			System.out.println("In for loops " + i);
			clickOnElement(driver.findElement(By.xpath("//nav[@aria-label='Pagination']//li[text()='" + i + "']")));
			Thread.sleep(7000);
			result = result + Integer.parseInt(getTotalItemCountOnPage());
		}
		return result;
	}

	/**
	 * This method used to verify the pagInation is not present on Search or browse
	 * page throws Exception
	 * 
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 05/11/2024
	 */
	public boolean verifyPagInationIsNotPresentOnBrowseOrSearchPage() throws Exception {
		List<WebElement> pagInation = driver.findElements(By.xpath("//span[text()='Page:']"));
		return isElementNotPresent(pagInation);
	}

	/**
	 * This method used to verify the relevance is selected in sort by dd
	 * 
	 * throws Exception
	 * 
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 05/11/2024
	 */
	public boolean verifyRelevanceIsSelectedInTheSortByDDOnBrowseOrSearchPage() throws Exception {
		List<WebElement> relevance = driver.findElements(By.xpath("//option[text()='Relevance' and @selected]"));
		return isElementPresent(relevance);
	}

	/**
	 * This method used to return the list of publication date only for contents
	 * 
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 06/11/2024
	 */
	public List<String> getPublicationDateForAllContentOnsarchResultsPage() throws Exception {
		Thread.sleep(10000);
		List<WebElement> publicationDate = driver.findElements(By.xpath(
				"//span[contains(text(),'Online Publication Date')]//parent::span//following-sibling::span//child::span"));
		return getMultipleWebElementText(publicationDate);
	}

	/**
	 * This method used to return the list of publication date in year only for
	 * contents
	 * 
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 06/11/2024
	 */
	public List<String> getPublicationDateYearForAllContentOnsarchResultsPage() throws Exception {
		List<String> onlyYear = new ArrayList<>();
		List<String> publication = getPublicationDateForAllContentOnsarchResultsPage();
		for (int i = 0; i < publication.size(); i++) {
			onlyYear.add(publication.get(i).substring(publication.get(i).length() - 4, publication.get(i).length()));
		}
		return onlyYear;
	}

	/**
	 * This method returns the list of total content title
	 *
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return List <String>
	 * @Created Date : 06/11/2024
	 */
	public List<String> getTitleListOnSearchResultPage() throws Exception {
		Thread.sleep(7000);
		List<WebElement> titleWeb = driver.findElements(By.xpath("//div[@class='title']"));
		List<String> title = getMultipleWebElementText(titleWeb);
		return title;
	}

	/**
	 * 
	 * This method is used for selecting relevance option from sort by drop down
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/11/2024
	 * 
	 */

	public void SelectRelevanceFromSortByDropdownOnSearchOrBrowsePage() throws Exception {
		clickOnElement(relevanceSortBY, "Changing the drop down option to relevance in search results page");
	}

	/**
	 * This method used click on open access article if not found click on next page again click on open article 
	 * 
	 * @param openAccesstitle
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/11/2024
	 */
	public void ScrollAndClickOnOpenAccessArticle(String openAccesstitle) throws Exception {
		int lastPageInation=getLastItemOfPaginationLinks(getSeletedPerPageItemNumberFromDD());
		List<WebElement> element = driver.findElements(By.xpath("//div[text()='" + openAccesstitle + "']"));
		if (element.size() > 0) {
			clickOnElement(driver.findElement(By.xpath("//div[text()='" + openAccesstitle + "']")));
		} else {
			System.out.println("In ELSE LOOPS");
//			List<WebElement> pagInation = driver.findElements(By.xpath("//nav[@aria-label='Pagination']//li"));
//			int pagInationPage = getMultipleWebElementText(pagInation).size();
			if (lastPageInation >= 2) {
				for (int i = 2; i <= lastPageInation; i++) {
					clickOnElement(
							driver.findElement(By.xpath("//nav[@aria-label='Pagination']//li[text()='" + i + "']")));
					List<WebElement> title = driver.findElements(By.xpath("//div[text()='" + openAccesstitle + "']"));
					if (title.size() > 0) {
						clickOnElement(driver.findElement(By.xpath("//div[text()='" + openAccesstitle + "']")));
						break;
					}
				}
			}
		}
	}
	
	/**
	 * This method used to get selected page per item in String format from DD
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/11/2024
	 */
	public String getSeletedPerPageItemNumberFromDD() throws Exception {
		return getTextFromElement(selectedItemPerPage);
	}

	public void ClickOnArticleFromeRefineByType() throws Exception {
		clickOnElement(articleValuefromRefineByType, "Clicking on the article type from the Refine by type filter on browse page.");
	}



    @FindBy(xpath = "//h1[text()='Browse']")
    private WebElement browseText;

    @FindBy(xpath = "//span[text()='of ']//following::span[1]")
    private WebElement totalResultCount;

    @FindBy(xpath = "//select[@id='itemsPerPage']")
    private WebElement itemsPerPage;

    @FindBy(xpath = "")
    private WebElement noOfPaginationLinks;

    @FindBy(xpath = "//span[text()='Page:']//following::ul[@role='list']//child::li")
    private List<WebElement> paginationArray;

    @FindBy(xpath = "//select[@id='sortOptions']")
    private WebElement sortByDropdown;

    @FindBy(xpath = "//select[@id='sortOptions']//option[text()='Sort Date ASC']")
    private WebElement sortDateAsc;

    @FindBy(xpath = "//select[@id='sortOptions']//option[text()='Sort Date DESC']")
    private WebElement sortDateDesc;

    @FindBy(xpath = "//button[@data-facet-value='USER']/div")
    private WebElement refineByUserAccess;

    @FindBy(xpath = "//div[@class='searchResultsList']//a")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//h1[text()='Search Results']")
    private WebElement SearchResultText;

    @FindBy(xpath = "(//h6[@data-identifier='<title>'])[1]")
    private WebElement firstArticleOnBrowseOrSearchPage;
    @FindBy(xpath = "//button[@title='[+] Add row']")
    private WebElement AddRowRefineTerm;
    @FindBy(xpath = "//button[@title='Clear']")
    private WebElement clearButtonRefineTerm;
    @FindBy(xpath = "//button[@title='Clear']//following-sibling::button")
    private WebElement searchButtonRefineTerm;
    @FindBy(xpath = "//span[contains(text(),'Refine by Date')]//following::select[@name='fromDate']")
    private WebElement selectDateFromDD;
    @FindBy(xpath = "//span[contains(text(),'Refine by Date')]//following::select[@name='toDate']")
    private WebElement selectDateToDD;
    @FindBy(xpath = "//span[contains(text(),'Refine by Date')]//following::button[contains(@title,'Submit')]")
    private WebElement submitBtnRefineByDate;
    @FindBy(xpath = "//button[text()='Clear All']")
    private WebElement clearAllButton;
    @FindBy(xpath = "//button[@title='Save']")
    private WebElement saveButton;
    @FindBy(xpath = "//span[@title='Share']//parent::button[@role='button']")
    private WebElement shareButton;
    @FindBy(xpath = "(//span[contains(@title,'access')])[1]")
    private WebElement accessIcon;
    @FindBy(xpath = "(//div[contains(@data-testid,'contributors')])[1]")
    private WebElement authorElement;
    @FindBy(xpath = "(((//div[contains(@data-testid,'contributors')])[1]//child::div)//child::span)[1]")
    private WebElement firstAuthorFirstArticle;
    @FindBy(xpath = "(//span[contains(text(),'Volume/Issue')])[1]")
    private WebElement volumeIssueElement;
    @FindBy(xpath = "(//span[contains(text(),'DOI')])[1]")
    private WebElement DOIElement;
    @FindBy(xpath = "(//span[contains(text(),'Online Publication Date')])[1]")
    private WebElement onlinePublicationDateElement;
    @FindBy(xpath = "(//button[contains(text(),'Abstract')])[1]")
    private WebElement abstractElement;
    @FindBy(xpath = "//span[contains(text(),'Refine terms')]")
    private WebElement refineTerms;
    @FindBy(xpath = "//span[contains(text(),'Refine by Type')]")
    private WebElement refineByType;
    @FindBy(xpath = "//span[contains(text(),'By Article Type')]")
    private WebElement refineByArticleType;
    @FindBy(xpath = "//span[contains(text(),'Refine by Date')]")
    private WebElement refineByDate;
    @FindBy(xpath = "//span[contains(text(),'Refine by Access')]")
    private WebElement refineByAccess;
    @FindBy(xpath = "//select[@name='fromDate']")
    private WebElement fromDateDD;
    @FindBy(xpath = "//select[@name='toDate']")
    private WebElement toDateDD;
    @FindBy(xpath = "(//span[text()='Restricted access']//preceding::div[@class='title'])[1]")
    private WebElement firstRestrictedContent;
    @FindBy(xpath = "(//input[@aria-label='Quick search term'])[1]")
    private WebElement defaultRefinetermTextbox;
    @FindBy(xpath = "((//div[@class='title'])[1]//following::span[text()='Abstract'])[1]")
    private WebElement abstractButton;
    @FindBy(xpath = "//meta[@property='og:url']")
    private WebElement ogURLMetaTag;
    @FindBy(xpath = "//meta[@property='og:site_name']")
    private WebElement ogSiteNameMetaTag;
    @FindBy(xpath = "//meta[@property='og:type']")
    private WebElement ogTypeMetaTag;
    @FindBy(xpath = "//meta[@property='og:locale']")
    private WebElement ogLocaleMetaTag;
    @FindBy(xpath = "//meta[@property='og:image']")
    private WebElement ogImageMetaTag;
    @FindBy(xpath = "//meta[@property='twitter:card']")
    private WebElement twitterCardMetaTag;
    @FindBy(xpath = "//meta[@property='twitter:title']")
    private WebElement twitterTitleMetaTag;
    @FindBy(xpath = "(//meta[@property='og:description'])[1]")
    private WebElement ogDescriptionMetaTag;
    @FindBy(xpath = "(//meta[@name='description'])[2]")
    private WebElement descriptionMetaTag;
    @FindBy(xpath = "//meta[@property='twitter:description']")
    private WebElement twitterDescriptionMetaTag;
    @FindBy(xpath = "//meta[@property='og:title']")
    private WebElement ogTitleMetaTag;
    @FindBy(xpath = "//span[text()='By Journal']")
    private WebElement byJournalFilter;
    @FindBy(xpath = "//span[text()='Brain Medicine']")
    private WebElement firstJournalFilterValue;
    @FindBy(xpath = "(//span[text()='By Journal']//following::span[@data-testid='treeFilter']//following::button[1]//child::span)[2]")
    private WebElement firstJournalFilterValueCount;
    @FindBy(xpath = "//button[@title='[+] Add Value']")
    private WebElement AddValueRefineTerm;
    @FindBy(xpath = "//button[contains(@data-testid,'AdvanceSearchFilter-remove-cta-0-1')]")
    private WebElement crossButtonAdvancedSearchFilter;
    @FindBy(xpath = "//span[@data-testid='dataFilter']//following::button[@title='[+] Add row']")
    private WebElement addRowForDateFilter;
	@FindBy(xpath = "//select[@id='sortOptions']//option[text()='Relevance']")
	private WebElement relevanceSortBY;
	@FindBy(xpath="//select[@id='itemsPerPage']//option[@selected]")
	private WebElement selectedItemPerPage;
	@FindBy(xpath="(//span[text()='Refine by Type']//following::span[text()='Article'])[1]")
	private WebElement articleValuefromRefineByType;

}


