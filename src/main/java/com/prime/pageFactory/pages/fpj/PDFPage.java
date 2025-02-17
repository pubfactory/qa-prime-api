
package com.prime.pageFactory.pages.fpj;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.prime.generics.BasePage;
import com.prime.generics.Helper;

public class PDFPage extends BasePage {

    /**
     * This constructor initializes the PDFPage class object
     * 
     * @param WebDriver
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 27/07/2023
     */
    public PDFPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForDocumentReady();
    }

    /**
     * This method is used to click on the download PDF button on article page
     * 
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 06/11/2023
     */
    public void clickOnDownloadPDFButtonOnArticlePage() throws Exception {
        clickOnElement(downloadPDFIntoolBar, "Clicking on Download PDF button on Article Page");
    }

    /** This method is used to check PDF button is present on article page
     * 
     * @throws Exception
     * @author Veena.Mathew
     * @return
     * @Created Date : 06/11/2023
     */
    public boolean verifyPDFButtonPresentOnArticlePage() throws Exception {
        List<WebElement> pdfbutton = driver.findElements(By.xpath("(//div[@data-testid='block-downloadpdfbutton']//child::a)[1]"));
        return isElementPresent(pdfbutton);
    }

    /**
     * This method used to fetch the latest downloaded file name
     *
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 06/11/2023
     */
    public String getLatestDownloadFileRelatedToPDF() throws Exception {
   //     String filePdfName = getDownloadPDFFileNames();
    	String filePdfName = fetchLatestDownloadFileWithExtension(".pdf");
        System.out.println("Download Filename : "+filePdfName);
        String[] splitPDF = filePdfName.split("-");
        List<String> splitPDF1 = Helper.INSTANCE.convertArrayToList(splitPDF);
        String articleID = (Helper.INSTANCE.convertArrayToList(splitPDF1.get(2).split("pd")).get(0) + "xml");
        return articleID;
    }

    /**
     * This method used to verify pdf file is downloaded
     *
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 28/12/2023
     */
    public boolean toVerifyPDFFIleIsDownload() throws Exception {
    	
    	return verifyFileISDowloadedwithExtension(".pdf");
    }

    /** This method is used to check Inline PDF tab is present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 07/11/2023
     */
    public boolean verifyInlinePDFTabIsPresentOnArticlePage() throws Exception {
        //                         List<WebElement> element = driver.findElements(By.xpath("//button[contains(text(),'Inline PDF')]"));
        List<WebElement> element = driver.findElements(By.xpath("//button[text()='Full Text']//following::button[text()='PDF']"));
        return isElementPresent(element);
    }

    /**
     * This method is used to clicks on the Inline PDF tab on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 07/11/2023
     */
    public void clickOnInlinePdfTabOnArticlePage() throws Exception {
    	mouseOver(inlinePDF, "");
        clickOnElement(inlinePDF, "Clicking on Inline PDF tab on Article Page");
    }

    /** This method is used to check Zoom in button is present on Inline PDF tab on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 07/11/2023
     */
    public boolean verifyZoomInButtonIsPresentInInlinePDFTabOnArticlePage() throws Exception {
        List<WebElement> element = driver.findElements(By.xpath("//button[@id='zoomIn']"));
        return isElementPresent(element);
    }

    /** This method is used to check Zoom Out button is present on Inline PDF tab on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 07/11/2023
     */
    public boolean verifyZoomOutButtonIsPresentInInlinePDFTabOnArticlePage() throws Exception {
        List<WebElement> element = driver.findElements(By.xpath("//button[@id='zoomOut']"));
        return isElementPresent(element);
    }

    /**
     * This method return default zoom value from PDFPageSize drop down from PDF viewer in Inline PDF tab on article page
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public String getDefaultPDFZoomValueInInlinePDFTab() throws Exception {
        String defaultValue = getTextFromElement(defaultPDFZoomValue);
        return defaultValue;
    }

    /**
     * This method is used to clicks on the ZoomOut button from PDF viewer in Inline PDF tab on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public void clickOnZoomOutButton() throws Exception {
        clickOnElement(zoomOutPDF, "Clicking on pdf zoom out button");
    }

    /**
     * This method is used to clicks on the ZoomOut button in PDF viewer in Inline PDF tab on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public void clickOnZoomInButton() throws Exception {
        clickOnElement(zoomInPDF, "Clicking on pdf zoom in button");
    }

    /** This method is used to check PDF page size changed after clicking on the zoom in button in PDF tab
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 08/11/2023
     */
    public boolean VerifyPDFSizeChangesWhenClickOnZoomInButtonAtHundredPercentZoom() throws Exception {
        //           List<WebElement> element = driver.findElements(By.xpath("(//div[@class='canvasWrapper'][contains(@style,'width: 791px')])[1]"));
        WebElement element = driver.findElement(By.xpath("(//div[@class='canvasWrapper'][contains(@style,'width: 633px')])[1]"));
        scroll(element);
        List<WebElement> elements = driver.findElements(By.xpath("(//div[@class='canvasWrapper'][contains(@style,'width: 633px')])[1]"));
        return isElementPresent(elements);
    }

    /**
     * This method is used to Select Default zoom value from PDFSizeChange drop down in PDF tab on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public void ClickOnAutomaticZoomFromPDFZoomScaleSelectorDD() throws Exception {
        clickOnElement(automaticZoompdfZoomDD, "Selecting the on automatic zoom pdf from PDF zoom dropdown");
    }

    /** This method is used to check PDF page size changed after clicking on the zoom out button in PDF tab
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 08/11/2023
     */
    public boolean VerifyPDFSizeChangesWhenClickOnZoomOutButtonAtEightyPercentZoom() throws Exception {
        //       List<WebElement> element = driver.findElements(By.xpath("(//div[@class='canvasWrapper'][contains(@style,'width: 633px')])[1]"));
        WebElement element = driver.findElement(By.xpath("(//div[@class='canvasWrapper'][contains(@style,'width: 475px')])[1]"));
        scroll(element);
        List<WebElement> elements = driver.findElements(By.xpath("(//div[@class='canvasWrapper'][contains(@style,'width: 475px')])[1]"));
        return isElementPresent(elements);
    }

    public void scroll(WebElement Element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", Element);
    }

    /**
     * This method used to switching into PDP IFrame
     * @param driver
     * @return 
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public void switchToFrame(WebDriver driver) {
        Helper.INSTANCE.switchToIFrame(driver, pdfIFrame, "Switching to Pdf IFrame");
    }

    /**
     * This method return partial article title from PDF viewer in Inline PDF tab on article page
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 08/11/2023
     */
    public String getPartialArticleTitleFromInlinePDFTab() throws Exception {
        String text = getTextFromElement(partialArticleTitleFromInlinePDFTab);
        return text;
    }

    /** This method is used to check PDF button is not present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 09/11/2023
     */
    public boolean verifyPDFButtonIsNotPresentOnRestrictedArticleOnArticlePage() throws Exception {
        List<WebElement> pdfbutton = driver.findElements(By.xpath("//div[@data-identifier='<toolbar>']//a[contains(text(),'Download PDF')]"));
        return isElementNotPresent(pdfbutton);
    }

    /** This method is used to check Inline PDF tab is not present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 09/11/2023
     */
    public boolean verifyInlinePDFTabIsNotPresentOnArticlePage() throws Exception {
        List<WebElement> element = driver.findElements(By.xpath("//button[contains(text(),'Inline PDF')]"));
        return isElementNotPresent(element);
    }

    /** This method is used to verify the watermark is presented on pdf  in Pdf preview in Inline pdf tab on articla page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 09/11/2023
     */
    public boolean verifyWatermarkIsPresentOnPreviewInPDFTabOnArticlePage(String waterMarkAppsName) throws Exception {
        List<WebElement> element = driver.findElements(By.xpath("(//span[@role='presentation' and contains(text(),'" + waterMarkAppsName + "')])[1]"));
        mouseOver(element.get(0),"Mouse hovering on the water mark");
	Thread.sleep(2000);
	return isElementPresent(element);
    }
    
    /**
	 * This method is returns all keywords from PDF tab(PDF PREVIEW).
	 * 
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 11/11/2024
	 */
	public String getKeywordsFromThePDFTab() throws Exception {
		String keywords = getTextFromElement(keywordsFromPDFtab);
		return keywords;
	}

    @FindBy(xpath = "(//div[@data-testid='block-downloadpdfbutton'])[1]")
    private WebElement downloadPDFIntoolBar;
    //   @FindBy(xpath = "//button[contains(text(),'Inline PDF')]")
    @FindBy(xpath = "//button[text()='Full Text']//following::button[text()='PDF']")
    private WebElement inlinePDF;
    @FindBy(xpath = "//span[@id='scaleSelectContainer']/select")
    private WebElement zoomDropDownPDF;
    @FindBy(xpath = "//button[@id='zoomIn']")
    private WebElement zoomInPDF;
    @FindBy(xpath = "//button[@id='zoomOut']")
    private WebElement zoomOutPDF;
    @FindBy(xpath = "//input[@id='pageNumber']")
    private WebElement pageNumberInputPDF;
    @FindBy(xpath = "(//span[@role='presentation' and contains(text(),'anesthesiaprogress')])[1]")
    private WebElement watermarkPDF;
  //  @FindBy(xpath = "(//select[@id='scaleSelect']//option)[1]")
    @FindBy(xpath = "(//select[@id='scaleSelect']//option)[1]")
    private WebElement defaultPDFZoomValue;
    @FindBy(xpath = "//div[contains(@data-testid,'block-pdf')]//iframe[contains(@title,'PDF Viewer')]")
    private WebElement pdfIFrame;
    @FindBy(xpath = "(//select[@id='scaleSelect']//option)[1]")
    private WebElement automaticZoompdfZoomDD;
    @FindBy(xpath = "(//div[@class='textLayer'])[1]//span[2]")
    private WebElement partialArticleTitleFromInlinePDFTab;
    @FindBy(xpath="//span[text()='Key Words:']//following::span[2]")
	private WebElement keywordsFromPDFtab;
	
}
