package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.runtime.directive.Parse;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;

public class ArticleCitationPage extends BasePage {

    /**
     * This constructor initializes the ArticleCitationPage class object
     * 
     * @param WebDriver
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public ArticleCitationPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForDocumentReady();
    }

    /**
     * This method is used to check citation button is present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 07/07/2023
     */
    public boolean verifyCitationButtonPresentOnArticlePage() throws Exception {
        List<WebElement> cite = driver.findElements(By.xpath("(//a[contains(text(),'Get Permissions')])[1]//following-sibling::button"));
        return isElementPresent(cite);
    }

    /**
     * This method is used to click on the citation on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public void clickOnCitationButtonOnArticlePage() throws Exception {
        clickOnElement(citationButton, "Clicking on citation button on Article Page");
    }

    /**
     * This method return the RIS button text which is available on Preview/Export
     * Citation Popup
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getRISButtonTextonCitationPopup() throws Exception {
        String actualRISButtonText = getTextFromElement(RISButton);
        return actualRISButtonText;
    }

    /**
     * This method return the BIB button text which is available on Preview/Export
     * Citation Popup
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getBIBButtonTextonCitationPopup() throws Exception {
        String actualButtonText = getTextFromElement(BIBButton);
        return actualButtonText;
    }

    /**
     * This method return the ENW button text which is available on Preview/Export
     * Citation Popup
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getENWButtonTextonCitationPopup() throws Exception {
        String actualButtonText = getTextFromElement(ENWButton);
        return actualButtonText;
    }

    /**
     * This method return the Export Citation Format Labels which is available on
     * Preview/Export Citation Popup
     * 
     * @param buttontext
     * @return List<String>
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/07/2023
     */
    public List<String> getExportCitationFormatLabels(String buttontext) throws Exception {
        List<String> actualList = new ArrayList<String>();
        By locator = By.xpath("//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button[contains(text(),'" + buttontext + "')]//parent::div//div//p");
        actualList = getTextFindElements(locator);
        // String[] exptdArr = extdText.split(",");
        // List<String> exptdList = Arrays.asList(exptdArr);
        // for(int i=0;i<exptdList.size();i++) {
        // assertExpectedActualValue(exptdList.get(i),actualList.get(i), "checking the
        // Export Citation Format text");
        // }
        return actualList;
    }

    /**
     * This method return the Preview Export Citation Popup Header text
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getPreviewExportCitationPopUpHeaderText() throws Exception {
        String citationPopUpHeaderText = getTextFromElement(PreviewExportCitationPopUp);
        return citationPopUpHeaderText;
    }

    /**
     * This method is used to check Preview Export Citation PopUp Close button is
     * present on Preview Export Citation PopUp
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 07/07/2023
     */
    public boolean verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp() throws Exception {
        List<WebElement> closebutton = driver.findElements(By.xpath("//header[contains(text(),'Preview/Export Citation')]//following-sibling::button[@aria-label='Close']"));
        return isElementPresent(closebutton);
    }

    /**
     * This method used to close the Preview Export Citation PopUp
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public void clickOnPreviewExportCitationPopUpCloseButton() throws Exception {
        clickOnElement(PreviewExportCitationPopUpCloseButton, "Clicking on Close button on Preview Export Citation popup");
    }

    /**
     * This method used to select the format value from dropdown on Preview Export
     * Citation PopUp
     * 
     * @param value
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public void selectFormatValueOnPreviewExportCitationPopUp(String value) throws Exception {
        selectByValue(citationFormatDropdown, value, "Selecting the value " + value + " from Format dropdown on Preview Export Citation PopUp");
    }

    /**
    * This method return the Preview Citation Section text which is available on
     * ciatatin pop up
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getPreviewCitationSectionTextOnPreviewExportCitationPopUp() throws Exception {
        String PreviewCitationSectiontext = getTextFromElement(PreviewCitationSection);
        return PreviewCitationSectiontext;
    }

    /**
     * This method return the Export Citation Section text which is available on
     * ciatatin pop up
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getExportCitationSectionTextOnPreviewExportCitationPopUp() throws Exception {
        String ExportCitationSectiontext = getTextFromElement(ExportCitationSection);
        return ExportCitationSectiontext;
    }

    /**
     * This method return the Abbreviated Journal Title text on Export citation
     * popup while selecting the AMA format
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/07/2023
     */
    public String getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingFormat() throws Exception {
        String actualTitle = getTextFromElement(abbreviatedJournalTitle);
        return actualTitle;
    }

    /**
     * This method used to click on the open access or restricted access article on
     * article page
     * 
     * @param contentType
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public void clickArticleOnArticlePagewrtContentType(String accessType) throws Exception {
        List<WebElement> Contentlist = driver.findElements(By.xpath("//span[@title='" + accessType + "']//preceding-sibling::span//parent::span//parent::div//preceding-sibling::div//h6"));
        clickOnElement(Contentlist.get(0), "Clicking on content type " + accessType + " on article page");
    }

    /**
     * This method used for downloading the Export Citation in RIS format on Preview
     * Export Citation PopUp
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public void clickOnRISExportCitationFormat() throws Exception {
        clickOnElement(RISButton, "clicking on .RIS button under Export citation section on Preview Export Citation PopUp");
    }

    /**
     * This method used to click on Tools button on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnToolsButtonOnArticlePage() throws Exception {
        clickOnElement(toolsButton, "clicking on tools button on Article Page");
    }


    /**
     * This method used for downloading the Export Citation in BIB format on Preview
     * Export Citation PopUp
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public void clickOnBIBExportCitationFormat() throws Exception {
        clickOnElement(BIBButton, "clicking on .BIB button under Export citation section on Preview Export Citation PopUp");
    }

    /**
     * This method used for downloading the Export Citation in ENW format on Preview
    * Export Citation PopUp
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public void clickOnENWExportCitationFormat() throws Exception {
        clickOnElement(ENWButton, "clicking on .ENW button under Export citation section on Preview Export Citation PopUp");
    }

    /**
     * This method return the Citation Button ToolTip text on the article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public String getCitationButtonToolTip() throws Exception {
        String ciationButtontootipText = getToolTipText(By.xpath("//button[@title='Cite']"), "title");
        return ciationButtontootipText;
    }

    /**
     * This method used to check Export Citation RIS Button is present under Export
     * citation section on Preview Export Citation PopUp
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public boolean verifyRISButtonIsPresentOnPreviewExportCitationOnPopup() throws Exception {
        List<WebElement> RisButton = driver.findElements(By.xpath("//button[contains(text(),'.ris')]"));
        return isElementPresent(RisButton);
    }

    /**
     * This method used to check Export Citation BIB Button is present under Export
     * citation section on Preview Export Citation PopUp
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public boolean verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup() throws Exception {
        List<WebElement> bibButton = driver.findElements(By.xpath("//button[contains(text(),'.bib')]"));
        return isElementPresent(bibButton);
    }

    /**
     * This method used to check Export Citation ENW Button is present under Export
     * citation section on Preview Export Citation PopUp
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 10/07/2023
     */
    public boolean verifyENWButtonIsPresentOnPreviewExportCitationOnPopup() throws Exception {
        List<WebElement> enwbButton = driver.findElements(By.xpath("//button[contains(text(),'.enw')]"));
        return isElementPresent(enwbButton);
    }

