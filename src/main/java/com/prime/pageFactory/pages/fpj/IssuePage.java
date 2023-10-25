package com.prime.pageFactory.pages.fpj;



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
        clickOnElement(firstArticleOnIssuePage, "Clicking on Artcile on Issue Page");
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



    @FindBy(xpath = "//span[text()='Issues']")
    private WebElement issuePageHeader;
    @FindBy(xpath = "(//div[@class='title'])[1]")
    private WebElement firstArticleOnIssuePage;
    @FindBy(xpath = "//a[text()='All Issues']")
    private WebElement allIssues;
	@FindBy(xpath="(//span[contains(text(),'Volume/Issue')])[1]")
	private WebElement volumeIssueElement;
	@FindBy(xpath="(//span[contains(text(),'DOI')])[1]")
	private WebElement DOIElement;
	@FindBy(xpath="(//div[contains(@data-testid,'contributors')])[1]")
	private WebElement authorElement;
	@FindBy(xpath="(//button[contains(text(),'Abstract')])[1]")
	private WebElement abstractElement;
}
