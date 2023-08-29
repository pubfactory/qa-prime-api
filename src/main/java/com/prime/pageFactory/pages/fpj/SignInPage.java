package com.prime.pageFactory.pages.fpj;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prime.generics.BasePage;
import com.prime.generics.WebDriverManager;

public class SignInPage extends BasePage {

	/**
	 * This constructor initializes the SignInPage class object
	 * 
	 * @param WebDriver
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public SignInPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver, this);
		waitForDocumentReady();
	}

	/**
	 * This method used to check the username is present on the header
	 * 
	 * @param userName
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifyUserNamePresentOnHeader(String userName) throws Exception {
		WebElement userNameElement = driver.findElement(
				By.xpath("//div[@data-identifier='top-menuContainer']//child::a[contains(text(),'" + userName + "')]"));
		isElementPresent(userNameElement, "Checking the username " + userName + " is present on the header");
	}

	/**
	 * This method returns the SignIn Personal Profile Page Header text on SignIn
	 * page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public String getSignInPersonalProfilePageHeaderText() throws Exception {
		String actualPersonalProfilePageHeader = getTextFromElement(SignInPageHeader);
		return actualPersonalProfilePageHeader;
	}

	/**
	 * This method used to check Sign Up link is present or not on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifySignUPLinkPresentOnSignInPage() throws Exception {
		isElementPresent(SignUpLink,
				"checking the Sign Up link is present or not on Sign in to your personal profile page");
	}

	/**
	 * This method returns UserName Email AddressLabel Text on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public String getUserNameEmailAddressLabelText() throws Exception {
		String actualUserNameEmailAddressLabel = getTextFromElement(userNameEmailAddressLabel);
		return actualUserNameEmailAddressLabel;
	}

	/**
	 * This method used to enter username or Email Address in username email Address
	 * text box On SignIn Page
	 * 
	 * @param emailID
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void enterUserNameEmailAddressInUserNameEmailAddressTextBoxOnSignInPage(String emailID) throws Exception {
		typeOnElement(userNameEmailAddressTextBox, emailID,
				"Entering the username or Email Address in username or Email Address on signIn page");
	}

	/**
	 * This method returns Password Label Text on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public String getPasswordLabelText() throws Exception {
		String actualPasswordLabel = getTextFromElement(passwordLabel);
		return actualPasswordLabel;
	}

	/**
	 * This method used to enter Password in Password text box On SigIn Page
	 * 
	 * @param password
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void enterPasswordInPasswordTextBoxOnSignINPage(String password) throws Exception {
		typeOnElement(passwordTextBox, password, "Entering the password on signin page");
	}

	/**
	 * This method used to check forgot your password link is present or not on Sign
	 * in to your personal profile page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifyForgotYourPasswordLinkPresentOnSignInPage() throws Exception {
		isElementPresent(forgotYourPasswordLink,
				"checking the forgot your password link is present or not on Sign in to your personal profile page");
	}

	/**
	 * This method used to check SUBMIT Button is present or not on Sign in to your
	 * personal profile page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verify_SUBMIT_ButtonPresentOnSignInPage() throws Exception {
		isElementPresent(SubmitButton,
				"checking the SUBMIT Button is present or not on Sign in to your personal profile page");
	}

	/**
	 * This method is used to click on SUBMIT button on SIgnIn Page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void clickOnSUBMITButtonOnSIgnInPage() throws Exception {
		clickOnElement(SubmitButton, "Clicking on SUBMIT button on SIgnIn Page");
	}

//	public String getUserNameTextonHeader1() throws Exception {
//		WebElement element = WebDriverManager.getDriver()
//				.findElement(By.xpath("//div[@data-identifier='<search_input>']//following-sibling::div//a[1]"));
//		String userNameText = getTextFromElement(element);
//		return userNameText;
//	}

	/**
	 * This method used to click on SignUp link on the header()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 20/07/2023
	 */
	public void clickOnsignOutlinkOnHeader() throws Exception {
		clickOnElement(signOutlinkOnHeader, "Clicking on SignOut link on the header");
	}