    /**
     * This method used to fetch the latest downloaded file name
     *
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 13/07/2023
     */
    public String getLatestDownloadFileRelatedToCitation(String extensionName) throws Exception {
        String citationFileName = fetchLatestDownloadFileWithExtension(extensionName);
        System.out.println("citationFileName : "+citationFileName);
//        String str1 = fileName.substring(0, 22);
        String citationFileName1 = citationFileName.substring((citationFileName.length()) - 4, citationFileName.length());
 //       String updateFileName = str1.concat(str2);
        return citationFileName1;
    }
    
    /**
     * This method used to verify file is downloaded
     *
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 28/12/2023
     */
    public boolean toVerifyCitationFormatFileIsDownload(String extensionName) throws Exception {
    	
    	return verifyFileISDowloadedwithExtension(extensionName);
    }

    /**
     * This method return Article Header text on the article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 27/09/2023
     */
    public String getArticleHeaderOnArticlePage() throws Exception {
        String articleHeaderText = getTextFromElement(articleHeader);
        return articleHeaderText;
    }

    /**
     * This method return Author affiliation popup label
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/10/2023
     */
    public String getauthorAffiliationPopupLabel() throws Exception {
        String authorAffiliationPopupLabelText = getTextFromElement(authorAffiliationPopupLabel);
        return authorAffiliationPopupLabelText;
    }


    /**
     * This method is used to check citation button is present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 07/07/2023
     */
    public boolean verifyCitationButtonNotPresentOnArticlePage() throws Exception {
        return isElementNotPresentWithTimeOut(citationButton, 7, "checking the cite Button is not present on Article Page");
    }

    /**
     * This method is used to check citation button is present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 07/07/2023
     */
    public boolean verifyCitationButtonPresentOnArticlePageWithTimeOut() throws Exception {
        return isElementPresentWithTimeOut(citationButton, 7, "checking the cite Button is present on Article Page");
    }

    /**
     * This method used to clicking Journal cover on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/07/2023
     */
    public void clickOnJournalCoverOnArtcilePage() throws Exception {
        clickOnElement(journalCover, "clicking Journal cover on article page");
    }

    /**
     * This method used clicks on first Author hyper link
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/q0/2023
     */
    public void clickFirstAuthorOnArticlePage() throws Exception {
        clickOnElement(firstauthor, "clicking first Author hyperlink on article page");
    }

    /**
     * This method used to click on share via email button on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnshareViaEmailButtonOnArticlePage() throws Exception {
        clickOnElement(shareViaEmailButton, "clicking on share via email button on Article Page");
    }

    /**
     * This method is used to check share via Email button is present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return
     * @Created Date : 01/11/2023
     */
    public boolean verifyShareViaEmailButtonPresentOnArticlePage() throws Exception {
        List<WebElement> email = driver.findElements(By.xpath("(//button[text()='Share via Email'])[1]"));
        return isElementPresent(email);
    }

    /**
     * This method return share link Popup header available on Article page
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/11/2023
     */
    public String getShareLinkPopupHeader() throws Exception {
        String shareLinkText = getTextFromElement(shareLinkPopupHeader);
        return shareLinkText;
    }

    /**
    * This method used to clicks email this content button on Share link popup on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnEmailThisContentButtonOnShareLinkOnArticlePage() throws Exception {
        clickOnElement(emailThisContentButton, "clicking on Email This content button on share Link popup on Article Page");
    }

    /** 
     * This method used to clicks on copy link button on Share link popupon article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnCopyLinkButtonOnShareLinkOnArticlePage() throws Exception {
        clickOnElement(copyLinkButton, "clicking on copy link button on share Link popup on Article Page");
    }

    /**
     * This method returns copy link direct on share link Popup on article page
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 01/11/2023
     */
    public String getCopyLinkDirectlyOnShareLinkPopupOnArticlePage() throws Exception {
        //     String directlyCopyLink = getTextFromElement(copyLinkDirectly);

        String textagain = copyLinkDirectly.getAttribute("value");
        return textagain;
    }

    /**
     * This method is used to check popup close button is present on share link popup on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 01/11/2023
     */
    public boolean verifyShareLinkPopUpCloseButtononPresentOnArticlePage() throws Exception {
        List<WebElement> closeButton = driver.findElements(By.xpath("(//button[text()='Share via Email'])[1]"));
        return isElementPresent(closeButton);
    }

    /** 
     * This method used to clicks on share link popup close button on Share link popup on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnShareLinkPopupClosekButtonOnShareLinkOnArticlePage() throws Exception {
        clickOnElement(shareLinkPopupCloseButton, "clicking on shareLink pop up close  button on share Link popup on Article Page");
    }

    /**
     * This method is returns list of Author available on author affiliation block on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return List<String>
     * @Created Date : 01/11/2023
     */
    public List<String> getAllAuthorNamesFromAuthorAffiliationBlockOnArticlePage() throws Exception {
        List<String> author = getTextFindElements(By.xpath("//div[@data-testid='contributors']//button//span"));
        return author;
    }

    /** 
     * This method used to clicks on google scholar button on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnGoogleScholarkButtonOnArticlePage() throws Exception {
        clickOnElement(googleScholarButton, "clicking on google scholar button on Article Page");
    }

    /** 
     * This method used to clicks on PubMed button on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 1/11/2023
     */
    public void clickOnPubMedButtonOnArticlePage() throws Exception {
        clickOnElement(pubMedButton, "clicking on PubMed button on Article Page");
    }

    /**
     * This method is returns list of Author available on Google scholar section on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 01/11/2023
     */
    public List<String> getAllAuthorNamesFromAuthorGoogleScholarSectionOnArticlePage() throws Exception {
        List<String> authorsText = getTextFindElements(By.xpath("(//div[@data-testid='GoogleScholarWidget-container'])[1]//li//a"));
        return splitStringAndRetunsList(authorsText);
    }

