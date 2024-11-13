package com.prime.pageFactory.pages.fpj;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;
import com.prime.generics.Helper;

public class MasterPage extends BasePage {

	/**
	 * This constructor initializes the MasterPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	public MasterPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}

	/**
	 * This method used to Verifying the search box is present on header
	 *
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return
	 * @Created Date : 10/10/2023
	 */
	public boolean verifySearchBoxIsPresentOnHeader() throws Exception {
		List<WebElement> searchBox = driver.findElements(By.xpath("(//input[@placeholder='Search'])[1]"));
		return isElementPresent(searchBox);
	}

	/**
	 * This method used to enter the text in search box
	 * 
	 * @param searchText
	 * @return MasterPage Object
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	public MasterPage enterTextInSearchBoxOnHomePage(String searchText) throws Exception {
		typeOnElement(searchBox, searchText, "Entering the text in search box");
		Thread.sleep(1500);
		return new MasterPage(driver);
	}

	/**
	 * This method used to click on Magnifying Lens for search on home page`
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	public void clickOnSearchMagnifyingLense() throws Exception {
		clickOnElement(clickOnMagnifyingLens, "clicking on Magnifying Lens on home page beside the search box");
	}

	/**
	 * This method used to Verifying the Magnifying Lense is present on header
	 *
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 19/10/2023
	 */
	public boolean verifySearchMagnifyingLenseIsPresentOnHeader() throws Exception {
		List<WebElement> magniFyingLense = driver
				.findElements(By.xpath("(//button[@type='submit'][@aria-label='Search'])[1]"));
		return isElementPresent(magniFyingLense);
	}

	/**
	 * This method used to click on Article under Content List section on home page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 19/07/2023
	 */
	public void clickOnArticleUnderContentListOnHomePage() throws Exception {
		clickOnElement(homePageArticle, "clicking on Article under Content List section on home page");
	}

	/**
	 * This method used to click on Current Article on home page on TSCIR APP
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 19/07/2023
	 */
	public void clickOnCurrentArticleonHomePageTSCIRAPP() throws Exception {
		clickOnElement(currentArticleonHomePageTSCIRAPP, "clicking on current Article on home page on TSCIR APP");
	}

	/**
	 * This method used to click on SignIn link on the header()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public void clickOnsignInlinkOnHeader() throws Exception {
		clickOnElement(signInlinkOnHeader, "clicking on SignIn link on the header");
	}

	/**
	 * This method used to click on Sign In button link on the header()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 03/08/2023
	 */
	public void clickOnSignInButtonOnHeader() throws Exception {
		clickOnElement(SignInButton, "clicking on SignIn link on the header");
	}

	/**
	 * This method used to click on SignUp link on the header()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public void clickOnsignUplinkOnHeader() throws Exception {
		clickOnElement(signUplinkOnHeader, "clicking on SignUp link on the header");
	}

	/**
	 * This method used to click on My Stuff link on the header()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 03/08/2023
	 */
	public void clickOnMystuffLinkOnHeader() throws Exception {
		clickOnElement(myStuffLinkOnHeader, "clicking on mystuff link on the header");
	}

	/**
	 * This method return the About This Journal Text which is available on home
	 * page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public String getAboutThisJournalTextOnHomePage() throws Exception {
		String actualAboutThisJournalOnHomePage = getTextFromElement(AboutThisJournalOnHomePage);
		return actualAboutThisJournalOnHomePage;
	}

	/**
	 * This method used to check signIn link is present on header
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifySignInLinkpresentOnHeader() throws Exception {
		isElementPresent(signInlinkOnHeader, "checking the signIn link is present or not on header");
	}

	/**
	 * This method used to check signIn link is present on header
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifySignUpLinkpresentOnHeader() throws Exception {
		isElementPresent(signUplinkOnHeader, "checking the signUp link is present or not on header");
	}

	/**
	 * This method return the content list article title text on home page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public String getcontentListArticleTitleText() throws Exception {
		String contentListArticleTitleText = getTextFromElement(contentListArticleTitle);
		return contentListArticleTitleText;
	}

	/**
	 * This method clicks on view this issue on homepage
	 * 
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public void clickOnViewThisIssueOnHomePage() throws Exception {
		clickOnElement(viewThisIssue, "Clicking on View This Issue On HomePage");
	}

	/**
	 * This method clicks on main apps logo on left hand side
	 * 
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public void clickOnMainAppLogoInHeader() throws Exception {
		clickOnElement(AppMainLogo, "Clicking on main apps logo in header");
	}

	/**
	 * This method used to check Devtools setting link is present on header
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @return boolean
	 * @Created Date : 25/06/2024
	 */
	public boolean verifyDevToolsSettings() throws Exception {
		List<WebElement>DevTool=driver.findElements(By.xpath("//button[@data-testid='DevtoolsControls']"));
		 return isElementPresent(DevTool);
	}