	/**
	 * This method used to check signOut link is present on header after
	 * successfully login
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifySignOutLinkpresentAfterSuccessfullyLoginOnHeader() throws Exception {
		isElementPresent(signOutlinkOnHeader,
				"checking the signOut link is present or not on header after successfully login");
	}

	/**
	 * This method used to check the username is present on the header
	 * @param userName
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 25/07/2023
	 */
	public void verifyUserNameNotPresentOnHeader(String userName) throws Exception {
		WebElement userNameElement = driver.findElement(
				By.xpath("//div[@data-identifier='top-menuContainer']//child::a[contains(text(),'" + userName + "')]"));
		isElementNotPresent(userNameElement, "Checking the username " + userName + " is not present on the header");
	}

	/**
	 * This method used to click on show password button on SignIn Page()
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 26/07/2023
	 */
	public void clickOnShowPasswordButtonOnSignInPage() throws Exception {
		clickOnElement(showPasswordButton, "Clicking on show password Button on SignIn Page");
	}

	/**
	 * This method returns hide Password Text on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 26/07/2023
	 */
	public String getHidePassword() throws Exception {
		String hidePassword = getAttributeFromElement(passwordTextBox, "type");
		return hidePassword;
	}

	/**
	 * This method returns show Password  Text on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 26/07/2023
	 */
	public String getShowPassword() throws Exception {
		String hidePassword = getAttributeFromElement(passwordTextBox, "type");
		return hidePassword;
	}

	/**
	 * This method returns error message Text on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 26/07/2023
	 */
	public String getErrorMessage(String text) throws Exception {
		String PasswordErrorMessage = getTextFromElement(driver.findElement(By.xpath("//label[contains(text(),'"+text+"')]//following-sibling::div[contains(@class,'error')]")));
		return PasswordErrorMessage;
	}

	/**
	 * This method returns error message color on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 26/07/2023
	 */
	public String getErrorMessageColor() throws Exception {
		String color = passwordErrorMessage.getCssValue("color");
		return color;
	}

	/**
	 * This method returns header error message on SignIn page
	 * 
	 * @throws Exception
	 * @author Rakesh.Shevale
	 * @Created Date : 26/07/2023
	 */
	public String getHeaderErrorMessageOnSignInPage() throws Exception {
		String headerErrorMessage = getTextFromElement(headerErrMsgOnSignInPage);
		return headerErrorMessage;
	}
	
	@FindBy(xpath = "//div[@data-testid]//h2")
	private WebElement SignInPageHeader;
	@FindBy(xpath = "//div[@data-testid]//following::p[contains(text(),'have an account')]")
	private WebElement dontHaveAnAccountLable;
	@FindBy(xpath = "//p[contains(text(),'have an account')]//following-sibling::a[contains(text(),'Sign Up')]")
	private WebElement SignUpLink;
	@FindBy(xpath = "//label[contains(text(),'Username/Email Address')]")
	private WebElement userNameEmailAddressLabel;
	@FindBy(xpath = "//label[contains(text(),'Password')]")
	private WebElement passwordLabel;
	@FindBy(xpath = "//label[contains(text(),'Username/Email Address')]//following-sibling::input")
	private WebElement userNameEmailAddressTextBox;
	@FindBy(xpath = "//label[contains(text(),'Password')]//following-sibling::div//input")
	private WebElement passwordTextBox;
	@FindBy(xpath = "//input[@name='password']//parent::div//child::div//child::button[@type]")
	private WebElement showPasswordButton;
	@FindBy(xpath = "//a[contains(text(),'Forgot your password')]")
	private WebElement forgotYourPasswordLink;
	@FindBy(xpath = "//a[contains(text(),'Forgot your password')]//following-sibling::button")
	private WebElement SubmitButton;
	@FindBy(xpath = "//div[contains(text(),'Please enter a valid Username')]")
	private WebElement userNameErrorMessage;
	@FindBy(xpath = "//div[contains(text(),'Please enter your Password')]")
	private WebElement passwordErrorMessage;
	@FindBy(xpath = "//input[@type='password']")
	private WebElement encryptedPassword;
	@FindBy(xpath = "//input[@type='text']")
	private WebElement nonEncryptedPassword;
	@FindBy(xpath = "//div[@data-identifier='<search_input>']//following-sibling::div//a[contains(text(),'Sign out')]")
	private WebElement signOutlinkOnHeader;
	@FindBy(xpath="//div[contains(text(),'You must correct the following errors before continuing.')]//following-sibling::div//ul//li")
	private WebElement headerErrMsgOnSignInPage;
}
