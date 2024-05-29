package com.prime.pageFactory.pages.fpj;

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
        List<WebElement> issue = driver.findElements(By.xpath("((//span[contains(text(),'"+volumeValue+"')])[1]//following::a[contains(text(),'Issue 1')])[1]"));
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

    @FindBy(xpath = "//span[text()='Archive']")
    private WebElement journalpageHeader;
    @FindBy(xpath = "(//div[contains(text(),'Volume 70')])[1]//parent::button[@aria-expanded='false']")
    private WebElement volumeLinkCollapse;
    @FindBy(xpath = "(//div[contains(text(),'Volume 70')])[1]//parent::button[@aria-expanded='true']")
    private WebElement volumeLinkExpand;
}
