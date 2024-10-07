package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

public class JournalPage extends BasePage {

    /**
     * This constructor initializes the JournalPage class object
     * 
     * @param WebDriver
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 27/07/2023
     */
    public JournalPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForDocumentReady();
    }

    /**
     * This method return the Journal Page header text
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 28/09/2023
     */
    public String getJournalPageHeaderText() throws Exception {
        String header = getTextFromElement(journalpageHeader);
        return header;
    }

    /**
     * This method used to verify Volume section is present on Journal page
     * 
     * @param volumeValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 20/10/2023
     */
    public boolean verifyVolumeSectionIsDisplayed(String volumeValue) throws Exception {
        List<WebElement> volumeSection = driver.findElements(By.xpath("(//span[contains(text(),'" + volumeValue + "')])[1]"));
        return isElementPresent(volumeSection);
    }

    /**
     * This method used to verify Volume link are collapsed on Journal page
     * 
     * @param volumeValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 20/10/2023
     */
    public boolean verifyVolumeLinkCollapseOnJournalPage(String volumeValue) throws Exception {
        List<WebElement> volumeCollpase = driver.findElements(By.xpath("(//span[contains(text(),'" + volumeValue + "')])[1]//parent::div//parent::button[@aria-expanded='false']"));
        return isElementPresent(volumeCollpase);
    }

    /**
     * This method used to verify Volume link are Expanded on Journal page
     * 
     * @param volumeValue
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 20/10/2023
     */
    public boolean verifyVolumeLinkExpandOnJournalPage(String volumeValue) throws Exception {
        List<WebElement> volumeCollpase = driver.findElements(By.xpath("(//span[contains(text(),'" + volumeValue + "')])[1]//parent::div//parent::button[@aria-expanded='true']"));
        return isElementPresent(volumeCollpase);
    }

    /**
     * This method used to verfy issue Toc is present on journal page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 23/10/2023
     */
    public boolean verifyIssueToCIsPresentOnJournalPage() throws Exception {
        List<WebElement> IssuTOC = driver.findElements(By.xpath("//div[@data-identifier='<issue-ToC>']"));
        return isElementPresent(IssuTOC);
    }


    /**
     * This method clicks on Volume Section on Journal page
     * @param volumeValue
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 20/10/2023
     */
    public void clickOnVolumeSectionOnJournalPage(String volumeValue) throws Exception {
        WebElement volumeSection = driver.findElement(By.xpath("(//span[contains(text(),'" + volumeValue + "')])[1]"));
        clickOnElement(volumeSection, "Clicking on Volume Section on Journal page");
    }

    /**
     * This method used to verify issue is displayed under Volume section on Journal page
     * 
     * @param volumeValue
     * @throws Exception
     * @return boolean
     * @author Rakesh.Shevale
     * @Created Date : 20/10/2023
     */
    public boolean verifyIssueIsDisplayedUnderVolumeSectionOnJornalPage(String volumeValue) throws Exception {
        List<WebElement> issue = driver.findElements(By.xpath("((//span[contains(text(),'" + volumeValue + "')])[1]//following::a[contains(text(),'Issue 1')])[1]"));
        return isElementPresent(issue);
    }

    /**
     * This method clicks on issue under Volume section on Journal page
     * @param volumeValue
     * @return void
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 20/10/2023
     */
    public void clickOnIssueUnderVolumeSectionOnJornalPage(String volumeValue, String issueValue) throws Exception {
        WebElement volumeSection = driver.findElement(By.xpath("((//span[contains(text(),'" + volumeValue + "')])[1]//following::a[contains(text(),'" + issueValue + "')])[1]"));
        clickOnElement(volumeSection, "Clicking on issue under Volume section on Journal page");
    }

    /**This method is used to check og:url meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean ogURLMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> OgURL = driver.findElements(By.xpath("//meta[@property='og:url']"));
        return isElementPresent(OgURL);
    }

    /**This method returns the og:url meta tag  content attribute value on Journal page
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

    /**This method is used to check og:site_name meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean ogSiteNameMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> OgSiteName = driver.findElements(By.xpath("//meta[@property='og:site_name']"));
        return isElementPresent(OgSiteName);
    }

    /**This method returns the og:site_name meta tag content attribute value on Journal page
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

    /**This method is used to check og:type meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean ogTypeMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> ogType = driver.findElements(By.xpath("//meta[@property='og:type']"));
        return isElementPresent(ogType);
    }

    /**This method returns the og:type meta tag content attribute value on Journal page
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

    /**This method is used to check og:locale meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean ogLocaleMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> ogLocale = driver.findElements(By.xpath("//meta[@property='og:locale']"));
        return isElementPresent(ogLocale);
    }

    /**This method returns the og:locale meta tag content attribute value on Journal page
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

    /**This method is used to check og:image meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean ogImageMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> ogImage = driver.findElements(By.xpath("//meta[@property='og:image']"));
        return isElementPresent(ogImage);
    }

    /**This method returns the og:image meta tag content attribute value on Journal page
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

    /**This method is used to check twitter:card meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean VerifyTwitterCardMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> twitterCard = driver.findElements(By.xpath("//meta[@property='twitter:card']"));
        return isElementPresent(twitterCard);
    }

    /**This method returns the twitter:card meta tag content attribute value on Journal page
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

    /**This method is used to check twitter:title meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean VerifyTwitterTitleMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> twitterTitle = driver.findElements(By.xpath("//meta[@property='twitter:title']"));
        return isElementPresent(twitterTitle);
    }

    /**This method returns the twitter:title meta tag content attribute value on Issue page
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

    /**This method is used to check og:description meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean VerifyOgDescriptionMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> ogDescription = driver.findElements(By.xpath("(//meta[@property='og:description'])[1]"));
        return isElementPresent(ogDescription);
    }

    /**This method returns the og:description meta tag content attribute value on Journal page
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

    /**This method is used to check og:description meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean VerifyDescriptionMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> Description = driver.findElements(By.xpath("(//meta[@name='description'])[1]"));
        return isElementPresent(Description);
    }

    /**This method returns the og:description meta tag content attribute value on Issue page
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

    /**This method is used to check twitter:description meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean VerifyTwitterDescriptionMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> twitterDescription = driver.findElements(By.xpath("//meta[@property='twitter:description']"));
        return isElementPresent(twitterDescription);
    }

    /**This method returns the twitter:description meta tag content attribute value on Journal page
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

    /**This method is used to check og:title meta tag is present on Journal page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/08/2024
     */
    public boolean VerifyOgTitleMetaTagisPresentOnJournalPage() throws Exception {
        List<WebElement> ogTitle = driver.findElements(By.xpath("//meta[@property='og:title']"));
        return isElementPresent(ogTitle);
    }

    /**This method returns the og:title meta tag content attribute value on Journal page
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
    
    

    
    @FindBy(xpath = "//span[text()='Archive']")
    private WebElement journalpageHeader;
    @FindBy(xpath = "(//div[contains(text(),'Volume 70')])[1]//parent::button[@aria-expanded='false']")
    private WebElement volumeLinkCollapse;
    @FindBy(xpath = "(//div[contains(text(),'Volume 70')])[1]//parent::button[@aria-expanded='true']")
    private WebElement volumeLinkExpand;
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
    @FindBy(xpath = "(//meta[@name='description'])[1]")
    private WebElement descriptionMetaTag;
    @FindBy(xpath = "//meta[@property='twitter:description']")
    private WebElement twitterDescriptionMetaTag;
    @FindBy(xpath = "//meta[@property='og:title']")
    private WebElement ogTitleMetaTag;
}