	/**
	 * This method clicks on dev tools button in the home page
	 * 
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @Created Date : 25/06/2024
	 */
	public void clickOnDevToolSetting() throws Exception {
		clickOnElement(DevToolSetting, "Clicking on dev tool setting");
	}

	/**
	 * This method used to check Show Region Keys setting link is present on header
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @Created Date : 25/06/2024
	 */
	public boolean verifyShowRegionsKeysSettings() throws Exception {
		List<WebElement>ShowRegion=driver.findElements(By.xpath("//div[text()='Show Region Keys']"));
		 return isElementPresent(ShowRegion);
		
	}
	/**
	 * This method used to check Show Missing Components link is present on header
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @Created Date : 25/06/2024
	 */
	public boolean verifyShowMissingComponents() throws Exception {
		List<WebElement>ShowRegion=driver.findElements(By.xpath("//div[text()='Show Missing Components']"));
		 return isElementPresent(ShowRegion);
		
	}
	/**
	 * This method used to check Bypass MetaData Cache is present on header
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @Created Date : 25/06/2024
	 */
	public boolean verifyBypassMetaDataCatche() throws Exception {
		List<WebElement>ShowRegion=driver.findElements(By.xpath("//div[text()='Bypass Cache']"));
		 return isElementPresent(ShowRegion);
		
	}

	/**
	 * This method clicks on Show Region Keys button in the home page
	 * 
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @Created Date : 25/06/2024
	 */
	public void clickOnRegionKeySetting() throws Exception {
		clickOnElement(ShowRegionKeys, "Clicking on RegionKey setting");
	}

	/**
	 * This method used to check Devtools setting link is present on header
	 * 
	 * @throws Exception
	 * @author Veena.Mathew
	 * @Created Date : 25/06/2024
	 */
	public String getLabelNameFromSignInPostShowRegionKeysOON() throws Exception {
		return getTextFromElement(ShowRegionKeysSignIn).toString();
	}

	/**
	 * This method used to verify the with option(s) that matches the user has type
	 * 
	 * @param word
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 29/07/2024
	 * 
	 */
	public boolean VerifyingAutoSuggetionContainSearchWord(String word) throws Exception {

		boolean flag = false;
		List<String> AllSuggestion = getTextFindElements(By.xpath("//*[contains(@id,'id-typeahead-item')]"));
		if (AllSuggestion.size() > 0) {
			for (String tr : AllSuggestion) {
				if (tr.toUpperCase().startsWith(""+word.charAt(0))) {
					flag = true;
				} else {
					return false;
				}
			}
		}
		return flag;
	}

	/**
	 * This method returns the All Auto Suggestion List in list format
	 * 
	 * 
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 29/07/2024
	 */
	public List<String> getAllAutoSuggestionList() throws Exception {
		boolean flag = false;
		List<String> AllSuggestion = getTextFindElements(By.xpath("//*[contains(@id,'id-typeahead-item')]"));
		return AllSuggestion;
	}

	/**
	 * This method return the attribute value from Search Box
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @created Date : 29/07/24
	 */
	public String getSearchBoxValue() throws Exception {
		return getAttributeFromElement(searchBox, "value");
	}

	/**
	 * This method return the Auto Suggestion DD Focused Text
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @created Date : 29/07/24
	 */
	public String getAutoSuggestionFocusedText() throws Exception {
		return getTextFromElement(focusedAutoSuggestion);
	}
	