    /**
     * This method is returns list of Author available on PubMed section on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 01/11/2023
     */
    public List<String> getAllAuthorNamesFromPubMedSectionOnArticlePage() throws Exception {
        List<String> authorsText = getTextFindElements(By.xpath("(//div[@data-testid='PubMedWidget-container'])[1]//li//a"));
        return splitStringAndRetunsList(authorsText);

    }


    /**
     * This method is used to check Altmetric Badge is present on right hand side on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 01/11/2023
     */
    public boolean verifyAltmetricBadgePresentOnArticlePage() throws Exception {
        List<WebElement> altmtricBadge = driver.findElements(By.xpath("(//img[contains(@alt,'Article has an altmetric score')])[1]"));
        return isElementPresent(altmtricBadge);
    }


    /**
     * This method is used to check Similar article hyperLink is present in google scholar section on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 01/11/2023
     */
    public boolean verifySimilarArtcileHyperLinkPresentOnArticlePage() throws Exception {
        List<WebElement> simlilarArticle = driver.findElements(By.xpath("(//a[text()='Similar articles in Google Scholar'])[1]"));
        return isElementPresent(simlilarArticle);
    }

    /**
     * This method is used to check link copied message displayed on share link popup on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 02/11/2023
     */
    public boolean verifyLinkCopiedSuccessfullyMessageDisplayedOnPopupOnArticlePage() throws Exception {
        //        List<WebElement> linkCopiedMessage = driver.findElements(By.xpath("//button[text()='Copy link'][@aria-expanded='true']"));
        //        return isElementPresent(linkCopiedMessage);
        String javascript = "document.getElementById('popover-body-:r42j:')";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = (WebElement) jsExecutor.executeScript(javascript);
        return element.isDisplayed();
        //            String msgName = getTextFromElement(element);
        //            return msgName;
    }

    /**
     * This method is used to check popup close button is present on share link popup on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 02/11/2023
     */
    public boolean verifyShareLinkPopUpIsNotPresentOnArticlePageAfterClickingCloseButton() throws Exception {
        List<WebElement> shareLink = driver.findElements(By.xpath("//header[text()='Share Link']"));
        return isElementNotPresent(shareLink);
    }

    public boolean hoverOnShareViaEmailButton() throws Exception {
        return mouseOver(shareViaEmailButton, "");
    }



    /**
     * This method is used to check Google Scholar is present on right hand side on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 03/11/2023
     */
    public boolean verifyGoogleScholarButtonPresentOnArticlePage() throws Exception {
        List<WebElement> googleScholar = driver.findElements(By.xpath("(//button[text()='Google Scholar'])[1]"));
        return isElementPresent(googleScholar);
    }

    /**
     * This method is used to check PubMed is present on right hand side on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 06/11/2023
     */
    public boolean verifyPubmedButtonPresentOnArticlePage() throws Exception {
        List<WebElement> pubmed = driver.findElements(By.xpath("(//button[text()='PubMed'])[1]"));
        return isElementPresent(pubmed);
    }

    /**
     * This method is used to click on Similar Article in Google Shcolar HyperLink in Google Shcolar section on Article Page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/11/2023
     */
    public void clickOnSimilarArticleInGoogleScholarHyperLink() throws Exception {
        clickOnElement(similarArticleInGoogleScolar, "Clicking on Similar Article in Google Shcolar HyperLink in Google Shcolar section on Article Page");
    }

    /**
     * This method is used to check Article Element In Google scholar tab is presented When Click on Similar Article in Google Scholar HyperLink
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 03/11/2023
     */
    public boolean verifyArticleElementInGoogleScholarTabIsPresenstedWhenClickOnSimilarArticleInGoogleScholarHyperLink() throws Exception {
        List<WebElement> articleElement = driver.findElements(By.xpath("(//span[text()='Articles'])[1]"));
        return isElementPresent(articleElement);
    }

    /** 
     * This method used to clicks on First Author in Google Scholar Section on Article Page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 3/11/2023
     */
    public void clickOnFirstAuthorInGoogleScholarSectionOnArticlePage() throws Exception {
        clickOnElement(firstAuthorInGoogleSection, "clicking on first author in google scholar on Article Page");
    }

    /** 
     * This method used to clicks on First Author in PubMed Section on Article Page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 6/11/2023
     */
    public void clickOnFirstAuthorInPubMedSectionOnArticlePage() throws Exception {
        clickOnElement(firstAuthorInPubMedSection, "clicking on first author in pubmed on Article Page");
    }

    /**
     * This method is returns first Author available on Google scholar section on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 03/11/2023
     */
    public String getFirstAuthorNamesFromGoogleScholarSectionOnArticlePage() throws Exception {
        WebElement authorsText = driver.findElement(By.xpath("((//div[@data-testid='GoogleScholarWidget-container'])[1]//li//a)[1]"));
        return splitStringandReturnString(getTextFromElement(authorsText));
    }

    /**
     * This method is returns first Author available on pubMed section on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/11/2023
     */
    public String getFirstAuthorNamesFromPubMedSectionOnArticlePage() throws Exception {
        //       WebElement authorsText = driver.findElement(By.xpath("((//div[@data-testid='GoogleScholarWidget-container'])[1]//li//a)[1]"));
        return splitStringandReturnString(getTextFromElement(firstAuthorInPubMedSection));
    }

    /** 
     * This method used to clicks on tools button in action bar on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 20/11/2023
     */
    public void clickOnToolsButtonInActionBarOnArticlePage() throws Exception {
        clickOnElement(toolsButton, "clicking on tools button in action bar on article page");
    }
    
    /** 
     * This method used to verify the DOI metadata tag name return true or false
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/01/2024
     */
    public boolean verifyDOIMetaDataIsLink() {
    	return VerifyLinkIsPresent(DOIMetaData, "Verying DOI Meta data tag name and return true or false");
    }
    
    /** 
     * This method used to clicks on DOI Meta data on article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/01/2024
     */
    public void clickOnDOIMETADataOnArticlePage() throws Exception {
    	clickOnElement(DOIMetaData, "Clicking On DOI Meta Data On article page");
    }
    
    /**
     * This method is returns DOI meta data text on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @return 
     * @Created Date : 16/01/2024
     */
    public String getDOIMetaDataOnMeridianAllenPressSite() throws Exception {
    	String DOIAllen=getTextFromElement(DOIMetaDataOnMeridianAllenPress);
    	return DOIAllen;
    }

