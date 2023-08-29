package com.prime.pageFactory.pages.fpj;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;

public class SignupPage extends BasePage{
	
	/**
	 * This constructor initializes the signupPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public SignupPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}
	
	/**
	 * This method returns Header Text on SignUp page
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public String getHeaderTextonSignUpPage() throws Exception {
		String headerText = getTextFromElement(signupPageHeader);
		return headerText;
	}	
	
	/**
	 * This method used to click on SignIn Link on Signup Page()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public void clickOnSignInLinkOnSignUpPage() throws Exception {
		clickOnElement(signInLink, "Clicking on the SignIn link on SignUp Page");
	}
	
	/**
	 * This method used to enter field in the field text box on signup page
	 * 
	 * @param labelText
	 * @param enterText
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public void entertextInTextBoxOnSignUpPage(String labelText,String enterText) throws Exception {
		typeOnElement(driver.findElement(By.xpath("//label[contains(text(),'')]//parent::div//following::input[@name='"+labelText+"']")), enterText,
				"Entering the "+labelText+ " "+enterText+" in the "+labelText+" textbox on signUp page");
	}
	
	/**
	 * This method returns Header Text on SignUp page
	 * @param labelText
	 * @return String
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public String getLabelTextOnSignUpPage(String labelText) throws Exception {
		String actualLabel = getTextFromElement(driver.findElement(By.xpath("//h2[contains(text(),'Membership is free.')]//parent::div//following-sibling::div//label[contains(text(),'"+labelText+"')]")));
		return actualLabel;
	}
		
	/**
	 * This method used to accept term and condition on Signup Page()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public void acceptTermAndConditionOnSignInPage() throws Exception {
		clickOnElement(clickOnTermAndCondition, "Accepting the term and condition on SignUp Page");
	}

	/**
	 * This method used to click on Cancel link on Signup Page()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public void clickOnCancelLinkOnSignUpPage() throws Exception {
		clickOnElement(cancelLink, "Clicking on the Cancel Link on SignUp Page");
	}
	
	/**
	 * This method used to click on Submit button on Signup Page()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public void clickOnSUBMITButtonOnSignUpPage() throws Exception {
		clickOnElement(submitButton, "Clicking on the Submit button on SignUp Page");
	}
	
	/**
	 * This method returns list of error on signup page
	 *
	 * @return List<STring>
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 27/07/2023
	 */
	public List<String> getAllFieldErrorOnSignUpPage() {
		List<String> allFieldError = getTextFindElements(By.xpath("//label[contains(text(),'')]//parent::div//following::div[contains(@data-testid,'error')]"));
		return allFieldError;
	}

	/**
	 * This method used to click on show or hide password button on Signup Page()
	 * @param passOrConfpassTextBox
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 31/07/2023
	 */
	public void clickOnShowPasswordButtonOnSighUpPage(String passOrConfpassTextBox) throws Exception {
		clickOnElement(driver.findElement(By.xpath("//input[@name='"+passOrConfpassTextBox+"']//parent::div//child::div//child::button[@type]")),"Clicking on show or hide password button");
	}
	
	/**
	 * This method used to click on show or hide password button on Signup Page()
	 * @param passOrConfpassTextBox
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 31/07/2023
	 */
	public String getHideorShowPasswordOnSignUpPage(String passOrConfpassTextBox) throws Exception {
		String hideShowPassword = getAttributeFromElement(driver.findElement(By.xpath("//label[contains(text(),'')]//parent::div//following::input[@name='"+passOrConfpassTextBox+"']")), "type");
		return hideShowPassword;
	}
	
	public void clickOnVerifyCheckBoxSighUpPage() throws Exception {
		waitForVisibleAndInVisibleElement(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")), 20);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
		clickOnElement(verifyCheckBox);
		waitFor("20");
		driver.switchTo().defaultContent();
	}
	
	@FindBy (xpath="//div[@data-testid='signup-page']//h2[contains(text(),'Membership is free')]")
	private WebElement signupPageHeader; 
	@FindBy (xpath="//span[contains(text(),'I agree to the terms and conditions')]//ancestor::label")
	private WebElement clickOnTermAndCondition;
	@FindBy (xpath="//h2[contains(text(),'Membership is free')]//ancestor::div[@data-testid='signup-page']//following::a[contains(text(),'Cancel')]")
	private WebElement cancelLink;
	@FindBy (xpath="//h2[contains(text(),'Membership is free')]//ancestor::div[@data-testid='signup-page']//following::button[contains(text(),'Submit')]")
	private WebElement submitButton;
	@FindBy(xpath="//h2[contains(text(),'Membership is free')]//parent::div//following-sibling::div//p//following-sibling::a[contains(text(),'Sign in')]")
	private WebElement signInLink;
	
	@FindBy(xpath="//div[@class='recaptcha-checkbox-border']//parent::span")
	private WebElement verifyCheckBox;
}
