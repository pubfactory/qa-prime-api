package com.prime.pageFactory.pages.fpj;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

import freemarker.core.ReturnInstruction.Return;

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
     * This method returns first article title on Browse/search  Page
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
     * This method returns first DOI Value on Browse/search  Page
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
     * 
     * This method returns first Author Value on Browse/search  Page
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
		clickOnElement(firstArticleOnBrowseOrSearchPage,"Click On First Article On Search Or Browse Page");
		
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
	 * This method used to select the Refine term value from Refine term drop down on Browse or Search Result Page
	 * 
	 * @param value
	 * @param refineTermOption
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 06/10/2023
	 */
	public void selectRefineTermValueFromRefineTermDDOnBrowseOrSearchResultPage(String testidvalue,String refineTermOption) throws Exception {
		WebElement selectValueFromRefineTermDD=driver.findElement(By.xpath("//select[@data-testid='"+testidvalue+"']//option[text()='"+refineTermOption+"']"));
		clickOnElement(selectValueFromRefineTermDD,
				"Selecting the "+refineTermOption+" value from Refine Term dropdown on Browse or Search Page");
	}
	
	/**
	 * This method returns all Refine term filter values from Refine term drop down on Browse or Search Result Page
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
     * This method used to entering the refine term value in refine term text box on Search or browse page
     * 
     * @param testIDValue
     * @param enterRefineTermValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/10/2023
     */
    public void enterRefineTermValueInRefineTermBoxOnBrowseOrSearchPage(String testidvalue,String enterRefineTermValue) throws Exception {
    	WebElement refineTermValueTxtBox = driver.findElement(By.xpath("//input[@data-testid='"+testidvalue+"']"));
        typeOnElement(refineTermValueTxtBox,enterRefineTermValue ,"Entering the Refine Term Value in Refine Term Textbox on Search or browse page");
    }
	
    /**
     * This method used to verify the refine term textbox in refine term filter is present on Search or browse page
     * 
     * @param testIDValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyRefineTermTextBoxIsPresentOnBrowseOrSearchPage(String testIDValue) throws Exception {
    	 List<WebElement> refineTermValueTxtBox = driver.findElements(By.xpath("//input[@data-testid='"+testIDValue+"']"));
       return isElementPresent(refineTermValueTxtBox);
    }
    
    /**
     * This method used to verify the refine term textbox in refine term filter is not present on Search or browse page
     * 
     * @param testIDValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/10/2023
     */
    public boolean verifyRefineTermTextBoxIsNotPresentOnBrowseOrSearchPage(String testIDValue) throws Exception {
    	List<WebElement> refineTermValueTxtBox = driver.findElements(By.xpath("//input[@data-testid='"+testIDValue+"']"));
       return isElementNotPresent(refineTermValueTxtBox);
    }
    
    
    /**
	 * This method used to verify the refine term options field in refine term filter is present on Search or browse page
	 * 
	 * @param testidvalue
	 * @throws Exception
	 * @author Rakesh.Shevale
     * @return boolean
	 * @Created Date : 17/10/2023
	 */
	public boolean verifyRefineTermValueOptionIsPresentOnBrowseOrSearchPage(String testidvalue) throws Exception {
		List<WebElement> selectValueFromRefineTermDD = driver.findElements(By.xpath("//select[@data-testid='"+testidvalue+"']"));
		return isElementPresent(selectValueFromRefineTermDD);
	}
    
	 /**
		 *  This method used to verify the refine term options field in refine term filter is not present on Search or browse page
		 * 
		 * @param testidvalue
		 * @throws Exception
		 * @author Rakesh.Shevale
	     * @return boolean
		 * @Created Date : 17/10/2023
		 */
		public boolean verifyRefineTermValueOptionIsNotPresentOnBrowseOrSearchPage(String testidvalue) throws Exception {
			List<WebElement> selectValueFromRefineTermDD = driver.findElements(By.xpath("//select[@data-testid='"+testidvalue+"']"));
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
     * This method used to clicks on cancel button front of Refine term textbox In Refine term DD 
     * 
     * @param testIDValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnCancelButtonFrontOfRefineTermTextBoxInRefineTermDDOnBrowseOrSearchPage(String testIDValue) throws Exception {
    	WebElement refineTermTxtBoxCancelButton= driver.findElement(By.xpath("//button[@data-testid='"+testIDValue+"']"));
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
     * This method used to clicks on Search button in Refine term filter on Browse or search page
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
     * @param byType
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnRefineByTypeFilterValueOnBrowseOrSearchPage(String byType) throws Exception {
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Refine by Type')]//following::ul[1]//li//button//span[text()='"+byType+"']"));
        clickOnElement(element, "Clicking on " + byType + " Refine by type");
    }
    
    /**
   	 * This method used to select the FromDate value from FromDate drop down In Refine by date filter on Browse or Search Result Page
   	 * 
   	 * @param value
   	 * @throws Exception
   	 * @author Rakesh.Shevale
   	 * @Created Date : 06/10/2023
   	 */
   	public void selectFromDateValueFromFromDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(String value) throws Exception {
   			selectByValue(selectDateFromDD, value,
   				"Selecting the value " + value + " from fromDate dropdown in refine by Date filter on Browse or Search Page");
   	}
    
    /**
   	 * This method used to select the toDate value from FromDate drop down In Refine by date filter on Browse or Search Result Page
   	 * 
   	 * @param value
   	 * @throws Exception
   	 * @author Rakesh.Shevale
   	 * @Created Date : 06/10/2023
   	 */
   	public void selectFromDateValueFromToDateDDInRefineByDateFilterOnBrowseOrSearchResultPage(String value) throws Exception {
   			selectByValue(selectDateToDD, value,
   				"Selecting the value " + value + " from toDate dropdown in refine by Date filter on Browse or Search Page");
   	}
   	
   	/**
     * This method used to clicks on Submit button in Refine by Date filter on Browse or search page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/10/2023
     */
    public void clickOnSubmitButtonInRefineByDateOnBrowseOrSearchPage() throws Exception {
        clickOnElement(submitBtnRefineByDate, "Clicking on Submit button in Refine By Date filter on Browse or search page");
    }
    
    /**
     * This method used to clicks on accesstype article in Refine By Access filter on Browse or search page
     * 
     * @param accessType
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public void clickOnAccessTypeInRefineByAccessFilterOnBrowseOrSearchPage(String accessType) throws Exception {
    	WebElement accessTypeEle=driver.findElement(By.xpath("(//span[contains(text(),'Refine by Access')]//following::span[text()='"+accessType+"'])[1]"));
        clickOnElement(accessTypeEle, "Clicking on "+accessTypeEle+" accesstype article in Refine By Access filter on Browse or search page");
    }
    
    /**
     * This method used to verify the accesstype in Refine By Access filter is present on Browse or search page
     * 
     * @param accessType
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/10/2023
     */
    public boolean VerifyfilterOptionInRefineByAccessFilterIsPresentOnBrowseOrSearchPage(String accessType) throws Exception {
    	 List<WebElement> accessTypeEle = driver.findElements(By.xpath("(//span[contains(text(),'Refine by Access')]//following::span[text()='"+accessType+"'])[1]"));
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
	 * This method used to Verifying the Clear all button is present on Browse or search page
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
     * This method used to Verify refine term filter search keyword present on search/browse page
     * 
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
	 * @return 
     * @Created Date : 18/10/2023
     */
    public boolean verifyRefineTermFilterSearchKewordIsPresentOnSearchOrBrowsePage(String valueName) throws Exception {
    	 List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[contains(text(),'"+valueName+"')]//parent::span//following-sibling::button"));
        return isElementPresent(SearchSlugSign);
    }
    
    /**
     * This method used to Verify refine term filter search keyword present on search/browse page
     * 
     * @param valueName
     * @throws Exception
     * @author Rakesh.Shevale
     * @return 
     * @Created Date : 18/10/2023
     */
    public boolean verifyRefineTermFilterSearchKewordIsNotPresentOnSearchOrBrowsePage(String valueName) throws Exception {
    	 List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[text()='"+valueName+"']//parent::span//following-sibling::button"));
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
    	WebElement SearchSlugSign=driver.findElement(By.xpath("//strong[contains(text(),'"+valueName+"')]//parent::span//following-sibling::button"));
        clickOnElement(SearchSlugSign, "Clicking on - sign which is availbale in backside of "+valueName+" on Browse or search page");
    }
    
    /**
	 * This method used to Verifying Search Slug(-) Sign is Present On browse or SearchPage
	 *
	 * @param valueName
	 * @throws Exception
	 * @author Rakesh.Shevale
     * @return 
	 * @Created Date : 11/10/2023
	 */
	public boolean verifySearchSlugSignIsPresentOnBrowseOrSearchPage(String valueName) throws Exception {
		 List<WebElement> SearchSlugSign = driver.findElements(By.xpath("//strong[text()='"+valueName+"']//parent::span//following-sibling::button"));
		return isElementPresent(SearchSlugSign);
	}
	
	/**
	 * This method used to Verifying Search Slug(-) Sign is Present On browse or SearchPage
	 *
	 * @param valueName
	 * @throws Exception
	 * @author Rakesh.Shevale
     * @return 
	 * @Created Date : 18/10/2023
	 */
	public boolean verifyFilterValueIsPresentOnBrowseOrSearchPage(String valueName) throws Exception {
		 List<WebElement> filterValue = driver.findElements(By.xpath("(//strong[contains(text(),'"+valueName+"')])[1]"));
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
	 * This method used to Verifying the Share button is present on Browse or search page
	 *
	 * @throws Exception
	 * @return boolean
	 * @author Rakesh.Shevale
	 * @Created Date : 11/10/2023
	 */
	public boolean verifyShareButtonIsPresentOnBrowseOrSearchPage() throws Exception {
		List<WebElement> share = driver.findElements(By.xpath("//button[@title='Save']//following-sibling::span"));
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
	 * This method used to Verifying the save button is present on Browse or search page
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
	 * This method used to Verifying sharing platform is present when clicks on share button on Browse or search page
	 *
	 *@param platformName
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 11/10/2023
	 */
	public boolean verifySharingPlatformButtonIsPresentOnBrowseOrSearchPage(String platformName) throws Exception {
		 List<WebElement> platform = driver.findElements(By.xpath("//button[@title='Save']//following-sibling::span//following::button[contains(text(),'Share on "+platformName+"')]"));
		return isElementPresent(platform);
	}
	
	/**
	 * This method used to Verifying Item per page dropdown is  present on Browse or search page
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
		String defaultValue=getDefaultDropDownValue(itemsPerPage);
		return defaultValue;
	}
	
	/**
	 * This method used to select the item per page from item per page dropdown on browse or search result page
	 * 
	 * @param value
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 11/10/2023
	 */
	public void selectItemPerPageValueFromItemPerPageDropdownOnBrowseOrSearchPage(String value) throws Exception {
		selectByValue(itemsPerPage, value,
				"Selecting filter value from item per page dropdown on browse or search page");
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
		String totalCount=String.valueOf(list.size());
		return totalCount;
	}
	
	/**
	 * This method is used to check content title name present on browse or search page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 16/10/2023
	 */
	public boolean verifyContentTitleNameIsPresentOnBrosweOrSearchPage() throws Exception {
		List<WebElement> TitleName = driver.findElements(By.xpath("(//a[@target='_self'])[1]"));
		return isElementPresent(TitleName);
	}
	
	/**
	 * This method is used to check content Access Icon is present on browse or search page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 16/10/2023
	 */
	public boolean verifyContentAccessIconIsPresentOnBrosweOrSearchPage() throws Exception {
		List<WebElement>accessIcon=driver.findElements(By.xpath("(//span[contains(@title,'access')])[1]"));
		return isElementPresent(accessIcon);
	}
	/**
	 * This method is used to check content Author is present on browse or search page
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
	 * This method is used to check content Volume Issue is present on browse or search page
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
	 * This method is used to check content Online Publication Date is present on browse or search page
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
	 * This method is used to check content abstract is present on browse or search page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 16/10/2023
	 */
	public boolean verifyContentAbstractIsPresentOnBrosweOrSearchPage() throws Exception {
		List<WebElement> abstractele = driver.findElements(By.xpath("(//button[contains(text(),'Abstract')])[1]"));
		return isElementPresent(abstractele);
	}
	
	/**
     * 
     * This method returns Refine terms filter text on Browse Page
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
	 * This method is used to from Date dropdown in refine date filter is present on browse or search page
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
	 * This method is used to to Date dropdown in refine date filter is present on browse or search page
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
   	 * This method used to Verify the Clear button from Refine terms is present on Browse or search page
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

    @FindBy(xpath="(//a[@target='_self'])[1]")
    private WebElement firstArticleOnBrowseOrSearchPage;
	@FindBy(xpath="//button[@title='[+] Add row']")
	private WebElement AddRowRefineTerm;
	@FindBy(xpath="//button[@title='Clear']")
	private WebElement clearButtonRefineTerm;
	@FindBy(xpath="//button[@title='Clear']//following-sibling::button")
	private WebElement searchButtonRefineTerm;
	@FindBy(xpath="//span[contains(text(),'Refine by Date')]//following::select[@name='fromDate']")
	private WebElement selectDateFromDD;
	@FindBy(xpath="//span[contains(text(),'Refine by Date')]//following::select[@name='toDate']")
	private WebElement selectDateToDD;
	@FindBy(xpath="//span[contains(text(),'Refine by Date')]//following::button[contains(@title,'Submit')]")
	private WebElement submitBtnRefineByDate;
	@FindBy(xpath="//button[text()='Clear All']")
	private WebElement clearAllButton;
	@FindBy(xpath="//button[@title='Save']")
	private WebElement saveButton;
	@FindBy(xpath="//button[@title='Save']//following-sibling::span")
	private WebElement shareButton;
	@FindBy(xpath="(//span[contains(@title,'access')])[1]")
	private WebElement accessIcon;
	@FindBy(xpath="(//div[contains(@data-testid,'contributors')])[1]")
	private WebElement authorElement;
	@FindBy(xpath="(((//div[contains(@data-testid,'contributors')])[1]//child::div)//child::span)[1]")
	private WebElement firstAuthorFirstArticle;
	@FindBy(xpath="(//span[contains(text(),'Volume/Issue')])[1]")
	private WebElement volumeIssueElement;
	@FindBy(xpath="(//span[contains(text(),'DOI')])[1]")
	private WebElement DOIElement;
	@FindBy(xpath="(//span[contains(text(),'Online Publication Date')])[1]")
	private WebElement onlinePublicationDateElement;
	@FindBy(xpath="(//button[contains(text(),'Abstract')])[1]")
	private WebElement abstractElement;
	@FindBy(xpath="//span[contains(text(),'Refine terms')]")
	private WebElement refineTerms;
	@FindBy(xpath="//span[contains(text(),'Refine by Type')]")
	private WebElement refineByType;
	@FindBy(xpath="//span[contains(text(),'By Article Type')]")
	private WebElement refineByArticleType;
	@FindBy(xpath="//span[contains(text(),'Refine by Date')]")
	private WebElement refineByDate;
	@FindBy(xpath="//span[contains(text(),'Refine by Access')]")
	private WebElement refineByAccess;
	@FindBy(xpath="//select[@name='fromDate']")
	private WebElement fromDateDD;
	@FindBy(xpath="//select[@name='toDate']")
	private WebElement toDateDD;
	
	}