    /**
     * This method is returns DOI meta data text on Meridian AllenPress site
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 16/01/2024
     */
    public String getDOIMetaDataOnArticlePage() throws Exception {
    	String DOI=getTextFromElement(DOIMetaData);
    	return DOI;
    }
    
    /**
     * This method is used to checks Copy to ClipBoard button is present on preview Export Citation popup
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 29/05/2024
     */
    public boolean verifyCopyToClipBoardButtonIsPresentOnPreviewExportCitationPopup() throws Exception {
        List<WebElement> copyClipBoard = driver.findElements(By.xpath("//button[text()='Copy to clipboard']"));
        return isElementPresent(copyClipBoard);
    }

    /**
     * This method is used to click on the citation on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 29/05/2024
     */
    public void clickOnCopyToClipBoardButtonOnPreviewExportCitationPopup() throws Exception {
        clickOnElement(copyToClipboard, "Clicking on Copy to ClipBoard button on Preview Export Citation Popup");
    }
    
    /**
     * This method is used to checks Copy to ClipBoard button is present on preview Export Citation popup
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 29/05/2024
     */
    public boolean verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButton() throws Exception {
        List<WebElement> toastMessage = driver.findElements(By.xpath("(//div[@data-status='success'])[1]"));
        return isElementPresent(toastMessage);
    }
    
    /**
     * This method is used to checks Copy to ClipBoard button is present on preview Export Citation popup in headless ,ode
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 26/06/2024
     */
    public boolean verifyChakraToastMessageIsDisplayedAfterClickingOnCopyToClipBoardButtonInHeadless() throws Exception {
		List<WebElement> toastMessage1 =null;
		LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
		System.out.println("logs : "+logs);
		for (LogEntry log : logs) {
			if (log.getMessage().contains("Clipboard")) {
				System.out.println("Toast message detected in logs!");								
				toastMessage1 = driver.findElements(By.xpath("(//div[contains(@id,'toast-')])[1]"));	
				break;
			}
		}
		return isElementPresent(toastMessage1);
    }
    
    /**
     * This method is used to checks the Selected citation format is displayed on chakra toast message
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 29/05/2024
     */
    public boolean verifySelectedCitationFormatIsDisplayedInToastMessage(String formattype) throws Exception {
        List<WebElement> formatType = driver.findElements(By.xpath("//div[text()='"+formattype+" Preview Copied.']"));
        return isElementPresent(formatType);
    }
    
    /**
     * This method is used to clicks on close button of toast message popup
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 30/05/2024
     */
    public void clickOnToastMessagePopupCloseButton() throws Exception {
        clickOnElement(toastMessageCloseButton, "Clicking on the close button of toast message popup");
    }
    
    /**
     * This method used to Verify the Citations link is present in google scholar section on Article page
     *
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 06/06/2024
     */
    public boolean verifyCitationsLinkIsPresentInGoofglrScholarSectionOnArticlePage() throws Exception {
        List<WebElement> citationlink = driver.findElements(By.xpath("//a[text()='Citations']"));
        return isElementPresent(citationlink);
    }
    
    /**
     * This method is used to clicks on Citations link in google scholar section on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 30/05/2024
     */
    public void clickOnCitationsLinkInGoogleScholarSectionOnArticlePage() throws Exception {
        clickOnElement(citationsLink, "Clicking on Citations link in google scholar section on article page");
    }
    
    /**
     * This method is used to remove unwanted string from URL and return String format URL
     * 
     * @param s
     * @return String
     * @author Rakesh.Shevale
     * @Created Date : 07/06/2024
     */
    public String removeContainFromURL(String s) {
        if (s.contains("q=link:")) {
            String[] a = s.split("q=link:");
            String mainURL= a[1].toString();
                return mainURL;            
        } else {
            return s;
        }
    }
    
    /**
     * This method is used to clicks on Abstract or full text tab on article page
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 12/06/2024
     */
    public void clickOnFullTextOrAbstractTabOnArticlePage() throws Exception {
	   List<WebElement> fullTextList = driver.findElements(By.xpath("//button[text()='Full Text']"));
	   if(fullTextList.size()>0) {
		   WebElement fullTextTab = driver.findElement(By.xpath("//button[text()='Full Text']"));
		   clickOnElement(fullTextTab, "Clicking on fullText tab on article page");
	   }
	   else {
		   WebElement abstractTab = driver.findElement(By.xpath("//button[text()='Abstract']"));
		   clickOnElement(abstractTab, "Clicking on Abstract tab on article page");
	   }
    }
    
   /** This method return the background color of element
    * 
    * @return String
    * @author Rakesh.Shevale
    * @created Date : 12/06/24
    */
    public String getBackgroundColor(String searchKeyword) {
    	WebElement element = driver.findElement(By.xpath("((//div[@class='abstract'])[1]//span[@class='hi' ][contains(text(),'"+searchKeyword+"')])[1]"));
        String backgroundColor = element.getCssValue("background-color");
        return backgroundColor;
    }
    
    /**
     * This method is used to verifying the search keyword is displayed as hit highlighted 
     * 
     * @param searchKeyword
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
    * @created Date : 12/06/24
     */
    public boolean verifySearchKeywordIsNotHitHighlighted(String searchKeyword) throws Exception {
       	 List<WebElement> element = driver.findElements(By.xpath("//span[@class='hi'][contains(text(),'"+searchKeyword+"')]"));
           return isElementNotPresent(element);
        }
    
    /**
     * This method return the Number of Citation text which is available on Article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public String getNumberOfCitationTextOnArticlePae() throws Exception {
        String NumberOfCitationText = getTextFromElement(NumberOfCitationWebElement);
        return NumberOfCitationText;
    }
    
    /**
     * This method return the Number of Citation count which is available on Article page
     * 
     * @return int
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public int getCountOfNumberOfCitationOnArticlePage() throws Exception {
        String CountOfNumberOfCitation = getTextFromElement(countOfNumberOfCitation);
        return Integer.parseInt(CountOfNumberOfCitation);
        
    }
    
    /**
     * This method returns Cited by text on Article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public String getCitedByTextOnArticlePage() throws Exception {
        String citedByElementText = getTextFromElement(citedByElement);
        return citedByElementText;
    }
    
    /**
     * This method is used to click in the cited by element on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public void clickOnCitedByElementOnArticlePage() throws Exception {
        clickOnElement(citedByElement, "Clicking on cited by element on Article Page");
    }
    
    /**
     * This method is used to checks not provided ascending number before the each citation
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/06/2024
     */
    public boolean verifyCitationIndexIsNotPresentInCitedByOnArticlePage() throws Exception {
        List<WebElement> citationIndex = driver.findElements(By.xpath("//span[@class='citationIndex']"));
        return isElementNotPresent(citationIndex);
    }
    
