package com.prime.pageFactory.pages.fpj;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

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
		List<WebElement>searchBox=driver.findElements(By.xpath("(//input[@placeholder='Search'])[1]"));
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
		List<WebElement>magniFyingLense=driver.findElements(By.xpath("(//button[@type='submit'][@aria-label='Search'])[1]"));
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
    @FindBy(xpath="(//img[@alt='Logo'])[2]")
    private WebElement AppMainLogo;

}
