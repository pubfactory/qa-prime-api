package com.prime.pageFactory.pages.fpj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

public class ArticleDetailsPage extends BasePage {

    /**
     * This constructor initializes the MasterPage class object
     * 
     * @param WebDriver
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public ArticleDetailsPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForDocumentReady();
    }



    /**
     * This method used to check if Full Text tab is enabled
     * 
     * @param title
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 27-09-23
     */
    public void verifyFullTextTabOnArticleDetailsPage() throws Exception {

        clickOnElement(fullTextTab, "Verifying if full text tab is enabled");
    }

    @FindBy(xpath = "//button[contains(text(),'Full Text')]")
    private WebElement fullTextTab;
}
