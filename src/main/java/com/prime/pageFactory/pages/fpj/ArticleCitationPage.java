package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.Arrays;
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
	 * @Created Date : 07/07/2023
	 */
	public void verifyCitationButtonPresentOnArticlePage() throws Exception {
		isElementPresent(citationButton, "checking the cite Button is present or not on Article Page");
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
//		String[] exptdArr = extdText.split(",");
//		List<String> exptdList = Arrays.asList(exptdArr);
//		for(int i=0;i<exptdList.size();i++) {
//				assertExpectedActualValue(exptdList.get(i),actualList.get(i), "checking the Export Citation Format text");
//		}
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
	 * @Created Date : 07/07/2023
	 */
	public void verifyPreviewExportCitationCloseButtonPresentOnPreviewExportCitationPopUp() throws Exception {
		isElementPresent(PreviewExportCitationPopUpCloseButton,
				"checking the Preview Export Citation PopUp Close Button is present or not on Preview Export Citation PopUp");
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
	public void clickArticleOnArticlePagewrtContentType(String contentType) throws Exception {
		List<WebElement> Contentlist = driver.findElements(By.xpath("//span[@title='" + contentType
				+ "']//preceding-sibling::span//parent::span//parent::div//preceding-sibling::div//h6"));
		clickOnElement(Contentlist.get(0), "Clicking on content type " + contentType + " on article page");
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
	public void verifyRISButtonIsPresentOnPreviewExportCitationOnPopup() throws Exception {
		isElementPresent(RISButton,
				"checking the RIS Button is present or not under Export citation section on Preview Export Citation PopUp");
	}

	/**
	 * This method used to check Export Citation BIB Button is present under Export
	 * citation section on Preview Export Citation PopUp
	 *
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 10/07/2023
	 */
	public void verifyBIBButtonIsPresentOnPreviewExportCitationOnPopup() throws Exception {
		isElementPresent(BIBButton,
				"checking the BIB Button is present or not under Export citation section on Preview Export Citation PopUp");
	}

	/**
	 * This method used to check Export Citation ENW Button is present under Export
	 * citation section on Preview Export Citation PopUp
	 *
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 10/07/2023
	 */
	public void verifyENWButtonIsPresentOnPreviewExportCitationOnPopup() throws Exception {
		isElementPresent(ENWButton,
				"checking the ENW Button is present or not under Export citation section on Preview Export Citation PopUp");
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
		return fetchLatestDownloadFile();
	}

	@FindBy(xpath = "//button[@title='Cite']")
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
}