	/**
	 * This method return the attribute value from Search Box
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @created Date : 29/07/24
	 */
	public String getQuickSearchBoxAttributeValue() throws Exception {
		return getAttributeFromElement(quickSearchWithFormTag, "data-testid");
	}
	
	
	/**This method is used to check og:url meta tag is present on home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogURLMetaTagisPresentOnHomePage() throws Exception {
    	Thread.sleep(4000);
    	List<WebElement> OgURL=driver.findElements(By.xpath("//meta[@property='og:url']"));
    	 return isElementPresent(OgURL);
    }
    
    /**This method returns the og:url meta tag  content attribute value on home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgURLMetaTagPropertyValue() throws Exception {
    	String URL=getMetaTagAttribute(ogURLMetaTag,"content");
    	return URL;
    }
	
    /**This method is used to check og:site_name meta tag is present on home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogSiteNameMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> OgSiteName=driver.findElements(By.xpath("//meta[@property='og:site_name']"));
    	 return isElementPresent(OgSiteName);
    }
    
    /**This method returns the og:site_name meta tag content attribute value on home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgSiteNameMetaTagPropertyValue() throws Exception {
    	String siteName=getMetaTagAttribute(ogSiteNameMetaTag,"content");
    	return siteName;    	
    }
    
    /**This method is used to check og:type meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogTypeMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> ogType=driver.findElements(By.xpath("//meta[@property='og:type']"));
    	 return isElementPresent(ogType);
    }
    
    /**This method returns the og:type meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgTypeMetaTagPropertyValue() throws Exception {
    	String ogType=getMetaTagAttribute(ogTypeMetaTag,"content");
    	return ogType;    	
    }
    
    /**This method is used to check og:locale meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogLocaleMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> ogLocale=driver.findElements(By.xpath("//meta[@property='og:locale']"));
    	 return isElementPresent(ogLocale);
    }
    
    /**This method returns the og:locale meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgLocaleMetaTagPropertyValue() throws Exception {
    	String ogLocale=getMetaTagAttribute(ogLocaleMetaTag,"content");
    	return ogLocale;    	
    }
    
    /**This method is used to check og:image meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean ogImageMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> ogImage=driver.findElements(By.xpath("//meta[@property='og:image']"));
    	 return isElementPresent(ogImage);
    }
    
    /**This method returns the og:image meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgImageMetaTagPropertyValue() throws Exception {
    	String ogImage=getMetaTagAttribute(ogImageMetaTag,"content");
    	return ogImage;    	
    }
    
    /**This method is used to check twitter:card meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyTwitterCardMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> twitterCard=driver.findElements(By.xpath("//meta[@property='twitter:card']"));
    	 return isElementPresent(twitterCard);
    }
    
    /**This method returns the twitter:card meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getTwitterCardMetaTagPropertyValue() throws Exception {
    	String twitterCard=getMetaTagAttribute(twitterCardMetaTag,"content");
    	return twitterCard;    	
    }

    /**This method is used to check twitter:title meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyTwitterTitleMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> twitterTitle=driver.findElements(By.xpath("//meta[@property='twitter:title']"));
    	 return isElementPresent(twitterTitle);
    }
    
    /**This method returns the twitter:title meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getTwitterTitleMetaTagPropertyValue() throws Exception {
    	String twitterTitle=getMetaTagAttribute(twitterTitleMetaTag,"content");
    	return twitterTitle;    	
    }
    
    /**This method is used to check og:description meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyOgDescriptionMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> ogDescription=driver.findElements(By.xpath("(//meta[@property='og:description'])[1]"));
    	 return isElementPresent(ogDescription);
    }
    
    /**This method returns the og:description meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getOgDescriptionMetaTagPropertyValue() throws Exception {
    	String ogDescription=getMetaTagAttribute(ogDescriptionMetaTag,"content");
    	return ogDescription;    	
    }
    
    /**This method is used to check og:description meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyDescriptionMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> Description=driver.findElements(By.xpath("(//meta[@name='description'])[1]"));
    	 return isElementPresent(Description);
    }
    
    /**This method returns the og:description meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getDescriptionMetaTagPropertyValue() throws Exception {
    	String ogDescription=getMetaTagAttribute(descriptionMetaTag,"content");
    	return ogDescription;    	
    }
    
    /**This method is used to check twitter:description meta tag is present on Home page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public boolean VerifyTwitterDescriptionMetaTagisPresentOnHomePage() throws Exception {
    	List<WebElement> twitterDescription=driver.findElements(By.xpath("//meta[@property='twitter:description']"));
    	 return isElementPresent(twitterDescription);
    }
    
    /**This method returns the twitter:description meta tag content attribute value on Home page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/08/2024
     */
    public String getTwitterDescriptionMetaTagPropertyValue() throws Exception {
    	String twitterDescription=getMetaTagAttribute(twitterDescriptionMetaTag,"content");
    	return twitterDescription;    	
    }
    
    /**
     * 
     * This method is used to clicks on About static page button
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/20234
     * 
     */
    public void clickOnAboutStaticButtonAtFooter() throws Exception {
        clickOnElement(aboutStaticButton, "clicking on About static button at the footer");
    }
    
