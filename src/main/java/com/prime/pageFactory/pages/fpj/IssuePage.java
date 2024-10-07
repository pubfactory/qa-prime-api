package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

public class IssuePage extends BasePage {

	/**
	 * This constructor initializes the MasterPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/07/2023
	 */
	public IssuePage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}

	/**
	 * This method return the Issue Page header text
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/09/2023
	 */
	public String getIssuePageHeaderText() throws Exception {
		String header = getTextFromElement(issuePageHeader);
		return header;
	}

	/**
	 * This method used to clicks on first article on Issue Page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/09/2023
	 */
	public void clickOnFirstArticleOnIssuePage() throws Exception {
		clickOnElement(firstArticleOnIssuePage, "Clicking on Article on Issue Page");
	}

	/**
	 * This method return the first Article header text on Issue Page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/09/2023
	 */
	public String getFirstArticleTextOnIssuePageHeaderText() throws Exception {
		String articleText = getTextFromElement(firstArticleOnIssuePage);
		return articleText;
	}

	/**
	 * This method used to clicks on All Issues on Issue Page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 28/09/2023
	 */
	public void clickOnAllIssuesOnIssuePage() throws Exception {

		clickOnElement(allIssues, "Clicking on All Issues on Issue Page");
	}

	/**
	 * This method is used to check content title name present on Issue Page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 16/10/2023
	 */
	public boolean verifyContentTitleNameIsPresentOnIssuePage() throws Exception {
		List<WebElement> TitleName = driver.findElements(By.xpath("(//a[@target='_self'])[1]"));
		return isElementPresent(TitleName);
	}

	/**
	 * This method is used to clicks on content on Issue Page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 25/10/2023
	 */
	public boolean ClickingOnContentToCheckHyperLinkOrNot() throws Exception {
		return clickOnElement(firstArticleOnIssuePage);
	}

	/**
	 * This method is used to check content Author is present on issue page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 25/10/2023
	 */
	public boolean verifyContentAuthorIsPresentOnIssuePage() throws Exception {
		List<WebElement> author = driver.findElements(By.xpath("(//div[contains(@data-testid,'contributors')])[1]"));
		return isElementPresent(author);
	}

	/**
	 * This method is used to check content DOI is present on issue page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 25/10/2023
	 */
	public boolean verifyContentDOIIsPresentOnIssuePage() throws Exception {
		List<WebElement> DOI = driver.findElements(By.xpath("(//span[contains(text(),'DOI')])[1]"));
		return isElementPresent(DOI);
	}

	/**
	 * This method is used to check content abstract is present on Issue page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 25/10/2023
	 */
	public boolean verifyContentAbstractIsPresentOnIssuePage() throws Exception {
		List<WebElement> abstractele = driver.findElements(By.xpath("	]"));
		return isElementPresent(abstractele);
	}

	/**
	 * This method is used to check content Volume Issue is present on Issue page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @Created Date : 25/10/2023
	 */
	public boolean verifyContentVolumeIssueIsPresentOnIssuePage() throws Exception {
		List<WebElement> volumeIssue = driver.findElements(By.xpath("(//span[contains(text(),'Volume/Issue')])[1]"));
		return isElementPresent(volumeIssue);
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
	 * This method is used to verifying the search keyword is displayed as hit
	 * highlighted
	 * 
	 * @param searchKeyword
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @return boolean
	 * @created Date : 13/06/24
	 */
	public boolean verifySearchKeywordIsNotHitHighlighted(String searchKeyword) throws Exception {
		List<WebElement> element = driver
				.findElements(By.xpath("//span[@class='hi'][contains(text(),'" + searchKeyword + "')]"));
		return isElementNotPresent(element);
	}

	/**
	 * This method is used to check og:url meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean ogURLMetaTagisPresentOnIssuePage() throws Exception {
		Thread.sleep(4000);
		List<WebElement> OgURL = driver.findElements(By.xpath("//meta[@property='og:url']"));
		return isElementPresent(OgURL);
	}

	/**
	 * This method returns the og:url meta tag content attribute value on Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgURLMetaTagPropertyValue() throws Exception {
		String URL = getMetaTagAttribute(ogURLMetaTag, "content");
		return URL;
	}

	/**
	 * This method is used to check og:site_name meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean ogSiteNameMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> OgSiteName = driver.findElements(By.xpath("//meta[@property='og:site_name']"));
		return isElementPresent(OgSiteName);
	}

	/**
	 * This method returns the og:site_name meta tag content attribute value on
	 * Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgSiteNameMetaTagPropertyValue() throws Exception {
		String siteName = getMetaTagAttribute(ogSiteNameMetaTag, "content");
		return siteName;
	}

	/**
	 * This method is used to check og:type meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean ogTypeMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> ogType = driver.findElements(By.xpath("//meta[@property='og:type']"));
		return isElementPresent(ogType);
	}

	/**
	 * This method returns the og:type meta tag content attribute value on Issue
	 * page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgTypeMetaTagPropertyValue() throws Exception {
		String ogType = getMetaTagAttribute(ogTypeMetaTag, "content");
		return ogType;
	}

	/**
	 * This method is used to check og:locale meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean ogLocaleMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> ogLocale = driver.findElements(By.xpath("//meta[@property='og:locale']"));
		return isElementPresent(ogLocale);
	}

	/**
	 * This method returns the og:locale meta tag content attribute value on Issue
	 * page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgLocaleMetaTagPropertyValue() throws Exception {
		String ogLocale = getMetaTagAttribute(ogLocaleMetaTag, "content");
		return ogLocale;
	}

	/**
	 * This method is used to check og:image meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean ogImageMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> ogImage = driver.findElements(By.xpath("//meta[@property='og:image']"));
		return isElementPresent(ogImage);
	}

	/**
	 * This method returns the og:image meta tag content attribute value on Issue
	 * page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgImageMetaTagPropertyValue() throws Exception {
		String ogImage = getMetaTagAttribute(ogImageMetaTag, "content");
		return ogImage;
	}

	/**
	 * This method is used to check twitter:card meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean VerifyTwitterCardMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> twitterCard = driver.findElements(By.xpath("//meta[@property='twitter:card']"));
		return isElementPresent(twitterCard);
	}

	/**
	 * This method returns the twitter:card meta tag content attribute value on
	 * Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getTwitterCardMetaTagPropertyValue() throws Exception {
		String twitterCard = getMetaTagAttribute(twitterCardMetaTag, "content");
		return twitterCard;
	}

	/**
	 * This method is used to check twitter:title meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean VerifyTwitterTitleMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> twitterTitle = driver.findElements(By.xpath("//meta[@property='twitter:title']"));
		return isElementPresent(twitterTitle);
	}

	/**
	 * This method returns the twitter:title meta tag content attribute value on
	 * Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getTwitterTitleMetaTagPropertyValue() throws Exception {
		String twitterTitle = getMetaTagAttribute(twitterTitleMetaTag, "content");
		return twitterTitle;
	}

	/**
	 * This method is used to check og:description meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean VerifyOgDescriptionMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> ogDescription = driver.findElements(By.xpath("(//meta[@property='og:description'])[1]"));
		return isElementPresent(ogDescription);
	}

	/**
	 * This method returns the og:description meta tag content attribute value on
	 * Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgDescriptionMetaTagPropertyValue() throws Exception {
		String ogDescription = getMetaTagAttribute(ogDescriptionMetaTag, "content");
		return ogDescription;
	}

	/**
	 * This method is used to check og:description meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean VerifyDescriptionMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> Description = driver.findElements(By.xpath("(//meta[@name='description'])[1]"));
		return isElementPresent(Description);
	}

	/**
	 * This method returns the og:description meta tag content attribute value on
	 * Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getDescriptionMetaTagPropertyValue() throws Exception {
		String ogDescription = getMetaTagAttribute(descriptionMetaTag, "content");
		return ogDescription;
	}

	/**
	 * This method is used to check twitter:description meta tag is present on Issue
	 * page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean VerifyTwitterDescriptionMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> twitterDescription = driver.findElements(By.xpath("//meta[@property='twitter:description']"));
		return isElementPresent(twitterDescription);
	}

	/**
	 * This method returns the twitter:description meta tag content attribute value
	 * on Issue page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getTwitterDescriptionMetaTagPropertyValue() throws Exception {
		String twitterDescription = getMetaTagAttribute(twitterDescriptionMetaTag, "content");
		return twitterDescription;
	}

	/**
	 * This method is used to check og:title meta tag is present on Issue page
	 * 
	 * @return boolean
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public boolean VerifyOgTitleMetaTagisPresentOnIssuePage() throws Exception {
		List<WebElement> ogTitle = driver.findElements(By.xpath("//meta[@property='og:title']"));
		return isElementPresent(ogTitle);
	}

	/**
	 * This method returns the og:title meta tag content attribute value on Issue
	 * page
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 13/08/2024
	 */
	public String getOgTitleMetaTagPropertyValue() throws Exception {
		String ogTitle = getMetaTagAttribute(ogTitleMetaTag, "content");
		return ogTitle;
	}

	/**
	 * This method returns the List of the Volume on Journal page
	 * 
	 * @param buttontext
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/10/2024
	 */
	public List<String> getTotalVolumeListOnJournalPage() throws Exception {
		List<String> actualList = new ArrayList<String>();
		By locator = By.xpath("//div[contains(text(),'Volume:')]");
		actualList = getTextFindElements(locator);
		return actualList;
	}

	/**
	 * This method clicks on the Issue Select Dropdown
	 * 
	 * @return void
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/10/2024
	 */
	public void clickOnIssueSelector() throws Exception {
		WebElement issueSelect = driver.findElement(By.xpath("//button[@data-testid='block-issueselector-trigger']"));
		clickOnElement(issueSelect);
	}

	/**
	 * This method clicks on the Volume from Select Issue Drop down on Journal page
	 * 
	 * @param volumeValue
	 * @return void
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/10/2024
	 */
	public void clickOnVolumeFromSelectIssueDDOnJournalPage(String volumeValue) throws Exception {
		WebElement volumeNumber = driver.findElement(By.xpath("(//div[contains(text(),'" + volumeValue + "')])[1]"));
		clickOnElement(volumeNumber, "Clicking on Volume Section on Journal page");
	}

	/**
	 * This method returns the Volume Number from select issue drop down in Integer
	 * format
	 * 
	 * @param volumeValue
	 * @return Integer
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/10/2024
	 */
	public int getVolumeNumberFromSelectIssueDDOnJournalPage(String volumeValue) throws Exception {
		WebElement volumeSection = driver.findElement(By.xpath("(//div[contains(text(),'" + volumeValue + "')])[1]"));
		String volumeNumber = getTextFromElement(volumeSection);
		int volume = Integer.parseInt(volumeNumber.substring(8, volumeNumber.length() - 7));
		return volume;
	}

	/**
	 * This method returns the List of the issue under volume From Select Issue DD
	 * on Issue Page
	 * 
	 * @param buttontext
	 * @return List<String>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/10/2024
	 */
	public List<String> getTotalIssueListUnderVolumeFromSelectIssueDDOnJournalPage(String volumeValue)
			throws Exception {
		List<String> actualList = new ArrayList<String>();
		By locator = By.xpath("(//div[contains(text(),'" + volumeValue
				+ "')])[1]//parent::div//following::div[contains(@style,'display: block')]");
		actualList = getTextFindElements(locator);
		return actualList;
	}

	/**
	 * This method returns the Issue Number under Volume from select issue drop down
	 * in Integer format
	 * 
	 * @param volumeValue
	 * @return Integer
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 07/10/2024
	 */
	public int getIssueNumberUnderVolumeFromSelectIssueDDOnJournalPage(String volumeValue) throws Exception {
		int issue = 0;
		if (!(volumeValue == null)) {
			return issue = Integer.parseInt(volumeValue.substring(6));
		} else
			return issue;
	}

	@FindBy(xpath = "//span[text()='Issues']")
	private WebElement issuePageHeader;
	@FindBy(xpath = "(//div[@class='title'])[1]")
	private WebElement firstArticleOnIssuePage;
	@FindBy(xpath = "//a[text()='All Issues']")
	private WebElement allIssues;
	@FindBy(xpath = "(//span[contains(text(),'Volume/Issue')])[1]")
	private WebElement volumeIssueElement;
	@FindBy(xpath = "(//span[contains(text(),'DOI')])[1]")
	private WebElement DOIElement;
	@FindBy(xpath = "(//div[contains(@data-testid,'contributors')])[1]")
	private WebElement authorElement;
	@FindBy(xpath = "(//button[contains(text(),'Abstract')])[1]")
	private WebElement abstractElement;
	@FindBy(xpath = "(//div[@class='title'])[1]//following::span[text()='Abstract'][1]")
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

}
