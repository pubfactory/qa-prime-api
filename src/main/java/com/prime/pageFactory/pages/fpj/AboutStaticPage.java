package com.prime.pageFactory.pages.fpj;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class AboutStaticPage extends BasePage{

	
	/**
     * This constructor initializes the AboutStaticPage class object
     * 
     * @param WebDriver
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 27/07/2023
     */
    public AboutStaticPage(WebDriver driver) throws Exception {
        super(driver);
        PageFactory.initElements(driver, this);
        waitForDocumentReady();
    }
    
    
    
    
    
    
    /**This method is used to check og:url meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean ogURLMetaTagisPresentOnAboutStaticPage() throws Exception {
    	Thread.sleep(4000);
    	List<WebElement> OgURL=driver.findElements(By.xpath("//meta[@property='og:url']"));
    	 return isElementPresent(OgURL);
    }
    
    /**This method returns the og:url meta tag  content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getOgURLMetaTagPropertyValue() throws Exception {
    	String URL=getMetaTagAttribute(ogURLMetaTag,"content");
    	return URL;
    }
	
    /**This method is used to check og:site_name meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean ogSiteNameMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> OgSiteName=driver.findElements(By.xpath("//meta[@property='og:site_name']"));
    	 return isElementPresent(OgSiteName);
    }
    
    /**This method returns the og:site_name meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getOgSiteNameMetaTagPropertyValue() throws Exception {
    	String siteName=getMetaTagAttribute(ogSiteNameMetaTag,"content");
    	return siteName;    	
    }
    
    /**This method is used to check og:type meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean ogTypeMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> ogType=driver.findElements(By.xpath("//meta[@property='og:type']"));
    	 return isElementPresent(ogType);
    }
    
    /**This method returns the og:type meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getOgTypeMetaTagPropertyValue() throws Exception {
    	String ogType=getMetaTagAttribute(ogTypeMetaTag,"content");
    	return ogType;    	
    }
    
    /**This method is used to check og:locale meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean ogLocaleMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> ogLocale=driver.findElements(By.xpath("//meta[@property='og:locale']"));
    	 return isElementPresent(ogLocale);
    }
    
    /**This method returns the og:locale meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getOgLocaleMetaTagPropertyValue() throws Exception {
    	String ogLocale=getMetaTagAttribute(ogLocaleMetaTag,"content");
    	return ogLocale;    	
    }
    
    /**This method is used to check og:image meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean ogImageMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> ogImage=driver.findElements(By.xpath("//meta[@property='og:image']"));
    	 return isElementPresent(ogImage);
    }
    
    /**This method returns the og:image meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getOgImageMetaTagPropertyValue() throws Exception {
    	String ogImage=getMetaTagAttribute(ogImageMetaTag,"content");
    	return ogImage;    	
    }
    
    /**This method is used to check twitter:card meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean VerifyTwitterCardMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> twitterCard=driver.findElements(By.xpath("//meta[@property='twitter:card']"));
    	 return isElementPresent(twitterCard);
    }
    
    /**This method returns the twitter:card meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getTwitterCardMetaTagPropertyValue() throws Exception {
    	String twitterCard=getMetaTagAttribute(twitterCardMetaTag,"content");
    	return twitterCard;    	
    }

    /**This method is used to check twitter:title meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean VerifyTwitterTitleMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> twitterTitle=driver.findElements(By.xpath("//meta[@property='twitter:title']"));
    	 return isElementPresent(twitterTitle);
    }
    
    /**This method returns the twitter:title meta tag content attribute value on Issue page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getTwitterTitleMetaTagPropertyValue() throws Exception {
    	String twitterTitle=getMetaTagAttribute(twitterTitleMetaTag,"content");
    	return twitterTitle;    	
    }
    
    /**This method is used to check og:description meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean VerifyOgDescriptionMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> ogDescription=driver.findElements(By.xpath("(//meta[@property='og:description'])[1]"));
    	 return isElementPresent(ogDescription);
    }
    
    /**This method returns the og:description meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getOgDescriptionMetaTagPropertyValue() throws Exception {
    	String ogDescription=getMetaTagAttribute(ogDescriptionMetaTag,"content");
    	return ogDescription;    	
    }
    
    /**This method is used to check og:description meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean VerifyDescriptionMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> Description=driver.findElements(By.xpath("(//meta[@name='description'])[1]"));
    	 return isElementPresent(Description);
    }
    
    /**This method returns the og:description meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getDescriptionMetaTagPropertyValue() throws Exception {
    	String ogDescription=getMetaTagAttribute(descriptionMetaTag,"content");
    	return ogDescription;    	
    }
    
    /**This method is used to check twitter:description meta tag is present on About Static Page
     * 
     * @return boolean
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public boolean VerifyTwitterDescriptionMetaTagisPresentOnAboutStaticPage() throws Exception {
    	List<WebElement> twitterDescription=driver.findElements(By.xpath("//meta[@property='twitter:description']"));
    	 return isElementPresent(twitterDescription);
    }
    
    /**This method returns the twitter:description meta tag content attribute value on About Static Page
     * 
     * @return String
     * @throws Exception
     * @author Rakesh.Shevale
     * @Created Date : 14/08/2024
     */
    public String getTwitterDescriptionMetaTagPropertyValue() throws Exception {
    	String twitterDescription=getMetaTagAttribute(twitterDescriptionMetaTag,"content");
    	return twitterDescription;    	
    }
    
    

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
    @FindBy(xpath="(//meta[@name='description'])[2]")
    private WebElement descriptionMetaTag;
    @FindBy(xpath="//meta[@property='twitter:description']")
    private WebElement twitterDescriptionMetaTag;

}