    /**
	 * This method return the Sign In button text
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @created Date : 08/10/24
	 */
	public String getSignInButtonText() throws Exception {
		return getTextFromElement(SignInButton);
	}
	
	/**
	 * This method return the current issue volume text on home page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @created Date : 31/10/24
	 */
	public String getCurrentIssueVolumeTextOnHomePage() throws Exception {
		return getTextFromElement(currentIssueVolumeText);
	}
    

	@FindBy(xpath = "//button[contains(text(),'Accept All Cookies')]")
	private WebElement AcceptAllCookies;
	@FindBy(xpath = "(//input[@placeholder='Search'])[1]")
	private WebElement searchBox;
	@FindBy(xpath = "(//button[@type='submit'][@aria-label='Search'])[1]")
	private WebElement clickOnMagnifyingLens;
	@FindBy(xpath = "(//button[contains(text(),'Content List')]//parent::div//following-sibling::div//following::div[@class='title'])[1]")
	private WebElement homePageArticle;
	@FindBy(xpath = "(//p[contains(text(),'Current Articles')])[2]//parent::div//ancestor::div[@data-identifier='<current-articles-title>']//following::div[@class='title'][1]")
	private WebElement currentArticleonHomePageTSCIRAPP;
	@FindBy(xpath = "//div[@data-identifier='<search_input>']//following-sibling::div//a[contains(text(),'Sign in')]")
	private WebElement signInlinkOnHeader;
	@FindBy(xpath = "//a[contains(text(),'Sign in')]")
	private WebElement SignInButton;
	@FindBy(xpath = "//div[@data-identifier='<search_input>']//following-sibling::div//a[contains(text(),'Sign up')]")
	private WebElement signUplinkOnHeader;
	@FindBy(xpath = "//div[@data-identifier='<search_input>']//following-sibling::div//a[contains(text(),'My Stuff')]")
	private WebElement myStuffLinkOnHeader;
	@FindBy(xpath = "//h3[contains(text(),'About this Journal')]")
	private WebElement AboutThisJournalOnHomePage;
	@FindBy(xpath = "//div[@class='title']")
	private WebElement contentListArticleTitle;
	@FindBy(xpath = "(//a[text()='View This Issue'])[2]")
	private WebElement viewThisIssue;
	@FindBy(xpath = "(//img[@alt='Logo'])[2]")
	private WebElement AppMainLogo;
	@FindBy(xpath = "//button[@data-testid='DevtoolsControls']")
	private WebElement DevToolSetting;
	@FindBy(xpath = "//section[contains(@id,'popover-content')]//label[@data-testid='showRegionKeys']//span")
	private WebElement ShowRegionKeys;
	@FindBy(xpath = "//div[@data-testid='block-authbuttons']/a")
	private WebElement ShowRegionKeysSignIn;
	@FindBy(xpath = "//*[@class='dropdown-item active']")
	private WebElement focusedAutoSuggestion;
	@FindBy(xpath="//form[@role='search']")
	private WebElement quickSearchWithFormTag;
	@FindBy(xpath="//meta[@property='og:url']")
    private WebElement ogURLMetaTag;
	@FindBy(xpath="//meta[@property='og:site_name']")
	private WebElement ogSiteNameMetaTag;
    @FindBy(xpath="//meta[@property='og:type']")
    private WebElement ogTypeMetaTag;
    @FindBy(xpath="//meta[@property='og:locale']")
    private WebElement ogLocaleMetaTag;
    @FindBy(xpath="//meta[@property='og:image']")
    private WebElement ogImageMetaTag;
    @FindBy(xpath="//meta[@property='twitter:card']")
    private WebElement twitterCardMetaTag;
    @FindBy(xpath="//meta[@property='twitter:title']")
    private WebElement twitterTitleMetaTag;
    @FindBy(xpath="(//meta[@property='og:description'])[1]")
    private WebElement ogDescriptionMetaTag;
    @FindBy(xpath="(//meta[@name='description'])[1]")
    private WebElement descriptionMetaTag;
    @FindBy(xpath="//meta[@property='twitter:description']")
    private WebElement twitterDescriptionMetaTag;
    @FindBy(xpath="//a[@data-testid='block-labeledbutton' and text()='About']")
    private WebElement aboutStaticButton;   
    @FindBy(xpath="//h1[@data-identifier='<issue_title>']")
    private WebElement currentIssueVolumeText;
}