    /**
     * This method is used to checks the pagination style will be a string of numbers.
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 17/06/2024
     */
    public boolean verifyPaginationStyleWillBeAStringOfNumbersInCitedByOnArticlePage() throws Exception {
        List<WebElement> secondPagination = driver.findElements(By.xpath("(//li[contains(@data-testid,'pagination-page-index-2')])[1]"));
        return isElementPresent(secondPagination);
    }
    
    /**
     * This method is used to click 2nd pagination link in Cited by element on article page.
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public void clickOnSecondPaginationLinkInCitedByElementOnArticlePage() throws Exception {
    	WebElement secondPagination = driver.findElement(By.xpath("((//span[text()='Page:'])[1]//following::li[text()='2'])[1]"));   	  
        clickOnElement(secondPagination, "Clicking on second pagination link in cited by element on Article Page");
    }
    
    /**
     * This method used to returns the total citation result in Cited By element On Article Page
     * 
     * @return int
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public int getAppropriateCitationResultCountInCitedByElementOnArticlePage() throws Exception {
    	List<WebElement> resultCount = driver.findElements(By.xpath("(//a[@target='_blank' and text()='Crossref'])"));
        return resultCount.size();
    }
    
    /**
     * This method used to checks the pagInation links is present in cited by element on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 17/06/2024
     */
    public boolean verifyPagInationLinkNumberIsPresentInCitedByElement() throws Exception {
        List<WebElement> Pagination = driver.findElements(By.xpath("(//li[@class='css-lkygn'])[1]"));
        return isElementPresent(Pagination);
    }
    
    /**
     * This method used to returns the pagInation number in Cited By element On Article Page
     * 
     * @return int
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/06/2024
     */
    public int getPagInationLinkNumberInCitedByElement() throws Exception {
    	String pagInationNumber=getTextFromElement(pagInationLinkNumber);
    	return Integer.parseInt(pagInationNumber);
    }
    
    
    /**
     * This method returns Default You are looking for text in Cited by element on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/06/2024
     */
    public String getDefaultYouAreLookingForTextInCitedByElement() throws Exception {
    	WebElement youAreLooking=driver.findElement(By.xpath("//p[text()='Publications Citing This Document']//following-sibling::p"));
    	return getTextFromElement(youAreLooking);   	
    }
    
    
    /**
     * This method returns the starting or ending result number of You are looking text in cited by element
     * 
     * @param StartEndResultNumber
     * @return int
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/06/2024
     */
    public int getStartingAndEndingResultTextOfYouAreLookingInCitedByElement(int StartEndResultNumber) throws Exception {
    	if(verifyPagInationLinkNumberIsPresentInCitedByElement()==true) {
    		int PagInationLinkNumber = getPagInationLinkNumberInCitedByElement();
    		int totalAllResultUptoPagInationLinkNumbe=(PagInationLinkNumber-1)*20 + StartEndResultNumber;
    		System.out.println("totalAllResultUptoPagInationLinkNumbe : "+totalAllResultUptoPagInationLinkNumbe);
    		return totalAllResultUptoPagInationLinkNumbe;
    	}
    	else {
    		System.out.println("totalAllResultUptoPagInationLinkNumbe : "+StartEndResultNumber);
    		return StartEndResultNumber;
    	}
    }
    
    /**
     * This method returns crossRef text in Cited by element on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/06/2024
     */
    public String getCrossRefTextInCitedByElement() throws Exception {
       	return getTextFromElement(crossRefLink);   	
    }
    
    /**
     * This method is used to clicks rossRef link in Cited by element on article page.
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/06/2024
     */
    public void clickOnCrossRefLinkInCitedByElementOnArticlePage() throws Exception {
           clickOnElement(crossRefLink, "Clicking on crossRef link in cited by element on Article Page");
    }
    
    /**
     * This method used to checks the DOI user to the citing article is present in a new browser tab after clicking on crossref link.
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 18/06/2024
     */
    public boolean verifyDOIUserIsPresentInNewTab() throws Exception {
        List<WebElement> doiUser = driver.findElements(By.xpath("(//a[contains(@href,'doi.org')])[1]"));
        return isElementPresent(doiUser);
    }
    
    /**
     * This method used to checks the pagInation in top of cited by element is present on article page.
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/06/2024
     */
    public boolean verifyPagInationInTopInCitedByElementIsPresentOnArticlePage() throws Exception {
        List<WebElement> paginationTop = driver.findElements(By.xpath("(//span[text()='Page:'])[1]"));
        return isElementPresent(paginationTop);
    }
    
    /**
     * This method used to checks the pagInation in bottom of cited by element is present on article page..
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 19/06/2024
     */
    public boolean verifyPagInationInBottomInCitedByElementIsPresentOnArticlePage() throws Exception {
        List<WebElement> paginationBottom = driver.findElements(By.xpath("(//span[text()='Page:'])[2]"));
        return isElementPresent(paginationBottom);
    }
    
