package com.prime.pageFactory.pages.fpj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;



public class JournalPage extends BasePage {



    /**
     * This constructor initializes the signupPage class object
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

    @FindBy(xpath = "//span[text()='Anesthesia Progress']")
    private WebElement journalpageHeader;

}
