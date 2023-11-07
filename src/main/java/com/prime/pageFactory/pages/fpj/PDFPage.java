
package com.prime.pageFactory.pages.fpj;
 
import java.util.List;
import org.openqa.selenium.By;
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
        List<WebElement> pdfbutton = driver.findElements(By.xpath("//div[@data-identifier='<toolbar>']//a[contains(text(),'Download PDF')]"));
        return isElementPresent(pdfbutton);
    }
 
    /**
     * This method used to fetch the latest downloaded file name
     *
     * @return String
     * @throws Exception
     * @author Veena.Mathew
     * @Created Date : 06/11/2023
     */
    public String getLatestDownloadFileRelatedToPDF() throws Exception {
    	String fileName =fetchLatestDownloadFile();
        String[] str1 = fileName.split("-");
       List<String>l= Helper.INSTANCE.convertArrayToList(str1);
       String articleID=(Helper.INSTANCE.convertArrayToList(l.get(2).split("pd")).get(0)+"xml");
       return articleID;
    }
 
    
    public boolean hoverOnPdfButton() throws Exception {
        return mouseOver(downloadPDFIntoolBar,"");
    }
    
    /** This method is used to check Inline PDF tab is present on article page
     * 
     * @throws Exception
     * @author Rakesh.Shevale
     * @return boolean
     * @Created Date : 07/11/2023
     */
    public boolean verifyInlinePDFTabIsPresentOnArticlePage() throws Exception {
		List<WebElement> element = driver.findElements(By.xpath("//button[contains(text(),'Inline PDF')]"));
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
    
    public String getDefaultPDFZoomValueInInlinePDFTab() throws Exception {
    	 String zoomValue= getDefaultDropDownValue(pdfDefaultZoomValue);
    	 return zoomValue;
    }
    
    @FindBy(xpath = "//div[@data-identifier='<toolbar>']//a[contains(text(),'Download PDF')]")
    private WebElement downloadPDFIntoolBar;
    @FindBy(xpath = "//button[contains(text(),'Inline PDF')]")
    private WebElement inlinePDF;
    @FindBy(xpath = "//span[@id='scaleSelectContainer']/select")
    private WebElement zoomDropDownPDF;
    @FindBy(xpath = "//button[@id='zoomIn']")
    private WebElement zoomInPDF;
    @FindBy(xpath = "//button[@id='zoomOut']")
    private WebElement zoomOutPDF;
    @FindBy(xpath = "//input[@id='pageNumber']")
    private WebElement pageNumberInputPDF;
    @FindBy(xpath = "//span[@role='presentation' and contains(text(),'anesthesiaprogress')]")
    private WebElement watermarkPDF;
    @FindBy(xpath="//option[@selected='selected']")
    private WebElement pdfDefaultZoomValue;
	
	}