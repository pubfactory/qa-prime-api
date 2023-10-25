package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		By locator = By.xpath(
				"//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button[contains(text(),'"
						+ buttontext + "')]//parent::div//div//p");
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
		clickOnElement(PreviewExportCitationPopUpCloseButton,
				"Clicking on Close button on Preview Export Citation popup");
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
		selectByValue(citationFormatDropdown, value,
				"Selecting the value " + value + " from Format dropdown on Preview Export Citation PopUp");
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
	public String getAbbreviatedJournalTitleOnCitationPopUpWhileSelectingAMAFormat() throws Exception {
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
		List<WebElement> Contentlist = driver.findElements(By.xpath("//span[@title='" + accessType
				+ "']//preceding-sibling::span//parent::span//parent::div//preceding-sibling::div//h6"));
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
		clickOnElement(RISButton,
				"clicking on .RIS button under Export citation section on Preview Export Citation PopUp");
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
		clickOnElement(BIBButton,
				"clicking on .BIB button under Export citation section on Preview Export Citation PopUp");
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
		clickOnElement(ENWButton,
				"clicking on .ENW button under Export citation section on Preview Export Citation PopUp");
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
		List<WebElement>RisButton=driver.findElements(By.xpath("(//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button)[1]"));
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
		List<WebElement>bibButton=driver.findElements(By.xpath("(//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button)[2]"));
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
		List<WebElement>enwbButton=driver.findElements(By.xpath("(//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button)[3]"));
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
	public String getLatestDownloadFileRelatedToCitation() throws Exception {
		String fileName = fetchLatestDownloadFile();
		String str1 = fileName.substring(0, 22);
		String str2 = fileName.substring((fileName.length()) - 4, fileName.length());
		String updateFileName = str1.concat(str2);
		return updateFileName;
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
		return isElementNotPresentWithTimeOut(citationButton, 7,
				"checking the cite Button is not present on Article Page");
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
		clickOnElement(journalCover,
				"clicking Journal cover on article page");
	}
	
	/**
	 * This method used clicks on first Author hyper link
	 *
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 19/q0/2023
	 */
	public void clickFirstAuthorOnArticlePage() throws Exception {
		clickOnElement(firstauthor,"clicking first Author hyperlink on article page");
	}
	

	@FindBy(xpath = "(//a[contains(text(),'Get Permissions')])[1]//following-sibling::button")
	private WebElement citationButton;
	@FindBy(xpath = "//header[contains(text(),'Preview/Export Citation')]")
	private WebElement PreviewExportCitationPopUp;
	@FindBy(xpath = "//Select[@name='format']")
	private WebElement citationFormatDropdown;
	@FindBy(xpath = "(//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button)[1]")
	private WebElement RISButton;
	@FindBy(xpath = "(//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button)[2]")
	private WebElement BIBButton;
	@FindBy(xpath = "(//h2[contains(text(),'Export Citation')]//parent::div//following-sibling::div//button)[3]")
	private WebElement ENWButton;
	@FindBy(xpath = "//header[contains(text(),'Preview/Export Citation')]//following-sibling::button[@aria-label='Close']")
	private WebElement PreviewExportCitationPopUpCloseButton;
	@FindBy(xpath = "//h2[contains(text(),'Preview Citation')]")
	private WebElement PreviewCitationSection;
	@FindBy(xpath = "//h2[contains(text(),'Export Citation')]")
	private WebElement ExportCitationSection;
	@FindBy(xpath = "(//h2[contains(text(),'Preview Citation')]//following-sibling::div//following-sibling::p)[1]")
	private WebElement abbreviatedJournalTitle;
	@FindBy(xpath = "(//div[@class='title'])[1]")
	private WebElement articleHeader;
	@FindBy(xpath="(//img[contains(@alt,'Cover Anesthesia Progress')])[1]")
	private WebElement journalCover;
	@FindBy(xpath="((//div[@data-testid='contributors'])[1]//div//button//span)[1]")
	private WebElement firstauthor;
	@FindBy(xpath="(//div[@class='affiliation'])[1]")
	private WebElement authorAffiliationPopupLabel;
}
