package com.prime.pageFactory.pages.fpj;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

public class BrowseOrSearchPage extends BasePage {

    /**
     * 
     * This constructor initializes the ArticleCitationPage class object
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
        //        WebDriverWait wait = new WebDriverWait(driver, 30);
        //        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Page:']//following-sibling::ul[@role='list']//child::li")));
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
    public int getLastItemOfPaginationLinks() throws Exception {
        WebElement lastWebElementOfPaginationLinks = paginationArray.get(paginationArray.size() - 1);
        String lastItemOfPaginationLinksString = getTextFromElement(lastWebElementOfPaginationLinks);
        int lastPageOfPagination = Integer.parseInt(lastItemOfPaginationLinksString);
        return lastPageOfPagination;
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
     * This method clicks on article type filter
     * 
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


    @FindBy(xpath = "//h1[text()='Browse']")
    private WebElement browseText;

    @FindBy(xpath = "//span[text()='of ']//following::span[1]")
    private WebElement totalResultCount;

    @FindBy(xpath = "//select[@id='itemsPerPage']")
    private WebElement itemsPerPage;

    @FindBy(xpath = "")
    private WebElement noOfPaginationLinks;

    @FindBy(xpath = "//span[text()='Page:']//following-sibling::ul[@role='list']//child::li")
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



}