    /**This method is used to check og:url meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean ogURLMetaTagisPresentOnArticlePage() throws Exception {
    	Thread.sleep(4000);
    	List<WebElement> OgURL=driver.findElements(By.xpath("//meta[@property='og:url']"));
    	 return isElementPresent(OgURL);
    }
    
    /**This method returns the og:url meta tag  content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgURLMetaTagPropertyValue() throws Exception {
    	String URL=getMetaTagAttribute(ogURLMetaTag,"content");
    	return URL;
    }
  
    /**This method is used to check og:site_name meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean ogSiteNameMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> OgSiteName=driver.findElements(By.xpath("//meta[@property='og:site_name']"));
    	 return isElementPresent(OgSiteName);
    }
    
    /**This method returns the og:site_name meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgSiteNameMetaTagPropertyValue() throws Exception {
    	String siteName=getMetaTagAttribute(ogSiteNameMetaTag,"content");
    	return siteName;    	
    }
    
    /**This method is used to check og:type meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean ogTypeMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> ogType=driver.findElements(By.xpath("//meta[@property='og:type']"));
    	 return isElementPresent(ogType);
    }
    
    /**This method returns the og:type meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgTypeMetaTagPropertyValue() throws Exception {
    	String ogType=getMetaTagAttribute(ogTypeMetaTag,"content");
    	return ogType;    	
    }
    
    /**This method is used to check og:locale meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean ogLocaleMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> ogLocale=driver.findElements(By.xpath("//meta[@property='og:locale']"));
    	 return isElementPresent(ogLocale);
    }
    
    /**This method returns the og:locale meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgLocaleMetaTagPropertyValue() throws Exception {
    	String ogLocale=getMetaTagAttribute(ogLocaleMetaTag,"content");
    	return ogLocale;    	
    }
    
    /**This method is used to check og:image meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean ogImageMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> ogImage=driver.findElements(By.xpath("//meta[@property='og:image']"));
    	 return isElementPresent(ogImage);
    }
    
    /**This method returns the og:image meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgImageMetaTagPropertyValue() throws Exception {
    	String ogImage=getMetaTagAttribute(ogImageMetaTag,"content");
    	return ogImage;    	
    }
    
    /**This method is used to check twitter:card meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyTwitterCardMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> twitterCard=driver.findElements(By.xpath("//meta[@property='twitter:card']"));
    	 return isElementPresent(twitterCard);
    }
    
    /**This method returns the twitter:card meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getTwitterCardMetaTagPropertyValue() throws Exception {
    	String twitterCard=getMetaTagAttribute(twitterCardMetaTag,"content");
    	return twitterCard;    	
    }
    
    /**This method is used to check twitter:title meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyTwitterTitleMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> twitterTitle=driver.findElements(By.xpath("//meta[@property='twitter:title']"));
    	 return isElementPresent(twitterTitle);
    }
    
    /**This method returns the twitter:title meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
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
     * @Created Date : 06/08/2024
     */
    public boolean VerifyOgDescriptionMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> ogDescription=driver.findElements(By.xpath("(//meta[@property='og:description'])[1]"));
    	 return isElementPresent(ogDescription);
    }
    
    /**This method returns the og:description meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgDescriptionMetaTagPropertyValue() throws Exception {
    	String ogDescription=getMetaTagAttribute(ogDescriptionMetaTag,"content");
    	return ogDescription;    	
    }
    
    /**This method is used to check twitter:description meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyTwitterDescriptionMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> twitterDescription=driver.findElements(By.xpath("//meta[@property='twitter:description']"));
    	 return isElementPresent(twitterDescription);
    }
    
    /**This method returns the twitter:description meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getTwitterDescriptionMetaTagPropertyValue() throws Exception {
    	String twitterDescription=getMetaTagAttribute(twitterDescriptionMetaTag,"content");
    	return twitterDescription;    	
    }
    
    /**This method is used to check og:title meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyOgTitleMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> ogTitle=driver.findElements(By.xpath("//meta[@property='og:title']"));
    	 return isElementPresent(ogTitle);
    }
    
    /**This method returns the og:title meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getOgTitleMetaTagPropertyValue() throws Exception {
    	String ogTitle=getMetaTagAttribute(ogTitleMetaTag,"content");
    	return ogTitle;    	
    }
    
    /**This method is used to check article author meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyArticleAuthorMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> articleAuthor=driver.findElements(By.xpath("(//meta[@property='article:author'])[1]"));
    	 return isElementPresent(articleAuthor);
    }
    
    /**This method returns the article author meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getArticleAuthorMetaTagProperytValue() throws Exception {
    	String articleAuthor=getMetaTagAttribute(articleAuthorMetaTag,"content");
    	return articleAuthor;    	
    }
    
    /**This method is used to check article publish meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyArticlePublishTimeMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> articlePublishTime=driver.findElements(By.xpath("(//meta[@name='article:published_time'])[1]"));
    	 return isElementPresent(articlePublishTime);
    }
    
    /**This method returns the article publish meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getArticlePublishTimeMetaTagPropertyValue() throws Exception {
    	String articlePublishTime=getMetaTagAttribute(articlePublishTimeMetaTag,"content");
    	return articlePublishTime;    	
    }
    
    /**This method is used to check article section meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyArticleSectionMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> articleSection=driver.findElements(By.xpath("(//meta[@name='article:section'])[1]"));
    	 return isElementPresent(articleSection);
    }
    
    /**This method returns the article section meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getArticleSectionMetaTagPropertyValue() throws Exception {
    	String articleSection=getMetaTagAttribute(articleSectionMetaTag,"content");
    	return articleSection;    	
    }
    
    /**This method is used to check citation last page meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationLastPageMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationLastPage=driver.findElements(By.xpath("(//meta[@name='citation_lastpage'])[1]"));
    	 return isElementPresent(citationLastPage);
    }
    
    /**This method returns the citation last page meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationLastPageMetaTagPropertyValue() throws Exception {
    	String citationLastPage=getMetaTagAttribute(citationLastPageMetaTag,"content");
    	return citationLastPage;    	
    }
    
    /**This method is used to check citation issue meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationIssueMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationIssue=driver.findElements(By.xpath("(//meta[@name='citation_issue'])[1]"));
    	 return isElementPresent(citationIssue);
    }
    
    /**This method returns the citation issue meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationIssueMetaTagPropertyValue() throws Exception {
    	String citationIssue=getMetaTagAttribute(citationIssueMetaTag,"content");
    	return citationIssue;    	
    }
    
    /**This method is used to check citation language meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationLanguageMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationLanguage=driver.findElements(By.xpath("(//meta[@name='citation_language'])[1]"));
    	 return isElementPresent(citationLanguage);
    }
    
    /**This method returns the citation language meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationLanguageMetaTagPropertyValue() throws Exception {
    	String citationLanguage=getMetaTagAttribute(citationLanguageMetaTag,"content");
    	return citationLanguage;    	
    }
    
    /**This method is used to check citation title meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationTitleMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationTitle=driver.findElements(By.xpath("(//meta[@name='citation_title'])[1]"));
    	 return isElementPresent(citationTitle);
    }
    
    /**This method returns the citation title meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationTitleMetaTagPropertyValue() throws Exception {
    	String citationTitle=getMetaTagAttribute(citationTitleMetaTag,"content");
    	return citationTitle;    	
    }
    
    /**This method is used to check citation xml url meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationXMLURLMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationXMLURL=driver.findElements(By.xpath("(//meta[@name='citation_xml_url'])[1]"));
    	 return isElementPresent(citationXMLURL);
    }
    
    /**This method returns the citation xml url meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationXMLURLMetaTagPropertyValue() throws Exception {
    	String citationXMLURL=getMetaTagAttribute(citationXMLURLMetaTag,"content");
    	return citationXMLURL;    	
    }
    
    /**This method is used to check citation volume meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean verifyCitationVolumeMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationVolume=driver.findElements(By.xpath("(//meta[@name='citation_volume'])[1]"));
    	 return isElementPresent(citationVolume);
    }
    
    /**This method returns the citation volume meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationVolumeMetaTagPropertyValue() throws Exception {
    	String citationVolume=getMetaTagAttribute(citationVolumeMetaTag,"content");
    	return citationVolume;    	
    }
    
    /**This method is used to check citation PDF URL meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationPDFURLMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationPDFURL=driver.findElements(By.xpath("(//meta[@name='citation_pdf_url'])[1]"));
    	 return isElementPresent(citationPDFURL);
    }
    
    /**This method returns the citation PDF URL meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationPDFURLMetaTagPropertyValue() throws Exception {
    	String citationPDFURL=getMetaTagAttribute(citationPDFURLMetaTag,"content");
    	return citationPDFURL;    	
    }
    
    /**This method is used to check citation ISSN meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationISSNMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationISSN=driver.findElements(By.xpath("(//meta[@name='citation_issn'])[1]"));
    	 return isElementPresent(citationISSN);
    }
    
    /**This method returns the citation ISSN meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationISSNMetaTagPropertyValue() throws Exception {
    	String citationISSN=getMetaTagAttribute(citationISSNMetaTag,"content");
    	return citationISSN;    	
    }
  
    /**This method is used to check citation first page meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationFirstPageMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationFirstPage=driver.findElements(By.xpath("(//meta[@name='citation_firstpage'])[1]"));
    	 return isElementPresent(citationFirstPage);
    }
    
    /**This method returns the citation first page meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationFirstPagePageMetaTagPropertyValue() throws Exception {
    	String citationFirstPage=getMetaTagAttribute(citationFirstPageMetaTag,"content");
    	return citationFirstPage;    	
    }  
    
    /**This method is used to check citation publisher meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationPublisherMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationPublisher=driver.findElements(By.xpath("(//meta[@name='citation_publisher'])[1]"));
    	 return isElementPresent(citationPublisher);
    }
    
    /**This method returns the citation publisher meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationPublisherMetaTagPropertyValue() throws Exception {
    	String citationPublisher=getMetaTagAttribute(citationPublisherMetaTag,"content");
    	return citationPublisher;    	
    } 
    
    /**This method is used to check citation journal title meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationJournalTitleMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationJournalTite=driver.findElements(By.xpath("(//meta[@name='citation_journal_title'])[1]"));
    	 return isElementPresent(citationJournalTite);
    }
    
    /**This method returns the citation journal title meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationJournalTilteMetaTagPropertyValue() throws Exception {
    	String citationJournalTite=getMetaTagAttribute(citationJoutnalTitleMetaTag,"content");
    	return citationJournalTite;    	
    } 
        
    /**This method is used to check publication date meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationPublicationDateMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationPublicationDate=driver.findElements(By.xpath("(//meta[@name='citation_publication_date'])[1]"));
    	 return isElementPresent(citationPublicationDate);
    }
    
    /**This method returns the citation publication date meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationPublicationDateMetaTagPropertyValue() throws Exception {
    	String citationPublicationDate=getMetaTagAttribute(citationPulicationDateMetaTag,"content");
    	return citationPublicationDate;    	
    } 
    
    /**This method is used to check citation author meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationAuthorMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationAuthor=driver.findElements(By.xpath("(//meta[@name='citation_author'])[1]"));
    	 return isElementPresent(citationAuthor);
    }
    
    /**This method returns the citation author meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationAuthorMetaTagPropertyValue() throws Exception {
    	String citationAuthor=getMetaTagAttribute(citationAuthorMetaTag,"content");
    	return citationAuthor;    	
    } 

    /**This method is used to check citation DOI meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationDOIMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationDOI=driver.findElements(By.xpath("(//meta[@name='citation_doi'])[1]"));
    	 return isElementPresent(citationDOI);
    }
    
    /**This method returns the citation DOI meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationDOIMetaTagPropertyValue() throws Exception {
    	String citationDOI=getMetaTagAttribute(citationDOIMetaTag,"content");
    	return citationDOI;    	
    } 
    
    /**This method is used to check article tag meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyArticleTagMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> articleTag=driver.findElements(By.xpath("(//meta[@name='article_tag'])[1]"));
    	 return isElementPresent(articleTag);
    }
    
    /**This method returns the article tag meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getArticleTagMetaTagPropertyValue() throws Exception {
    	String articleTag=getMetaTagAttribute(articleTagMetaTag,"content");
    	return articleTag;    	
    } 
    
    /**This method is used to citation reference tag meta tag is present on article page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public boolean VerifyCitationReferenceMetaTagisPresentOnArticlePage() throws Exception {
    	List<WebElement> citationReference=driver.findElements(By.xpath("(//meta[@name='citation:reference'])[1]"));
    	 return isElementPresent(citationReference);
    }
    
    /**This method returns the citation reference meta tag content attribute value on article page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/08/2024
     */
    public String getCitationReferenceMetaTagPropertyValue() throws Exception {
    	String citationReference=getMetaTagAttribute(citationReferenceMetaTag,"content");
    	return citationReference;    	
    } 
    
    /**
     * This method returns first Author name on article page.
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/08/2024
     */
    public String getFirstAuthorNameText() throws Exception {
        String FirstAuthorName = getTextFromElement(firstAuthorText);
        return FirstAuthorName;
    }
    
    
    @FindBy(xpath = "(//a[contains(text(),'Get Permissions')])[1]//following-sibling::button")
    private WebElement citationButton;
    @FindBy(xpath = "//header[contains(text(),'Preview/Export Citation')]")
    private WebElement PreviewExportCitationPopUp;
    @FindBy(xpath = "//Select[@id='Citation Format']")
    private WebElement citationFormatDropdown;
    @FindBy(xpath = "//button[contains(text(),'.ris')]")
    private WebElement RISButton;
    @FindBy(xpath = "//button[contains(text(),'.bib')]")
    private WebElement BIBButton;
    @FindBy(xpath = "//button[contains(text(),'.enw')]")
    private WebElement ENWButton;
    @FindBy(xpath = "//header[contains(text(),'Preview/Export Citation')]//following-sibling::button[@aria-label='Close']")
    private WebElement PreviewExportCitationPopUpCloseButton;
    @FindBy(xpath = "//h2[contains(text(),'Preview Citation')]")
    private WebElement PreviewCitationSection;
    @FindBy(xpath = "//h2[contains(text(),'Export Citation')]")
    private WebElement ExportCitationSection;
    @FindBy(xpath = "//p[@id='previewCitationContainer']")
    private WebElement abbreviatedJournalTitle;
    @FindBy(xpath = "(//div[@class='title'])[1]")
    private WebElement articleHeader;
    @FindBy(xpath = "(//img[contains(@alt,'Cover Anesthesia Progress')])[1]")
    private WebElement journalCover;
    @FindBy(xpath = "((//div[contains(@data-testid,'contributors')])//div//span)[1]")
    private WebElement firstauthor;
    @FindBy(xpath = "(//div[@class='affiliation'])[1]")
    private WebElement authorAffiliationPopupLabel;
    @FindBy(xpath = "(//span[contains(text(),'Tools')]//parent::button[@role='button'])[1]")
    private WebElement toolsButton;
    @FindBy(xpath = "(//button[text()='Share via Email'])[1]")
    private WebElement shareViaEmailButton;
    @FindBy(xpath = "//header[text()='Share Link']")
    private WebElement shareLinkPopupHeader;
    @FindBy(xpath = "//button[text()='Email this content']")
    private WebElement emailThisContentButton;
    @FindBy(xpath = "//button[text()='Copy link']")
    private WebElement copyLinkButton;
    @FindBy(xpath = "//textarea[contains(text(),'.com')]")
    private WebElement copyLinkDirectly;
    @FindBy(xpath = "//header[text()='Share Link']//following-sibling::button")
    private WebElement shareLinkPopupCloseButton;
    @FindBy(xpath = "(//button[text()='Google Scholar'])[1]")
    private WebElement googleScholarButton;
    @FindBy(xpath = "(//a[text()='Similar articles in Google Scholar'])[1]")
    private WebElement similarArticleInGoogleScolar;
    @FindBy(xpath = "(//button[text()='PubMed'])[1]")
    private WebElement pubMedButton;
    @FindBy(xpath = "(//img[contains(@alt,'Article has an altmetric score')])[1]")
    private WebElement AltmetricBadge;
    @FindBy(xpath = "//button[text()='Copy link'][@aria-expanded='true']")
    private WebElement linkCopiedMessageDisplay;
    @FindBy(xpath = "((//div[@data-testid='GoogleScholarWidget-container'])[1]//li//a)[1]")
    private WebElement firstAuthorInGoogleSection;
    @FindBy(xpath = "((//div[@data-testid='PubMedWidget-container'])[1]//li//a)[1]")
    private WebElement firstAuthorInPubMedSection;
    @FindBy(xpath = "(//button[text()='Tools'])[1]")
    private WebElement toolsButtonInActionBar;
    @FindBy(xpath="//a[@data-testid='Metadata-doi-link']")
    private WebElement DOIMetaData;
    @FindBy(xpath="//div[@class='citation-doi']")
    private WebElement DOIMetaDataOnMeridianAllenPress;
    @FindBy(xpath="//button[text()='Copy to clipboard']")
    private WebElement copyToClipboard;
    @FindBy(xpath="//div[@data-status='success']//button[@aria-label='Close']")
    private WebElement toastMessageCloseButton;
    @FindBy(xpath="//a[text()='Citations']")
    private WebElement citationsLink;
    @FindBy(xpath="//span[contains(text(),'Cited by:')]")
    private WebElement NumberOfCitationWebElement;
    @FindBy(xpath="(//span[contains(text(),'Cited by')]//following::span)[1]")
    private WebElement countOfNumberOfCitation;
    @FindBy(xpath="//span[contains(text(),'Cited By')]")
    private WebElement citedByElement;
    @FindBy(xpath="(//li[@class='css-lkygn'])[1]")
    private WebElement pagInationLinkNumber;
    @FindBy(xpath="(//a[@target='_blank' and text()='Crossref'])[1]")
    private WebElement crossRefLink; 
    @FindBy (xpath="(//span[text()='Page:'])[1]")
    private WebElement pagInationTop;
    @FindBy (xpath="(//span[text()='Page:'])[2]")
    private WebElement pagInationBottom;
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
    @FindBy(xpath="//meta[@property='twitter:description']")
    private WebElement twitterDescriptionMetaTag;
    @FindBy(xpath="//meta[@property='og:title']")
    private WebElement ogTitleMetaTag;
    @FindBy(xpath="(//meta[@property='article:author'])[1]")
    private WebElement articleAuthorMetaTag;
    @FindBy(xpath="(//meta[@name='article:published_time'])[1]")
    private WebElement articlePublishTimeMetaTag;
    @FindBy(xpath="(//meta[@name='article:section'])[1]")
    private WebElement  articleSectionMetaTag;
    @FindBy(xpath="(//meta[@name='citation_lastpage'])[1]")
    private WebElement  citationLastPageMetaTag;
    @FindBy(xpath="(//meta[@name='citation_issue'])[1]")
    private WebElement  citationIssueMetaTag;
    @FindBy(xpath="(//meta[@name='citation_language'])[1]")
    private WebElement  citationLanguageMetaTag;
    @FindBy(xpath="(//meta[@name='citation_title'])[1]")
    private WebElement  citationTitleMetaTag;
    @FindBy(xpath="(//meta[@name='citation_xml_url'])[1]")
    private WebElement  citationXMLURLMetaTag;
    @FindBy(xpath="(//meta[@name='citation_volume'])[1]")
    private WebElement  citationVolumeMetaTag;
    @FindBy(xpath="(//meta[@name='citation_pdf_url'])[1]")
    private WebElement  citationPDFURLMetaTag;    
    @FindBy(xpath="(//meta[@name='citation_issn'])[1]")
    private WebElement  citationISSNMetaTag;
    @FindBy(xpath="(//meta[@name='citation_firstpage'])[1]")
    private WebElement  citationFirstPageMetaTag;
    @FindBy(xpath="(//meta[@name='citation_publisher'])[1]")
    private WebElement  citationPublisherMetaTag;
    @FindBy(xpath="(//meta[@name='citation_journal_title'])[1]")
    private WebElement  citationJoutnalTitleMetaTag;
    @FindBy(xpath="(//meta[@name='citation_publication_date'])[1]")
    private WebElement  citationPulicationDateMetaTag;
    @FindBy(xpath="(//meta[@name='citation_author'])[1]")
    private WebElement  citationAuthorMetaTag;
    @FindBy(xpath="(//meta[@name='citation_doi'])[1]")
    private WebElement  citationDOIMetaTag;
    @FindBy(xpath="(//meta[@name='article_tag'])[1]")
    private WebElement  articleTagMetaTag;
    @FindBy(xpath="(//meta[@name='citation:reference'])[1]")
    private WebElement  citationReferenceMetaTag;
    @FindBy(xpath="(//div[@data-testid='block-contributors']//span)[1]")
    private WebElement  firstAuthorText;
}
